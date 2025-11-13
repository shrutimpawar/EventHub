package com.eventhub.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfilePage {

    private Label nameLabel, usernameLabel, emailLabel, roleLabel;
    private ImageView photoPreview;

    private String name = "Default Organizer";
    private String username = "@default_org";
    private String email = "default@example.com";
    private String role = "N/A"; // you can remove if not required

    private String organizerDocId;  // <-- Firestore document ID for organizer

    public void setOrganizerDocId(String docId) {
        this.organizerDocId = docId;
    }

    public void show(Stage primaryStage) {
        System.out.println("[DEBUG] Showing Organizer ProfilePage for docId: " + organizerDocId);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f7e8e8, #f0c1e1);");

        Label heading = new Label("Organizer Profile");
        heading.setFont(Font.font("Segoe UI Semibold", 34));
        heading.setTextFill(Color.web("#2C3E50"));
        BorderPane.setAlignment(heading, Pos.CENTER);
        root.setTop(heading);

        // IMAGE BOX
        photoPreview = new ImageView();
        photoPreview.setFitHeight(140);
        photoPreview.setFitWidth(180);
        photoPreview.setPreserveRatio(true);

        VBox imageBox = new VBox(10, photoPreview, createUploadButton(primaryStage));
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(20));
        imageBox.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #ddd;" +
                        "-fx-border-width: 1;" +
                        "-fx-background-radius: 0;" +
                        "-fx-effect: null;"
        );
        imageBox.setMaxWidth(220);

        // INFO BOX
        nameLabel = createInfoLabel(name, true);
        usernameLabel = createInfoLabel(username, false);
        emailLabel = createInfoLabel("Email: " + email, false);
        roleLabel = createInfoLabel("Role: " + role, false);

        VBox infoBox = new VBox(10, nameLabel, usernameLabel, new Separator(), emailLabel, roleLabel);
        infoBox.setPadding(new Insets(30));
        infoBox.setMaxWidth(500);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 20;" +
                        "-fx-effect: dropshadow(gaussian, #c299ff, 35, 0.5, 0, 4);"
        );

        // BUTTONS
        Button editBtn = new Button("Edit Profile");
        Button backBtn = new Button("Back");
        styleButton(editBtn);
        styleButton(backBtn);

        editBtn.setOnAction(e -> showEditDialog(primaryStage));
        backBtn.setOnAction(e -> {
            OrganizerDashboard dashboard = new OrganizerDashboard();
            dashboard.showOrganizerDashboard(primaryStage);
        });

        HBox buttonBox = new HBox(30, editBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox contentBox = new VBox(40, imageBox, infoBox, buttonBox);
        contentBox.setAlignment(Pos.CENTER);

        root.setCenter(contentBox);

        Scene scene = new Scene(root, 1540, 795);
        primaryStage.setTitle("Organizer Profile");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Fetch Organizer data from Firestore
        if (organizerDocId != null && !organizerDocId.isEmpty()) {
            fetchOrganizerDataFromFirestore(organizerDocId);
        } else {
            System.out.println("[DEBUG] No organizerDocId provided, using default values.");
        }
    }

    private void fetchOrganizerDataFromFirestore(String docId) {
        new Thread(() -> {
            try {
                System.out.println("[DEBUG] Fetching organizer data for docId: " + docId);
                String endpoint = String.format(
                        "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/organizer/%s?key=%s",
                        "event-hub-7033e",
                        URLDecoder.decode(docId, StandardCharsets.UTF_8),
                        "AIzaSyBRUq__-ukWR0ZgoZBfombRkHpd9G_MMWA"
                );

                System.out.println("[DEBUG] API URL: " + endpoint);

                URL url = new URL(endpoint);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                conn.disconnect();

                String json = response.toString();
                System.out.println("[DEBUG] Organizer API response: " + json);

                String fetchedName = extractValue(json, "name");
                String fetchedUsername = extractValue(json, "username");
                String fetchedEmail = extractValue(json, "email");
                String fetchedRole = extractValue(json, "role");

                Platform.runLater(() -> {
                    name = fetchedName;
                    username = "@" + fetchedUsername;
                    email = fetchedEmail;
                    role = fetchedRole; // Just showing role as phone placeholder if you want
                    updateProfileUI();
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // private String extractValue(String json, String field) {
    //     String key = "\"" + field + "\":";
    //     int startIndex = json.indexOf(key);
    //     if (startIndex == -1) return "";
    //     String sub = json.substring(startIndex);
    //     int valueStart = sub.indexOf("\"stringValue\":");
    //     if (valueStart == -1) return "";
    //     int firstQuote = sub.indexOf("\"", valueStart + 14);
    //     int secondQuote = sub.indexOf("\"", firstQuote + 1);
    //     return sub.substring(firstQuote + 1, secondQuote);
    // }
    private String extractValue(String json, String field) {
    try {
        org.json.JSONObject jsonObj = new org.json.JSONObject(json);
        org.json.JSONObject fields = jsonObj.getJSONObject("fields");
        if (fields.has(field)) {
            return fields.getJSONObject(field).getString("stringValue");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "";
   }


    private Button createUploadButton(Stage primaryStage) {
        Button uploadBtn = new Button("Upload Image");
        uploadBtn.setStyle(
                "-fx-background-radius: 30;" +
                        "-fx-padding: 8 20;" +
                        "-fx-background-color: linear-gradient(to right, #0f4646, #f042ff);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;"
        );

        uploadBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                photoPreview.setImage(image);
            }
        });

        return uploadBtn;
    }

    private void styleButton(Button button) {
        button.setStyle(
                "-fx-background-radius: 30;" +
                        "-fx-padding: 8 20;" +
                        "-fx-background-color: linear-gradient(to right, #0f4646, #f042ff);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;"
        );
        button.setPrefWidth(140);
    }

    private Label createInfoLabel(String text, boolean isHeading) {
        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI", isHeading ? 24 : 16));
        label.setTextFill(isHeading ? Color.web("#111111") : Color.web("#444444"));
        return label;
    }

    private void showEditDialog(Stage owner) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Edit Profile");

        TextField nameField = new TextField(name);
        TextField usernameField = new TextField(username.replace("@", ""));
        TextField emailField = new TextField(email);
        TextField roleField = new TextField(role);

        Button saveBtn = new Button("Save");
        saveBtn.setStyle(
                "-fx-background-color: linear-gradient(to right, #949e9e, #ed6cf9);" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #ddd;" +
                        "-fx-border-radius: 10;"
        );

        saveBtn.setOnAction(e -> {
            name = nameField.getText();
            username = "@" + usernameField.getText();
            email = emailField.getText();
            role = roleField.getText();
            updateProfileUI();
            dialog.close();
        });

        VBox dialogVBox = new VBox(12,
                new Label("Name:"), nameField,
                new Label("Username:"), usernameField,
                new Label("Email:"), emailField,
                new Label("Role:"), roleField,
                saveBtn
        );
        dialogVBox.setPadding(new Insets(25));
        dialogVBox.setStyle("-fx-background-color: #f0f8ff; -fx-font-weight: bold;");
        dialogVBox.setAlignment(Pos.CENTER_LEFT);

        Scene dialogScene = new Scene(dialogVBox, 450, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void updateProfileUI() {
        nameLabel.setText(name);
        usernameLabel.setText(username);
        emailLabel.setText("Email: " + email);
        roleLabel.setText("Role: " + role);
    }
}
