<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>login.jsp</title>
</head>

<body>
	<s:form action="usersAction" method="post" theme="simple">
        账号：<s:textfield name="users.UId" value="1200" />
		<s:textfield name="users.UName" value="2" />
		<s:textfield name="users.psd" value="3" />
		<s:textfield name="users.number" value="4" />
		<s:textfield name="users.UAbouts" value="5" />
		<s:textfield name="users.phone" value="6" />
		<s:submit value="登录" />
		<s:reset value="重置" />
	</s:form>


</body>
</html>
