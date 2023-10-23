package com.mycompany.proyectoestructurasoto2023;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.*;
import java.util.*;
        
public class PanelMapa extends JPanel{
    private int x, y, x2, y2;
    private boolean dirigido = false;//empieza no dirigido
    private boolean distPersonalizadas = false;
    private int conta = 1, estado = 1;
    private ArrayList<Nodo>nodos;
    private ArrayList<Linea>lineas;
    private ArrayList<Matriz>matriz;
    //--------------------------------------------------------------------------
    PanelMapa(){
        setBackground(Color.white);
        setFocusable(true);
        nodos = new ArrayList<Nodo>();
        lineas = new ArrayList<Linea>();
        matriz = new ArrayList<Matriz>();
        addKeyListener(new KeyAdapter(){//teclas--------------------------------
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar()=='n'){//poner nodos
                    System.out.println("tecla n presionada");
                    estado=1;
                }
                if (e.getKeyChar()=='l'){//ligar nodos
                    System.out.println("tecla l presionada");
                    estado=2;
                    dirigido = false;
                }
                if (e.getKeyChar()=='d'){//ligar dirigido
                    System.out.println("tecla d presionada");
                    estado=2;
                    dirigido = true;
                }
                if (e.getKeyChar()=='m'){//mover nodos 
                    System.out.println("tecla m presionada");
                    estado=3;
                }
                if (e.getKeyChar()=='p'){//distancias propias 
                    System.out.println("tecla p presionada");
                    distPersonalizadas = !(distPersonalizadas);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        addMouseListener(new MouseAdapter(){//mouse-----------------------------
            int n1 = -1, n2 = -1;
            int nodoCambiante = -1;
            @Override
            public void mousePressed(MouseEvent e){
                if(estado == 1){//nodos
                    x = e.getX();
                    y = e.getY();
                    Nodo n = new Nodo();
                    n.setX(x); n.setY(y);
                    n.setH(10); n.setW(10);
                    n.setXn(x-5); n.setYn(y-5);
                    n.setNum("" + conta++);
                    nodos.add(n);
                    repaint();
                }
                if(estado==2){//ligas   
                    x = e.getX();
                    y = e.getY();
                    for(int i=0; i<nodos.size(); i++){
                        if(x <= (nodos.get(i).getX()-5)+10 && x >= (nodos.get(i).getX()-5)-10){
                            for(int j=0; j<nodos.size(); j++){
                                if(y <= (nodos.get(i).getY()-5)+10 && y >= (nodos.get(i).getY()-5)-10){
                                    if(i == j){
                                        n1 = i;
                                    }
                                }
                            }
                        }
                    }
                }
                if(estado==3){//mover
                    x = e.getX();
                    y = e.getY();
                    for(int i=0; i<nodos.size(); i++){
                        if(x <= (nodos.get(i).getX()-5)+10 && x >= (nodos.get(i).getX()-5)-10){
                            for(int j=0; j<nodos.size(); j++){
                                if(y <= (nodos.get(i).getY()-5)+10 && y >= (nodos.get(i).getY()-5)-10){
                                    if(i == j){
                                        nodoCambiante = i;
                                    }
                                }
                           }
                        }
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e){
                if(estado==2){//ligas 
                    x = e.getX();
                    y = e.getY();
                    for(int i=0; i<nodos.size(); i++){
                        if(x <= (nodos.get(i).getX()-5)+10 && x >= (nodos.get(i).getX()-5)-10){
                            for(int j=0; j<nodos.size(); j++){
                                if(y <= (nodos.get(i).getY()-5)+5 && y >= (nodos.get(i).getY()-5)-5){
                                    if(i == j){
                                        if(n1 != i){
                                            n2 = i;
                                        }
                                    }
                                }
                           }
                        }
                    }
                    if(n1 != -1 && n2 !=-1){
                        creaLiga(n1, n2);
                        n1 = -1;
                        n2 = -1;
                    }
                }
                if(estado==3){
                    if(nodoCambiante!=-1){
                        x = e.getX();
                        y = e.getY();
                        for(int i=0;i<lineas.size();i++){
                            if(lineas.get(i).getX1()==nodos.get(nodoCambiante).getX() && lineas.get(i).getY1()==nodos.get(nodoCambiante).getY()){
                                lineas.get(i).setX1(x);
                                lineas.get(i).setY1(y);
                                lineas.get(i).setDistancia((int) sqrt((x2-x)*(x2-x)+(y2-y)*(y2-y)));
                            }if(lineas.get(i).getX2()==nodos.get(nodoCambiante).getX() && lineas.get(i).getY2()==nodos.get(nodoCambiante).getY()){
                                lineas.get(i).setX2(x);
                                lineas.get(i).setY2(y);
                                lineas.get(i).setDistancia((int) sqrt((x2-x)*(x2-x)+(y2-y)*(y2-y)));
                            }
                        }
                        nodos.get(nodoCambiante).setX(x);
                        nodos.get(nodoCambiante).setXn(x-5);
                        nodos.get(nodoCambiante).setY(y);
                        nodos.get(nodoCambiante).setYn(y-5);
                        repaint();
                        nodoCambiante = -1;
                    }
                }
            }
        });
    }
    //figuras y demas-----------------------------------------------------------
    @Override public void paintComponent(Graphics g){
        super.paintComponent(g);
        //imagen----------------------------------------------------------------
        
        Image img;
        img = new ImageIcon(getClass().getResource("/images/mapa.jpeg")).getImage();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        
        //for de lineas y nodos-------------------------------------------------
        for(int i=0; i<lineas.size(); i++){//lineas
            g.setColor(Color.BLACK);
            int x1 = lineas.get(i).getX1();int y1 = lineas.get(i).getY1();
            int x22 = lineas.get(i).getX2();int y22 = lineas.get(i).getY2();
            g.drawLine(x1,y1,x22,y22);            
            if(lineas.get(i).getDirigido()){
                double angle = Math.atan2(y22 - y1, x22 - x1);
                int arrowLength = 15;
                int x3 = (int) (x22 - arrowLength * Math.cos(angle - Math.PI / 6));
                int y3 = (int) (y22 - arrowLength * Math.sin(angle - Math.PI / 6));
                int x4 = (int) (x22 - arrowLength * Math.cos(angle + Math.PI / 6));
                int y4 = (int) (y22 - arrowLength * Math.sin(angle + Math.PI / 6));
                Polygon arrow = new Polygon();
                arrow.addPoint(x22, y22);
                arrow.addPoint(x3, y3);
                arrow.addPoint(x4, y4);
                g.fillPolygon(arrow);
            }
            g.setColor(Color.BLUE);
            g.drawString(""+lineas.get(i).getDistancia(), (x1+x22)/2, (y1+y22)/2);
        }
        for(int i=0; i<nodos.size(); i++){//puntos
            //punto
            g.setColor(Color.BLACK);
            g.fillOval(nodos.get(i).getX()-5,nodos.get(i).getY()-5,nodos.get(i).getW(),nodos.get(i).getH());
            //numero de punto
            g.setColor(Color.BLUE);
            g.drawString(nodos.get(i).getNum(), nodos.get(i).getXn(), nodos.get(i).getYn());
        }
    }
    //funciones varias----------------------------------------------------------
    public void creaLiga(int n1, int n2){
        Linea lin = new Linea();
        x = nodos.get(n1).getX();
        y = nodos.get(n1).getY();
        x2 = nodos.get(n2).getX();
        y2 = nodos.get(n2).getY();
        lin.setDistancia((int) sqrt((x2-x)*(x2-x)+(y2-y)*(y2-y)));
        if (distPersonalizadas){
            try{
                int distancia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la distancia deseada"));
                lin.setDistancia(distancia);
            }catch(java.lang.NumberFormatException ex){}
        }
        lin.setX1(x);
        lin.setY1(y);
        lin.setX2(x2);
        lin.setY2(y2);
        lin.setDirigido(dirigido);
        lineas.add(lin);
        repaint();
    }
    //==========================================================================
    public void Matrix(){
        
        int tamaño = nodos.size();
        for (int i = 0; i < tamaño; i++){
            Matriz mat = new Matriz();
            mat.setNodo(i);//Nodo
            for(int k=0;k<lineas.size();k++){
                if(lineas.get(k).getX1()==nodos.get(i).getX() && lineas.get(k).getY1()==nodos.get(i).getY()){
                    
                    for(int j=0;j<tamaño;j++){
                        if(lineas.get(k).getX2()==nodos.get(j).getX() && lineas.get(k).getY2()==nodos.get(j).getY()){
                            mat.setNodoAs(j);
                            mat.setValor(lineas.get(k).getDistancia());
                        }
                    }
                }if(lineas.get(k).getX2()==nodos.get(i).getX() && lineas.get(k).getY2()==nodos.get(i).getY()){
                    
                    for(int j=0;j<tamaño;j++){
                        if(lineas.get(k).getX1()==nodos.get(j).getX() && lineas.get(k).getY1()==nodos.get(j).getY()){
                            mat.setNodoAs(j);
                            if(lineas.get(k).getDirigido()){
                                mat.setValor(-1);
                            }else{
                                mat.setValor(lineas.get(k).getDistancia());
                            }
                        }
                    }
                }       
            }
            matriz.add(mat);
        }
        PanelMatriz ventana = new PanelMatriz(matriz);
        matriz.clear();
        ventana.setVisible(true);
    }
    public void dijkstraDatos(){
        
        int tamaño = nodos.size();
        for (int i = 0; i < tamaño; i++){
            Matriz mat = new Matriz();
            mat.setNodo(i);//Nodo
            for(int k=0;k<lineas.size();k++){
                if(lineas.get(k).getX1()==nodos.get(i).getX() && lineas.get(k).getY1()==nodos.get(i).getY()){
                    
                    for(int j=0;j<tamaño;j++){
                        if(lineas.get(k).getX2()==nodos.get(j).getX() && lineas.get(k).getY2()==nodos.get(j).getY()){
                            mat.setNodoAs(j);
                            mat.setValor(lineas.get(k).getDistancia());
                        }
                    }
                }if(lineas.get(k).getX2()==nodos.get(i).getX() && lineas.get(k).getY2()==nodos.get(i).getY()){
                    
                    for(int j=0;j<tamaño;j++){
                        if(lineas.get(k).getX1()==nodos.get(j).getX() && lineas.get(k).getY1()==nodos.get(j).getY()){
                            mat.setNodoAs(j);
                            if(lineas.get(k).getDirigido()){
                                mat.setValor(-1);
                            }else{
                                mat.setValor(lineas.get(k).getDistancia());
                            }
                        }
                    }
                }       
            }
            matriz.add(mat);
        }
        Dijkstra ventana = new Dijkstra(matriz);
        matriz.clear();
        ventana.setVisible(true);
    }
    public void floydDatos(){
        
        int tamaño = nodos.size();
        for (int i = 0; i < tamaño; i++){
            Matriz mat = new Matriz();
            mat.setNodo(i);//Nodo
            for(int k=0;k<lineas.size();k++){
                if(lineas.get(k).getX1()==nodos.get(i).getX() && lineas.get(k).getY1()==nodos.get(i).getY()){
                    
                    for(int j=0;j<tamaño;j++){
                        if(lineas.get(k).getX2()==nodos.get(j).getX() && lineas.get(k).getY2()==nodos.get(j).getY()){
                            mat.setNodoAs(j);
                            mat.setValor(lineas.get(k).getDistancia());
                        }
                    }
                }if(lineas.get(k).getX2()==nodos.get(i).getX() && lineas.get(k).getY2()==nodos.get(i).getY()){
                    
                    for(int j=0;j<tamaño;j++){
                        if(lineas.get(k).getX1()==nodos.get(j).getX() && lineas.get(k).getY1()==nodos.get(j).getY()){
                            mat.setNodoAs(j);
                            if(lineas.get(k).getDirigido()){
                                mat.setValor(-1);
                            }else{
                                mat.setValor(lineas.get(k).getDistancia());
                            }
                        }
                    }
                }       
            }
            matriz.add(mat);
        }
        Floyd ventana = new Floyd(matriz);
        matriz.clear();
        ventana.setVisible(true);
    }
    //==========================================================================
}
