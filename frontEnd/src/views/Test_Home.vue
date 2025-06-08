<template>
  <div>
    <div>
      <!-- <el-inp v-model="input" style="width: 240px" placeholder="Please input"/> -->
      <el-button v-model="shuru">{{ shuru }}</el-button>
    </div>
    <h4>——————————————————————我是分割线————————————————————————</h4>
    <div>
      <el-radio-group v-model="sex">
        <el-radio value="nan">男</el-radio>
        <el-radio value="nv">女</el-radio>
      </el-radio-group>
    </div>
    <h4>——————————————————————我是分割线————————————————————————</h4>
    <div>
      <el-radio-button v-for="item in neirong" :key="item.id" :label="item.id">{{ item.name }}</el-radio-button>
    </div>
    <h4>——————————————————————我是分割线————————————————————————</h4>
    <div>
      <el-radio-group v-model="select">
        <el-radio-button v-for="item in neirong" :key="item.id" :label="item.id">{{ item.name }}</el-radio-button>
      </el-radio-group>
    </div>
    <h4>——————————————————————我是分割线————————————————————————</h4>
    <div>
      <el-checkbox-group v-model="checkedValues">
        <!-- 复选框 存储value绑定的值 数组形式 -->
        <el-checkbox v-for="item in checklist" :key="item.id" :label="item.id">{{ item.name }}</el-checkbox>
      </el-checkbox-group>
    </div>
    <h4>——————————————————————我是分割线————————————————————————</h4>
    <div>
      <el-image
      style="width:100px;height:100px"
      :src = url
      :zoom-rate="1.2"
      :max-scale="7"
      :min-scale="0.2"
      :preview-src-list= srclist
      :initial-index="4"
      fit="cover"
      />
    </div>
    <h4>——————————————————————我是分割线————————————————————————</h4>
    <div>
      <el-carousel style="height: 300px;">
        <el-carousel-item v-for="(item,index) in srclist" :key="index">
          <h3 class="carousel-content">轮播项 {{ index+1 }}</h3>
          <img  alt="xxxx" :src="item">
        </el-carousel-item>
      </el-carousel>
    </div>
    <h4>——————————————————————我是分割线————————————————————————</h4>
    <div>
      <el-date-picker
      style="margin-left: 50px;"
      v-model="date"
      type="datetime"
      placeholder="请选择日期时间"
      format="YY:MM:DD HH:mm:ss"
      value-format="YYYY-MM-DD HH:mm:ss">
      <!-- v-model传参 格式得和format统一 -->
      </el-date-picker>
      <h3>你选择的时间是：{{ date }}</h3>
    </div>
    <h4>——————————————————————我是分割线————————————————————————</h4>
    <div>
      <el-date-picker
      style="margin-left: 50px;"
      v-model="dateRange"
      type="daterange"
      range-separator="到"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      format="YY-MM-DD"
      value-format="YYYY-MM-DD">
      <!-- v-model传参 格式得和format统一 -->
      </el-date-picker>
      <h3>你选择的时间是：{{ dateRange }}</h3>
    </div>
    <h4>——————————————————————我是分割线————————————————————————</h4>
    <div>
      <el-table :data="tableData" style="width: 100%" stripe>
        <!-- stripe 让表格带上斑马纹 -->
        <el-table-column prop="name1" label="lie1" width="180"/>
        <el-table-column prop="name2" label="lie2" width="180"/>
        <el-table-column prop="name3" label="lie3" width="180"/>
      </el-table>
    </div>
    <h4>——————————————————————我是分割线————————————————————————</h4>
    <div>
      <el-table :data="tableDataLarge" style="width: 100%" stripe>
        <!-- stripe 让表格带上斑马纹 -->
        <el-table-column prop="name1" label="lie1" width="180"/>
        <el-table-column prop="name2" label="lie2" width="180"/>
        <el-table-column prop="name3" label="lie3" width="180"/>
        <el-table-column>
          <template #default="scope">
            <!-- 拿到当前的行对象数据 -->
            {{ scope.row }}
            {{ scope.row.id }}
            <el-button type="danger" @click="Edit(scope.row)">编辑</el-button>
            <el-icon><edit/></el-icon>
            <el-button type="danger" @click="del(scope.row.id)">删除</el-button>
            <el-icon><Delete/></el-icon>
            <!-- icon全局导入过了 -->
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页还未能绑定上面的表格 -->
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[5, 10, 15, 20]"
        layout="total, jumper, prev, pager, next" 
        :total="50"
      />
    </div>
    <h4>——————————————————————我是分割线————————————————————————</h4>
    <div>
      <el-dialog v-model="dialogVisible" :title="'编辑行对象'+row1.id" width="500">
        <!-- dialogVisible 是一个控制 <el-dialog> 是否显示的布尔值 -->
        <el-form v-model="row1" label-width="80px">
          <el-form-item label="lie1"><el-input v-model="row1.name1"/></el-form-item>
          <el-form-item label="lie2"><el-input v-model="row1.name2"/></el-form-item>
          <el-form-item label="lie3"><el-input v-model="row1.name3"/></el-form-item>
        </el-form>
      </el-dialog>
    </div>
    
  </div>
</template>

<script setup lang="ts" name="Home">
import {ref} from 'vue';
const shuru = ref("neirong"); 
const sex = ref("nan");
const neirong = ref([
  {id:'01',name:'neirong1'},
  {id:'02',name:'neirong2'},
  {id:'03',name:'neirong3'},
]);
const select = ref('01');
const checklist = ref([
  {id:'01',name:'xuanze01'},
  {id:'02',name:'xuanze02'},
  {id:'03',name:'xuanze03'},
]);
// 选中的值（动态变化）
const checkedValues = ref([]); // 存储选中的 id，如 ['1', '2']
const url = "https://bpic.588ku.com/element_origin_min_pic/23/07/11/d32dabe266d10da8b21bd640a2e9b611.jpg!r650"
const srclist = [
  "https://img.keaitupian.cn/newupload/08/1628502171175140.jpg",
  "https://tse2-mm.cn.bing.net/th/id/OIP-C.StrDRqennoZNbzSPZapKZwAAAA?rs=1&pid=ImgDetMain",
  "https://pic2.zhimg.com/v2-428b3f295f94a2a0890e9464552ab24e_720w.jpg?source=172ae18b",
  ]
const date = ref('');
const dateRange = ref([
  "2025-06-01",  // 开始时间
  "2025-06-05"   // 结束时间
])//格式与value-format统一
const tableData = ref([
  {name1:'woshi1', name2:'woshi2', name3:'woshi3'},
  {name1:'woshi4', name2:'woshi5', name3:'woshi6'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9'}
])
const tableDataLarge = ref([
  {name1:'woshi1', name2:'woshi2', name3:'woshi3', id:'1'},
  {name1:'woshi4', name2:'woshi5', name3:'woshi6', id:'2'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'3'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'4'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'5'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'6'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'7'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'8'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'9'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'10'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'11'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'12'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'13'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'14'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'15'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'16'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'17'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'18'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'19'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'20'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'21'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'22'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'23'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'24'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'25'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'26'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'27'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'28'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'29'},
  {name1:'woshi7', name2:'woshi8', name3:'woshi9', id:'30'},
])
let currentPage = ref(1);
let pageSize = 5;
const del = (id:string)=>{
  alert('删除id='+id+'的数据');
}
interface TableRow {
  id: string;
  name1: string;
  name2: string;
  name3: string;
}
const row1 = ref<TableRow>({
  id: '',
  name1: '',
  name2: '',
  name3: ''
});
let dialogVisible = ref(false);
const Edit = (row:any)=>{
  row1.value = { ...row };  // 复制一份以避免直接修改原数据
  dialogVisible.value = true;
}

</script>
