/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.histograma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author f211
 */
public class Histograma extends JPanel implements Observer {
    Conjunto c;
    public Histograma(Conjunto c) {
        this.c=c;
        this.setSize(new Dimension(400,400));
        this.setMinimumSize(new Dimension(400,400));
        this.setPreferredSize(new Dimension(400,400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        boolean control=false;
        int xi=10;
        int i=0;
        for(int y:c.getX()){
            if(i%2==0)
                g.setColor(Color.RED);
            else
                g.setColor(Color.GREEN);
            g.drawLine(xi,300, xi,10+y);
            xi+=5;
            i++;
            //fff
            
        }
    }
    
    
    

    
    @Override
    public void update(Observable o, Object arg) {
        this.updateUI();
    }
    
    
    public static void main(String[] args) {
        final JFrame f=new JFrame("Histograma");
        final Conjunto c=new Conjunto();
        Histograma h=new Histograma(c);
        c.addObserver(h);
        f.getContentPane().add(h);
        f.pack();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Thread t=new Thread(new Runnable() {

            @Override
            public void run() {
                   f.setVisible(true);
            }
        });
        t.start();
        
        Thread orden=new Thread(new Runnable() {

            @Override
            public void run() {
                   c.ordenar();
            }
        });
        orden.start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Histograma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
