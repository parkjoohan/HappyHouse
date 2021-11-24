<template>
  <b-row>
    <b-col></b-col>
    <b-col cols="8">
      <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
        <b-form class="text-left" @submit="onSubmit" @reset="onReset">
          <b-form-group
            id="userId-group"
            label="아이디:"
            label-for="input-live"
            description=""
          >
            <b-form-input
              id="input-live"
              v-model="user.userId"
              :state="idState"
              aria-describedby="input-live-help input-live-feedback"
              type="text"
              required
              placeholder="아이디를 입력하세요"
              @keyup="idCheck"
            ></b-form-input>
            <!-- This will only be shown if the preceding input has an invalid state -->
            <b-form-invalid-feedback id="input-live-feedback">
              사용할 수 없는 아이디입니다.
            </b-form-invalid-feedback>
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
              placeholder="이름을 입력하세요"
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
              placeholder="주소를 입력하세요"
              @click="address_kakao"
            ></b-form-input>
            <b-form-input
              v-model="user.userAddressDetail"
              type="text"
              required
              placeholder="상세 주소를 입력하세요"
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
          <b-row class="mb-2">
            <b-col class="text-left col-sm-7">
              <b-button type="submit" variant="primary" class="m-1"
                ><b-icon icon="person-plus"></b-icon> 회원가입
              </b-button>
              <b-button type="reset" variant="danger" class="m-1"
                ><b-icon icon="arrow-clockwise"></b-icon> 초기화</b-button
              >
              <b-button @click="goHome" variant="secondary" class="m-1"
                ><b-icon icon="box-arrow-left"></b-icon> 홈으로</b-button
              >
            </b-col>
            <b-col class="text-right col-sm-5">
              <b-h3>회원이라면 </b-h3>
              <b-button @click="goLogin" variant="success" class="m-1"
                ><b-icon icon="box-arrow-left"></b-icon> 로그인</b-button
              >
            </b-col>
          </b-row>
        </b-form>
      </b-card>
    </b-col>
    <b-col></b-col>
  </b-row>
</template>

<script>
import { joinMember, checkId } from "@/api/member";

export default {
  name: "MemberJoinForm",
  data() {
    return {
      user: {
        userId: "",
        userPwd: "",
        userName: "",
        userAddress: "",
        userAddressDetail: "",
        userPhone: "",
      },
      idresult: false,
    };
  },
  watch: {},
  computed: {
    idState() {
      return this.idresult;
    },
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
      checkId(
        this.user.userId,
        ({ data }) => {
          let cnt = data.idcount;
          if (cnt == 0) {
            this.idresult = true;
          } else {
            this.idresult = false;
          }
        },
        (error) => {
          console.log(error);
          this.idresult = false;
        }
      );
    },
    registerMember() {
      this.user.userAddress += " " + this.user.userAddressDetail;
      joinMember(
        this.user,
        ({ data }) => {
          let msg = "회원가입 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "회원가입이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "SignIn" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    goHome() {
      this.$router.push("/");
    },
    goLogin() {
      this.$router.push("/user/singin");
      [3];
    },
    address_kakao() {
      new window.daum.Postcode({
        oncomplete: function (data) {
          document.getElementById("userAddress").value = data.address;
        },
      }).open();
    },
  },
};
</script>

<style>
.text-center {
  margin: 0px auto;
  position: relative;
}
</style>
