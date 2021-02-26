<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
    <title>就业流向图</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" >
    <link rel="stylesheet" href="../../css/font.css">
    <link rel="stylesheet" href="../../css/xadmin.css">
    <link rel="stylesheet" href="../../css/echartsHtmlbase.css">
</head>
<script src="../../lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="../../js/xadmin.js"></script>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/dark.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:void(0);">就业分析</a>
        <a><cite>就业流向图</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
    </a>
</div>
<form class="layui-form nav-select">
    <div class="layui-form-item">
        <label class="layui-form-label">入学年份:</label>
        <div class="layui-input-inline layui-show-xs-block">
            <select lay-filter="year" id="YEAR">
                <option value="2019">2019</option>
                <option value="2018">2018</option>
                <option value="2017">2017</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item nav-selectXY2">
        <label class="layui-form-label">选择学院:</label>
        <div class="layui-input-inline layui-show-xs-block">
            <select name="college" lay-filter="college" id="COLLEGE">
                <option value="计算机学院">计算机学院</option>
            </select>
        </div>
    </div>
</form>
<script>
    layui.use('form',function () {
        var form = layui.form;
        form.on('select(year)',function (data) {
            if(data.value==2019)
                year2019()
            else if (data.value==2018)
                year2018()
            else if (data.value==2017)
                year2017()
            else if (data.value==2015)
                year2015()
        })
        form.on('select(college)',function (data) {
            if(data.value==计算机学院)
                year2018()
        })

    })
</script>
<div id="container" class="echarts-container"></div>
<script type="text/javascript">
    $("#YEAR").val("${YEAR}")
    layui.use('form',function () {
        var form = layui.form;
        form.on('select(year)',function (data) {
            window.location.href="test?ActionType=chart2&YEAR="+data.value;
        })
    })
    /*var student = {name};
    var len = student.length;
    var countMap = {countMap};*/
    var jobFlowList = ${jobFlowList};
    var data = {
        hb_hb : 0,
        hb_hn : 0,
        hb_hd : 0,
        hb_xn : 0,
        hb_xb : 0,
        hb_db : 0,

        hn_hb : 0,
        hn_hn : 0,
        hn_hd : 0,
        hn_xn : 0,
        hn_xb : 0,
        hn_db : 0,

        hd_hb : 0,
        hd_hn : 0,
        hd_hd : 0,
        hd_xn : 0,
        hd_xb : 0,
        hd_db : 0,

        xn_hb : 0,
        xn_hn : 0,
        xn_hd : 0,
        xn_xn : 0,
        xn_xb : 0,
        xn_db : 0,

        xb_hb : 0,
        xb_hn : 0,
        xb_hd : 0,
        xb_xn : 0,
        xb_xb : 0,
        xb_db : 0,

        db_hb : 0,
        db_hn : 0,
        db_hd : 0,
        db_xn : 0,
        db_xb : 0,
        db_db : 0,
    }
    /*
    * 华北 华南 华东 西南 西北 东北
    * 华北 华南 华东 西南 西北 东北
    * */
    jobFlowList.forEach(function(val,index){
        if (val.come==='华北' && val.goto==="华北"){
            data.hb_hb ++;
        }else if (val.come==='华北' && val.goto==="华南"){
            data.hb_hn ++;
        }else if (val.come==='华北' && val.goto==="华东"){
            data.hb_hd ++;
        }else if (val.come==='华北' && val.goto==="西南"){
            data.hb_xn ++;
        }else if (val.come==='华北' && val.goto==="西北"){
            data.hb_xb ++;
        }else if (val.come==='华北' && val.goto==="东北"){
            data.hb_db ++;
        }

        else if (val.come==='华南' && val.goto==="华北"){
            data.hn_hb ++;
        }else if (val.come==='华南' && val.goto==="华南"){
            data.hn_hn ++;
        }else if (val.come==='华南' && val.goto==="华东"){
            data.hn_hd ++;
        }else if (val.come==='华南' && val.goto==="西南"){
            data.hn_xn ++;
        }else if (val.come==='华南' && val.goto==="西北"){
            data.hn_xb ++;
        }else if (val.come==='华南' && val.goto==="东北"){
            data.hn_db ++;
        }

        else if (val.come==='华东' && val.goto==="华北"){
            data.hd_hb ++;
        }else if (val.come==='华东' && val.goto==="华南"){
            data.hd_hn ++;
        }else if (val.come==='华东' && val.goto==="华东"){
            data.hd_hd ++;
        }else if (val.come==='华东' && val.goto==="西南"){
            data.hd_xn ++;
        }else if (val.come==='华东' && val.goto==="西北"){
            data.hd_xb ++;
        }else if (val.come==='华东' && val.goto==="东北"){
            data.hd_db ++;
        }

        else if (val.come==='西南' && val.goto==="华北"){
            data.xn_hb  ++;
        }else if (val.come==='西南' && val.goto==="华南"){
            data.xn_hn ++;
        }else if (val.come==='西南' && val.goto==="华东"){
            data.xn_hd ++;
        }else if (val.come==='西南' && val.goto==="西南"){
            data.xn_xn ++;
        }else if (val.come==='西南' && val.goto==="西北"){
            data.xn_xb ++;
        }else if (val.come==='西南' && val.goto==="东北"){
            data.xn_db ++;
        }

        else if (val.come==='西北' && val.goto==="华北"){
            data.xb_db ++;
        }else if (val.come==='西北' && val.goto==="华南"){
            data.xb_hn ++;
        }else if (val.come==='西北' && val.goto==="华东"){
            data.xb_hd ++;
        }else if (val.come==='西北' && val.goto==="西南"){
            data.xb_xn ++;
        }else if (val.come==='西北' && val.goto==="西北"){
            data.xb_xb ++;
        }else if (val.come==='西北' && val.goto==="东北"){
            data.xb_db ++;
        }

        else if (val.come==='东北' && val.goto==="华北"){
            data.db_db ++;
        }else if (val.come==='东北' && val.goto==="华南"){
            data.db_hn ++;
        }else if (val.come==='东北' && val.goto==="华东"){
            data.db_hd ++;
        }else if (val.come==='东北' && val.goto==="西南"){
            data.db_xn ++;
        }else if (val.come==='东北' && val.goto==="西北"){
            data.db_xb ++;
        }else if (val.come==='东北' && val.goto==="东北"){
            data.db_db ++;
        }
    })

    /*for (var i = 0;i < len;i++){

        var region = student[i].region;
        if (region==undefined){
            continue;
        }
        var workplace = student[i].FWMC1;
        if (region!='' && region.length>0){
            if (region==='华东' && workplace==="华东"){
                data.huaDong_huaDong ++;
            }else if (region==='华东' && workplace==="华西"){
                data.huaDong_huaXi ++;
            }else if (region==='华东' && workplace==="华南"){
                data.huaDong_huaNai ++;
            }else if (region==='华东' && workplace==="华北"){
                data.huaDong_huaBei ++;
            }else if (region==='华东' && workplace==="华中"){
                data.huaDong_huaZhong ++;
            }
            else if (region==='华西' && workplace==="华东"){
                data.huaXi_huaDong ++;
            }else if (region==='华西' && workplace==="华西"){
                data.huaXi_huaXi ++;
            }else if (region==='华西' && workplace==="华南"){
                data.huaXi_huaNai ++;
            }else if (region==='华西' && workplace==="华北"){
                data.huaXi_huaBei ++;
            }else if (region==='华西' && workplace==="华中"){
                data.huaXi_huaZhong ++;
            }

            else if (region==='华南' && workplace==="华东"){
                data.huaNai_huaDong ++;
            }else if (region==='华南' && workplace==="华西"){
                data.huaNai_huaXi ++;
            }else if (region==='华南' && workplace==="华南"){
                data.huaNai_huaNai ++;
            }else if (region==='华南' && workplace==="华北"){
                data.huaNai_huaNai ++;
            }else if (region==='华南' && workplace==="华中"){
                data.huaNai_huaZhong ++;
            }

            else if (region==='华北' && workplace==="华东"){
                data.huaBei_huaDong  ++;
            }else if (region==='华北' && workplace==="华西"){
                data.huaBei_huaXi ++;
            }else if (region==='华北' && workplace==="华南"){
                data.huaBei_huaNai ++;
            }else if (region==='华北' && workplace==="华北"){
                data.huaBei_huaBei ++;
            }else if (region==='华北' && workplace==="华中"){
                data.huaBei_huaZhong ++;
            }

            else if (region==='华中' && workplace==="华东"){
                data.huaZhong_huaDong ++;
            }else if (region==='华中' && workplace==="华西"){
                data.huaZhong_huaXi ++;
            }else if (region==='华中' && workplace==="华南"){
                data.huaZhong_huaNai ++;
            }else if (region==='华中' && workplace==="华北"){
                data.huaZhong_huaBei ++;
            }else if (region==='华中' && workplace==="华中"){
                data.huaZhong_huaZhong ++;
            }
        }
    }
    var count = function (name) {
        return data[name];
    }*/


    var dom = document.getElementById("container");
    var myChart = echarts.init(dom,'dark');
    var app = {};
    option = null;
    option = {
        title: {
            text:"${YEAR}年就业流向图",
        },
        tooltip: {
            trigger: 'item',
            triggerOn: 'mousemove'
        },
        grid: {
            top:'10%',
            left: '5%',
            right: '6%',
            bottom: '7%',
            containLabel: true
        },
        series: {
            type: 'sankey',
            layout:'none',
            focusNodeAdjacency: 'allEdges',
            data: [{
                name: '华北(源)',
                itemStyle: {
                    color: '#c23531'
                }
            }, {
                name: '华南(源)',
                itemStyle: {
                    color: '#2f4554'
                }
            }, {
                name: '华东(源)',
                itemStyle: {
                    color: '#61a0a8'
                }
            },{
                name: '西南(源)',
                itemStyle: {
                    color: '#d48265'
                }
            },{
                name: '西北(源)',
                itemStyle: {
                    color: '#91c7ae'
                }
            },{
                name: '东北(源)',
                itemStyle: {
                    color: '#749f83'
                }
            },{
                name: '华北(业)',
                itemStyle: {
                    color: '#c23531'
                }
            }, {
                name: '华南(业)',
                itemStyle: {
                    color: '#2f4554'
                }
            }, {
                name: '华东(业)',
                itemStyle: {
                    color: '#61a0a8'
                }
            },{
                name: '西南(业)',
                itemStyle: {
                    color: '#d48265'
                }
            },{
                name: '西北(业)',
                itemStyle: {
                    color: '#91c7ae'
                }
            },{
                name: '东北(业)',
                itemStyle: {
                    color: '#749f83'
                }
            }],

            links:[{
                source: '华北(源)',
                target: '华北(业)',
                value: data.hd_hd
            },{
                source: '华北(源)',
                target: '华南(业)',
                value: data.hb_hn,
            }, {
                source: '华北(源)',
                target: '华东(业)',
                value: data.hb_hd,
            },{
                source: '华北(源)',
                target: '西南(业)',
                value: data.hb_xn,
            },{
                source: '华北(源)',
                target: '西北(业)',
                value: data.hb_xb,
            },{
                source: '华北(源)',
                target: '东北(业)',
                value: data.hb_db,
            },

            {
                source: '华南(源)',
                target: '华北(业)',
                value: data.hn_hd
            },{
                source: '华南(源)',
                target: '华南(业)',
                value: data.hn_hn,
            }, {
                source: '华南(源)',
                target: '华东(业)',
                value: data.hn_hd,
            },{
                source: '华南(源)',
                target: '西南(业)',
                value: data.hn_xn,
            },{
                source: '华南(源)',
                target: '西北(业)',
                value: data.hn_xb,
            },{
                source: '华南(源)',
                target: '东北(业)',
                value: data.hn_db,
            },

            {
                source: '华东(源)',
                target: '华北(业)',
                value: data.hd_hd
            },{
                source: '华东(源)',
                target: '华南(业)',
                value: data.hd_hn,
            }, {
                source: '华东(源)',
                target: '华东(业)',
                value: data.hd_hd,
            },{
                source: '华东(源)',
                target: '西南(业)',
                value: data.hd_xn,
            },{
                source: '华东(源)',
                target: '西北(业)',
                value: data.hd_xb,
            },{
                source: '华东(源)',
                target: '东北(业)',
                value: data.hd_db,
            },

            {
                source: '西南(源)',
                target: '华北(业)',
                value: data.xn_hd
            },{
                source: '西南(源)',
                target: '华南(业)',
                value: data.xn_hn,
            }, {
                source: '西南(源)',
                target: '华东(业)',
                value: data.xn_hd,
            },{
                source: '西南(源)',
                target: '西南(业)',
                value: data.xn_xn,
            },{
                source: '西南(源)',
                target: '西北(业)',
                value: data.xn_xb,
            },{
                source: '西南(源)',
                target: '东北(业)',
                value: data.xn_db,
            },

            {
                source: '西北(源)',
                target: '华北(业)',
                value: data.xb_hd
            },{
                source: '西北(源)',
                target: '华南(业)',
                value: data.xb_hn,
            }, {
                source: '西北(源)',
                target: '华东(业)',
                value: data.xb_hd,
            },{
                source: '西北(源)',
                target: '西南(业)',
                value: data.xb_xn,
            },{
                source: '西北(源)',
                target: '西北(业)',
                value: data.xb_xb,
            },{
                source: '西北(源)',
                target: '东北(业)',
                value: data.xb_db,
            },

            {
                source: '东北(源)',
                target: '华北(业)',
                value: data.db_hd
            },{
                source: '东北(源)',
                target: '华南(业)',
                value: data.db_hn,
            }, {
                source: '东北(源)',
                target: '华东(业)',
                value: data.db_hd,
            },{
                source: '东北(源)',
                target: '西南(业)',
                value: data.db_xn,
            },{
                source: '东北(源)',
                target: '西北(业)',
                value: data.db_xb,
            },{
                source: '东北(源)',
                target: '东北(业)',
                value: data.db_db,
            },

            ],
            lineStyle: {
                //线性渐变，前四个参数分别是 x0, y0, x2, y2, 范围从 0 - 1，相当于在图形包围盒中的百分比
                /*color:new echarts.graphic.LinearGradient(0, 0, 1, 0, [{  //设置边为水平方向渐变
                    offset: 0,
                    color: '#136bff'
                },{
                    offset: 1,
                    color: '#87ffd6'
                }]),*/
                color:'source',
                orient: 'vertical',
                curveness: 0.5,  //设置边的曲度
                opacity:0.9  //设置边的透明度
            }
        }
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>

</body>
</html>