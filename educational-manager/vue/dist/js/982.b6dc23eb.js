"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[982],{1982:function(t,e,l){l.r(e),l.d(e,{default:function(){return n}});var a=function(){var t=this,e=t._self._c;return e("div",[e("div",{staticClass:"table"},[e("el-table",{attrs:{data:t.tableData,stripe:"",border:""}},[e("el-table-column",{attrs:{prop:"segment",label:"时间",width:"200"}}),e("el-table-column",{attrs:{prop:"monday",label:"星期一","show-overflow-tooltip":""}}),e("el-table-column",{attrs:{prop:"tuesday",label:"星期二","show-overflow-tooltip":""}}),e("el-table-column",{attrs:{prop:"wednesday",label:"星期三","show-overflow-tooltip":""}}),e("el-table-column",{attrs:{prop:"thursday",label:"星期四","show-overflow-tooltip":""}}),e("el-table-column",{attrs:{prop:"friday",label:"星期五","show-overflow-tooltip":""}}),e("el-table-column",{attrs:{prop:"saturday",label:"星期六","show-overflow-tooltip":""}}),e("el-table-column",{attrs:{prop:"sunday",label:"星期日","show-overflow-tooltip":""}})],1)],1)])},o=[],r={name:"Curriculum",data(){return{tableData:[],user:JSON.parse(localStorage.getItem("xm-user")||"{}")}},created(){this.load()},methods:{load(){this.$request.get("/choice/getCurriculum").then((t=>{"200"===t.code?this.tableData=t.data:this.$message.error(t.msg)}))}}},s=r,u=l(1001),p=(0,u.Z)(s,a,o,!1,null,"a3b3f508",null),n=p.exports}}]);
//# sourceMappingURL=982.b6dc23eb.js.map