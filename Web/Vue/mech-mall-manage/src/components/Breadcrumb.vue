<!-- src/components/Breadcrumb.vue -->
<template>
  <nav class="breadcrumb">
    <ul>
      <li v-for="(crumb, index) in breadcrumbs" :key="index">
        <router-link v-if="index < breadcrumbs.length - 1" :to="crumb.path">
          {{ crumb.meta.breadcrumb }}
        </router-link>
        <span v-else>{{ crumb.meta.breadcrumb }}</span>
      </li>
    </ul>
  </nav>
</template>

<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();

const breadcrumbs = computed(() => {
  const matchedRoutes = route.matched;
  return matchedRoutes.map((match, index) => {
    return {
      path: match.path,
      meta: match.meta,
      // Generate path up to this breadcrumb
      to: matchedRoutes.slice(0, index + 1).map(route => route.path).join('/')
    };
  });
});
</script>

<style scoped>
.breadcrumb {
  padding: 10px;
  background-color: #f0f0f0;
  border-radius: 4px;
  font-size: 24px;
  color: rgb(90, 90, 90);
}

.breadcrumb ul {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
}

.breadcrumb li {
  margin-right: 10px;
}

.breadcrumb li::after {
  content: '>';
  margin-left: 10px;
}

.breadcrumb li:last-child::after {
  content: '';
  margin: 0;
}
</style>
