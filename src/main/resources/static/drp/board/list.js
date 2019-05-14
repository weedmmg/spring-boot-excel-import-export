
layui.config({
  base: '/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    murl: 'url',
    excel: 'excel.min'
});

var apply = {
    baseUrl: ctxPath+"board",
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
        field: 'id',
        title: '序号',
        class: 'text-cxf'
    },{
        field: 'telphone',
        title: '手机号码',
        class: 'text-cxf'
    },{
        field: 'statusDesc',
        title: '状态'
     },{
        field: 'message',
        title: '咨询内容',
        class: 'text-cxf'
    }, {
      field: 'crtTime',
      title: '创建时间'
   }];
};
apply.queryParams = function (params) {
    if (!params)
        return {
            orderStr: 'desc',
            telphone: $("#telphone").val(),
            status: $("#status").val()
        };
    var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit, //页面大小
        orderStr: 'desc',
        offset: (params.offset / params.limit)+1, //页码
        telphone: $("#telphone").val(),
        status: $("#status").val()
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

layui.use(['form', 'layedit','upload', 'excel','laydate'], function () {
    apply.init();

    var editIndex;
    var layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
        layer = layui.layer, //获取当前窗口的layer对象
        layedit = layui.layedit,upload=layui.upload, excel = layui.excel,
        laydate = layui.laydate;

    var uploadInst = upload.render({
        elem: '#uploadFile' //缁戝畾鍏冪礌
        ,accept:'file'
        ,exts:'xls|xlsx'
        ,url: '/board/upload' //涓婁紶鎺ュ彛
        ,type: 'post'
        ,done: function(res){
            layerTips.msg(res.msg);
            apply.table.bootstrapTable('refresh', apply.queryParams());
        }
        ,error: function(){
            //璇锋眰寮傚父鍥炶皟
            layerTips.msg("上传失败");
        }
    });
    var addBoxIndex = -1;
    //初始化页面上面的按钮事件
    $('#btn_query').on('click', function () {
        apply.table.bootstrapTable('refresh', apply.queryParams());
    });

    $('#btn_export').on('click', function () {
             exportApiDemo();


       });



});



function edit(id){
    parent.tab.tabAdd({
        title: '咨询处理',
        href: ctxPath+'boardv/edit?id='+id
    });
}

function exportApiDemo() {
    var opthons=  apply.table.bootstrapTable('getOptions');
    layui.use(['jquery', 'excel', 'layer'], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var excel = layui.excel;

        // 模拟从后端接口读取需要导出的数据
        $.ajax({
            url:apply.baseUrl+"/list"
                ,type: 'get'
            ,data:apply.queryParams({offset:opthons.pageNumber-1,limit:opthons.pageSize})
            ,dataType: 'json'
            ,success: function(res) {
                if(res.total){
                    var data = res.rows;
                    // 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
                    data = excel.filterExportData(data, {
                        id: 'id'
                        ,telphone: 'telphone'
                        ,message: 'message'

                    });
                    // 重点2！！！一般都需要加一个表头，表头的键名顺序需要与最终导出的数据一致
                    data.unshift({ id: "编号", telphone: "手机号码", message: '咨询内容' });

                    var timestart = Date.now();
                    excel.exportExcel({
                        sheet1: data
                    }, '留言咨询.xlsx', 'xlsx');
                    var timeend = Date.now();

                    var spent = (timeend - timestart) / 1000;
                    layer.alert(' 导出成功');
                }

            }
            ,error: function() {
                layer.alert('获取数据失败，请检查是否部署在本地服务器环境下');
            }
        });
    });
}