"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[343],{5343:function(t,e,a){a.r(e),a.d(e,{default:function(){return c}});var l=function(){var t=this,e=t._self._c;return e("div",[e("div",{staticClass:"search"},[e("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入教师姓名"},model:{value:t.teacher,callback:function(e){t.teacher=e},expression:"teacher"}}),e("el-input",{staticStyle:{width:"200px","margin-left":"5px"},attrs:{placeholder:"请输入评教内容"},model:{value:t.content,callback:function(e){t.content=e},expression:"content"}}),e("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"info",plain:""},on:{click:function(e){return t.load(1)}}},[t._v("查询")]),e("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"warning",plain:""},on:{click:t.reset}},[t._v("重置")])],1),e("div",{staticClass:"table"},[e("el-table",{attrs:{data:t.tableData,stripe:""}},[e("el-table-column",{attrs:{prop:"id",label:"序号",width:"80",align:"center",sortable:""}}),e("el-table-column",{attrs:{prop:"name",label:"课程名称","show-overflow-tooltip":"",width:"200px"}}),e("el-table-column",{attrs:{prop:"teacher",label:"授课教师",width:"100px"}}),e("el-table-column",{attrs:{prop:"content",label:"评教内容"}}),e("el-table-column",{attrs:{prop:"time",label:"评教时间",width:"250px"}}),"ADMIN"===t.user.role?e("el-table-column",{attrs:{label:"操作",width:"180",align:"center"},scopedSlots:t._u([{key:"default",fn:function(a){return[e("el-button",{attrs:{plain:"",type:"danger",size:"mini"},on:{click:function(e){return t.del(a.row.id)}}},[t._v("删除")])]}}],null,!1,1677150881)}):t._e()],1),e("div",{staticClass:"pagination"},[e("el-pagination",{attrs:{background:"","current-page":t.pageNum,"page-sizes":[5,10,20],"page-size":t.pageSize,layout:"total, prev, pager, next",total:t.total},on:{"current-change":t.handleCurrentChange}})],1)],1)])},n=[],r={name:"Comment",data(){return{tableData:[],pageNum:1,pageSize:10,total:0,teacher:null,content:null,user:JSON.parse(localStorage.getItem("xm-user")||"{}"),rules:{},ids:[]}},created(){this.load(1)},methods:{del(t){this.$confirm("您确定删除吗？","确认删除",{type:"warning"}).then((e=>{this.$request.delete("/comment/delete/"+t).then((t=>{"200"===t.code?(this.$message.success("操作成功"),this.load(1)):this.$message.error(t.msg)}))})).catch((()=>{}))},load(t){t&&(this.pageNum=t),this.$request.get("/comment/selectPage",{params:{pageNum:this.pageNum,pageSize:this.pageSize,teacher:this.teacher,content:this.content}}).then((t=>{this.tableData=t.data?.list,this.total=t.data?.total}))},reset(){this.teacher=null,this.content=null,this.load(1)},handleCurrentChange(t){this.load(t)}}},s=r,i=a(1001),o=(0,i.Z)(s,l,n,!1,null,"32ad6aca",null),c=o.exports}}]);
//# sourceMappingURL=343.18605d00.js.map