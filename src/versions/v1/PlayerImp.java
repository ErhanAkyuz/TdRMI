package versions.v1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import enumeration.Action;
import enumeration.Result;
import versions.Player;
import versions.PlayerDispenserImp;
import versions.Score;

//2. Implémentation de l'interface
public class PlayerImp extends UnicastRemoteObject implements Player {
	private static final long serialVersionUID = 1L;
	
	private static final int MAXTURNS = 3;             // nombre maximum de tours
	
	private int                turn = 1;               // compteur de tours
	private PlayerDispenserImp dispenser = null;       // distributeur d'url
	
	private String[]           name   = new String[2]; // noms des 2 joueurs
	private Action[]           action = new Action[2]; // actions des 2 joueurs
	private Result[]           result = new Result[2]; // résultats des 2 joueurs
	private int[]              v      = new int[2];    // nombre de victoires des 2 joueurs
	private int                nbPlayers = 1;          // nombre de joueurs identifiés



	
	/**
	 * @param dispenser
	 * cet objet est nécessaire pour informer le serveur de la nécessité de créer un nouvel objet Player
	 */
	public PlayerImp(PlayerDispenserImp dispenser) throws RemoteException {
		this.name[0] = "computer";
		this.v[1] = 0;
		this.v[0] = 0;
		turn = 1;
    }


	/**
	 * identification du joueur
	 * @param name
	 * nom avec lequel le joueur s'identifie
	 * @return
	 * id avec lequel le joueur peut réaliser des actions
	 */
	public int hello(String name) throws RemoteException, InterruptedException {
		this.name[1] = name;
		System.out.println("Joueur " + name + " identifié");
		System.out.println("Jeu démarré : "+ this.name[1] + " vs "+ getOpponentName(0));
        return 1;
	}
	
	/**
	 * action du joueur
	 * @param id
	 * id du joueur
	 * @param action
	 * action du joueur
	 * @return
	 * résultat de l'action
	 */

	public Score play(int id, Action action) throws InterruptedException {

		Result gameResult = null;
		boolean checkEndgame;

		this.action[id] = action;
		this.action[0] = Action.fromInt(new Random().nextInt(Action.values().length));



		if (this.action[id].equals(this.action[0])) {
			this.result[id] = Result.NUL;
			this.result[0] = Result.NUL;
		} else if (this.action[id].equals(Action.PIERRE) && this.action[0].equals(Action.CISEAUX)) {
			this.result[id] = Result.GAGNE;
			this.result[0] = Result.PERDU;
			this.v[id] += 1;
		} else if (this.action[id].equals(Action.FEUILLE) && this.action[0].equals(Action.PIERRE)) {
			this.result[0] = Result.PERDU;
			this.result[id] = Result.GAGNE;
			this.v[id] += 1;
		} else if (this.action[id].equals(Action.CISEAUX) && this.action[0].equals(Action.FEUILLE)) {
			this.result[id] = Result.GAGNE;
			this.result[0] = Result.PERDU;
			this.v[id] += 1;
		} else {
			this.v[0] += 1;
			this.result[id] = Result.PERDU;
			this.result[0] = Result.GAGNE;
		}

		System.out.println(this.name[id] + "->" + this.action[id].name());
		System.out.println(this.name[0] + " (" +  this.action[0].name() + ") vs "+ this.name[1]+ " ("+ this.action[1].name()+ ")");
		System.out.println(this.name[0] + " (" +  this.result[0] + ") - "+ this.name[1]+ " ("+ this.result[1] + ")");


		if (turn == MAXTURNS) {

			if (v[0] < v[id]) {
				this.v[id] = 0;
				this.v[0] = 0;
				turn = 1;
				gameResult = Result.GAGNE;
				System.out.println("GAGNE -> "+this.name[id]);
				return new Score(this.action, result[id], gameResult);
			} else if (v[0] == v[id]) {
				this.v[id] = 0;
				this.v[0] = 0;
				turn = 1;
				gameResult = Result.NUL;
				System.out.println("Égalité");
				return new Score(this.action, result[id], gameResult);
			} else {
				this.v[id] = 0;
				this.v[0] = 0;
				System.out.println("GAGNE -> "+this.name[0]);
				gameResult = Result.PERDU;

				turn = 1;

				return new Score(this.action, result[id], gameResult);
			}
		}


		turn++;
		return new Score(this.action, result[id], null );


	}
	
	/**
	 * obtention du nom du joueur opposant
	 * @param id
	 * id du joueur
	 * @return
	 * le nom du joueur opposant
	 */
	public String getOpponentName(int id) throws RemoteException, InterruptedException {
		return this.name[id];
	}
	
}
