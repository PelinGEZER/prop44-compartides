package nodo;

import nodo.Node.TipusNode;

public class TestNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		TipusNode tn = TipusNode.AUTOR;
		Node d = new Node(123, "Pepe", tn);
		System.out.println(d.get_id() + d.get_nom() + d.get_tipus_node());
		d.set_nom("José");
		System.out.println(d.get_id() + d.get_nom() + d.get_tipus_node());
		tn = TipusNode.PAPER;
		Node d1 = new Node(124, "ogloc", tn);
		d1.set_nom("ogloc");
		System.out.println(d1.get_nom());
		d1.set_nom(d.get_nom());
		d = d1;
		System.out.println(d.get_id() + d.get_nom() + d.get_tipus_node());
		d.set_nom("ogloc");
		System.out.println(d.get_id());
		System.out.println(d1.get_nom() + d.get_tipus_node());
	}

}
