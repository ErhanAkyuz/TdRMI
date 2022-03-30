package enumeration;

public class EnumInterfaceImpl {
	EnumValuesMethodInterface m; // référence vers méthode values() de enum

	// constructeur avec en paramètre une référence vers la méthode values() de enum
	public EnumInterfaceImpl(EnumValuesMethodInterface valuesMethod) {
		this.m = valuesMethod;
	}
	
	// obtention de la valeur énumérée à partir d'une chaîne
	/**
	 * @param s
	 * chaîne de caractère à partir de laquelle l'objet énuméré est obtenu
	 * @return
	 * l'objet énuméré ou null si la chaîne s ne correspond à aucune valeur
	 */
	public Object fromString(String s) {
		Object[] t = m.values(); // la méthode values() de l'enum est invoquée
    	int i = 0;
    	
    	while ((i < t.length) && (s.compareToIgnoreCase(t[i].toString()) != 0)) i++;

    	if (i < t.length)
    		return t[i];
    	else
    		return null;
    }
	
	// obtention de la valeur énumérée à partir d'un entier
	/**
	 * @param i
	 * entier à partir de duquel l'objet énuméré est obtenu
	 * @return
	 * l'objet énuméré ou null si la chaîne i ne correspond à aucune valeur
	 */
    public Object fromInt(int i) {
    	Object[] t = m.values();
    	int      j = 0;
    	Object   resul = null;
    	
    	while ((j < t.length) && (resul == null))
    		if (((Enum<?>) t[j]).ordinal() == i)
    			resul = t[j];
    		else
    			j++;
    	
    	return resul;    
    }
}