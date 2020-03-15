
$(function () {

    laydate.render({
        elem: '#inStorageDate',
        max:0
    });

    //初始化表格数据
    $('#tableGrid').bootstrapTable({
        url: ctx + "drug/list", // 获取表格数据的url
        method:'post',
        contentType:'application/x-www-form-urlencoded',
        cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
        striped: true,  //表格显示条纹，默认为false
        pagination: true, // 在表格底部显示分页组件，默认false
        pageList: [10, 20, 30, 40, 50], // 设置页面可以显示的数据条数
        pageSize: 10, // 页面数据条数
        pageNumber: 1, // 首页页码
        sidePagination: 'server', // 设置为服务器端分页
        toolbar:'#toolbar',
        showColumns:true,
        showRefresh:true,
        showToggle:true,
        iconSize: 'default',
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                status: $('#queryForm [name=status]').val(),
                medicalInstitutionName: $('#queryForm [name=medicalInstitutionName]').val(),
                itemName: $('#queryForm [name=itemName]').val(),
                inStorageDate: $('#queryForm [name=inStorageDate]').val(),
            };
            return temp;
        },
        singleSelect: true,
        columns: [
            {
              field:'id',
              title:'#',
              formatter:function (v,r,i) {
                  return parseInt(i)+1;
              }
            },
            {
                field: 'medicalInstitutionName',
                title: '医院名称',
            },
            {
                field: 'deptName',
                title: '科室名称',
            },
            {
                field: 'itemTypeName',
                title: '项目类型名称',
            },
            {
                field: 'itemName',
                title: '项目名称',
            },
            {
                field: 'inStorageDate',
                title: '入库日期',
                formatter:function (v,r,i) {
                    return v ? v.replace('00:00:00','') : '';
                }
            },
            {
                field: 'inAmount',
                title: '入库数量',
            },
            {
                field: 'stockInPrice',
                title: '进货价格',
            },
            {
                field: 'productionDate',
                title: '生产日期',
                formatter:function (v,r,i) {
                    return v ? v.replace('00:00:00','') : '';
                }
            },
            {
                field: 'validDate',
                title: '有效期(月)',
            },
            {
                field: 'productionBatchNo',
                title: '生产批号',
            },
            {
                field: 'recordStaffName',
                title: '登记人员名称',
            },
            {
                field: '',
                title: '操作',
                formatter: function (v, r, i) {
                    var html =
                        '<div class="btn-group  btn-group-sm" style="color:#fff">' +
                        '<a class="btn btn-primary"  title="调拨"><i class="fa fa-play-circle"></i> 调拨</a>' +
                        '<a class="btn btn-success" title="调配"><i class="fa fa-play-circle"></i> 调配</a>' +
                        '<a class="btn btn-danger"  title="处方"><i class="fa fa-play-circle"></i> 处方</a>' +
                        '</div>';

                    return html;
                }
            }

        ]
    })
});

//查询数据
function doQuery() {
    $('#tableGrid').bootstrapTable('refresh');
}
