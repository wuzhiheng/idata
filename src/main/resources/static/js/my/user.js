$(function () {
    //头像上传
    $('#avatarUpload').change(function () {
        let that = this;
        let file = this.files[0];
        if (file && file.name) {
            if (!/jpg|jpeg|png|gif/.test(file.name)) {
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
                        let src = ctx.replace(/\/$/, '') + data.data
                        $('.userAvatar').attr('src', src);
                    }
                }
            });
        }
        that.value = "";
    })
})
