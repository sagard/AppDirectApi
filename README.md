AppDirectApi
============

API Integration 

This integrates the Subscription management,SSO : OpenID Authentication and OAuthAPI APIs.

WebApp is deployed at:
http://sag-basicshopping.rhcloud.com/ShoppingCart/login.jsp
This is a basic shopping cart that adds some items to cart,modify the cart and order the items.
===============================================================================================
To build a war file : Run the build.xml located in repo using ant.

===============================================================================================
Endpoint in AppDirect for testing are:
LoginURL : http://sag-basicshopping.rhcloud.com/ShoppingCart/OpenIdLoginServlet?openid_url={openid}

Subscription Create Notification URL:
http://sag-basicshopping.rhcloud.com/ShoppingCart/GetParamServlet?url={eventUrl}

Subscription Change Notification URL:
http://sag-basicshopping.rhcloud.com/ShoppingCart/GetParamServlet?url={eventUrl}

Subscription Cancel Notification URL:
http://sag-basicshopping.rhcloud.com/ShoppingCart/GetParamServlet?url={eventUrl}

Subscription Status Notification URL:
http://sag-basicshopping.rhcloud.com/ShoppingCart/GetParamServlet?url={eventUrl}

==================================================================================================
User information is not persisted in the app,it read the data in ParseXML.java which could be persisted later.
