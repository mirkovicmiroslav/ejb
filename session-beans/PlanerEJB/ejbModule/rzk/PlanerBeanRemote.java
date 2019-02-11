package rzk;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.Event;
import model.EventType;

@Remote
public interface PlanerBeanRemote {

	public String login(String username, String password);

	public boolean createAnEvent(String description, Date dateFormat, Date dateTo, int eventTypeId);

	public List<Event> searchEvents(Date date);

	public List<EventType> getTypes();
	
}