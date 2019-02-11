import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rzk.EventTypeBean;
import rzk.EventTypeBeanRemote;
public class ClientApp2 {

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
		String beanName = EventTypeBean.class.getSimpleName();
		final String interfaceName = EventTypeBeanRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" +
				distinctName    + "/" + beanName + "!" + interfaceName;
		return name;
	}

	public static void main(String[] args) {

		Context context = null;
		EventTypeBeanRemote bean = null;
		try {
			context = getInitialContext();
			String lookupName = getLookupName();
			System.out.println("JNDI ime:   "+lookupName);
			bean = (EventTypeBeanRemote) context.lookup(lookupName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
}
