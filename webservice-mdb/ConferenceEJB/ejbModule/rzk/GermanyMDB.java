package rzk;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import model.Conference;

/**
 * Message-Driven Bean implementation class for: GermanyMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/topic/confinfo"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Topic")
		}, 
		mappedName = "java:/jms/topic/confinfo")
public class GermanyMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public GermanyMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	ObjectMessage om = (ObjectMessage) message;
    	try {
    		Conference conf = (Conference) om.getObject();
    		if (conf.getCountry().equalsIgnoreCase("germany")) {
    			System.out.println("^^^^^^^^^^^^^^^^^");
				System.out.println("\tGuten Tag");
				System.out.println(conf.getCity() + " " + conf.getCurrency() + " " + conf.getDialingCode());
				System.out.println("^^^^^^^^^^^^^^^^^");
    		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

}
