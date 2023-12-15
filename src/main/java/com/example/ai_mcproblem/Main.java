package com.example.ai_mcproblem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        State initialState = new State(3, 3, true);
        State goalState = new State(0, 0, false);
        Solver solver = new Solver();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));

        stage.setTitle("Missionaries and Cannibals");
        BorderPane MCBorderPane = new BorderPane();

        Label title = new Label("Missionaries and Cannibals problem");
        title.setTextFill(Color.BLACK);
        title.setAlignment(Pos.CENTER);
        title.setFont(new Font("Times New Roman Bold", 50));

        Button BFSButton = new Button(" BFS ");
        BFSButton.setStyle("-fx-background-color:lightYellow;-fx-border-color:black");
        BFSButton.setTextFill(Color.BLACK);
        BFSButton.setFont(new Font("Times New Roman", 50));
        BFSButton.setPadding(new Insets(10));

        Button DFSButton = new Button(" DFS ");
        DFSButton.setStyle("-fx-background-color:lightYellow;-fx-border-color:black");
        DFSButton.setTextFill(Color.BLACK);
        DFSButton.setFont(new Font("Times New Roman", 50));
        DFSButton.setPadding(new Insets(10));

        HBox MCHBox = new HBox(80);
        MCHBox.setAlignment(Pos.CENTER);
        MCHBox.getChildren().addAll(BFSButton, DFSButton);
        MCBorderPane.setCenter(MCHBox);
        MCBorderPane.setPadding(new Insets(20));
        MCBorderPane.setTop(title);
        MCBorderPane.setPadding(new Insets(20));
        MCBorderPane.setAlignment(title, Pos.CENTER);
        MCBorderPane.setMargin(title, new Insets(40, 0, 0, 0));
        Scene scene = new Scene(MCBorderPane, 1170, 720);

        BorderPane BFSBorderPane = new BorderPane();
        BFSBorderPane.setPadding(new Insets(10));

        Button BFSBackButton = new Button("Back");
        BFSBackButton.setStyle("-fx-background-color:lightYellow;-fx-border-color:black");
        BFSBackButton.setTextFill(Color.BLACK);
        BFSBackButton.setFont(new Font("Times New Roman", 25));

        TextArea BFSTextArea = new TextArea();
        BFSTextArea.setStyle(" -fx-font-family: \"Times New Roman\";\r\n" + " -fx-font-size: 20;\r\n"
                + "   -fx-padding: 5,5,5,5;\r\n" + "   -fx-border-width: 1;\r\n"
                + " -fx-text-fill:black;");

        String str1 = solver.BFS(initialState,goalState);
        BFSTextArea.setText("Solution using BFS: "+str1);

        BorderPane.setAlignment(BFSTextArea, Pos.CENTER);
        BFSBorderPane.setCenter(BFSTextArea);
        BorderPane.setAlignment(BFSBackButton, Pos.BOTTOM_CENTER);
        BFSBorderPane.setBottom(BFSBackButton);
        Scene BFSScene = new Scene(BFSBorderPane, 1170, 720);

        BFSBackButton.setOnAction(e -> {
            stage.setScene(scene);
        });
        BFSButton.setOnAction(e -> stage.setScene(BFSScene));


        String str = solver.DFS(initialState,goalState);

        BorderPane DFSBorderPane = new BorderPane();
        DFSBorderPane.setPadding(new Insets(10));

        Button DFSBackButton = new Button("Back");
        DFSBackButton.setStyle("-fx-background-color:lightYellow;-fx-border-color:black");
        DFSBackButton.setTextFill(Color.BLACK);
        DFSBackButton.setFont(new Font("Times New Roman", 25));

        TextArea DFSTextArea = new TextArea();
        DFSTextArea.setStyle(" -fx-font-family: \"Times New Roman\";\r\n" + " -fx-font-size: 20;\r\n"
                + "   -fx-padding: 5,5,5,5;\r\n" + "   -fx-border-width: 1;\r\n"
                + " -fx-text-fill:black;");
        DFSTextArea.setText("Solution using DFS: "+str);

        BorderPane.setAlignment(DFSTextArea, Pos.CENTER);
        DFSBorderPane.setCenter(DFSTextArea);
        BorderPane.setAlignment(DFSBackButton, Pos.BOTTOM_CENTER);
        DFSBorderPane.setBottom(DFSBackButton);
        Scene DFSScene = new Scene(DFSBorderPane, 1170, 720);

        DFSBackButton.setOnAction(e -> {
            stage.setScene(scene);
        });

        DFSButton.setOnAction(e -> stage.setScene(DFSScene));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}