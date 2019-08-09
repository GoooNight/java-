<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/8
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="text" id="message">
<button onclick="send()">发送消息</button>
<script>
    var id = Math.floor(Math.random()*9);
    console.log(id)
    var socket = new WebSocket("ws://localhost:8080/websocket_war_exploded/websocket/" + id);
    socket.onopen = function (ws) {
        console.log("建立连接");
    }
    socket.onmessage = function (ws) {
        var code = JSON.parse(ws.data).code;
        var info = JSON.parse(ws.data).info;
        if (code == 400) {
            socket.close();
        }
        console.log("收到消息是：" + info);
    }
    socket.onclose = function (ws) {
        console.log("连接已断开");
    }

    function send() {
        var test = document.getElementById("message").value;
        socket.send(test);
    }
</script>
</body>
</html>
