<template>
  <div class="data-container">
    <h2>数据统计与可视化</h2>
    <div class="chart-row">
      <el-card class="chart-card">
        <template #header>员工性别分布</template>
        <div ref="sexChartRef" class="chart-box"></div>
      </el-card>
      <el-card class="chart-card">
        <template #header>员工年龄分布</template>
        <div ref="ageChartRef" class="chart-box"></div>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts" name="Data">
import { onMounted, ref } from 'vue'
import * as echarts from 'echarts'
import request from '@/utils/request.js'

const sexChartRef = ref()
const ageChartRef = ref()

onMounted(() => {
  request.get('/employee/selectAll').then(res => {
    if (res.code === '200') {
      const list = res.data

      // 性别统计
      const sexMap = { 男: 0, 女: 0 }
      // 年龄统计分段
      const ageGroups = { '20以下': 0, '20-29': 0, '30-39': 0, '40以上': 0 }

      list.forEach(emp => {
        sexMap[emp.sex] = (sexMap[emp.sex] || 0) + 1

        const age = emp.age
        if (age < 20) ageGroups['20以下']++
        else if (age < 30) ageGroups['20-29']++
        else if (age < 40) ageGroups['30-39']++
        else ageGroups['40以上']++
      })

      // 初始化性别图
      const sexChart = echarts.init(sexChartRef.value)
      sexChart.setOption({
        tooltip: { trigger: 'item' },
        series: [
          {
            type: 'pie',
            radius: '70%',
            data: Object.entries(sexMap).map(([name, value]) => ({ name, value })),
            label: { formatter: '{b}: {d}%' }
          }
        ]
      })

      // 初始化年龄图
      const ageChart = echarts.init(ageChartRef.value)
      ageChart.setOption({
        tooltip: { trigger: 'axis' },
        xAxis: { type: 'category', data: Object.keys(ageGroups) },
        yAxis: { type: 'value' },
        series: [
          {
            data: Object.values(ageGroups),
            type: 'bar',
            color: '#5470C6'
          }
        ]
      })
    }
  })
})
</script>

<style scoped>
.data-container {
  padding: 20px;
  font-family: "Microsoft YaHei", sans-serif;
}

h2 {
  margin-bottom: 35px;
  font-size: 28px;
  text-align: center;
  color: #444;
}

.chart-row {
  display: flex;
  gap: 20px;
  justify-content: space-between;
  flex-wrap: wrap;
}

.chart-card {
  flex: 1;
  width: 400px;
}

.chart-box {
  height: 500px;
  width: 100%;
}
</style>
