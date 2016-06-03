/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorytmy;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import mainPackage.RGB;

/**
 *
 * @author i9gaca
 */
public class odszumianie {
     //szum typu sol i pieprz
     public static BufferedImage szum1(BufferedImage in,int param) {
        BufferedImage out = new BufferedImage(in.getWidth(),in.getHeight(),in.getType());
        int width,height;
        width = out.getWidth();
        height = out.getHeight();
        int r,g,b;
        int szansa;
        out=RGB.powiekszKopiujac(in,0);
        for (int i = 1; i < width-1; i++) {
            for (int j = 1; j < height-1; j++) {
                // Tu wypełnij właściwym kodem
                szansa = (int)(Math.random()*100);
                if(szansa<param){
                    if(Math.random()*100>50)
                        out.setRGB(i,j,RGB.toRGB(0, 0, 0));
                    else
                        out.setRGB(i,j,RGB.toRGB(255, 255, 255));
                }      
            }
        }
        return out;
    }
     //szum rownomierny
      public static BufferedImage szum2(BufferedImage in,int param) {
        BufferedImage out = new BufferedImage(in.getWidth(),in.getHeight(),in.getType());
        int width,height;
        width = out.getWidth();
        height = out.getHeight();
        int r,g,b;
        int szansa;
        out=RGB.powiekszKopiujac(in,0);
        
        for (int i = 1; i < width-1; i++) {
            for (int j = 1; j < height-1; j++) {
                // Tu wypełnij właściwym kodem

                    r = RGB.getR(in.getRGB(i, j));
                    g = RGB.getG(in.getRGB(i, j));
                    b = RGB.getB(in.getRGB(i, j));
                    
                    int val = ((int)(Math.random()*255))%param;
                    
                    if((int)(Math.random()*100)<50){
                        out.setRGB(i,j,RGB.toRGB(RGB.zakresy(r+val),RGB.zakresy(g+val),RGB.zakresy(b+val)));
                    }else
                        out.setRGB(i,j,RGB.toRGB(RGB.zakresy(r-val),RGB.zakresy(g-val),RGB.zakresy(b-val)));
                    
            }
        }
        return out;
    }
      public static BufferedImage odszumianie1(BufferedImage in,int param) {
        BufferedImage out = new BufferedImage(in.getWidth(),in.getHeight(),in.getType());
        int width,height;
        width = out.getWidth();
        height = out.getHeight();
        int r,g,b;
        out=RGB.powiekszKopiujac(in,0);
        int rozmiarMaski = param;
        int a[][] = new int[rozmiarMaski][rozmiarMaski];
        
        for (int i = rozmiarMaski/2+3; i < width-rozmiarMaski/2-3; i++) {
            for (int j = rozmiarMaski/2+3; j < height-rozmiarMaski/2-3; j++) {
                // Tu wypełnij właściwym kodem
                   int wagaR = 0;
                   int wagaG = 0;
                   int wagaB = 0;
                   
                   //tworzenie dwuwymiarowej tablicy wartosci koloru sasiadow o wielkosc rozmiarMaski x rozmiarMaski
                   for(int q=0;q<rozmiarMaski;q++){
                       for(int w=0;w<rozmiarMaski;w++){
                           a[q][w] = in.getRGB(i-rozmiarMaski/2+q,j-rozmiarMaski/2+w);
                       }
                   }
                   //obliczanie wagi
                   for(int k = 0;k<rozmiarMaski;k++){
                       for(int l = 0;l<rozmiarMaski;l++){
                           wagaR+=RGB.getR(a[k][l]);
                           wagaG+=RGB.getG(a[k][l]);
                           wagaB+=RGB.getB(a[k][l]);
                       }
                   }
                   //konkretny kolor = waga/ilosc pikseli 
                   wagaR = wagaR/(int)(Math.pow(rozmiarMaski,2));
                   wagaG = wagaG/(int)(Math.pow(rozmiarMaski,2));
                   wagaB = wagaB/(int)(Math.pow(rozmiarMaski,2));
                   
                   out.setRGB(i, j, RGB.toRGB(wagaR,wagaG,wagaB));
            }
        }
        
        out = RGB.powiekszBialymi(out,-rozmiarMaski);
        return out;
    }
      
      public static BufferedImage odszumianie2(BufferedImage in,int param) {
        BufferedImage out = new BufferedImage(in.getWidth(),in.getHeight(),in.getType());
        int width,height;
        width = out.getWidth();
        height = out.getHeight();
        int r,g,b;
        out=RGB.powiekszKopiujac(in,0);
        int rozmiarMaski = param;
        int a[][] = new int[rozmiarMaski][rozmiarMaski];
        
        ArrayList<Integer> listaR = new ArrayList<>();
        ArrayList<Integer> listaG = new ArrayList<>();
        ArrayList<Integer> listaB = new ArrayList<>();
        
        for (int i = rozmiarMaski/2+3; i < width-rozmiarMaski/2-3; i++) {
            for (int j = rozmiarMaski/2+3; j < height-rozmiarMaski/2-3; j++) {
                // Tu wypełnij właściwym kodem
                   int wagaR = 0;
                   int wagaG = 0;
                   int wagaB = 0;
                   
                   listaR.clear();
                   listaG.clear();
                   listaB.clear();
                   
                   //tworzenie dwuwymiarowej tablicy wartosci koloru sasiadow o wielkosc rozmiarMaski x rozmiarMaski
                   for(int q=0;q<rozmiarMaski;q++){
                       for(int w=0;w<rozmiarMaski;w++){
                           a[q][w] = in.getRGB(i-rozmiarMaski/2+q,j-rozmiarMaski/2+w);
                       }
                   }
                   
                   for(int k = 0;k<rozmiarMaski;k++){
                       for(int l = 0;l<rozmiarMaski;l++){
                           listaR.add(RGB.getR(a[k][l]));
                           listaG.add(RGB.getG(a[k][l]));
                           listaB.add(RGB.getB(a[k][l]));
                       }
                   }
                   
                   Collections.sort(listaR);
                   Collections.sort(listaG);
                   Collections.sort(listaB);
                   
                   r=listaR.get(listaR.size()/2);
                   g=listaG.get(listaG.size()/2);
                   b=listaB.get(listaB.size()/2);
                   
                   out.setRGB(i, j, RGB.toRGB(r,g,b));
        
            }
        }
        
        out=RGB.powiekszKopiujac(out,-3);
        return out;
    }
      
       public static BufferedImage odszumianie3(BufferedImage in,int param) {
        BufferedImage out = new BufferedImage(in.getWidth(),in.getHeight(),in.getType());
        int width,height;
        width = out.getWidth();
        height = out.getHeight();
        int r,g,b;
        out=RGB.powiekszKopiujac(in,0);
        
        int rozmiarMaski = param;
        int a[][] = new int[rozmiarMaski][rozmiarMaski];
        
        ArrayList<Integer> listaR = new ArrayList<>();
        ArrayList<Integer> listaG = new ArrayList<>();
        ArrayList<Integer> listaB = new ArrayList<>();
        
        for (int i = rozmiarMaski/2+3; i < width-rozmiarMaski/2-3; i++) {
            for (int j = rozmiarMaski/2+3; j < height-rozmiarMaski/2-3; j++) {
                // Tu wypełnij właściwym kodem
                   int wagaR = 0;
                   int wagaG = 0;
                   int wagaB = 0;
                   
                   listaR.clear();
                   listaG.clear();
                   listaB.clear();
                   
                   //tworzenie dwuwymiarowej tablicy wartosci koloru sasiadow o wielkosc rozmiarMaski x rozmiarMaski
                   for(int q=0;q<rozmiarMaski;q++){
                       for(int w=0;w<rozmiarMaski;w++){
                           a[q][w] = in.getRGB(i-rozmiarMaski/2+q,j-rozmiarMaski/2+w);
                       }
                   }
                   
                   for(int k = 0;k<rozmiarMaski;k++){
                       for(int l = 0;l<rozmiarMaski;l++){
                           listaR.add(RGB.getR(a[k][l]));
                           listaG.add(RGB.getG(a[k][l]));
                           listaB.add(RGB.getB(a[k][l]));
                       }
                   }
                   
                   Collections.sort(listaR);
                   Collections.sort(listaG);
                   Collections.sort(listaB);
                   
                   r=RGB.getR(in.getRGB(i, j));
                   g=RGB.getG(in.getRGB(i, j));
                   b=RGB.getB(in.getRGB(i, j));
                   
                   
                   //Zmiana gdy lapie sie w 20% wartości skrajnych - każdy kanał osobno
                   for(int m=0;m<rozmiarMaski;m++){
                       if(listaR.get(m)==RGB.getR(in.getRGB(i,j)))
                           r=listaR.get(listaB.size()/2);
                       if(listaG.get(m)==RGB.getG(in.getRGB(i,j)))
                           g=listaG.get(listaB.size()/2);
                       if(listaB.get(m)==RGB.getB(in.getRGB(i,j)))
                           b=listaB.get(listaB.size()/2);
                   }
                   
                   for(int m=0;m<rozmiarMaski;m++){
                       if(listaR.get(listaR.size()-m-1)==RGB.getR(in.getRGB(i,j)))
                           r=listaR.get(listaB.size()/2);
                       if(listaG.get(listaR.size()-m-1)==RGB.getG(in.getRGB(i,j)))
                           g=listaG.get(listaB.size()/2);
                       if(listaB.get(listaR.size()-m-1)==RGB.getB(in.getRGB(i,j)))
                           b=listaB.get(listaB.size()/2);
                   }
                   
                   out.setRGB(i, j,RGB.toRGB(r,g,b));
            }
        }
        
        out=RGB.powiekszKopiujac(out,-rozmiarMaski);
        return out;
    }   
  
}
