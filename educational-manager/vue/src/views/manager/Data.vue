<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入课程资料" style="width: 200px" v-model="content"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation" v-if="user.role !== 'STUDENT'">
      <el-button type="primary" plain @click="handleAdd">资料上传</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="content" label="资料说明" show-overflow-tooltip></el-table-column>
<!--        <el-table-column prop="studentName" label="学生姓名" show-overflow-tooltip></el-table-column>-->
        <el-table-column prop="courseName" label="课程名称"></el-table-column>
        <el-table-column prop="teacherName" label="授课教师"></el-table-column>
        <el-table-column prop="file" label="资料文件">
          <template v-slot="scope">
            <el-button v-if="scope.row.file" type="warning" size="mini" @click="down(scope.row.file)">下载查看</el-button>
            <span v-else>无文件</span> <!-- 没有文件时显示的文本 -->
          </template>
        </el-table-column>
        <el-table-column prop="uploadDate" label="上传时间" :formatter="dateFormat"></el-table-column>
        <el-table-column label="操作" width="180" align="center" v-if="user.role !== 'STUDENT'">
          <template v-slot="scope">
            <el-button plain type="primary" @click="handleEdit(scope.row)" size="mini">编辑</el-button>
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


    <el-dialog title="资料信息" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="content" label="资料说明">
          <el-input type="textarea" :rows="4" v-model="form.content" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="courseId" label="选择课程">
          <el-select v-model="form.courseId" placeholder="请选择课程" style="width: 100%">
            <el-option v-for="item in courseData" :label="item.name" :value="item.courseId"></el-option>
<!--            <el-option v-for="item in courseData" :label="item.name" :value="item.value" :key="item.courseId"></el-option>-->
          </el-select>
        </el-form-item>
        <el-form-item label="资料文件">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :headers="{ token: user.token }"
              list-type="text"
              :on-success="handleFileSuccess"
          >
            <el-button type="primary">上传资料</el-button>
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
  name: "Data",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      content: null,
      fromVisible: false,
      checkVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        courseId: [
          {required: true, message: '请选择课程', trigger: 'blur'},
        ],
        content: [
          {required: true, message: '请输入资料说明', trigger: 'blur'},
        ],
      },
      ids: [],
      courseData: []
    }
  },
  created() {
    this.load(1)
    this.loadCourse()
  },
  methods: {
    dateFormat(row, column) {
      const date = row[column.property];
      return date ? this.formatDate(date) : '';
    },
    formatDate(dateStr) {
      const date = new Date(dateStr);
      return `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
    },
    loadCourse() {
      this.$request.get('/choice/selectAll?studentId=' + this.user.id).then(res => {
        if (res.code === '200') {
          this.courseData = res.data;
        } else {
          this.$message.error(res.msg);
        }
      })
    },
    handleAdd() {   // 新增数据
      this.form = {
        studentId: this.user.id,
      }  // 新增数据的时候清空数据
      this.fromVisible = true   // 打开弹窗
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.fromVisible = true   // 打开弹窗
    },
    handleCheck(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.checkVisible = true
    },
    check() {
      this.$request.put('/data/update', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          this.load(1)
          this.checkVisible = false
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/data/update' : '/data/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {  // 表示成功保存
              this.$message.success('保存成功')
              this.load(1)
              this.fromVisible = false
            } else {
              this.$message.error(res.msg)  // 弹出错误的信息
            }
          })
        }
      })
    },
    del(id) {   // 单个删除
      this.$confirm('您确定删除资料吗？', '灵魂拷问', {type: "warning"}).then(response => {
        this.$request.delete('/data/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/data/selectPage', {
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
    reset() {
      this.content = null;
      this.load(1);
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
