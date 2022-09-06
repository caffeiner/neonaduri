<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="search">
    <div class="main-content">
      <div>추천</div>
      <div>검색</div>
      <div>지역</div>
      <div>표</div>
    </div>
    <navbar-component></navbar-component>
  </div>
</template>

<script>
import NavbarComponent from '~/components/NavbarComponent.vue'

export default {
  components: { NavbarComponent },
}
</script>

<style scoped>
.search {
  width: 100vw;
  height: 100vh;
  background-color: #eaf2f9;
  display: flex;
  justify-content: space-between;
}
.main-content {
  width: 85vw;
}
</style>
