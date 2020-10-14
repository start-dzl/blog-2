var deleteArticleId = "";

$('.AdminIndexList .AdminIndexClick').click(function () {
    var flag = $(this).attr('class').substring(16);
    $('#statistics,#articleManagement,#articleThumbsUp,#articleCategories,#friendLink,#userFeedback,#privateWord').css("display", "none");
    $("#" + flag).css("display", "block");
});

//获取统计信息
function getStatisticsInfo() {
    $.ajax({
        type: 'get',
        url: '/getStatisticsInfo',
        dataType: 'json',
        data: {},
        success: function (data) {
            $('.allVisitor').html(data['allVisitor']);

            $('.allUser').html(data['allUser']);
            $('.articleNum').html(data['articleNum']);
        },
        error: function () {
            alert("获取统计信息失败");
        }
    });
}

//填充文章管理
function putInArticleManagement(data) {
    var articleManagementTable = $('.articleManagementTable');
    articleManagementTable.empty();
    $.each(data['content'], function (index, obj) {
        articleManagementTable.append($('<tr id="a' + obj['id'] + '"><td><a href="article/' + obj['articleId'] + '">' + obj['title'] + '</a></td><td>' + obj['publishDate'] + '</td><td>' + obj['articleCategories'] + '</td> <td><span class="am-badge am-badge-success">' + obj['visitorNum'] + '</span></td>' +
            '<td>' +
            '<div class="am-dropdown" data-am-dropdown>' +
            '<button class="articleManagementBtn articleEditor am-btn am-btn-secondary">编辑</button>' +
            '<button class="articleDeleteBtn articleDelete am-btn am-btn-danger">删除</button>' +
            '</div>' +
            '</td>' +
            '</tr>'));
    });
    articleManagementTable.append($('<div class="my-row" id="page-father">' +
        '<div id="articleManagementPagination">' +
        '<ul class="am-pagination  am-pagination-centered">' +
        '</ul>' +
        '</div>' +
        '</div>'));

    $('.articleManagementBtn').click(function () {
        var $this = $(this);
        var id = $this.parent().parent().parent().attr("id").substring(1);
        window.location.replace("/editor?id=" + id);
    });
    $('.articleDeleteBtn').click(function () {
        var $this = $(this);
        deleteArticleId = $this.parent().parent().parent().attr("id").substring(1);
        $('#deleteAlter').modal('open');
    })
}

$('.sureArticleDeleteBtn').click(function () {
    $.ajax({
        type: 'get',
        url: '/deleteArticle',
        dataType: 'json',
        data: {
            id: deleteArticleId
        },
        success: function (data) {
            if (data == 0) {
                dangerNotice("删除文章失败")
            } else {
                successNotice("删除文章成功");
                getArticleManagement(1);
            }
        },
        error: function () {
            alert("删除失败");
        }
    });
})

//点击文章管理
$('.AdminIndexList .articleManagement').click(function () {
    getArticleManagement(1);
});

//获得文章管理文章
function getArticleManagement(currentPage) {
    $.ajax({
        type: 'get',
        url: '/api/v0/article/backend/list',
        dataType: 'json',
        data: {
            size: 10,
            current: currentPage
        },
        success: function (data) {
            putInArticleManagement(data);
            scrollTo(0, 0);//回到顶部

            //分页
            $("#articleManagementPagination").paging({
                rows: size,//每页显示条数
                pageNum: currentPage,//当前所在页码
                pages: page,//总页数
                total: total,//总记录数
                callback: function (currentPage) {
                    getArticleManagement(currentPage);
                }
            });
        },
        error: function () {
            alert("获取文章信息失败");
        }
    });
}

getStatisticsInfo();