<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入任务说明" style="width: 200px" v-model="content"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">报告提交</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="content" label="任务说明" show-overflow-tooltip></el-table-column>
        <el-table-column prop="studentName" label="提交者姓名" show-overflow-tooltip></el-table-column>
        <el-table-column prop="courseName" label="课程名称"></el-table-column>
        <el-table-column prop="file" label="报告文件">
          <template v-slot="scope">
            <el-button type="warning" size="mini" @click="down(scope.row.file)">下载查看</el-button>
          </template>
        </el-table-column>
<!--        <el-table-column prop="descr" label="审核说明"></el-table-column>-->

        <el-table-column label="操作" width="180" align="center" >
          <template v-slot="scope">
            <el-button plain type="primary" @click="handleEdit(scope.row)" size="mini" >编辑</el-button>
            <el-button plain type="danger" size="mini" @click=del(scope.row.id) >删除</el-button>
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

    <el-dialog title="任务信息" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="content" label="任务说明">
          <el-input type="textarea" :rows="4" v-model="form.content" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="courseId" label="选择课程">
          <el-select v-model="form.courseId" placeholder="请选择课程" style="width: 100%">
            <el-option v-for="item in courseData" :label="item.name" :value="item.courseId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="报告文件">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :headers="{ token: user.token }"
              list-type="text"
              :on-success="handleFileSuccess"
          >
            <el-button type="primary">上传文件</el-button>
          </el-upload>
        </el-form-item>
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
  name: "Teamwork",
  data() {
    return {
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      content: null,
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        courseId: [
          {required: true, message: '请选择课程', trigger: 'blur'},
        ],
        content: [
          {required: true, message: '请输入报告说明', trigger: 'blur'},
        ],
      },
      courseData: []
    }
  },
  created() {
    this.load(1)
    this.loadCourse()
    this.loadDepartmentWork(this.user.departmentId)  // 加载小组作业
  },
  methods: {
    loadCourse() {
      this.$request.get('/choice/selectAll?studentId=' + this.user.id).then(res => {
        if (res.code === '200') {
          this.courseData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAdd() {
      this.form = {
        studentId: this.user.id,
        departmentId: this.user.departmentId || 0,  // 新增departmentId字段
      }
      this.fromVisible = true
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.form.status = '待审核'
      this.form.descr = ''
      this.fromVisible = true
    },
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/teamwork/update' : '/teamwork/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {
              this.$message.success('保存成功')
              this.load(1)
              this.fromVisible = false
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
    del(id) {
      this.$confirm('您确定删除报告吗？', '灵魂拷问', {type: "warning"}).then(response => {
        this.$request.delete('/teamwork/delete/' + id).then(res => {
          if (res.code === '200') {
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/teamwork/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          content: this.content,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    loadDepartmentWork(departmentId) {  // 加载小组报告
      if (departmentId) {
        this.$request.get(`/teamwork/selectByDepartmentId/${departmentId}`).then(res => {
          if (res.code === '200') {
            this.tableData = res.data
          } else {
            this.$message.error(res.msg)
          }
        })
      } else {
        // this.$message.error('请先选择一个小组')
      }
    },
    reset() {
      this.content = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    handleFileSuccess(res) {
      this.form.file = res.data
    },
    down(url) {
      location.href = url
    }
  }
}
</script>

<style scoped>

</style>
