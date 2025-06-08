<template>
    <div class="card">
        <el-form ref="formRef" :rules="data.rules" :model="data.form" class="form" label-width="80px">
            <!-- 输入账号 -->
            <el-form-item label="账 号" prop="username"> 
                <el-input disabled v-model="data.form.username" autocomplete="off" placeholder="请输入账号"/>
            </el-form-item>
            <!-- 输入姓名 -->
            <el-form-item label="姓 名" prop="name">
                <el-input v-model="data.form.name" autocomplete="off" placeholder="请输入姓名"/>
            </el-form-item>

            <div v-if="data.user.role === 'EMP'">
                <!-- 输入性别 -->
                <el-form-item label="性 别">
                    <el-radio-group v-model="data.form.sex">
                        <el-radio value="男" label="男"></el-radio>
                        <el-radio value="女" label="女"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <!-- 输入工号 -->
                <el-form-item label="工 号" prop="no">
                    <el-input v-model="data.form.no" autocomplete="off" placeholder="请输入工号"/>
                </el-form-item>
                <!-- 输入年龄 -->
                <el-form-item label="年 龄">
                    <el-input-number v-model="data.form.age" autocomplete="off" placeholder="请输入年龄" style="width: 170px;"/>
                </el-form-item>
                <!-- 输入个人介绍 -->
                <el-form-item label="个人介绍">
                    <el-input :rows="3" type="textarea" v-model="data.form.description" autocomplete="off" placeholder="请输入个人介绍"/>
                </el-form-item>
            </div>
        </el-form>
        <div style="text-align: center">
            <el-button class="btn" @click="updateUser">更 新 个 人 信 息</el-button>
        </div>
    </div>
</template>

<script setup lang="ts" name="Person">
    import {ref,reactive} from 'vue';
    import request from '@/utils/request.js';

    const formRef = ref();
    const data = reactive({
        user: JSON.parse(localStorage.getItem('B_myproject')),
        form: {},
        rules:{
            username:[
                {required:true, message:'请输入账号', trigger: 'blur'}//鼠标失焦触发
            ],
            name:[
                {required:true, message:'请输入姓名', trigger: 'blur'}
            ],
            no:[
                {required:true, message:'请输入工号', trigger: 'blur'}
            ],
        },
    });
    if (data.user.role === "EMP") {
        request.get('/employee/selectById/' + data.user.id).then(res => {
            data.form = res.data;
        });
    } else {
            data.form = data.user;
    };

    //声明一个可以向父组件发送事件的接口
    const emit = defineEmits(['updateUser'])//更改信息后 同步一下上面的用户信息
    const updateUser = () => {
        if (data.user.role === 'EMP') {
            request.put('/employee/update', data.form).then(res => {
                if (res.code === '200') {
                    ElMessage.success('更新成功');
                    //更新缓存数据
                    localStorage.setItem('B_myproject', JSON.stringify(data.form))
                    //触发父级从缓存里面取到最新数据
                    emit('updateUser')
                } else {
                    ElMessage.error(res.msg);
                }
            })
        } else {
            request.put('/admin/update', data.form).then(res => {
                if (res.code === '200') {
                    ElMessage.success('更新成功');
                    //更新缓存数据
                    localStorage.setItem('B_myproject', JSON.stringify(data.form))
                    //触发父级从缓存里面取到最新数据
                    emit('updateUser')
                } else {
                    ElMessage.error(res.msg);
                }
            })
        }
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
    .btn{
        margin-top:20px;
        font-size: 18px; 
        font-family: Microsoft YaHei;
        width:80%;
        color:white;
        background-color: #9ba8ab;
    }
</style>
