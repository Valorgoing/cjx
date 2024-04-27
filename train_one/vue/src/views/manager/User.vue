<template>
  <div>
    <el-table :data="tableData" stripe>
      <el-table-column label="序号" prop="id" align="center"></el-table-column>
      <el-table-column label="用户名" prop="username" align="center"></el-table-column>
      <el-table-column label="姓名" prop="name" align="center"></el-table-column>
      <el-table-column label="手机号" prop="phone" align="center"></el-table-column>
      <el-table-column label="邮箱" prop="email" align="center"></el-table-column>
      <el-table-column label="地址" prop="address" align="center"></el-table-column>
      <el-table-column label="头像" align="center">
        <template v-slot="scope">
          <div style="display: flex;align-items: center">
            <el-image style="width: 50px;height: 50px;border-radius: 50%" v-if="scope.row.avatar"
                      :src="scope.row.avatar" :preview-src-list="[scope.row.avatar]"></el-image>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="角色" prop="role" align="center"></el-table-column>
      <el-table-column label="操作" align="center">
        <template v-slot="scope">
          <el-button size="mini" type="success" plain>编辑</el-button>
          <el-button size="mini" type="danger" plain>删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.$request.get('/user/selectAll').then(res => {
        this.tableData = res.data
      })
    }
  }
}
</script>

<style>

</style>