package com.mycompany.proyectoestructurasoto2023;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Dijkstra extends JFrame {
    private JTextArea textoMatriz;
    private JScrollPane scrollpanel;
    private JComboBox<Integer> comboBox1, comboBox2;
    private JButton seleccionarButton;
    private int seleccion1, seleccion2;
    private JPanel panel;
    private final ArrayList<Matriz> matriz;
    private final PanelMapa mapa;
    private static final int I = 99999; private static int n;
    private int[] dist;
    private boolean[] visi;
    private int[] predecesores;
    Dijkstra(ArrayList<Matriz> matriz, PanelMapa mapa) {
        this.matriz = new ArrayList<>(matriz);
        this.mapa = mapa;
        n = matriz.size();
        this.setSize(500, 200);
        this.setLocation(100, 150);
        setTitle("Dijkstra");
        iniciarComponentes();
        textoMatriz.setText("Par     Dist    Camino\n");
        dist = new int[n];
        visi = new boolean[n];
        predecesores = new int[n];
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
        comboBox2 = new JComboBox<>();
        seleccionarButton = new JButton("Seleccionar");

        scrollpanel.setBounds(10, 0, 200, 100);
        seleccionarButton.setBounds(300, 50, 110, 20);
        comboBox1.setBounds(275, 20, 50, 20);
        comboBox2.setBounds(375, 20, 50, 20);

        for (int i = 1; i <= n; i++) {
            comboBox1.addItem(i);
            comboBox2.addItem(i);
        }

        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccion1 = (int) comboBox1.getSelectedItem() - 1;
                seleccion2 = (int) comboBox2.getSelectedItem() - 1;

                if (seleccion1 == seleccion2) {
                    JOptionPane.showMessageDialog(null, "¡No puedes seleccionar números iguales!");
                } else {
                    algoritmoDijkstra(seleccion1);
                    int distanciaMinima = dist[seleccion2];
                    List<Integer> caminoMasCorto = obtenerCamino(seleccion1, seleccion2);
                    imprimirEspecifico(seleccion1, seleccion2, distanciaMinima, caminoMasCorto);
                    textoMatriz.append("Resultado: ");
                    textoMatriz.append(Arrays.toString(dist)+"\n");
                    //imprimirResultado(dist);
                    
                    textoMatriz.append("\n");
                }
            }
        });

        panel.add(scrollpanel);
        panel.add(seleccionarButton);
        panel.add(comboBox1);
        panel.add(comboBox2);
    }
    private int distMin() {
        int min = I;
        int minIndex = -1;
        for (int v = 0; v < n; v++) {
            if (!visi[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    public void algoritmoDijkstra(int source) {
        long startTime = System.nanoTime(); // Registro del tiempo inicial
        int[][] grafo = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int valor = distanciaAB(i, j);
                grafo[i][j] = (valor == -1) ? I : valor;
            }
        }

        Arrays.fill(dist, I);
        Arrays.fill(visi, false);
        Arrays.fill(predecesores, -1);
        dist[source] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = distMin();
            visi[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visi[v] && grafo[u][v] != I && dist[u] != I && dist[u] + grafo[u][v] < dist[v]) {
                    dist[v] = dist[u] + grafo[u][v];
                    predecesores[v] = u;
                }
            }
        }
        long endTime = System.nanoTime(); // Registro del tiempo final
        long executionTime = endTime - startTime; // Cálculo del tiempo de ejecución en milisegundos
        System.out.println("Tiempo de ejecución del algoritmo Dijkstra: " + executionTime + " ns");
    }
    //--------------------------------------------------------------------------
    private void imprimirResultado(int[] dist) {
        textoMatriz.append("\n");
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                if (i != j) {
                    int u = i + 1;
                    int v = j + 1;
                    List<Integer> caminoMasCorto = obtenerCamino(i, j);
                    String path = String.format("%d -> %d    %2d     %s", u, v, (int) dist[j], caminoMasCorto.get(0)+1);
                    for(int k=1;k<caminoMasCorto.size();k++){
                        u = caminoMasCorto.get(k)+1;    
                        path += " -> " + u;
                    }
                    textoMatriz.append(path+"\n");
                }
            }
        }
    }
    private void imprimirEspecifico(int origen, int destino, int distanciaMinima, List<Integer> caminoMasCorto){
        mapa.camino.add(origen);
        int u = origen + 1;
        int v = destino + 1;
        String path = String.format("%d -> %d    %2d     %s", u, v, distanciaMinima, caminoMasCorto.get(0)+1);
        for(int i=1;i<caminoMasCorto.size();i++){
            mapa.camino.add(caminoMasCorto.get(i));
            u = caminoMasCorto.get(i)+1;    
            path += " -> " + u;
        }
        textoMatriz.append(path+"\n");
        System.out.println(mapa.camino.toString());
        mapa.repaint();
    }
    private List<Integer> obtenerCamino(int source, int destination) {
        List<Integer> path = new ArrayList<>();
        int current = destination;
        
        while (current != source) {
            path.add(current);
            current = predecesores[current];
        }

        path.add(source);
        Collections.reverse(path);
        return path;
    }
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
