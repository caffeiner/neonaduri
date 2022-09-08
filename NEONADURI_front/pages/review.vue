<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="review">
    <div class="main-content">
      <div>
        <div class="spot-img">
          <img src="/review/deoksugung.jpg" alt="" />
        </div>
        <div>
          <div class="spot-main">
            <div class="spot-main-title">동궁과 월지</div>
            <button>경유지추가</button>
          </div>
          <div class="spot-content">
            신라의 문무왕이 삼국을 통일한 후 궁궐을 넓게 확장해 태자가
            거처하도록 한 게 동궁! 밤에 보니깐 더 화려해보이네요.
          </div>
        </div>
      </div>
      <div class="spot-review">
        <div class="spot-review-title">후기</div>
        <div class="spot-review-content"></div>
      </div>
    </div>
    <navbar-component></navbar-component>
  </div>
</template>

<script>
import NavbarComponent from '~/components/NavbarComponent.vue'

export default {
  component: { NavbarComponent },
}
</script>

<style scoped>
.review {
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: space-between;
}
.main-content {
  width: 90%;
  margin-left: 20px;
}
.spot-img {
  text-align: center;
  margin-bottom: 40px;
}
.spot-img > img {
  width: 500px;
}
.spot-main {
  display: flex;
  justify-content: space-between;
}
.spot-main-title {
  font-size: 20px;
  font-weight: bold;
}
.spot-main > button {
  padding: 10px;
  background-color: #45a9c8;
  border-radius: 5px;
  color: white;
}
.spot-content {
  width: 70%;
  margin-bottom: 40px;
}
.spot-review-title {
  font-size: 20px;
  font-weight: bold;
}
.carousel-wrap {
  margin: 90px auto;
  padding: 0 5%;
  width: 80%;
  position: relative;
}
</style>
