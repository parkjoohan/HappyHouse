<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="primary">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/ssafy_logo.png"
            class="d-inline-block align-middle"
            width="90px"
            alt="Kitten"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Home' }" class="link"
              ><b-icon icon="house" font-scale="1"></b-icon> 부동산
            </router-link></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Board' }" class="link"
              ><b-icon icon="journal" font-scale="1"></b-icon>
              게시판</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Instargram' }" class="link"
              ><b-icon icon="instagram" font-scale="1"></b-icon>
              뉴스</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-nav-item href="#"
            ><router-link :to="{ name: 'SignUp' }" class="link"
              ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'SignIn' }" class="link"
              ><b-icon icon="key"></b-icon> 로그인</router-link
            ></b-nav-item
          >
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "NaviBar",
};
</script>

<style></style>
