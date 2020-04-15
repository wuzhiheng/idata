$(function () {
    // category
    $('.category-box input:radio').change(function () {
        $(this).parent().parent().addClass('active').siblings().removeClass('active')
    })
    // date-picker
    $('input[name="date-picker"]:radio').change(function () {
        $(this).parent().addClass('active').siblings().removeClass('active')
    })

    $.common.initDatePicker();

    $('#tableGrid').table({
        method:'get',
        url: ctx + "tendency.json", // 获取表格数据的url
        columns: [
            {
                field: 'category',
                title: '分类',
                formatter:function (v,r,i) {
                    return '<div>'+v+'</div><div class="category_sub">'+r['category_sub']+'</div>';
                }
            },
            {
                field: 'newBooks',
                title: '新书数',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['newBooks_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['newBooks_sub']+'</div>';
                }
            },
            {
                field: 'recommends',
                title: '推荐票',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['newBooks_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['recommends_sub']+'</div>';
                }
            },
            {
                field: 'fans',
                title: '粉丝',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['fans_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['fans_sub']+'</div>';
                }
            },
            {
                field: 'months',
                title: '月票',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['months_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['months_sub']+'</div>';
                }
            },
            {
                field: 'indexNumbers',
                title: '畅销指数',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['indexNumbers_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['indexNumbers_sub']+'</div>';
                }
            },
            {
                field: '',
                title: '',
                formatter:function (v,r,i) {
                    return '<span style="color: #32C3F2;cursor: pointer">趋势分析</span>';
                }
            },

        ]
    })


})


