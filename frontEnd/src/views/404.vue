<template>
  <div class="container">
    <div class="content">
      <h1>404</h1>
      <p>抱歉，您访问的页面不存在</p>
      <el-button class="btn" @click="goHome">返回登录页</el-button>
    </div>
  </div>
</template>

<script setup lang="ts" name="Err404">
    import { useRouter } from 'vue-router';
    const router = useRouter();
    const goHome = () => {
      router.push('/');
    }
</script>

<style scoped>
    .container {
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        background: #f8f8f8;
    }
    .content {
        text-align: center;
    }
    h1 {
        font-size: 120px;
        margin: 0;
        color: rgba(0, 0, 0, 0.25);
    }
    p {
        font-size: 30px;
        margin: 20px 0;
        color: #666;
    }
    .btn{
        text-align: center;
        background-color: #50666b;
        color:white;
        height:40px;
        width:300px;
        margin-top: 30px;
    }
    .btn:hover{
        opacity: 0.9;
        /* transform: scale(1.02); */
        transition: all 0.2s ease;
    }
</style>
