
import { documentCompent } from './component/document/document.js'
import { dashbordCompent } from './component/dashbord/dashbord.js'


//Vue.use(VueRouter);


const routes = [
    {
        path: '/',
        name: 'home1',
    },
    {
        path: '/document',
        name: 'document',
        component: documentCompent
    },
    {
        path: '/dashbord',
        name: 'dashbord',
        component: dashbordCompent,
        children: [
            {
                path: '/',
                name: 'home1',
            },
            {
                path: '/document',
                name: 'document',
                component: documentCompent
            },
            {
                path: '/dashbord',
                name: 'dashbord',
                component: dashbordCompent
            }
        ]
    }



];





const RouterConfig = {
    mode: 'history',
    scrollBehavior: () => ({
        y: 0
    }),
    routes: routes
}


const createRouter = () => new VueRouter(RouterConfig)

// 创建路由实例
const router = createRouter()
//const router = new VueRouter({ routes });

export { router };
