package com.mycompany.proyectoestructurasoto2023;

import java.util.*;

/**
 *
 * @author yesu7
 */
public class Matriz {
    public int nodo;
    public List<Integer> nodoAs, valor;
    Matriz(){
        nodoAs = new ArrayList<Integer>();
        valor = new ArrayList<Integer>();
    }
    public int getNodo() {
        return nodo;
    }
    public void setNodo (int nodo){
        this.nodo = nodo;
    }
    public void setValor(int valor) {
        this.valor.add(valor);
    }
    
    public void setNodoAs(int nodoAs){
        this.nodoAs.add(nodoAs);
    }
}
