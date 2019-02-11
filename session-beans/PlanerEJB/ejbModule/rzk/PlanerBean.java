package rzk;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Event;
import model.EventType;
import model.User;

/**
 * Session Bean implementation class PlanerBean
 */
@Stateful
@LocalBean
public class PlanerBean implements PlanerBeanRemote, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3411982536209459499L;
	@PersistenceContext
	EntityManager em;
	@EJB
	EventTypeBean eTB;

	private int userID;

	public PlanerBean() {
		
	}

	@Override
	public String login(String username, String password) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email LIKE :user AND u.password LIKE :pass");
		q.setParameter("user", username);
		q.setParameter("pass", password);
		List<User> users = q.getResultList();
		User us = new User();
		userID = users.get(0).getId();
		us.setId(userID);
		try {
			String user = users.get(0).getEmail();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public boolean createAnEvent(String description, Date dateFormat, Date dateTo, int eventTypeId) {
		try {
			Event event = new Event();
			event.setDescription(description);
			event.setFromDate(dateFormat);
			event.setToDate(dateTo);
			event.setEventType(em.find(EventType.class, eventTypeId));
			User user = em.find(User.class, userID);
			event.setUser(user);
			user.addEvent(event);
			
			em.persist(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Event> searchEvents(Date date) {
		Query q = em.createQuery("SELECT e FROM Event e WHERE e.fromDate=:dt");
		q.setParameter("dt", date);
		List<Event> events = q.getResultList();
		try {
			return events;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EventType> getTypes() {
		try {
			return eTB.getTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostConstruct
	public void created() {
		System.out.println("Kreiran stateful: " + (new java.util.Date()));
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Unisten stateful: " + (new java.util.Date()));
	}

}