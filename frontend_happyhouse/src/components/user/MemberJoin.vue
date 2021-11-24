<template>
  <div>
    <div class="bg2">
      <video muted autoplay loop>
        <source src="@/assets/signup.mp4" type="video/mp4" />
      </video>
    </div>
    <div>
      <b-container class="bv-example-row mt-3">
        <row>
          <b-alert show><h3>회원가입</h3></b-alert>
        </row>
        <member-join-form type="register" />
      </b-container>
    </div>
  </div>
</template>

<script>
import MemberJoinForm from "./child/MemberJoinForm.vue";

export default {
  name: "MemberJoin",
  components: {
    MemberJoinForm,
  },
};
</script>

<style>
.bg2 {
  width: 50%;
  overflow: hidden;
  margin: 0px auto;
  position: relative;
}
.alert-info {
  color: #383d41;
  background-color: #e2e3e5;
  border-color: #d6d8db;
  max-width: 52%;
  margin: 0px auto;
  position: relative;
}
</style>
