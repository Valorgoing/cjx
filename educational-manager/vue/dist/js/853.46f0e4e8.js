"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[853],{2853:function(e,a,t){t.r(a),t.d(a,{default:function(){return n}});var s=function(){var e=this,a=e._self._c;return a("div",[a("el-card",{staticStyle:{width:"50%"}},[a("el-form",{staticStyle:{"padding-right":"50px"},attrs:{model:e.user,"label-width":"100px"}},[a("div",{staticStyle:{margin:"15px","text-align":"center"}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:e.$baseUrl+"/files/upload","show-file-list":!1,"on-success":e.handleAvatarSuccess}},[e.user.avatar?a("img",{staticClass:"avatar",attrs:{src:e.user.avatar}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),a("el-form-item",{attrs:{label:"用户名",prop:"username"}},[a("el-input",{attrs:{placeholder:"用户名",disabled:""},model:{value:e.user.username,callback:function(a){e.$set(e.user,"username",a)},expression:"user.username"}})],1),a("el-form-item",{attrs:{label:"姓名",prop:"name"}},[a("el-input",{attrs:{placeholder:"姓名"},model:{value:e.user.name,callback:function(a){e.$set(e.user,"name",a)},expression:"user.name"}})],1),a("el-form-item",{attrs:{label:"学院",prop:"collegeName"}},[a("el-input",{attrs:{disabled:""},model:{value:e.user.collegeName,callback:function(a){e.$set(e.user,"collegeName",a)},expression:"user.collegeName"}})],1),a("el-form-item",{attrs:{label:"专业",prop:"specialityName"}},[a("el-input",{attrs:{disabled:""},model:{value:e.user.specialityName,callback:function(a){e.$set(e.user,"specialityName",a)},expression:"user.specialityName"}})],1),a("el-form-item",{attrs:{label:"班级",prop:"className"}},[a("el-input",{attrs:{disabled:""},model:{value:e.user.className,callback:function(a){e.$set(e.user,"className",a)},expression:"user.className"}})],1),a("div",{staticStyle:{"text-align":"center","margin-bottom":"20px"}},[a("el-button",{attrs:{type:"primary"},on:{click:e.update}},[e._v("保 存")])],1)],1)],1)],1)},l=[],r={name:"StudentPerson",data(){return{user:JSON.parse(localStorage.getItem("xm-user")||"{}")}},created(){},methods:{update(){this.$request.put("/student/update",this.user).then((e=>{"200"===e.code?(this.$message.success("保存成功"),localStorage.setItem("xm-user",JSON.stringify(this.user)),this.$emit("update:user")):this.$message.error(e.msg)}))},handleAvatarSuccess(e,a,t){this.$set(this.user,"avatar",e.data)}}},u=r,i=t(1001),c=(0,i.Z)(u,s,l,!1,null,"719953da",null),n=c.exports}}]);
//# sourceMappingURL=853.46f0e4e8.js.map