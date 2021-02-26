

function indexInit(menuList){
    for(let i = 0; i<menuList.length; i++){
        if(menuList[i].pid1==-1)
            createParent(menuList[i])
        else
            createChildren(menuList[i])
    }
}

function createParent(parentObj){
    var parentNode = document.getElementById('nav')
    var li = document.createElement('li')
    const parentIcon={
        '角色管理':'&#xe70b;',
        '菜单管理':'&#xe696;',
        '数据导入':'&#xe6ab;',
        '招生统计':'&#xe6b5;',
        '招生分析':'&#xe757;',
        '就业分析':'&#xe726;',
    }

    li.setAttribute('id',parentObj.id1)

    var parentStr = "<a href = 'javascript:;' target='middle'><i class = 'iconfont left-nav-li' lay-tips="+parentObj.name1+
        ">"+parentIcon[parentObj.name1]+"</i><cite>"+parentObj.name1+"</cite><i class='iconfont nav_right'>&#xe697;</i></a>";

    li.innerHTML = parentStr
    parentNode.appendChild(li)
}


function createChildren(childrenObj){
    var parentNode = document.getElementById(childrenObj.pid1)
    var ul = document.createElement('ul')

    ul.setAttribute('class','sub-menu')

    var childStr = "<li><a target='middle' onclick=\"xadmin.add_tab('"+childrenObj.name1+"','"+childrenObj.url1+"')\">"+
        "<i class='iconfont'>&#xe6a7;</i><cite>"+childrenObj.name1+"</cite></a></li>";

    ul.innerHTML = childStr
    parentNode.appendChild(ul)
}