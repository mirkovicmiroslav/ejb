package rzk;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ogla;

/**
 * Session Bean implementation class StatsBean
 */
@Singleton
@LocalBean
public class StatsBean {

	@PersistenceContext
	EntityManager em;
	private HashMap<Integer, Integer> brojPregleda;
	int brJavljanja = 0;

	/**
	 * Default constructor.
	 */
	public StatsBean() {
		brojPregleda = new HashMap<>();
	}

	public void updateMap(Ogla o) {
		if (brojPregleda.containsKey(o.getIdOglas())) {
			brojPregleda.put(o.getIdOglas(), brojPregleda.get(o.getIdOglas()) + 1);
		} else {
			brojPregleda.put(o.getIdOglas(), 1);
		}
	}

	public void javljanje() {
		setBrJavljanja(brJavljanja + 1);
	}

	@Schedule(minute = "*/15", hour = "*")
	public void updateDB() {
		for (Entry<Integer, Integer> entry : brojPregleda.entrySet()) {
			Ogla o = em.find(Ogla.class, entry.getKey());
			o.setBrojPregleda(o.getBrojPregleda() + entry.getValue());
			em.merge(o);
			System.out.println("Promjenjen broj pregleda na: " + o.getBrojPregleda());
		}
	}

	public HashMap<Integer, Integer> getbrojPregleda() {
		return brojPregleda;
	}

	public void setbrojPregleda(HashMap<Integer, Integer> brojPregleda) {
		this.brojPregleda = brojPregleda;
	}

	public int getBrJavljanja() {
		return brJavljanja;
	}

	public void setBrJavljanja(int brJavljanja) {
		this.brJavljanja = brJavljanja;
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