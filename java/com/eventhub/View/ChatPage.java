package com.eventhub.View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChatPage extends Application {

    private VBox chatBox;
    private TextField inputField;

    @Override
    public void start(Stage stage) {
        showChatPage(stage, "General Chat Room");
    }

    public void showChatPage(Stage stage, String eventName) {
        // ---- Title ----
        Label title = new Label("Chat Room - " + eventName);
        title.setFont(new Font(24));
        title.setStyle("-fx-text-fill: #501f5a; -fx-font-weight: bold;");

        // ---- Chat Display Area ----
        chatBox = new VBox(5);
        chatBox.setPadding(new Insets(10));
        ScrollPane chatScrollPane = new ScrollPane(chatBox);
        chatScrollPane.setFitToWidth(true);

        // ---- Input Field & Send Button ----
        inputField = new TextField();
        inputField.setPromptText("Type your message...");
        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-background-color: #501f5a; -fx-text-fill: white;");

        sendButton.setOnAction(e -> sendMessage("You"));

        HBox inputArea = new HBox(10, inputField, sendButton);
        inputArea.setPadding(new Insets(10));
        inputArea.setAlignment(Pos.CENTER);

        // ---- Back Button ----
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.close());

        VBox root = new VBox(15, title, chatScrollPane, inputArea, backButton);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f7e8e8ff;");

        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Chat Page");
        stage.show();
    }

    private void sendMessage(String sender) {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            Label msgLabel = new Label(sender + ": " + message);
            msgLabel.setStyle("-fx-background-color: #e1bee7; -fx-padding: 5; -fx-background-radius: 5;");
            chatBox.getChildren().add(msgLabel);
            inputField.clear();
        }
    }

}
