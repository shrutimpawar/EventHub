package com.eventhub.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FeedbackPage {

    private int currentRating = 0;
    private Button[] starButtons = new Button[5];

    public void showFeedbackPage() {
        Stage primaryStage = new Stage();

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #f7e8e8ff;");

        // Header with dark purple background
        Label titleLabel = new Label("Feedback");
        titleLabel.setFont(Font.font("Arial", 24));
        titleLabel.setTextFill(Color.WHITE);

        HBox header = new HBox(titleLabel);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: #271539; -fx-background-radius: 20;");

        // Star rating label
        Label ratingLabel = new Label("Rate our App:");
        ratingLabel.setFont(Font.font("Arial", 16));
        ratingLabel.setTextFill(Color.web("#800080")); // purple

        // Star buttons
        HBox starsBox = new HBox(10);
        starsBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < 5; i++) {
            Button star = new Button("☆");
            star.setFont(Font.font("Arial", 24));
            star.setStyle("-fx-background-color: transparent; -fx-text-fill: gold;");
            int rating = i + 1;

            star.setOnAction(e -> {
                currentRating = rating;
                updateStars();
            });

            starButtons[i] = star;
            starsBox.getChildren().add(star);
        }

        // Comment section
        Label commentLabel = new Label("Tell us more");
        commentLabel.setFont(Font.font("Arial", 16));
        commentLabel.setTextFill(Color.web("#800080")); // purple

        TextArea commentArea = new TextArea();
        commentArea.setPromptText("Type here...");
        commentArea.setPrefRowCount(5);
        commentArea.setMaxWidth(600);

        // Email field
        Label emailLabel = new Label("Email ID");
        emailLabel.setFont(Font.font("Arial", 16));
        emailLabel.setTextFill(Color.web("#800080")); // purple

        TextField emailField = new TextField();
        emailField.setPromptText("you@example.com");
        emailField.setMaxWidth(400);

        // Send button with deep purple
        Button sendButton = new Button("Send");
        //sendButton.setStyle("-fx-background-color: #6a0dad; -fx-text-fill: white; -fx-background-radius: 20;");
        sendButton.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"+ "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +"-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        sendButton.setPrefWidth(100);

        VBox.setMargin(sendButton, new Insets(10, 0, 0, 0));

        sendButton.setOnAction(e -> {
            String feedback = commentArea.getText();
            String email = emailField.getText();
            System.out.println("Rating: " + currentRating);
            System.out.println("Feedback: " + feedback);
            System.out.println("Email: " + email);

            primaryStage.close(); // Close after submission
        });

        // Add all nodes to root layout
        root.getChildren().addAll(
                header,
                ratingLabel,
                starsBox,
                commentLabel,
                commentArea,
                emailLabel,
                emailField,
                sendButton
        );

        Scene scene = new Scene(root, 1540, 795);
        primaryStage.setTitle("Feedback");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to update stars on click
    private void updateStars() {
        for (int i = 0; i < 5; i++) {
            if (i < currentRating) {
                starButtons[i].setText("★");
            } else {
                starButtons[i].setText("☆");
            }
   }
   }
}
