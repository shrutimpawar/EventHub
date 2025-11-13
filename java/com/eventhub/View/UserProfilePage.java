
// package com.eventhub.View;
// import java.io.File;
// import java.net.URL;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.stage.FileChooser;
// import javafx.stage.Modality;
// import javafx.stage.Stage;

// public class UserProfilePage {

//     Stage UserProfilePageStage;
//     public void setUserProfilePageStage(Stage userProfilePageStage) {
//         UserProfilePageStage = userProfilePageStage;
//     }

//     public void setUserProfilePageScene(Scene userProfilePageScene) {
//         UserProfilePageScene = userProfilePageScene;
//     }

//     Scene UserProfilePageScene;

//     private Label nameLabel, usernameLabel, emailLabel, phoneLabel, addressLabel;
//     private ImageView profilePic;

//     private String name = "saundarya surana";
//     private String username= "@saundaryasurana";
//     private String email = "sau@gmail.com";
//     private String phone = "+91 988989898";
//     private String address = "123, Pune, Maharashtra";

//     private String profileImagePath;

//     public VBox createUserProfileScene() {
//         profileImagePath = loadDefaultImage();

//         BorderPane root = new BorderPane();
//         root.setPadding(new Insets(40));
//         root.setPrefSize(1200,900 );
//         root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f7e8e8, #f0c1e1);");

//         // ← Back Button
//         Button backButton = new Button("<<");
//         backButton.setFont(Font.font("Arial", 20));
//         backButton.setStyle(
//             "-fx-background-color: white; -fx-background-radius: 50; -fx-text-fill: #9f52a9;" +
//             "-fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0.5, 0, 1);"
//         );
//         backButton.setOnAction(e -> {
//             UserDashboard dashboard = new UserDashboard();
//             try {
//                 dashboard.setPrimaryStage(UserProfilePageStage);
//                 dashboard.showUserDashboardPage();
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });

//         Label heading = new Label("User Profile");
//         heading.setFont(Font.font("Segoe UI Semibold", 34));
//         heading.setTextFill(Color.web("#2C3E50"));

//         BorderPane topPane = new BorderPane();
//         topPane.setLeft(backButton);
//         topPane.setCenter(heading);

//         root.setTop(topPane);

//         // Profile Picture
//         profilePic = new ImageView(new Image(new File(profileImagePath).toURI().toString()));
//         profilePic.setFitHeight(150);
//         profilePic.setFitWidth(150);
//         profilePic.setPreserveRatio(true);
//         profilePic.setStyle("-fx-effect: dropshadow(gaussian, gray, 10, 0.5, 0, 0);");

//         Button changePhotoBtn = new Button("Change Photo");
//         changePhotoBtn.setStyle(
//             "-fx-background-color: #eeeeee; -fx-text-fill: #333; -fx-background-radius: 20;" +
//             "-fx-cursor: hand; -fx-font-size: 14px; -fx-padding: 5 15;"
//         );
//         changePhotoBtn.setOnAction(e -> changeProfileImage(UserProfilePageStage));

//         VBox imageBox = new VBox(10, profilePic, changePhotoBtn);
//         imageBox.setAlignment(Pos.CENTER);
//         imageBox.setPadding(new Insets(10));

//         // Profile Info
//         nameLabel = createInfoLabel(name, true);
//         usernameLabel = createInfoLabel(username, false);
//         emailLabel = createInfoLabel("Email: " + email, false);
//         phoneLabel = createInfoLabel("Phone: " + phone, false);
//         addressLabel = createInfoLabel("Address: " + address, false);

//         VBox infoBox = new VBox(10, nameLabel, usernameLabel, new Separator(),
//                 emailLabel, phoneLabel, addressLabel);
//         infoBox.setPadding(new Insets(30));
//         infoBox.setMaxWidth(500);
//         infoBox.setMaxHeight(800);
//         infoBox.setAlignment(Pos.CENTER);
//         infoBox.setStyle(
//             "-fx-background-color: white; -fx-background-radius: 20;" +
//             "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 10, 0.2, 0, 2);"
//         );

//         // Glow region behind infoBox
//             Region glow = new Region();
//             glow.setStyle("-fx-background-radius: 30; -fx-background-color: white; "
//                 + "-fx-effect: dropshadow(gaussian, #c299ff, 35, 0.5, 0, 4);");
//             glow.setMaxSize(600, 500);
//             glow.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

           
//             // StackPane to layer glow and infoBox
//             StackPane infoWithGlow = new StackPane(glow, infoBox);
//             infoWithGlow.setAlignment(Pos.CENTER);

//             // Keep image on top of glow + info box
//             VBox centerBox = new VBox(30, imageBox, infoWithGlow);
//             centerBox.setAlignment(Pos.TOP_CENTER);




        
//         // Buttons
//         Button editBtn = new Button("Edit Profile");
//       //  Button logoutBtn = new Button("Logout");
//       //  String buttonBaseStyle =
//             // "-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 25; -fx-cursor: hand; -fx-text-fill: white; -fx-padding: 8 20;";
//       //  editBtn.setStyle(buttonBaseStyle + "-fx-background-color: #007BFF;");
//         // logoutBtn.setStyle(buttonBaseStyle + "-fx-background-color: #dc3545;");

//         editBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
//                 + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
//                 "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
//         editBtn.setFont(Font.font("Arial", 20));

//         editBtn.setOnAction(e -> showEditDialog(UserProfilePageStage));
//         // logoutBtn.setOnAction(e -> showLogoutDialog(UserProfilePageStage));

//         HBox buttonBox = new HBox(30, editBtn);
//         buttonBox.setAlignment(Pos.CENTER);

//         VBox mainContent = new VBox(40, centerBox, buttonBox);
//         mainContent.setAlignment(Pos.TOP_CENTER);
//         root.setCenter(mainContent);

//         return new VBox(root);
//     }

//     private Label createInfoLabel(String text, boolean isHeading) {
//         Label label = new Label(text);
//         if (isHeading) {
//             label.setFont(Font.font("Segoe UI", 24));
//             label.setTextFill(Color.web("#2C3E50"));
//         } else {
//             label.setFont(Font.font("Segoe UI", 16));
//             label.setTextFill(Color.web("#555"));
//         }
//         return label;
//     }

//     private void showEditDialog(Stage owner) {
//         Stage dialog = new Stage();
//         dialog.initOwner(owner);
//         dialog.initModality(Modality.APPLICATION_MODAL);
//         dialog.setTitle("Edit Profile");

//         TextField nameField = new TextField(name);
//         TextField usernameField = new TextField(username.replace("@", ""));
//         TextField emailField = new TextField(email);
//         TextField phoneField = new TextField(phone);
//         TextField addressField = new TextField(address);

//         Button saveBtn = new Button("Save");
//         saveBtn.setStyle(
//             "-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 6 20;"
//         );

//         saveBtn.setOnAction(e -> {
//             name = nameField.getText();
//             username = "@" + usernameField.getText();
//             email = emailField.getText();
//             phone = phoneField.getText();
//             address = addressField.getText();
//             updateProfileUI();
//             dialog.close();
//         });

//         VBox dialogVBox = new VBox(12,
//                 new Label("Name:"), nameField,
//                 new Label("Username:"), usernameField,
//                 new Label("Email:"), emailField,
//                 new Label("Phone:"), phoneField,
//                 new Label("Address:"), addressField,
//                 saveBtn
//         );
//         dialogVBox.setPadding(new Insets(25));
//         dialogVBox.setAlignment(Pos.CENTER_LEFT);

//         Scene dialogScene = new Scene(dialogVBox, 400, 430);
//         dialog.setScene(dialogScene);
//         dialog.show();
//     }

//     private void updateProfileUI() {
//         nameLabel.setText(name);
//         usernameLabel.setText(username);
//         emailLabel.setText("Email: " + email);
//         phoneLabel.setText("Phone: " + phone);
//         addressLabel.setText("Address: " + address);
//     }

//     private void showLogoutDialog(Stage stage) {
//         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//         alert.setTitle("Logout Confirmation");
//         alert.setHeaderText("Are you sure you want to logout?");
//         alert.initOwner(stage);

//         ButtonType yes = new ButtonType("Yes");
//         ButtonType no = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
//         alert.getButtonTypes().setAll(yes, no);

//         alert.showAndWait().ifPresent(response -> {
//             if (response == yes) {
//                 stage.close();
//             }
//         });
//     }

//     private void changeProfileImage(Stage stage) {
//         FileChooser fileChooser = new FileChooser();
//         fileChooser.setTitle("Choose Profile Image");
//         fileChooser.getExtensionFilters().addAll(
//                 new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
//         );

//         File selectedFile = fileChooser.showOpenDialog(stage);
//         if (selectedFile != null) {
//             profileImagePath = selectedFile.getAbsolutePath();
//             profilePic.setImage(new Image(selectedFile.toURI().toString()));
//         }
//     }

//     private String loadDefaultImage() {
//         try {
//             URL url = getClass().getResource("/Assets/Images/profile.jpg");
//             if (url != null) {
//                 return new File(url.toURI()).getAbsolutePath();
//             } else {
//                 System.out.println("Default image not found.");
//                 return "";
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             return "";
//         }
//     }
// }

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

public class UserProfilePage {

    Stage UserProfilePageStage;
    Scene UserProfilePageScene;

    private Label nameLabel, usernameLabel, emailLabel, roleLabel;
    private ImageView profilePic;

    private String name = "";
    private String username = "";
    private String email = "";
    private String role = "";

    private String profileImagePath;

    private String userDocId; // Firestore document ID from login

    public void setUserProfilePageStage(Stage userProfilePageStage) {
        UserProfilePageStage = userProfilePageStage;
    }

    public void setUserProfilePageScene(Scene userProfilePageScene) {
        UserProfilePageScene = userProfilePageScene;
    }

    public void setUserDocId(String userDocId) {
        this.userDocId = userDocId;
    }

    public VBox createUserProfileScene() {

        // Fetch logged-in user document id
        System.out.println("[DEBUG] Creating UserProfilePage UI...");//2
        String docId = LoggedInHelper.getUserDocId();
        System.out.println("[DEBUG] LoggedInHelper.getUserDocId() -> " + docId);
        if (docId != null && !docId.isEmpty()) {
            fetchUserDataFromFirestore(docId);
            System.out.println("[DEBUG] LoggedInHelper.getUserDocId() -> " + docId);
        }

        profileImagePath = loadDefaultImage();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(40));
        root.setPrefSize(1200, 900);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f7e8e8, #f0c1e1);");

        // Back Button
        Button backButton = new Button("<<");
        backButton.setFont(Font.font("Arial", 20));
        backButton.setStyle(
                "-fx-background-color: white; -fx-background-radius: 50; -fx-text-fill: #9f52a9;" +
                        "-fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0.5, 0, 1);"
        );
        backButton.setOnAction(e -> {
            UserDashboard dashboard = new UserDashboard();
            try {
                dashboard.setPrimaryStage(UserProfilePageStage);
                dashboard.showUserDashboardPage();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Label heading = new Label("User Profile");
        heading.setFont(Font.font("Segoe UI Semibold", 34));
        heading.setTextFill(Color.web("#2C3E50"));

        BorderPane topPane = new BorderPane();
        topPane.setLeft(backButton);
        topPane.setCenter(heading);

        root.setTop(topPane);

        // Profile Picture
        profilePic = new ImageView(new Image(new File(profileImagePath).toURI().toString()));
        profilePic.setFitHeight(150);
        profilePic.setFitWidth(150);
        profilePic.setPreserveRatio(true);
        profilePic.setStyle("-fx-effect: dropshadow(gaussian, gray, 10, 0.5, 0, 0);");

        Button changePhotoBtn = new Button("Change Photo");
        changePhotoBtn.setStyle(
                "-fx-background-color: #eeeeee; -fx-text-fill: #333; -fx-background-radius: 20;" +
                        "-fx-cursor: hand; -fx-font-size: 14px; -fx-padding: 5 15;"
        );
        changePhotoBtn.setOnAction(e -> changeProfileImage(UserProfilePageStage));

        VBox imageBox = new VBox(10, profilePic, changePhotoBtn);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(10));

        // Profile Info Labels
        nameLabel = createInfoLabel(username, true);
        usernameLabel = createInfoLabel(username, false);
        emailLabel = createInfoLabel("Email: " + email, false);
        roleLabel = createInfoLabel("Role: " + role, false);

        VBox infoBox = new VBox(10, nameLabel, usernameLabel, new Separator(),
                emailLabel, roleLabel);
        infoBox.setPadding(new Insets(30));
        infoBox.setMaxWidth(500);
        infoBox.setMaxHeight(800);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setStyle(
                "-fx-background-color: white; -fx-background-radius: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 10, 0.2, 0, 2);"
        );

        Region glow = new Region();
        glow.setStyle("-fx-background-radius: 30; -fx-background-color: white; "
                + "-fx-effect: dropshadow(gaussian, #c299ff, 35, 0.5, 0, 4);");
        glow.setMaxSize(600, 500);

        StackPane infoWithGlow = new StackPane(glow, infoBox);
        infoWithGlow.setAlignment(Pos.CENTER);

        VBox centerBox = new VBox(30, imageBox, infoWithGlow);
        centerBox.setAlignment(Pos.TOP_CENTER);

        // Buttons
        Button editBtn = new Button("Edit Profile");
        editBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        editBtn.setFont(Font.font("Arial", 20));
        editBtn.setOnAction(e -> showEditDialog(UserProfilePageStage));

        HBox buttonBox = new HBox(30, editBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox mainContent = new VBox(40, centerBox, buttonBox);
        mainContent.setAlignment(Pos.TOP_CENTER);
        root.setCenter(mainContent);

        return new VBox(root);
    }

    private void fetchUserDataFromFirestore(String docId) {
        new Thread(() -> {
            try {
                 System.out.println("[DEBUG] Fetching user data for docId: " + docId);
                String endpoint = String.format(
                        "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/users/%s?key=%s",
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
                System.out.println("[DEBUG] Response Code: " + response);
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                conn.disconnect();

                String json = response.toString();
                String fetchedName = extractValue(json, "name");
                String fetchedUsername = extractValue(json, "username");
                String fetchedEmail = extractValue(json, "email");
                String fetchedRole = extractValue(json, "role");
                System.out.println("[DEBUG] Extracted -> Name: " + fetchedName + ", Username: " + fetchedUsername
                        + ", Email: " + fetchedEmail + ", Role: " + fetchedRole);

                Platform.runLater(() -> {
                    name = fetchedName;
                    username = "@" + fetchedUsername;
                    email = fetchedEmail;
                    role = fetchedRole;
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


    private Label createInfoLabel(String text, boolean isHeading) {
        Label label = new Label(text);
        if (isHeading) {
            label.setFont(Font.font("Segoe UI", 24));
            label.setTextFill(Color.web("#2C3E50"));
        } else {
            label.setFont(Font.font("Segoe UI", 16));
            label.setTextFill(Color.web("#555"));
        }
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
                "-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 6 20;"
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
        dialogVBox.setAlignment(Pos.CENTER_LEFT);

        Scene dialogScene = new Scene(dialogVBox, 400, 350);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void updateProfileUI() {
        nameLabel.setText(name);
        usernameLabel.setText(username);
        emailLabel.setText("Email: " + email);
        roleLabel.setText("Role: " + role);
    }

    private void changeProfileImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            profileImagePath = selectedFile.getAbsolutePath();
            profilePic.setImage(new Image(selectedFile.toURI().toString()));
        }
    }

    private String loadDefaultImage() {
        try {
            URL url = getClass().getResource("/Assets/Images/profile.jpg");
            if (url != null) {
                return new File(url.toURI()).getAbsolutePath();
            } else {
                System.out.println("Default image not found.");
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
