/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorytmy;

import mainPackage.RGB;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author i9gaca
 */
public class krawedziowe {
    // zmiana jasnosci
    public static BufferedImage zmianaJasnosci(BufferedImage in, int param) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
        int width, height;
        width = out.getWidth();
        height = out.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Tu wypełnij właściwym kodem
                Color a = new Color(in.getRGB(i, j));

                int r = a.getRed() + param;
                int g = a.getGreen() + param;
                int b = a.getBlue() + param;

                Color colorOut = new Color(RGB.zakresy(r), RGB.zakresy(g), RGB.zakresy(b));

                out.setRGB(i, j, colorOut.getRGB());
            }
        }
        return out;
    }


    // "Krzyż Robertsa"
    public static BufferedImage krzyzRobertsa(BufferedImage in, int param) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
        int width, height;
        width = out.getWidth() - 1;
        height = out.getHeight() - 1;
        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                // Tu wypełnij właściwym kodem    
                int col = in.getRGB(i, j);

                int r = RGB.getR(col);
                int g = RGB.getG(col);
                int b = RGB.getB(col);

                r = Math.abs(r - (RGB.getR(in.getRGB(i + 1, j + 1)))) + (RGB.getR(in.getRGB(i + 1, j)) - (RGB.getR(in.getRGB(i, j + 1))));
                g = Math.abs(g - (RGB.getG(in.getRGB(i + 1, j + 1)))) + (RGB.getG(in.getRGB(i + 1, j)) - (RGB.getG(in.getRGB(i, j + 1))));
                b = Math.abs(b - (RGB.getB(in.getRGB(i + 1, j + 1)))) + (RGB.getB(in.getRGB(i + 1, j)) - (RGB.getB(in.getRGB(i, j + 1))));

                Color colorOut = new Color(RGB.zakresy(r), RGB.zakresy(g), RGB.zakresy(b));

                out.setRGB(i, j, colorOut.getRGB());
            }
        }
        return out;
    }

    // "Filtr Sobela"
    public static BufferedImage filtrSobela(BufferedImage in, int param) {
        BufferedImage out = new BufferedImage(in.getWidth() - 1, in.getHeight() - 1, in.getType());
        int width, height;
        width = out.getWidth();
        height = out.getHeight();


        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                // Tu wypełnij właściwym kodem   

                int a[][] = {{in.getRGB(i - 1, j - 1), in.getRGB(i, j - 1), in.getRGB(i + 1, j - 1)},
                        {in.getRGB(i - 1, j), in.getRGB(i, j), in.getRGB(i + 1, j)},
                        {in.getRGB(i - 1, j + 1), in.getRGB(i, j + 1), in.getRGB(i + 1, j + 1)}
                };

                int col = in.getRGB(i, j);

                int r = RGB.getR(col);
                int g = RGB.getG(col);
                int b = RGB.getB(col);

                int xr = (RGB.getR(a[0][2]) + 2 * RGB.getR(a[1][2]) + RGB.getR(a[2][2])) - (RGB.getR(a[0][0]) + 2 * RGB.getR(a[1][0]) + RGB.getR(a[2][0]));
                int xg = (RGB.getG(a[0][2]) + 2 * RGB.getG(a[1][2]) + RGB.getG(a[2][2])) - (RGB.getG(a[0][0]) + 2 * RGB.getG(a[1][0]) + RGB.getG(a[2][0]));
                int xb = (RGB.getB(a[0][2]) + 2 * RGB.getB(a[1][2]) + RGB.getB(a[2][2])) - (RGB.getB(a[0][0]) + 2 * RGB.getB(a[1][0]) + RGB.getB(a[2][0]));

                int yr = (RGB.getR(a[2][0]) + 2 * RGB.getR(a[2][1]) + RGB.getR(a[2][2])) - (RGB.getR(a[0][0]) + 2 * RGB.getR(a[0][1]) + RGB.getR(a[0][2]));
                int yg = (RGB.getG(a[2][0]) + 2 * RGB.getG(a[2][1]) + RGB.getG(a[2][2])) - (RGB.getG(a[0][0]) + 2 * RGB.getG(a[0][1]) + RGB.getG(a[0][2]));
                int yb = (RGB.getB(a[2][0]) + 2 * RGB.getB(a[2][1]) + RGB.getB(a[2][2])) - (RGB.getB(a[0][0]) + 2 * RGB.getB(a[0][1]) + RGB.getB(a[0][2]));


                double pxR = Math.sqrt(xr * xr + yr * yr);
                double pxG = Math.sqrt(xg * xg + yg * yg);
                double pxB = Math.sqrt(xb * xb + yb * yb);


                Color colorOut = new Color(RGB.zakresy((int) (pxR)), RGB.zakresy((int) (pxG)), RGB.zakresy((int) (pxB)));

                out.setRGB(i, j, colorOut.getRGB());
            }
        }
        return out;
    }

    public static BufferedImage obrot(BufferedImage in, int param) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
        int width, height;
        width = out.getWidth();
        height = out.getHeight();
        int r, g, b;

        double x, y;
        double minX, maxX, minY, maxY;
        minX = minY = 0;
        maxX = width - 1;
        maxY = height - 1;

        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                // Tu wypełnij właściwym kodem   
                x = Math.cos(Math.toRadians(param)) * i - Math.sin(Math.toRadians(param)) * j;
                y = Math.sin(Math.toRadians(param)) * i + Math.cos(Math.toRadians(param)) * j;

                if (x < minX) minX = x;
                if (x > maxX) maxX = x;
                if (y < minY) minY = y;
                if (y > maxY) maxY = y;
            }
        }

        System.out.println("X od " + minX + " do " + maxX);
        System.out.println("Y od " + minY + " do " + maxY);

        int wymiarX = (int) maxX + Math.abs((int) minX) + 10; //zaokraglenia
        int wymiarY = (int) maxY + Math.abs((int) minY) + 10; //zaokraglenia

        System.out.println("Wymiar obrazka :" + wymiarX + "x" + wymiarY);

        out = new BufferedImage(wymiarX, wymiarY, in.getType());
        int roznicaX = wymiarX - in.getWidth() + 5;
        int roznicaY = wymiarY - in.getHeight() + 5;

        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                // Tu wypełnij właściwym kodem   
                x = Math.cos(Math.toRadians(param)) * i - Math.sin(Math.toRadians(param)) * j;
                y = Math.sin(Math.toRadians(param)) * i + Math.cos(Math.toRadians(param)) * j;

                out.setRGB(Math.abs((int) (x)), Math.abs((int) (y)), in.getRGB(i, j));
            }
        }
        return out;
    }
}
