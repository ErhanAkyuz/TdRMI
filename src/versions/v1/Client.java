package versions.v1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import enumeration.Action;
import versions.Player;
import versions.Score;

public class Client {
	public static void main(String[] args) throws RemoteException, InterruptedException, MalformedURLException, NotBoundException {
		Scanner  kb     = new Scanner(System.in);
		String   line;		
		Player   player;	    // objet
		String   name;			// nom du joueur
		String   opponentName;  // nom de l'opposant
		int      id;			// id du joueur
		Score    r = null;		        // résultat
		
		String url;

		id = 1;
		//init
		player = (Player) Naming.lookup("rmi://localhost/pfc");

		System.out.println("Saisir un nom de joueur");
		name = kb.next();

		player.hello(name);
		System.out.println(name + " VS " + player.getOpponentName(0));


		while(r == null || r.getGameResult() == null){

				System.out.println("pierre, feuille ou ciseaux ?");
				line = kb.next();

				Action action = Action.fromString(line);

				r = player.play(id, action);
				System.out.println(action +  " / " + r.getAction()[0] + " - "+ r.getResult());
		}

		System.out.println(r.getGameResult());



			//System.out.println(r.getResult());




	}

}
