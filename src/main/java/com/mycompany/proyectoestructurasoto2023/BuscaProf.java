 package com.mycompany.proyectoestructurasoto2023;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class BuscaProf extends JFrame {
    private JTextArea textoMatriz;
    private JScrollPane scrollpanel;
    private JComboBox comboBox1, comboBox2;
    private JButton seleccionarButton;
    private int seleccion1, seleccion2;
    private JPanel panel;
    private final  ArrayList<Matriz> matriz;
    private final PanelMapa mapa;
    private static final int I = 99999;private static int n;
    BuscaProf(ArrayList<Matriz> matriz, PanelMapa mapa){
        this.matriz = new ArrayList<Matriz>(matriz);
        this.mapa = mapa;
        n = matriz.size();
        this.setSize(500,200);//tamaño
        this.setLocation(100, 150);
            setTitle("Floyd");//titulo
        iniciarComponentes();
        textoMatriz.setText("Par     Dist    Camino\n");
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
        comboBox2 = new JComboBox<>();
        seleccionarButton = new JButton("Seleccionar");
        //posicionar------------------------------------------------------------
        scrollpanel.setBounds(10,0,200,
                100);
        seleccionarButton.setBounds(300, 50, 110, 20);
            comboBox1.setBounds(275, 20, 50,20);
            comboBox2.setBounds(375, 20, 50,20);
        for (int i = 1; i <= n; i++) {
            comboBox1.addItem(i);
            comboBox2.addItem(i);
        }
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccion1 = (int) comboBox1.getSelectedItem()-1;
                seleccion2 = (int) comboBox2.getSelectedItem()-1;
                
                if (seleccion1 == seleccion2) {
                    JOptionPane.showMessageDialog(null,"¡No puedes seleccionar números iguales!");
                } else {
                    algoritmoBPP(seleccion1, seleccion2);
                }
            }
        });
        //agregar objetos-------------------------------------------------------
        panel.add(scrollpanel);
        panel.add(seleccionarButton);
        panel.add(comboBox1);
        panel.add(comboBox2);
    }
    private void algoritmoBPP(int origen, int destino){
        
    }
    //--------------------------------------------------------------------------
    private void imprimirEspecifico(int origen, int destino, int[][] dist, int[][] predMatrix){
        mapa.camino.add(origen);
        int u = origen + 1;
        int v = destino + 1;
        String path = String.format("%d -> %d    %2d     %s", u, v, (int) dist[origen][destino], u);
        do {
            u = predMatrix[u - 1][v - 1];
            mapa.camino.add(u-1);
            path += " -> " + u;
        } while (u != v);
        textoMatriz.append(path+"\n");
        mapa.repaint();
    }
    private int distanciaAB(int a, int b){
        int valor;
        if (a == b){
            valor = 0;
        }else{
            valor = -1;
            
        }if(!matriz.get(a).nodoAs.isEmpty()){
            for(int k=0;k<matriz.get(a).nodoAs.size();k++){
                if(matriz.get(a).nodoAs.get(k) == b){
                    valor = matriz.get(a).valor.get(k);
                }
            }
        }
        return valor;
    }
}
