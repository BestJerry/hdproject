<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>login.jsp</title>
</head>

<body>
	<s:a action="findAllNew">返回主页
	</s:a>
	
	<table style="width: 800px; ">
		<tr>
			<td style="width:100px; "></td>
			<td colspan="2" align="left" style="width: 400px; "><h1>
					<s:property value="news.title" />
				</h1></td>
		</tr>
		<tr>
			<td style="width:100px; ">&nbsp;</td>
			<td align="left" style="width: 250px; "><s:date
					name="news.NDate" format="yyyy年MM月dd日" var="sdate" /> <s:property
					value="#sdate" /> <s:property value="news.NFlag" /></td>
			<td align="right"><s:property value="news.author" />&emsp;&emsp;</td>
		</tr>
	</table>
	<hr>
	<table style="width: 800px;">
		<tr>
			<td style="width:100px; "></td>
			<td colspan="2" align="left">&emsp;&emsp;<s:property
					value="news.NBody" /></td>
		</tr>
	</table>
</body>
</html>
