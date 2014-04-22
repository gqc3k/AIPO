package mainPackage;

import algorytmy.krawedziowe;
import algorytmy.odszumianie;
import algorytmy.podstawoweOperacje;
import algorytmy.szkieletyzacja;
import algorytmy.szkieletyzacjaLiter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

public class runFile {
    
    private static final int czarny = Color.BLACK.getRGB();
    private static final int bialy = Color.WHITE.getRGB();
    private static final int czerwony = Color.RED.getRGB();
    
    //STUFF to K3M
    
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
       {1,1,1},
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
    
    
   
    
    private static final int [][] maska = {{128, 64, 32}, {1, 0, 16}, {2, 4, 8}};
    
    
    private final String[] operacje = {"Negatyw","Normalizacja Histogramu","Odcienie szarości przez średnią","Sepia",
        "Normalizacja histogramu dla szarości przez średnią","Odcienie szarości naturalne","Normalizacja histogramu dla szarości naturalnych",
        "Skalowanie 0.4, a potem 2.5 gotowe","Progowanie przez średnią globalną","Progowanie przez średnią lokalną z sąsiedztwem szerokości",
        "Progowanie przez średnią lokalną z sąsiedztwem szerokości  i odchyleniem od średniej globalnej","Filtr splotowy",
        "Zmiana jasności","Krzyż Robertsa","Filtr Sobela","Obrót","Szum typu sól i pieprz","Szum Rónomierny",
        "Odszumianie : filtr za pomocą średniej","Odszumianie : filtr medianowy","Odszumianie:Uleszony filtr medianowy","Scienianie K3M","Scienianie KMM","Scienianie przez maske",
        "Fragmentacja liter metoda"
    };
    
    private BufferedImage out;
    
    public String[] getOperations(){
        return this.operacje;
    }
    
    public BufferedImage getOutImage(){
        return out;
    }
    
    void run(BufferedImage in,int choose,int param) {
        
        try {
            switch(choose){
                case 0:{//negatyw
                    out = podstawoweOperacje.negatyw(in);
                    //ImageIO.write(out,"jpg",new File("out/01-01.jpg"));
                    System.out.println("Negatyw gotowy!");
                }                    break;
                case 1:{//normalizacja hist kolor
                    out = podstawoweOperacje.histnorm(in);
                    //ImageIO.write(out,"jpg",new File("out/01-02.jpg"));
                    System.out.println("Normalizacja histogramu kolorowego gotowa!");
                }                    break;
                case 2:{//odcienieszarosci global
                    out = podstawoweOperacje.szarosciS(in);
                    //ImageIO.write(out,"jpg",new File("out/01-03.jpg"));
                    System.out.println("Odcienie szarości przez średnią gotowe!");
                }                    break;
                case 3:{//sepia
                    out = podstawoweOperacje.sepia(in,param);
                    //ImageIO.write(out,"jpg",new File("out/sepia40.jpg"));
                    System.out.println("Sepia w = 40 - gotowe!");
                }                    break;
                case 4:{//norm hist dla szarego
                    out = podstawoweOperacje.szarosciN(in);
                    out = podstawoweOperacje.histnorm(out);
                    //ImageIO.write(out,"jpg",new File("out/NormalizacjaHistogramu.jpg"));
                    System.out.println("Normalizacja histogramu dla szarości przez średnią gotowa!");
                }                    break;
                case 5:{//odcienie szarosci zwykle
                    out = podstawoweOperacje.szarosciN(in);
                    //ImageIO.write(out,"jpg",new File("out/01-05.jpg"));
                    System.out.println("Odcienie szarości naturalne gotowe!");
                }                    break;
                case 6:{//normalizacja hist szarego
                    out = podstawoweOperacje.szarosciN(in);
                    out = podstawoweOperacje.histnorm(out);
                    //ImageIO.write(out,"jpg",new File("out/01-06.jpg"));
                    System.out.println("Normalizacja histogramu dla szarości naturalnych gotowa!");
                }                    break;
                case 7:{//skalowanie
                    out = podstawoweOperacje.skalowanie(in,(float)(0.4));
                    out = podstawoweOperacje.skalowanie(out,(float)(2.5));
                    //ImageIO.write(out,"jpg",new File("out/Skala.jpg"));
                    System.out.println("Skalowanie 0.4, a potem 2.5 gotowe!");
                }                    break;
                case 8:{//progowanie globalne
                    BufferedImage szary = podstawoweOperacje.szarosciS(in);
                    out = podstawoweOperacje.progowanieSG(szary);
                    //ImageIO.write(out,"jpg",new File("out/01-10.jpg"));
                    System.out.println("Progowanie przez średnią globalną gotowe!");
                }                case 9:{//Progowanie przez średnią lokalną z sąsiedztwem szerokości
                    int size[] = {2,5,7,10,12};
                    BufferedImage szary = podstawoweOperacje.szarosciS(in);
                    for(int pom_licz = 0; pom_licz < size.length;pom_licz++) {
                        out = podstawoweOperacje.progowanieL(szary, size[pom_licz]);
                        //ImageIO.write(out,"jpg",new File("out/01-11-" + (2*size[pom_licz]+1) + ".jpg"));
                        System.out.println("Progowanie przez średnią lokalną z sąsiedztwem szerokości " +  (2*size[pom_licz]+1) + " gotowe!");
                    }
                }                    break;
                case 10:{//progowanie lokalne
                    int size2[] = {15,25,35};
                    int so = 7;
                    BufferedImage szary = podstawoweOperacje.szarosciS(in);
                     for(int pom_licz = 0; pom_licz < size2.length;pom_licz++) {
                         out = podstawoweOperacje.progowanieLSG(szary, so, size2[pom_licz]);
                         //ImageIO.write(out,"jpg",new File("out/01-12-" + size2[pom_licz] + ".jpg"));
                         System.out.println("Progowanie przez średnią lokalną z sąsiedztwem szerokości " + (2*so+1) + " i odchyleniem od średniej globalnej o " +  size2[pom_licz] + " gotowe!");
                     }
                }                    break;
                case 11:{//filtr spolotowy
                    int size3[][][] =  {{{ 1, 1, 1},{ 1, 1, 1},{ 1, 1, 1}},
                                        {{ 1, 1, 1},{ 1, 2, 1},{ 1, 1, 1}},
                                        {{ 1, 2, 1},{ 2, 4, 2},{ 1, 2, 1}},
                                        {{ 0,-1, 0},{-1, 5,-1},{ 0,-1, 0}},
                                        {{-1,-1,-1},{-1, 9,-1},{-1,-1,-1}},
                                        {{ 1,-2, 1},{-2, 5,-2},{ 1,-2, 1}}
                    };
                    for(int pom_licz = 0; pom_licz < size3.length;pom_licz++) {
                        out = podstawoweOperacje.filtrSplotowy(in, size3[pom_licz]);
                        ImageIO.write(out,"jpg",new File("out/01-14-splot" + pom_licz + ".jpg"));
                        System.out.println("Filtr splotowy nr " +  pom_licz + " gotowy!");
                    }
                }                    break;
                    
                case 12:{//zmiana jasnosci
                    out = krawedziowe.zmianaJasnosci(in,param);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Zmiana jasnosci gotowa!");
                }                    break;
                case 13:{//roberts
                    out = krawedziowe.krzyzRobertsa(in,param);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Krzyż Robertsa gotowy!");
                }                    break;
                case 14:{//sobel
                    out = krawedziowe.filtrSobela(in,param);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Filtr Sobela gotowy!");
                }                     break;
                case 15:{//obrot
                    out = krawedziowe.obrot(in,param);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Obrot gotowy!");
                }                  
                    break;
                case 16:{//sol pieprz
                    out = odszumianie.szum1(in,param);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Szum sol pieprz gotowy!");
                }                
                    break;
                case 17:{//rownomierny1
                    out = odszumianie.szum2(in,param);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Szum Rownomierny!");
                }                
                    break;
                case 18:{//odszumienie1
                    out = odszumianie.odszumianie1(in,param);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Odszumianie srednia gotowe!");
                }                        break;
                case 19:{//odszumienie2
                    out = odszumianie.odszumianie2(in,param);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Odszumiania mediana gotowe!");
                }                        break;
                case 20:{//odszumienie3
                    out = odszumianie.odszumianie3(in,param);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Odszumienie ulepszana mediana gotowe!");
                }                    break;
                case 21:{//scienianie K3M
                    out = szkieletyzacja.K3M(in);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Algorytm K3M gotowy!");
                };
                    break;
                case 22:{//scienianie KMM
                    out = szkieletyzacja.KMM(in);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Algorytm KMM gotowy!");
                };
                    break;
                case 23:{//scienianie przez maske
                    out = szkieletyzacja.Maska(in);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Algorytm Szkieletyzacji przez maske gotowy!");
                };
                    break;
                case 24:{//szkieletyzacja liter 1
                    out = szkieletyzacjaLiter.szkieletyzacja1(in);
                    //ImageIO.write(out,"jpg",new File("out/jasnosc.jpg"));
                    System.out.println("Szkieletyzacja liter metoda pierwsza gotowe!");
                };
               
                /** New function add here with case STRING : {} 
                 * 
                 * 
                 */
                
             }
        } catch(IOException e) {
            System.out.println("W module Lab01 padło: " + e.toString());
        }
        
    }
        
}
