package versions.v3;

import enumeration.Action;
import enumeration.Result;
import versions.Player;
import versions.PlayerDispenserImp;
import versions.Score;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
		this.dispenser = dispenser;

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
	synchronized public int hello(String name) throws RemoteException, InterruptedException, MalformedURLException {
		if (this.name[0] == null) {
			System.out.println("Joueur " + name + " identifié");
			this.name[0] = name;
			wait();
			return 0;
		} else if (this.name[1] == null) {
			System.out.println("Joueur " + name + " identifié");
			this.name[1] = name;
			System.out.println("Jeu démarré : " + this.name[0] + " vs " + this.name[1]);
			notify();
			try {
				dispenser.nextUrl();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return 1;
		} else {
			return -1;
		}
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
	synchronized public Score play(int id, Action action) throws InterruptedException {

		Result gameResult = null;
		boolean checkEndgame;

		if(this.action[0] != null && this.action[1] != null)  {
			this.action[0] = null;
			this.action[1] = null;
			turn ++;
		}
		if(this.action[0] == null && this.action[1] == null) {
			this.action[id] = action;
			wait();
		} else {
			this.action[id] = action;
			notify();
		}


		if (this.action[1].equals(this.action[0])) {
			this.result[1] = Result.NUL;
			this.result[0] = Result.NUL;
		} else if (this.action[1].equals(Action.PIERRE) && this.action[0].equals(Action.CISEAUX)) {
			this.result[1] = Result.GAGNE;
			this.result[0] = Result.PERDU;
			this.v[1] += 1;
		} else if (this.action[1].equals(Action.FEUILLE) && this.action[0].equals(Action.PIERRE)) {
			this.result[0] = Result.PERDU;
			this.result[1] = Result.GAGNE;
			this.v[1] += 1;
		} else if (this.action[1].equals(Action.CISEAUX) && this.action[0].equals(Action.FEUILLE)) {
			this.result[1] = Result.GAGNE;
			this.result[0] = Result.PERDU;
			this.v[1] += 1;
		} else {
			this.v[0] += 1;
			this.result[1] = Result.PERDU;
			this.result[0] = Result.GAGNE;
		}

		System.out.println(this.name[1] + "->" + this.action[1].name());
		System.out.println(this.name[0] + "->" + this.action[0].name());
		System.out.println(this.name[0] + " (" +  this.action[0].name() + ") vs "+ this.name[1]+ " ("+ this.action[1].name()+ ")");
		System.out.println(this.name[0] + " (" +  this.result[0] + ") - "+ this.name[1]+ " ("+ this.result[1] + ")");


		if (turn == MAXTURNS) {

			if (v[0] < v[1]) {
				gameResult = Result.GAGNE;
				System.out.println("GAGNE -> "+ this.name[1]);
				if (id == 1) {
					return new Score(this.action, result[id], gameResult);
				} else {
					return new Score(this.action, result[id], Result.PERDU);
				}
			} else if (v[0] == v[1]) {
				gameResult = Result.NUL;
				System.out.println("Égalité");
				return new Score(this.action, result[id], gameResult);
			} else {
				System.out.println("GAGNE -> "+ this.name[0]);
				gameResult = Result.PERDU;
				if (id == 1) {
					return new Score(this.action, result[id], gameResult);
				} else {
					return new Score(this.action, result[id], Result.GAGNE);
				}
			}
		}

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
		if(id==1){
			return this.name[0];
		} else {
			return this.name[1];
		}
	}
	
}
