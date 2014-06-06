/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorytmy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import mainPackage.RGB;

/**
 *
 * @author Robert Gaca
 */
public class segmentacjaLiter {

    private static final int czarny     = Color.BLACK.getRGB();
    private static final int bialy      = Color.WHITE.getRGB();
    private static final int czerwony   = Color.RED.getRGB();
    private static final int zielony    = Color.GREEN.getRGB();
    private static final int niebieski  = Color.BLUE.getRGB();
    private static final int zolty      = Color.YELLOW.getRGB();
    private int licznikLiter;
    private int licznikWierszy;

    public static BufferedImage szkieletyzacja1(BufferedImage in) {

        List<BufferedImage> listaWierszy = new ArrayList<>();
        List<BufferedImage> listaLiter = new ArrayList<>();
        HashMap<String, ArrayList<Integer>> czarneWiersze = new HashMap<>();
        BufferedImage out;

        listaWierszy = wydobycieWierszy(in);

        for (BufferedImage l : listaWierszy) {
            listaLiter.add(wydobycieLiter(l));
        }

        out = joiner(listaLiter);
        return out;
    }

    public static BufferedImage joiner(List<BufferedImage> in) {
        BufferedImage out;
        int width = 0;
        int height = 0;
        int startHeight = 0;
        int endHeight = 0;

        for (BufferedImage im : in) {
            width = im.getWidth();
            height += im.getHeight();
        }

        out = new BufferedImage(width, height, in.get(0).getType());

        startHeight = 0;
        endHeight = 0;

        for (BufferedImage im : in) {
            endHeight += im.getHeight();

            for (int i = 0; i < width; i++) {
                for (int j = startHeight, k = 0; j < endHeight; j++, k++) {
                    out.setRGB(i, j, im.getRGB(i, k));
                }
            }

            startHeight += im.getHeight();
        }

        return out;
    }

    public static List<BufferedImage> wydobycieWierszy(BufferedImage in) {

        BufferedImage outtmp = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
        outtmp = RGB.powiekszKopiujac(in, 0);
        out = RGB.powiekszKopiujac(in, 0);

        int width;
        int heigth;
        width = in.getWidth();
        heigth = in.getHeight();

        int waga = 0;
        int r, g, b;
        int straznik = 0;

        //Szukanie po wierszach i kopiowanie do nowego obrazka
        /**
         * Jesli posiada czarny piksel tzn ze jest litera i do listy dodajemy
         * wartosc tego wiersza pozostale wiersze wypelniamy na zolto
         */
        HashMap<Integer, ArrayList<Integer>> czarneWiersze = new HashMap<>();
        List<Integer> czarne = new ArrayList<>();
        List<BufferedImage> wierszeObrazy = new ArrayList<>();
        //wiersze ktore posiadaja czarne piksele
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                if (RGB.getR(outtmp.getRGB(i, j)) == 0) {
                    if (!czarne.contains(j)) {
                        czarne.add(j);
                    }
                }
            }
        }
        //wypelnianie pozostalych wierszy
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                if (!czarne.contains(j)) {
                    out.setRGB(i, j, zolty);
                }
            }
        }

        Collections.sort(czarne);

        int first, current, previous;
        int licznikwierszy = 1;
        first = czarne.get(0);
        previous = czarne.get(0);

        for (int i = 0; i < czarne.size(); i++) {
            //System.out.println(czarne.get(i));
            current = czarne.get(i);

            if (current - previous > 1 || i == czarne.size() - 1) {
                //Jesli koniec petli ostatni wiersz obrazka
                if (i == czarne.size() - 1) {
                    previous = current;
                }

                BufferedImage temp = new BufferedImage(out.getWidth(), (previous - first + 1), out.getType());
                for (int k = 0; k < width; k++) {
                    for (int l = first, m = 0; l <= previous; l++) {
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
                ImageIO.write(wierszeObrazy.get(i), "jpg", new File("out/wiersz" + (i + 1) + ".jpg"));
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return wierszeObrazy;
    }

    public static BufferedImage wydobycieLiter(BufferedImage in) {

        BufferedImage outtmp = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
        outtmp = RGB.powiekszKopiujac(in, 0);
        out = RGB.powiekszKopiujac(in, 0);

        int width;
        int heigth;
        width = in.getWidth();
        heigth = in.getHeight();

        int waga = 0;

        int r, g, b;
        int straznik = 0;

        //Szukanie po wierszach i kopiowanie do nowego obrazka
        /**
         * Jesli posiada czarny piksel tzn ze jest litera i do listy dodajemy
         * wartosc tego wiersza pozostale wiersze wypelniamy na zolto
         */
        HashMap<Integer, ArrayList<Integer>> czarneWiersze = new HashMap<>();
        List<Integer> czarne = new ArrayList<>();
        List<List<Integer>> odcinki = new ArrayList<>();
        //wiersze ktore posiadaja czarne piksele
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                if (RGB.getR(outtmp.getRGB(i, j)) == 0) {
                    if (!czarne.contains(i)) {
                        czarne.add(i);
                    }
                }
            }
        }

        //wypelnianie pozostalych wierszy
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                if (!czarne.contains(i)) {
                    out.setRGB(i, j, zolty);
                }
            }
        }
        Collections.sort(czarne);
        //znacznik konca listy dodana wartosc wieksza o 2 od ostatniego wyrazu
        //zeby poprawnie przeszla petla i zauwazyla roznice w celu zazanczenia kolumny
        czarne.add(czarne.get(czarne.size() - 1) + 2);

        int first, previous, current, count, max, roznica;
        first = czarne.get(0);
        previous = czarne.get(0);
        max = heigth;
        count = 0;

        for (int k : czarne) {
            current = k;
            roznica = current - previous;
            if (roznica > 1) {
                List<Integer> tempList = new ArrayList<>();
                for (int i = first; i <= previous; i++) {
                    tempList.add(i);
                }
                odcinki.add(tempList);
                first = current;
            }
            previous = current;
        }

        //sprawdzanie kazdego odcinka
        boolean zawieraCzarne = false;
        for (List l : odcinki) {
            for (int j = 0; j < heigth; j++) {
                zawieraCzarne = false;

                for (int i = 0; i < l.size(); i++) {
                    int w = (int) l.get(i);
                    if (RGB.getB(out.getRGB(w, j)) == 0) {
                        zawieraCzarne = true;
                    }
                }

                if (zawieraCzarne == false) {
                    for (int i = 0; i < l.size(); i++) {
                        int w = (int) l.get(i);
                        out.setRGB(w, j, czerwony);
                    }
                }
            }
        }

        return out;
    }
    
    
    public static BufferedImage wycinanieLitery(BufferedImage in){
        
    BufferedImage out ;
    
    int up=0,down=0,left=0,right=0;
    
    //UP
    outloop:for (int i = 0; i < in.getWidth(); i++) {
                for (int j = 0; j < in.getHeight(); j++) {
                    if(RGB.getR(in.getRGB(i, j))==0 && RGB.getG(in.getRGB(i, j))==0 && RGB.getB(in.getRGB(i, j))==0){
                        up = i;
                        break outloop;
                    }
                }
        }
    
    //Down
    outloop:for (int i = 0; i < in.getWidth(); i++) {
                for (int j = in.getHeight()-1; j >=0 ; j--) {
                    if(RGB.getR(in.getRGB(i, j))==0 && RGB.getG(in.getRGB(i, j))==0 && RGB.getB(in.getRGB(i, j))==0){
                        down = i;
                        break outloop;
                    }
                }
        }
    
    //Left
    outloop:for (int j = 0; j < in.getHeight(); j++) {
                for (int i = 0; i < in.getWidth(); i++) {
                    if(RGB.getR(in.getRGB(i, j))==0 && RGB.getG(in.getRGB(i, j))==0 && RGB.getB(in.getRGB(i, j))==0){
                        left = j;
                        break outloop;
                    }
                }
        }
        
    //Right
    outloop:for (int j = 0; j < in.getHeight(); j++) {
                for (int i = in.getWidth()-1; i >0; i--) {
                    if(RGB.getR(in.getRGB(i, j))==0 && RGB.getG(in.getRGB(i, j))==0 && RGB.getB(in.getRGB(i, j))==0){
                        right = j;
                        break outloop;
                    }
                }
        }
    
        System.out.println("Left "+left+"  right "+right+"  up "+up+"  down  "+down);
    
    int newwidth,newheight;
    
    newwidth    = in.getWidth()-left-(in.getWidth()-right-1);
    newheight   = in.getHeight()-up-(in.getHeight()-down-1);
    
    
    out = new BufferedImage(newwidth,newheight,in.getType());
   
        for (int i = left,k=0; i <=right; i++,k++) {
            for (int j = up,l=0; j <=down; j++,l++) {
                out.setRGB(k, l, in.getRGB(i, j));
            }
        }
    
    return out;
    }

}
