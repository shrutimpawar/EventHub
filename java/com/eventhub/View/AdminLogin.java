package com.eventhub.View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AdminLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root container
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f3e8ff;"); // light purple

        // Title
        Label title = new Label("Admin Login");
        title.setFont(Font.font("Arial", 26));
        title.setTextFill(Color.web("#6a0dad")); // deep purple

        // Username field
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(300);

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);

        // Error message
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setFont(Font.font(14));

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #6a0dad; -fx-text-fill: white; -fx-background-radius: 20;");
        loginButton.setPrefWidth(100);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Simple hardcoded validation
            if (username.equals("admin") && password.equals("admin123")) {
                errorLabel.setText("");
                // Navigate to admin dashboard or show success (mockup)
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Successful! Redirecting to Admin Dashboard...");
                alert.setHeaderText(null);
                alert.showAndWait();
                // You can launch AdminDashboard here
            } else {
                errorLabel.setText("Invalid username or password.");
            }
        });

        // Add all components
        root.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                errorLabel,
                loginButton
        );

        Scene scene = new Scene(root, 1520, 795);
        primaryStage.setTitle("Admin Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
}

    

