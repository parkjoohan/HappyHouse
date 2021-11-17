<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form>
        <b-form-group
          id="userId-group"
          label="아이디:"
          label-for="userId"
          description=""
        >
          <b-form-input
            id="userId"
            :disabled="true"
            v-model="user.userId"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="userPwd-group"
          label="비밀번호:"
          label-for="userPwd"
          description=""
        >
          <b-form-input
            id="userPwd"
            :disabled="viewMode"
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
            :disabled="viewMode"
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
            :disabled="viewMode"
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
            :disabled="viewMode"
            v-model="user.userPhone"
            type="text"
            required
            placeholder="010-xxx-xxxx"
          ></b-form-input>
        </b-form-group>
        <div v-if="this.viewMode">
          <b-button variant="primary" class="m-1" @click="changeMode"
            >수정하기
          </b-button>
          <b-button variant="danger" class="m-1" @click="deleteMember"
            >삭제
          </b-button>
        </div>
        <div v-else>
          <b-button variant="primary" class="m-1" @click="modifyMember"
            >확인
          </b-button>
          <b-button variant="danger" class="m-1" @click="changeMode"
            >취소
          </b-button>
        </div>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import axios from "axios";

export default {
  name: "MemberInfoForm",
  data() {
    return {
      user: {
        userId: "",
        userPwd: "",
        userName: "",
        userAddress: "",
        userPhone: "",
      },
      viewMode: false,
    };
  },
  props: {},
  created() {
    axios.defaults.headers.common["access-token"] =
      this.$store.state.accessToken;
    axios
      .get(`http://localhost:9999/user/info/` + this.$store.state.userId)
      .then(({ data }) => {
        //console.log(data);
        this.user = data;
      });
    this.viewMode = true;
  },
  methods: {
    changeMode() {
      if (this.viewMode) {
        this.viewMode = false;
      } else {
        this.$router.push({ name: "Home" });
        //this.viewMode = true;
      }
    },
    modifyMember() {
      axios
        .put(`http://localhost:9999/user/` + this.user.userId, {
          userId: this.user.userId,
          userPwd: this.user.userPwd,
          userName: this.user.userName,
          userAddress: this.user.userAddress,
          userPhone: this.user.userPhone,
        })
        .then(({ data }) => {
          let msg = "회원 정보 수정시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.moveHome();
        });
    },
    deleteMember() {
      axios
        .delete(`http://localhost:9999/user/` + this.user.userId)
        .then(({ data }) => {
          let msg = "회원 삭제시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.$store.dispatch("logout");
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
