package classes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.signature.QueryStringSigningStrategy;

public class OAuth
{
    private static final String CONSUMER_SIGNING_KEY = "shoppingcart-8078";
    private static final String CONSUMER_SIGNING_SECRET = "7MqrlDj4exUyUqkn";
  
    // take the url ,key and value
    public static String signOutGoingRequest( String urlStr) throws Exception
    {
        // This example uses OAuth Signpost to sign an outgoing request:
        OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_SIGNING_KEY,CONSUMER_SIGNING_SECRET);
        URL url = new URL(urlStr);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        consumer.sign(request);
        request.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(
				request.getInputStream()));

		String inputLine =null;

		// contains the xml data
		inputLine= in.readLine();
		in.close();
        return inputLine;
    }
}