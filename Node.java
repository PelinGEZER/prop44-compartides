package com.company;

/**
 * Created by Edgar Perez on 17/04/2016.
 */

public class Node {

		public enum TipusNode {
			AUTOR, CONFERENCIA, PAPER, TERME
		}
		
		private TipusNode tipus;
		private String nom;
		private int id;

		public Node(int id, String nom, TipusNode tipus){
			this.id = id;
			this.nom = nom;
			this.tipus = tipus;
		}

		public int get_id(){
			return id;
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

		public void set_id(int id) {
			this.id = id;
		}
}
