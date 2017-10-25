function $(id) {
    return document.getElementById(id);
}
function sendAjax(method, url, body, success) {
    var xmlHttp;
    if (window.XMLHttpRequest){
        xmlHttp = new XMLHttpRequest();
    }else {
        xmlHttp = new ActiveXObject("microsoft.XMLHTTP");
    }
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            console.info("状态码为4");
            if (xmlHttp.status == 200 || xmlHttp.status == 304) {
                console.info("进入正常响应");
                success(xmlHttp.responseText);
            }
        }
    }
    xmlHttp.open(method, url);
    if (method == "POST"){
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    }
    xmlHttp.send(body);
}
