layui.config({
  base: '/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
  murl: 'url' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['form','murl','layer','element','laydate','laytpl'], function () {
  if($("#typeId").val()==4){
    initData(1,"chart-hy","注册会员","平台注册会员数量");
      initData(2,"chart-rz","认证会员","平台注册会员数量");
      initData(3,"chart-pay","认证费","平台认证总金额");
  }else{
     //显示推广码
     initCode();
  }

});
function initCode(){
$.ajax({
   url: '/user/code',
   type: 'get',
   dataType: "json",
   success: function (data) {
       if(data.rel){
           $("#crtName").html(data.result.crtName);
           $("#qrCode").attr("src",data.result.qrCode);
       }
   }
 });
}

function initData(type,id,typeName,title){
$.ajax({
   url: '/api_platform/count?type='+type,
   type: 'get',
   dataType: "json",
   success: function (data) {
       if(data.rel){
         initPie(data.result[0],data.result[1],id,typeName,title);
       }
   }
 });
}
function initPie(names,datas,id,typeName,title){
   var randomScalingFactor = function() {
  			return Math.round(Math.random() * 100);
  		};

  		var config = {
  			type: 'pie',
  			data: {
  				datasets: [{
  					data: datas,
  					backgroundColor: [
  						window.chartColors.red,
  						window.chartColors.blue
  					],
  					label: typeName
  				}
  				],
  				labels: names
  			},
        options: {
  				responsive: true,
  				legend: {
  					position: 'top',
  				},
  				title: {
  					display: true,
  					text: title
  				},
  				animation: {
  					animateScale: true,
  					animateRotate: true
  				}
  			}


  		};

  			var ctx = document.getElementById(id).getContext('2d');
  			window.myPie = new Chart(ctx, config);

}

