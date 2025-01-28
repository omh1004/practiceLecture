<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>채팅화면</title>
</head>
<body>
  <div class="chatting-container">
    <input type="text" id="msg"><button onclick="chattingHandeler()">전송</button>
    <div class="chatting-content"></div>
  </div>
<script>
  const user = '${loginMember.userId}';
  const socket = new WebSocket("ws://localhost:8080/spring/chatting");
  socket.onopen=(data)=>{
    console.log(data);
    sendMsg(new Message("open",user,"","",""));
  }
  socket.onmessage=(response)=>{
      const message=JSON.parse(response.data);
      switch(message.type){
          case "open":printMsg(message);break;
          case "chat":chatdata(message);break;

      }
  }

  const chatdata=(data)=>{

      debugger;
      const container=document.createElement("div");
      container.style.width="100%";
      container.style.display="flex";
      const sup=document.createElement("sup");
      sup.innerText=data.sender;
      const h4= document.createElement("h4");
      h4.innerText=data.msg;
      container.appendChild(sup);
      container.appendChild(h4);
      if(data.sender==user) container.style.justifyContent="end";
      else  container.style.justifyContent="start";
      document.querySelector(".chatting-container").appendChild(container);
  }



  const printMsg=(msg)=>{
      const container = document.createElement("div");
      const content=document.createElement("h3");
      content.style.textAlign='center';
      content.innerText=msg.msg;
      container.appendChild(content);
      document.querySelector(".chatting-container").appendChild(container);
  }

  const sendMsg=(data)=>{
    socket.send(JSON.stringify(data));
  }
  const chattingHandeler=()=>{
      const msg=document.querySelector("#msg").value;
      const sendData= new Message("chat",user,"",msg,"");
      sendMsg(sendData);
  }

  class Message{
    constructor(type,sender,receiver="",msg,room="") {
      this.type = type;
      this.sender = sender;
      this.receiver = receiver;
      this.msg = msg;
      this.room = room;
    }
  }
</script>
</body>
</html>
