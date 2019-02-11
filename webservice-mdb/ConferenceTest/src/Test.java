import servis.ConferenceWSRemote;
import servis.ConferenceWSService;

public class Test {

	public static void main(String[] args) {
		ConferenceWSService cws = new ConferenceWSService();
		ConferenceWSRemote cwsr = cws.getConferenceWSPort();
		
		cwsr.getAllConference("IT");

	}
}