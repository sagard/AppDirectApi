package classes;
import javax.servlet.http.HttpServletResponse;

public class CreateResponse {
	
	public static String out=null;

	public static String generateResponse(String type){
		switch(type){
		case "SUBSCRIPTION_ORDER": out = order(); 
		      break;
		case "SUBSCRIPTION_CHANGE": out = successXML("Order changed success");
		                       break;
		case "SUBSCRIPTION_CANCEL": out = successXML("Subscription cancelled successfully");
		     break;
		case "SUBSCRIPTION_NOTICE": out = successXML("Notice sent successfully");
		     break;
		}
		
		return out;
	}
	
	public static String order(){
		out =
				"<result>" +
						"<success>" +
						"true" + "</success>" +
						"<message>" + "Account created success" + "</message>" +
						"<accountIdentifier>" + "1234" + "</accountIdentifier>" +
						"</result>"
						;
		return out;
	}
	
	public static String successXML(String message){
		out = 
				"<result>" + "<success>" +	"true" + "</success>" +	
				"<message>" + message + "</message>" +
		        "</result>";
		return out;
	}
	
		
	public static String createFailXml(String errorCode,String message){
		out = 
				"<result>" +
						"<success>" +
						"false" + "</success>" +
						"<errorCode>" + errorCode + "</errorCode>" +
						"<message>" + message + "</message>" +
						"</result>"
						;
		return out;
	}

}
