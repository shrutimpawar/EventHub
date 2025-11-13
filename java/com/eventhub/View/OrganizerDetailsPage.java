package com.eventhub.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class OrganizerDetailsPage {

    public Scene getScene(Stage pStage) {

        Label title = new Label("Tell us about your role as an organizer");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.BLACK);

        Label q1 = new Label("What type of events do you host?");
        q1.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        FlowPane categoryPane = new FlowPane();
        categoryPane.setHgap(10);
        categoryPane.setVgap(10);
        categoryPane.setPrefWrapLength(400); 
        categoryPane.setPadding(new Insets(10, 0, 10, 0));

        String[] categories = {
            "Technology", "Business", "Cultural", "Fashion", 
            "Sports", "Health", "Music", "Art", "Food", "Education","Other"
        };

        for (String category : categories) {
            ToggleButton btn = new ToggleButton(category);
            btn.setStyle("-fx-font-size: 12px; -fx-padding: 5 15; -fx-background-radius: 8;");
            btn.setMinWidth(100);
            categoryPane.getChildren().add(btn);
        }

        Label q2 = new Label("How many events will you host this year?");
        ComboBox<String> eventCount = new ComboBox<>();
        eventCount.getItems().addAll(
                "Just one event",
                "2-5 events",
                "5-10 events",
                "10-25 events",
                "More than 25",
                "Not sure yet"
        );
        eventCount.setPromptText("Select an option");

        Label q3 = new Label("How experienced are you?");
        RadioButton beginner = new RadioButton("Beginner");
        RadioButton intermediate = new RadioButton("Intermediate");
        RadioButton expert = new RadioButton("Expert");
        ToggleGroup expGroup = new ToggleGroup();
        beginner.setToggleGroup(expGroup);
        intermediate.setToggleGroup(expGroup);
        expert.setToggleGroup(expGroup);
        HBox experienceBox = new HBox(10, beginner, intermediate, expert);

        Button bt = new Button("Continue");
        bt.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        bt.setFont(Font.font("Arial", 20));
        bt.setPrefWidth(200);
        bt.setOnAction(e -> {
            EventDetailsPage nextPage = new EventDetailsPage();
            Scene nextScene = nextPage.getScene(pStage);
            pStage.setScene(nextScene);
        });

        Button BackBtn = new Button("Back");
        BackBtn.setPrefWidth(200);
        BackBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        BackBtn.setFont(Font.font("Arial", 20));
        //tech.setPrefWidth(200);
        BackBtn.setOnAction(e -> {
            OrganizerDashboard orPage = new OrganizerDashboard();
            orPage.showOrganizerDashboard(pStage);
        });

        VBox formCard = new VBox(20, title, q1, categoryPane, q2, eventCount, q3, experienceBox, bt, BackBtn);
        formCard.setPadding(new Insets(30));
        formCard.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 5);");
        formCard.setMaxWidth(600);

        // Image image = new Image(getClass().getResource("Assets\\Images\\bg.jpg").toExternalForm());
        // ImageView img1 = new ImageView(image);
        // img1.setFitWidth(400);
        // img1.setPreserveRatio(true);

        // VBox rightBox = new VBox(img1);
        // rightBox.setAlignment(Pos.CENTER);

        HBox pageLayout = new HBox(50, formCard);
        pageLayout.setPadding(new Insets(50));
        pageLayout.setAlignment(Pos.CENTER);
        pageLayout.setStyle("-fx-background-color: #f7e8e8ff;");

        return new Scene(pageLayout, 1540, 795); // ❌ can't return from void
   }
}