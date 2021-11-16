<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userId-group"
          label="아이디:"
          label-for="userId"
          description=""
        >
          <b-form-input
            id="userId"
            v-model="user.userId"
            type="text"
            required
            placeholder="아이디 입력..."
            @keyup="idCheck"
          ></b-form-input>
          <h6>{{ idresult }}</h6>
        </b-form-group>

        <b-form-group
          id="userPwd-group"
          label="비밀번호:"
          label-for="userPwd"
          description=""
        >
          <b-form-input
            id="userPwd"
            v-model="user.userPwd"
            type="text"
            required
            placeholder="영문 숫자 포함 6자리 이상"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="userName-group"
          label="이름:"
          label-for="userName"
          description=""
        >
          <b-form-input
            id="userName"
            v-model="user.userName"
            type="text"
            required
            placeholder="이름 입력.."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="userAddress-group"
          label="주소:"
          label-for="userAddress"
          description=""
        >
          <b-form-input
            id="userAddress"
            v-model="user.userAddress"
            type="text"
            required
            placeholder="주소 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="userPhone-group"
          label="전화번호:"
          label-for="userPhone"
          description=""
        >
          <b-form-input
            id="userPhone"
            v-model="user.userPhone"
            type="text"
            required
            placeholder="010-xxx-xxxx"
          ></b-form-input>
        </b-form-group>

        <b-button type="submit" variant="primary" class="m-1"
          >회원가입
        </b-button>
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "MemberJoinForm",
  data() {
    return {
      user: {
        userId: "",
        userPwd: "",
        userName: "",
        userAddress: "",
        userPhone: "",
      },
      idresult: "",
    };
  },
  props: {},
  created() {},
  methods: {
    onSubmit(event) {
      event.preventDefault();
      this.registerMember();
    },
    onReset(event) {
      event.preventDefault();
      this.user.userId = "";
      this.user.userPwd = "";
      this.user.userName = "";
      this.user.userAddress = "";
      this.user.userPhone = "";
    },
    idCheck() {
      //console.log(this.user.userId);
      http.get(`/user/idCheck/` + this.user.userId).then(({ data }) => {
        //console.log(data);
        let cnt = data.idcount;
        if (cnt == 0) {
          this.idresult = this.user.userId + "는 사용 가능합니다.";
        } else {
          this.idresult = this.user.userId + "는 사용할 수 없습니다.";
        }
      });
    },
    registerMember() {
      http
        .post(`/user`, {
          userId: this.user.userId,
          userPwd: this.user.userPwd,
          userName: this.user.userName,
          userAddress: this.user.userAddress,
          userPhone: this.user.userPhone,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "회원가입이 완료되었습니다.";
          }
          alert(msg);
          this.moveHome();
        });
    },
    moveHome() {
      this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style></style>
