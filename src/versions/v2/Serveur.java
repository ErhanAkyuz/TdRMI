package versions.v2;

import versions.Player;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Serveur {

	public static void main(String[] args) throws RemoteException {
		// mise en place du service de nommage
		try {
			LocateRegistry.createRegistry(1099);
		}
		catch (RemoteException e) {
			LocateRegistry.getRegistry();
		}

//		System.setSecurityManager(new RMISecurityManager());

		try {
			// 4. instanciation d'un objet serveur
			Player player = new PlayerImp(null);

			// 5. publication auprès d'un service de nommage
			Naming.rebind("rmi://localhost/pfc",player);






//		    Naming.unbind("rmi://localhost/Calcul");
//		    System.out.println("Calcul désenregistré");
		}
		catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
}
