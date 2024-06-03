<template>
  <div>
    <div class="search">
<!--      <el-select v-model="courseId" placeholder="请选择课程" style="width: 200px">-->
      <el-select v-model="courseId" placeholder="软件协同设计" style="width: 200px">
        <!--        <el-option v-for="item in courseSearchData" :label="item.name" :value="item.id"></el-option>-->
        <el-option v-for="item in courseSearchData" :label="item.name" :value="item.value" :key="item.id"></el-option>
      </el-select>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
      <el-button type="info" plain style="margin-left: 10px" @click="add">添加学生</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="printAll">打印学生名单</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" stripe  @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" v-if="user.role === 'TEACHER'"></el-table-column>
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="courseName" label="课程名称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="teacherName" label="授课教师" show-overflow-tooltip></el-table-column>
        <el-table-column prop="studentName" label="学生姓名" show-overflow-tooltip></el-table-column>
        <el-table-column prop="teamName" label="队伍名称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="role" label="角色" show-overflow-tooltip></el-table-column>
<!--        <el-table-column prop="time" label="上课时间" show-overflow-tooltip></el-table-column>-->
<!--        <el-table-column prop="status" label="考勤状态" show-overflow-tooltip></el-table-column>-->

        <el-table-column label="操作" width="180" align="center">
          <template v-slot="scope">
            <el-button plain type="primary" size="mini" @click=handleEdit(scope.row)>编辑</el-button>
            <el-button plain type="danger" size="mini" @click=del(scope.row.id)>删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>
    </div>


    <el-dialog title="信息" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
<!--        <el-form-item prop="courseId" label="选择课程">-->
<!--          <el-select v-model="form.courseId" placeholder="请选择课程" style="width: 100%" @change="getStudent">-->
<!--            &lt;!&ndash;            <el-option v-for="item in courseData" :label="item.name" :value="item.id"></el-option>&ndash;&gt;-->
<!--            <el-option v-for="item in courseData" :label="item.name" :value="item.value" :key="item.id"></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item prop="studentId" label="选择学生">-->
<!--          <el-select v-model="studentId" placeholder="请选择学生" style="width: 100%">-->
<!--            &lt;!&ndash;            <el-option v-for="item in studentData" :label="item.studentName" :value="item.studentId"></el-option>&ndash;&gt;-->
<!--            <el-option v-for="item in studentData" :label="item.studentName" :value="item.value" :key="item.studentId"></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <el-form-item label="课程名称">
          <el-input v-model="form.courseName"></el-input>
        </el-form-item>
        <el-form-item label="学生姓名">
          <el-input v-model="form.studentName"></el-input>
        </el-form-item>
        <el-form-item prop="teamName" label="队伍名称">
          <el-input v-model="form.teamName" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item prop="role" label="角色">
          <el-input v-model="form.role" autocomplete="off" ></el-input>
        </el-form-item>
<!--        <el-form-item prop="time" label="上课时间">-->
<!--          <el-date-picker style="width: 100%"-->
<!--                          v-model="form.time"-->
<!--                          type="date"-->
<!--                          value-format="yyyy-MM-dd"-->
<!--                          placeholder="选择日期">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item prop="status" label="考勤状态">-->
<!--          <el-select v-model="form.status" placeholder="请选择状态" style="width: 100%">-->
<!--            <el-option label="正常" value="正常"></el-option>-->
<!--            <el-option label="迟到" value="迟到"></el-option>-->
<!--            <el-option label="早退" value="早退"></el-option>-->
<!--            <el-option label="缺勤" value="缺勤"></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
export default {
  name: "MyCourse",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      courseId: null,
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        teamName: [
          {required: true, message: '请输入队伍名称', trigger: 'blur'},
        ],
        role: [
          {required: true, message: '请输入队员角色', trigger: 'blur'},
        ],
      },
      ids: [],
      courseData: [],
      courseSearchData: [],
      studentData: [],
      studentId: null
    }
  },
  created() {
    this.load(1)
    this.loadCourseByTeacher()
    this.loadCourseSearch()
  },
  methods: {
    loadCourseSearch() {
      // if ('STUDENT' === this.user.role) {
      //   this.$request.get('/choice/selectAll?studentId=' + this.user.id).then(res => {
      //     if (res.code === '200') {
      //       res.data.forEach(item => {
      //         item.id = item.courseId
      //       })
      //       this.courseSearchData = res.data
      //     }
      //   })
      // } else {
      //   let url = 'ADMIN' === this.user.role ? '/course/selectAll' : '/course/selectAll?teacherId=' + this.user.id
      //   this.$request.get(url).then(res => {
      //     if (res.code === '200') {
      //       this.courseSearchData = res.data
      //     } else {
      //       this.$message.error(res.msg)
      //     }
      //   })
      // }
    },
    loadCourseByTeacher() {
      // this.$request.get('/course/selectAll', {
      //   params: {
      //     teacherId: this.user.id
      //   }
      // }).then(res => {
      //   if (res.code === '200') {
      //     this.courseData = res.data
      //   } else {
      //     this.$message.error(res.msg)
      //   }
      // })
    },
    getStudent(courseId) {
      // // 查询所有选择该门课的学生信息
      // this.$request.get('/choice/selectAll', {
      //   params: {
      //     courseId: courseId
      //   }
      // }).then(res => {
      //   if (res.code === '200') {
      //     this.studentData = res.data
      //     this.studentId = null
      //   } else {
      //     this.$message.error(res.msg)
      //   }
      // })
    },
    getStudentEdit(courseId) {
      // this.$request.get('/choice/selectAll', {
      //   params: {
      //     courseId: courseId
      //   }
      // }).then(res => {
      //   if (res.code === '200') {
      //     this.studentData = res.data
      //     this.studentId = this.form.studentId
      //     this.fromVisible = true
      //   } else {
      //     this.$message.error(res.msg)
      //   }
      // })
    },
    handleAdd() {   // 新增数据
      this.form = {
        teacherId: this.user.id
      }  // 新增数据的时候清空数据
      this.fromVisible = true   // 打开弹窗
      this.studentId = null
    },
    handleEdit(row) {   // 编辑数据
      // this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      // this.getStudentEdit(this.form.courseId)
      this.form = {
        courseName: row.courseName,
        studentName: row.studentName,
        teamName: row.teamName,
        role: row.role,
        // 假设还有其他需要编辑的字段
      };
      this.fromVisible = true; // 显示编辑对话框
    },
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          if (this.form.id) {
            // 模拟更新操作
            let index = this.tableData.findIndex(item => item.id === this.form.id);
            if (index !== -1) {
              this.tableData[index] = {...this.tableData[index], ...this.form};
            }
          } else {
            // 模拟添加操作
            this.form.id = this.tableData.length + 1;  // 假设ID简单递增
            this.tableData.push(this.form);
          }
          this.$message.success('操作成功');
          this.fromVisible = false;
          this.form = {};  // 清空表单
        }
      });
    },
    del(id) {
      this.$confirm('您确定要删除这条记录吗？', '确认删除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const index = this.tableData.findIndex(item => item.id === id);
        if (index !== -1) {
          this.tableData.splice(index, 1);  // 从数组中移除
          this.$message.success('删除成功');
        } else {
          this.$message.error('删除失败: 记录未找到');
        }
      }).catch(() => {
        this.$message.info('删除操作已取消');
      });
    },

    // save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
    //   this.$refs.formRef.validate((valid) => {
    //     if (valid) {
    //       this.form.studentId = this.studentId
    //       this.$request({
    //         url: this.form.id ? '/myCourse/update' : '/myCourse/add',
    //         method: this.form.id ? 'PUT' : 'POST',
    //         data: this.form
    //       }).then(res => {
    //         if (res.code === '200') {  // 表示成功保存
    //           this.$message.success('保存成功')
    //           this.load(1)
    //           this.fromVisible = false
    //         } else {
    //           this.$message.error(res.msg)  // 弹出错误的信息
    //         }
    //       })
    //     }
    //   })
    // },
    // del(id) {   // 单个删除
    //   this.$confirm('您确定删除吗？', '灵魂拷问', {type: "warning"}).then(response => {
    //     this.$request.delete('/myCourse/delete/' + id).then(res => {
    //       if (res.code === '200') {   // 表示操作成功
    //         this.$message.success('操作成功')
    //         this.load(1)
    //       } else {
    //         this.$message.error(res.msg)  // 弹出错误的信息
    //       }
    //     })
    //   }).catch(() => {
    //   })
    // },
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)   //  [1,2]
    },
    delBatch() {   // 批量删除
      // if (!this.ids.length) {
      //   this.$message.warning('请选择数据')
      //   return
      // }
      // this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
      //   this.$request.delete('/myCourse/delete/batch', {data: this.ids}).then(res => {
      //     if (res.code === '200') {   // 表示操作成功
      //       this.$message.success('操作成功')
      //       this.load(1)
      //     } else {
      //       this.$message.error(res.msg)  // 弹出错误的信息
      //     }
      //   })
      // }).catch(() => {
      // })
    },
    // load(pageNum) {  // 分页查询
      // if (pageNum) this.pageNum = pageNum
      // this.$request.get('/myCourse/selectPage', {
      //   params: {
      //     pageNum: this.pageNum,
      //     pageSize: this.pageSize,
      //     courseId: this.courseId,
      //   }
      // }).then(res => {
      //   this.tableData = res.data?.list
      //   this.total = res.data?.total
      // })
    // },
    load(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.total = 15;  // 总共有15条数据，代表3个团队，每个团队5个角色
      this.tableData = [
        // 第一组数据：团队 Alpha
        { id: 1, courseName: "软件协同设计", teacherName: "李教授", studentName: "李四", teamName: "Alpha", role: "产品经理" },
        { id: 2, courseName: "软件协同设计", teacherName: "李教授", studentName: "王五", teamName: "Alpha", role: "计划经理" },
        { id: 3, courseName: "软件协同设计", teacherName: "李教授", studentName: "赵六", teamName: "Alpha", role: "开发经理" },
        { id: 4, courseName: "软件协同设计", teacherName: "李教授", studentName: "钱七", teamName: "Alpha", role: "测试经理" },
        { id: 5, courseName: "软件协同设计", teacherName: "李教授", studentName: "孙八", teamName: "Alpha", role: "质量经理" },
        // 第二组数据：团队 Beta
        { id: 6, courseName: "软件协同设计", teacherName: "李教授", studentName: "周九", teamName: "Beta", role: "产品经理" },
        { id: 7, courseName: "软件协同设计", teacherName: "李教授", studentName: "吴十", teamName: "Beta", role: "计划经理" },
        { id: 8, courseName: "软件协同设计", teacherName: "李教授", studentName: "郑十一", teamName: "Beta", role: "开发经理" },
        { id: 9, courseName: "软件协同设计", teacherName: "李教授", studentName: "王十二", teamName: "Beta", role: "测试经理" },
        { id: 10, courseName: "软件协同设计", teacherName: "李教授", studentName: "冯十三", teamName: "Beta", role: "质量经理" },
      ];
    },
    reset() {
      this.courseId = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

<style scoped>

</style>
