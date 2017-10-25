<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MTL
  Date: 2017/10/24
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>三级联查</title>
    <script type="text/javascript" src="js/ajax.js"></script>
</head>
<body>
省份：<select name="province" id="s1">
    <option value="-1">请选择</option>
</select>
市<select name="" id="s2">
    <option  value="-1">请选择</option>
</select>
县<select name="" id="s3">
    <option value="-1">请选择</option>
</select>
</body>
<script type="text/javascript">
    window.onload = function () {
        sendAjax("GET", "area?method=getAllProvince", null, function (result) {
            var p = JSON.parse(result);
            for (var i = 0; i<p.length; i++){
                $("s1").appendChild(new Option(p[i].name, p[i].id));
            }
        });
        $("s1").onchange = function () {
            sendAjax("GET", "area?method=getCountry&pid=" + this.value, null, function (result) {
                var p = JSON.parse(result);
                $("s2").length = 1;
                for (var i = 0; i<p.length; i++){
                    $("s2").appendChild(new Option(p[i].name, p[i].id));
                }
            });
            $("s3").length = 1;
        }
        $("s2").onchange = function () {
            sendAjax("GET", "area?method=getCountry&pid=" + this.value, null, function (result) {
                var p = JSON.parse(result);
                $("s3").length = 1;
                for (var i = 0; i<p.length; i++){
                    $("s3").appendChild(new Option(p[i].name, p[i].id));
                }
            });
        }
    }

</script>
</html>
