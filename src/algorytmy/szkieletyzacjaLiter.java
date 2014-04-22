/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorytmy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import mainPackage.RGB;

/**
 *
 * @author i9gaca
 */
public class szkieletyzacjaLiter {
    
    private static final int czarny = Color.BLACK.getRGB();
    private static final int bialy = Color.WHITE.getRGB();
    private static final int czerwony = Color.RED.getRGB();
    private static final int zielony = Color.GREEN.getRGB();
    private static final int niebieski = Color.BLUE.getRGB();
    private static final int zolty = Color.YELLOW.getRGB();
    private int licznikLiter;
    private int licznikWierszy;
    
    
    public static BufferedImage szkieletyzacja1 ( BufferedImage in){
        
        List<BufferedImage> listaWierszy = new ArrayList<>();
        List<BufferedImage> listaLiter = new ArrayList<>();
        HashMap<String,ArrayList<Integer>> czarneWiersze = new HashMap<>();
        
        listaWierszy = wydobycieElementow(in,1);
        return listaWierszy.get(0);
    }
    /*
    public static List<BufferedImage> wydobycieElementow ( BufferedImage in,int dim){
     
        BufferedImage outtmp    = new BufferedImage( in.getWidth(), in.getHeight(), in.getType());
        BufferedImage out       = new BufferedImage( in.getWidth(), in.getHeight(), in.getType());
        outtmp                  = RGB.powiekszKopiujac(in,0);
        out                     = RGB.powiekszKopiujac(in,0);
        
        int width;
        int heigth;
        width=in.getWidth();
        heigth=in.getHeight();
        
        int waga=0; 
         
        int r,g,b;
        int straznik = 0;
       
        //Szukanie po wierszach i kopiowanie do nowego obrazka
        /**
         * Jesli posiada czarny piksel tzn ze jest litera 
         * i do listy dodajemy wartosc tego wiersza pozostale wiersze
         * wypelniamy na zolto
         */
        /*
        HashMap<Integer,ArrayList<Integer>> czarneWiersze = new HashMap<>();
        List<Integer> czarne = new ArrayList<>();
        List<BufferedImage> wierszeObrazy = new ArrayList<>();
        //wiersze ktore posiadaja czarne piksele
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {    
                if(RGB.getR(outtmp.getRGB(i, j))==0){
                    if(!czarne.contains(j))
                        czarne.add(j);
                }    
            }
        }
        //wypelnianie pozostalych wierszy
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {    
                if(!czarne.contains(j))
                    out.setRGB(i, j,zolty);
            }
        }
        
        czarne.sort(null);
        
        int first,current,previous;
        int licznikwierszy = 1;
        first   = czarne.get(0);
        previous= czarne.get(0);
        
        for (int i = 0; i < czarne.size(); i++) {
            //System.out.println(czarne.get(i));
            current = czarne.get(i);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
            
            if(current-previous>1 || i==czarne.size()-1){
                //Jesli koniec petli ostatni wiersz obrazka
                if(i==czarne.size()-1)previous=current;
                
                BufferedImage temp = new BufferedImage(out.getWidth(),(previous-first+1),out.getType());
                for (int k = 0; k < width; k++) {
                    for (int l = first,m=0; l <= previous; l++) {
                        temp.setRGB(k, m++, out.getRGB(k, l));
                    }
                }
                
                wierszeObrazy.add(temp);
                first = current;
            }
            previous = current;
        }
        
        try {
            for (int i = 0; i < wierszeObrazy.size(); i++) {
                ImageIO.write(wierszeObrazy.get(i),"jpg",new File("out/wiersz"+(i+1)+".jpg"));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        
        return wierszeObrazy;
    }
    */

    public static List<BufferedImage> wydobycieElementow ( BufferedImage in,int dim){
     
        BufferedImage outtmp    = new BufferedImage( in.getWidth(), in.getHeight(), in.getType());
        BufferedImage out       = new BufferedImage( in.getWidth(), in.getHeight(), in.getType());
        outtmp                  = RGB.powiekszKopiujac(in,0);
        out                     = RGB.powiekszKopiujac(in,0);
        
        int width;
        int heigth;
        width=in.getWidth();
        heigth=in.getHeight();
        
        int waga=0; 
         
        int r,g,b;
        int straznik = 0;
       
        //Szukanie po wierszach i kopiowanie do nowego obrazka
        /**
         * Jesli posiada czarny piksel tzn ze jest litera 
         * i do listy dodajemy wartosc tego wiersza pozostale wiersze
         * wypelniamy na zolto
         */
        
        HashMap<Integer,ArrayList<Integer>> czarneWiersze = new HashMap<>();
        List<Integer> czarne                = new ArrayList<>();    
        List<BufferedImage> wierszeObrazy   = new ArrayList<>();

        //wiersze ktore posiadaja czarne piksele
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {    
                if(RGB.getR(outtmp.getRGB(i, j))==0){
                    if(!czarne.contains(i))
                        czarne.add(i);
                }  
            }
        }
        //wypelnianie pozostalych wierszy
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {    
                if(!czarne.contains(i))
                    out.setRGB(i, j,zolty);
            }
        }
        czarne.sort(null);
        
        int first,previous,current,count,max;
        first       = czarne.get(0);
        previous    = czarne.get(0);
        max         = heigth;
        count       = 0;
        
        for(int k:czarne){
            current = k;    
            if(current-previous>1){
               
                for (int j = first; j <previous+1; j++) {
                    for (int i = 0; i <heigth; i++) {
                        if(RGB.getB(out.getRGB(j, i))!=0){
                            count=i;
                            if(count<max)
                                max = count;
                            
                        }                           
                    }
                }
                for (int i = 0; i <10; i++) {
                    for (int j = first; j <previous; j++) {
                        out.setRGB(j, i, czerwony);   
                    }
                }
             max = out.getHeight();
             first=current;
            }
            previous=current;
        }
        
        wierszeObrazy.add(out);
        
        try {
            for (int i = 0; i < wierszeObrazy.size(); i++) {
                ImageIO.write(wierszeObrazy.get(i),"jpg",new File("out/wiersz"+(i+1)+".jpg"));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        
        return wierszeObrazy;
    }
    
    public static BufferedImage szkieletyzacja2 ( BufferedImage in){
    
        BufferedImage out=new BufferedImage( in.getWidth(), in.getHeight(), in.getType());
        out  = RGB.powiekszKopiujac(in,0);
        int width;
        int heigth;
        width=in.getWidth();
        heigth=in.getHeight();
        
        return out;
    }
 }

