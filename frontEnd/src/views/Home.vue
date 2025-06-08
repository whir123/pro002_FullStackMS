<template>
  <div class="home-container">
    <div class="welcome-card">
      <h2>欢迎使用后台管理系统</h2>
      <p>当前管理员总数：<strong>{{ adminList.length }}</strong> 人</p>
      <p>当前员工总数：<strong>{{ employeeList.length }}</strong> 人</p>
    </div>

    <el-card class="table-card">
      <template #header>
        <div class="table-title">近期员工信息预览</div>
      </template>
      <el-table :data="employeeList.slice(0, 5)" border stripe style="width: 100%">
        <el-table-column prop="id" label="ID" width="60"/>
        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="sex" label="性别"/>
        <el-table-column prop="age" label="年龄"/>
        <el-table-column prop="no" label="工号"/>
        <el-table-column prop="description" label="介绍"/>
      </el-table>
    </el-card>
  </div>
</template>

<script setup lang="ts" name="Home">
import { ref, onMounted } from 'vue'
import request from "@/utils/request.js"

const employeeList = ref([]);
const adminList = ref([]);

onMounted(() => {
  request.get('/employee/selectAll').then(res => {
    if (res.code === '200') {
      employeeList.value = res.data
    }
  });
  request.get('/admin/selectAll').then(res => {
    if (res.code === '200') {
      adminList.value = res.data
    }
  });
})
</script>

<style scoped>
.home-container {
    padding: 20px;
    font-family: "Microsoft YaHei", sans-serif;
}

.welcome-card {
    background: linear-gradient(135deg, #9ba8ab, #ccd0cf);
    color: white;
    border-radius: 12px;
    padding: 24px;
    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
    margin-bottom: 24px;
    text-align: center;
}

.welcome-card h2 {
    margin-bottom: 12px;
    font-size: 28px;
}
.welcome-card p {
    margin-top: 2px;
    margin-bottom: 12px;
    font-size: 15px;
}

.table-card {
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.08);
}

.table-title {
    font-size: 18px;
    font-weight: bold;
    color: #333;
}
</style>
