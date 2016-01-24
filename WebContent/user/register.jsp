<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>register page</title>
</head>
<body>
	<table width="650" border="0">
		<tr>
			<td width="650" height="80" background="../image/top.jpg">&nbsp;</td>
		</tr>
		<tr>
			<td><a href="/login.jsp">Login</a>&nbsp;&nbsp; <a href="/register.jsp">Register</a>&nbsp;&nbsp; <a
				href="../StockListServlet">Watch Stock List</a>&nbsp;&nbsp; <a href="logout.do">Log off</a>&nbsp;&nbsp; <a
				href="loadUser.do">My Profile</a>&nbsp;&nbsp; <a href="showWatchList.do">Watch My Stock List</a>&nbsp;&nbsp; <a
				href="index.do">Order Cancellation</a>&nbsp;&nbsp; <a href="../stock/search.jsp">Search</a></td>
		</tr>
	</table>
	<br>
	<br>
	<H1><%=request.getAttribute("mess")%></H1>
	<BR>
	<table width=650>
		<tr>
			<td><s:form action="/User/register" method="post">
					<table align="center" border="0">
						<tr>
							<td><s:textfield name="user.userid" label="Userid" /></td>
						</tr>
						<tr>
							<td><s:password name="passwordOne" label="Password" /></td>
						</tr>
						<tr>
							<td><s:password name="passwordTwo" label="Retype password" /></td>
						</tr>
						<tr>
							<td><s:textfield name="user.name" label="Name" /></td>
						</tr>
						<tr>
							<td>Birthday</td>
							<td><s:textfield name="year" label="Year" /> <s:textfield name="month" label="Month" /> <s:textfield
									name="day" label="Day" /></td>
						</tr>
						<tr>
							<td><s:textfield name="user.phone" label="Phone" /></td>
						</tr>
						<tr>
							<td><s:textfield name="user.email" label="Email" /></td>
						</tr>
						<tr>
							<td><s:textfield name="user.bankid" label="Bankid" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><s:submit value="Submit" /><br> <s:reset value="Reset" /></td>
						</tr>
					</table>
				</s:form></td>
		</tr>
	</table>


</body>
</html>