<template>
  <div class="manager-container">
    <!--  头部  -->
    <div class="manager-header">
      <div class="manager-header-left">
        <img src="@/assets/imgs/logo.png"/>
        <div class="title" style="font-size:18px;">课程过程管理系统</div>
      </div>

      <div class="manager-header-center">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: $route.path }">{{ $route.meta.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <div class="manager-header-right">
        <el-dropdown placement="bottom">
          <div class="avatar">
            <img :src="user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"/>
            <div>{{ user.name || '管理员' }}</div>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="goToPerson">个人信息</el-dropdown-item>
            <el-dropdown-item @click.native="$router.push('/password')">修改密码</el-dropdown-item>
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <!--  主体  -->
    <div class="manager-main">
      <!--  侧边栏  -->
      <div class="manager-main-left">
        <el-menu :default-openeds="[]" router style="border: none" :default-active="$route.path">
          <el-menu-item index="/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>
<!--          <el-menu-item index="/department">小组信息</el-menu-item>-->
<!--          <el-menu-item index="/member">小组成员</el-menu-item>-->
<!--          <el-menu-item index="/bpply">申请审批</el-menu-item>-->
 <!--      管理员-->
          <el-submenu index="info" v-if="user.role === 'ADMIN'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>信息公告</span>
            </template>
            <el-menu-item index="/notice">公告信息</el-menu-item>
            <el-menu-item index="/examPlan">考试安排</el-menu-item>
          </el-submenu>
          <el-submenu index="administration" v-if="user.role === 'ADMIN'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>行政管理</span>
            </template>
            <el-menu-item index="/college">学院信息</el-menu-item>
            <el-menu-item index="/speciality">专业信息</el-menu-item>
            <el-menu-item index="/roomPlan">教室安排</el-menu-item>
            <el-menu-item index="/classes">班级信息</el-menu-item>
          </el-submenu>
          <el-submenu index="teach" v-if="user.role === 'ADMIN'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>教学管理</span>
            </template>
            <el-menu-item index="/course">课程信息</el-menu-item>
            <el-menu-item index="/data">课程资料</el-menu-item>
            <el-menu-item index="/choice">选课管理</el-menu-item>
            <el-menu-item index="/score">我的成绩</el-menu-item>
            <el-menu-item index="/comment">网上评教</el-menu-item>
          </el-submenu>
          <el-submenu index="educational" v-if="user.role === 'ADMIN'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>教务管理</span>
            </template>
            <el-menu-item index="/apply">请假申请</el-menu-item>
            <el-menu-item index="/homework">作业提交</el-menu-item>
            <el-menu-item index="/attendance">考勤信息</el-menu-item>
            <el-menu-item index="/test">任务发布</el-menu-item>
            <el-menu-item index="/department">小组信息</el-menu-item>
            <el-menu-item index="/member">小组成员</el-menu-item>
            <el-menu-item index="/bpply">申请审批</el-menu-item>
          </el-submenu>
          <el-submenu index="user" v-if="user.role==='ADMIN'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>用户管理</span>
            </template>
            <el-menu-item index="/admin">管理员信息</el-menu-item>
            <el-menu-item index="/teacher">教师信息</el-menu-item>
            <el-menu-item index="/student">学生信息</el-menu-item>
          </el-submenu>
          <el-menu-item index="/chatroom" v-if="user.role === 'ADMIN'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>聊天室</span>
            </template>
          </el-menu-item>
          <el-menu-item index="/logs" v-if="user.role === 'ADMIN'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>日志管理</span>
            </template>
          </el-menu-item>

          <!--  学生
          <el-submenu index="curse" v-if="user.role === 'STUDENT'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>课程管理</span>
            </template>
            <el-menu-item index="/course">课程信息</el-menu-item>
            <el-menu-item index="/myCourse">我的课程</el-menu-item>
            <el-menu-item index="/curriculum">我的课表</el-menu-item>
            <el-menu-item index="/choice">我的选课</el-menu-item>
          </el-submenu>
          <el-submenu index="study" v-if="user.role === 'STUDENT'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>学习活动</span>
            </template>
            <el-menu-item index="/homework">作业提交</el-menu-item>
            <el-menu-item index="/data">课程资料</el-menu-item>
            <el-menu-item index="/test">我的任务</el-menu-item>
            <el-menu-item index="/group">小组成员管理</el-menu-item>
            <el-menu-item index="/comment">网上评教</el-menu-item>
            <el-menu-item index="/chatroom">聊天室</el-menu-item>
          </el-submenu>
          <el-submenu index="thing" v-if="user.role === 'STUDENT'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>个人事务管理</span>
            </template>
            <el-menu-item index="/score">我的成绩</el-menu-item>
            <el-menu-item index="/attendance">我的考勤</el-menu-item>
            <el-menu-item index="/apply">我的请假</el-menu-item>
          </el-submenu>
          -->

          <!--  教师
          <el-submenu index="curse" v-if="user.role === 'TEACHER'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>课程管理</span>
            </template>
            <el-menu-item index="/myCourse">课程信息</el-menu-item>
            <el-menu-item index="/curriculum">课表管理</el-menu-item>
            <el-menu-item index="/curriculum" v-if="user.role === 'STUDENT'">我的课表</el-menu-item>

          </el-submenu>
          <el-submenu index="study" v-if="user.role === 'TEACHER'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>教学活动</span>
            </template>
            <el-menu-item index="/homework">作业管理</el-menu-item>
            <el-menu-item index="/data">课程资料</el-menu-item>
            <el-menu-item index="/test">任务发布</el-menu-item>
            <el-menu-item index="/comment">评教管理</el-menu-item>
            <el-menu-item index="/chatroom">聊天室</el-menu-item>
            <el-menu-item index="/score">成绩管理</el-menu-item>
          </el-submenu>
          <el-submenu index="student" v-if="user.role === 'TEACHER'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>学生管理</span>
            </template>
            <el-menu-item index="/score">班级成绩</el-menu-item>
            <el-menu-item index="/group">小组成员管理</el-menu-item>
          </el-submenu>
          <el-submenu index="commute" v-if="user.role === 'TEACHER'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>互动与沟通</span>
            </template>
            <el-menu-item index="/notice">公告信息</el-menu-item>
            <el-menu-item index="/examPlan">考试安排</el-menu-item>
          </el-submenu>
          <el-submenu index="attendance" v-if="user.role === 'TEACHER'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>请假审批与考勤</span>
            </template>
            <el-menu-item index="/attendance">考勤记录</el-menu-item>
            <el-menu-item index="/apply">请假审批</el-menu-item>
          </el-submenu>
          -->
<!--          学生-->
          <el-submenu index="curse" v-if="user.role === 'STUDENT'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>学生选课模块</span>
            </template>
            <el-menu-item index="/course">课程信息</el-menu-item>
<!--            <el-menu-item index="/myCourse">我的课程</el-menu-item>-->
            <el-menu-item index="/curriculum">我的课表</el-menu-item>
            <el-menu-item index="/choice">我的选课</el-menu-item>
          </el-submenu>
          <el-submenu index="study" v-if="user.role === 'STUDENT'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>学生组队模块</span>
            </template>
            <el-menu-item index="/department">学生组队</el-menu-item>
            <el-menu-item index="/group">成员管理</el-menu-item>
            <el-menu-item index="/member">组队审批</el-menu-item>
          </el-submenu>
          <el-submenu index="thing" v-if="user.role === 'STUDENT'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>组队管理模块</span>
            </template>
<!--            <el-menu-item index="/attendance">我的考勤</el-menu-item>-->
<!--            <el-menu-item index="/apply">我的请假</el-menu-item>-->
            <el-menu-item index="/data">课程资料</el-menu-item>
            <el-menu-item index="/test">课程任务</el-menu-item>
            <el-menu-item index="/homework">作业提交</el-menu-item>
            <el-menu-item index="/work">组内任务分配</el-menu-item>
            <el-menu-item index="/teamwork">组内任务提交</el-menu-item>
            <el-menu-item index="/score">我的成绩</el-menu-item>
<!--            <el-menu-item index="/comment">网上评教</el-menu-item>-->
            <el-menu-item index="/chatroom">小组讨论</el-menu-item>
            <el-menu-item index="/chatroom">私信交流</el-menu-item>
          </el-submenu>

<!--          教师-->
          <el-submenu index="curse" v-if="user.role === 'TEACHER'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>选课管理模块</span>
            </template>
            <el-menu-item index="/myCourse">课程信息</el-menu-item>
          </el-submenu>
          <el-submenu index="study" v-if="user.role === 'TEACHER'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>学生组队模块</span>
            </template>
            <el-menu-item index="/department">队伍管理</el-menu-item>

<!--            <el-menu-item index="/homework">作业管理</el-menu-item>-->
<!--            <el-menu-item index="/data">课程资料</el-menu-item>-->
<!--            <el-menu-item index="/comment">评教管理</el-menu-item>-->
<!--            <el-menu-item index="/chatroom">聊天室</el-menu-item>-->
<!--            <el-menu-item index="/score">成绩管理</el-menu-item>-->
          </el-submenu>
          <el-submenu index="student" v-if="user.role === 'TEACHER'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>课程运行管理模块</span>
            </template>
<!--            <el-menu-item index="/group">小组成员管理</el-menu-item>-->
            <el-menu-item index="/course">课程阶段</el-menu-item>
            <el-menu-item index="/notice">课程公告</el-menu-item>
            <el-menu-item index="/examPlan">考试安排</el-menu-item>
            <el-menu-item index="/data">课程资料</el-menu-item>
            <el-menu-item index="/homework">课程作业</el-menu-item>
            <el-menu-item index="/test">课程任务</el-menu-item>
            <el-menu-item index="/score">成绩管理</el-menu-item>
            <el-menu-item index="/chatroom">私信交流</el-menu-item>
          </el-submenu>
<!--          <el-submenu index="commute" v-if="user.role === 'TEACHER'">-->
<!--            <template slot="title">-->
<!--              <i class="el-icon-menu"></i><span>互动与沟通</span>-->
<!--            </template>-->
<!--            <el-menu-item index="/notice">公告信息</el-menu-item>-->
<!--          </el-submenu>-->
<!--          <el-submenu index="attendance" v-if="user.role === 'TEACHER'">-->
<!--            <template slot="title">-->
<!--              <i class="el-icon-menu"></i><span>请假审批与考勤</span>-->
<!--            </template>-->
<!--            <el-menu-item index="/attendance">考勤记录</el-menu-item>-->
<!--            <el-menu-item index="/apply">请假审批</el-menu-item>-->
<!--          </el-submenu>-->

        </el-menu>
      </div>

      <!--  数据表格  -->
      <div class="manager-main-right">
        <router-view @update:user="updateUser"/>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "Manager",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },
  created() {
    console.log(this.user.role); // 检查角色值是否预期
    if (!this.user.id) {
      this.$router.push('/login')
    }
  },
  methods: {
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}')   // 重新获取下用户的最新信息
    },
    goToPerson() {
      if (this.user.role === 'ADMIN') {
        this.$router.push('/adminPerson')
      }
      if (this.user.role === 'TEACHER') {
        this.$router.push('/teacherPerson')
      }
      if (this.user.role === 'STUDENT') {
        this.$router.push('/studentPerson')
      }
    },
    logout() {
      localStorage.removeItem('xm-user')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
@import "@/assets/css/manager.css";
</style>