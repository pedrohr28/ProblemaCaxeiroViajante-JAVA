package tpaeds;

import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fls
 */
public class ForcaBruta extends Grafo {

    private final int[] distanciaProfundidade;//armazena as distancias entre cada vertice
    private final int[] verticePredecessor;//armazena os vertice predecessores
    private final int infinito = Integer.MAX_VALUE;
    private final boolean[] visitado;//contem informação dos vertices visitados ou nao visitados
    private ArrayList<Integer> caminhos;
    private ArrayList<Integer> adiciona_caminhos;// armazena caminhos da recursividade
    private ArrayList<ArrayList<Integer>> map;//responsavel por armazenar a distancia e o caminho correpondente
    private ArrayList<Integer> caminhos_mat;// armazena caminhos da recursividade
    private int contador;//responsavel pelo index de map

    public ForcaBruta(int vertices) {
        super(vertices); //inicializa construtor da classe Grafo
        this.distanciaProfundidade = new int[vertices]; //cria vetor com tamanho de vertices
        this.verticePredecessor = new int[vertices]; //cria vetor com tamanho de vertices
        this.visitado = new boolean[vertices];
        this.caminhos = new ArrayList<Integer>();
        this.adiciona_caminhos = new ArrayList<Integer>();
        this.caminhos_mat = new ArrayList<Integer>();
        for(int i=0;i<this.numeroVertices;i++){ //inicializa arraylist com zeros
            this.caminhos_mat.add(0);
        }
        map = new ArrayList<ArrayList<Integer>>(); //arraylist de arraylist
        this.contador = 0;//inicializa com zero
    }

    public void gerarInstancia() { //gerar instancia aleatoria (n_vertices varia de 2 a n)
        /*int[][] graph = {{0, 3, 4, 2, 7},
        {3, 0, 4, 6, 3},
        {4, 4, 0, 5, 8},
        {2, 6, 5, 0, 6},
        {7, 3, 8, 6, 0}};

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                this.insereAresta(i, j, graph[i][j]);
            }
        }*/
        Random aleatorio = new Random();
        int peso;
        for (int i = 0; i < this.numeroVertices; i++) {
            for (int j = 0; j < this.numeroVertices; j++) {
                peso = aleatorio.nextInt(this.numeroVertices + 1); //peso recebe números aleatório de 0 a n_vertices
                this.insereAresta(i, j, peso); //insere arestas no grafo
            }
        }
    }
    //metodo inicia a busca da menorRota
    public ArrayList<Integer> menorRota() { //retorna menor caminho
        for (int i = 0; i < this.numeroVertices; i++) {
            this.verticePredecessor[i] = -1; //inicia todos vertices predecessor com -1
            this.visitado[i] = false; // inicia todos vertices como não visitados (falso)
            this.distanciaProfundidade[i] = 0; //coloca zeros
        }
        this.visitado[0] = true; //marca vértice inicial como visitado
        buscaProfundidade(0, 0); //inicia busca em profundidade
        return this.caminhos;
    }
    //inicia o forca bruta a partir de um vertice inicial
    private void buscaProfundidade(int vertice, int cont) {
        ArrayList<Integer> vizinhos = this.listaDeAdjacencia(vertice); //cria um arraylist de vizinhos
        
         // Se o último nó for alcançado e tiver um link para o nó inicial, ou seja, a fonte então
         // mantém o valor mínimo fora do custo total em caminhos
         // Finalmente, retorne para verificar se há mais valores possíveis e é alcançado e retorna para o nó inicial, ou seja, a fonte entãomantém o valor mínimo fora do custo total
         // e Finalmente, retorne para verificar se há mais valores possíveis
        if (cont == this.numeroVertices - 1) {
            this.caminhos.add(this.distanciaProfundidade[vertice] + this.getPeso(0, vertice));
            this.adiciona_caminhos.add(vertice);
            //insere no caminhos_mat o arraylist adiciona_caminhosa a cada posição correspondente
            for (int i = 0; i < this.adiciona_caminhos.size(); i++) {
                this.caminhos_mat.set(i, this.adiciona_caminhos.get(i));
                //this.caminhos_mat[this.contador][i] = this.adiciona_caminhos.get(i);
            }
            //adiciona no arraylist de arrylist map, o arraylist caminhos_mat na posição contados
            map.add(this.contador, this.caminhos_mat);
            this.contador++;
            //System.out.println("");
            return;
        }
        //remove os vertices adicionados na recursividade até a posição onde a quantidade de vezes que a recursividade foi chamada
        for (int i = this.adiciona_caminhos.size() - 1; i > cont - 1; i--) {
            this.adiciona_caminhos.remove(i);
        }
        for (Integer vizinho : vizinhos) {
            //se a distancia eh infinita, o vertice nao foi visitado
            if (!this.visitado[vizinho]) {//se o vizinho não for visitado 
                //armazenada no vetor de distancia  na posição do vizinho o peso da distancia do vertice mais o peso da aresta que liga o vertice ao vizinho
                this.distanciaProfundidade[vizinho] = this.distanciaProfundidade[vertice] + this.getPeso(vertice, vizinho); //atualiza a distancia
                //marca o vizinho como já visitado
                this.visitado[vizinho] = true;
                //adiciona o vertice como parte do caminho
                this.adiciona_caminhos.add(vertice);
                //chama a funçao passando o vizinho como parametro do vertice e adicionando uma unidade ao cont, isso indica a quantiadade de vezes da chamada do método
                buscaProfundidade(vizinho, cont + 1); //chama de novo
                //backtracking vizinho volta para falso;
                this.visitado[vizinho] = false;
            }
        }
        return;
    }
    //metodo de get dos Vertices Predecessore
    public int[] getVerticepredecessor() {
        return this.verticePredecessor;
    }
    //metodo de retorno do arrayList de arrayList
    public ArrayList<ArrayList<Integer>> getCaminhos() {
        return this.map;
    }
    //metodo que retorna a menor distancia do forca bruta
    public int getMenorDistancia() {
        int menor = infinito;
        for (int i = 0; i < this.caminhos.size(); i++) {
            //seleciona o menor valor entre os valores do caminhos
            menor = Math.min(menor, this.caminhos.get(i));
        }
        return menor;
    }

}