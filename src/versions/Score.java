package versions;

import java.io.Serializable;

import enumeration.Action;
import enumeration.Result;

public class Score implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Action[] action;	// les actions des 2 joueurs
	private Result result;		// le résultat du tour
	private Result gameResult;	// le résultat du jeu (null tant que le jeu n'est pas terminé)
	
	/**
	 * @param action
	 * actions des joueurs
	 * @param result
	 * résultat du tour
	 * @param gameResult
	 * résultat du jeu (doit être null tant que le jeu n'est pas terminé)
	 */
	public Score(Action[] action, Result result, Result gameResult) {
		this.action = action;
		this.result = result;
		this.gameResult = gameResult;
	}
	/**
	 * retourne les actions des joueurs
	 * @return
	 * actions des joueurs
	 */
	public Action[] getAction() {
		return this.action;
	}
	
	/**
	 * retourne le résultat du tour
	 * @return
	 * résultat du tour
	 */
	public Result getResult() {
		return this.result;
	}
	
	/**
	 * retourne le résultat du jeu
	 * @return
	 * résultat du jeu
	 */
	public Result getGameResult() {
		return this.gameResult;
	}

}
