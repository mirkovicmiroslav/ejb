package rzk;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.EventType;

/**
 * Session Bean implementation class EventTypeBean
 */
@Singleton
@LocalBean
public class EventTypeBean implements EventTypeBeanRemote {

	@PersistenceContext
	EntityManager em;

	private List<EventType> types;

	public EventTypeBean() {
		
	}

	@Override
	public List<EventType> getTypes() {
		Query q = em.createQuery("SELECT e FROM EventType e");
		types = q.getResultList();
		try {
			return types;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostConstruct
	public void created() {
		System.out.println("Kreiran singleton: " + (new java.util.Date()));
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Unisten singleton: " + (new java.util.Date()));
	}

}