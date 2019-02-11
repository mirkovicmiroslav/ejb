package rzk;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/**
 * Session Bean implementation class TimerJavljanjeBean
 */
@Singleton
@LocalBean
@Startup
public class TimerJavljanjeBean {

	@Resource
	private TimerService timerService;
	@EJB
	StatsBean sb;

	@Timeout
	public void brojPregleda(Timer t) {
		System.out.println("Ukupno pregleda: " + sb.getBrJavljanja());
		sb.setBrJavljanja(0);
	}
	
	@PostConstruct
	private void init() {
		TimerConfig config = new TimerConfig();
        config.setPersistent(false);
        ScheduleExpression schedule = new ScheduleExpression();
        schedule.dayOfWeek("*");
        schedule.hour(0);
        schedule.minute(0);
        schedule.second(0);
        Timer timer = timerService.createCalendarTimer(schedule);
	}

}