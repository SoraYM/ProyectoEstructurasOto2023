package com.mycompany.proyectoestructurasoto2023;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author yesu7
 */
public class Kruskall extends JFrame{
    private JTextArea textoMatriz;
    private JScrollPane scrollpanel;
    private JComboBox comboBox1, comboBox2;
    private JButton seleccionarButton;
    private int seleccion1, seleccion2;
    private JPanel panel;
    private final  ArrayList<Matriz> matriz;
    private final PanelMapa mapa;
    private static final int I = 99999;private static int n;
    Kruskall(ArrayList<Matriz> matriz, PanelMapa mapa){
        this.matriz = new ArrayList<>(matriz);
        this.mapa = mapa;
        n = matriz.size();
        this.setSize(500, 200);
        this.setLocation(100, 150);
        setTitle("Kruskall");
        iniciarComponentes();
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
        scrollpanel.setBounds(10,0,200,100);
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
                    //algoritmoFloyd(seleccion1, seleccion2);
                    //algoritmoLarios(seleccion1, seleccion2);
                }
            }
        });
        //agregar objetos-------------------------------------------------------
        panel.add(scrollpanel);
        panel.add(seleccionarButton);
        panel.add(comboBox1);
        panel.add(comboBox2);
    }
    
}