$(function () {
    //头像上传
    $('#avatarUpload').change(function () {
        let file = this.files[0];
        if (file && file.name) {
            if (!/(jpg|jpeg|png|gif)$/.test(file.name)) {
                $.toast.error('格式错误')
                return false;
            }
            let formData = new FormData();
            formData.append('file', file);
            $.json({
                url: ctx + 'user/avatar/upload',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data.code == '200') {
                        let avatar = ctx.replace(/\/$/, '') + data.data;
                        $.bussiness.refreshUserAvatar(avatar);
                    }
                }
            });
        }
        this.value = "";
    })

    //校验form
    $('#userForm').validate({
        //校验表单成功后的回调
        submitHandler: function () {
            $.bussiness.saveUserInfo();
        },
        rules: {
            nick: {
                required: true,
            },
            email:{
                email:true
            }
        },
        messages: {
            nick: {
                required: "昵称不能为空",
            },
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
})
