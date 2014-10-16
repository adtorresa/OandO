/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.histograma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Familia Torres
 */
public class Histograma extends JPanel implements Observer {
    
    private static Object frame;
    static Conjunto c;
    static ActionListenerCommand Sort;
    static ActionListenerCommand shell;
    static ActionListenerCommand burbuja;
    
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
            g.setColor(Color.BLACK);
            g.drawString(Arrays.toString(c.getX()), 50, 350);
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.updateUI();
    }
    
    
    public static void main(String[] args) {
        final JFrame f=new JFrame("Histograma");
        int intQuantity = 0;
        Object result = "20"; //JOptionPane.showInputDialog(frame, "Ingrese la cantidad de números:");
        intQuantity =Integer.parseInt(result.toString());
        final Conjunto c=new Conjunto(intQuantity);
        Histograma h=new Histograma(c);
        c.addObserver(h);
        f.getContentPane().add(h);
        JPanel contentCenter = new JPanel(new GridBagLayout());
        JButton  button1 = new JButton ("Organizar por burbuja");
        JButton  button2 = new JButton ("Organizar por QuickSort");
        JButton  button3 = new JButton ("Organizar por ShellSort");
        JButton  button4 = new JButton ("Desordenar");
        
        burbuja = new Burbuja(c.getX());
        shell   = new Shell(c.getX());
        Sort    = new Sort(c.getX());
        
        f.add(contentCenter,BorderLayout.SOUTH);
        contentCenter.add(button1);
        contentCenter.add(button2);
        contentCenter.add(button3);
        contentCenter.add(button4);
        f.pack();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        final Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                   f.setVisible(true);
            }
        });
        
        t.start();
        
        //METODOS PARA ORDENAL
        final Thread OrdenBurbuja=new Thread(new Runnable() {
        public void run() {
               c.setCommand(burbuja);
               c.Ordenar();
         }
        });
        
        final Thread ordenSort=new Thread(new Runnable() {
        public void run() {
               c.setCommand(Sort);
               c.Ordenar();
         }
        });
        
        final Thread ordenShell=new Thread(new Runnable() {
        public void run() {
            c.setCommand(shell);
            c.Ordenar();
         }
        });
        
        final Thread Random=new Thread(new Runnable() {
        public void run() {            
            c.Random(20);
         }
        });
        
        //Eventos Botones
        button1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
              OrdenBurbuja.start();
        }          
        }); 
        
        button2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
             ordenSort.start();
         }          
        }); 

        button3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
             ordenShell.start();
         }          
        });
        
        button4.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
             Random.start();
         }          
        });
        

        
        
        
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Histograma.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      
}
