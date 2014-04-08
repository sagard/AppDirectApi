<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Hello Welcome</title>
</head>
<body>
<br>
<table width="100%" style="color:#AD68BE">
<tr>
<td align="center"><h2>WELCOME TO MY SHOPPING SITE</h2></td>
</tr>
<td align="center" style="color:#AD68BE">Use login sagar/test</td>
</table>

<table border="2" bgcolor="#F4C6FF" style="color:#800080" align="center" >
<form name="loginform" method="get" action="LoginServlet" >
      <tr><td align="right">* UserId : <input type="text" name="userid" /></td></tr>
      <tr><td align="right">* Password : <input type="password" name="pwd" /></td></tr>
      <tr><td><input type="submit" value="submit"></td></tr>    
      <tr><td><input type="submit" value="register"></td></tr>   
</form>  
<tr><td align="center"><form name="loginform1" method="get" action="OpenIdLoginServlet"> <input type="hidden" name="openid_url" value="https://www.google.com/accounts/o8/id"/>
<input type="submit" value="Login using google">    
</form></td></tr>
<tr><td align="center"><form name="loginform2" method="get" action="OpenIdLoginServlet"> <input type="hidden" name="openid_url" value="https://me.yahoo.com"/>
<input type="submit" value="Login using yahoo">
</table>
<h3> 
</h3>
</body>
</html>