package rzk;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import model.Conference;

/**
 * Message-Driven Bean implementation class for: FranceMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/topic/confinfo"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Topic")
		}, 
		mappedName = "java:/jms/topic/confinfo")
public class FranceMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public FranceMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	ObjectMessage om = (ObjectMessage) message;
    	try {
    		Conference conf = (Conference) om.getObject();
    		if (conf.getCountry().equalsIgnoreCase("france")) {
    			System.out.println("##############");
				System.out.println("\tBonjour");
				System.out.println(conf.getCity() + " " + conf.getCurrency() + " " + conf.getDialingCode());
				System.out.println("##############");
    		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

}
