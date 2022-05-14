package com.example.filtrs;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class MFiltrs {
    public HashMap<String,Double[]> fil;
    public MFiltrs(){
        fil=new HashMap<>();
        fil.put("Запад",new Double[]{new Double(1),new Double(1),new Double(1.1)});
        fil.put("Пальма",new Double[]{new Double(1.1),new Double(1),new Double(1)});
        fil.put("Луг",new Double[]{new Double(1),new Double(1.1),new Double(1)});
        fil.put("Румянец",new Double[]{new Double(1.25),new Double(1),new Double(1.1)});
        fil.put("Кинолента",new Double[]{new Double(0.95),new Double(0.95),new Double(1.25)});
        fil.put("Черно-Белый",new Double[]{new Double(0),new Double(0),new Double(0)});
        fil.put("Негатив",new Double[]{new Double(0),new Double(0),new Double(0)});
    }
    public WritableImage setFiltrs(String s, Image image){
        if(s!="Негатив"&&s!="Черно-Белый")
            return filtrsMetod(image,fil.get(s));
        if(s=="Черно-Белый"){
            Image tempImige = image;
            int width = (int) (tempImige.getWidth());
            int height = (int) (tempImige.getHeight());
            WritableImage wImage = new WritableImage(width, height);
            PixelWriter writer = wImage.getPixelWriter();
            PixelReader reader = tempImige.getPixelReader();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color color = reader.getColor(x, y);
                    double red = color.getRed();
                    double green = color.getGreen();
                    double blue = color.getBlue();
                    double grey = red * 0.299 + green * 0.587 + blue * 0.114;
                    Color nColor = new Color(grey, grey, grey, 1.0);
                    writer.setColor(x, y, nColor);
                }
            }
            return wImage;
        }
        if(s=="Негатив"){
            Image tempImige = image;
            int width = (int) (tempImige.getWidth());
            int height = (int) (tempImige.getHeight());
            WritableImage wImage = new WritableImage(width, height);
            PixelWriter writer = wImage.getPixelWriter();
            PixelReader reader = tempImige.getPixelReader();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color color = reader.getColor(x, y);
                    double red = 1-color.getRed();
                    double green = 1-color.getGreen();
                    double blue = 1-color.getBlue();
                    Color nColor = new Color(red, green, blue, 1.0);
                    writer.setColor(x, y, nColor);
                }
            }
            return wImage;
        }

        return null;
    }
    public WritableImage filtrsMetod(Image image, Double[] d) {
        Image tempImige = image;
        int width = (int) (tempImige.getWidth());
        int height = (int) (tempImige.getHeight());
        WritableImage wImage = new WritableImage(width, height);
        PixelWriter writer = wImage.getPixelWriter();
        PixelReader reader = tempImige.getPixelReader();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                double red = color.getRed()*d[0];
                double green = color.getGreen()*d[1];
                double blue = color.getBlue()*d[2];
                if(red>1){
                    red=1;
                }
                if (red<0){
                    red=0;
                }
                if(green>1){
                    green=1;
                }
                if (green<0){
                    green=0;
                }
                if(blue>1){
                    blue=1;
                }
                if (blue<0){
                    blue=0;
                }
                Color nColor = new Color(red, green, blue, 1.0);
                writer.setColor(x, y, nColor);
            }
        }
        return wImage;
    }
}
