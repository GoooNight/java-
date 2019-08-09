import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value = "/websocket/{uid}",encoders = ServerEncoder.class)
public class MyWebSocket {
    private static int onlineCount = 0;
    private static Map<Integer, MyWebSocket> webSocketSet = new HashMap(16);
    private static Map<Integer, List<String>> info = new HashMap(16);
    private boolean state = false;
    private Session session;
    private InfoEntity infoEntity;

    @OnOpen
    public void open(Session session, @PathParam(value = "uid") Integer uid) throws Exception {
        infoEntity = new InfoEntity();
        this.session = session;
        addOnlineCount();
        if (webSocketSet.get(uid)!=null){
            infoEntity.setCode(400);
            infoEntity.setInfo("该账户已登录，强制下线");
            webSocketSet.get(uid).session.getBasicRemote().sendObject(infoEntity);
        }
        infoEntity.setCode(200);
        webSocketSet.put(uid, this);
        if (info.get(uid) == null) {
            info.put(uid, new ArrayList<String>(16));
        } else {
            for (String text : info.get(uid)) {
                infoEntity.setInfo(text);
                session.getBasicRemote().sendObject(infoEntity);
                System.out.println(text);
            }
            info.put(uid, new ArrayList<String>(16));
        }
        System.out.println("连接建立");


    }

    @OnClose
    public void close(Session session, @PathParam(value = "uid") Integer uid) {
        subOnlineCount();
        webSocketSet.put(uid,null);
        System.out.println("断开连接");
    }

    @OnMessage
    public void message(String message, Session session,@PathParam(value = "uid") Integer uid) throws IOException, EncodeException {
        System.out.println("收到消息：" + message);
        for (Integer integer : webSocketSet.keySet()) {
            if(webSocketSet.get(integer)==null){
                info.get(integer).add(message);
            }else if(!uid.equals(integer)){
                infoEntity.setInfo(message);
                webSocketSet.get(integer).session.getAsyncRemote().sendObject(infoEntity);
            }
        }
        infoEntity.setCode(100);
        infoEntity.setInfo("消息发送成功");
        session.getBasicRemote().sendObject(infoEntity);
    }
    @OnError
    public void error(Session session,Throwable e,@PathParam(value = "uid") Integer uid){
        System.out.println(e);
        System.out.println("-----链接出错-----");
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
