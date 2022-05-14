package com.example.coursework;

import com.example.filtrs.MFiltrs;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class MainController implements Initializable {
    private FileChooser fileChooser=new FileChooser();
    private Stage stage=new Stage();
    private BufferImages bufferImage=new BufferImages();
    private Color colorFromCircl;
    private int count=0;
    private Label TextFoto;
    private Rectangle rect;
    private MFiltrs mFiltrs=new MFiltrs();





    @FXML
    private ImageView workImage;
    @FXML
    private VBox VBoxMain;
    @FXML
    private HBox hBoxViewImage;
    @FXML
    private ToolBar MainFunToolBar;
    @FXML
    private ToolBar FotoToolBar;
    @FXML
    private AnchorPane AncPane;
    @FXML
    private Button FilterBut;
    @FXML
    private Button CorBut;
    @FXML
    private MenuButton ScaleBut;
    @FXML
    private MenuButton DrowBut;
    @FXML
    private Button SafeBut;
    @FXML
    private Slider SliderRed;
    @FXML
    private Slider SliderBlue;
    @FXML
    private Slider SliderGreen;
    @FXML
    private Slider SliderLight;
    @FXML
    private Slider SliderSaturation;
    @FXML
    private Slider SliderWarm;
    @FXML
    private Button ReturneFoto;
    @FXML
    private ImageView ColorCircle;
    @FXML
    private ImageView Pluss;
    @FXML
    private ImageView Minuss;
    @FXML
    private TextField UserText;
    @FXML
    private Button ButTex;
    @FXML
    private ListView<String> ListFilters;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        VBoxMain.setPrefHeight(MainClassApplication.heightScene);
        VBoxMain.setPrefWidth(MainClassApplication.widthScene);


        hBoxViewImage.setPrefWidth(MainClassApplication.widthScene);
        hBoxViewImage.setPrefHeight(MainClassApplication.heightScene-MainFunToolBar.getPrefHeight()-10);

        AncPane.setPrefHeight(hBoxViewImage.getPrefHeight());
        AncPane.setPrefWidth(hBoxViewImage.getPrefWidth());

        workImage.setFitHeight(hBoxViewImage.getPrefHeight());
        workImage.setFitWidth(MainClassApplication.widthScene);

        UserText.setPrefHeight(40);
        UserText.setPrefWidth(120);
        UserText.setLayoutX(5);
        UserText.setLayoutY(5);
        InputStream Stream = getClass().getResourceAsStream("useFoto/plus.png");
        Image image = new Image(Stream);
        Pluss.setFitHeight(20);
        Pluss.setFitWidth(20);
        Pluss.setLayoutX(125);
        Pluss.setLayoutY(5);
        Pluss.setImage(image);
        Stream=getClass().getResourceAsStream("useFoto/minus.png");
        Image image1=new Image(Stream);
        Minuss.setFitHeight(22);
        Minuss.setFitWidth(20);
        Minuss.setLayoutX(125);
        Minuss.setLayoutY(25);
        Minuss.setImage(image1);
        ButTex.setPrefWidth(145);
        ButTex.setPrefHeight(40);
        ButTex.setLayoutX(5);
        ButTex.setLayoutY(50);


        ListFilters.getItems().add("Запад");
        ListFilters.getItems().add("Пальма");
        ListFilters.getItems().add("Луг");
        ListFilters.getItems().add("Румянец");
        ListFilters.getItems().add("Кинолента");
        ListFilters.getItems().add("Черно-Белый");
        ListFilters.getItems().add("Негатив");


        CleanScrean();
        DontClick();
    }

    public void setUserTextInst(){
        UserText.setVisible(true);
        Pluss.setVisible(true);
        Minuss.setVisible(true);
        ButTex.setVisible(true);
    }

    public void CleanScrean(){
        ListFilters.setVisible(false);
        ToolBarFotoVisibleOff();
        offTextOnFoto();
        centerImage();
        workImage.setX(workImage.getX()-150);
    }

    public void DontClick(){
        FilterBut.setVisible(false);
        CorBut.setVisible(false);
        ScaleBut.setVisible(false);
        DrowBut.setVisible(false);
        SafeBut.setVisible(false);
        ReturneFoto.setVisible(false);
    }

    public void OkClick(){
        FilterBut.setVisible(true);
        CorBut.setVisible(true);
        ScaleBut.setVisible(true);
        DrowBut.setVisible(true);
        SafeBut.setVisible(true);
    }

    public void ToolBarFotoVisibleOff(){
        FotoToolBar.setVisible(false);
        FotoToolBar.setPrefHeight(0);
        FotoToolBar.setPrefWidth(0);
        workImage.setFitWidth(MainClassApplication.widthScene);
    }
    public void ToolBarFotoVisibleOn(){
        FotoToolBar.setPrefHeight(hBoxViewImage.getPrefHeight());
        FotoToolBar.setPrefWidth(150.0);
        FotoToolBar.setVisible(true);
        workImage.setFitWidth(MainClassApplication.widthScene-150);

        centerImage();
    }


    public void LoudFoto(ActionEvent actionEvent) {
        try {
            fileChooser.setTitle("Выбор изображения");
            File FileImage = fileChooser.showOpenDialog(stage);
            if (FileImage != null) {
                Image image = new Image(FileImage.getAbsolutePath());
                ToolBarFotoVisibleOff();
                SetFotoInWorKSpace(image,image);

            }
            centerImage();
            workImage.setX(workImage.getX()-150);
            OkClick();
        }
        catch (Exception e){
            NullPointerFoto();
        }
    }



    public void SafeFoto(ActionEvent actionEvent) {


    }

    public void SetFotoInWorKSpace(Image inBuf, Image inScreen){
        if(inScreen!=null&&inBuf!=null){
            workImage.setImage(inScreen);
            bufferImage.PutImeginn(inBuf);
            ReturneFoto.setVisible(true);
        }
        else {
            NullPointerFoto();
        }
    }

    public void NullPointerFoto(){
        InputStream loudStream = getClass().getResourceAsStream("useFoto/errorFoto.jpg");
        Image image = new Image(loudStream);
        workImage.setImage(image);
    }

    public void centerImage() {
        Image img = workImage.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = workImage.getFitWidth() / img.getWidth();
            double ratioY = workImage.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            workImage.setX((workImage.getFitWidth() - w) / 2);
            workImage.setY((workImage.getFitHeight() - h) / 2);


        }
    }


    public void parametersOn(ActionEvent actionEvent) {
        CleanScrean();
        ToolBarFotoVisibleOn();

    }

    public void CustomBlue(MouseEvent mouseEvent) {
        Image tempImige=workImage.getImage();
        int width=(int)(tempImige.getWidth());
        int height=(int) (tempImige.getHeight());
        WritableImage wImage=new WritableImage(width,height);
        PixelWriter writer=wImage.getPixelWriter();
        PixelReader reader=tempImige.getPixelReader();
        double temp=SliderBlue.getValue();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                double red=color.getRed();
                double green=color.getGreen();
                double blue=color.getBlue();
                if(blue+temp<1) blue+=temp; else blue=1.0;
                if(blue+temp<0) blue=0;
                if(green-temp/5<1) green-=temp/5; else green=1.0;
                if(green-temp/5<0) green=0;
                if(red-temp/5<1) red-=temp/5; else red=1.0;
                if(red-temp/5<0) red=0;
                Color nColor=new Color(red, green, blue,1.0);
                writer.setColor(x, y, nColor);
            }
        }
        SetFotoInWorKSpace(workImage.getImage(),wImage);
    }

    public void CustomRed(MouseEvent mouseEvent) {
        Image tempImige=workImage.getImage();
        int width=(int)(tempImige.getWidth());
        int height=(int) (tempImige.getHeight());
        WritableImage wImage=new WritableImage(width,height);
        PixelWriter writer=wImage.getPixelWriter();
        PixelReader reader=tempImige.getPixelReader();
        double temp=SliderRed.getValue();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                double red=color.getRed();
                double green=color.getGreen();
                double blue=color.getBlue();
                if(red+temp<1) red+=temp; else red=1.0;
                if(red+temp<0) red=0;
                if(green-temp/5<1) green-=temp/5; else green=1.0;
                if(green-temp/5<0) green=0;
                if(blue-temp/5<1) blue-=temp/5; else blue=1.0;
                if(blue-temp/5<0) blue=0;
                Color nColor=new Color(red, green, blue,1.0);
                writer.setColor(x, y, nColor);
            }
        }
        SetFotoInWorKSpace(workImage.getImage(),wImage);

    }

    public void CustomGreen(MouseEvent mouseEvent) {
        Image tempImige=workImage.getImage();
        int width=(int)(tempImige.getWidth());
        int height=(int) (tempImige.getHeight());
        WritableImage wImage=new WritableImage(width,height);
        PixelWriter writer=wImage.getPixelWriter();
        PixelReader reader=tempImige.getPixelReader();
        double temp=SliderGreen.getValue();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                double red=color.getRed();
                double green=color.getGreen();
                double blue=color.getBlue();
                if(green+temp<1) green+=temp; else green=1.0;
                if(green+temp<0) green=0;
                if(red-temp/5<1) red-=temp/5; else red=1.0;
                if(red-temp/5<0) red=0;
                if(blue-temp/5<1) blue-=temp/5; else blue=1.0;
                if(blue-temp/5<0) blue=0;
                Color nColor=new Color(red, green, blue,1.0);
                writer.setColor(x, y, nColor);
            }
        }
        SetFotoInWorKSpace(workImage.getImage(),wImage);
    }

    public void CustomLight(MouseEvent mouseEvent) {
        Image tempImige=workImage.getImage();
        int width=(int)(tempImige.getWidth());
        int height=(int) (tempImige.getHeight());
        WritableImage wImage=new WritableImage(width,height);
        PixelWriter writer=wImage.getPixelWriter();
        PixelReader reader=tempImige.getPixelReader();
        double temp=SliderLight.getValue();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                double red=color.getRed();
                double green=color.getGreen();
                double blue=color.getBlue();
                if(red+temp<1) red+=temp; else red=1.0;
                if(red+temp<0) red=0;
                if(green+temp<1) green+=temp; else green=1.0;
                if(green+temp<0) green=0;
                if(blue+temp<1) blue+=temp; else blue=1.0;
                if(blue+temp<0) blue=0;
                Color nColor=new Color(red, green, blue,1.0);
                writer.setColor(x, y, nColor);
            }
        }
        SetFotoInWorKSpace(workImage.getImage(),wImage);

    }

    public void CustomSaturation(MouseEvent mouseEvent) {
        Image tempImige=workImage.getImage();
        int width=(int)(tempImige.getWidth());
        int height=(int) (tempImige.getHeight());
        WritableImage wImage=new WritableImage(width,height);
        PixelWriter writer=wImage.getPixelWriter();
        PixelReader reader=tempImige.getPixelReader();
        double temp=SliderSaturation.getValue();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                double red=color.getRed();
                double green=color.getGreen();
                double blue=color.getBlue();

                if(red>green){
                    if(red>blue){
                        if(red+temp<1) red+=temp; else red=1.0;
                        if(red+temp<0) red=0;
                    }
                    else {
                        if(blue+temp<1) blue+=temp; else blue=1.0;
                        if(blue+temp<0) blue=0;
                    }
                }
                else {
                    if(green>blue){
                        if(green+temp<1) green+=temp; else green=1.0;
                        if(green+temp<0) green=0;
                    }
                    else {
                        if(blue+temp<1) blue+=temp; else blue=1.0;
                        if(blue+temp<0) blue=0;
                    }
                }
                Color nColor=new Color(red, green, blue,1.0);
                writer.setColor(x, y, nColor);
            }
        }
        SetFotoInWorKSpace(workImage.getImage(),wImage);

    }

    public void CustomWarm(MouseEvent mouseEvent) {
        Image tempImige=workImage.getImage();
        int width=(int)(tempImige.getWidth());
        int height=(int) (tempImige.getHeight());
        WritableImage wImage=new WritableImage(width,height);
        PixelWriter writer=wImage.getPixelWriter();
        PixelReader reader=tempImige.getPixelReader();
        double temp=SliderWarm.getValue();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                double red=color.getRed();
                double green=color.getGreen();
                double blue=color.getBlue();
                if(temp<0){
                    if(blue+temp<1) blue+=temp; else blue=1.0;
                    if(blue+temp<0) blue=0;
                }
                else {
                    if(red-temp<1) red-=temp; else red=1.0;
                    if(red-temp<0) red=0;
                }
                Color nColor=new Color(red, green, blue,1.0);
                writer.setColor(x, y, nColor);
            }
        }
        SetFotoInWorKSpace(workImage.getImage(),wImage);


    }

    public void ReturnPastFoto(ActionEvent actionEvent) {
        if(bufferImage.isNullImageStack()){
            workImage.setImage(bufferImage.ReturnImeginn());
        }
        if(!bufferImage.isNullImageStack()){
            ReturneFoto.setVisible(false);
        }

    }

    public void DrowAction(ActionEvent actionEvent) {
        CleanScrean();
        setColorCircl();


    }

    public void setTextOnFoto(ActionEvent actionEvent) {
        CleanScrean();
        setColorCircl();
        setUserTextInst();
        TextFoto=new Label();
        TextFoto.setLayoutX(workImage.getX());
        TextFoto.setLayoutY(workImage.getY());
        TextFoto.setFont(new Font(40));
        ColorCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image tempImige=ColorCircle.getImage();
                PixelReader reader=tempImige.getPixelReader();
                colorFromCircl=reader.getColor((int)mouseEvent.getX(),(int)(mouseEvent.getY()-ColorCircle.getY()));
                TextFoto.setTextFill(colorFromCircl);
            }
        });
        Pluss.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(count<10) {
                    TextFoto.setFont(new Font(TextFoto.getFont().getSize() * 1.1));
                    count++;
                }
            }
        });
        Minuss.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(count>-10) {
                    TextFoto.setFont(new Font(TextFoto.getFont().getSize() * 0.9));
                    count--;
                }
            }
        });
        AncPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(count%2==0) {
                    TextFoto.setLayoutX(mouseEvent.getX());
                    TextFoto.setLayoutY(mouseEvent.getY());
                    count++;
                }
                else {
                    count++;
                }
            }
        });
        AncPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(count%2==1){
                    TextFoto.setLayoutY(mouseEvent.getY());
                    TextFoto.setLayoutX(mouseEvent.getX());
                }
            }
        });
        AncPane.getChildren().add(TextFoto);
    }
    public void setColorCircl(){
        InputStream Stream = getClass().getResourceAsStream("useFoto/CirColr.jpg");
        Image image = new Image(Stream);
        ColorCircle.setImage(image);
        ColorCircle.setY(hBoxViewImage.getPrefHeight()/3);
        ColorCircle.setVisible(true);

    }
    public void offTextOnFoto(){
        UserText.setVisible(false);
        Pluss.setVisible(false);
        Minuss.setVisible(false);
        ButTex.setVisible(false);
        ColorCircle.setVisible(false);
    }
    public void sText(ActionEvent actionEvent) {
        TextFoto.setText(UserText.getText());
    }

    public void SkaleFoto(ActionEvent actionEvent) {
        Rectgl();
        AncPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(count%2==0) {
                   // rect.setVisible(true);
                    System.out.println();
                    rect.setX(mouseEvent.getX());
                    rect.setY(mouseEvent.getY());
                    count++;
                }
                else {
                    Image tempImige=workImage.getImage();
                    int width=(int)(rect.getWidth());
                    int height=(int) (rect.getHeight());
                    PixelReader reader=tempImige.getPixelReader();
                    double deltaX=tempImige.getWidth()/(AncPane.getWidth()+AncPane.getLayoutX());
                    double deltaY=tempImige.getHeight()/AncPane.getPrefHeight();
                    WritableImage wImage=new WritableImage(reader,(int)((rect.getX()*deltaX)+AncPane.getLayoutX()),(int)(rect.getY()*deltaY),(int)((width*deltaX)+ workImage.getX()),(int)(height*deltaY));
                    SetFotoInWorKSpace(workImage.getImage(),wImage);
                    // rect.setVisible(false);
                    count++;
/*
                    System.out.println(count);
                    System.out.println((int)rect.getX());
                    System.out.println(mouseEvent.getX());
                    System.out.println(workImage.getX());
                    Image tempImige=workImage.getImage();
                    int width=(int)(rect.getWidth());
                    int height=(int) (rect.getHeight());
                    WritableImage wImage=new WritableImage(width,height);
                    PixelWriter writer=wImage.getPixelWriter();
                    PixelReader reader=tempImige.getPixelReader();
                    System.out.println(wImage.getWidth());
                    System.out.println(wImage.getHeight());
                    for(int y = 0; y < height; y++) {
                        for(int x = 0; x < width; x++) {
                            Color color = reader.getColor((int)(rect.getX()-workImage.getX()+x),(int)rect.getY()+y);

                            writer.setColor(x, y, color);
                        }
                    }
                    System.out.println(rect.getX());
                    SetFotoInWorKSpace(workImage.getImage(),wImage);
                   // rect.setVisible(false);
                    count++;*/
                }
            }
        });
        AncPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(count%2==1){
                    rect.setHeight(mouseEvent.getY()-rect.getY());
                    rect.setWidth(mouseEvent.getX()-rect.getX());
                }
            }
        });
        AncPane.getChildren().add(rect);

        SetFotoInWorKSpace(workImage.getImage(),workImage.getImage());


    }
    public void Rectgl(){
        rect=new Rectangle(0,0,0,0);
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(1);
        rect.setStrokeLineCap(StrokeLineCap.ROUND);
        rect.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.6));
    }

    public void setFiltrs(ActionEvent actionEvent) {
        CleanScrean();
        ListFilters.setVisible(true);
        ListFilters.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                WritableImage writableImage=mFiltrs.setFiltrs(ListFilters.getSelectionModel().getSelectedItem(),workImage.getImage());
                SetFotoInWorKSpace(workImage.getImage(),writableImage);
            }
        });
    }
}
