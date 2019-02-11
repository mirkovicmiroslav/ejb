package rzk;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface ConferenceBeanRemote {
	
	public void sendConference(String title, String country, String city, Date dateFrom, Date dateTo, String field);
	
}