<template lang="">
  <div class="container">
    <div id="news_title">오늘의 뉴스</div>
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  name: "news",
};
</script>
<style scoped>
#news_title {
  font-size: 28px;
  font-weight: bold;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  margin: 20px;
}
</style>
