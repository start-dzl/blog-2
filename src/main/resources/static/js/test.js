function get() {
    $.ajax(
        {
            type: 'get',
            url: '/article/lists',
            DataType: 'json',
            data: {}, success: function (data) {
                document.getElementsByClassName("good")[0].innerHTML = data['total']
            }
        }
    )
}

get()