<template>
  <v-container class="bv-example-row">
    <v-row>
      <v-col></v-col>
      <v-col cols="8">
        <v-card
          class="mt-3"
          header="로그인"
          style="max-width: 40rem"
          align="left"
        >
          <v-form>
            <v-form>
              <v-text-field
                id="userid"
                v-model="user.userid"
                label="Input your ID"
              ></v-text-field>
            </v-form>
            <v-form>
              <v-text-field
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                label="Input your PW"
                @keypress.enter="login"
              ></v-text-field>
            </v-form>
            <v-btn type="btn" variant="primary" class="m-1" @click="login"
              >로그인</v-btn
            >
            <v-btn type="btn" variant="success" class="m-1">회원가입</v-btn>
          </v-form>
        </v-card>
      </v-col>
      <v-col></v-col>
    </v-row>
  </v-container>
</template>

<script>
import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

export default {
  name: "Login",
  data: function () {
    return {
      user: {
        userid: "",
        userpwd: "",
      },
      message: "",
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    },
  },
  methods: {
    login: function () {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch("LOGIN", this.user)
        .then(() => this.$router.replace(`/${this.nextRoute}`))
        .catch(({ message }) => (this.msg = message));
    },
  },
};
</script>
