"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[771],{6771:function(t,e,s){s.r(e),s.d(e,{default:function(){return c}});s(7658);var o=function(){var t=this,e=t._self._c;return e("div",[e("div",{staticClass:"front-notice"},[e("i",{staticClass:"el-icon-bell",staticStyle:{"margin-right":"2px"}}),t._v("公告："+t._s(t.top))]),e("div",{staticClass:"front-header"},[t._m(0),e("div",{staticClass:"front-header-center"},[e("div",{staticClass:"front-header-nav"},[e("el-menu",{attrs:{"default-active":t.$route.path,mode:"horizontal",router:""}},[e("el-menu-item",{attrs:{index:"/front/home"}},[t._v("首页")]),e("el-menu-item",{attrs:{index:"/front/person"}},[t._v("个人中心")])],1)],1)]),e("div",{staticClass:"front-header-right"},[t.user.username?e("div",[e("el-dropdown",[e("div",{staticClass:"front-header-dropdown"},[e("img",{attrs:{src:t.user.avatar,alt:""}}),e("div",{staticStyle:{"margin-left":"10px"}},[e("span",[t._v(t._s(t.user.name))]),e("i",{staticClass:"el-icon-arrow-down",staticStyle:{"margin-left":"5px"}})])]),e("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[e("el-dropdown-item",[e("div",{staticStyle:{"text-decoration":"none"},on:{click:t.logout}},[t._v("退出")])])],1)],1)],1):e("div",[e("el-button",{on:{click:function(e){return t.$router.push("/login")}}},[t._v("登录")]),e("el-button",{on:{click:function(e){return t.$router.push("/register")}}},[t._v("注册")])],1)])]),e("div",{staticClass:"main-body"},[e("router-view",{ref:"child",on:{"update:user":t.updateUser}})],1)])},r=[function(){var t=this,e=t._self._c;return e("div",{staticClass:"front-header-left"},[e("img",{attrs:{src:s(9955),alt:""}}),e("div",{staticClass:"title"},[t._v("项目前台")])])}],i={name:"FrontLayout",data(){return{top:"",notice:[],user:JSON.parse(localStorage.getItem("xm-user")||"{}")}},mounted(){this.loadNotice()},methods:{loadNotice(){this.$request.get("/notice/selectAll").then((t=>{this.notice=t.data;let e=0;this.notice&&this.notice.length&&(this.top=this.notice[0].content,setInterval((()=>{this.top=this.notice[e].content,e++,e===this.notice.length&&(e=0)}),2500))}))},updateUser(){this.user=JSON.parse(localStorage.getItem("xm-user")||"{}")},logout(){localStorage.removeItem("xm-user"),this.$router.push("/login")}}},n=i,a=s(1001),l=(0,a.Z)(n,o,r,!1,null,"78a16a9c",null),c=l.exports},9955:function(t,e,s){t.exports=s.p+"img/logo.d55bb149.png"}}]);
//# sourceMappingURL=771.ec455e11.js.map