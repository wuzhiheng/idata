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
        url: ctx + "sect-up.json", // 获取表格数据的url
        columns: [
            {
                field: 'sect',
                title: '流派'
            },
            {
                field: 'indexNumbers',
                title: '畅销指数'
            },
            {
                field: 'raise',
                title: '较前一天涨幅',
                cellStyle:function (v,r,i) {
                    let className = v.indexOf('+')==0 ? 'text-danger' : 'text-success';
                    return {classes:className}
                },
            },
            {
                field: 'raiseRank',
                title: '涨幅排名',
                align:'center',
                cellStyle:function (v,r,i) {
                    if(/^[123]$/.test(v)){
                        return {classes:"rank rank-"+v};
                    }
                    return {};
                },
            },
            {
                field: 'up',
                title: '',
                formatter:function (v,r,i) {
                    if('NEW' == v){
                        return '<span style="color: #fff;background: #df5566;font-size: 12px;display: inline-block;padding: 1px;line-height: 1">'+v+'</span>'
                    }
                    return v;
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


