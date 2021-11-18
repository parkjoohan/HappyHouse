import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";

import Member from "@/views/Member.vue";
import MemberLogin from "@/components/user/MemberLogin.vue";
import MemberJoin from "@/components/user/MemberJoin.vue";
import MemberInfo from "@/components/user/MemberInfo.vue";

import Notice from "@/views/Notice.vue";
import NoticeList from "@/components/notice/NoticeList.vue";
import NoticeView from "@/components/notice/NoticeView.vue";
import NoticeWrite from "@/components/notice/NoticeWrite.vue";
import NoticeUpdate from "@/components/notice/NoticeUpdate.vue";
import NoticeDelete from "@/components/notice/NoticeDelete.vue";

import House from "@/views/House.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다!");
    // next({ name: "SignIn" });
    router.push({ name: "SignIn" });
  } else {
    console.log("로그인 완료");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/user",
    name: "Member",
    component: Member,
    children: [
      {
        path: "singin",
        name: "SignIn",
        component: MemberLogin,
      },
      {
        path: "singup",
        name: "SignUp",
        component: MemberJoin,
      },
      {
        path: "myinfo",
        name: "MyInfo",
        beforeEnter: onlyAuthUser,
        component: MemberInfo,
      },
    ],
  },
  {
    path: "/notice",
    name: "Notice",
    component: Notice,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "NoticeList",
        component: NoticeList,
      },
      {
        path: ":no",
        name: "NoticeView",
        beforeEnter: onlyAuthUser,
        component: NoticeView,
      },
      {
        path: "write",
        name: "NoticeWrite",
        beforeEnter: onlyAuthUser,
        component: NoticeWrite,
      },
      {
        path: "update/:no",
        name: "NoticeUpdate",
        beforeEnter: onlyAuthUser,
        component: NoticeUpdate,
      },
      {
        path: "delete/:no",
        name: "NoticeDelete",
        beforeEnter: onlyAuthUser,
        component: NoticeDelete,
      },
    ],
  },
  {
    path: "/house",
    name: "House",
    component: House,
  },
  {
    path: "*",
    redirect: "/",
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
