package tpaeds;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fls
 */
public class Guloso extends Grafo {

    private final int infinito = Integer.MAX_VALUE;
    private final ArrayList<Boolean> visitado;
    private int menorPeso;
    private int pesoTotal;
    private int verticeLigacaoMenorAresta;
    private final ArrayList<Integer> caminho;

    public Guloso(Grafo grafo) {
        super(grafo);
        this.visitado = new ArrayList();
        this.menorPeso = infinito;
        this.pesoTotal = 0;
        this.verticeLigacaoMenorAresta = 0;
        this.caminho = new ArrayList();
    }

    public void algoritmoGuloso(int vertice) {
        int verticeInicial = vertice;
        //inicializa array de visitados como falso
        for (int i = 0; i < this.numeroVertices; i++) {
            this.visitado.add(false);
        }
        this.visitado.set(vertice, true); //marca vértice inicial como visitado
        this.verticeLigacaoMenorAresta = vertice; //só pra poder colocar vertice inicial ao caminho minimo

        for (int i = 0; i < this.numeroVertices; i++) {
            this.caminho.add(this.verticeLigacaoMenorAresta); //adiciona vertice ao caminho mínimo
            for (int j = 0; j < this.numeroVertices; j++) {
                if (this.getPeso(vertice, j) < this.menorPeso && !this.visitado.get(j) && this.getPeso(vertice, j) > 0) { //se peso da aresta atual menor que menor peso guardado e vértice não foi visitado
                    this.menorPeso = this.getPeso(vertice, j); //pega peso da aresta
                    this.verticeLigacaoMenorAresta = j; //guarda vértice com menor aresta de ligação
                }
            }
            this.menorPeso = infinito; // coloca menor peso como infinito novamente
            this.visitado.set(this.verticeLigacaoMenorAresta, true); //marca como visitado vértice que possui menor aresta de ligação
            this.pesoTotal = this.pesoTotal + this.getPeso(vertice, this.verticeLigacaoMenorAresta); //vai somando peso das menores arestas
            vertice = this.verticeLigacaoMenorAresta; //começa a olhar a partir do outro vértice agora
        }
        this.pesoTotal = this.pesoTotal + this.getPeso(verticeInicial, this.verticeLigacaoMenorAresta);
        this.caminho.add(verticeInicial);
    }

    public ArrayList<Integer> getMenorCaminho() {
        return this.caminho;
    }

    public int getDistanciaTotal() {
        return this.pesoTotal;
    }

}
