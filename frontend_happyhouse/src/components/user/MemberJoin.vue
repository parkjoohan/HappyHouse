<template>
  <b-container class="bv-example-row mt-3">
    <video muted autoplay loop>
      <source src="@/assets/signup.mp4" type="video/mp4" />
    </video>
    <row>
      <b-alert show><h3>글보기</h3></b-alert>
    </row>
    <member-join-form type="register" />
  </b-container>
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
video {
  width: 50%;
}
.alert-secondary {
  color: #383d41;
  background-color: #e2e3e5;
  border-color: #d6d8db;
  width: 60%;

  /* align-content: center; */
}
</style>
