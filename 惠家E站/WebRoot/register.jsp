<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>用户注册</title>
</head>

<body>
	<br>
	<br>
	<center>
		<h1>用户注册</h1>
		<br>
		<s:form action="usersAction" method="post" theme="simple">
			<table>
				<tr>
					<td>账号：</td>
					<td><s:textfield name="users.UId" cssStyle="width=200px"
							value="" /></td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td><s:textfield name="users.UName" cssStyle="width=200px"
							value="" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><s:password name="users.psd" cssStyle="width=200px"
							value="" /></td>
				</tr>
				<tr>
					<td>备注：</td>
					<td><s:textfield name="users.UAbouts" cssStyle="width=200px"
							value="" /></td>
				</tr>
				<tr>
					<td>手机号码：</td>
					<td><s:textfield name="users.phone" cssStyle="width=200px"
							value="" /></td>
				</tr>
				<tr>
					<td></td>
					<td><s:submit value="注册" /> &emsp;&emsp;<s:reset value="重置" />&emsp;&emsp;<a
						href="login.jsp">返回</td>
				</tr>
			</table>
		</s:form>
	</center>
</body>
</html>
