// 定义懒加载插件
import { useIntersectionObserver } from '@vueuse/core'

export const lazyPlugin = {
	install (app) {
		// 懒加载指令逻辑
		app.directive('img-lazy', {
			mounted (el, binding) {
				// el: 指令绑定的元素
				// binding: 指令等于号后面绑定的表达式的值  图片url
				// console.log(el, binding.value);
				useIntersectionObserver(
					el, // 要监听谁,就传谁
					// isIntersecting是bool值,监听的el是否进入视图区域
					([{ isIntersecting }]) => {
						// console.log(isIntersecting);
						if (isIntersecting) {
							// 进入视图区域
							el.src = binding.value
						}
					},
				)
			}
		})
	}
}