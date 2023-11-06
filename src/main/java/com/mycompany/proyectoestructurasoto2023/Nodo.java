package com.mycompany.proyectoestructurasoto2023;
import java.io.Serializable;
public class Nodo implements Serializable{
    private int x,y,w,h;
    private String num;
    private int xn,yn;
    Nodo(){        
    }
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the w
     */
    public int getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * @return the h
     */
    public int getH() {
        return h;
    }

    /**
     * @param h the h to set
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * @return the num
     */
    public String getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * @return the xn
     */
    public int getXn() {
        return xn;
    }

    /**
     * @param xn the xn to set
     */
    public void setXn(int xn) {
        this.xn = xn;
    }
    
    public int getYn() {
        return yn;
    }

    /**
     * @param yn the xn to set
     */
    public void setYn(int yn) {
        this.yn = yn;
    }
}
