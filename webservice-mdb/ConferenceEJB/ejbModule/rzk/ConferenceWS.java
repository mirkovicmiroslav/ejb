package rzk;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Conference;

/**
 * Session Bean implementation class ConferenceWS
 */
@Stateless
@LocalBean
@WebService
public class ConferenceWS implements ConferenceWSRemote {
	
	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ConferenceWS() {
        // TODO Auto-generated constructor stub
    }

	@Override
	@WebMethod
	public List<Conference> getAllConference(String field) {
		Query q = em.createQuery("SELECT c FROM Conference c WHERE c.field like :field AND c.dateFrom > :date");
		q.setParameter("field", field);
		q.setParameter("date", new Date());
		List<Conference> k = q.getResultList();
		System.out.println("Number of conferences: " + k.size());
		return k;
	}

}