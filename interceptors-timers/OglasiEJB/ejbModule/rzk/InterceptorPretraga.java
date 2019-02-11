package rzk;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Ogla;

public class InterceptorPretraga {

	@EJB
	StatsBean sbl;

	@AroundInvoke
	public Object interseptuj(InvocationContext ctx) throws Exception {
		List<Ogla> oglasi = (List<Ogla>) ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());
		for (Ogla o : oglasi) {
			sbl.updateMap(o);
			System.out.println("Presretnut pregled oglasa...");
		}
		return ctx.proceed();
	}
}