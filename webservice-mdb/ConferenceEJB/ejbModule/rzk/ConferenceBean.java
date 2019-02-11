package rzk;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;

import model.Conference;

/**
 * Session Bean implementation class ConferenceBean
 */
@Stateless
@LocalBean
public class ConferenceBean implements ConferenceBeanRemote {

	@Inject
	JMSContext ctx;

	@Resource(mappedName = "java:/jms/queue/conference")
	Destination queueDestinaton;

	/**
	 * Default constructor.
	 */
	public ConferenceBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendConference(String title, String country, String city, Date dateFrom, Date dateTo, String field) {
		ObjectMessage message = ctx.createObjectMessage();

		Conference conf = new Conference();
		conf.setTitle(title);
		conf.setCountry(country);
		conf.setCity(city);
		conf.setDateFrom(dateFrom);
		conf.setDateTo(dateTo);
		conf.setField(field);

		try {
			message.setObject(conf);
			JMSProducer producer = ctx.createProducer();
			producer.send(queueDestinaton, message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
