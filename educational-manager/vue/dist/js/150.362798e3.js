"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[150],{3150:function(e,t,l){l.r(t),l.d(t,{default:function(){return c}});var a=function(){var e=this,t=e._self._c;return t("div",[t("div",{staticClass:"search"},[t("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入课程名称"},model:{value:e.name,callback:function(t){e.name=t},expression:"name"}}),t("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"info",plain:""},on:{click:function(t){return e.load(1)}}},[e._v("查询")]),t("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"warning",plain:""},on:{click:e.reset}},[e._v("重置")])],1),"ADMIN"===e.user.role?t("div",{staticClass:"operation"},[t("el-button",{attrs:{type:"primary",plain:""},on:{click:e.handleAdd}},[e._v("新增")]),t("el-button",{attrs:{type:"danger",plain:""},on:{click:e.delBatch}},[e._v("批量删除")])],1):e._e(),t("div",{staticClass:"table"},[t("el-table",{attrs:{data:e.tableData,stripe:""},on:{"selection-change":e.handleSelectionChange}},["ADMIN"===e.user.role?t("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}):e._e(),t("el-table-column",{attrs:{prop:"id",label:"序号",width:"80",align:"center",sortable:""}}),t("el-table-column",{attrs:{prop:"name",label:"课程名称","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"type",label:"课程类型","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"teacherName",label:"授课教师","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"score",label:"学分","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"num",label:"上课人数","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"room",label:"上课教室","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"week",label:"周几","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"segment",label:"第几大节","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{prop:"status",label:"上课状态","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"操作",width:"180",align:"center"},scopedSlots:e._u([{key:"default",fn:function(l){return["STUDENT"!==e.user.role?t("el-button",{attrs:{plain:"",type:"primary",size:"mini"},on:{click:function(t){return e.handleEdit(l.row)}}},[e._v("编辑")]):e._e(),"STUDENT"===e.user.role?t("el-button",{attrs:{plain:"",type:"primary",size:"mini",disabled:"未开课"!==l.row.status},on:{click:function(t){return e.choiceCourse(l.row)}}},[e._v("选课")]):e._e(),"ADMIN"===e.user.role?t("el-button",{attrs:{plain:"",type:"danger",size:"mini"},on:{click:function(t){return e.del(l.row.id)}}},[e._v("删除")]):e._e()]}}])})],1),t("div",{staticClass:"pagination"},[t("el-pagination",{attrs:{background:"","current-page":e.pageNum,"page-sizes":[5,10,20],"page-size":e.pageSize,layout:"total, prev, pager, next",total:e.total},on:{"current-change":e.handleCurrentChange}})],1)],1),t("el-dialog",{attrs:{title:"信息",visible:e.fromVisible,width:"40%","close-on-click-modal":!1,"destroy-on-close":""},on:{"update:visible":function(t){e.fromVisible=t}}},[t("el-form",{ref:"formRef",staticStyle:{"padding-right":"50px"},attrs:{"label-width":"100px",model:e.form,rules:e.rules}},[t("el-form-item",{attrs:{prop:"name",label:"课程名称"}},[t("el-input",{attrs:{autocomplete:"off",disabled:"ADMIN"!==e.user.role},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),t("el-form-item",{attrs:{prop:"type",label:"课程类型"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择类型",disabled:"ADMIN"!==e.user.role},model:{value:e.form.type,callback:function(t){e.$set(e.form,"type",t)},expression:"form.type"}},[t("el-option",{attrs:{label:"必修",value:"必修"}}),t("el-option",{attrs:{label:"选修",value:"选修"}})],1)],1),t("el-form-item",{attrs:{prop:"teacherId",label:"授课教师"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择教师",disabled:"ADMIN"!==e.user.role},model:{value:e.form.teacherId,callback:function(t){e.$set(e.form,"teacherId",t)},expression:"form.teacherId"}},e._l(e.teacherData,(function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.value}})})),1)],1),t("el-form-item",{attrs:{prop:"score",label:"学分"}},[t("el-input",{attrs:{autocomplete:"off",disabled:"ADMIN"!==e.user.role},model:{value:e.form.score,callback:function(t){e.$set(e.form,"score",t)},expression:"form.score"}})],1),t("el-form-item",{attrs:{prop:"num",label:"上课人数"}},[t("el-input",{attrs:{autocomplete:"off",disabled:"ADMIN"!==e.user.role},model:{value:e.form.num,callback:function(t){e.$set(e.form,"num",t)},expression:"form.num"}})],1),t("el-form-item",{attrs:{prop:"room",label:"上课教室"}},[t("el-input",{attrs:{autocomplete:"off",disabled:"ADMIN"!==e.user.role},model:{value:e.form.room,callback:function(t){e.$set(e.form,"room",t)},expression:"form.room"}})],1),t("el-form-item",{attrs:{prop:"week",label:"周几"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择类型",disabled:"ADMIN"!==e.user.role},model:{value:e.form.week,callback:function(t){e.$set(e.form,"week",t)},expression:"form.week"}},[t("el-option",{attrs:{label:"星期一",value:"星期一"}}),t("el-option",{attrs:{label:"星期二",value:"星期二"}}),t("el-option",{attrs:{label:"星期三",value:"星期三"}}),t("el-option",{attrs:{label:"星期四",value:"星期四"}}),t("el-option",{attrs:{label:"星期五",value:"星期五"}}),t("el-option",{attrs:{label:"星期六",value:"星期六"}}),t("el-option",{attrs:{label:"星期日",value:"星期日"}})],1)],1),t("el-form-item",{attrs:{prop:"segment",label:"第几大节"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择类型",disabled:"ADMIN"!==e.user.role},model:{value:e.form.segment,callback:function(t){e.$set(e.form,"segment",t)},expression:"form.segment"}},[t("el-option",{attrs:{label:"第一大节（08:00 ~ 09:35）",value:"第一大节（08:00 ~ 09:30）"}}),t("el-option",{attrs:{label:"第二大节（09:55 ~ 11:30）",value:"第二大节（09:55 ~ 11:30）"}}),t("el-option",{attrs:{label:"第三大节（13:15 ~ 14:50）",value:"第三大节（13:15 ~ 14:50）"}}),t("el-option",{attrs:{label:"第四大节（15:05 ~ 16:40）",value:"第四大节（15:05 ~ 16:40）"}}),t("el-option",{attrs:{label:"第五大节（18:00 ~ 20:20）",value:"第五大节（18:00 ~ 20:20）"}})],1)],1),t("el-form-item",{attrs:{prop:"status",label:"上课状态"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择类型"},model:{value:e.form.status,callback:function(t){e.$set(e.form,"status",t)},expression:"form.status"}},[t("el-option",{attrs:{label:"未开课",value:"未开课"}}),t("el-option",{attrs:{label:"已开课",value:"已开课"}}),t("el-option",{attrs:{label:"已结课",value:"已结课"}})],1)],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.fromVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.save}},[e._v("确 定")])],1)],1)],1)},o=[],s={name:"Course",data(){return{tableData:[],pageNum:1,pageSize:10,total:0,name:null,fromVisible:!1,form:{},user:JSON.parse(localStorage.getItem("xm-user")||"{}"),rules:{name:[{required:!0,message:"请输入课程名称",trigger:"blur"}]},ids:[],teacherData:[]}},created(){this.load(1),this.loadTeacher()},methods:{choiceCourse(e){let t={studentId:this.user.id,teacherId:e.teacherId,courseId:e.id};this.$request.post("/choice/add",t).then((e=>{"200"===e.code?this.$message.success("选课成功"):this.$message.error(e.msg)}))},loadTeacher(){this.$request.get("/teacher/selectAll").then((e=>{"200"===e.code?this.teacherData=e.data:this.$message.error(e.msg)}))},handleAdd(){this.form={},this.fromVisible=!0},handleEdit(e){this.form=JSON.parse(JSON.stringify(e)),this.fromVisible=!0},save(){this.$refs.formRef.validate((e=>{e&&this.$request({url:this.form.id?"/course/update":"/course/add",method:this.form.id?"PUT":"POST",data:this.form}).then((e=>{"200"===e.code?(this.$message.success("保存成功"),this.load(1),this.fromVisible=!1):this.$message.error(e.msg)}))}))},del(e){this.$confirm("您确定删除吗？","确认删除",{type:"warning"}).then((t=>{this.$request.delete("/course/delete/"+e).then((e=>{"200"===e.code?(this.$message.success("操作成功"),this.load(1)):this.$message.error(e.msg)}))})).catch((()=>{}))},handleSelectionChange(e){this.ids=e.map((e=>e.id))},delBatch(){this.ids.length?this.$confirm("您确定批量删除这些数据吗？","确认删除",{type:"warning"}).then((e=>{this.$request.delete("/course/delete/batch",{data:this.ids}).then((e=>{"200"===e.code?(this.$message.success("操作成功"),this.load(1)):this.$message.error(e.msg)}))})).catch((()=>{})):this.$message.warning("请选择数据")},load(e){e&&(this.pageNum=e),this.$request.get("/course/selectPage",{params:{pageNum:this.pageNum,pageSize:this.pageSize,name:this.name}}).then((e=>{this.tableData=e.data?.list,this.total=e.data?.total}))},reset(){this.name=null,this.load(1)},handleCurrentChange(e){this.load(e)}}},r=s,i=l(1001),n=(0,i.Z)(r,a,o,!1,null,"2488d1fc",null),c=n.exports}}]);
//# sourceMappingURL=150.362798e3.js.map