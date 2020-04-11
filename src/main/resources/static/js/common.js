(function ($) {
    $.extend({
        json: function (option, defaultHandle = true) {
            let config = {
                dataType: 'json',
                type: 'post',
                loading:true
            };
            $.extend(config, option);
            config.url = config.url.replace(/(https?:\/\/.*)\/\/(.*)/, '$1\/$2');

            config.success = function (data, textStatus, xhr) {
                if(loading) loading.hide()
                if (data && data.code == '401') {
                    console.log(data.msg);
                    $.modal.showLoginModal();
                    $('#loginForm [name=forwardUrl]').val(location.href);
                    return;
                }

                if (defaultHandle) {
                    if (data && data.code && data.code != '200') {
                        // alert(data.msg);
                        $.toast.error(data.msg);
                        return;
                    }
                }
                if (typeof option.success == 'function')
                    option.success(data);

            };
            config.error = function (xhr, textStatus) {
                if(loading) loading.hide()
                $.toast.error('服务正忙,请稍后再试');
                console.log(arguments);
                if (typeof option.error == 'function')
                    option.error(xhr, textStatus);
            };
            config.complete = function (xhr, textStatus) {
                if (typeof option.complete == 'function')
                    option.complete(xhr, textStatus);
            };
            if(config.loading){
                var loading = weui.loading('loading', {
                    content: '请稍后...'
                });
            }

            //真正发起请求
            $.ajax(config);
        },
        form: {
            autoFillData: function (container, data) {
                if (!data)
                    return;

                $(':input:not(:radio,:checkbox)', $(container)).each(function (i, o) {
                    var name = this.name;
                    if (name && data[name] + '') {
                        $(this).val(data[name]);
                    }
                });

                $(':radio', $(container)).each(function (i, o) {
                    var name = this.name;
                    this.checked = false;
                    if (name && (data[name] + '') && this.value == data[name]) {
                        this.checked = true;
                    }
                });
                $(':checkbox', $(container)).each(function (i, o) {
                    var name = this.name;
                    this.checked = false;
                    if (name && data[name] && (data[name] + '').split(',').indexOf(this.value) > -1) {
                        this.checked = true;
                    }
                });
            },
            initSelectOption: function (option) {
                if (!option.data) {
                    $(option.container).html('');
                    return;
                }
                option = $.extend({"value": "value", "name": "name", "nullDesc": "--请选择--"}, option);
                var html = '';
                if (option.needNull) html += '<option value="">' + option.nullDesc + '</option>';
                $.each(option.data, function (i, o) {
                    html += '<option value="' + o[option.value] + '" ' + (option.defaultVal && o[option.value] == option.defaultVal ? "selected" : "") + '>' + o[option.name] + '</option>';
                });
                if (option.append) {
                    $(option.container).append(html);
                } else {
                    $(option.container).html(html);
                }
                if (option.callback && typeof option.callback == 'function') {
                    option.callback();
                }
            },
            //给指定的select容器通过url返回的数组初始化数据
            initSelect: function (option) {
                option = $.extend({needNull: true, queryParam: {}, append: false}, option);
                $.json({
                    url: option.url,
                    data: option.queryParam,
                    success: function (data) {
                        option.data = data.data;
                        this.initSelectOption(option);
                    }
                });
            },
            validate:function (options) {
                if(!options.form){
                    throw 'form选择器没有配置';
                }
                let defaults = {
                    submitHandler: function () {
                        console.log('还没有设置表单提交');
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
                }
                options = $.extend(defaults,options);

                $(options.form).validate(options);

            }
        },
        str: {
            // 找出queryString的某个值
            getQueryString: function (name) {
                var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)'); // 匹配目标参数
                var result = window.location.search.substr(1).match(reg); // 对querystring匹配目标参数
                if (result != null) {
                    return decodeURIComponent(result[2]);
                } else {
                    return null;
                }
            },
            // 把queryString组装成对象返回
            searchParams: function (url) {
                var ret = {};
                var match;
                var plus = /\+/g;
                var reg = /([^\?&=]+)=([^&]*)/g;
                var decode = function (s) {
                    return decodeURIComponent(s.replace(plus, " "));
                };
                while (match = reg.exec(url)) ret[decode(match[1])] = decode(match[2]);
                return ret;
            },
            guid: function () {
                return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                    var r = Math.random() * 16 | 0,
                        v = c == 'x' ? r : (r & 0x3 | 0x8);
                    return v.toString(16);
                });
            },
            hidePhone:function (phone) {
                if($.str.isPhone(phone)){
                    return phone.substring(0,3)+'****'+phone.substr(7);
                }
            },
            isPhone:function (phone) {
                return /^1[3456789]\d{9}$/.test(phone);
            },
            getTextClass(str) {
                str = str || '';
                return str.indexOf('+') == 0 ? 'up_sub' : 'down_sub';
            }
        },
        modal: {
            createImgModal: function () {
                let html =
                    '<div class="modal" id="imgBigModal" style="animation-duration: 200ms">' +
                    '<div class="modal-dialog" style="width: auto;background: none">' +
                    '<div class="modal-content" style="background: none;border: none;box-shadow: none;">' +
                    '<div class="modal-body p-0" style="display: flex;justify-content: center;align-items: center;">' +
                    '<img style="width: auto;"/>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                $('body').append(html);
            },
            showLoginModal: function () {
                $.modal.hideModal();
                $('#loginModal').modalShow('zoomIn', {vertical: true});
                if (!/\/login$/.test(location.href)) {
                    $('#loginForm [name=forwardUrl]').val(location.href);
                }
            },
            showPayModal: function () {
                $.modal.hideModal();
                $('#payModal').modalShow('zoomIn', {vertical: true});
            },
            showPhoneModal: function () {
                $('#phoneModal').removeClass('step-2').addClass('step-1');
                $('#phoneModal input').val('');
                $.modal.hideModal();
                $('#phoneModal').modalShow('zoomIn', {vertical: true});
            },
            hideModal: function (modal) {
                modal = modal || '.modal';
                $(modal).modal('hide');
            }
        },
        toast: {
            error: function (msg) {
                Toast.fire({
                    type: 'error',
                    title: '&nbsp;&nbsp;' + msg
                })
            },
            success: function (msg) {
                Toast.fire({
                    type: 'success',
                    title: '&nbsp;&nbsp;' + msg
                })
            }
        },
        bussiness: {
            createOrder: function () {
                var priceId = $('#payForm [name=priceId]').val();
                if(!priceId){
                    return false;
                }
                $.json({
                    url: ctx + '/order/create',
                    data: {priceId: priceId},
                    success: function (data) {
                        if (data.code == '200') {
                            Swal.fire({
                                type: 'success',
                                title: '购买成功',
                                showConfirmButton: false,
                                timer: 1500
                            })
                            $.modal.hideModal('#payModal');
                        }

                    }
                })
            },
            checkPhoneByStep1:function() {
                let code = $('#phoneModal.step-1 .step-1 [name=code]').val();
                if(code && code == '1234'){
                    $('#phoneModal.step-1').removeClass('step-1').addClass('step-2');
                }else {
                    $.toast.error('验证码不正确');
                }
            },
            bindPhone:function(){
                let code = $('#phoneModal.step-2 .step-2 [name=code]').val();
                let phone = $('#phoneModal.step-2 .step-2 [name=phone]').val();
                if(!code || !phone){
                    $.toast.error('信息不完善');
                }else if(!$.str.isPhone(phone)){
                    $.toast.error('手机号码不正确');
                }else{
                    $.json({
                        url:ctx+'/user/bindPhone',
                        data:{code:code,phone:phone,"remember-me":"true"},
                        success:function(data){
                            $.toast.success('修改成功');
                            $.modal.hideModal('#phoneModal');
                            $('.phone-number').text($.str.hidePhone(phone)).val($.str.hidePhone(phone));
                        }
                    })
                }
            },
            // 个人中心，保存用户信息
            saveUserInfo:function() {
                let nick = $('#userForm [name=nick]').val(),
                    email = $('#userForm [name=email]').val();

                $.json({
                    url : ctx + '/user/save',
                    data:{nick:nick,email:email},
                    success:function (data) {
                        $.toast.success('修改成功');
                        $.bussiness.refreshUserNick(nick);
                    }
                })
            },
            refreshUserNick:function (nick) {
                $('.userNick').text(nick).val(nick);
            },
            refreshUserAvatar:function (avatar) {
                $('img.userAvatar').attr('src', avatar);
            }
        },
        common:{
            initDatePicker:function(){
                //date-picker
                var startDate1=new Date(new Date().setDate(1));
                var endDate1=new Date(new Date(new Date().setMonth(new Date().getMonth()+1)).setDate(0));
                //定义接收上个月的第一天和最后一天
                var startDate2=new Date(new Date(new Date().setMonth(new Date().getMonth()-1)).setDate(1));
                var endDate2=new Date(new Date().setDate(0));

                laydate.render({
                    elem: '#date-picker',
                    eventElem: ".date-picker-icon",
                    trigger: 'click',
                    type: 'date',
                    range: '~',
                    format: 'yyyy-MM-dd',
                    max:new Date().format('yyyy-MM-dd'),//可选最大日期
                    extrabtns: [
                        {id:'today', text:'今天', range:[new Date(), new Date()]},
                        {id:'lastday-7', text:'过去7天', range:[new Date(new Date().setDate(new Date().getDate()-7)),
                                new Date(new Date().setDate(new Date().getDate()-1))]},
                        {id:'lastday-30', text:'过去30天', range:[new Date(new Date().setDate(new Date().getDate()-30)),
                                new Date(new Date().setDate(new Date().getDate()-1))]},
                        {id:'yesterday', text:'昨天', range:[new Date(new Date().setDate(new Date().getDate()-1)),
                                new Date(new Date().setDate(new Date().getDate()-1))]},
                        {id:'thismonth', text:'本月', range:[startDate1,endDate1]},
                        {id:'lastmonth', text:'上个月', range:[startDate2,endDate2]}
                    ]
                });
            }
        }
    })

    $.fn.extend({
        // bootstrapTable
        table: function (options) {
            let defaults = {
                method: 'post',
                contentType: 'application/x-www-form-urlencoded',// 默认是post情况下是application/json
                classes: 'table table-no-bordered',// 设置table的class 默认是table table-hover
                cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
                striped: true,  //表格显示条纹，默认为false
                pagination: true, // 在表格底部显示分页组件，默认false
                pageList: [10, 20, 30, 40, 50], // 设置页面可以显示的数据条数
                pageSize: 10, // 页面数据条数
                pageNumber: 1, // 首页页码
                sidePagination: 'server', // 设置为服务器端分页
                // toolbar: '#toolbar',
                showColumns: false,
                showRefresh: false,
                showToggle: false,
                iconSize: 'default',
                queryParams: function (params) {
                    var currentParams = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                        limit: params.limit,   //页面大小
                        offset: params.offset,  //页码
                    };
                    if (this.form) {
                        $.extend(currentParams, $(this.form).serializeObject());
                    }
                    return currentParams;
                },
                singleSelect: true,

            }
            var newOptions = $.extend(defaults, options);
            $(this).bootstrapTable(newOptions);

        },

    })
})(jQuery);


$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(),    //day
        "H+": this.getHours(),   //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}


function doLogin() {
    let phone = $('#loginForm [name=phone]').val(),
        smsCode = $('#loginForm [name=smsCode]').val();

    if (!phone || !$.str.isPhone(phone)) {
        $.toast.error('请填写正确的手机号码')
        $('#loginForm [name=phone]').focus();
        return;
    }
    if (!smsCode || !/^\d{4}$/.test(smsCode)) {
        $.toast.error('请填写正确的验证码')
        $('#loginForm [name=smsCode]').focus();
        return;
    }

    $.json({
        url: ctx + 'login/smsLogin',
        type: 'post',
        data: $('#loginForm').serialize(),
        success: function (data) {
            window.location.href = $('#loginForm [name=forwardUrl]').val() || ctx;
        }
    })
}

// 点击图片放大功能
$(function () {
    $('img.can-show').click(function () {
        let src = this.src;
        if (src) {
            let exists = $('#imgBigModal').length == 1;
            exists || $.modal.createImgModal();
            $('#imgBigModal img').attr('src', src);
            $('#imgBigModal').modalShow('zoomIn', {vertical: true});
            setTimeout(function () {
                let img = $('#imgBigModal img')[0];
                $('#imgBigModal .modal-dialog').css(
                    {
                        'margin-top': Math.max(0, ($(window).height() - img.offsetHeight) / 2),
                        'width': img.offsetWidth
                    }
                );
            }, 50)

        }
    })

    //暂时
    $('.get_code').click(function () {
        let phone = $('#loginForm [name=phone]').val();
        if (!$.str.isPhone(phone)) {
            $.toast.error('手机号码不正确');
            return;
        }
        $.toast.success('验证码为：1234');
    })
})


