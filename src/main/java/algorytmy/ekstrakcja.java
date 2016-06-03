/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorytmy;

import java.awt.image.BufferedImage;
import algorytmy.szkieletyzacja;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import mainPackage.RGB;
import algorytmy.podstawoweOperacje;
import algorytmy.segmentacjaLiter;

/**
 *
 * @author Greko
 */
public class ekstrakcja {
    
    private static final int czarny     = Color.BLACK.getRGB();
    private static final int bialy      = Color.WHITE.getRGB();
    private static final int czerwony   = Color.RED.getRGB();
    private static final int zielony    = Color.GREEN.getRGB();
    private static final int niebieski  = Color.BLUE.getRGB();
    private static final int zolty      = Color.YELLOW.getRGB();
   
    
    HashMap<String, ArrayList<Integer>> baza;
    /**
     *
     * @param in
     * @param nazwa
     */
    public static void dodajtreningowy(BufferedImage in,String opis){
        
    }
    
    public static BufferedImage cechyMetoda1(BufferedImage in){
        
       
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
        BufferedImage outtmp = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
        out = RGB.powiekszKopiujac(in, 0);
        outtmp = podstawoweOperacje.negatyw(outtmp);
        int width;
        int height;
        width   = in.getWidth();
        height  = in.getHeight();
        int rozmiarMaski    = 3;
        int [][] maska      = new int[rozmiarMaski][rozmiarMaski];
        /**
         * @wektor1  zawiera punkty któe mają więcej niż 2 sąsiadów oraz skrajne punkty litery
         */
        ArrayList<HashMap<Integer,Integer>> wektor1 = new ArrayList<>();
        HashMap<Integer,Integer> mapa  = new HashMap<>();
        
        
        int sumaSasiadow = 0;
        
        out = szkieletyzacja.KMM(in);
        out = segmentacjaLiter.wydobycieLiter(out);
        out = segmentacjaLiter.wycinanieLitery(out);
        
        
        
//        for(int i=2;i<width-2;i++){
//            for (int j = 2; j < height-2; j++) {
//                if(RGB.getB(out.getRGB(i, j))==0){
//                    maska = RGB.zwrocMaske(rozmiarMaski,out,i,j);
//                    
//                    sumaSasiadow = 0;
//                    
//                    for(int[] x : maska){
//                        for(int y:x){
//                            if(RGB.getR(y)==0){
//                                sumaSasiadow++;
//                            }
//                        }
//                    }
//                    
//                    //3 zamiast 2 zeby uproscic kod - licze razem z pikselem
//                    //ktory aktualnie analizuje
//                    if(sumaSasiadow>3 || sumaSasiadow<3)
//                    {
//                        outtmp.setRGB(i, j, czarny);
//                        mapa.put(i,j);
//                    }
//                    
//                    
//                }
//                
//            }
//        }
        
        
        
        return out;
    }
    
}
