
//vue
var app = new Vue({
    el: '#app',
    data: {
        sqlList: [],
    },
});

$(function () {
    //初始化表格数据
    $('#tableGrid').bootstrapTable({
        url: ctx + "zg/list", // 获取表格数据的url
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
                zbmc: $('#queryForm [name=zbmc]').val(),
                ztmc: $('#queryForm [name=ztmc]').val(),
                hzbm: $('#queryForm [name=hzbm]').val(),
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
                field: 'ztmc',
                title: '主题名称',
            },
            {
                field: 'tbmc',
                title: '图表名称',
            },
            {
                field: 'zbmc',
                title: '指标名称',
            },
            {
                field: 'zbbm',
                title: '指标编码',
            },
            {
                field: 'zbdw',
                title: '指标单位',
            },
            {
                field: 'hzbm',
                title: '表名',
            },

            {
                field: 'dataSql',
                title: '获取数据SQL',
                formatter: function (v, r, i) {
                    var html =
                        '<button type="button" class="btn btn-primary btn-sm" onclick="doShowSql(' + i + ')">' +
                        '<i class="fa fa-search"></i> </button>';

                    return v ? html : '-';
                }
            }
            ,
            {
                field: 'status',
                title: '状态',
                formatter: function (v, r, i) {
                    var html =
                        '<div class="custom-control custom-switch">\n' +
                        '    <input type="checkbox" class="custom-control-input" id="customSwitch-status-'+i+'" '+
                                    (v == '1' ? 'checked':'')+' onclick="switchStatus(this,\'' + r.id + '\')"/>' +
                        '    <label class="custom-control-label" for="customSwitch-status-'+i+'"></label>\n' +
                        '</div>';

                    return html;
                }
            },
            {
                field: '',
                title: '操作',
                formatter: function (v, r, i) {
                    var html =
                        '<div class="btn-group  btn-group-sm" style="color:#fff">' +
                        '<a class="btn btn-primary" onclick="doExecute(\'' + r.id + '\',\''+r.zbmc+'\')" title="执行"><i class="fa fa-play-circle"></i></a>' +
                        '<a class="btn btn-success" onclick="doEdit(' + i + ')" title="修改"><i class="fa fa-edit"></i></a>' +
                        '<a class="btn btn-danger" onclick="doDelete(\'' + r.id + '\')" title="删除"><i class="fa fa-trash"></i></a>' +
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

//修改
function doEdit(index) {

    var rows = $('#tableGrid').bootstrapTable('getData');
    if(rows.length == 0) return;
    var data = rows[index];

    autoFillData($('#saveForm'), data);

    $('#myModalLabel').text('修改');
    $('#myModal').modal('show');

    setTimeout(function () {
        $('#saveForm').valid()
    }, 200);
}
//删除
function doDelete(id) {

    if (!confirm('是否确定删除？'))
        return;

    $.json({
        url: ctx + 'zg/delete?id=' + id,
        success: function (data) {
            weui.toast(data.msg, {
                duration: 100,
                callback: function () {
                    $('#tableGrid').bootstrapTable('refresh');
                }
            });
        }
    });
}
//新增
function doAdd() {
    $('#saveForm')[0].reset();
    $('#saveForm [name=id]').val('');
    $('#myModalLabel').text('新增');
    $('#myModal').modal('show');
}

//保存数据
function save() {

    var id = $('#saveForm [name=id]').val();
    var url = id ? ctx + 'zg/update' : ctx + 'zg/save';

    var data = $('#saveForm').serializeObject();

    $.json({
        type: 'post',
        url: url,
        data: data,
        success: function (res) {
            weui.toast(res.msg, {
                duration: 100,
                callback: function () {
                    $('#myModal').modal('hide');
                    $('#tableGrid').bootstrapTable('refresh');
                }
            });
        }
    })
}
//切换状态
function switchStatus(obj, id) {
    var status = obj.checked ? '1' : '0';

    $.json({
        type: 'post',
        url: ctx + 'zg/updateStatus',
        data: {id: id, status: status},
        success: function (res) {
            weui.toast(res.msg, {
                duration: 100,
                callback: function () {
                    $('#tableGrid').bootstrapTable('refresh');
                }
            });
        }
    })
}
//显示sql-text
function doShowSql(index) {
    var rows = $('#tableGrid').bootstrapTable('getData');
    if(rows.length == 0) return;
    var data = rows[index];

    $('#dataSql_text').val(data.dataSql);
    $('#myModal2').modal('show');
}

//校验form
$('#saveForm').validate({
    //校验表单成功后的回调
    submitHandler: save,
    rules: {
        zbmc: {
            required: true,
        },
        ztmc: {
            required: true,
        },
        tbmc: {
            required: true,
        },
        dataSql: {
            required: true
        },
    },
    messages: {
        zbmc: {
            required: "指标名称不能为空",
        },
        ztmc: "主题名称不能为空",
        tbmc: "图表名称不能为空",
        dataSql: "获取数据SQL不能为空"
    },
    //显示错误的包裹元素
    errorElement: 'span',
    errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.after(error);
    },
    highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
    }

});

//执行sql
function doExecute(id,zbmc) {

    var taskId = guid();

    app.sqlList.push({
        id:id,
        taskId:taskId,
        zbmc:zbmc,
        status:0,
        success:true,
        startTime:new Date().format("yyyy-MM-dd HH:mm:ss")
    })

    $.json({
        url: ctx + 'zg/execute',
        data:{id:id,taskId:taskId},
        success: function (data) {
            weui.toast(data.msg, {
                duration: 100,
                callback: function () {
                    $("html,body").animate({"scrollTop":0},0)
                }
            });
        }
    });
}

//socket
var stompClient = null;

connect();
//连接socket-订阅
function connect() {
    var socket = new SockJS(ctx+'zg_indicator');
    stompClient = Stomp.over(socket);
    stompClient.connect({"id": $("#id").val()}, function (frame) { //客户端ID
        console.log('Connected: ' + frame);
        stompClient.subscribe('/session/' +sessionId, function (res) { //表明客户端地址
            handleMessage(res);
        }, {"id": "Host_" + $("#id").val()});   //最后一个参数是header
    });
}

//处理websocket信息
function handleMessage(res) {
    var message = JSON.parse(res.body);
    // console.log("ws返回结果："+JSON.stringify(message));

    if(message.code != '200'){
        alert(message.msg);
    }
    var data = message.data;


    $.each(app.sqlList,function (index,item) {
        if(item.taskId == data.taskId){
            item.endTime = data.endTime;
            item.duration = data.duration;
            item.status = message.code == '200' ? 1 : 2;
            item.count = data.count;


            app.sqlList.splice(index,1,item);
            return false;
        }
    })
}


