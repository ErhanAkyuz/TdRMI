package versions;

import java.rmi.Remote;
import java.rmi.RemoteException;

//1. Définition de l'interface
public interface PlayerDispenser extends Remote {
	
	/**
	 * Obtention de l'url sous laquelle un objet Player est enregistré
	 * @return
	 * retourne l'url
	 */
	public String getUrl() throws RemoteException;
}
