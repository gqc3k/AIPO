/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorytmy;

import java.awt.image.BufferedImage;

/**
 *
 * @author Greko
 */
public class helpers {
    
        /**
     * 
     * @param rozmiarMaski rozmiar zwracanej maski
     * @param in obraz wejsciowy
     * @param x polozenie x
     * @param y polozenie y
     * @return 
     */
    public static int[][] zwrocMaske(int rozmiarMaski, BufferedImage in,int x,int y){
        
        int width,height;
        width   = in.getWidth();
        height  = in.getHeight();
        
        int [][] maska = new int[rozmiarMaski][rozmiarMaski];
        
        try{
                    //////////////////////////////////
                    for(int q=0;q<rozmiarMaski;q++){
                             for(int w=0;w<rozmiarMaski;w++){
                               maska[q][w] =in.getRGB(x-rozmiarMaski/2+q,y-rozmiarMaski/2+w);  
                             }
                        }
                    //////////////////////////////////
           
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Sprawdz zakresy, maska prawdopodobnie wyskakuje poza obraz !");
        }
        
        return maska;
        
    }
    
    
    /**
     * 
     * @param in wejsciowy obraz
     * @param x1 wspolrzedna x poczatkowa
     * @param x2 wpolrzedna x koncowa
     * @param y1 wpolrzedna y poczatkowa
     * @param y2 wpolrzedna y koncowa
     * @return wyciaty obrazek
     */
    public static BufferedImage wytnijObraz(BufferedImage in,int x1,int x2,int y1,int y2){
        
        BufferedImage out;
        
        int width,height;
        width   = x2 - x1 + 1;
        height  = y2 - y1 + 1;
        
        out = new BufferedImage(width,height,in.getType());
        
        
        
        return out;  
    }
    
    
    
}
