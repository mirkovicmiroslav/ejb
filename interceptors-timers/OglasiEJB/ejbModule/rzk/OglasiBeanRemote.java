package rzk;

import java.util.List;

import javax.ejb.Remote;

import model.Ogla;

@Remote
public interface OglasiBeanRemote {
	
	public String login(String username, String password);
	
	public List<Ogla> search(String text);
	
	public boolean addOglas(String text);
	
	public boolean respondingOglas(int idOglas, String text);
	
}