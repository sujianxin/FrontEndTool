<%--
  Created by IntelliJ IDEA.
  User: sujianxin
  Date: 2016/3/4
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>前端可视化布局工具</title>
    <link rel="stylesheet" href="css/font-awesome.css"/>
    <link rel="stylesheet" href="css/main.css"/>
<body>
<%@include file="header.jsp" %>
<div class="info_main">
    <div class="info_main_container">
        <nav class="info_main_nav">
            <ul class="info_main_nav_list">
                <li class="info_main_nav_list_item"><a
                        class="info_main_nav_list_item_link info_main_nav_list_item_link_Active" href="project"><i
                        class="icon-reorder"></i>个人项目</a></li>
                <li class="info_main_nav_list_item"><a class="info_main_nav_list_item_link" href="userInfo"><i
                        class="icon-user"></i>账号信息</a></li>
            </ul>
        </nav>
        <div class="info_main_stage">
            <div class="tool_header">
                <div class="tool_header_left">
                    <div class="tool_header_title_back">
                        <a href="project"><i class="icon-chevron-left"></i></a>
                    </div>
                    <h2 class="tool_header_title">新增项目</h2>
                </div>
            </div>
            <div class="info_main_content">
                <form action="newProject" class="info_newProject_form" method="post">
                    <div class="info_newProject_item">
                        <span>项目名称：</span><input name="name" type="text" class="tool_text info_newProject_name"
                                                 maxlength="255" required pattern="^[A-Za-z0-9_]{1,255}$"
                                                 placeholder="英文字母、数字、下划线或其组合"/>
                    </div>
                    <div class="info_newProject_item">
                        <span>备注：</span><textarea name="remark" placeholder="最多输入255个字符" maxlength="255" class="tool_textarea"></textarea>
                    </div>
                    <div class="info_newProject_item">
                        <input type="submit" value="确 定" class="tool_btn tool_btn_Big tool_btn_Blue info_newProject_submitBtn"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery.js"></script>
<script src="js/common.js"></script>
<script src="js/main.js"></script>
<script>
    $(function(){
        fe.app.confirmBoxEvent();
        fe.app.remindBoxEvent();

        fe.tool.success(window.location.search, function () {
            $.confirmBox({
                id: "info_newProject",
                title: "创建成功",
                confirm: "项目已创建成功,是否跳转到编辑页面"
            });
        }, function (msg) {
            $.remindBox({
                remind: msg || "项目创建失败"
            });
        });

    });
</script>
</body>
</html>