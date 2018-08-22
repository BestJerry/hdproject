<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>用户登录</title>
</head>

<body>
	<br>
	<br>
	<center>
		<h1>用户登录</h1>
		<br>
		<s:form action="usersLogin" method="post" theme="simple">
			<table>
				<tr>
					<td>账号：</td>
					<td><s:textfield name="users.UId" value=""
							cssStyle="width=200px" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><s:password name="users.psd" cssStyle="width=200px" /></td>
				</tr>
				<tr>
					<td></td>
					<td><s:submit value="登录" /> &emsp;&emsp;<s:reset value="重置" />
						&emsp;&emsp;<a href="register.jsp">注册</a></td>
				</tr>
			</table>
		</s:form>
	</center>
</body>
</html>
