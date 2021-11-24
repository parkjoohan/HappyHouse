<template>
  <div>
    <detail />
  </div>
</template>

<script>
import Detail from "@/components/news/Detail.vue";

export default {
  name: "read",
  components: {
    Detail,
  },
};
</script>

<style>
.table thead th {
  color: #fff;
  background-color: #3d3d3d;
}
.container,
.container-sm,
.container-md,
.container-lg,
.container-xl {
  max-width: 1140px;
  height: 700px;
}
</style>
