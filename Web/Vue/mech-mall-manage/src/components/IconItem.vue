<template>
  <svg class="icon" aria-hidden="true">
    <use :xlink:href="icon"></use>
  </svg>
</template>

<script setup lang="ts">
import { PropType } from 'vue'

const props = defineProps({
  icon: {
    type: String as PropType<string>,
    required: true,
  },
})
</script>

<style scoped>
.icon {
  transform: scale(1.4);
  width: 15px;
  height: 15px;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
.icon:hover {
  cursor: pointer;
}
</style>