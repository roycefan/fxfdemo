<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <link href="css/admin.css" rel="stylesheet" type="text/css"/>

    <!-- js -->
    <
    <script src="js/moment-with-locales.min.js"></script>
    <script src="js/vue.min.js"></script>
    -
    <script src="js/antd-with-locales.min.js"></script>


    <script src="https://unpkg.com/element-ui@2.15.3/lib/index.js"></script>

    <link href="https://unpkg.com/element-ui@2.15.3/lib/theme-chalk/index.css" rel="stylesheet" type="text/css"/>


    <title></title>
</head>
<body>

<div id="app" name="app">

    <a-layout id="components-layout-demo-top" class="layout">
        <a-layout-header style="background:#565656">
            <div>demo</div>
        </a-layout-header>
        <a-layout-content style="padding: 0px">
            <div class="main" style="background:white">
                <transactionrecord-componet></transactionrecord-componet>
                <positon-componet></positon-componet>
            </div>
        </a-layout-content>
        <a-layout-footer style="text-align: center; height: 20px; padding-top: 10px">

        </a-layout-footer>
    </a-layout>
</div>


<script type="text/x-template" id="transactionrecord">
    <div style="height: 300px">
        record
    </div>

</script>


<script type="text/x-template" id="positon">
    <div style="height: 300px">
        postion
    </div>

</script>


<script>

    var transactionrecord = {
        template: '#transactionrecord',
        data: function () {
            return {};

        },

        mounted: async function () {


        },
        methods: {}
    };


    var positon = {
        template: '#positon',
        data: function () {
            return {};
        },

        mounted: async function () {

        },
        methods: {}
    };


    var app = new Vue({
        el: '#app',
        components: {
            'transactionrecord-componet': transactionrecord,
            'positon-componet': positon,
        },
        mounted: function () {

        }

    });
</script>

</body>
</html>