import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.Event;
import rzk.PlanerBean;
import rzk.PlanerBeanRemote;
public class ClientApp {

	/**
	 * @param args
	 */

	private static Context initialContext;

	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";

	public static Context getInitialContext() throws NamingException {
		if (initialContext == null) {
			Properties properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);

			initialContext = new InitialContext(properties);
		}
		return initialContext;
	}
	
	private static String getLookupName() {
		String appName = "PlanerEAR";
		String moduleName = "PlanerEJB";
		String distinctName = "";
		String beanName = PlanerBean.class.getSimpleName();
		final String interfaceName = PlanerBeanRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" +
				distinctName    + "/" + beanName + "!" + interfaceName+"?stateful";
		return name;
	}

	public static void main(String[] args) {

		Context context = null;
		PlanerBeanRemote bean = null;
		try {
			context = getInitialContext();
			String lookupName = getLookupName();
			System.out.println("JNDI ime:   "+lookupName);
			bean = (PlanerBeanRemote) context.lookup(lookupName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		// login (stateful)
		Scanner scanner = new Scanner(System.in);
		System.out.println("Unesite svoje korisnicko ime: ");
		String ki = scanner.nextLine();
		System.out.println("Unesite svoju lozinku: ");
		String lozinka = scanner.nextLine();
		
		System.out.println("Vase korisnicko ime: " + bean.login(ki, lozinka));
		
		// createAnEvent (stateful)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateTo;
//		try {
//			dateTo = sdf.parse("2019-10-10");
//			if (bean.createAnEvent("Novi", new Date(), dateTo, 1)== true) {
//				System.out.println("Uspjesno dodan event!");
//			}else {
//				System.out.println("Neuspjesno dodan event! Pokusaj ponovo..");
//			}
//			
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		// searchEvents (stateful)
		try {
			List<Event> events = bean.searchEvents(sdf.parse("2017-11-24"));
			System.out.println("Dogadjaji(opisi) za odgovarajuci datum:" + events);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// getTypes (stateful)
		System.out.println("Tipovi dogadjaja:" + bean.getTypes());
		
	}
}
