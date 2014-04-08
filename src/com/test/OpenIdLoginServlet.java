package com.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;

/**
 * Servlet implementation class OpenIdLoginServlet
 */
@WebServlet(urlPatterns={"/OpenIdLoginServlet","/OpenIdLoginServlet1"})
public class OpenIdLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenIdLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    final static String YAHOO_ENDPOINT = "https://me.yahoo.com";
    final static String GOOGLE_ENDPOINT = "https://www.google.com/accounts/o8/id";
    final static String LOGIN_PATH = "/ShoppingCart/OpenIdLoginServlet";
    private static final String OPENID_PATH = "/ShoppingCart/OpenIdLoginServlet1";

    public ServletContext context;
    public ConsumerManager manager;
    
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    context = config.getServletContext();
    this.manager = new ConsumerManager();
    }
    
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
	  String reqUri = req.getRequestURI() == null ? "" : req.getRequestURI().trim();   
	  System.out.println("reqUri :" + reqUri);
	  
	if (LOGIN_PATH.equalsIgnoreCase(reqUri))	  
	{		
      String openid_url = req.getParameter("openid_url");
      System.out.println("identifier is :" + openid_url);
      this.authRequest(openid_url, req, resp);
	}
   else if (OPENID_PATH.equalsIgnoreCase(reqUri))
   {
     Identifier identifier = this.verifyResponse(req);
    
    // redirect to home page
   if (identifier != null) {
    resp.sendRedirect("main.jsp");
    } else {
    resp.sendRedirect("loginfailed.jsp");
    System.out.println("login with openid failed");
    }
   }
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    	   System.out.print("in postt");
  
    }
    
    // --- placing the authentication request ---
    public String authRequest(String userSuppliedString,
    HttpServletRequest httpReq, HttpServletResponse httpResp)
    throws IOException {
    try {
    // configure the return_to URL where your application will receive
    // the authentication responses from the OpenID provider
    String returnToUrl = httpReq.getRequestURL().toString() + "1";
    System.out.println("Return url:" + returnToUrl);
    System.out.println("User suppleid string url:" + userSuppliedString);

        
    // perform discovery on the user-supplied identifier
    List discoveries = manager.discover(userSuppliedString);
    
    // attempt to associate with the OpenID provider
    // and retrieve one service end point for authentication
    DiscoveryInformation discovered = manager.associate(discoveries);
    
    // store the discovery information in the user's session
    httpReq.getSession().setAttribute("openid-disc", discovered);
    
    // obtain a AuthRequest message to be sent to the OpenID provider
    AuthRequest authReq = manager.authenticate(discovered, returnToUrl);
    
    FetchRequest fetch = FetchRequest.createFetchRequest();
    if (userSuppliedString.startsWith(GOOGLE_ENDPOINT)) {
    fetch.addAttribute("email",
    "http://schema.openid.net/contact/email", true);
    fetch.addAttribute("firstName",
    "http://schema.openid.net/namePerson/first", true);
    fetch.addAttribute("lastName",
    "http://schema.openid.net/namePerson/last", true);
    } else if (userSuppliedString.startsWith(YAHOO_ENDPOINT)) {
    fetch.addAttribute("email",
    "http://schema.openid.net/contact/email", true);
    fetch.addAttribute("fullname",
    "http://schema.openid.net/namePerson", true);
    } 
    // attach the extension to the authentication request
    authReq.addExtension(fetch);
    
    httpResp.sendRedirect(authReq.getDestinationUrl(true));
    
    } catch (OpenIDException e) {
    // present error to the user
    }

    return null;
    }
    
    // --- processing the authentication response ---
    public Identifier verifyResponse(HttpServletRequest httpReq) {
    try {
    // extract the parameters from the authentication response
    // (which comes in as a HTTP request from the OpenID provider)
    ParameterList response = new ParameterList(
    httpReq.getParameterMap());
    
    // retrieve the previously stored discovery information
    DiscoveryInformation discovered = (DiscoveryInformation) httpReq
    .getSession().getAttribute("openid-disc");
    
    // extract the receiving URL from the HTTP request
    StringBuffer receivingURL = httpReq.getRequestURL();
    String queryString = httpReq.getQueryString();
    if (queryString != null && queryString.length() > 0)
    receivingURL.append("?").append(httpReq.getQueryString());
    
    // verify the response; ConsumerManager needs to be the same
    // (static) instance used to place the authentication request
    VerificationResult verification = manager.verify(
    receivingURL.toString(), response, discovered);
    
    // examine the verification result and extract the verified
    // identifier
    Identifier verified = verification.getVerifiedId();
    if (verified != null) {
    AuthSuccess authSuccess = (AuthSuccess) verification
    .getAuthResponse();
    System.out.println(authSuccess);
    if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
    FetchResponse fetchResp = (FetchResponse) authSuccess
    .getExtension(AxMessage.OPENID_NS_AX);
    
    List emails = fetchResp.getAttributeValues("email");
    String email = (String) emails.get(0);
    }
    
    return verified; // success
    }
    } catch (OpenIDException e) {
    // present error to the user
    }
    
    return null;
    }
    
    }