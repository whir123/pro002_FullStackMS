<template>
  <div>

    <!-- 查询卡片 -->
    <div class="card">
        <el-card>
            <el-input class="input" v-model="data.name" placeholder="请输入名称查询" prefix-icon="Search"></el-input>
            <el-button class="btn" @click="load">查询姓名</el-button>
            <el-button class="btn" @click="reset">重 置</el-button>
        </el-card>
    </div>

    <!-- 操作卡片 -->
    <div class="card">
        <el-card>
            <el-button class="btn" @click = 'handleAdd'>新 增</el-button>
            <el-button class="btn" @click = 'delBatch(data.ids)'>批量删除</el-button>
            <!-- <el-button class="btn">导 入</el-button> -->
            <!-- <el-button class="btn">导 出</el-button> -->
        </el-card>
    </div>

    <!-- 表格数据卡片 -->
    <div class="card">
        <el-card>
            <el-table :data="data.tableData" class="tab" stripe @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55"/>
                <el-table-column label="账号" prop="username" width="120"/>
                <el-table-column label="角色" prop="role" width="120"/>
                <el-table-column label="姓名" prop="name" width="150"/>
                <el-table-column label="性别" prop="sex" width="120"/>
                <el-table-column label="工号" prop="no" width="120"/>
                <el-table-column label="年龄" prop="age" width="120"/>
                <el-table-column label="个人介绍" prop="description" show-overflow-tooltip/>
                <el-table-column label="部门" prop="departmentName" width="120"/>
                <el-table-column label="操作" width="200">
                    <template #default="scope">
                        <el-button class="btn2" @click="handleRow(scope.row)">编辑</el-button>
                        <el-button class="btn2" @click="del(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination class="pag"
                @current-change="load"
                @size-change="load"
                v-model:current-page="data.pageNum"
                v-model:page-size="data.pageSize"
                background
                layout="total, sizes, prev, pager, next, jumper"
                :total="data.total"
            />
        </el-card>
    </div>

    <!-- 操作新增时弹窗 -->
    <el-dialog title="新增员工信息" v-model="data.formVisible" width="500" class="dialog" destroy-on-close>
        <el-form ref="formRef" :rules="data.rules" :model="data.form" class="form" label-width="80px" >
            <!-- 输入账号 -->
            <el-form-item label="账 号" prop="username"> 
                <el-input v-model="data.form.username" autocomplete="off" placeholder="请输入账号"/>
            </el-form-item>
            <!-- 输入姓名 -->
            <el-form-item label="姓 名" prop="name">
                <el-input v-model="data.form.name" autocomplete="off" placeholder="请输入姓名"/>
            </el-form-item>
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
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button class='btn' @click="data.formVisible = false">取 消</el-button>
                <el-button class='btn1' @click="save">保 存</el-button>
            </div>
        </template>
    </el-dialog>


  </div>
</template>

<script setup lang="ts" name="Employee">
    import {ref,reactive} from 'vue';
    import request from "@/utils/request.js";
    const data = reactive({
        name: null,
        tableData: [],
        pageNum: 1,
        pageSize: 10,
        total: 0,
        formVisible: false,
        form: {},
        ids: [],
        rules: {
            username:[
                {required:true, message:'请输入账号', trigger: 'blur'}//鼠标失焦触发
            ],
            name:[
                {required:true, message:'请输入姓名', trigger: 'blur'}
            ],
            no:[
                {required:true, message:'请输入工号', trigger: 'blur'}
            ],
        }
    })

    const formRef = ref();

    const load = () => {
        request.get('/employee/selectPage',{
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                name: data.name
            }
        }).then(res => {
            data.tableData = res.data.list;
            data.total = res.data.total;
        })
    }
    load();

    const reset = () => {//重置
        data.name = null;
        load();
    }

    const handleAdd = () => {//打开弹窗
        data.formVisible = true
        data.form = {}
    }

    const save = () => {//保存 [新增 / 编辑]
        formRef.value.validate((valid) => {
            if (valid) {
                data.form.id ? update() : add()
            }
        })
    }
    const add = () => {
        request.post('employee/add',data.form).then(res => {
            if (res.code === '200'){
                data.formVisible = false;
                ElMessage.success('操作成功');
                load();
            } else {
                ElMessage.error(res.msg);
            }
        })
    }
    const update = () => {
        request.put('employee/update',data.form).then(res => {
            if (res.code === '200'){
                data.formVisible = false;
                ElMessage.success('操作成功');
                load();
            } else {
                ElMessage.error(res.msg);
            }
        })
    }

    const handleRow = (row) => {//编辑行
        data.form = JSON.parse(JSON.stringify(row)) //深拷贝一个新对象 不影响行对象
        data.formVisible = true;
    }

    const del = (id) => {
        ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？','删除确认', {type:'warning'}).then(() => {
            request.delete('employee/deleteById/'+id).then(res => {
            if (res.code === '200'){
                ElMessage.success('操作成功');
                load();
            } else {
                ElMessage.error(res.msg);
            }
            })
        }).catch()
    }

    const handleSelectionChange = (rows) => {//返回所有选中的行对象数组
        console.log(rows);
        data.ids = rows.map(row => row.id);
        console.log(data.ids);
    }

    const delBatch = (ids) => {
        if (data.ids.length === 0) {
            ElMessage.warning('请选择数据');
            return;
        }
        ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？','删除确认', {type:'warning'}).then(() => {
            request.delete('/employee/deleteBatch', {data: data.ids}).then(res => {
                if (res.code === '200'){
                    ElMessage.success('操作成功');
                    load();
                } else {
                    ElMessage.error(res.msg);
                }
            })
        }).catch()
    }
</script>

<style scoped>
    .card{
        margin-bottom: 13px;
    }
    .input{
        width: 240px; 
        margin-top: 8px; 
        margin-bottom: 8px;
        margin-right: 20px; 
        font-size: 15px; 
        font-family: Microsoft YaHei;
    }
    .btn, .btn1, .tab, .pag{
        margin-top: 8px; 
        margin-bottom: 8px; 
        font-size: 15px; 
        font-family: Microsoft YaHei;
        color:black;
    }
    .btn,.btn1 {
        font-size: 15px;
        font-family: Microsoft YaHei;
        padding: 8px 16px;
        border: none;
        background-color: #9ba8ab;
        color: white;
        margin-right: 8px;
        margin-top: 8px;
        margin-bottom: 8px;
        border-radius: 4px;
        cursor: pointer;
    }
    .btn1{
        background-color: #50666b;
    }
    .btn2{
        background-color: #f0f2f3;
        color: black;
    }
    .btn:hover,.btn1:hover {
        opacity: 0.9;
        transform: scale(1.02);
        transition: all 0.2s ease;
    }
    ::v-deep(.pag .number.is-active) {
        background-color: #9ba8ab !important; /* 选中背景色 */
        color: #fff !important;               /* 字体颜色 */
        border-radius: 4px;
        font-weight: bold;
    }
    .dialog{
        font-size: 15px; 
        font-family: Microsoft YaHei;
        color:black;
    }
    .form{
        padding-top: 20px;
        margin-right: 30px; 
        font-size: 15px; 
        font-family: Microsoft YaHei;
        color:black;
    }
</style>
