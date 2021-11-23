<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="light">
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav
        ><router-link to="/">
          <img
            src="@/assets/logo.jpg"
            class="align-middle"
            width="150px"
            alt="Kitten"
          />
        </router-link>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'House' }" class="link"
              ><p class="h5 mb-2">
                <b-icon icon="building" font-scale="1"></b-icon> 부동산
              </p>
            </router-link></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Notice' }" class="link"
              ><p class="h5 mb-2">
                <b-icon icon="pen" font-scale="1"></b-icon> 게시판
              </p></router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'News' }" class="link"
              ><p class="h5 mb-2">
                <b-icon icon="newspaper" font-scale="1"></b-icon> 뉴스
              </p></router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-collapse id="nav-collapse" is-nav> </b-collapse>

        <b-collapse id="nav-collapse" is-nav> </b-collapse>
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item class="align-self-center"
            ><p class="h6 mb-2">
              <b-avatar
                variant="primary"
                v-text="userInfo ? userInfo.userId.charAt(0).toUpperCase() : ''"
              ></b-avatar>
              {{ userInfo.userName }}({{ userInfo.userId }})님 환영합니다.
            </p></b-nav-item
          >
          <b-nav-item class="align-self-center"
            ><router-link
              :to="{ name: 'MyInfo' }"
              class="link align-self-center"
              ><h5>내정보보기</h5></router-link
            ></b-nav-item
          >
          <b-nav-item
            class="link align-self-center"
            @click.prevent="onClickLogout"
            ><h5>로그아웃</h5></b-nav-item
          >
        </b-navbar-nav>
        <!--로그인 전-->
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item-dropdown right>
            <template #button-content>
              <p class="h6 mb-2">
                <b-icon icon="person-lines-fill" font-scale="2"></b-icon>
              </p>
            </template>
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'SignUp' }" class="link"
                ><p class="h5 mb-2">
                  <b-icon icon="person-plus"></b-icon>
                  회원가입
                </p></router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'SignIn' }" class="link"
                ><p class="h5 mb-2">
                  <b-icon icon="key"></b-icon> 로그인
                </p></router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "Header",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
    // moveNews() {
    //   this.$router.push("/news/list");
    //   this.intercepter();
    // },
  },
};
</script>

<style></style>
