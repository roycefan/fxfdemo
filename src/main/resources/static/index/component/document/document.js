import { template } from './template.js?version=1.0';


const documentCompent = {
    template: template,
    data: function () {
        return {
           
        }
    },
    created: function () {
        alert("adasda");
    },
    methods: {
      
    },
    mounted: function () { alert("mounted")},
    created: function () {

        alert("created")
    }
    
};

export { documentCompent };

