import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rzk.AccountBean;
import rzk.AccountBeanRemote;
public class ClientApp3 {

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
		String beanName = AccountBean.class.getSimpleName();
		final String interfaceName = AccountBeanRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" +
				distinctName    + "/" + beanName + "!" + interfaceName;
		return name;
	}

	public static void main(String[] args) {

		Context context = null;
		AccountBeanRemote bean = null;
		try {
			context = getInitialContext();
			String lookupName = getLookupName();
			System.out.println("JNDI ime:   "+lookupName);
			bean = (AccountBeanRemote) context.lookup(lookupName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		// createAccount (stateless)
		bean.createAccount("M", "M", "M", "M");
		
	}
}
