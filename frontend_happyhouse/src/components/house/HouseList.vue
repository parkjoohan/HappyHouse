<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <house-list-row
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
      @select-apt="selectApt"
      @select-img="selectImage"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListRow from "@/components/house/HouseListRow.vue";
import { mapState } from "vuex";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  data() {
    return {
      apts: ["a"],
    };
  },
  computed: {
    ...mapState(["houses"]),
  },
  methods: {
    selectApt: function (apt) {
      this.$emit("select-apt", apt);
    },
    selectImage: function (img) {
      this.$emit("select-img", img);
    },
  },
};
</script>

<style></style>
