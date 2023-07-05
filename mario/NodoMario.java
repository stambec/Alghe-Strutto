package mario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class NodoMario{
	private HashMap<Integer,NodoMario> childs;	//private ArrayList<NodoMario> figli;
	private String info;
	private NodoMario father;

	
	public NodoMario(String info){
		this.info=info;
		this.childs=new HashMap<>();
	}
	public NodoMario(String info,NodoMario father){
		this(info);
		this.father=father;
	}
	//public NodoMario(NodoMario father){	//	this.father=father;	//	this.childs=new HashMap<>();	//}

	
	@Override
	public boolean equals(Object other) {	//NOTA: serve? NOTA2: è equals shallow
		if (this==other)	return true;
		if (!(other instanceof NodoMario)) return false;
		if(((NodoMario)other).get()==this.get())	return true;
		return false;
	}
	
	@Override
	public String toString() {
		if(hasFather())	return "  -nodo '"+this.get()+"'\t(figlio di: "+father.get()+") ["+totalChildren()+" figli]";
		else			return "  -nodo '"+this.get()+"' ["+totalChildren()+" figli]";
	}
	
	
	
	public void show() {
		System.out.println(toString());
	}
	
	//just for debug
	public HashMap<Integer,NodoMario> getMap() {
		return this.childs;
	}
//////////////////////
	
	
	
	
	public NodoMario asNode() {
		return new NodoMario(this.get());
	}
	
	public String get(){				// IX
		return info;
	}

	public void set(String info){		// X
		this.info=info;
	}
	
	public boolean isExternal() {
		if (totalChildren()==0)	return true;
		return false;
	}
	
	public boolean isLeaf() {
		for (NodoMario figlio : getChildren()) {
			if ( !figlio.isExternal() )	return false;
		}
		return true;
	}
	
	
	public boolean hasFather(){
		return (father!=null);
	}
	public NodoMario getFather(){
		return father;			//returns null if root/unfathered
	}
	public void setFather(NodoMario father) {
		this.father=father;
	}

	
	
	public void setChild(NodoMario newChild, int index) throws OverWriteException {
		if (childs.get(index)!=null)	throw new OverWriteException(childs.get(index),newChild);
		this.childs.put(index,newChild);
		newChild.setFather(this);
	}
	public void setChild(String newChildInfo, int index) throws OverWriteException {
		setChild(new NodoMario(newChildInfo),index);
	}
	public NodoMario getChild(int index) throws ChildDoesntExistException{
		if(childs.get(index)==null)throw new ChildDoesntExistException(this,index);
		return childs.get(index);
	}
	
	

	
	
	public int totalChildren(){
		return childs.size();
	}

	public ArrayList<NodoMario> getChildren(){
		ArrayList<NodoMario> out= new ArrayList<>(childs.values());
		return out;
	}
	
	
	public ArrayList<NodoMario> getInternalChildren(){						// VII
		ArrayList<NodoMario> result=new ArrayList<>();
		for (NodoMario figlio : getChildren()) {
			if(figlio.totalChildren()>0)		result.add(figlio);
		}
		return result;
	}

	public ArrayList<String> getInternalChildrenInfo(){					// VIII
		ArrayList<String> result=new ArrayList<>();
		for(NodoMario nodo : getInternalChildren())		result.add(nodo.get());
		return result;
	}
}