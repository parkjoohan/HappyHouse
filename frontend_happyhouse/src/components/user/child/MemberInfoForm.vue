<template>
  <b-row>
    <b-col></b-col>
    <b-col cols="8">
      <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
        <b-form class="text-left">
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
              v-model="userInfo.userPhone"
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
      </b-card>
    </b-col>
    <b-col></b-col>
  </b-row>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import { modifyInfo, deleteInfo } from "@/api/member";

const memberStore = "memberStore";

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
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.viewMode = true;
    this.user = this.userInfo;
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    changeMode() {
      if (this.viewMode) {
        this.viewMode = false;
      } else {
        //this.viewMode = true;
        this.$router.push({ name: "Home" });
      }
    },
    modifyMember() {
      modifyInfo(
        this.user,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.moveHome();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    deleteMember() {
      deleteInfo(
        this.user,
        ({ data }) => {
          let msg = "탈퇴 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "탈퇴가 완료되었습니다.";
            this.SET_IS_LOGIN(false);
            this.SET_USER_INFO(null);
            sessionStorage.removeItem("access-token");
          }
          alert(msg);
          this.moveHome();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveHome() {
      this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style></style>
