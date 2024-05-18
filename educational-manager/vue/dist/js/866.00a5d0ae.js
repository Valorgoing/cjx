"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[866],{1866:function(e,t,s){s.r(t),s.d(t,{default:function(){return d}});var a=function(){var e=this,t=e._self._c;return t("div",[t("div",{staticClass:"search"},[t("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"请选择课程"},model:{value:e.courseId,callback:function(t){e.courseId=t},expression:"courseId"}},e._l(e.courseSearchData,(function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.value}})})),1),t("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"info",plain:""},on:{click:function(t){return e.load(1)}}},[e._v("查询")]),t("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"warning",plain:""},on:{click:e.reset}},[e._v("重置")])],1),"TEACHER"===e.user.role?t("div",{staticClass:"operation"},[t("el-button",{attrs:{type:"primary",plain:""},on:{click:e.handleAdd}},[e._v("添加考勤")]),t("el-button",{attrs:{type:"danger",plain:""},on:{click:e.delBatch}},[e._v("批量删除")])],1):e._e(),t("div",{staticClass:"table"},[t("el-table",{attrs:{data:e.tableData,stripe:""},on:{"selection-change":e.handleSelectionChange}},["TEACHER"===e.user.role?t("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}):e._e(),t("el-table-column",{attrs:{prop:"id",label:"序号",width:"80",align:"center",sortable:""}}),t("el-table-column",{attrs:{prop:"courseName",label:"课程名称","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"teacherName",label:"授课教师","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"studentName",label:"学生姓名","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"time",label:"上课时间","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"status",label:"考勤状态","show-overflow-tooltip":""}}),"TEACHER"===e.user.role?t("el-table-column",{attrs:{label:"操作",width:"180",align:"center"},scopedSlots:e._u([{key:"default",fn:function(s){return[t("el-button",{attrs:{plain:"",type:"primary",size:"mini"},on:{click:function(t){return e.handleEdit(s.row)}}},[e._v("编辑")]),t("el-button",{attrs:{plain:"",type:"danger",size:"mini"},on:{click:function(t){return e.del(s.row.id)}}},[e._v("删除")])]}}],null,!1,4063315224)}):e._e()],1),t("div",{staticClass:"pagination"},[t("el-pagination",{attrs:{background:"","current-page":e.pageNum,"page-sizes":[5,10,20],"page-size":e.pageSize,layout:"total, prev, pager, next",total:e.total},on:{"current-change":e.handleCurrentChange}})],1)],1),t("el-dialog",{attrs:{title:"信息",visible:e.fromVisible,width:"40%","close-on-click-modal":!1,"destroy-on-close":""},on:{"update:visible":function(t){e.fromVisible=t}}},[t("el-form",{ref:"formRef",staticStyle:{"padding-right":"50px"},attrs:{"label-width":"100px",model:e.form,rules:e.rules}},[t("el-form-item",{attrs:{prop:"courseId",label:"选择课程"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择课程"},on:{change:e.getStudent},model:{value:e.form.courseId,callback:function(t){e.$set(e.form,"courseId",t)},expression:"form.courseId"}},e._l(e.courseData,(function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.value}})})),1)],1),t("el-form-item",{attrs:{prop:"studentId",label:"选择学生"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择学生"},model:{value:e.studentId,callback:function(t){e.studentId=t},expression:"studentId"}},e._l(e.studentData,(function(e){return t("el-option",{key:e.studentId,attrs:{label:e.studentName,value:e.value}})})),1)],1),t("el-form-item",{attrs:{prop:"time",label:"上课时间"}},[t("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:e.form.time,callback:function(t){e.$set(e.form,"time",t)},expression:"form.time"}})],1),t("el-form-item",{attrs:{prop:"status",label:"考勤状态"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择状态"},model:{value:e.form.status,callback:function(t){e.$set(e.form,"status",t)},expression:"form.status"}},[t("el-option",{attrs:{label:"正常",value:"正常"}}),t("el-option",{attrs:{label:"迟到",value:"迟到"}}),t("el-option",{attrs:{label:"早退",value:"早退"}}),t("el-option",{attrs:{label:"缺勤",value:"缺勤"}})],1)],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.fromVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.save}},[e._v("确 定")])],1)],1)],1)},l=[],r={name:"Attendance",data(){return{tableData:[],pageNum:1,pageSize:10,total:0,courseId:null,fromVisible:!1,form:{},user:JSON.parse(localStorage.getItem("xm-user")||"{}"),rules:{time:[{required:!0,message:"请输入上课时间",trigger:"blur"}],status:[{required:!0,message:"请输入考勤状态",trigger:"blur"}]},ids:[],courseData:[],courseSearchData:[],studentData:[],studentId:null}},created(){this.load(1),this.loadCourseByTeacher(),this.loadCourseSearch()},methods:{loadCourseSearch(){if("STUDENT"===this.user.role)this.$request.get("/choice/selectAll?studentId="+this.user.id).then((e=>{"200"===e.code&&(e.data.forEach((e=>{e.id=e.courseId})),this.courseSearchData=e.data)}));else{let e="ADMIN"===this.user.role?"/course/selectAll":"/course/selectAll?teacherId="+this.user.id;this.$request.get(e).then((e=>{"200"===e.code?this.courseSearchData=e.data:this.$message.error(e.msg)}))}},loadCourseByTeacher(){this.$request.get("/course/selectAll",{params:{teacherId:this.user.id}}).then((e=>{"200"===e.code?this.courseData=e.data:this.$message.error(e.msg)}))},getStudent(e){this.$request.get("/choice/selectAll",{params:{courseId:e}}).then((e=>{"200"===e.code?(this.studentData=e.data,this.studentId=null):this.$message.error(e.msg)}))},getStudentEdit(e){this.$request.get("/choice/selectAll",{params:{courseId:e}}).then((e=>{"200"===e.code?(this.studentData=e.data,this.studentId=this.form.studentId,this.fromVisible=!0):this.$message.error(e.msg)}))},handleAdd(){this.form={teacherId:this.user.id},this.fromVisible=!0,this.studentId=null},handleEdit(e){this.form=JSON.parse(JSON.stringify(e)),this.getStudentEdit(this.form.courseId)},save(){this.$refs.formRef.validate((e=>{e&&(this.form.studentId=this.studentId,this.$request({url:this.form.id?"/attendance/update":"/attendance/add",method:this.form.id?"PUT":"POST",data:this.form}).then((e=>{"200"===e.code?(this.$message.success("保存成功"),this.load(1),this.fromVisible=!1):this.$message.error(e.msg)})))}))},del(e){this.$confirm("您确定删除吗？","灵魂拷问",{type:"warning"}).then((t=>{this.$request.delete("/attendance/delete/"+e).then((e=>{"200"===e.code?(this.$message.success("操作成功"),this.load(1)):this.$message.error(e.msg)}))})).catch((()=>{}))},handleSelectionChange(e){this.ids=e.map((e=>e.id))},delBatch(){this.ids.length?this.$confirm("您确定批量删除这些数据吗？","确认删除",{type:"warning"}).then((e=>{this.$request.delete("/attendance/delete/batch",{data:this.ids}).then((e=>{"200"===e.code?(this.$message.success("操作成功"),this.load(1)):this.$message.error(e.msg)}))})).catch((()=>{})):this.$message.warning("请选择数据")},load(e){e&&(this.pageNum=e),this.$request.get("/attendance/selectPage",{params:{pageNum:this.pageNum,pageSize:this.pageSize,courseId:this.courseId}}).then((e=>{this.tableData=e.data?.list,this.total=e.data?.total}))},reset(){this.courseId=null,this.load(1)},handleCurrentChange(e){this.load(e)}}},o=r,i=s(1001),n=(0,i.Z)(o,a,l,!1,null,"37362d68",null),d=n.exports}}]);
//# sourceMappingURL=866.00a5d0ae.js.map