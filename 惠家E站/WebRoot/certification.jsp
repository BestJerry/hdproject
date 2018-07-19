<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>login.jsp</title>
</head>

<body>
	<%-- 	<s:a action="findByPassflag">审核 --%>
	<%-- 	<s:param name="news.NPassflag"></s:param> --%>
	<%-- 	</s:a> --%>


	<div style="margin-top: 100px" align="center">
		<s:form action="userscheckAction" method="post" theme="simple">
			<h2>实名认证</h2>
			<br>
待审核的账号：<s:property value="#session.userID" />
			<br>
审核的身份证：<s:textfield name="userscheck.ucNumber" value="" />
			<br>
			<s:submit value="提交" />
			<s:reset value="重置 " />
		</s:form>
		<%-- 		<s:if test="#session.userscheck==ture"> --%>

		<%-- 		</s:if> --%>
	</div>
</body>
</html>
