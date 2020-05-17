function check() {
    /* if (${all_price} == 0) {
         alert("菜单为空！");
         return false;
     }*/

    // 保存缓存
    localStorage["a"] = "sdsdsd";

    return true;
}

function load() {
    // 读取缓存
    if (localStorage["a"]) {
        alert(localStorage["a"])
    }
}

check();
load();
