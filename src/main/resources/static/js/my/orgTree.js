var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pid",
            rootPId: 0
        }
    },
    callback:{
        onClick:zTreeOnCheck
    }

};

function zTreeOnCheck(e,treeId,treeNode){
    var data = [];
    if(treeNode.pid){
        treeNode.children ? data = treeNode.children: data.push(treeNode)
        $('#tableGrid').bootstrapTable('load',data);
    }else{

    }
}

function changeData(node){
    node.pid ? node.iconSkin = 'fa fa-sitemap' : node.iconSkin = 'fa fa-tree'
    if(node.children){
        node.open = true;

        $.each(node.children,function (i,o) {
            changeData(o)
        })
    }else {
       node.iconSkin = 'fa fa-university'
    }
}

$(function () {

    $.json({
        url: ctx + 'orgTree/tree',
        success: function (data) {
           changeData(data.data[0])
            console.log(data.data)
            $.fn.zTree.init($("#orgTree"), setting, data.data);
        }
    })
    //初始化表格数据
    $('#tableGrid').bootstrapTable({
        url: ctx + "orgTree/list", // 获取表格数据的url
        method: 'post',
        contentType: 'application/x-www-form-urlencoded',
        cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
        striped: true,  //表格显示条纹，默认为false
        pagination: true, // 在表格底部显示分页组件，默认false
        pageList: [10, 20, 30, 40, 50], // 设置页面可以显示的数据条数
        pageSize: 10, // 页面数据条数
        pageNumber: 1, // 首页页码
        sidePagination: 'server', // 设置为服务器端分页
        toolbar: '#toolbar',
        // showColumns: true,
        // showRefresh: true,
        // showToggle: true,
        iconSize: 'default',
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                orgName: $('#queryForm [name=orgName]').val(),
            };
            return temp;
        },
        singleSelect: true,
        columns: [
            {
                field: 'id',
                title: '#',
                formatter: function (v, r, i) {
                    return parseInt(i) + 1;
                }
            },
            {
                field: 'orgCode',
                title: '机构代码',
            },
            {
                field: 'orgName',
                title: '机构名称',
            },
            {
                field: 'people',
                title: '法人代表',
            },
            {
                field: 'practiceTerm',
                title: '执业期限'
            },
            {
                field: 'practiceRange',
                title: '执业范围'
            },
            // {
            //     field: '',
            //     title: '操作',
            //     formatter: function (v, r, i) {
            //         var html =
            //             '<div class="btn-group  btn-group-sm" style="color:#fff">' +
            //             '<a class="btn btn-success" onclick="doEdit(' + i + ')" title="修改" style="display: inline-block ;margin-right: 10px"><i class="fa fa-edit"></i></a>' +
            //             '<a class="btn btn-danger" onclick="doDelete(\'' + r.id + '\')" title="删除"><i class="fa fa-trash"></i></a>' +
            //             '</div>';
            //
            //         return html;
            //     }
            // }

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
    if (rows.length == 0) return;
    var data = rows[index];

    autoFillData($('#saveForm'), data);
    $('#textarea').summernote('code', data.description);

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
        url: ctx + 'zgDesc/delete?id=' + id,
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
    var url = id ? ctx + 'zgDesc/update' : ctx + 'zgDesc/save';

    var data = $('#saveForm').serializeObject();

    data.description = $('#textarea').summernote('code');
    console.log(data);

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


//校验form
$('#saveForm').validate({
    //校验表单成功后的回调
    submitHandler: save,
    rules: {
        title1: {
            required: true,
        },
        title2: {
            required: true,
        }

    },
    messages: {
        title1: "一级标题不能为空",
        title2: "二级标题不能为空",
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




