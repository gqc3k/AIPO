/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import mainPackage.Interface;

import java.awt.Color;
import javax.swing.WindowConstants;

/**
 *
 * @author Adam
 */
public class Main {

    public static void main(String[] args) {
        
        Interface inter = new Interface();
            inter.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            inter.setResizable(true);
            inter.setBackground(Color.CYAN);
            inter.setSize(800,600);
            inter.setVisible(true);
        
    }
    
}
