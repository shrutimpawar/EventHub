package com.eventhub.View;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

public class About {

     Stage primaryStage;
     public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void showAboutPage(Stage primaryStage) {
        VBox mainLayout = new VBox(30);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle("-fx-background-color: #f5f8fc;");

        Button backButton = new Button("<<");
        backButton.setFont(Font.font("Arial", 20));
        backButton.setStyle(
                "-fx-background-color: white;-fx-background-radius: 160; -fx-text-fill: #9f52a9ff;");
        backButton.setOnAction(e -> {
        Homepage homepage = new Homepage();
        homepage.setPrimaryStage(primaryStage);
        homepage.showHomePage(primaryStage);
});
        // Heading
        Label heading = new Label("About Us");
        heading.setFont(Font.font("Arial", 36));
        heading.setStyle("-fx-text-fill: white;");

        // --- Modified topsection using BorderPane ---
        BorderPane topsection = new BorderPane();
        topsection.setPadding(new Insets(20));
        topsection.setStyle(
                "-fx-background-color: #271539; -fx-background-radius: 10; "
                        + "-fx-border-color: #ddd; -fx-border-radius: 10; "
                        + "-fx-font-weight: bold; -fx-text-fill: white;");
        topsection.setMaxWidth(1600);

        topsection.setLeft(backButton);
        BorderPane.setAlignment(backButton, Pos.CENTER_LEFT);

        topsection.setCenter(heading);
        BorderPane.setAlignment(heading, Pos.CENTER);

        // -------- Images ---------
        ImageView eventImage = new ImageView(new Image("Assets/Images/eventHub_logo.jpg"));
        eventImage.setFitWidth(300);
        eventImage.setFitHeight(200);
        eventImage.setPreserveRatio(true);

        ImageView missionImage = new ImageView(new Image("Assets/Images/core2web_logo.jpg"));
        missionImage.setFitWidth(300);
        missionImage.setFitHeight(150);
        missionImage.setPreserveRatio(true);

        ImageView sirImage = new ImageView(new Image("Assets/Images/shahsi_sir.unknown"));
        sirImage.setFitWidth(300);
        sirImage.setPreserveRatio(true);

        // ---- Add hover animation to images ----
        addHoverEffect(eventImage);
        addHoverEffect(missionImage);
        addHoverEffect(sirImage);

        StackPane circleHeading2 = new StackPane(eventImage);
        circleHeading2.setAlignment(Pos.CENTER);

        Label desc2 = new Label(
                "🎉 Stop Scrolling, Start Doing! 🚀\n"
                        + "Welcome to EventHub – where the most exciting events 🎭🎤🎨 happening around you are just a tap away! 📲\n"
                        + "Whether you're in the mood for fun 😄, learning 📚, or connecting 🤝,\n"
                        + "your perfect local experience is waiting for you! 🌟✨");
        desc2.setFont(Font.font("Arial", 22));
        desc2.setStyle("-fx-text-fill: black;");
        desc2.setAlignment(Pos.CENTER_LEFT);

        HBox innerBox2 = new HBox(100, desc2, circleHeading2);
        innerBox2.setAlignment(Pos.CENTER_LEFT);

        HBox description2 = new HBox(60, innerBox2);
        description2.setAlignment(Pos.CENTER);
        description2.setPadding(new Insets(20));
        description2.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 10; -fx-border-color: #ddd; -fx-border-radius: 10;");
        description2.setMaxWidth(1500);
        description2.setPrefHeight(300);

        VBox descBox2 = new VBox(60, description2);
        descBox2.setAlignment(Pos.CENTER);

        StackPane circleHeading = new StackPane(missionImage);
        circleHeading.setAlignment(Pos.CENTER);

        Label desc = new Label(
                "We are proud learners at Core2Web, an exceptional edtech platform that truly lives up to its name \n teaching code to the core.\n"
                        + "Since joining, it has become one of the most valuable and transformative parts of our journey.\n");
        desc.setFont(Font.font("Arial", 22));
        desc.setStyle("-fx-text-fill: black;");
        desc.setAlignment(Pos.CENTER_LEFT);

        HBox innerBox = new HBox(100, circleHeading, desc);
        innerBox.setAlignment(Pos.CENTER_LEFT);

        HBox description = new HBox(60, innerBox);
        description.setAlignment(Pos.CENTER);
        description.setPadding(new Insets(20));
        description.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 10; -fx-border-color: #ddd; -fx-border-radius: 10;");
        description.setMaxWidth(1500);
        description.setPrefHeight(300);

        VBox descBox = new VBox(60, description);
        descBox.setAlignment(Pos.CENTER);

        StackPane circleHeading1 = new StackPane(sirImage);
        circleHeading1.setAlignment(Pos.CENTER);

        Label desc1 = new Label(
                "Shashikant Bagal (Shashi Sir) is the founder of Core2Web, a leading edtech platform launched in 2017.\n"
                        + "He has been instrumental in training thousands of students in core programming languages like C, C++,\n Java, and Python, along with Operating System fundamentals. His teaching approach focuses on \n building a strong foundation from scratch, helping learners gain deep conceptual clarity.\n\n"+"We extend our heartfelt gratitude to Core2Web for being a constant source of inspiration and learning.\n"+" A special thanks to Shashi Sir,for helping us grow, code by code, concept by concept.\n"+"We also sincerely thank Sachin Sir and Pramod Sir for their unwavering support, guidance\n"+" and dedication throughout our learning journey. Your efforts have made a lasting impact on our growth.");
        desc1.setFont(Font.font("Arial", 22));
        desc1.setStyle("-fx-text-fill: black;");
        desc1.setAlignment(Pos.CENTER_LEFT);

        HBox innerBox1 = new HBox(100, desc1, circleHeading1);
        innerBox1.setAlignment(Pos.CENTER_LEFT);

        HBox description1 = new HBox(60, innerBox1);
        description1.setAlignment(Pos.CENTER);
        description1.setPadding(new Insets(20));
        description1.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 10; -fx-border-color: #ddd; -fx-border-radius: 10;");
        description1.setMaxWidth(1500);
        description1.setPrefHeight(300);

        VBox descBox1 = new VBox(60, description1);
        descBox1.setAlignment(Pos.CENTER);

        Label team = new Label("Team Members:\nSaundarya Surana\nSayali Pawar\nShruti Pawar\nAnjali Jadhav");
        team.setFont(Font.font("Arial", 22));
        team.setStyle("-fx-text-fill: black;");
        team.setAlignment(Pos.CENTER_LEFT);

        HBox teammember = new HBox(60, team);
teammember.setAlignment(Pos.CENTER);
teammember.setPadding(new Insets(20));
teammember.setStyle(
        "-fx-background-color: linear-gradient(to bottom right, #ffdde1, #d5b0daff);" + /* soft gradient */
        "-fx-background-radius: 20;" +  /* rounded corners */
        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 15, 0, 0, 8);" + /* shadow */
        "-fx-border-color: #ffffff;" +
        "-fx-border-width: 2;" +
        "-fx-border-radius: 20;");

teammember.setMaxWidth(500);
teammember.setPrefHeight(220);

team.setStyle("-fx-font-size: 20px; -fx-text-fill: #333333; -fx-font-weight: bold; -fx-alignment: center;");


        VBox teamVBox = new VBox(60, teammember);
        teamVBox.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(topsection, descBox2, descBox, descBox1, teamVBox);
        mainLayout.setStyle("-fx-background-color: #f7e8e8ff");

        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Scene scene = new Scene(scrollPane, 1540, 795);
        primaryStage.setScene(scene);
        primaryStage.setTitle("About Us - Core2Web");
        primaryStage.show();
    }

    // -------- Hover effect method for images --------
    private void addHoverEffect(ImageView imageView) {
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), imageView);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), imageView);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });
    }


}
