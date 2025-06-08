<template>
    <div class="card">
        <el-form ref="formRef" :rules="data.rules" :model="data.form" class="form" label-width="80px">
            <!-- 输入密码修改 -->
            <el-form-item label="原密码" prop="password" class="input"> 
                <el-input v-model="data.form.password" autocomplete="off" placeholder="请输入原密码" show-password/>
            </el-form-item>
            <el-form-item label="新密码" prop="newPassword" class="input"> 
                <el-input v-model="data.form.newPassword" autocomplete="off" placeholder="请输入新密码" show-password/>
            </el-form-item>
            <el-form-item label="确认密码" prop="confirmPassword" class="input"> 
                <el-input v-model="data.form.confirmPassword" autocomplete="off" placeholder="请确认新密码" show-password/>
            </el-form-item>
        </el-form>
        <div style="text-align: center">
            <el-button class="btn" @click="updatePassword">立即修改</el-button>
        </div>
    </div>
</template>

<script setup lang="ts" name="Person">
    import {ref,reactive} from 'vue';
    import request from '@/utils/request.js';
    const formRef = ref();
    const validatePass = (rule: any, value: any, callback: any) => {
        if (!value) {
            callback(new Error('请再次确认密码'));
        } else if (value != data.form.newPassword) {
            callback(new Error('两次输入的密码不一致'));
        } else {
            callback();
        }
    }
    const data = reactive({
        user: JSON.parse(localStorage.getItem('B_myproject')),
        form: {},
        rules:{
            password:[
                {required:true, message:'请输入原密码', trigger: 'blur'}//鼠标失焦触发
            ],
            newPassword:[
                {required:true, message:'请输入新密码', trigger: 'blur'}
            ],
            confirmPassword:[{validator:validatePass, trigger:'blur'}]
        },
    });

    const updatePassword = () => {
        data.form.id = data.user.id;
        data.form.role = data.user.role;
        formRef.value.validate((valid)=>{
            if(valid) {
                request.put('/updatePassword',data.form).then(res => {
                    if(res.code === '200') {
                        ElMessage.success('修改成功');
                        localStorage.removeItem('B_myproject');//清除当前用户登录的缓存数据
                        setTimeout(()=>{
                            location.href='/login';
                        },500);
                    } else {
                        ElMessage.error(res.msg);
                    }
                })
            }
        })
    }
</script>

<style scoped>
    .card{
        margin-top: 2%;
        margin-left: 3%;
        width: 1200px;
        height: 700px;
        background: rgba(255, 255, 255, 0.25);/* 半透明 */
        border-radius: 20px;/* 圆角 */
        backdrop-filter: blur(12px);/* 毛玻璃效果 */
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);/* 阴影 */
        border: 1px solid rgba(255, 255, 255, 0.3);
        flex-direction: column; 
    }
    .form{
        width: 95%; 
        margin-top: 100px; 
        margin-left: 25px;
        font-family: Microsoft YaHei;
    }
    .input{
        margin-bottom: 30px;
    }
    .btn{
        margin-top:20px;
        font-size: 18px; 
        font-family: Microsoft YaHei;
        width:80%;
        color:white;
        background-color: #9ba8ab;
    }
</style>
