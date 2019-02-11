package rzk;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.BindingProvider;

import model.Conference;
import servis.CountryDetailsPOJO;
import servis.CountryDetailsRemoteService;
import servis.CountryDetailsServiceService;

/**
 * Message-Driven Bean implementation class for: ConferenceMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/conference"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:/jms/queue/conference")
public class ConferenceMDB implements MessageListener {

	@PersistenceContext
	EntityManager em;

	@Inject
	JMSContext ctx;

	@Resource(mappedName = "java:/jms/topic/confinfo")
	Destination topicDestination;

	/**
	 * Default constructor.
	 */
	public ConferenceMDB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {

		ObjectMessage om = (ObjectMessage) message;

		try {
			Conference conf = (Conference) om.getObject();

			CountryDetailsServiceService cdss = new CountryDetailsServiceService();
			CountryDetailsRemoteService cdrs = cdss.getCountryDetailsServicePort();

			((BindingProvider) cdrs).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					"https://jovicev-ejb-servis.herokuapp.com/JovicevEJBServis-0.0.1-SNAPSHOT/CountryDetailsService");

			CountryDetailsPOJO details = cdrs.getInfoForCountry(conf.getCountry());

			conf.setCurrency(details.getCurrency());
			conf.setDialingCode(details.getDialingCode());
			conf.setCountryCode(details.getIsoCode());
			
			sendToTopic(conf);
			writeToDB(conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendToTopic(Conference c) {
		ObjectMessage om = ctx.createObjectMessage();
		try {
			om.setObject(c);
			JMSProducer producer = ctx.createProducer();
			producer.send(topicDestination, om);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeToDB(Conference c) {
		em.persist(c);
	}

}
