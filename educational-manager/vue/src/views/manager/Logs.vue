<template>
  <div>
    <!-- 搜索和重置功能 -->
    <div class="search">
      <el-select v-model="logLevel" placeholder="请选择日志级别" style="width: 200px">
        <el-option v-for="level in logLevels" :label="level.label" :value="level.value" :key="level.value"></el-option>
      </el-select>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <!-- 日志表格显示 -->
    <div class="table">
      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="logContent" label="日志内容" show-overflow-tooltip></el-table-column>
        <el-table-column prop="logLevel" label="日志级别" show-overflow-tooltip></el-table-column>
        <el-table-column prop="createdAt" label="创建时间" show-overflow-tooltip></el-table-column>
        <el-table-column prop="creatorName" label="创建者" show-overflow-tooltip></el-table-column>
      </el-table>

      <!-- 分页 -->
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
  </div>
</template>

<script>
export default {
  name: "LogManagement",
  data() {
    return {
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      logLevel: null,
      logLevels: [
        { value: 'INFO', label: '信息' },
        { value: 'WARNING', label: '警告' },
        { value: 'ERROR', label: '错误' },
      ],
      // 模拟数据
      mockData: [
        { id: 1, logContent: '系统启动', logLevel: 'INFO', createdAt: '2024-05-29 08:00', creatorName: '管理员' },
        { id: 2, logContent: '用户登录失败', logLevel: 'WARNING', createdAt: '2024-05-29 08:30', creatorName: '用户A' },
        // 更多模拟数据
      ]
    }
  },
  created() {
    this.tableData = this.mockData; // 初始化时加载模拟数据
  },
  methods: {
    load(pageNum) {
      // 此处可以添加实际的分页逻辑
    },
    reset() {
      this.logLevel = null;
      this.tableData = this.mockData; // 重置数据
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum);
    },
  }
}
</script>
