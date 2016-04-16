import java.util.ArrayList;


public class Graf {
	
	
	private int paperAutor[][];
	private int paperConferencia[][];
	private int paperTerme[][];

	private ArrayList<Node> paper;
	private ArrayList<Node> conferencia;
	private ArrayList<Node> autor;
	private ArrayList<Node> terme;
		
	
	public Graf()
	{
		paperAutor = new int[10][10];
		paperConferencia = new int[10][10];
		paperTerme = new int[10][10];
		
		paper = new ArrayList<Node>();
		conferencia = new ArrayList<Node>();
		autor = new ArrayList<Node>();
		terme = new ArrayList<Node>();
	
	}
	
	
	public int afegir_node(Node node)
	{
		Node.TipusNode tipusNode = node.get_tipus_node();
		switch (tipusNode) {
		case AUTOR: 
			if(!(this.autor.contains(node))) this.autor.add(node);
			break;
		case PAPER: 
			if(!(this.paper.contains(node))) this.paper.add(node);
			break;
		case TERME:
			if(!(this.terme.contains(node))) this.terme.add(node);
			break;
		case CONFERENCIA:
			if(!(this.conferencia.contains(node))) this.conferencia.add(node);
			break;
		}
		return -1;
	}
	
	public int afegir_aresta(Node node1, Node node2)
	{
		int i = node1.get_id();
		int j = node2.get_id();
		if (i >= 0 && j >= 0) {
			Node.TipusNode Tn1 = node1.get_tipus_node();
			Node.TipusNode Tn2 = node2.get_tipus_node();
			if (Tn1 == Node.TipusNode.PAPER || Tn2 == Node.TipusNode.PAPER) {
				if (Tn1 == Node.TipusNode.AUTOR || Tn2 == Node.TipusNode.AUTOR) {
					if (i<paperAutor.length && j< paperAutor.length) {
						this.paperAutor[i][j] = 1;
						this.paperAutor[j][i] = 1;
					}
				}
				else if (Tn1 == Node.TipusNode.TERME || Tn2 == Node.TipusNode.TERME) {
					if (i<paperTerme.length && j< paperTerme.length) {
						this.paperTerme[i][j] = 1;
						this.paperTerme[j][i] = 1;
					}
				}
				else if (Tn1 == Node.TipusNode.CONFERENCIA || Tn2 == Node.TipusNode.CONFERENCIA) {
					if (i<paperConferencia.length && j< paperConferencia.length) {
						this.paperConferencia[i][j] = 1;
						this.paperConferencia[j][i] = 1;
					}
				}
			}
			
			return 1;
		}
		else return 0;
	}
	
	public int eliminar_node(Node node)
	{
		int i = node.get_id();
		if (i >= 0) {
			Node.TipusNode Tn = node.get_tipus_node();
			switch (Tn) {
			case AUTOR: 
				if(this.autor.contains(node)) this.autor.remove(node);
				break;
			case PAPER: 
				if(this.paper.contains(node)) this.paper.remove(node);
				break;
			case TERME:
				if(this.terme.contains(node)) this.terme.remove(node);
				break;
			case CONFERENCIA:
				if(this.conferencia.contains(node)) this.conferencia.remove(node);
				break;
			}
			return 1;
		}
		else return -1;
	}
	
	
	public int eliminar_aresta(Node node1, Node node2)
	{
		int i = node1.get_id();
		int j = node2.get_id();
		if (i >= 0 && j >= 0) {
			Node.TipusNode Tn1 = node1.get_tipus_node();
			Node.TipusNode Tn2 = node2.get_tipus_node();
			if (Tn1 == Node.TipusNode.PAPER || Tn2 == Node.TipusNode.PAPER) {
				if (Tn1 == Node.TipusNode.AUTOR || Tn2 == Node.TipusNode.AUTOR) {
					if (i<paperAutor.length && j< paperAutor.length) {
						this.paperAutor[i][j] = -1;
						this.paperAutor[j][i] = -1;
					}
				}
				else if (Tn1 == Node.TipusNode.TERME || Tn2 == Node.TipusNode.TERME) {
					if (i<paperTerme.length && j< paperTerme.length) {
						this.paperTerme[i][j] = -1;
						this.paperTerme[j][i] = -1;
					}
				}
				else if (Tn1 == Node.TipusNode.CONFERENCIA || Tn2 == Node.TipusNode.CONFERENCIA) {
					if (i<paperConferencia.length && j< paperConferencia.length) {
						this.paperConferencia[i][j] = -1;
						this.paperConferencia[j][i] = -1;
					}
				}
			}
			
			return 1;
		}
		else return 0;
	}
	
	public int actualizar_node(Node node)
	{
		int i = node.get_id();
		if (i >= 0) {
			Node.TipusNode Tn = node.get_tipus_node();
			switch (Tn) {
			case AUTOR: 
				this.autor.remove(i);
				this.autor.add(node);
				break;
			case PAPER: 
				this.paper.remove(i);
				this.paper.add(node);
				break;
			case TERME:
				this.terme.remove(i);
				this.terme.add(node);
				break;
			case CONFERENCIA:
				this.conferencia.remove(i);
				this.conferencia.add(node);
				break;
			}
			return 1;
		}
		else return 0;
	}
	
	public int[][] get_paper_autor()
	{
		return this.paperAutor;
	}
	
	public int[][] get_paper_conferencia()
	{		
		return this.paperConferencia;
	}
	
	public int[][] get_paper_terme()
	{		
		return this.paperTerme;
	}
	
	public ArrayList<Node> get_paper()
	{
		return this.paper;	
	}
	
	public ArrayList<Node> get_conferencia()
	{
		return this.conferencia;	
	}
	
	
	public ArrayList<Node> get_autor()
	{
		return this.autor;	
	}
	
	public ArrayList<Node> get_terme()
	{
		return this.terme;	
	}
	
	
	public Node get_node(int i, Node.TipusNode tipus)
	{
		switch(tipus) {
		case AUTOR:
			if(i < autor.size()) return autor.get(i);
		case CONFERENCIA:
			if(i < conferencia.size()) return conferencia.get(i);
		case PAPER:
			if(i < paper.size()) return paper.get(i);
		case TERME:
			if(i < terme.size()) return terme.get(i);
		default:
			return null;
		}
	}

}
