package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import classes.CreateResponse;
import classes.ParseXML;
import classes.OAuth;



/**
 * Servlet implementation class GetParamServlet
 */
@WebServlet("/GetParamServlet")
public class GetParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		handleRequest(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		handleRequest(req, res);

	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String type=null;
		URLConnection yc=null;
		String url=null;
		String inputLine = null;
        // get the key and value from authorization
		HashMap<String, String> authmap = new HashMap<String, String>();
		String auth = req.getHeader("Authorization");
	    String authVal[] = auth.split(" |,");
		
		for(String val : authVal){
          final String split[] = val.split("=");
          // puts the key-value in authorization if map
          if(split.length > 1)
            authmap.put(split[0],split[1]);
		}
		
		String consumerKey = authmap.get("oauth_consumer_key");
		String oauthSignature = authmap.get("oauth_signature");
		System.out.println("key is : " + consumerKey);

		if(consumerKey == null || oauthSignature == null){
			sendFailResponse(res,"UNAUTHORIZED", "No key or sign provided,cannot authorize user");
		}
		System.out.println("ouath sign is :" + oauthSignature);
		
		// match the key
		if(consumerKey.equals("\"shoppingcart-8078\""))
			{
			//read the url paramter from received request
			 url = req.getParameter("url");
			System.out.println("url is :" + url);
			if(url == null){
				sendFailResponse(res,"CONFIGURATION_ERROR", "No url provided in endpoint");
			}
			try {
				inputLine = OAuth.signOutGoingRequest(url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				sendFailResponse(res,"UNAUTHORIZED", "Cannot verify oauth sign");
  			}
		}		
        //sign the return url ; call oauth.java by passing the oauthkey nd sign.
		else{
			sendFailResponse(res,"UNAUTHORIZED", "No key or sign provided,cannot authorize user");
		}
				
                   
		//cal parse xml to parse the xml file
		try {
			type = ParseXML.parse(inputLine);
		} catch (ParserConfigurationException | SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			sendFailResponse(res,"INVALID_RESPONSE", "Error in parsing XML file");

		}
		
		/*// if user exists send error
		if(type.equals("SUBSCRIPTION_ORDER")){
			String username = ParseXML.creator.get("firstName");
			if(username.equals("DummyCreatorFirst")){
				sendFailResponse(res,"USER_ALREADY_EXISTS", "User already exists");
			}
		}*/
		
		//error for notice,change and cancel events
		if(type.equals("SUBSCRIPTION_CHANGE") || type.equals("SUBSCRIPTION_CANCEL") || type.equals("SUBSCRIPTION_NOTICE")){
			String username = ParseXML.creator.get("firstName");
			if(!username.equals("DummyCreatorFirst")){
				sendFailResponse(res,"USER_NOT_FOUND", "User does not exist");
			}
		}
				
						
		//create the response based on type
		res.setContentType("text/xml");
		String out = CreateResponse.generateResponse(type);
		res.getWriter().println(out);


	}
	
	public static void sendFailResponse(HttpServletResponse res,String errorCode,String message){
		System.out.println("in send failed resp");
		res.setContentType("text/xml");
		String out = CreateResponse.createFailXml(errorCode, message);
		try {
				res.getWriter().println(out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
