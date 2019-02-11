package rzk;

import java.util.List;

import javax.ejb.Remote;

import model.EventType;

@Remote
public interface EventTypeBeanRemote {

	public List<EventType> getTypes();
}