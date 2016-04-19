package com.company;

import java.util.ArrayList;

import static com.company.Node.TipusNode.*;

/**
 * Created by Marc Garrido on 17/04/2016. Col·laboracio Ruben Bagan Benavides
 */

public class Graf {
    private Matriu paperAutor;
    private Matriu paperConferencia;
    private Matriu paperTerme;

    private ArrayList<Node> paper;
    private ArrayList<Node> conferencia;
    private ArrayList<Node> autor;
    private ArrayList<Node> terme;


    public Graf()
    {
        paperAutor = new Matriu();
        paperConferencia = new Matriu();
        paperTerme = new Matriu();

        paper = new ArrayList<Node>();
        conferencia = new ArrayList<Node>();
        autor = new ArrayList<Node>();
        terme = new ArrayList<Node>();
    }

    // Falta pre,post,coste
    public boolean afegir_node(Node.TipusNode tipusNode, String nomNode)
    {
        int id = 0;
        switch (tipusNode){
            case AUTOR:
                id = this.autor.size();
                break;
            case CONFERENCIA:
                id = this.conferencia.size();
                break;
            case TERME:
                id = this.terme.size();
                break;
            case PAPER:
                id = this.paper.size();
                break;
            default:
                return false;
        }
        return afegir_node(new Node(id, nomNode, tipusNode));
    }

    public boolean afegir_node(Node node)
    {
        switch (node.get_tipus_node()) {
            case AUTOR:
                if (this.autor.add(node)) {
                    this.paperAutor.afegir_columna();
                    return true;
                }
                break;
            case PAPER:
                if (this.paper.add(node)) {
                    this.paperAutor.afegir_fila();
                    this.paperTerme.afegir_fila();
                    this.paperConferencia.afegir_fila();
                    return true;
                }
                break;
            case TERME:
                if (this.terme.add(node)) {
                    paperTerme.afegir_columna();
                    return true;
                }
                break;
            case CONFERENCIA:
                if (this.conferencia.add(node)) {
                    this.paperConferencia.afegir_columna();
                    return true;
                }
                break;
        }
        return false;
    }

    // Node Origen sempre es paper, perque les matrius son Paper x [Autor,Conferencia,Terme]
    public int afegir_aresta(Node nodeOrigen, Node nodeDesti) {
        switch (nodeDesti.get_tipus_node()) {
            case AUTOR:
                this.paperAutor.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 1.0);
                break;
            case CONFERENCIA:
                this.paperConferencia.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 1.0);
                break;
            case TERME:
                this.paperTerme.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 1.0);
                break;
            default:
                return -1;
        }
        return 0;
    }

    public int eliminar_node(Node node) {
        switch (node.get_tipus_node()) {
            case AUTOR:
                if (this.autor.remove(node.get_id()) != null) {
                    this.paperAutor.eliminar_columna(node.get_id());
                    for (int i = node.get_id(); i < autor.size(); ++i) {
                        autor.get(i).set_id(i);
                    }
                    return 0;
                }
            case PAPER:
                if (this.paper.remove(node.get_id()) != null) {
                    this.paperAutor.eliminar_fila(node.get_id());
                    this.paperTerme.eliminar_fila(node.get_id());
                    this.paperConferencia.eliminar_fila(node.get_id());
                    for (int i = node.get_id(); i < paper.size(); ++i) {
                        paper.get(i).set_id(i);
                    }
                    return 0;
                }
            case TERME:
                if (this.terme.remove(node.get_id()) != null) {
                    this.paperTerme.eliminar_columna(node.get_id());
                    for (int i = node.get_id(); i < terme.size(); ++i) {
                        terme.get(i).set_id(i);
                    }
                    return 0;
                }
            case CONFERENCIA:
                if (this.conferencia.remove(node.get_id()) != null) {
                    this.paperConferencia.eliminar_columna(node.get_id());
                    for (int i = node.get_id(); i < conferencia.size(); ++i) {
                        conferencia.get(i).set_id(i);
                    }
                    return 0;
                }
        }
        return -1;
    }

    public int actualizar_node(Node node) {
        switch (node.get_tipus_node()) {
            case AUTOR:
                this.autor.get(node.get_id()).set_nom(node.get_nom());
                break;
            case PAPER:
                this.paper.get(node.get_id()).set_nom(node.get_nom());
                break;
            case TERME:
                this.terme.get(node.get_id()).set_nom(node.get_nom());
                break;
            case CONFERENCIA:
                this.conferencia.get(node.get_id()).set_nom(node.get_nom());
                break;
            default:
                return -1;
        }
        return 0;
    }

    public int eliminar_aresta(Node nodeOrigen, Node nodeDesti) {
        switch (nodeDesti.get_tipus_node()) {
            case AUTOR:
                this.paperAutor.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 0.0);
                break;
            case CONFERENCIA:
                this.paperConferencia.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 0.0);
                break;
            case TERME:
                this.paperTerme.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 0.0);
                break;
            default:
                return -1;
        }
        return 0;
    }

    public Matriu get_paper_autor() {
        return this.paperAutor;
    }

    public Matriu get_paper_conferencia() {
        return this.paperConferencia;
    }

    public Matriu get_paper_terme() {
        return this.paperTerme;
    }

    public ArrayList<Node> get_paper() {
        return this.paper;
    }

    public ArrayList<Node> get_conferencia() {
        return this.conferencia;
    }


    public ArrayList<Node> get_autor() {
        return this.autor;
    }

    public ArrayList<Node> get_terme() {
        return this.terme;
    }

    public void set_paper_autor(double[][] matriuAdjecencia) {
        paperAutor.set_data(matriuAdjecencia);
    }

    public void set_paper_terme(double[][] matriuAdjecencia) {
        paperTerme.set_data(matriuAdjecencia);
    }

    public void set_paper_conferencia(double[][] matriuAdjecencia) {
        paperConferencia.set_data(matriuAdjecencia);
    }

    public void set_autor(ArrayList<Node> autor) {
        this.autor = autor;
    }

    public void set_paper(ArrayList<Node> paper) {
        this.paper = paper;
    }

    public void set_terme(ArrayList<Node> terme) {
        this.terme = terme;
    }

    public void set_conferencia(ArrayList<Node> conferencia) {
        this.conferencia = conferencia;
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
