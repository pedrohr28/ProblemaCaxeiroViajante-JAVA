/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpaeds;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author fls
 */
public class TPAEDS {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println(">>>>>>>>>>FORÇA BRUTA<<<<<<<<<<");
        //forca bruta
        int qntd_instancias = 11; //máx de instância para não estourar heap java
        ForcaBruta forca_bruta;
        ArrayList<ArrayList<Integer>> instancia_FB = new ArrayList();
        ArrayList<Long> tempo_FB = new ArrayList();

        for (int i = 0; i < qntd_instancias; i++) {
            long time2;
            forca_bruta = new ForcaBruta(i+2); //inicia instancia atual
            forca_bruta.gerarInstancia(); //preenche matriz aleatória da instancia atual
            time2 = System.currentTimeMillis();
            //ArrayList<Integer> distancia = forca_bruta.menorRota();
            instancia_FB.add(i, forca_bruta.menorRota()); //armazena array de menor caminho da instancia atual
            time2 = System.currentTimeMillis() - time2;
            tempo_FB.add(i, time2); //guarda tempo de execução do força bruta
            ArrayList<ArrayList<Integer>> caminhos = forca_bruta.getCaminhos();
            System.out.println("Instância "+i+": ");
            for (int j = 0; j < instancia_FB.get(i).size(); j++) {
                if (instancia_FB.get(i).get(j) == forca_bruta.getMenorDistancia()) {
                    System.out.print("Distancia Total = " + instancia_FB.get(i).get(j) + ": ");
                    for (int k = 0; k < forca_bruta.numeroVertices; k++) {
                        System.out.print("Vertice " + caminhos.get(j).get(k));
                        if (k < forca_bruta.numeroVertices - 1) {
                            System.out.print(" >> ");
                        }
                    }
                    System.out.println("");
                }
            }
            System.out.println("Tempo de execução: "+tempo_FB.get(i)+" ms");
            System.out.println("");
        }
        //fim forca bruta

        //leitura
        String arquivo1 = "si535.tsp";
        String arquivo2 = "pa561.tsp";
        String arquivo3 = "si1032.tsp";

        Leitura ler1 = new Leitura(arquivo1);
        Leitura ler2 = new Leitura(arquivo2);
        Leitura ler3 = new Leitura(arquivo3);
        //fim leitura

        System.out.println(">>>>>>>>>>GULOSO<<<<<<<<<<");

        //guloso
        Guloso guloso = new Guloso(ler1.iniciaGrafo());
        long time2 = System.currentTimeMillis();
        guloso.algoritmoGuloso(0);
        long time_final = System.currentTimeMillis() - time2;
        System.out.println(arquivo1);

        System.out.println("Caminho: " + guloso.getMenorCaminho());
        System.out.println("Distância mínima total: " + guloso.getDistanciaTotal());

        System.out.println("Tempo de execução: " + time_final + " ms");

        System.out.println("");

        guloso = new Guloso(ler2.iniciaGrafo());
        time2 = System.currentTimeMillis();
        guloso.algoritmoGuloso(0);
        time_final = System.currentTimeMillis() - time2;
        System.out.println(arquivo2);
        System.out.println("Caminho: " + guloso.getMenorCaminho());
        System.out.println("Distância mínima total: " + guloso.getDistanciaTotal());

        System.out.println("Tempo de execução: " + time_final + " ms");

        System.out.println("");

        guloso = new Guloso(ler3.iniciaGrafo());
        time2 = System.currentTimeMillis();
        guloso.algoritmoGuloso(0);
        time_final = System.currentTimeMillis() - time2;
        System.out.println(arquivo3);
        System.out.println("Caminho: " + guloso.getMenorCaminho());
        System.out.println("Distância mínima total: " + guloso.getDistanciaTotal());

        System.out.println("Tempo de execução: " + time_final + " ms");
        //fim guloso

        System.out.println("");
        System.out.println(">>>>>>>>>>BACKTRACKING<<<<<<<<<<");
        System.out.println("");

        //inicio backtracking
        Backtracking backtrack = new Backtracking(ler1.iniciaGrafo());
        time2 = System.currentTimeMillis();
        backtrack.iniciaBuscaBacktracking(0);
        time_final = System.currentTimeMillis() - time2;
        System.out.println(arquivo1);
        System.out.println("Caminho: " + backtrack.getVertices());
        System.out.println("Distância mínima total: " + backtrack.getPesoTotal());
        System.out.println("Tempo de execução: " + time_final + " ms");

        System.out.println("");

        backtrack = new Backtracking(ler2.iniciaGrafo());
        time2 = System.currentTimeMillis();
        backtrack.iniciaBuscaBacktracking(0);
        time_final = System.currentTimeMillis() - time2;
        System.out.println(arquivo2);
        System.out.println("Caminho: " + backtrack.getVertices());
        System.out.println("Distância mínima total: " + backtrack.getPesoTotal());
        System.out.println("Tempo de execução: " + time_final + " ms");
        /*for(int i=0;i<backtrack.numeroVertices;i++){
            for(int j=0;j<backtrack.numeroVertices;j++){
                if(j==i)
                    System.out.println(i+" | "+j+" "+backtrack.getPeso(i, j));
            }
        }*/
        System.out.println("");

        backtrack = new Backtracking(ler3.iniciaGrafo());
        time2 = System.currentTimeMillis();
        backtrack.iniciaBuscaBacktracking(0);
        time_final = System.currentTimeMillis() - time2;
        System.out.println(arquivo3);
        System.out.println("Caminho: " + backtrack.getVertices());
        System.out.println("Distância mínima total: " + backtrack.getPesoTotal());
        System.out.println("Tempo de execução: " + time_final + " ms");

        //fim backtracking
    }

}
