package rzk;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InterceptorJavljanje {

	@EJB
	StatsBean sbl;

	@AroundInvoke
	public Object inters(InvocationContext ctx) throws Exception {
		sbl.javljanje();
		System.out.println("Presretnuto javljanje na oglas oglasa...");
		System.out.println("Trenutno javljanja na oglas: " + sbl.getBrJavljanja());
		return ctx.proceed();
	}

}
