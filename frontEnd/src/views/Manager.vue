<template>
  <div class="container">
    <!-- 头部开始 -->
    <div class="head">
      <h2>后台管理系统</h2>
      <div class="header-right">
        <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" alt="头像">
        <span class="username">{{ data.user.name }}</span>
      </div>
    </div>
    <!-- 头部结束 -->

    <!-- 下面部分开始 -->
    <div class="down">
      <!-- 左侧导航开始 -->
      <div class="menu">
        <el-menu class="elmenu" router :default-active="route.path">
            <el-menu-item class="elmenuitem" id="lzk1" index="/manager/home"><el-icon><HomeFilled/></el-icon>系统首页</el-menu-item>
            <el-menu-item class="elmenuitem" id="lzk1" index="/manager/data"><el-icon><DataLine/></el-icon>数据统计</el-menu-item>
            <el-sub-menu class="elmenuitem">
                <template #title>
                    <span id="lzk0"><el-icon><User/></el-icon>用户管理</span>
                </template>
                <el-menu-item id="lzk2" index="/manager/admin">管理员信息</el-menu-item>
                <el-menu-item id="lzk2" index="/manager/employee">员工信息</el-menu-item>
            </el-sub-menu>
            <el-menu-item class="elmenuitem" id="lzk1" index="/manager/person"><el-icon><UserFilled/></el-icon>个人信息</el-menu-item>
            <el-menu-item class="elmenuitem" id="lzk1" index="/manager/password"><el-icon><EditPen/></el-icon>修改密码</el-menu-item>
            <el-menu-item class="elmenuitem" id="lzk1" @click="logout"><el-icon><SwitchButton/></el-icon>退出登录</el-menu-item>
            <!-- 退出登录不仅要跳转 也得清除数据 -->
          </el-menu>
      </div>
      <!-- 左侧导航结束 -->

      <!-- 右侧主体开始 -->
      <div class="body">
        <router-view @updateUser="updateUser"/>
      </div>
      <!-- 右侧主体结束 -->
    </div>
    <!-- 下面部分结束 -->
  </div>
</template>

<script setup lang="ts" name="Manager">
  import { reactive } from 'vue';
  import { useRoute } from 'vue-router';
  const route = useRoute()  // 当前激活的路由
  console.log('当前路径是：', route.path)

  const data = reactive({
    user: JSON.parse(localStorage.getItem('B_myproject'))
  });

  const logout = () => {
    localStorage.removeItem('B_myproject');//清除当前用户登录的缓存数据
    location.href='/login';
  }

  const updateUser = () => {
    data.user = JSON.parse(localStorage.getItem('B_myproject'))
  }
</script>

<style scoped>
.container {
  padding: 12px;
  background: #f5f7fa; /* 整体背景 */
}
.head {
  position: relative; /* 加这个作为子元素定位参考 */
  background: linear-gradient(135deg, #9ba8ab 0%, #ccd0cf 100%);
  color: white;
  height: 64px;
  border-radius: 15px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  margin-bottom: 12px;
  display: flex;
  align-items: center;
  padding: 0 24px;
}
h2{
  font-family: Microsoft YaHei;
  font-weight: bold;
}
.header-right {
  position: absolute;
  top: 0;
  right: 0;
  height: 100%;
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 0 20px;
}
.header-right img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
.username {
  font-size: 16px;
  font-weight: bold;
  color: white;
  margin-right: 50px;
}

.down {
  display: flex;
  border-radius: 15px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  background: white;
}
.menu {
  width: 220px;
  background: linear-gradient(180deg, #ccd0cf 0%, #9ba8ab 100%);
}
.elmenu{
  border: 0;
  min-height: calc(100vh - 88px);
  background: transparent;
}
/* 阴影悬停动画 */
.head, .down {
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
}
.head:hover {
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}
.down:hover {
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}
/* 右侧内容区域 */
.body {
  flex: 1;
  width: 0;
  background: #ffffff;
  padding: 24px;
  min-height: calc(100vh - 88px);
  border-left: 1px solid #eaeef2;
}
/* 左侧主菜单项和子项统一样式 */
#lzk0{
  text-align: center;
  font-size: 18px;
  font-family: Microsoft YaHei;
  font-weight: bold;
  transition: all 0.3s ease;
  color: black;
  margin: 8px 12px;
  border-radius: 4px;
}
#lzk1{
  text-align: center;
  font-size: 18px;
  font-family: Microsoft YaHei;
  font-weight: bold;
  transition: all 0.3s ease;
  color: black;
  margin: 8px 12px;
  border-radius: 4px;
}
#lzk2{
  text-align: center;
  font-size: 17px;
  font-family: Microsoft YaHei;
  font-weight: bold;
  transition: all 0.3s ease;
  color: black;
  margin: 8px 12px;
}
/* hover 效果 */
#lzk1:hover{
  color:white;
  /* background-color: rgba(255, 255, 255, 0.3) !important; */
}
#lzk2:hover{
  color:rgba(56, 29, 29, 0.432);
  /* background-color: rgba(255, 255, 255, 0.3) !important; */
}
.elmenuitem:hover{
  color: #ffffff86;
  background-color: rgba(255, 255, 255, 0.3);
}
/* 设置选中项样式 */
::v-deep(#lzk1.is-active) {
  background-color: rgba(255, 255, 255, 0.25);
  color: #a17e7e;
  font-weight: bold;
  border-radius: 8px;
  transition: all 0.3s ease;
}
::v-deep(#lzk2.is-active) {
  background-color: rgba(255, 255, 255, 0);
  color: #a17e7e;
}

</style>