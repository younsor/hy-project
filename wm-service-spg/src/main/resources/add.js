<script>
var videos = document.getElementsByTagName('iframe');
var length = videos.length;
var imageBase = 'http://cdn.apilnk.com';
var imageForward = 'http://weiying.adbpm.com/images?url=';
for (var i = 0; i < length; ++i) {
    var o=videos[i];
    var v=videos[i].getAttribute('src');
    var u=videos[i].className;
    if (v) {
        console.log(v);
        var _ = o.getAttribute("data-display-src");
        v = v.replace(/^https:/, location.protocol), v =
            v.replace(/^http:/, location.protocol),
            v = v.replace(/preview.html/, "player.html");
        console.log(v);
        var g = v.match(/[\?&]vid\=([^&]*)/);
        if (!g || !g[1]) {
            continue;
        }
        console.log(g);
        var x = g[1],
            y = document.getElementById("js_content").offsetWidth,
            b = Math.ceil(3 * y / 4);
        var result="http://v.qq.com/iframe/player.html?vid="+x+"&auto=0"+"&width="+y+"&height="+b;
        o.setAttribute("src", result);
    }
}
if(document.addEventListener){
    document.addEventListener("DOMContentLoaded",function(){
        replaceImgUrl()
    })
}else{
    document.onreadystatechange=function(){
        if(document.readyState==="complete"){
            replaceImgUrl()
        }
    }
}
var replaceImgUrl=function(){
    setTimeout(function(){
        [].forEach.call(document.querySelectorAll("img[data-src]"),function(a){
            var b=a.getAttribute("data-src");
            if(b.indexOf(imageBase) == -1){
                b = imageForward + b;
            }
            a.setAttribute("src",b);
            a.onload=function(){
                a.removeAttribute("data-src");
                a.style.cssText+="visibility: visible !important";
                a.style.cssText+="height: auto !important;"
            }
        })
    },1000)
};
</script>