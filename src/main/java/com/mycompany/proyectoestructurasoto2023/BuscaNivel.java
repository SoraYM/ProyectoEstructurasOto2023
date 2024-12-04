package com.mycompany.proyectoestructurasoto2023;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class BuscaNivel extends JFrame {
    private JTextArea textoMatriz;
    private JScrollPane scrollpanel;
    private JComboBox comboBox1;
    private JButton seleccionarButton;
    private int seleccion1;
    private JPanel panel;
    private final  ArrayList<Matriz> matriz;
    private final PanelMapa mapa;
    private boolean[] visited;
    private Queue<Integer>queue;
    private ArrayList<Integer>arbol;
    private ArrayList<Integer>arbolg;
    private static final int I = 99999;private static int n;
    BuscaNivel(ArrayList<Matriz> matriz, PanelMapa mapa){
        this.matriz = new ArrayList<Matriz>(matriz);
        this.mapa = mapa;
        n = matriz.size();
        this.visited = new boolean[n];
        this.queue = new LinkedList<Integer>();
        this.arbol = new ArrayList<Integer>();
        this.arbolg = new ArrayList<Integer>();
        this.setSize(500,200);//tamaño
        this.setLocation(100, 150);
        setTitle("BPA");//titulo
        iniciarComponentes();
        textoMatriz.setText("Arbol\n");
    }
    private void iniciarComponentes(){
        //panel
        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);
        getContentPane().add(panel);
        //
        textoMatriz = new JTextArea();
            scrollpanel = new JScrollPane(textoMatriz);  
            textoMatriz.setEditable(false);
        comboBox1 = new JComboBox<>();
        seleccionarButton = new JButton("Seleccionar");
        //posicionar------------------------------------------------------------
        scrollpanel.setBounds(10,0,200,
                100);
        seleccionarButton.setBounds(300, 50, 110, 20);
            comboBox1.setBounds(275, 20, 50,20);
        for (int i = 1; i <= n; i++) {
            comboBox1.addItem(i);
        }
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccion1 = (int) comboBox1.getSelectedItem()-1;
                algoritmoBPA(seleccion1);
                textoMatriz.append(arbol.toString()+"\n");
                pintaCamino();
                
                visited = new boolean[n];
                queue = new LinkedList<Integer>();
                arbol = new ArrayList<Integer>();
                arbolg = new ArrayList<Integer>();
            }
        });
        //agregar objetos-------------------------------------------------------
        panel.add(scrollpanel);
        panel.add(seleccionarButton);
        panel.add(comboBox1);
    }
    public void algoritmoBPA(int origen) {
        queue.add(origen);
        visited[origen] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            arbol.add(current+1);
            for (int i = 0; i < n; i++) {
                if (distanciaAB(current,i) == 1 && !visited[i]) {
                    arbolg.add(current); // Agrega el nodo "padre" antes del hijo
                    arbolg.add(i);      // Agrega el nodo hijo
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
    private void pintaCamino(){
        for(int i = 0; i < arbolg.size(); i++){mapa.camino.add(arbolg.get(i));}
        mapa.repaint();
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
                    valor = 1;
                }
            }
        }
        return valor;
    }
}
