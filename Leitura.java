/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpaeds;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author fls
 */
public class Leitura {

    private int dimensao;
    private int peso;
    private String tipo;
    private String arquivo;
    Scanner ler;

    public Leitura(String file) {
        this.arquivo = file;
    }

    @SuppressWarnings("empty-statement")
    public Grafo iniciaGrafo() throws FileNotFoundException {
        this.ler = new Scanner(new File("src/tpaeds/" + this.arquivo));
        while (!ler.next().equals("DIMENSION:"));
        this.dimensao = ler.nextInt();
        Grafo grafo = new Grafo(dimensao);
        while (!ler.next().equals("EDGE_WEIGHT_FORMAT:"));
        this.tipo = ler.next();
        while (!ler.next().equals("EDGE_WEIGHT_SECTION"));

        if (tipo.equals("UPPER_DIAG_ROW")) {
            for (int i = 0; i < dimensao; i++) {
                for (int j = i; j < dimensao; j++) {
                    String peso = ler.next();
                        grafo.insereAresta(i, j, Integer.parseInt(peso));
                        grafo.insereAresta(j, i, Integer.parseInt(peso));
                }
            }
        } 
        else if(tipo.equals("FULL_MATRIX")){
            for(int i=0;i<dimensao;i++){
                for(int j=0;j<dimensao;j++){
                    String peso = ler.next();
                        grafo.insereAresta(i, j, Integer.parseInt(peso));
                }
            }
        }   else { //LOWER_DIAG_ROW
            for (int i = 0; i < dimensao; i++) {
                for (int j = 0; j <= i; j++) {
                    String peso = ler.next();
                        grafo.insereAresta(i, j, Integer.parseInt(peso));
                        grafo.insereAresta(j, i, Integer.parseInt(peso));
                }
            }
        }

        return grafo;
    }

}
