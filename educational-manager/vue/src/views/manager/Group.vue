<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入账号查询" style="width: 200px" v-model="username"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">新增</el-button>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" strip @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="序号" width="70" align="center" sortable></el-table-column>
        <el-table-column label="头像">
          <template v-slot="scope">
            <div style="display: flex; align-items: center">
              <el-image style="width: 40px; height: 40px; border-radius: 50%" v-if="scope.row.avatar"
                        :src="scope.row.avatar" :preview-src-list="[scope.row.avatar]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="username" label="账号"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="role" label="角色"></el-table-column>
        <el-table-column prop="collegeName" label="学院"></el-table-column>
        <el-table-column prop="specialityName" label="专业"></el-table-column>
        <el-table-column prop="className" label="班级"></el-table-column>
        <el-table-column prop="score" label="学分"></el-table-column>
        <el-table-column label="操作" align="center" width="180">
          <template v-slot="scope">
            <el-button size="mini" type="primary" plain @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" plain @click="del(scope.row.id)">删除</el-button>
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


    <el-dialog title="教师信息" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="form" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <el-form-item label="头像">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :headers="{ token: user.token }"
              list-type="picture"
              :on-success="handleAvatarSuccess"
          >
            <el-button type="primary">上传头像</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item label="学院" prop="collegeId">
          <el-select v-model="form.collegeId" placeholder="请选择学院" style="width: 100%">
<!--            <el-option v-for="item in collegeData" :label="item.name" :value="item.id"></el-option>-->
            <el-option v-for="item in collegeData" :label="item.name" :value="item.value" :key="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色" prop="roleId">
          <el-select v-model="form.roleId" placeholder="请选择角色" style="width: 100%">
            <el-option v-for="role in roleData" :label="role.name" :value="role.value" :key="role.id"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="专业" prop="specialityId">
          <el-select v-model="form.specialityId" placeholder="请选择专业" style="width: 100%">
<!--            <el-option v-for="item in specialityData" :label="item.name" :value="item.id"></el-option>-->
            <el-option v-for="item in specialityData" :label="item.name" :value="item.value" :key="item.id"></el-option>
          </el-select>        </el-form-item>
        <el-form-item label="班级" prop="classId">
          <el-select v-model="form.classId" placeholder="请选择班级" style="width: 100%">
<!--            <el-option v-for="item in classData" :label="item.name" :value="item.id"></el-option>-->
            <el-option v-for="item in classData" :label="item.name" :value="item.value" :key="item.id"></el-option>
          </el-select>        </el-form-item>
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
  name: "Student",
  data() {
    return {
      // tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      username: null,
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ]
      },
      ids: [],
      roleData: [
        { id: '1', name: '学生', value: 'student' },
        { id: '2', name: '教师', value: 'teacher' },
        { id: '3', name: '管理员', value: 'admin' }
      ],

      // collegeData:[],
      // specialityData:[],
      // classData:[]
      collegeData: [
        { id: '1', name: '计算机学院', value: '1' },
        { id: '2', name: '工程学院', value: '2' },
        { id: '3', name: '人文科学学院', value: '3' }
      ],

      specialityData: [
        { id: '1', name: '软件工程', value: '1' },
        { id: '2', name: '机械工程', value: '2' },
        { id: '3', name: '英语', value: '3' }
      ],

      classData: [
        { id: '1', name: '软件一班', value: '1' },
        { id: '2', name: '机械一班', value: '2' },
        { id: '3', name: '英语一班', value: '3' }
      ],
      tableData: [
        { id: '1', avatar: 'http://localhost:9091/files/1714805298340-pexels-vurzie-kim-21194857.jpg', username: 'ccc', name: '陈陈陈', role: '开发经理', collegeName: '计算机学院', specialityName: '软件工程', className: '软件一班', score: '3' },
        { id: '2', avatar: 'http://localhost:9091/files/1714838192012-s34647971.jpg', username: 'kkkkk', name: 'kk', role: '测试经理', collegeName: '计算机学院', specialityName: '软件工程', className: '软件一班', score: '3' },
        { id: '3', avatar: 'http://localhost:9091/files/1715568003363-32b54f04737c4c24a009b6b44379002d.jpeg.webp', username: 'lili', name: '李', role: '质量经理', collegeName: '计算机学院', specialityName: '软件工程', className: '软件一班', score: '3' },
        { id: '4', avatar: 'http://localhost:9091/files/1715568003363-32b54f04737c4c24a009b6b44379002d.jpeg.webp', username: 'nnnn', name: 'nn', role: '计划经理', collegeName: '计算机学院', specialityName: '软件工程', className: '软件一班', score: '3' },
        { id: '5', avatar: 'http://localhost:9091/files/1715568003363-32b54f04737c4c24a009b6b44379002d.jpeg.webp', username: 'opo', name: 'opo', role: '产品经理', collegeName: '计算机学院', specialityName: '软件工程', className: '软件一班', score: '3' }
      ],

    }
  },
  created() {
    this.load(1)
    this.loadCollege()
    this.loadSpeciality()
    this.loadClass()
  },
  methods: {
    // loadCollege() {
    //   this.$request.get('/college/selectAll').then(res => {
    //     if (res.code === '200') {
    //       this.collegeData = res.data
    //     } else {
    //       this.$message.error(res.data)
    //     }
    //   })
    // },
    // loadSpeciality() {
    //   this.$request.get('/speciality/selectAll').then(res => {
    //     if (res.code === '200') {
    //       this.specialityData = res.data
    //     } else {
    //       this.$message.error(res.data)
    //     }
    //   })
    // },
    // loadClass() {
    //   this.$request.get('/classes/selectAll').then(res => {
    //     if (res.code === '200') {
    //       this.classData = res.data
    //     } else {
    //       this.$message.error(res.data)
    //     }
    //   })
    // },
    handleAdd() {   // 新增数据
      this.form = {}  // 新增数据的时候清空数据
      this.fromVisible = true   // 打开弹窗
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.fromVisible = true   // 打开弹窗
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/student/update' : '/student/add',
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
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/student/delete/' + id).then(res => {
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
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)
    },
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/student/delete/batch', {data: this.ids}).then(res => {
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
    // load(pageNum) {  // 分页查询
    //   if (pageNum) this.pageNum = pageNum
    //   this.$request.get('/student/selectPage', {
    //     params: {
    //       pageNum: this.pageNum,
    //       pageSize: this.pageSize,
    //       username: this.username,
    //     }
    //   }).then(res => {
    //     this.tableData = res.data?.list
    //     this.total = res.data?.total
    //   })
    // },
    loadCollege() {
      // 直接使用静态数据填充
      this.collegeData = this.collegeData;
    },

    loadSpeciality() {
      // 直接使用静态数据填充
      this.specialityData = this.specialityData;
    },

    loadClass() {
      // 直接使用静态数据填充
      this.classData = this.classData;
    },

    load(pageNum) {
      if (pageNum) this.pageNum = pageNum;
      let filteredData = this.tableData;
      if (this.username) {
        filteredData = this.tableData.filter(data => data.username.includes(this.username));
      }
      this.total = filteredData.length;
      this.tableData = filteredData.slice((this.pageNum - 1) * this.pageSize, this.pageNum * this.pageSize);
    },

    reset() {
      this.username = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    handleAvatarSuccess(response, file, fileList) {
      // 把头像属性换成上传的图片的链接
      this.form.avatar = response.data
    },
  }
}
</script>

<style scoped>

</style>