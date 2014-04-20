/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorytmy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import mainPackage.RGB;

/**
 *
 * @author i9gaca
 */
public class podstawoweOperacje {
    
    //tworzenie negatywu - spacer po wszystkich pikselach i zamiana ich wartości R, G i B na 255 - wartość
    public static BufferedImage negatyw(BufferedImage in) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(),in.getType());
        int height,width;
        width = out.getWidth();
        height = out.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem
                Color a = new Color(in.getRGB(i, j));
                
                int r = a.getRed();
                int g = a.getGreen();
                int b = a.getBlue();
                
                Color colorOut = new Color(255 -r,255 -g,255 -b);
                
                out.setRGB(i,j,colorOut.getRGB());
                
            }
        }
        return out;
    }
    
    // tworzenie odcieni szarości - (R+G+B)/3
    public static BufferedImage szarosciS(BufferedImage in) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(),in.getType());
        int width,height;
        width = out.getWidth();
        height = out.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem
                Color a = new Color(in.getRGB(i, j));
                
                int r = a.getRed();
                int g = a.getGreen();
                int b = a.getBlue();
                
                int srednia = (r+g+b)/3;
                
                Color colorOut = new Color(srednia,srednia,srednia);
                
                out.setRGB(i,j,colorOut.getRGB());
            }
        }
        return out;
    }
    // tworzenie odcieni szarości - 0.3*R+0.59*G+0.11*B
    public static BufferedImage szarosciN(BufferedImage in) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(),in.getType());
        int width,height;
        width = out.getWidth();
        height = out.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem
                Color a = new Color(in.getRGB(i, j));
                
                int r = (int)(a.getRed()*0.3f);
                int g = (int)(a.getGreen()*0.59f);
                int b = (int)(a.getBlue()*0.11f);
                
                int val = r+g+b;
               
                Color colorOut = new Color(val,val,val);
                
                out.setRGB(i,j,colorOut.getRGB());
            }
        }
        return out;
    }
    // sepia R=R+2*W, G=G+W, B=B
    public static BufferedImage sepia(BufferedImage in,int w) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(),in.getType());
        int width,height;
        width = out.getWidth();
        height = out.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem

                Color a = new Color(in.getRGB(i, j));
                
                int r = (int)(RGB.zakresy(a.getRed()+2*w));
                int g = (int)(RGB.zakresy(a.getGreen()+w));
                int b = (int)(RGB.zakresy(a.getBlue()));
                
                Color colorOut = new Color(r,g,b);
                
                out.setRGB(i,j,colorOut.getRGB());
            }
        }
        return out;
    }
    // histogram normalizacja 
    public static BufferedImage histnorm(BufferedImage in) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(),in.getType());
        int width,height,R,G,B;
        width = out.getWidth();
        height = out.getHeight();
         int maxR=0,minR=255,minG=255,maxG=0,minB=255,maxB=0;
        
        // szukanie minimów i maksimów
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem
                
                Color c = new Color(in.getRGB(i,j));
                
                R = c.getRed();
                G = c.getGreen();
                B = c.getBlue();
                
                if(R>maxR) maxR=R; if(R<minR) minR=R;
                if(G>maxG) maxG=G; if(G<minG) minG=G;
                if(B>maxB) maxB=B; if(B<minB) minB=B;
                
            }
        }
        // właściwa normalizacja  pixel[x,y]=255*(pixel[x,y]-minPix)/(maxPix-minPix)

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(in.getRGB(i, j));
                int red,green,blue;
                
                red     = 255*(c.getRed()-minR)     /(maxR-minR);
                green   = 255*(c.getGreen()-minG)   /(maxG-minG);
                blue    = 255*(c.getBlue()-minB)    /(maxB-minB);
                
                out.setRGB(i, j,new Color(red,green,blue).getRGB());
            }
        }
        return out;
    }
     // skalowanie obrazu
    public static BufferedImage skalowanie(BufferedImage in,float skala) {
        BufferedImage out = new BufferedImage(Math.round(skala*in.getWidth()), Math.round(skala*in.getHeight()),in.getType());
        int r,g,b,height,width;
        width = out.getWidth();
        height = out.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem
                Color a = new Color(in.getRGB((int)(i/skala),(int)(j/skala)));
                out.setRGB((int)(i),(int)(j),a.getRGB());
            }
        }
        
        return out;
    }
    
    // progowanie po średniej globalnej
    public static BufferedImage progowanieSG(BufferedImage in) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(),in.getType());
        //BufferedImage szary = Lab01.szarosciN(in); //Tu potrzebujesz kodu z pierwszych zajęć
        int r,g,b,height,width,suma=0,ilosc=0;
        width = out.getWidth();
        height = out.getHeight();
        int czarny = 0x00000000;
        int bialy = 0x00ffffff;
        // obliczanie średniej
        int srednia = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem
                //dla szarego obrazu r g b maja ta sama wartosc wiec mozna uproscic do jednej zmiennej
                Color a = new Color(in.getRGB(i, j));
                r           =  a.getRed();
                suma        += r;
                ilosc       =  width*height;
            }
        }
        
        // Tu wypełnij właściwym kodem
        srednia = suma/ilosc;
        
        // właściwe progowanie
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem
                Color a     = new Color(in.getRGB(i, j));
                
                if(a.getRed()>=srednia)
                    out.setRGB(i, j,bialy);
                else
                    out.setRGB(i, j,czarny);   
            }
        }

        return out;
    }
    
    // progowanie lokalne
    public static BufferedImage progowanieL(BufferedImage in,int rozmiar) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(),in.getType());
        //BufferedImage szary = Lab01.szarosciN(in); //Tu potrzebujesz kodu z pierwszych zajęć
        int height,width;
        int srednia_lokalna=0;
        int i_dol,i_gora,j_dol,j_gora,sumcia;
        width = out.getWidth();
        height = out.getHeight();
        int czarny = 0x00000000;
        int bialy = 0x00ffffff;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem
                
                int k,l;
                sumcia = 0;
                srednia_lokalna=0;
                
              
                    i_dol=i-rozmiar/2;
                    i_gora=i+rozmiar/2;
                    j_dol=j-rozmiar/2;
                    j_gora=j+rozmiar/2;
                    
                    if(i_dol<0) i_dol=0;
                    if(i_gora>width) i_gora=width;
                    if(j_dol<0) j_dol=0;
                    if(j_gora>height) j_gora=height;
                    
                    for(k=i_dol;k<i_gora;k++){
                        for(l=j_dol;l<j_gora;l++){
                            sumcia++;
                            srednia_lokalna+=new Color(in.getRGB(k, l)).getRed();
                        }
                    }
                    
                try{
                    srednia_lokalna=srednia_lokalna/sumcia;
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                if(new Color(in.getRGB(i, j)).getRed()<srednia_lokalna)
                    out.setRGB(i, j,czarny);
                else
                    out.setRGB(i, j,bialy);
            
            }
        }
        return out;
    }
    
    // progowanie lokalne ze średnią globalną
    public static BufferedImage progowanieLSG(BufferedImage in,int rozmiar,int odbieganie) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(),in.getType());
        //BufferedImage szary = Lab01.szarosciN(in); //Tu potrzebujesz kodu z pierwszych zajęć
        int height,width;
        int srednia_lokalna;
        int i_dol,i_gora,j_dol,j_gora,sumcia;
        width = out.getWidth();
        height = out.getHeight();
        int czarny = 0x00000000;
        int bialy = 0x00ffffff;
        
        int srednia = 0;
        sumcia = 0;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem
                Color col = new Color(in.getRGB(i, j));
                srednia+=col.getRed();
                sumcia++;
            }
        }
        
        // Tu wypełnij właściwym kodem
        srednia = srednia/sumcia;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem
                int k,l;
                sumcia = 0;
                srednia_lokalna=0;
                
                    i_dol=i-rozmiar/2;
                    i_gora=i+rozmiar/2;
                    j_dol=j-rozmiar/2;
                    j_gora=j+rozmiar/2;
                    
                    if(i_dol<0) i_dol=0;
                    if(i_gora>width) i_gora=width;
                    if(j_dol<0) j_dol=0;
                    if(j_gora>height) j_gora=height;
                    
                    for(k=i_dol;k<i_gora;k++){
                        for(l=j_dol;l<j_gora;l++){
                            sumcia++;
                            srednia_lokalna+=new Color(in.getRGB(k, l)).getRed();
                        }
                    }
                try{
                    srednia_lokalna=srednia_lokalna/sumcia;
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                if(Math.abs(srednia_lokalna-srednia)>odbieganie)
                {
                    if(new Color(in.getRGB(i, j)).getRed()<srednia)
                        out.setRGB(i, j,czarny);
                     else
                        out.setRGB(i, j,bialy);
                }
                else{
                    if(new Color(in.getRGB(i, j)).getRed()<srednia_lokalna)
                        out.setRGB(i, j,czarny);
                    else
                        out.setRGB(i, j,bialy);
                }
            }
        }

        return out;
    }
    
    // filtry splotowe
    public static BufferedImage filtrSplotowy(BufferedImage in,int maska[][]) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(),in.getType());
        int height,width,mheight,mwidth;
        int r,g,b;
        mheight = maska[0].length-1;
        mwidth = maska.length-1;
        width = out.getWidth()-(int)(maska.length/2);
        height = out.getHeight()-(int)(maska[0].length/2);
        
        int ilosc = 0;
        int k,l;
        for(int i=0;i<=mwidth;i++) {
            for(int j=0;j<=mheight;j++) {
                // Tu wypełnij właściwym kodem
                ilosc += maska[i][j];  
            }
        }
        if(ilosc==0)
            ilosc=1;
        //System.out.println("ilosc "+ilosc);
        out = RGB.powiekszBialymi(in,1);
        // Tu wypełnij właściwym kodem
        
        for (int i = 1; i < width; i++) {
            for (int j = 1; j < height; j++) {
                // Tu wypełnij właściwym kodem
                r=g=b=0;
                for(k=0;k<mwidth;k++){
                    for(l=0;l<mheight;l++){
                        r += RGB.zakresy((new Color(out.getRGB(i+k, j+l)).getRed())*maska[k][l]);
                        g += RGB.zakresy((new Color(out.getRGB(i+k, j+l)).getGreen())*maska[k][l]);
                        b += RGB.zakresy((new Color(out.getRGB(i+k, j+l)).getBlue())*maska[k][l]);
                    }
                }
                
                r = RGB.zakresy(r);
                g = RGB.zakresy(g);
                b = RGB.zakresy(b);
                out.setRGB(i, j, new Color(r,g,b).getRGB());
            }
        }
        
        
        
        //out = RGB.powiekszKopiujac(out,-1);
        
        return out;
    }
    
}
