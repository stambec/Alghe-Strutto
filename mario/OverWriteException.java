package mario;

public class OverWriteException extends Exception {

	private static final long serialVersionUID = 880185994883173436L;

	public OverWriteException(NodoMario nodo1,NodoMario nodo2) {
		super("\nCauzione! Si è tentato di sovrascrivere nodo '"+nodo1.get()+"' con nodo '"+nodo2.get()+"'.\nUsare invece il comando apposito '.changeNodeInfo(NodoMario nodo)' ");
	}
}
