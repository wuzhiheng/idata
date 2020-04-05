$(function () {
    $('#col-2').css('min-height',($(window).height() - 120)+'px');

    $('#tableGrid').table({
        url: ctx + "order/list", // 获取表格数据的url
        columns: [
            {
                field: 'id',
                width: 30,
                title: '#',
                formatter: function (v, r, i) {
                    return parseInt(i) + 1;
                }
            },
            {
                field: 'orderTime',
                title: '订单时间',
            },
            {
                field: 'id',
                title: '订单ID',
            },
            {
                field: 'orderType',
                title: '订单类型',
                formatter: function (v, r, i) {
                    let orderTypeDic = {
                        "1": "购买",
                        "2": "续费",
                        "3": "升级"
                    }
                    return orderTypeDic[v];
                }
            },
            {
                field: 'packageName',
                title: '套餐类型',
            },
            {
                field: 'period',
                title: '购买时长',
                formatter: function (v, r, i) {
                    return v + '个月'
                }
            },
            {
                field: 'packageFee',
                title: '套餐费用',
                formatter: function (v, r, i) {
                    return '￥' + v;
                }
            },
            {
                field: 'discount',
                title: '扣减费用',
                formatter: function (v, r, i) {
                    return '￥' + v;
                }
            },
            {
                field: 'actualFee',
                title: '实际支付费用',
                formatter: function (v, r, i) {
                    return '<span class="text-theme">￥' + v + '</span>';
                }
            },
            {
                field: 'status',
                title: '订单状态',
                align: 'center',
                formatter: function (v, r, i) {
                    let html;
                    if (v == '1') {
                        html = '<button class="btn btn-sm btn-green" onclick="$.modal.showPayModal()">立即支付</button>';
                    } else if (v == '2') {
                        html = '<span class="text-theme">支付成功</span>';
                    } else if (v == '3') {
                        html = '<span style="color:#FF4216">支付取消</span>';
                    }
                    return html;
                }
            },
        ]
    })
})
