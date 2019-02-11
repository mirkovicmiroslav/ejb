package rzk;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Ogla;
import model.OglasKorisnik;
import model.OglasPrijava;

/**
 * Session Bean implementation class OglasiBean
 */
@Stateful
@LocalBean
public class OglasiBean implements OglasiBeanRemote, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5120121909159754462L;

	@PersistenceContext
	EntityManager em;
	@EJB
	TimerJavljanjeBean tjb;
	@EJB
	StatsBean sb;
	private int userID;

	/**
	 * Default constructor.
	 */
	public OglasiBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String login(String username, String password) {
		Query q = em.createQuery("SELECT o FROM OglasKorisnik o WHERE o.username LIKE :us AND o.password LIKE :pass");
		q.setParameter("us", username);
		q.setParameter("pass", password);
		List<OglasKorisnik> users = q.getResultList();
		userID = users.get(0).getIdKorisnik();
		OglasKorisnik korisnik = new OglasKorisnik();
		korisnik.setIdKorisnik(userID);
		try {
			return users.get(0).getUsername();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return " ";
	}

	@Override
	@Interceptors(InterceptorPretraga.class)
	public List<Ogla> search(String text) {
		Query q = em.createQuery("SELECT o FROM Ogla o WHERE o.text LIKE :t");
		q.setParameter("t", text);
		List<Ogla> oglasi = q.getResultList();
		try {
			return oglasi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addOglas(String text) {
		try {
			Ogla oglas = new Ogla();
			oglas.setText(text);
			oglas.setBrojPregleda(0);
			OglasKorisnik user = em.find(OglasKorisnik.class, userID);
			oglas.setOglasKorisnik(user);
			em.persist(oglas);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Interceptors(InterceptorJavljanje.class)
	@Override
	public boolean respondingOglas(int idOglas, String text) {
		try {
			OglasPrijava oglas = new OglasPrijava();
			oglas.setText(text);
			oglas.setOgla(em.find(Ogla.class, idOglas));
			OglasKorisnik user = em.find(OglasKorisnik.class, userID);
			oglas.setOglasKorisnik(user);
			user.addOglasPrijava(oglas);
			em.persist(oglas);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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