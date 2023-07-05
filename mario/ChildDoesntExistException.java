package mario;

public class ChildDoesntExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7542250243844123916L;

	public ChildDoesntExistException(NodoMario father,int index) {
		super("Attenzione! Tentato accesso ad inesistente nodo figlio "+index+"esimo di  nodo '"+father.get()+"'.");
	}
}
