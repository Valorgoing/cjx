<script>

export default {
  name: "Chatroom",
  data() {
    return {
      main: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      socket: null,
      users: [],
      messages: [],
      message: ""
    };
  },
  methods: {
    sendMessage() {
      if (this.message.trim() !== '') {
        this.socket.send(JSON.stringify({ sender: this.main.name, content: this.message }))
        this.message = ''; // 发送后清空输入框
      } else {
        this.$message({ // 使用 Element UI 的消息提示组件给出反馈
          message: '请输入消息内容',
          type: 'warning'
        });
      }
    }
  },
  mounted() {
    this.socket = new WebSocket("ws://localhost:9091/chatroom/" + this.main.role + "-" + this.main.id);
    this.socket.onmessage = (event) => {
      let data = JSON.parse(event.data);
      console.log(data);
      if (data[0]?.name !== undefined) {
        this.users = data
      } else {
        this.messages = data
      }
    };
  },
  beforeDestroy() {
    if (this.socket) {
      this.socket.close();
    }
  }
}
</script>

<template>
  <el-container style="height: 100vh;">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246);">
      <el-menu
          default-active="1"
          class="custom-menu"
      >
        <p class="header">小组成员</p>
        <p class="el-paragraph" v-for="(usr, index) in users" :key="index">
          {{ usr.name }}
          <span class="status-dot" :class="{'is-online': usr.online, 'is-offline': !usr.online}"></span>
        </p>
      </el-menu>
    </el-aside>





    <el-container class="el-container">
      <el-header style="text-align: right; padding: 20px;">
        <span>聊天室</span>
      </el-header>
      <el-main>
        <div class="chat-container">
          <div
              v-for="(message, index) in messages" :key="index"
              :class="{ 'left': message.sender !== main.name, 'right': message.sender === main.name }"
              class="bubble"
          ><p>{{ message.content }}</p></div>
        </div>


        <div class="message-sender">
          <el-input
              v-model="message"
              placeholder="请输入消息"
              class="message-input"
              @keyup.enter="sendMessage"
          ></el-input>
          <el-button
              type="primary"
              @click="sendMessage"
          >发送</el-button>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>

.status-dot {
  height: 10px;
  width: 10px;
  border-radius: 50%;
  display: inline-block;
  margin-right: 10px;
  margin-left: 5px;
}

.is-online {
  background-color: green; /* Green dot for online users */
}

.is-offline {
  background-color: gray; /* Gray dot for offline users */
}

.el-paragraph {
  margin: 10px 20px;
  font-size: 14px;
  line-height: 1.6;
  color: #dcdfe6;
}

.header {
  margin: 20px 20px;
  font-size: 16px;
  color: #dcdfe6;
  font-weight: bold;
  text-align: center;
}

/* 聊天室 */

.chat-container {
  position: relative;
  width: 100%;

  padding: 20px;
  overflow-y: auto; /* 内容超出时出现滚动条 */
  height: 70vh;
}

.bubble {
  position: relative;
  display: flex;
  width: 100%;
  margin: 10px 0;
}

.bubble p {
  position: relative;
  right: 0;
  text-align: left;
  max-width: 65%;
  padding: 12px;
  border-radius: 10px;

  font-size: 1.1em;
}

.right {
  justify-content: flex-end;
}

.right p {
  background: #eef5ff;
}

.left {
  justify-content: flex-start;
}

.left p {
  background: #ffffff;
}



/* 发送消息框 */

.message-sender {
  position: relative;
  width: 100%;
  height: 60px;
  padding: 15px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.message-input {
  position: relative;
  width: 90%;
  margin: 0 10px;
  padding: 10px 5px;
  border: none;
  outline: none;
  border-radius: 30px;
}

</style>