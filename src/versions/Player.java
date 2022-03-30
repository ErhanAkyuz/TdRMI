package versions;
import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import enumeration.Action;

//1. Définition de l'interface
public interface Player extends Remote {
	
	/**
	 * identification du joueur
	 * @param name
	 * nom avec lequel le joueur s'identifie
	 * @return
	 * id avec lequel le joueur peut réaliser des actions
	 */
	public int hello(String name) throws RemoteException, InterruptedException, MalformedURLException;
	
	/**
	 * action du joueur
	 * @param id
	 * id du joueur
	 * @param action
	 * action du joueur
	 * @return
	 * résultat de l'action
	 */
	public Score play(int id, Action action) throws RemoteException, InterruptedException;
	
	/**
	 * obtention du nom du joueur opposant
	 * @param id
	 * id du joueur
	 * @return
	 * le nom du joueur opposant
	 */
	public String getOpponentName(int id) throws RemoteException, InterruptedException;
	
}
