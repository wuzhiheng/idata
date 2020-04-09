$(function () {
    $('.category-box input:radio').change(function () {
        $(this).parent().parent().addClass('active').siblings().removeClass('active')
    })
    var d = {}
    $('a[data-toggle="pill"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href");
        if(!d[target]){
            d[target] = true;
            $(target+' .pie').each(function (i,o) {
                var myChart = echarts.init(this);
                myChart.resize();
            })
        }
    })

    let height_arr = [];
    $('.tab-content>div').each(function (i,o) {
        height_arr.push($(this).height())
    })
    $('.tab-content>div').each(function (i,o) {
        $(this).height(Math.max(...height_arr))
    })

})

option = {
    color: ['#db733e', '#6f655c', '#75b9b5', '#e8ad59', '#d2d352'],
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
        itemGap:15,
        icon: 'circle',
        itemWidth: 8,
        itemHeight: 8,
        orient: 'vertical',
        right: 10,
        top: 'center',
        data: ['玄幻', '科幻', '都市', '历史', '仙侠']
    },
    series: [
        {
            name: '作品数分布',
            type: 'pie',
            center: ['40%', '50%'],
            radius: ['0%', '65%'],
            avoidLabelOverlap: false,
            label:{            //饼图图形上的文本标签
                normal:{
                    show:true,
                    position:'inner', //标签的位置
                    textStyle : {
                        fontWeight : 300 ,
                        fontSize : 12    //文字的字体大小
                    },
                    formatter:'{d}%'
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [
                {value: 25, name: '玄幻'},
                {value: 30, name: '科幻'},
                {value: 20, name: '都市'},
                {value: 15, name: '历史'},
                {value: 10, name: '仙侠'}
            ]
        }
    ]
};
$('.pie').each(function (i,o) {
    var myChart = echarts.init(this);
    myChart.setOption(option);
})


//当窗口尺寸发生改变时，重新渲染
$(window).resize(function () {
    // myChart.resize();
})

