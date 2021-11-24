<template>
  <div>
    <div class="bg5">
      <video muted autoplay loop>
        <source src="@/assets/human.mp4" type="video/mp4" />
      </video>
    </div>
    <div>
      <b-container class="bv-example-row mt-3">
        <row>
          <b-alert show><h3>회원정보</h3></b-alert>
        </row>
        <member-info-form />
      </b-container>
    </div>
  </div>
</template>

<script>
import MemberInfoForm from "./child/MemberInfoForm.vue";

export default {
  name: "MemberInfo",
  components: {
    MemberInfoForm,
  },
};
</script>

<style>
.bg5 {
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
