<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html><html></html>
<%@ page import="classes.Item" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout</title>

</head>
<body>
<div>
<h2> Your Shopping Cart </h2>
<table>
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
                                    <td align="center"><% out.print(it.getName());%></td>
                                    <td align="center"><% out.print(it.getDesc());%></td> 
                                    <td align="center"><% out.print(it.getPrice());%></td>
                                    <td align="center"><% out.print(it.getQty());%></td>
                                </tr>
                                <%}
                                    }
                                %>    
                            </table>
 </div>
 <div>
 <h2>Your total Order Amount is <%= session.getAttribute("total") %></h2>
 </div>
 <div>
 <h2> Enter Shipping Details</h2>
 <p>
 <form name=pay method=post action=OrderPlacedServlet>
 <table>
<tr><td>Name:</td><td><input type="text" name="name"></td></tr>
<tr><td>Address:</td><td><input type="text" name="address1"></td>
</tr>
<tr><td></td><td><input type="text" name="address2"></td></tr>
<tr><td>City:</td><td><input type="text" name="city"></td></tr>
 <tr>   <td>State:</td>
    <td><input type="text" name="state" size=2 maxlength=2></td></tr>
<tr><td>Postal Code (Zip in U.S.):</td>
    <td><input type="text" name="postalCode"></td></tr>
<tr><td>Country:</td><td><input type="text" name="country"></td></tr>
<tr><td>Email Address:</td><td><input type="text" name="email">
</td></tr>
</table>
<p>

<h2>Please enter your billing information</h2>
<table>
<tr><td>Name (as it appears on credit card):</td>
    <td><input type="text" name="nameOnCard"></td></tr>
<tr><td>Credit Card:</td>
<td><select name="creditCardType">
    <option value="amex">American Express</option>
    <option value="visa">Visa</option>
    <option value="mc">Mastercard</option>
    <option value="discover">Discover</option>
    </select></td></tr>
<tr><td>Credit Card Number:</td>
    <td><input type="text" name="creditCardNumber"></td></tr>
<tr><td>Expiration Date:</td>
    <td><input type="text" name="creditCardExpiration"></td></tr>
</table>
<p>
<input type="submit" value="Place Order">
</p>
</form>
<p>
<a href="main.jsp">Shop More</a>
<a href="viewcart.jsp">Edit Cart</a>
</p>
<p>
<jsp:include page="logout.jsp" />
</p>
</div>
</body>


</html>