package enumeration;

public enum Result {
	GAGNE, PERDU, NUL;

	// instanciation de la classe EnumInterfaceImpl avec en paramètre une référence vers la méthode values() de enum
	private static EnumInterfaceImpl impl = new EnumInterfaceImpl(() -> {return Result.values();});

	/**
	 * retourne la valeur énumérée correspondant à une chaîne de caractères
	 * @param s
	 * chaîne de caractère à partir de laquelle l'objet énuméré est obtenu
	 * @return
	 * l'objet énuméré Result ou null si la chaîne s ne correspond à aucune valeur
	 */
	public static Result fromString(String s) {
		return (Result) impl.fromString(s);
    }

	/**
	 * retourne la valeur énumérée correspondant à un entier
	 * @param i
	 * entier à partir de duquel l'objet énuméré est obtenu
	 * @return
	 * l'objet énuméré Result ou null si la chaîne i ne correspond à aucune valeur
	 */
	public static Result fromInt(int i) {
		return (Result) impl.fromInt(i);
	}

}
