/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.histograma;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Familia Torres
 */
public class Shell implements ActionListenerCommand{
    public int x[];

    Shell(int[] x) {
        this.x = x;
    }
    
    public void Shell(int x[])
    {
        this.x = x;
    }

    @Override
    public void Execute() {
         Boolean b=true;
        int increment = this.x.length / 2;
	while (increment > 0) {
		for (int i = increment; i < this.x.length; i++) {
			int j = i;
			int temp = this.x[i];
			while (j >= increment && this.x[j - increment] > temp) {
				this.x[j] = this.x[j - increment];
				j = j - increment;
			}
			this.x[j] = temp;
                         
		}
		if (increment == 2) {
			increment = 1;
		} else {
			increment *= (5.0 / 11);
		}
	}
        
        synchronized(b){
                    try {
                        b.wait(5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }
    
    public int [] getX()
    {
        return this.x;
    }
    
}
