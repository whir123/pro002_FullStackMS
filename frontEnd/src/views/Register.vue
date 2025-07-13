<template>
    <div class="container">
        <div class="card">
            <div class="card1">
                <div class="title">欢 迎 注 册</div>
                <el-form ref="formRef" :model="data.form" :rules="data.rules">
                    <el-form-item prop="username">
                        <el-input class="input" v-model="data.form.username" placeholder="请输入账号" prefix-icon="User"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input class="input" v-model="data.form.password" placeholder="请输入密码" prefix-icon="Lock" show-password></el-input>
                    </el-form-item>
                    <el-form-item prop="confirmPassword">
                        <el-input class="input" v-model="data.form.confirmPassword" placeholder="请确认密码" prefix-icon="Lock" show-password></el-input>
                    </el-form-item>
                </el-form>
                <div class="title"><el-button class="btn" @click="register">注 册</el-button></div>
                <div class="register">已有帐号？请<a href="/login" class="register">登录</a></div>
            </div>  
        </div>
    </div>
</template>

<script setup lang="ts" name="Register">
    import {reactive,ref} from 'vue';
    import request from '@/utils/request';//一定不能忘记
    
    const validatePass = (rule: any, value: any, callback: any) => {
        if (!value) {
            callback(new Error('请再次确认密码'));
        } else if (value != data.form.password) {
            callback(new Error('两次输入的密码不一致'));
        } else {
            callback();
        }
    }
    const data = reactive({
        form: {
            username: '',
            password: '',
            confirmPassword:'',
        },
        rules:{
            username:[{required:true, message:"请输入账号", trigger:'blur'}],
            password:[{required:true, message:"请输入密码", trigger:'blur'}],
            confirmPassword:[{validator:validatePass, trigger:'blur'}]
        }
    })
    
    const formRef = ref();
    
    //注册：后端接口
    const register = () => {
        formRef.value.validate((valid)=>{
            if(valid){
                request.post('/register', data.form).then(res => {
                    if(res.code === '200'){
                        ElMessage.success('注册成功');
                        setTimeout(() => {
                            location.href = '/login'
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
.register{
    text-align: right;
    margin-right: 5px;
    text-decoration: none;
    font-size: 16px;
    font-family: Microsoft YaHei;
    margin-top: -15px;
}
a{color: #567177;}
</style>
