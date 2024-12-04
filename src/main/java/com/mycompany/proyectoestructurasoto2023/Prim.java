package com.mycompany.proyectoestructurasoto2023;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author yesu7
 */
public class Prim extends JFrame {
    private JTextArea textoMatriz;
    private JScrollPane scrollpanel;
    private JComboBox<Integer> comboBox1;
    private JButton seleccionarButton;
    private int seleccion1;
    private JPanel panel;
    private final ArrayList<Matriz> matriz;
    private final PanelMapa mapa;
    private static final int I = 99999; private static int n; private int mat[][];
    Prim(ArrayList<Matriz> matriz, PanelMapa mapa) {
        this.matriz = new ArrayList<>(matriz);
        this.mapa = mapa;
        n = matriz.size();
        mat = new int[n][n];
        this.setSize(500, 200);
        this.setLocation(100, 150);
        setTitle("Prim");
        iniciarComponentes();
    }
    private void iniciarComponentes() {
        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);
        getContentPane().add(panel);

        textoMatriz = new JTextArea();
        scrollpanel = new JScrollPane(textoMatriz);
        textoMatriz.setEditable(false);

        comboBox1 = new JComboBox<>();
        seleccionarButton = new JButton("Seleccionar");

        scrollpanel.setBounds(10, 0, 200, 100);
        seleccionarButton.setBounds(300, 50, 110, 20);
        comboBox1.setBounds(275, 20, 50, 20);

        for (int i = 1; i <= n; i++) {
            comboBox1.addItem(i);
        }

        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccion1 = (int) comboBox1.getSelectedItem() - 1;
                //imprimir resultado
                algoritmoPrim(seleccion1);
            }
        });

        panel.add(scrollpanel);
        panel.add(seleccionarButton);
        panel.add(comboBox1);
    }
    //--------------------------------------------------------------------------
    public void algoritmoPrim(int partida) {
        inicializar();
        
        long startTime = System.nanoTime(); // Registro del tiempo inicial
        
        boolean[] mst = new boolean[n];
        int[] pesos = new int [n];
        Resultado[] resultado = new Resultado[n];
        for (int i = 0; i < n; i++) {
            pesos[i] = I;
            resultado[i] = new Resultado();
        }
        pesos[partida]=0;
        resultado[partida].padre=-1;
        for (int i = 0; i < n; i++) {
            int vertice = vertMin(mst, pesos);
            mst[vertice]= true;
            for (int j = 0; j < n; j++) {
                if (mat[vertice][j]>0) {
                    if (mst[j]==false && mat[vertice][j]< pesos[j]) {
                        pesos[j] = mat[vertice][j];
                        resultado[j].padre=vertice;
                        resultado[j].peso = pesos[j];
                    }                        
                }                                        
            }                
        }
        
        long endTime = System.nanoTime(); // Registro del tiempo final
        long executionTime = endTime - startTime; // Cálculo del tiempo de ejecución en milisegundos
        System.out.println("Tiempo de ejecución del algoritmo Prim: " + executionTime + " ns");
        
        imprimirRes(resultado);
    }
    //--------------------------------------------------------------------------
    private int vertMin( boolean[] mst, int[] pesos){
            int minPeso = I;
            int vertice = -1;

            for (int i = 0; i < n; i++) {
                if (mst[i]== false && minPeso > pesos [i]) {
                    minPeso =pesos [i];
                    vertice = i;
                }
            }
            return vertice;
    }
    private void imprimirRes(Resultado[] resultado) {
            int total_coste_min = 0;
            textoMatriz.append("Arbol de recubrimiento minimo\n");
            for (int i = 0; i < n; i++) {
                if(resultado[i].padre != -1){
                    textoMatriz.append("Arista " + (i+1) + " - " + (resultado[i].padre+1) + " peso: " + resultado[i].peso+"\n");
                    total_coste_min +=resultado[i].peso;
                    
                    mapa.camino.add(resultado[i].padre);
                    mapa.camino.add(i);
                    mapa.camino.add(resultado[i].padre);
                }
            }
            System.out.println(mapa.camino.toString());
            mapa.repaint();
            textoMatriz.append("Coste min total = " + total_coste_min+"\n");
    }
        private class Resultado {
            int padre;
            int peso;
    }
    private void inicializar(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int valor = distanciaAB(i, j);
                switch(valor){
                    case -1 -> {
                        mat[i][j]= I;
                    }
                    case 0 -> {
                        mat[i][j]=valor;
                    }
                    default -> {
                        mat[i][j]=valor;
                    }
                }
            }
        }
    }
    //--------------------------------------------------------------------------
    private int distanciaAB(int a, int b) {
        int valor;
        if (a == b) {
            valor = 0;
        } else {
            valor = -1;
        }
        if (!matriz.get(a).nodoAs.isEmpty()) {
            for (int k = 0; k < matriz.get(a).nodoAs.size(); k++) {
                if (matriz.get(a).nodoAs.get(k) == b) {
                    valor = matriz.get(a).valor.get(k);
                }
            }
        }
        return valor;
    }
}
