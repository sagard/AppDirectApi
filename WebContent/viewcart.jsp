<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="classes.Item" %>
 <%@ page import="java.util.ArrayList" %>
    
<!DOCTYPE html><html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart</title>
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
	  <h2 align="center">Your cart items</h2>
</header>
 <table >
                                <tr>
                                    <th>Name</th>
                                    <th>Desc</th>
                                    <th>Price</th>
                                    <th>Qty</th>
                                </tr>
                                <%if (session.getAttribute("cart") != null) {
                                        ArrayList mycart = (ArrayList) session.getAttribute("cart");
                                        for (int i = 0; i < mycart.size(); i++) {
                                            Item it = (Item) mycart.get(i);
                                %>
                                <tr>
                                <form name="remove" method="get" action="RemoveFromCartServlet">
                                    <td align="center"><% out.print(it.getName());%> <input type="hidden" name="num" value="<% out.print(i);%>"/></td>
                                    <td align="center"><% out.print(it.getDesc());%></td> 
                                    <td align="center"><% out.print(it.getPrice());%></td>
                                    <td align="center"><% out.print(it.getQty());%></td>
                                    <td align="center"><input type="submit" value="RemoveFromCart"></td>
                                    </form>
                                </tr>
                                <%}
                                    }
                                %>    
                            </table>
<div>
<p>
<a href="main.jsp">Continue Shopping</a>
<a href="checkout.jsp">Checkout</a>
</p>
<p>
<jsp:include page="logout.jsp" />
</p>
</div>



</body>
</html>