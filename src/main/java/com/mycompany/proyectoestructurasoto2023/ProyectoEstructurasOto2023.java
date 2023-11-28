package com.mycompany.proyectoestructurasoto2023;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author yesu7
 */
public class ProyectoEstructurasOto2023 extends JFrame{
    private PanelMapa panel;
    private JMenuBar barraMenu;
    //archivo-------------------------------------------------------------------
    private JMenu archivo;
    private JMenuItem abrir, guardar, cerrar;
    //ligas---------------------------------------------------------------------
    private JMenu ligas;
    private JMenuItem ligarNodos;
    //grafos dirigidos----------------------------------------------------------
    private JMenu gDirigidos;
    private JMenuItem algoDijk, algoFloyd;
    //grafos no dirigidos-------------------------------------------------------
    private JMenu gNoDirigidos;
    private JMenuItem algoKrusk, algoPrim;
    //arboles-------------------------------------------------------------------
    private JMenu arboles;
    private JMenuItem bNivel, bProfundidad;
    //tabla hash----------------------------------------------------------------
    private JMenu tablaHash;
    private JMenuItem recolector;
    //Matrriz
    private JMenu matriz;
    private JMenuItem mostrarMatriz;
    ProyectoEstructurasOto2023(){
        setSize(1200,700);//tamaño
        setLocationRelativeTo(null);//centrado
        setTitle("Proyecto Final Estructuras Otoño 2023");//titulo
        iniciarComponentes();
    }
    private void iniciarComponentes(){
        panel = new PanelMapa();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        barraMenu = new JMenuBar();
        //dentro de la barra----------------------------------------------------
        archivo = new JMenu("Archivos");
            abrir = new JMenuItem("Abrir archivo");
            guardar = new JMenuItem("Guardar archivo");
            cerrar = new JMenuItem("Cerrar todo");
        ligas = new JMenu("Aristas");
            ligarNodos = new JMenuItem("Ligar nodos");
        gDirigidos = new JMenu("Grafos dirigidos");
            algoDijk = new JMenuItem("Algoritmo Dijkstra");
            algoFloyd = new JMenuItem("Algoritmo Floyd");
        gNoDirigidos = new JMenu("Grafos no dirigidos");
            algoKrusk = new JMenuItem("Algoritmo Kruskall");
            algoPrim = new JMenuItem("Algoritmo Prim");
        arboles = new JMenu("Arboles");
            bNivel = new JMenuItem("Busqueda por niveles");
            bProfundidad = new JMenuItem("Busqueda por profundidad");
        tablaHash = new JMenu("Hash");
            recolector = new JMenuItem("Recolector de basura");
        matriz = new JMenu("Matriz");
            mostrarMatriz = new JMenuItem("Mostrar matriz");
        //posicion objetos------------------------------------------------------
        barraMenu.setBounds(0, 0, getWidth(), 20);
        //agregar objetos-------------------------------------------------------
        panel.add(barraMenu);
        barraMenu.add(archivo);
            archivo.add(abrir);
                abrir.addActionListener(new ActionListener(){
                    @Override
                        public void actionPerformed(ActionEvent e){
                        panel.cargarGrafo();
                    }  
                });
            archivo.add(guardar);
                guardar.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        panel.guardarGrafo();
                    }  
                });
            archivo.add(cerrar);
                cerrar.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        panel.cerrarGrafo();
                    }  
                });
        barraMenu.add(ligas);
            ligas.add(ligarNodos);
                ligarNodos.addActionListener(new ActionListener(){//metodo crear lineas en menu con botones
                    @Override
                    public void actionPerformed(ActionEvent e){
                        try{
                            int n1 = Integer.parseInt(JOptionPane.showInputDialog("Nodo Inicio"));
                            int n2 = Integer.parseInt(JOptionPane.showInputDialog("Nodo final"));
                            JOptionPane.showMessageDialog(null,"Desde el nodo <<" + n1 +">> al nodo <<"+ n2 + ">>");
                            n1--;n2--;
                            panel.creaLiga(n1, n2);
                        }catch(java.lang.NumberFormatException ex){}
                    }
                });        
        barraMenu.add(gDirigidos);
            gDirigidos.add(algoDijk);
                algoDijk.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        panel.dijkstraDatos();
                    }
                });
            gDirigidos.add(algoFloyd);      
                algoFloyd.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        panel.floydDatos();
                    }
                });
        barraMenu.add(gNoDirigidos);
            gNoDirigidos.add(algoKrusk);
                algoKrusk.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //
                    }
                });
            gNoDirigidos.add(algoPrim);
                algoPrim.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        panel.primDatos();
                    }
                });
        barraMenu.add(arboles);
            arboles.add(bNivel);
                bNivel.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //
                    }
                });
            arboles.add(bProfundidad);    
                bProfundidad.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //
                    }
                });
        barraMenu.add(tablaHash);
            tablaHash.add(recolector);
        barraMenu.add(matriz);
            matriz.add(mostrarMatriz);
                mostrarMatriz.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        panel.Matrix();
                    }
                });
    }
    public static void main (String[]args){
        ProyectoEstructurasOto2023 fr = new ProyectoEstructurasOto2023();
	fr.setVisible(true);
	fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
    }
}