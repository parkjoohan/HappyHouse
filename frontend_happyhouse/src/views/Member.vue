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

export default {};
</script>
