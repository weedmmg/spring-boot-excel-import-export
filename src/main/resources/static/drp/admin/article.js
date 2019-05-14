
layui.config({
  base: '/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
  murl: 'url'
});

var apply = {
    baseUrl: "/api_content",
    entity: "user",
    tableId: "userTable",
    toolbarId: "toolbar",
    unique: "id",
    order: "desc",
    currentItem: {}
};
apply.columns = function () {
    return [{
                    checkbox: true
    },{
          field: 'image',
          title: '缩略图',
          align: 'center',
         formatter:function (value,row,index) {
             if(value==undefined){
               return '<img  src="/img/admin-login-bg.jpg" width="100" class="img-rounded" >';
             }
             return '<img  src='+value+' width="100" class="img-rounded" >';
         }
    },{

        field: 'title',
        title: '标题'

    }, {
      field: 'crtTime',
      title: '创建时间'
   }];
};
apply.queryParams = function (params) {
    if (!params)
        return {
            beginTime: $("#beginTime").val(),
            name: $("#name").val(),
            phone: $("#phone").val(),
            endTime:$("#endTime").val()
        };
    var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit, //页面大小
        offset: (params.offset / params.limit)+1, //页码
        beginTime: $("#beginTime").val(),
        name: $("#name").val(),
        phone: $("#phone").val(),
        endTime:$("#endTime").val()
    };
    return temp;
};

apply.init = function () {

    apply.table = $('#' + apply.tableId).bootstrapTable({
        url: apply.baseUrl+"/list" , //请求后台的URL（*）
        method: 'get', //请求方式（*）
        toolbar: '#' + apply.toolbarId, //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: false, //是否启用排序
        sortOrder: apply.order, //排序方式
        queryParams: apply.queryParams,//传递参数（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: false, //是否显示所有的列
        showRefresh: true, //是否显示刷新按钮
        minimumCountColumns: 2, //最少允许的列数
        clickToSelect: true, //是否启用点击选中行
        uniqueId: apply.unique, //每一行的唯一标识，一般为主键列
        showToggle: true, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        columns: apply.columns(),

    });
};
apply.select = function (layerTips) {
    var rows = apply.table.bootstrapTable('getSelections');
    if (rows.length == 1) {
        apply.currentItem = rows[0];
        return true;
    } else {
        layerTips.msg("请选中一行");
        return false;
    }
};

layui.use(['form', 'layedit', 'laydate'], function () {
    apply.init();

    var editIndex;
    var layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
        layer = layui.layer, //获取当前窗口的layer对象
        layedit = layui.layedit,
        laydate = layui.laydate;

     laydate.render({
           elem: '#beginTime' //指定元素
         });
     laydate.render({
        elem: '#endTime' //指定元素
     });
    var addBoxIndex = -1;
    //初始化页面上面的按钮事件
    $('#btn_query').on('click', function () {
        apply.table.bootstrapTable('refresh', apply.queryParams());
    });

    $('#btn_new').on('click', function () {
        parent.tab.tabAdd({
               title: '添加新资讯',
               href: '/admin/articleAdd'
         });
     });

      $('#btn_view').on('click', function () {
            if (apply.select(layer)) {
               var id = apply.currentItem.id;
               parent.tab.tabAdd({
                      title: '查看资讯',
                      href: '/admin/articleView?id='+id
                });
              }
       });

      $('#btn_del').on('click', function () {
               if (apply.select(layer)) {
                     var id = apply.currentItem.id;

                           $.ajax({
                     					url: apply.baseUrl+"/" + id,
                     					type: 'delete',
                     					dataType: "json",
                     					success: function(data) {
                     						if(data.rel) {
                     						 layerTips.msg('删除成功');
                     						 apply.table.bootstrapTable('refresh', apply.queryParams());
                     						}
                     					}
                     				});


                   }
              });
});