package mario;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;


//immediati:
//		inserire radice da info 							 I
//		restituire info di nodo 							IX
//		cambiare info di nodo 								 X
//		restituire radice 									XI

//da pensarci:
//		Inserire V figlio di U in indice ix 				II
//		restituire numero nodi 								VI
//		restituire il padre di nodo 					   XII
//		restituire numero foglie albero					  XIII
//		restituire altezza albero						   XIV
//		restituire livello nodo     						XV

//Algo easyyyss:
//		Da nodo interno, restituire figli nodi interni	   VII
//		Da nodo interno, restituire info figli interni 	  VIII

//Deep Algos:
//		Inserire nuova radice tc vecchia figlia in ix      III
//		Depth View (anticipata)								IV
//		Broad View											 V

import java.util.Queue;
public class AlberoMario{
	private NodoMario root;
	private int mariety;
	private int totalNodes=0;

	//public AlberoMario() {		//nota: fissare mariety a meno uno(conseguenti controlli) per NESSUN LIMITE 
	//	this(-1);				//MA FORSE NO: dopotutto è albero M-ARIO
	//	debug.debug("Caution. Albero settato senza marietà");
	//}							
	
	public AlberoMario(int mariety){			
		this.mariety=mariety;			
	}
	
	@Override
	public String toString() {
		if(this.root==null)	return "Albero vuoto (di"+this.mariety+").";
		else {
			return "Albero m-ario (di m="+this.mariety+"). Alto: "+getHeight()+"   lista nodi:";
		}
	}
	
	public void debug() {		//serve solo per debug
		System.out.println("---");
		System.out.println(toString());
		depthView();
		broadView();
		System.out.println("---\n");
	}
	
	//public void setMariety(int mariety){	//bannato: con downgrade? //	this.mariety=mariety; //}

	public boolean isEmpty(){
		return (root==null);
	}
	public int getTotalNodes(){					// VI
		return totalNodes;
	}
	public int getTotalLeaves(){
		return 24; //design Bruto:
	}
	public int getHeight(){						// XIV		//mica c'è un modo non/meno algoritmico?
		if (this.isEmpty())	return 0;
		int counterLvl=0;
		Queue<NodoMario> frangia=new LinkedList<NodoMario>();	//chiamiamola frangia per richiamare le lez.
		ArrayList<NodoMario> nextlevel=new ArrayList<>();
		frangia.add(this.root);
		while(true){
			while(!frangia.isEmpty())				nextlevel.add(frangia.remove());
			if (nextlevel.isEmpty()) 			return counterLvl;
			for (NodoMario nextnodo : nextlevel)	frangia.addAll(nextnodo.getChildren());
			nextlevel.clear();
			counterLvl++;
		}
	}
	
	

	public void insertRoot(String info) throws RootAlreadyExistingException{		// I
		if (this.root!=null)	throw new RootAlreadyExistingException();
		this.root=new NodoMario(info);
		totalNodes++;
	}
	public void replaceRoot(NodoMario newRoot,int index) throws IndexBiggerThanMarietyException, OverWriteException{// III
		if (index>=this.mariety)		throw new IndexBiggerThanMarietyException();
		NodoMario tmp=this.root;
		this.root=newRoot.asNode();	//Nota incredibile:se newRoot NON nodo puro>>merge fra due alberi. OverWrite!
		this.root.setChild(tmp,index);
	}
	public NodoMario getRoot(){					// XI
		return root;	
	}
	
	
	public void insertNode(NodoMario father,String childInfo,int index) throws OverWriteException{	// II	//riconoscimento per hash// IDEA: hash è mappa????design per hash:for (char e : hash.parse()){}
		NodoMario child= new NodoMario(childInfo);		//cerca father NON CE NE DI BISOGNO! father come arg
		father.setChild(child,index);
		totalNodes++;
	}
	public NodoMario getNodeFather(NodoMario child){		//risalire da hashmappa? o ogni nodo ha puntatore al padre? //design nodo ha puntatore al padre:
		return child.getFather(); //ritorna null se non lo ha
	}
	public int getNodeHeight(NodoMario point){					// XV		//se nodo avesse puntatore a padre, top: si risale fino a root, contando design puntatore a padre:
		int counter=0;
		while(point.hasFather()){
			point=point.getFather();
			counter++;
		}
		return counter;
	}
	public String getNodeInfo(NodoMario node) {							// IX
		return node.get();							//ridondante
	}
	public void changeNodeInfo(NodoMario node, String newInfo) {		// X
		node.set(newInfo);							//ridondante
	}

	
	
	
	public ArrayList<NodoMario> getInternalChildren(NodoMario query) {	   // VII
		return query.getInternalChildren();		//ridondanza
	}
	public ArrayList<String> getInternalChildrenInfo(NodoMario query){ 	   // VIII
		return query.getInternalChildrenInfo();	//ridondanza
	}

		
	
	
	public void depthView(){	
		if(this.root==null)	return;
		System.out.println("DEPTHVIEW");
		depthViewer(this.root);
	}
	private void depthViewer(NodoMario query){
		System.out.println(query);
		for (NodoMario child : query.getChildren())	depthViewer(child); 	//for(Integer ix : query.getMap().keySet()) {//	System.out.print("\t\t-child no "+ix+" ");//	DepthViewer(query.getMap().get(ix));//}
	}

	public void broadView() {						// V
		if (isEmpty()) return;
		System.out.println("BROADVIEW");
		Queue<NodoMario> flush=new LinkedList<>();
		NodoMario tmp;
		flush.add(this.root);
		while(!flush.isEmpty()){
			tmp=flush.remove();
			System.out.println(tmp);
			for (NodoMario child : tmp.getChildren()){
				flush.add(child);
			}
		}

	}
}