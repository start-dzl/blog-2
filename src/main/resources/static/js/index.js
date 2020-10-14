//填充热门文章
function putInHotArticle(data) {
    var bloglist = $('.blog-list');
    $.each(data, function (index, obj) {
        var center = $(
            '<li>' +
            '<a  href="' + obj['thisArticleUrl'] + '">' + obj['articleTitle'] + '</a>' +
            '</li>'
        )
        bloglist.append(center);
    })
}

function putTagClud(data) {

    var TagCloud = $('#IndexTagCloud')
    $.each(data, function (index, obj) {
        var center = $(
            ' <a href="/tags?tag=' + obj['name'] + '" class="blog-tag">' + obj['name'] + '</a>'
        )
        TagCloud.append(center);
    })

}

//填充文章
function putInArticle(data) {
    var articles = $('.articles');
    articles.empty();
    var articlehear = $(' <h1 class="bg-Title">\n' +
        '<div><a class="bg-myStory-a">文章</a></div>\n' +
        '</h1>'
    )
    articles.append(articlehear)
    $.each(data, function (index, obj) {
        if (index != (data.length) - 1) {
            var center = $('<div class="am-slider-desc">' +
                '<header class="article-header">' +
                '<h1 itemprop="name">' +
                '<a  href="' + obj['thisArticleUrl'] + '" target="_blank">' + obj['articleTitle'] + '</a>' +
                '</h1>' +
                '<div class="article-allta">' +
                '<div class="article-tag tagcloud">' +
                ' <ul class="article-tag-list article-allta">' +
                ' </ul>' +
                '</div>' +
                '</div>' +
                '</header>' +
                '<div class="article-entry">' +
                obj['articleTabloid'] +
                '</div>' +
                '<div class="read-all">' +
                '<a href="' + obj['thisArticleUrl'] + '" target="_blank">阅读全文 <i class="am-icon-angle-double-right"></i></a>' +
                '</div>' +
                '<hr class="style-two">' +

                '</div>');
            articles.append(center);
            var articletaglist = $('.article-tag-list');
            for (var i = 0; i < obj['articleTags'].length; i++) {
                var articleTag = $('<a class="article-tag-list-link color0 color6 color3" href="/tags?tag=' + obj['articleTags'][i] + '"> ' + obj['articleTags'][i] + '</a>');
                articletaglist.eq(index).append(articleTag);
            }
            // var likes = $('<span class="likes"><i class="am-icon-heart"> ' + obj['likes'] + '个喜欢</i></span>');
            // articleTags.eq(index).append(likes);
        }
    })

}

function ajaxHotArt() {
    $.ajax(
        {
            type: 'get',
            url: '/gethotAtricle',
            dataType: 'json',
            data: {},
            success: function (data) {
                putInHotArticle(data);
            },
            error: function () {
                alert("获得热门文章失败！");
            }
        }
    )

}

//首页文章分页请求
function ajaxFirst(currentPage) {
    //加载时请求
    $.ajax({
        type: 'POST',
        url: '/myArticles',
        dataType: 'json',
        data: {
            rows: "5",
            pageNum: currentPage
        },
        success: function (data) {
            //放入数据
            putInArticle(data);
            scrollTo(0, 0);//回到顶部

            //分页
            $("#pagination").paging({
                rows: data[data.length - 1]['pageSize'],//每页显示条数
                pageNum: data[data.length - 1]['pageNum'],//当前所在页码
                pages: data[data.length - 1]['pages'],//总页数
                total: data[data.length - 1]['total'],//总记录数
                callback: function (currentPage) {
                    ajaxFirst(currentPage);
                }
            });
        },
        error: function () {
            alert("获得文章信息失败！");
        }
    });
}

function ajaxTag() {
    $.ajax(
        {
            type: 'get',
            url: '/api/v0/tag/list/all',
            DataType: 'json',
            headers: {
                'Authorization': localStorage["token"]
            },
            data: {}, success: function (data) {
                if (data.length == 0) {
                    var tagCloud = $('#IndexTagCloud');
                    tagCloud.empty();
                    var widgetTagCloud = $('<div class="widget-tag-cloud"><span>暂无标签</span></div>');
                    tagCloud.append(widgetTagCloud);
                } else {
                    putTagClud(data);
                }
            },
            error: function () {
                alert("获得标签失败！");
            }
        }
    )
}

function localRefresh(currentPage, pageSize, isFirst) {
    if (!isFirst) {
        $('#table_refresh').load("/refresh/article?current=" + currentPage + "&size=" + pageSize);
    }
    getPageDetail();
    //分页
    $("#pagination").paging({
        rows: size,//每页显示条数
        pageNum: currentPage,//当前所在页码
        pages: page,//总页数
        total: total,//总记录数
        callback: function (currentPage, pageSize, haveSecond) {
            localRefresh(currentPage, pageSize, haveSecond);
        }
    });


}


ajaxTag();
localRefresh(1, 5, true);