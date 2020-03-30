(function ($) {
    $.extend({
        json: function (option, defaultHandle = true) {
            let config = {
                dataType: 'json',
                type: 'get'
            };
            $.extend(config, option);
            config.url = config.url.replace(/(https?:\/\/.*)\/\/(.*)/,'$1\/$2');

            config.success = function (data, textStatus, xhr) {
                loading.hide()
                if (data && data.code == '401') {
                    console.log(data.msg);
                    $.modal.showLoginModal();
                    $('#loginForm [name=forwardUrl]').val(location.href);
                    return;
                }

                if (defaultHandle) {
                    if (data && data.code && data.code != '200') {
                        // alert(data.msg);
                        Toast.fire({
                            type: 'error',
                            title: '&nbsp;&nbsp;' + data.msg
                        })
                        return;
                    }
                }
                if (typeof option.success == 'function')
                    option.success(data);

            };
            config.error = function (xhr, textStatus) {
                loading.hide()
                if (window.toastr)
                    toastr.error('服务正忙,请稍后再试');
                else
                    weui.topTips('服务正忙,请稍后再试');
                console.log(arguments);
                if (typeof option.error == 'function')
                    option.error(xhr, textStatus);
            };
            config.complete = function (xhr, textStatus) {
                if (typeof option.complete == 'function')
                    option.complete(xhr, textStatus);
            };

            let loading = weui.loading('loading', {
                content: '请稍后...'
            });

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
                $('#loginModal').modalShow('zoomIn', {vertical: true});
                if (!/\/login$/.test(location.href)) {
                    $('#loginForm [name=forwardUrl]').val(location.href);
                }
            },
            showPayModal: function () {
                $('#payModal').modalShow('zoomIn', {vertical: true});
            }
        }
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

    if (!phone || !/^\d{11}$/.test(phone)) {
        Toast.fire({
            type: 'error',
            title: '&nbsp;&nbsp;请填写正确的手机号码'
        })
        $('#loginForm [name=phone]').focus();
        return;
    }
    if (!smsCode || !/^\d{4}$/.test(smsCode)) {
        Toast.fire({
            type: 'error',
            title: '&nbsp;&nbsp;请填写正确的验证码'
        })
        $('#loginForm [name=smsCode]').focus();
        return;
    }

    $.json({
        url: ctx + 'login/smsLogin',
        type: 'post',
        data: $('#loginForm').serialize(),
        success: function (data) {
            let forwardUrl = $('#loginForm [name=forwardUrl]').val() || ctx;
            window.location.href = forwardUrl;
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
        if (!/^\d{11}$/.test(phone)) {
            Toast.fire({
                type: 'error',
                title: '&nbsp;&nbsp;' + '手机号码不正确'
            })
            return;
        }
        Toast.fire({
            type: 'success',
            title: '&nbsp;&nbsp;' + '验证码为：1234'
        })
    })
})


