//animate.css动画触动一次方法
const animationIn = ["bounceIn","bounceInDown","bounceInLeft","bounceInRight","bounceInUp",
    "fadeIn", "fadeInDown", "fadeInLeft", "fadeInRight", "fadeOutUp",
    "fadeInDownBig", "fadeInLeftBig", "fadeOutRightBig", "fadeOutUpBig","flipInX","flipInY",
    "lightSpeedIn","rotateIn","rotateInDownLeft","rotateInDownRight","rotateInUpLeft","rotateInUpRight",
    "zoomIn","zoomInDown","zoomInLeft","zoomInRight","zoomInUp","slideInDown","slideInLeft",
    "slideInRight", "slideInUp","rollIn"];
const animationOut = ["bounceOut", "bounceOutDown", "bounceOutLeft", "bounceOutRight", "bounceOutUp",
    "fadeOut", "fadeOutDown", "fadeOutLeft", "fadeOutRight", "fadeOutUp",
    "fadeOutDownBig", "fadeOutLeftBig", "fadeOutRightBig", "fadeOutUpBig", "flipOutX", "flipOutY",
    "lightSpeedOut", "rotateOut", "rotateOutDownLeft", "rotateOutDownRight", "rotateOutUpLeft", "rotateOutUpRight",
    "zoomOut", "zoomOutDown", "zoomOutLeft", "zoomOutRight", "zoomOutUp",
    "zoomOut", "zoomOutDown", "zoomOutLeft", "zoomOutRight", "zoomOutUp", "slideOutDown", "slideOutLeft",
    "slideOutRight", "slideOutUp", "rollOut"];

$.fn.extend({
    animateCss: function (animationName) {
        var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
        this.addClass('animated ' + animationName).one(animationEnd, function() {
            //执行完动画后移除class
            $(this).removeClass('animated ' + animationName);
        });
    },
    modalShow:function (animateName,modalOption={}) {
        var _that = this;
        if(!animateName || animationIn.indexOf(animateName)==-1){
            var intRandom =  Math.floor(Math.random()*animationIn.length);
            animateName = animationIn[intRandom];
        }
        modalOption = $.extend({show:true,vertical:false},modalOption);
        if(modalOption.vertical){
            $(this).on('show.bs.modal', function() {
                var $this = $(this);
                var $modal_dialog = $this.find('.modal-dialog');
                $this.css('display', 'block');
                $modal_dialog.css({'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2)});
            })
        }
        $(this).modal(modalOption)
        $(this).show().animateCss(animateName);
        $(this).children().click(function(e){e.stopPropagation()});
        $('.modal-header .close,[data-dismiss=modal]',this).click(function () {
            $(_that).modal('hide')
        })
    },
    modalHide:function (animateName) {
        if (!animateName || animationOut.indexOf(animateName) == -1) {
            var intRandom = Math.floor(Math.random() * animationOut.length);
            animateName = animationOut[intRandom];
        }
        $(this).children().click(function (e) {
            e.stopPropagation()
        });
        $(this).animateCss(animateName);
        $(this).delay(900).hide(1, function () {
            $(this).removeClass('animated ' + animateName);
        });
    }
});
// /**
//  * 显示模态框方法
//  * @param targetModel 模态框选择器，jquery选择器
//  * @param animateName 弹出动作
//  * @ callback 回调方法
//  */
// // function modalShow(targetModel, animateName){
// //     var animationIn = ["bounceIn","bounceInDown","bounceInLeft","bounceInRight","bounceInUp",
// //         "fadeIn", "fadeInDown", "fadeInLeft", "fadeInRight", "fadeOutUp",
// //         "fadeInDownBig", "fadeInLeftBig", "fadeOutRightBig", "fadeOutUpBig","flipInX","flipInY",
// //         "lightSpeedIn","rotateIn","rotateInDownLeft","rotateInDownRight","rotateInUpLeft","rotateInUpRight",
// //         "zoomIn","zoomInDown","zoomInLeft","zoomInRight","zoomInUp","slideInDown","slideInLeft",
// //         "slideInRight", "slideInUp","rollIn"];
// //     if(!animateName || animationIn.indexOf(animateName)==-1){
// //         console.log(animationIn.length);
// //         var intRandom =  Math.floor(Math.random()*animationIn.length);
// //         animateName = animationIn[intRandom];
// //     }
// //     console.log(targetModel + " " + animateName);
// //     $(targetModel).modal('show')
// //     $(targetModel).show().animateCss(animateName);
// //     $(targetModel).children().click(function(e){e.stopPropagation()});
// // }
// // /**
// //  * 隐藏模态框方法
// //  * @param targetModel 模态框选择器，jquery选择器
// //  * @param animateName 隐藏动作
// //  * @ callback 回调方法
// //  */
// // function modalHide(targetModel, animateName, callback){
// //     var animationOut = ["bounceOut","bounceOutDown","bounceOutLeft","bounceOutRight","bounceOutUp",
// //         "fadeOut", "fadeOutDown", "fadeOutLeft", "fadeOutRight", "fadeOutUp",
// //         "fadeOutDownBig", "fadeOutLeftBig", "fadeOutRightBig", "fadeOutUpBig","flipOutX","flipOutY",
// //         "lightSpeedOut","rotateOut","rotateOutDownLeft","rotateOutDownRight","rotateOutUpLeft","rotateOutUpRight",
// //         "zoomOut","zoomOutDown","zoomOutLeft","zoomOutRight","zoomOutUp",
// //         "zoomOut","zoomOutDown","zoomOutLeft","zoomOutRight","zoomOutUp","slideOutDown","slideOutLeft",
// //         "slideOutRight", "slideOutUp","rollOut"];
// //     if(!animateName || animationOut.indexOf(animateName)==-1){
// //         console.log(animationOut.length);
// //         var intRandom =  Math.floor(Math.random()*animationOut.length);
// //         animateName = animationOut[intRandom];
// //     }
// //     $(targetModel).children().click(function (e) {
// //         e.stopPropagation()
// //     });
// //     $(targetModel).animateCss(animateName);
// //     $(targetModel).delay(900).hide(1,function(){
// //         $(this).removeClass('animated ' + animateName);
// //     });
// //     //callback.apply(this);
// // }
