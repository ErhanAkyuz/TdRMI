package versions;


import versions.v3.PlayerImp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//2. Implémentation de l'interface
public class PlayerDispenserImp extends UnicastRemoteObject implements PlayerDispenser {
	private static final long serialVersionUID = 1L;
	
	private String url = null; // url construite
	private int nbPlayer = 0;


	public PlayerDispenserImp() throws RemoteException, MalformedURLException {
		super();
		String url = "rmi://localhost/player" + nbPlayer;
		Player player = new PlayerImp(this);
		Naming.rebind(url, player);
		this.url = url;

	}

	/**
	 * obtention de l'URL sous laquelle un objet Player est enregistré
	 * @return
	 * retourne l'url
	 */
	public String getUrl() throws RemoteException {
        return url;
    }

	/**
	 * définit la nouvelle URL pour délivrer un objet Player
	 * @param url
	 * url d'un objet Player
	 */
	public void setUrl(String url) throws RemoteException {
		this.url = url;
	}

	/**
	 * informe le serveur qu'un nouvel objet Player doit être créé et une nouvelle URL générée
	 */
	synchronized public void nextUrl() throws RemoteException, MalformedURLException {
		nbPlayer++;
		String url = "rmi://localhost/pfc" + nbPlayer;
		System.out.println("coucou");
		Player player = new PlayerImp(this);
		Naming.rebind(url, player);
		this.setUrl(url);
		notify();
	}

	/**
	 * attend de devoir générer une une nouvelle URL
	 */
	synchronized public void waitForNewUrl() throws RemoteException, InterruptedException {
        wait();
	}

}
