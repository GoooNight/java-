import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import com.alibaba.fastjson.*;

/**
 * @Description
 * @author:awei
 * @date:2019/8/7
 * @ver:1.0
 **/
public class ServerEncoder implements Encoder.Text<InfoEntity> {
    @Override
    public String encode(InfoEntity infoEntity) throws EncodeException {
        return JSONObject.toJSONString(infoEntity);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
