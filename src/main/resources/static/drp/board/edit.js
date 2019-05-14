layui.config({
  base: ctxPath //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
  murl: 'url' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['element','murl','form','upload','layedit'], function () {
     var element = layui.element,
     					form = layui.form,
     					upload = layui.upload,
     					layedit = layui.layedit,
         murl=layui.murl,id = murl.get("id");

    $.ajax({
        url: ctxPath+"board/"+id,
        type: 'get',
        dataType: "json",
        success: function (data) {
            if(data.rel){
                setLabelValues(data.result);
                setInputValues(data.result);
            }else{
                layerTips.msg(data.msg);
            }
        }
    });

     form.on('submit(save)', function(data){
      var layerTips = parent.layer === undefined ? layui.layer : parent.layer;
      //layerTips.msg("url:"+$("#ImgPr").attr("src"));

        submitForm(data);
       return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
     });

     form.on('submit(cancel)', function(data){
        layer.confirm('确定取消吗？', null, function (index) {

             window.setTimeout(function () {
                                    parent.tab.deleteTab(parent.tab.getCurrentTabId(),true);
                                },1000);
          });

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
      });
});

function submitForm(data){
  $.ajax({
     url: ctxPath+'board/process',
     type: 'put',
      data: data.field,
      dataType: "json",
     success: function (data) {
         var layerTips = parent.layer === undefined ? layui.layer : parent.layer;
         if(data.rel){
            layerTips.msg('处理成功');
              window.setTimeout(function () {
                                     parent.tab.deleteTab(parent.tab.getCurrentTabId(),true);
                                 },1000);
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



