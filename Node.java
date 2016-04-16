package nodo;

public class Node {

		public enum TipusNode {
			AUTOR, CONFERENCIA, PAPER, TERME
		}
		
		private TipusNode tipus;
		private String nom;
		private int idNode;
		
		
		public Node(int id, String nom, TipusNode tipus){
			idNode = id;
			this.nom = nom;
			this.tipus = tipus;
		}

		public int get_id(){
			return idNode;
		}

		public String get_nom(){
			return nom;
		}

		public TipusNode get_tipus_node(){
			return tipus;
		}
		
		public void set_nom(String nom){
			this.nom = nom;
		}
		
}
