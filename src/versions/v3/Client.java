package versions.v3;

import enumeration.Action;
import versions.Player;
import versions.PlayerDispenser;
import versions.PlayerDispenserImp;
import versions.Score;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws RemoteException, InterruptedException, MalformedURLException, NotBoundException {
		Scanner  kb     = new Scanner(System.in);
		String   line;		
		Player   player;	    // objet
		String   name;			// nom du joueur
		String   opponentName;  // nom de l'opposant
		int      id;			// id du joueur
		Score    r = null;		        // résultat
		PlayerDispenser playerDispenser = null;
		String url;


		playerDispenser = (PlayerDispenser) Naming.lookup("rmi://localhost/pfc");
		if(playerDispenser != null) {

		player = (Player) Naming.lookup(playerDispenser.getUrl());

		System.out.println("Saisir le nom du joueur");
		name = kb.next();
		id = player.hello(name);


		System.out.println(name + " VS " + player.getOpponentName(id));

		while (r == null || r.getGameResult() == null) {

			System.out.println("pierre, feuille ou ciseaux ?");
			line = kb.next();

			Action action = Action.fromString(line);

			r = player.play(id, action);


			if (id == 0) {
				System.out.println(r.getAction()[id] + " / " + r.getAction()[0] + " - " + r.getResult());
			} else {
				System.out.println(r.getAction()[id] + " / " + r.getAction()[1] + " - " + r.getResult());
			}
		}

		System.out.println(r.getGameResult());
	}
	}

}
