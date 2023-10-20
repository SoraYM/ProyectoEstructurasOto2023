package com.mycompany.proyectoestructurasoto2023;

import java.awt.Color;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author yesu7
 */
public class PanelMatriz extends JFrame {
    private JTextArea textoMatriz;
    private JScrollPane scrollpanel;
    private JPanel panel;
    private  ArrayList<Matriz> matriz;
    PanelMatriz(ArrayList<Matriz> matriz){
        this.matriz = new ArrayList<Matriz>(matriz);
        this.setSize(500,500);//tamaño
        this.setLocation(100, 200);
            setTitle("Matriz de costos");//titulo
        iniciarComponentes();
        imprimirMatriz();
    }
    private void iniciarComponentes(){
        //panel
        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);
        getContentPane().add(panel);
        //
        textoMatriz = new JTextArea();
        textoMatriz.setEditable(false);
        scrollpanel = new JScrollPane(textoMatriz);   
        //posicionar 
        scrollpanel.setBounds(0,0,getWidth(),getHeight());
        //agregar objetos-------------------------------------------------------
        panel.add(scrollpanel);
        
    }
    private void imprimirMatriz(){
        int tamaño = matriz.size();
        textoMatriz.setText("Nodo\n[x]\t");
        for(int i=0;i<tamaño;i++){
            textoMatriz.append("["+(i+1)+"]\t");
        }
        textoMatriz.append("\n\n");
        for(int i=0;i<tamaño;i++){
            textoMatriz.append("["+(i+1)+"]\t");
            for (int j=0;j<tamaño;j++){
                int valor = distanciaAB(i, j);
                switch(valor){
                    case -1 -> {
                        textoMatriz.append("infinito\t");
                    }
                    case 0 -> {
                        textoMatriz.append(0+"\t");
                    }
                    default -> {
                        textoMatriz.append(valor +"\t");
                    }
                }
            }
            textoMatriz.append("\n\n");
        }
        
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
