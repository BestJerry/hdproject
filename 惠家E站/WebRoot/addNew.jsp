<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>惠家网点开通新闻</title>
</head>

<body>
	<%
		request.setAttribute("myDate", new Date());
	%>
	<s:a action="findAllNew">返回主页
	</s:a>
	<center>
		<h1>惠家网点开通新闻</h1>

		<s:form action="createNew" method="post" theme="simple">
			<table>
				<tr>
					<td style="width: 570px;">标题：<s:textfield name="news.title"
							cssStyle="width:520px" /></td>
					<td><s:select name="news.NFlag"
							list="#{'金融类': '金融类','健康类':'健康类','便民类':'便民类','活动类':'活动类','其他':'其他'}"
							headerValue="请选择" cssStyle="width:70px"></s:select></td>
				</tr>
				<tr>
					<td colspan="2"><s:textarea name="news.NBody"
							cssStyle="width:650px" rows="20"></s:textarea></td>
				</tr>
				<tr>
					<td align="left" colspan="2">作者：<s:textfield
							name="news.author" label="用户名" /></td>
				</tr>
				<tr>
					<td><s:date name="#request.myDate" format="yyyy年MM月dd日"
							var="sdate" />时间：<s:property value="#sdate" /></td>
					<td align="right" background="blue"><s:submit value="发布"
							cssStyle="width:70px" /></td>
				</tr>
			</table>
		</s:form>
	</center>
</body>
</html>
