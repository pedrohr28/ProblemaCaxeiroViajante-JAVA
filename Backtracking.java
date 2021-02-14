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
public class Backtracking extends Grafo {

    private final ArrayList<Integer> vertices;//armazena os vertice que satisfaz o caminho cujo o pesototal é o menor
    private final boolean[] visitados;//resposavel por armazenar os vertices visitados e nao visitados
    private final int infinito = Integer.MAX_VALUE;
    private int vizinhos;//recebe o vizinho para obter sua lista de adjacência
    private int peso_total;//armazena o pesototal do caminho de aresta com menor peso
    private int menor_peso = infinito;//coloca menor_peso com um numero muito grande, na primeira verificação dos vizinhos dentro de Buscam, ele recebe o peso do vizinho
    //construtor

    public Backtracking(Grafo grafo) {
        super(grafo);
        this.vertices = new ArrayList<Integer>();
        this.visitados = new boolean[grafo.numeroVertices];//colocando como a quantidade de vertices visitados como a quantidade total de vertices
        this.vizinhos = 0;//inicializa vizinho como 0
        this.peso_total = 0;//inicializa peso como 0
    }

    public void iniciaBuscaBacktracking(int vertice) {
        //inicia todos os vértices como não visitado
        for (int i = 0; i < this.numeroVertices; i++) {
            this.visitados[i] = false;
        }
        //vertice inicial como visitado
        this.visitados[vertice] = true;
        //adiciona o vertice no caminho
        this.vertices.add(vertice);
        //chama o metódo da busca
        this.BuscaBacktracking(vertice);
    }

    //metodo busca Backtracking
    private void BuscaBacktracking(int vertice) {
        ArrayList<Integer> vizinhos2 = this.listaDeAdjacencia(vertice); //cria um arraylist de visinhos

        this.menor_peso = infinito;
        for (Integer vizinho : vizinhos2) {

            if (!this.visitados[vizinho] && this.getPeso(vertice, vizinho) < this.menor_peso) {//verifica se o peso da aresta da ligação do vertice ao vizinho e menor que o valor do menor_peso
                this.menor_peso = this.getPeso(vertice, vizinho);//menor_peso recebe o peso da aresta do vertice ao vizinho
                this.vizinhos = vizinho;//vizinho vai ser parametro da chamada da função Busca por recursividade

            }
        }
        //verifica se o vizinho nao foi visitado para que não fique em loop
        if (!this.visitados[this.vizinhos]) {

            this.vertices.add(this.vizinhos);//adiciona vizinho como um dos vertices do caminho
            this.peso_total += this.menor_peso;//armazena na variavel peso o valor anterior mais o peso da aresta vertice ao vizinho
            this.visitados[this.vizinhos] = true;//coloca vizinho como visitado
            //System.out.println("Vertice = " + vertice + " Vizinhos = " + this.vizinhos + " Peso = " + this.menor_peso + " PesoTotal = " + this.peso_total);
            //Chama a o metodo Busaca com parametro o vizinho que teve o menor peso
            this.BuscaBacktracking(this.vizinhos);
        } else {
            //todos os vertices ja foram visitados, soma com peso_total o peso da aresta inicial com o ultimo vertice visitado
            this.peso_total += this.getPeso(0, vertice);
        }

    }

    //retorna o pesotoal do menor caminho
    public int getPesoTotal() {
        return this.peso_total;
    }

    //retorna os vertice do menor caminho
    public ArrayList<Integer> getVertices() {
        this.vertices.add(this.vertices.get(0));
        return this.vertices;
    }
}
