"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[660],{9660:function(e,t,s){s.r(t),s.d(t,{default:function(){return o}});s(7658);var a=function(){var e=this,t=e._self._c;return t("div",{staticClass:"manager-container"},[t("div",{staticClass:"manager-header"},[e._m(0),t("div",{staticClass:"manager-header-center"},[t("el-breadcrumb",{attrs:{"separator-class":"el-icon-arrow-right"}},[t("el-breadcrumb-item",{attrs:{to:{path:"/"}}},[e._v("首页")]),t("el-breadcrumb-item",{attrs:{to:{path:e.$route.path}}},[e._v(e._s(e.$route.meta.name))])],1)],1),t("div",{staticClass:"manager-header-right"},[t("el-dropdown",{attrs:{placement:"bottom"}},[t("div",{staticClass:"avatar"},[t("img",{attrs:{src:e.user.avatar||"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"}}),t("div",[e._v(e._s(e.user.name||"管理员"))])]),t("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[t("el-dropdown-item",{nativeOn:{click:function(t){return e.goToPerson.apply(null,arguments)}}},[e._v("个人信息")]),t("el-dropdown-item",{nativeOn:{click:function(t){return e.$router.push("/password")}}},[e._v("修改密码")]),t("el-dropdown-item",{nativeOn:{click:function(t){return e.logout.apply(null,arguments)}}},[e._v("退出登录")])],1)],1)],1)]),t("div",{staticClass:"manager-main"},[t("div",{staticClass:"manager-main-left"},[t("el-menu",{staticStyle:{border:"none"},attrs:{"default-openeds":[],router:"","default-active":e.$route.path}},[t("el-menu-item",{attrs:{index:"/home"}},[t("i",{staticClass:"el-icon-s-home"}),t("span",{attrs:{slot:"title"},slot:"title"},[e._v("系统首页")])]),t("el-submenu",{attrs:{index:"info"}},[t("template",{slot:"title"},[t("i",{staticClass:"el-icon-menu"}),t("span",[e._v("信息公告")])]),"ADMIN"===e.user.role?t("el-menu-item",{attrs:{index:"/notice"}},[e._v("公告信息")]):e._e(),"ADMIN"===e.user.role?t("el-menu-item",{attrs:{index:"/examPlan"}},[e._v("考试安排")]):e._e(),t("el-menu-item",{attrs:{index:"/roomPlan"}},[e._v("教室安排")])],2),t("el-submenu",{attrs:{index:"administration"}},[t("template",{slot:"title"},[t("i",{staticClass:"el-icon-menu"}),t("span",[e._v("行政管理")])]),t("el-menu-item",{attrs:{index:"/college"}},[e._v("学院信息")]),t("el-menu-item",{attrs:{index:"/speciality"}},[e._v("专业信息")]),t("el-menu-item",{attrs:{index:"/classes"}},[e._v("班级信息")])],2),t("el-submenu",{attrs:{index:"teach"}},[t("template",{slot:"title"},[t("i",{staticClass:"el-icon-menu"}),t("span",[e._v("教学管理")])]),t("el-menu-item",{attrs:{index:"/course"}},[e._v("课程信息")]),t("el-menu-item",{attrs:{index:"/choice"}},[e._v("我的选课")]),"STUDENT"===e.user.role?t("el-menu-item",{attrs:{index:"/curriculum"}},[e._v("我的课表")]):e._e(),t("el-menu-item",{attrs:{index:"/score"}},[e._v("我的成绩")]),t("el-menu-item",{attrs:{index:"/comment"}},[e._v("网上评教")])],2),t("el-submenu",{attrs:{index:"educational"}},[t("template",{slot:"title"},[t("i",{staticClass:"el-icon-menu"}),t("span",[e._v("教务管理")])]),"TEACHER"!==e.user.role?t("el-menu-item",{attrs:{index:"/apply"}},[e._v("请假申请")]):e._e(),t("el-menu-item",{attrs:{index:"/homework"}},[e._v("作业提交")]),t("el-menu-item",{attrs:{index:"/attendance"}},[e._v("考勤信息")])],2),"ADMIN"===e.user.role?t("el-submenu",{attrs:{index:"user"}},[t("template",{slot:"title"},[t("i",{staticClass:"el-icon-menu"}),t("span",[e._v("用户管理")])]),t("el-menu-item",{attrs:{index:"/admin"}},[e._v("管理员信息")]),t("el-menu-item",{attrs:{index:"/teacher"}},[e._v("教师信息")]),t("el-menu-item",{attrs:{index:"/student"}},[e._v("学生信息")])],2):e._e()],1)],1),t("div",{staticClass:"manager-main-right"},[t("router-view",{on:{"update:user":e.updateUser}})],1)])])},r=[function(){var e=this,t=e._self._c;return t("div",{staticClass:"manager-header-left"},[t("img",{attrs:{src:s(9955)}}),t("div",{staticClass:"title",staticStyle:{"font-size":"18px"}},[e._v("课程过程管理系统")])])}],n={name:"Manager",data(){return{user:JSON.parse(localStorage.getItem("xm-user")||"{}")}},created(){this.user.id||this.$router.push("/login")},methods:{updateUser(){this.user=JSON.parse(localStorage.getItem("xm-user")||"{}")},goToPerson(){"ADMIN"===this.user.role&&this.$router.push("/adminPerson"),"TEACHER"===this.user.role&&this.$router.push("/teacherPerson"),"STUDENT"===this.user.role&&this.$router.push("/studentPerson")},logout(){localStorage.removeItem("xm-user"),this.$router.push("/login")}}},i=n,l=s(1001),u=(0,l.Z)(i,a,r,!1,null,"05afacec",null),o=u.exports},9955:function(e,t,s){e.exports=s.p+"img/logo.d55bb149.png"}}]);
//# sourceMappingURL=660.9288c751.js.map