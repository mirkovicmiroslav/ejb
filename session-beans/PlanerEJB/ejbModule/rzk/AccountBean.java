package rzk;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.User;

/**
 * Session Bean implementation class AccountBean
 */
@Stateless
@LocalBean
public class AccountBean implements AccountBeanRemote {

	@PersistenceContext
	EntityManager em;

	public AccountBean() {
	}

	@Override
	public boolean createAccount(String email, String password, String firstName, String lastName) {
		try {
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			em.persist(user);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@PostConstruct
	public void created() {
		System.out.println("Kreiran stateless: " + (new java.util.Date()));
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Unisten stateless: " + (new java.util.Date()));
	}

}