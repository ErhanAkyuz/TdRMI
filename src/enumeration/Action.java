package enumeration;

public enum Action {
	PIERRE, FEUILLE, CISEAUX;

	// instanciation de la classe EnumInterfaceImpl avec en paramètre une référence vers la méthode values() de enum
	private static EnumInterfaceImpl impl = new EnumInterfaceImpl(() -> {return Action.values();});
	
	/**
	 * retourne la valeur énumérée correspondant à une chaîne de caractères
	 * @param s
	 * chaîne de caractère à partir de laquelle l'objet énuméré est obtenu
	 * @return
	 * l'objet énuméré Action ou null si la chaîne s ne correspond à aucune valeur
	 */
	public static Action fromString(String s) {
		return (Action) impl.fromString(s);
    }

	/**
	 * retourne la valeur énumérée correspondant à un entier
	 * @param i
	 * entier à partir de duquel l'objet énuméré est obtenu
	 * @return
	 * l'objet énuméré Action ou null si la chaîne i ne correspond à aucune valeur
	 */
	public static Action fromInt(int i) {
		return (Action) impl.fromInt(i);
	}

}
