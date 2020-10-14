function loginStatus() {
    var localStorageElement = localStorage["token"];
    if (typeof localStorageElement == "undefined" || localStorageElement == null || localStorageElement == "") {
        $("#am-topbar-right1").attr("style", "visibility:hidden");
    } else {
        $("#am-topbar-right2").attr("style", "visibility:hidden");
    }

}


loginStatus();