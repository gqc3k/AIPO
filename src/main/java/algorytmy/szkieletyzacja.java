/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorytmy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import mainPackage.RGB;

/**
 *
 * @author Robert Gaca
 */
public class szkieletyzacja {
    
        private static final int czarny = Color.BLACK.getRGB();
        private static final int bialy = Color.WHITE.getRGB();
        private static final int czerwony = Color.RED.getRGB();
        private static final int zielony = Color.GREEN.getRGB();
        private static final int niebieski = Color.BLUE.getRGB();
        
    
        private static final int [][] A = {
       {
           3, 6, 7, 12, 14, 15, 24, 28, 30, 31, 48, 56, 60, 62, 63, 96, 112, 120, 124,
            126, 127, 129, 131, 135,143, 159, 191, 192, 193, 195, 199, 207, 223, 224,
            225, 227, 231, 239, 240, 241, 243, 247, 248, 249, 251, 252, 253, 254
       },
        {
            7, 14, 28, 56, 112, 131, 193, 224
        },
        {
            7, 14, 15, 28, 30, 56, 60, 112, 120, 131, 135, 193, 195, 224, 225, 240
        },
        {
            7, 14, 15, 28, 30, 31, 56, 60, 62, 112, 120, 124, 131, 135, 143, 193, 195,
            199, 224, 225, 227, 240, 241, 248},
        {
            7, 14, 15, 28, 30, 31, 56, 60, 62, 63, 112, 120, 124, 126, 131, 135, 143,
            159, 193, 195, 199, 207, 224, 225, 227, 231, 240, 241, 243, 248, 249, 252
        },
        {
            7, 14, 15, 28, 30, 31, 56, 60, 62, 63, 112, 120, 124, 126, 131, 135, 143,
            159, 191, 193, 195, 199, 207, 224, 225, 227, 231, 239, 240, 241, 243, 248,
            249, 251, 252, 254
        }
        };

    private static final int [] A1pix = {

            3, 6, 7, 12, 14, 15, 24, 28, 30, 31, 48, 56, 60, 62, 63, 96, 112, 120,
            124, 126, 127, 129, 131, 135, 143, 159, 191, 192, 193, 195, 199, 207,
            223, 224, 225, 227, 231, 239, 240,241, 243, 247, 248, 249, 251, 252, 253, 254
    };

    private static final int [] A12pix = {

            3, 6, 7, 12, 14, 15, 24, 28, 30, 31, 48, 56, 60, 62, 63, 96, 112, 120,
            124, 126, 127, 129, 131, 135, 143, 159, 191, 192, 193, 195, 199, 207,
            223, 224, 225, 227, 231, 239, 240,241, 243, 247, 248, 249, 251, 252, 253, 254
    };

    //STUFF to KMM
    
    private static final int [][][] K = {
    {
       {2,0,2},
       {2,1,2},
       {1,1,1},
    },
    {
       {2,2,1},
       {0,1,1},
       {2,2,1},
    },
    {
       {1,1,1},
       {2,1,2},
       {2,0,2},
    },
    {
       {1,2,2},
       {1,1,0},
       {1,2,2},
    },
    };
    
    private static final int [][]   maska       = {{128, 64, 32}, {1, 0, 16}, {2, 4, 8}};
    private static final int []     czworki     = {3, 6, 7, 12, 14, 15, 24, 28, 30, 48, 56, 60, 96, 112, 120, 129, 131, 135, 192, 193, 195, 224, 225, 240};    
    private static final int []     wyciecia    = {3, 5, 7, 12, 13, 14, 15, 20, 21, 22, 23, 28, 29, 30, 31, 48, 52, 53, 54, 55, 56, 60, 61, 62, 63, 65, 67, 
        69, 71, 77, 79, 80, 81, 83, 84, 85, 86, 87, 88, 89, 91, 92, 93, 94, 95, 97, 99, 101, 103, 109, 111, 112, 113, 115, 116, 117, 118, 119, 120, 
        121, 123, 124, 125, 126, 127, 131, 133, 135, 141, 143, 149, 151, 157, 159, 181, 183, 189, 191, 192, 193, 195, 197, 199, 205, 207, 208, 209,
        211, 212, 213, 214, 215, 216, 217, 219, 220, 221, 222, 223, 224, 225, 227, 229, 231, 237, 239, 240, 241, 243, 244, 245, 246, 247, 248, 249, 251, 252, 253, 254, 255};
        
    
        /**
        * 
        * @param in obraz wejsciowy do scieniania
        * @return out obraz scieniony
        */
        public static BufferedImage K3M ( BufferedImage in){
     
        BufferedImage out=new BufferedImage( in.getWidth(), in.getHeight(), in.getType());
        BufferedImage out1;//=RGB.powiekszBialymi(in, 1);
        out  = RGB.powiekszKopiujac(in,0);
        out1 = RGB.powiekszKopiujac(out,0);
        int width;
        int heigth;
        width   = in.getWidth();
        heigth  = in.getHeight();
        int waga= 0;
         
         
        boolean zmiany;
        do {
            zmiany = false;
            int r,g,b;
            int straznik = 0;
            //szukanie brzegowych pikseli i zamienianie ich na czerwone
 
            for (int i = 3; i < width - 3; i++) {
                for (int j = 3; j < heigth - 3; j++) {
                  //
                            
                            if(RGB.getB(out.getRGB(i, j))==0){
                             
                            int a [][] = {  {RGB.getB(out.getRGB(i-1, j-1))/255,   RGB.getB(out.getRGB(i, j-1))/255,  RGB.getB(out.getRGB(i+1, j-1))/255},
                                            {RGB.getB(out.getRGB(i-1, j))/255,     RGB.getB(out.getRGB(i, j))/255,    RGB.getB(out.getRGB(i+1, j))/255},
                                            {RGB.getB(out.getRGB(i-1, j+1))/255,   RGB.getB(out.getRGB(i, j+1))/255,  RGB.getB(out.getRGB(i+1, j+1))/255}
                            };
                                 
                              
                                    waga=0;
                                     
                                    for(int k=0;k<3;k++){
                                        for(int l=0;l<3;l++){
                                            waga+=maska[k][l]*Math.abs(a[k][l]-1);
                                        }
                                    }
                                     
                                    for(int x:A[0]){
                                        if(waga==x){
                                            out.setRGB(i,j,new Color(255,0,0).getRGB());
                                        }
                                   }
                                     
                            }
                }      
             }
             
//          pierwszy krok w celu wycięcia pikseli
            for(int licz=1;licz<6;licz++) {
                for (int i=3; i< width-3; i++){
                    for ( int j=3; j< heigth-3; j++){
                        waga=0;
                     
                        if((RGB.getR(out.getRGB(i, j)))==255 && (RGB.getB(out.getRGB(i, j)))==0){
                           
                            int a [][] = {  {RGB.getB(out.getRGB(i-1, j-1))/255,   RGB.getB(out.getRGB(i, j-1))/255,  RGB.getB(out.getRGB(i+1, j-1))/255},
                                            {RGB.getB(out.getRGB(i-1, j))/255,     RGB.getB(out.getRGB(i, j))/255,    RGB.getB(out.getRGB(i+1, j))/255},
                                            {RGB.getB(out.getRGB(i-1, j+1))/255,   RGB.getB(out.getRGB(i, j+1))/255,  RGB.getB(out.getRGB(i+1, j+1))/255}
                            };
                             
                            for(int k=0;k<3;k++){
                                for(int l=0;l<3;l++){
                                    waga+=maska[k][l]*Math.abs(a[k][l]-1);
                                }
                            }
 
                            for(int x:A[licz]){
                                if(waga==x){
                                    out.setRGB(i,j,new Color(255,255,255).getRGB());
                                    zmiany = true;
                                }
                            }
                        }
                         
                         
                    }
                }
            }
             
             
     
            //zamiana pozostałych czerwonych pikseli na czarne
            for (int i=1; i<width-2 ;i++){
                for ( int j=1; j< heigth-2; j++){
             // wartosc red  = 255 ale pozostale piksela musza byc 0 , dla bialego wszystkie 255 wiec red rowniez
             // to ze red = 255 niewystarczajace bo wszystkie biale rowniez beda sie kwalifikowaly
                    if(RGB.getR(out.getRGB(i, j))==255 && RGB.getG(out.getRGB(i, j))==0){
                        out.setRGB(i,j,new Color(0,0,0).getRGB());
                    }
                }
            }
              straznik++;
             if(straznik%10==0){
                 try{
                     ImageIO.write(out,"bmp", new File(straznik+"out.bmp"));
                 }catch(Exception e)
                 {
                     e.printStackTrace();
                 }
             }
        }
       
        //wszystkie czarne piksele, których waga znajduje sie w masce A1pix
        //sa usuwane dopoki zachodza zmiany powtarzamy
        while (zmiany==true);
        
        do {
         zmiany=false;
         for ( int i=3; i<width-3; i++){
                for ( int j=3; j<heigth-3; j++){
                    // tu uzupełnij kod
                     
                    if(RGB.getB(out.getRGB(i, j))==0){
                            waga = 0;
                            int a [][] = {  {RGB.getB(out.getRGB(i-1, j-1))/255,   RGB.getB(out.getRGB(i, j-1))/255,  RGB.getB(out.getRGB(i+1, j-1))/255},
                                            {RGB.getB(out.getRGB(i-1, j))/255,     RGB.getB(out.getRGB(i, j))/255,    RGB.getB(out.getRGB(i+1, j))/255},
                                            {RGB.getB(out.getRGB(i-1, j+1))/255,   RGB.getB(out.getRGB(i, j+1))/255,  RGB.getB(out.getRGB(i+1, j+1))/255}
                                    };
 
                            for(int k=0;k<3;k++){
                                for(int l=0;l<3;l++){
                                    waga+=maska[k][l]*Math.abs(a[k][l]-1);
                                }
                            }
 
                            for(int x:A1pix){
                                if(waga==x){
                                    out.setRGB(i,j,new Color(255,255,255).getRGB());
                                    zmiany = true;
                                }
                            }
                        }
                }
        }
       }
        while (zmiany==true);
        return out;
    }
        
    /**
    * 
    * @param in obraz wejsciowy
    * @param param parametr - mozliwosc jego nie podania
    * @return out obraz szkieletyzacja przez maske
     */    
    public static BufferedImage Maska(BufferedImage in,int... param){
        
        long start_time    = System.currentTimeMillis();
        BufferedImage out  = new BufferedImage( in.getWidth(), in.getHeight(), in.getType());
        int     width;
        int     heigth;
        
        width           = in.getWidth();
        heigth          = in.getHeight();
        
        in=RGB.powiekszBialymi(in,1);
        out=RGB.powiekszKopiujac(in,0);
        
        int waga        = 0;
        int straznik    = 0;
        int licznik     = 0;
        
        boolean zmiany;
        
        do{
            zmiany=false;
            
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < heigth; j++) {
                            
                            if(RGB.getR(in.getRGB(i, j))==0){
                             
                            int a [][] = {  {RGB.getB(in.getRGB(i-1, j-1))/255,   RGB.getB(in.getRGB(i, j-1))/255,  RGB.getB(in.getRGB(i+1, j-1))/255},
                                            {RGB.getB(in.getRGB(i-1, j))/255,     RGB.getB(in.getRGB(i, j))/255,    RGB.getB(in.getRGB(i+1, j))/255},
                                            {RGB.getB(in.getRGB(i-1, j+1))/255,   RGB.getB(in.getRGB(i, j+1))/255,  RGB.getB(in.getRGB(i+1, j+1))/255}
                            };
                            
                                for(int index=0;index<4;index++){
                                    waga = 0;
                                    for(int k=0;k<3;k++){
                                        for(int l=0;l<3;l++){
                                            if(K[index][k][l]==Math.abs(a[k][l]-1))
                                                waga++;
                                        }
                                    }
                                    if(waga==5){
                                        out.setRGB(i, j,bialy);
                                        zmiany = true;
                                    }
                                }   
                            }
                            
                }      
             }
            in = RGB.powiekszKopiujac(out,0);
        }while(zmiany==true);    
        
        long end_time = System.currentTimeMillis();
        System.out.println("Wykonanie w : "+(end_time-start_time)+"ms");
        return out;
    }

    /**
     *  oznaczenie pikseli w maskach i obrazie
     *  0 bialy
     *  1 czarny
     *  2 zielony
     *  3 niebieski
     *  4 czerwony
     * 
     * @param in
     * @return 
     */
    public static BufferedImage KMM ( BufferedImage in){
        long start_time = System.currentTimeMillis();
        
        BufferedImage   out       = new BufferedImage( in.getWidth(), in.getHeight(), in.getType());
        BufferedImage   outtmp    = new BufferedImage( in.getWidth(), in.getHeight(), in.getType());
        BufferedImage   outbin    = new BufferedImage( in.getWidth(), in.getHeight(), in.getType());
        
        int     width;
        int     heigth;
        
        width           = in.getWidth();
        heigth          = in.getHeight();
        
        in      = RGB.powiekszBialymi(in,1);
        out     = RGB.powiekszKopiujac(in,0);
        outtmp  = RGB.powiekszKopiujac(in,0);
        outbin  = RGB.powiekszKopiujac(in,0);
        
        int waga        = 0;
        boolean zmiany  = false;
        int straznik    = 0;
        int rozmiarMaski= 3;
        int r,g,b;
        int [][] a      = new int[rozmiarMaski][rozmiarMaski];
        
         
        do{
            
            zmiany = false;
            out = RGB.powiekszKopiujac(outtmp,0);
            for (int i = 1; i < width-1; i++) {
                for (int j = 1; j < heigth-1; j++) {
                    if(RGB.getR(out.getRGB(i, j))==0){
                        //krok 1
                        //tworzenie dwuwymiarowej tablicy wartosci koloru sasiadow o wielkosc rozmiarMaski x rozmiarMaski
                        //maska binarna 1 - czarny   0 - bialy
                        for(int q=0;q<rozmiarMaski;q++){
                             for(int w=0;w<rozmiarMaski;w++){
                               a[q][w] = RGB.binar(RGB.getR(out.getRGB(i-rozmiarMaski/2+q,j-rozmiarMaski/2+w)));  
                             }
                        }

                        //krok 2 oznaczanie pikseli w pierwszej kolejnosci
                        //jezeli sasiedzi po bokach gora dol to zielony maska = 2
                        if(a[0][1]==0 || a[2][1]==0 || a[1][0]==0 || a[1][2]==0)
                                outtmp.setRGB(i, j, zielony);

                    }    
                }            
            }
    out = RGB.powiekszKopiujac(outtmp,0);
             for (int i = 1; i < width-1; i++) {
                for (int j = 1; j < heigth-1; j++) {
                    if(RGB.getG(out.getRGB(i, j))==0){

                        for(int q=0;q<rozmiarMaski;q++){
                             for(int w=0;w<rozmiarMaski;w++){
                               a[q][w] = RGB.binar(RGB.getR(out.getRGB(i-rozmiarMaski/2+q,j-rozmiarMaski/2+w)));  
                             }
                        }

                        //krok 2 oznaczanie pikseli w pierwszej kolejnosci
                        //jezeli corners to niebieski maska = 4
                        if(a[0][0]==0 || a[0][2]==0 || a[2][0]==0 || a[2][2]==0)
                                outtmp.setRGB(i, j, niebieski);

                    }    
                }            
            }

            out = RGB.powiekszKopiujac(outtmp,0);
            for (int i = 1; i < width-1; i++) {
                for (int j = 1; j < heigth-1; j++) {
                    // Wszystkie zielone piksele
                    if(RGB.getR(out.getRGB(i, j))==0 && RGB.getG(out.getRGB(i, j))==255 && RGB.getB(out.getRGB(i, j))==0){
                        waga = 0;
                        for(int q=0;q<rozmiarMaski;q++){
                             for(int w=0;w<rozmiarMaski;w++){
                               a[q][w] = RGB.binar(RGB.getR(outbin.getRGB(i-rozmiarMaski/2+q,j-rozmiarMaski/2+w)));  
                             }
                        }

                        for(int q=0;q<rozmiarMaski;q++){
                             for(int w=0;w<rozmiarMaski;w++){
                                 waga+=a[q][w]*maska[q][w];
                             }
                        }
                        for(int x:czworki){
                            if(waga==x)
                                outtmp.setRGB(i, j, czerwony);
                        }

                    }    
                }            
            }
            
            out=RGB.powiekszKopiujac(outtmp,0);
            for(int color = 0;color<3;color++){
            //Usuwanie 4czerwone 2zielone 3niebieskie
                r=0;g=0;b=0;
                if(color==0){r=255;g=0;b=0;}
                if(color==1){r=0;g=255;b=0;}
                if(color==2){r=0;g=0;b=255;}

                for (int i = 1; i < width-1; i++) {
                    for (int j = 1; j < heigth-1; j++) {
                        // Wszystkie kolorowe piksele
                        if(RGB.getR(out.getRGB(i, j))==r && RGB.getG(out.getRGB(i, j))==g && RGB.getB(out.getRGB(i, j))==b){
                            waga = 0;
                            for(int q=0;q<rozmiarMaski;q++){
                                 for(int w=0;w<rozmiarMaski;w++){
                                   a[q][w] = RGB.binar(RGB.getR(outbin.getRGB(i-rozmiarMaski/2+q,j-rozmiarMaski/2+w)));  
                                 }
                            }

                            for(int q=0;q<rozmiarMaski;q++){
                                 for(int w=0;w<rozmiarMaski;w++){
                                     waga+=a[q][w]*maska[q][w];
                                 }
                            }
                            for(int x:wyciecia){
                                if(waga==x){
                                    outtmp.setRGB(i, j,bialy);
                                    outbin.setRGB(i, j,bialy);
                                    straznik++;
                                    zmiany=true;
                                }
                                
                               
                            }


                        }    
                    }            
                }
            }
            
        }while(zmiany==true);
        long end_time = System.currentTimeMillis();
        System.out.println("Wykonanie w : "+(end_time-start_time)+"ms");
        
        return outbin;
    }
    
}