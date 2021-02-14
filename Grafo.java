/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpaeds;

import java.util.ArrayList;

/**
 *
 * @author fls
 */
public class Grafo {
    protected int numeroVertices;
    protected int[][] matrizAdjacencia;
    
    public Grafo(int vertices) //construtor, recebe número de vertices
    {
        this.matrizAdjacencia=new int[vertices][vertices];
        this.numeroVertices=vertices;
        
        for(int i=0;i<vertices;i++)
        {
            for(int j=0;j<vertices;j++)
            {
                this.matrizAdjacencia[i][j]=0; //inicializa todas posições da matriz de adjacência com valor 0
            }
        }
    }
    
    public Grafo(Grafo gg){
        this.matrizAdjacencia = gg.matrizAdjacencia;
        this.numeroVertices = gg.numeroVertices;
    }
    
    public void insereAresta(int vertice1, int vertice2, int peso)
    {
        this.matrizAdjacencia[vertice1][vertice2]=peso; //insere aresta no grafo
    }
    public boolean existeAresta(int vertice1, int vertice2)
    {
        return (this.matrizAdjacencia[vertice1][vertice2]>0); //confere se aresta existe no grafo
    }
    ArrayList<Integer> listaDeAdjacencia(int vertice1) //passado 1 vertice
    {
        ArrayList aux = new ArrayList();
        for(int i=0; i<this.numeroVertices;i++){
            if(this.matrizAdjacencia[vertice1][i]!=0){ //se diferente de 0 então é adjacente
                aux.add(i);
            }
        }
        return aux; //retorna lista de vertices adjacentes
    }
    
    public int getPeso(int vertice1, int vertice2)
    {
        return this.matrizAdjacencia[vertice1][vertice2]; //retorna peso de uma aresta qualquer
    }
    
    public int numVertice(){ //retorna quantidade de vértices no grafo
        return this.numeroVertices;
    }
}
