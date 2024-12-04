 package com.mycompany.proyectoestructurasoto2023;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class BuscaProf extends JFrame {
    private JTextArea textoMatriz;
    private JScrollPane scrollpanel;
    private JComboBox comboBox1;
    private JButton seleccionarButton;
    private int seleccion1;
    private JPanel panel;
    private final  ArrayList<Matriz> matriz;
    private final PanelMapa mapa;
    private boolean[] visited;
    private ArrayList<Integer>arbol;
    private ArrayList<Integer>arbolg;
    private static final int I = 99999;private static int n;
    BuscaProf(ArrayList<Matriz> matriz, PanelMapa mapa){
        this.matriz = new ArrayList<Matriz>(matriz);
        this.mapa = mapa;
        n = matriz.size();
        this.visited = new boolean[n];
        this.arbol = new ArrayList<Integer>();
        this.arbolg = new ArrayList<Integer>();
        this.setSize(500,200);//tamaño
        this.setLocation(100, 150);
        setTitle("BPP");//titulo
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
                algoritmoBPP(seleccion1);
                textoMatriz.append(arbolg.toString()+"\n");
                pintaCamino();
                
                //reiniciar
                visited = new boolean[n];
                arbol = new ArrayList<Integer>();
                arbolg = new ArrayList<Integer>();
            }
        });
        //agregar objetos-------------------------------------------------------
        panel.add(scrollpanel);
        panel.add(seleccionarButton);
        panel.add(comboBox1);
    }
    private void algoritmoBPP(int origen) {
        arbol.add(origen + 1);
        arbolg.add(origen + 1);
        visited[origen] = true;
        for (int i = 0; i < n; i++) {
            if (distanciaAB(origen, i) == 1 && !visited[i]) {
                algoritmoBPP(i); // Recorrer la rama
                arbol.add(origen + 1); // Regresar al nodo raíz de esta rama
            }
    }   
}

    private void pintaCamino(){
        for(int i = 0; i < arbol.size(); i++){mapa.camino.add(arbol.get(i)-1);}
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
