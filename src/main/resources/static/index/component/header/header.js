import { template } from './template.js?version=1.0';


const headerComponent = {
    template: template,
    data: function () {
        return {
            activeIndex: '1',
        };
    },
    created: function () {

    },
    methods: {
        handleSelect: function (key, keyPath) {
            alert("key")
            console.log(key, keyPath);
        }
    }
};

export { headerComponent };

