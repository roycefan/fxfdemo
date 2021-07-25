import { template } from './template.js?version=1.0';


const siderCompoent = {
    template: template,
    data: function () {
        return {
            isCollapse: false
        }
    },
    created: function () {
   
    },
    methods: {
        handleSelect(key, keyPath) {
   
            this.$router.push({
                path: key,
                params: { data: key }
            })
        }


    }
};

export { siderCompoent };

