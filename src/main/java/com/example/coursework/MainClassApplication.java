package com.example.coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class MainClassApplication extends Application {
    static double widthScene;
    static double heightScene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainClassApplication.class.getResource("main-view.fxml"));
        Rectangle2D rectangle2D= Screen.getPrimary().getBounds();
        widthScene=rectangle2D.getWidth()/1.5;
        heightScene=rectangle2D.getHeight()/1.75;
        Scene scene = new Scene(fxmlLoader.load(), widthScene, heightScene );
        scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
        stage.setTitle("Фоторедактор");
        InputStream iconStream = getClass().getResourceAsStream("useFoto/iconca.png");
        Image icon = new Image(iconStream);
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}