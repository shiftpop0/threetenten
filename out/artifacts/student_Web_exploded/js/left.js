var data = "${sessionScope.RESULTMAP.MENULIST}";
var menuData = [];
//解析得到的字符串，转化成需要的数据格式
var index = 0;
//解析第一个符号"["

var menuItem;
if (data.length > 2) {
    data = data.substr(1, data.length - 2);
    menuItem = data.split("}");
}

//循环处理到结束
var menuIndex = 0;
while (menuIndex < menuItem.length - 1) {
    var menuString = menuItem[menuIndex];

    var msIndex = 0;
    while (menuString[msIndex] != "{") {
        msIndex++;
    }
    msIndex++;

    menuString = menuString.substr(msIndex, menuString.length - msIndex);

    var menuDetail = menuString.split(",");

    var item = new Object();

    var detailIndex = 0;
    while (detailIndex < menuDetail.length) {

        var detailItem = menuDetail[detailIndex];

        var detailString = detailItem.split("=");
        var detailKey = detailString[0];
        detailKey = detailKey.replace(" ", "");
        var detailValue = detailString[1];
        detailValue = detailValue.replace(" ", "");

        if (detailKey == "id1") {
            item.id1 = detailValue;
        }

        if (detailKey == "name1") {
            item.name1 = detailValue;
        }

        if (detailKey == "pid1") {
            item.pid1 = detailValue;
        }

        if (detailKey == "url1") {
            if (detailString.length > 2) {
                item.url1 = detailValue + "=" + detailString[2];
            } else {
                item.url1 = detailValue;
            }
        }

        detailIndex++;
    }

    //添加children节点
    if (item.pid1 == "-1") {
        item.children1 = [];
    } else {
        item.children1 = null;
    }

    menuData[menuIndex] = item;

    menuIndex++;

}
$(window).load(indexInit(menuData))

function skipBlank(data, index) {
    var i = index;
    while (data[i] == ' ') {
        i++;
    }
    return i;
}

/*
  menuData数据样式

var menuData = [
    {id1:"A01",pid1:"-1",name1:"zs1",url1:"#",children1:[]},
    {id1:"A02",pid1:"-1",name1:"zs2",url1:"#",children1:[]},
    {id1:"A03",pid1:"-1",name1:"zs3",url1:"#",children1:[]},
    {id1:"A0101",pid1:"A01",name1:"zs1sun1",url1:"#",children1:null},
    {id1:"A0102",pid1:"A01",name1:"zs1sun2",url1:"#",children1:null},
    {id1:"A0201",pid1:"A02",name1:"zs2sun1",url1:"#",children1:null},
    {id1:"A0202",pid1:"A02",name1:"zs2sun2",url1:"#",children1:null},
    {id1:"A0301",pid1:"A03",name1:"zs3sun1",url1:"#",children1:null},
    {id1:"A0302",pid1:"A03",name1:"zs3sun2",url1:"#",children1:null}
];
alert(menuData[0].id1);
*/

var menu = $("#accordion").createAccordion({
        id1: "id1"
        , pid1: "pid1"
        , name1: "name1"
        , url1: "url1"
        , children1: "children1"
    }
    , menuData
);


function ExChgClsName(Obj, NameA, NameB) {
    var Obj = document.getElementById(Obj) ? document.getElementById(Obj) : Obj;
    Obj.className = Obj.className == NameA ? NameB : NameA;
}

function showmenu_zzjs(iNo) {
    ExChgClsName("zzjs_" + iNo, "menu_zzjs_1", "menu_zzjs_2");
}

function showmenu_zxlx(iNo) {
    ExChgClsName("zxlx_" + iNo, "menu_zxlx_1", "menu_zxlx_2");
}