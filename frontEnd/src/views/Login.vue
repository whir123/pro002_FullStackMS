<template>
    <div class="container">
        <div class="card">
            <div class="card1">
                <div class="title">欢迎登录后台管理系统</div>
                <el-form ref="formRef" :model="data.form" :rules="data.rules">
                    <el-form-item prop="username">
                        <el-input class="input" v-model="data.form.username" placeholder="请输入账号" prefix-icon="User"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input class="input" v-model="data.form.password" placeholder="请输入密码" prefix-icon="Lock" show-password></el-input>
                    </el-form-item>
                    <el-form-item prop="role">
                        <el-select v-model="data.form.role" style="width:100%" size="large" class="input">
                            <el-option value="ADMIN" label="管理员"></el-option>
                            <el-option value="EMP" label="员工"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div class="title"><el-button class="btn" @click="login">登 录</el-button></div>
                <div class="login">还没有账号？请<a href="/register" class="login">注册</a></div>
            </div>  
        </div>
    </div>
</template>

<script setup lang="ts" name="Login">
    import {reactive,ref} from 'vue';
    import request from '@/utils/request';//一定不能忘记

    const data = reactive({
        form: {
            username: '',
            password: '',
            role: 'EMP'
        },
        rules:{
            username:[{required:true, message:"请输入账号", trigger:'blur'}],
            password:[{required:true, message:"请输入密码", trigger:'blur'}]
        }
    })
    const formRef = ref();

    //登录：后端接口
    const login = () => {
        formRef.value.validate((valid)=>{
            if(valid){
                request.post('/login', data.form).then(res => {
                    if(res.code === '200'){
                        //存储后台传来的用户信息
                        //localStorage.setItem(key, value) 浏览器提供的 API 用于将数据持久化存储在本地
                        localStorage.setItem('B_myproject', JSON.stringify(res.data))//将 JavaScript 对象 res.data 转换为 JSON 格式的字符串
                        ElMessage.success('登录成功');
                        setTimeout(() => {
                            location.href = '/manager/home'
                        }, 500);
                    }else{
                        ElMessage.error(res.msg);
                    }
                })
            }
        })
    };
</script>

<style scoped>
.container {
    height: 100vh;
    overflow: hidden;
    background: linear-gradient(135deg, #567177 0%, #dadbdb 100%);
}
.card {
    margin-top: 15%;
    margin-left: 35%;
    width: 500px;
    height: 400px;
    background: rgba(255, 255, 255, 0.25);/* 半透明 */
    border-radius: 20px;/* 圆角 */
    backdrop-filter: blur(12px);/* 毛玻璃效果 */
    box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);/* 阴影 */
    border: 1px solid rgba(255, 255, 255, 0.3);
    flex-direction: column; 
}
.card1 {
    margin-top: 0%;
    margin-left: 20%;
    width: 300px;
    height: 400px;
    flex-direction: column; 
}
.title{
    text-align: center;
    font-size: 24px;
    font-family: Microsoft YaHei;
    font-weight: bold;
    margin-top: 40px;
    margin-bottom: 22px;
}
.input,.btn{
    text-align: center;
    width:300px;
    font-size: 20px;
    font-family: Microsoft YaHei;
    font-weight: bold;
    margin-top: 6px;
    flex-flow: column;
}
.btn{
    text-align: center;
    background-color: #50666b;
    color:white;
    height:40px;
    width:300px;
    margin-top: -30px;
}
.btn:hover{
    opacity: 0.9;
    /* transform: scale(1.02); */
    transition: all 0.2s ease;
}
.login{
    text-align: right;
    margin-right: 5px;
    text-decoration: none;
    font-size: 16px;
    font-family: Microsoft YaHei;
    margin-top: -15px;
}
a{color: #567177;}
</style>
