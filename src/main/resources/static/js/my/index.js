option = {
    color: ['#3398DB'],
    title: {
        text: '过去7天-粉丝数',
        textStyle: {
            color: '#5a5a5a',
            fontSize: 14,
            fontWeight: '400'
        },
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '0%',
        right: '0%',
        bottom: '0%',
        top: '35px',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        data: ['1日', '2日', '3日', '4日', '5日', '6日', '7日']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: [400, 760, 1000, 500, 125, 500, 250],
        type: 'bar',
        barWidth: '40%'
    }]
};

option2 = {
    color: ['#5aa2f8', '#79c87e', '#f6d465', '#df5667', '#8f68de', '#71c9ca'],
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
        formatter: function (name) {
            let clientlabels = this.option2.series[0].data.map(function (res){
                return res.name;
            });
            let clientcounts = this.option2.series[0].data.map(function (res){
                return parseInt(res.value);
            });
            let totalVal = clientcounts.reduce(function(v,res){
                return v+res
            },0);

            let index = 0;
            clientlabels.forEach(function (value, i) {
                if (value == name) {
                    index = i;
                    return false;
                }
            });

            return name  + " | " + (clientcounts[index] / totalVal * 100).toFixed(1).replace('.0','') +"%";
        },
        itemGap:20,
        icon: 'circle',
        itemWidth: 8,
        itemHeight: 8,
        orient: 'vertical',
        right: 10,
        top: 'center',
        data: ['0年', '1年', '2年', '3-5年', '5-8年', '8年+']
    },
    series: [
        {
            name: '读者书齢',
            type: 'pie',
            center: ['30%', '50%'],
            radius: ['30%', '50%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '15',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [
                {value: 36, name: '0年'},
                {value: 20, name: '1年'},
                {value: 16, name: '2年'},
                {value: 10, name: '3-5年'},
                {value: 9, name: '5-8年'},
                {value: 9, name: '8年+'}
            ]
        }
    ]
};
option3 = {
    color: ['#5aa2f8', '#79c87e', '#f6d465', '#df5667', '#8f68de', '#71c9ca'],
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
        formatter: function (name) {

            let clientlabels = this.option3.series[0].data.map(function (res){
                return res.name;
            });
            let clientcounts = this.option3.series[0].data.map(function (res){
                return parseInt(res.value);
            });
            let totalVal = clientcounts.reduce(function(v,res){
                return v+res
            },0);

            let index = 0;
            clientlabels.forEach(function (value, i) {
                if (value == name) {
                    index = i;
                    return false;
                }
            });

            return name  + " | " + (clientcounts[index] / totalVal * 100).toFixed(1).replace('.0','') +"%";
        },
        itemGap:20,
        icon: 'circle',
        itemWidth: 8,
        itemHeight: 8,
        orient: 'vertical',
        right: 10,
        top: 'center',
        data: ['1本', '2本', '3本', '4-8本', '8-15本', '15本']
    },
    series: [
        {
            name: '读者订阅作品数',
            type: 'pie',
            center: ['30%', '50%'],
            radius: ['30%', '50%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '15',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [
                {value: 36, name: '1本'},
                {value: 20, name: '2本'},
                {value: 16, name: '3本'},
                {value: 10, name: '4-8本'},
                {value: 9, name: '8-15本'},
                {value: 9, name: '15本+'}
            ]
        }
    ]
};

var myChart = echarts.init(document.getElementById('main'));
var myChart2 = echarts.init(document.getElementById('main2'));
var myChart3 = echarts.init(document.getElementById('main3'));

myChart.setOption(option);
myChart2.setOption(option2);
myChart3.setOption(option3);

//当窗口尺寸发生改变时，重新渲染
$(window).resize(function () {
    myChart.resize();
    myChart2.resize();
    myChart3.resize();
})

