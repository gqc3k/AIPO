/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.awt.image.BufferedImage;

/**
 *
 * @author Administrator
 */
public class RGB {
    public static int getR(int in) {
        return (int)(in >> 16) & 0xff;
    }
    public static int getG(int in) {
        return (int)(in >> 8) & 0xff;
    }
    public static int getB(int in) {
        return (int)(in) & 0xff;
    }
    public static int toRGB(int r,int g,int b) {
        return (int)((((r << 8)|g) << 8)|b);
    }
    
    public static BufferedImage powiekszKopiujac(BufferedImage in,int ile) {
        int wielkosc = ile*2;
        int szerokosc = in.getWidth()+wielkosc;
        int wysokosc = in.getHeight()+wielkosc;
        
        int staraszerokosc = in.getWidth();
        int starawysokosc = in.getHeight();
        
        BufferedImage out = new BufferedImage(szerokosc, wysokosc,in.getType());
        
        for(int i=0;i<szerokosc;i++) {
            for(int j=0;j<wysokosc;j++) {
                int ri = i-ile;
                int rj= j-ile;
                if(ri < 0) {
                    if(rj < 0) {
                        out.setRGB(i,j,in.getRGB(0,0));
                    }
                    else if(rj >= starawysokosc) {
                        out.setRGB(i,j,in.getRGB(0,starawysokosc-1));
                    }
                    else {
                        out.setRGB(i,j,in.getRGB(0,rj));
                    }
                }
                else if(ri >= staraszerokosc) {
                    if(rj < 0) {
                        out.setRGB(i,j,in.getRGB(staraszerokosc-1,0));
                    }
                    else if(rj >= starawysokosc) {
                        out.setRGB(i,j,in.getRGB(staraszerokosc-1,starawysokosc-1));
                    }
                    else {
                        out.setRGB(i,j,in.getRGB(staraszerokosc-1,rj));
                    }
                }
                else {
                    if(rj < 0) {
                        out.setRGB(i,j,in.getRGB(ri,0));
                    }
                    else if(rj >= starawysokosc) {
                        out.setRGB(i,j,in.getRGB(ri,starawysokosc-1));
                    }
                    else {
                        out.setRGB(i,j,in.getRGB(ri,rj));
                    }
                }
            }
        }
        
        return out;
    }
    
    public static BufferedImage powiekszBialymi(BufferedImage in,int ile) {
        int bialy = 0x00ffffff;
        int wielkosc = ile*2;
        int szerokosc = in.getWidth()+wielkosc;
        int wysokosc = in.getHeight()+wielkosc;
        
        int staraszerokosc = in.getWidth();
        int starawysokosc = in.getHeight();
        
        BufferedImage out = new BufferedImage(szerokosc, wysokosc,in.getType());
        
        for(int i=0;i<szerokosc;i++) {
            for(int j=0;j<wysokosc;j++) {
                int ri = i-ile;
                int rj= j-ile;
                if(ri >= 0 && rj >=0 && ri < staraszerokosc && rj < starawysokosc) {
                    out.setRGB(i,j,in.getRGB(ri,rj));
                }
                else {
                    out.setRGB(i,j,bialy);
                }
            }
        }
        
        return out;
    }
    
    public static int zakresy(int w) {
        return w>255 ? 255 : w < 0 ? 0 : w;
    }
    
    public static int binar(int w){
        return w==0 ? 1 : 0;
    }
    
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
        
      
                    //////////////////////////////////
                    for(int q=0;q<rozmiarMaski;q++){
                             for(int w=0;w<rozmiarMaski;w++){
                               maska[q][w] =in.getRGB(x-rozmiarMaski/2+q,y-rozmiarMaski/2+w);  
                             }
                        }
                    //////////////////////////////////
           
        
        return maska;
        
    }
}
