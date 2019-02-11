package rzk;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebService;

import model.Conference;

@Remote
@WebService
public interface ConferenceWSRemote {
	
	public List<Conference> getAllConference(String field);
	
}