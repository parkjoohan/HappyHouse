<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="light">
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
            ><router-link :to="{ name: 'House' }" class="link"
              ><b-icon icon="house" font-scale="1"></b-icon> 부동산
            </router-link></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Notice' }" class="link"
              ><b-icon icon="journal" font-scale="1"></b-icon>
              게시판</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <!--로그인 후-->
        <b-navbar-nav class="ml-auto" v-if="accessToken">
          <b-nav-item href="#" v-if="accessToken"
            ><router-link :to="{ name: 'MyInfo' }" class="link"
              ><b-icon icon="person-circle"></b-icon> 내정보</router-link
            ></b-nav-item
          >
          <b-nav-item v-if="accessToken">
            <b-btn @click="logout">로그아웃</b-btn>
          </b-nav-item>
        </b-navbar-nav>
        <!--로그인 전-->
        <b-navbar-nav class="ml-auto" v-else>
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
  computed: {
    accessToken() {
      //console.log(this.$store.getters.accessToken);
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    logout() {
      //console.log("로그아웃");
      this.$store.dispatch("logout");
      this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style></style>
