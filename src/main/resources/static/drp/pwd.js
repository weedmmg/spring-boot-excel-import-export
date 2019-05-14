layui.use(['form','layer','element','laydate','laytpl'], function () {
     var laydate = layui.laydate,laytpl = layui.laytpl ,form = layui.form;


    form.on('submit(lsave)', function(data){
        submitForm(data);
       return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
     });


});

function submitForm(data){
  $.ajax({
     url: 'user/pwd',
     type: 'post',
     data: {
        password:$("#l_oldPassword").val(),
        newPassword:$("#l_newPassword").val(),
        newPassword2:$("#l_password").val()
     },
     dataType: "json",
     success: function (data) {
         var layerTips = parent.layer === undefined ? layui.layer : parent.layer;
         if(data.rel){
            layerTips.msg('修改成功');
         }else{
           layerTips.msg(data.msg);
         }
     }
   });
}


function setLabelValues(data) {
    for (var p in data) {
        $("#" + p + "").html(data[p]);
    }
}

function setInputValues(data) {
    for (var p in data) {
        $("#" + p + "").val(data[p]);
    }
}