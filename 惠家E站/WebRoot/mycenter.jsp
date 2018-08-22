<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'mycenter.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.mydiv {
	height: 31px;
	margin: 20px 0;
	background-color: #fff;
	border-bottom: 1px solid #ccc;
}

.mainbutton {
	border-style: solid;
	border-width: 0;
	border-color: none;
	height: 25px;
	width: 200px;
	border-radius: 40px;
	opacity: 10;
	background: none;
	color: white;
	font-size: 15px;
	border-color: none;
	height: 25px;
	width: 200px;
	border-radius: 40px;
	opacity: 10;
}

.mydiv button {
	display: inline-block;
	height: 30px;
	line-height: 20px;
	border: 1px solid #ccc;
	padding: 5px;
	margin-right: 10px;
	border-bottom: 0;
	background-color: #fff;
}
</style>
<script type="text/javascript">
	var mt = 0;
	var i = 0;
	window.onload = function() {
		var mydiv = document.getElementById("mydiv");
		var mt = mydiv.offsetTop;
		window.onscroll = function() {
			var t = document.documentElement.scrollTop || document.body.scrollTop;
			if (t > mt) {
				mydiv.style.position = "fixed";
				mydiv.style.margin = "0";
				mydiv.style.top = "0";
			} else {
				mydiv.style.margin = "20px 0";
				mydiv.style.position = "static";
			}
		}
	}
	function gomymess() {
		window.open("mymess.jsp", '_self');
	}
	function gocertification() {
		window.open("certification.jsp", '_self');
	}
	function applynews() {
		window.open("addNew.jsp", '_self');
	}
	function addrows() {
		var len = optionlist.rows.length; //得到table的行数 
		var obj = optionlist.insertRow(len); //在最后一行插入 
		/**插入第一列**/
		obj.insertCell(0);
		obj.cells(0).innerHTML = "<textarea name=" + i + " rows=18 cols=80></textarea>";
		var input = document.getElementById(i);
		i++;
		input.value += "第" + i + "新闻";
	//window.onload = addrows;
	}
</script>
</head>
<body>
	<s:property value="#session.userID" />
	<s:property value="#session.userName" />
	<div class="mydiv" id="mydiv">
		<!-- 		<button onclick="gomymess()">我的账户信息</button> -->
		<button onclick="gocertification()">实名认证</button>
		<button onclick="applynews()">开通新闻</button>
		<br>
	</div>
	<br>
	<div id="mydiv2" style="margin-top: 100px" align="center">
		<s:iterator value="newsList">
			<table id="optionlist">
				<tr>
					<td align=right"><s:a style="color: blue;font:20px"
							action="findNewById">
							<s:property value="title" />
							<s:param name="news.NId">
								<s:property value="NId" />
							</s:param>
						</s:a></td>
				</tr>
				<tr>
					<td><textarea cols="80" style="border: none;overflow:hidden;"><s:property value="NBody" />
					</textarea></td>
				</tr>
				<tr>
					<td style="color: #808080; font-size: 15px"><s:date
							name="NDate" format="yyyy年MM月dd日" var="ndate" /> <s:property
							value="#ndate" /></td>
				</tr>
			</table>
			<br>
			<br>
			<br>
		</s:iterator>
	</div>
</body>
</html>
