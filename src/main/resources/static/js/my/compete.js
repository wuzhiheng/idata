$(function () {
    // category
    $('.category-box input:radio').change(function () {
        $(this).parent().parent().addClass('active').siblings().removeClass('active')
    })
    // date-picker
    $('input[name="date-picker"]:radio').change(function () {
        $(this).parent().addClass('active').siblings().removeClass('active')
    })

    $.common.initDatePicker('#date-picker');
    $.common.initDatePicker('#date-picker-2');

    $('.tableGrid-temp').table({
        method:'get',
        url: ctx + "compete.json", // 获取表格数据的url
        columns: [
            {
                field: 'category',
                title: '分类',
                formatter:function (v,r,i) {
                    return '<div>'+v+'</div><div class="category_sub">'+r['category_sub']+'</div>';
                }
            },
            {
                field: 'top10',
                title: 'TOP 10',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['top10_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['top10_sub']+'</div>';
                }
            },
            {
                field: 'top11-50',
                title: '11-50名',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['top11-50_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['top11-50_sub']+'</div>';
                }
            },
            {
                field: 'top51-100',
                title: '51-100名',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['top51-100_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['top51-100_sub']+'</div>';
                }
            },
            {
                field: 'top101-500',
                title: '101-500名',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['top101-500_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['top101-500_sub']+'</div>';
                }
            },
            {
                field: 'top501-1000',
                title: '501-1000名',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['top501-1000_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['top501-1000_sub']+'</div>';
                }
            },
            {
                field: 'top1001',
                title: '1000+名',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['top1001_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['top1001_sub']+'</div>';
                }
            },
            {
                field: '',
                title: '',
                formatter:function (v,r,i) {
                    return '<span style="color: #32C3F2;cursor: pointer" onclick="$.modal.showTendencyModal()">趋势分析</span>';
                }
            },

        ]
    })

    $('#tableGrid-5').table({
        method:'get',
        url: ctx + "compete-2.json", // 获取表格数据的url
        columns: [
            {
                field: 'category',
                title: '分类',
                formatter:function (v,r,i) {
                    return '<div>'+v+'</div><div class="category_sub">'+r['category_sub']+'</div>';
                }
            },
            {
                field: 'newer',
                title: '新人',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['newer_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['newer_sub']+'</div>';
                }
            },
            {
                field: 'lv1',
                title: 'LV1',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['lv1_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['lv1_sub']+'</div>';
                }
            },
            {
                field: 'lv2',
                title: 'LV2',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['lv2_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['lv2_sub']+'</div>';
                }
            },
            {
                field: 'lv3',
                title: 'LV3',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['lv3_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['lv3_sub']+'</div>';
                }
            },
            {
                field: 'lv4',
                title: 'LV4',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['lv4_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['lv4_sub']+'</div>';
                }
            },
            {
                field: 'lv5',
                title: 'LV5',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['lv5_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['lv5_sub']+'</div>';
                }
            },
            {
                field: 'god',
                title: '大神',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['god_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['god_sub']+'</div>';
                }
            },
            {
                field: 'platinum',
                title: '白金',
                formatter:function (v,r,i) {
                    let text_class = $.str.getTextClass(r['platinum_sub']);
                    return '<div>'+v+'</div><div class="'+text_class+'">'+r['platinum_sub']+'</div>';
                }
            },
            {
                field: '',
                title: '',
                formatter:function (v,r,i) {
                    return '<span style="color: #32C3F2;cursor: pointer" onclick="$.modal.showTendencyModal()">趋势分析</span>';
                }
            },

        ]
    })


})


