
import { headerComponent } from './component/header/header.js'
import { siderCompoent } from './component/sider/sider.js'
import { router  } from './router.js'


//new Vue({
//    el: '#app',
//    router: router,
//    data: function () {
//        return {
//            visible: false
//        }
//    },
//    components: {
//        "layout-header": headerComponent,
//        "layout-sider": siderCompoent,
//    }
//})

Vue.config.debugger = true;
debugger;

 new Vue({
    router: router,
    components: {
        "layout-header": headerComponent,
        "layout-sider": siderCompoent,
    },
    template: "#app"
}).$mount('#app');
