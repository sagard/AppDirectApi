<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success Page</title>
<style>
table,th,td
{
border:1.5px solid black;
border-collapse:collapse;
}
th,td
{
padding:5px;
}
</style>
</head>
<body>
	<header>
	  <h2 align="center">Welcome to My Shopping Site</h2>
	</header>
	
	<nav>
		<a href="#">Electronics</a>
		<a href="#">Clothing</a>
		<a href="#">Music</a>
	</nav>
	<p>
	
	  <table style="width:700px">
       <tr>
         <th>Name</th>
         <th>Description</th>		
         <th>Price</th>
         <th>Quantity</th>
       </tr>
  <tr>
 <form name="row1" method="get" action="AddToCartServlet" >
    <td>PSP <input type="hidden" name="name" value="PSP"/></td>
    <td>Handheld gaming console <input type="hidden" name="desc" value="Handheld gaming console"/> </td>		
    <td>120<input type="hidden" name="price" value="120"/> </td>
    <td><select name="qty">
     <option value="1">1</option>
     <option value="2">2</option>
     <option value="3">3</option>
     <option value="4">4</option>
     </select>
     </td>
     <td><input type="submit" value="AddtoCart"></td>
  </form>
  </tr>
<tr>
  <form name="row2" method="get" action="AddToCartServlet" >
  <td>Bose Speaker<input type="hidden" name="name" value="Bose Speaker"/></td>
  <td>High Performance speaker to stream music <input type="hidden" name="desc" value="High Performance speaker to stream music"/></td>		
  <td>220<input type="hidden" name="price" value="220"/></td>
   <td><select name="qty" >
     <option value="1">1</option>
     <option value="2">2</option>
     <option value="3">3</option>
     <option value="4">4</option>
     </select>
     </td>
     <td><input type="submit" value="AddtoCart"></td>
    </form>
</tr>
<tr>
  <form name="row3" method="get" action="AddToCartServlet" >
  <td>Ipod <input type="hidden" name="name" value="Ipod"/></td>
  <td>Music player by Apple<input type="hidden" name="desc" value="Music player by Apple"/></td>		
  <td>300<input type="hidden" name="price" value="300"/></td>
   <td><select name="qty">
     <option value="1">1</option>
     <option value="2">2</option>
     <option value="3">3</option>
     <option value="4">4</option>
     </select>
     </td>
      <td><input type="submit" value="AddtoCart"></td>
    </form>
</tr>
</table>
<p>
<jsp:include page="logout.jsp" />
</p>
	</body>
</html>