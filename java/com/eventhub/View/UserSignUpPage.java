package com.eventhub.View;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.eventhub.Controller.FireBaseAuthController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserSignUpPage {

    static final String PROJECT_ID = "event-hub-7033e";
    static final String API_KEY = "GOOGLE_API_KEY";
    Scene userSignUpScene;
    Stage UserSignUpStage;
    Scene signuploginscene, organizerSignUpScene, loginScene, MultipleSelectionScene;
    Stage primaryStage;

    public void setScene(Scene scene) {
        this.userSignUpScene = scene;
    }

    public void setStage(Stage stage) {
        UserSignUpStage = stage;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public VBox createSignUpScene(Runnable back) {

        HBox root = new HBox();
        root.setPrefSize(1000, 800);

        VBox leftPane = new VBox();
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPrefWidth(600);
        leftPane.setSpacing(20);
        leftPane.setPadding(new Insets(20));
        leftPane.setStyle("-fx-background-color: linear-gradient(to bottom left, #e57a44, #251351);");

        Label logo = new Label("● Event Hub ●");
        // logo.setAlignment(Pos.TOP_CENTER);
        logo.setFont(Font.font("Arial", 24));
        logo.setTextFill(Color.WHITE);
        logo.setStyle("-fx-text-alignment: center;");

        Label desc = new Label(
                "We bring local events to your fingertips, helping you stay connected with everything happening around you. Whether it's concerts, exhibitions, meetups, or college fests you'll find it all here.\n"
                        + "\n" + "✨ Sign up now and never miss out on what’s happening nearby!");
        desc.setWrapText(true);
        desc.setTextFill(Color.LIGHTGRAY);
        desc.setFont(Font.font("Arial", 18));

        leftPane.getChildren().addAll(logo, desc);

        Label signUpLabel = new Label("Sign up as User");
        signUpLabel.setFont(Font.font("Arial", 25));
        signUpLabel.setTextFill(Color.web("#010101ff"));
        signUpLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");

        Region underline = new Region();
        underline.setPrefHeight(2);
        underline.setMaxWidth(540);
        underline.setBackground(
                new Background(new BackgroundFill(Color.web("#ff7fdf"), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox formPane = new VBox(15);

        // formPane.setPadding(new Insets(150));
        formPane.setSpacing(25);
        formPane.setAlignment(Pos.CENTER);
        formPane.setStyle("-fx-background-color: #6e3482;");
        formPane.setPrefWidth(950);
        formPane.setPadding(new Insets(0, 0, 0, 200));
        formPane.setPrefHeight(Region.USE_COMPUTED_SIZE);// change
        VBox.setVgrow(formPane, javafx.scene.layout.Priority.ALWAYS);// cahnge

        Text fullname = new Text("Full Name");
        TextField user = new TextField();
        user.setMaxWidth(550);
        user.setPromptText("full name");
        user.setStyle(
                "-fx-background-color: #f0f0f0;" + // Light gray background
                        "-fx-border-color: #863889;" + // Purple border
                        "-fx-border-radius: 8;" + // Rounded corners
                        "-fx-background-radius: 8;" + // Rounded background
                        "-fx-padding: 8;" + // Inner spacing
                        "-fx-font-size: 14px;" // Text size
        );
        user.setMaxWidth(550);
        user.setFocusTraversable(false);

        VBox userBox = new VBox(15, fullname, user);

        Text emailtx = new Text("Email");
        TextField email = new TextField();
        email.setMaxWidth(550);
        email.setPromptText("email address");
        email.setFocusTraversable(false);
        email.setStyle(
                "-fx-background-color: #f0f0f0;" + // Light gray background
                        "-fx-border-color: #863889;" + // Purple border
                        "-fx-border-radius: 8;" + // Rounded corners
                        "-fx-background-radius: 8;" + // Rounded background
                        "-fx-padding: 8;" + // Inner spacing
                        "-fx-font-size: 14px;" // Text size
        );
        VBox emailBox = new VBox(15, emailtx, email);

        Text usernametx = new Text("Username");
        TextField username = new TextField();
        username.setMaxWidth(550);
        username.setPromptText("User name");
        username.setFocusTraversable(false);

        username.setStyle(
                "-fx-background-color: #f0f0f0;" + // Light gray background
                        "-fx-border-color: #863889;" + // Purple border
                        "-fx-border-radius: 8;" + // Rounded corners
                        "-fx-background-radius: 8;" + // Rounded background
                        "-fx-padding: 8;" + // Inner spacing
                        "-fx-font-size: 14px;" // Text size
        );
        VBox usernameBox = new VBox(15, usernametx, username);

        Text passwordtx = new Text("Password");
        PasswordField userpassword = new PasswordField();
        userpassword.setMaxWidth(550);
        userpassword.setPromptText("password");
        userpassword.setFocusTraversable(false);
        userpassword.setStyle(
                "-fx-background-color: #f0f0f0;" + // Light gray background
                        "-fx-border-color: #863889;" + // Purple border
                        "-fx-border-radius: 8;" + // Rounded corners
                        "-fx-background-radius: 8;" + // Rounded background
                        "-fx-padding: 8;" + // Inner spacing
                        "-fx-font-size: 14px;" // Text size
        );
        VBox passwordBox = new VBox(15, passwordtx, userpassword);

        Text roletx = new Text("Role(organizer/User)");
        TextField role = new TextField();
        role.setMaxWidth(550);
        role.setPromptText("user/organizer");
        role.setFocusTraversable(false);
        role.setStyle(
                "-fx-background-color: #f0f0f0;" + // Light gray background
                        "-fx-border-color: #863889;" + // Purple border
                        "-fx-border-radius: 8;" + // Rounded corners
                        "-fx-background-radius: 8;" + // Rounded background
                        "-fx-padding: 8;" + // Inner spacing
                        "-fx-font-size: 14px;" // Text size
        );

        VBox roleBox = new VBox(15, roletx, role);

        Button BackBtn = new Button("Back");
        BackBtn.setMaxWidth(100);
        BackBtn.setMaxHeight(80);
        BackBtn.setFont(new Font(16));
        BackBtn.setStyle("-fx-fill:white;-fx-background-color : #ff7f50");
        BackBtn.setStyle(
                "-fx-background-radius: 30;" + // Rounded edges
                        "-fx-padding: 8 20;" + // Top-bottom and left-right padding
                        "-fx-background-color: linear-gradient(to right, #87f5f5, #f042ff);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" + "-fx-font-weight: bold;");

        BackBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                back.run();
            }

        });

        Button SignUpBtn = new Button("SignUp");
        SignUpBtn.setMaxWidth(100);
        Label resultLabel1 = new Label();
        Label resultLabel2 = new Label();
        FireBaseAuthController controllerobj = new FireBaseAuthController();

        SignUpBtn.setStyle(
                "-fx-background-radius: 30;" + // Rounded edges
                        "-fx-padding: 8 20;" + // Top-bottom and left-right padding
                        "-fx-background-color: linear-gradient(to right, #87f5f5, #f042ff);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        SignUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("SignUp button clicked!");
                String fullName = user.getText();
                String userEmail = email.getText();
                String userName = username.getText();
                String userRole = role.getText();
                String emailvar = email.getText().trim();
                String password = userpassword.getText().trim();

                if (emailvar.isEmpty() || password.isEmpty()) {
                    resultLabel1.setText("Email and Password must not be empty.");
                    return;
                }

                boolean success = controllerobj.signUpwithEmailandPassword(emailvar, password);
                String result = addUserToFirestore(fullName, userEmail, userName, password, userRole); //called database 
                resultLabel2.setText(result);
                if (success) {
                    resultLabel1.setText("SignUp Successful");
                    System.out.println("signup complete: " + resultLabel1.getText());
                    initializeMultipleSelection();
                    System.out.println("Scene initialized");
                    primaryStage.setScene(MultipleSelectionScene);
                    System.out.println("Scene set!");
                } else {
                    resultLabel1.setText("SignUp Failed: Email already exists or is invalid.");
                    System.out.println("signup failed: " + resultLabel1.getText());
                }
            }

            static String addUserToFirestore(String fullName, String userEmail, String userName, String password,
                    String userRole) {
                if (fullName.isEmpty() || userEmail.isEmpty() || userEmail.isEmpty() || userName.isEmpty()
                        || password.isEmpty() || userRole.isEmpty()) {
                    return "Please enter all details!!!";
                }
                String endpoint = String.format(
                        "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/users?key=%s",
                        PROJECT_ID, API_KEY);
                String payload = String.format("{\"fields\": {"
                                                                + "\"name\": {\"stringValue\": \"%s\"},"
                                                                + "\"email\": {\"stringValue\": \"%s\"},"
                                                                + "\"username\": {\"stringValue\": \"%s\"},"
                                                                + "\"password\": {\"stringValue\": \"%s\"},"
                                                                + "\"role\": {\"stringValue\": \"%s\"}"
                                                                + "}}", fullName, userEmail, userName, password, userRole);

                try {
                    URL url = new URL(endpoint);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);
                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(payload.getBytes(StandardCharsets.UTF_8));
                    }
                    InputStream is = conn.getInputStream();
                    byte[] responseBytes = is.readAllBytes();
                    String response = new String(responseBytes, StandardCharsets.UTF_8);
                    is.close();
                    conn.disconnect();
                    return "Added! Firestore says:\n" + response;
                } catch (Exception e) {
                    return "Error: " ;//+ e.getMessage();
                }
            }

        });

        VBox vbLabel = new VBox(15, signUpLabel);
        vbLabel.setPadding(new Insets(0, 0, 0, 200));

        VBox vbunderline = new VBox(15, underline);
        // vbunderline.setAlignment(Pos.CENTER);
        // vbLabel.setPadding(new Insets(0,0,0,200));
        VBox vbButton = new VBox(15, SignUpBtn, BackBtn);
        vbButton.setPadding(new Insets(0, 0, 0, 200));
        // vbButton.setAlignment(Pos.CENTER);

         formPane.getChildren().addAll(vbLabel, vbunderline, userBox, emailBox, usernameBox, passwordBox, roleBox,
                 vbButton, resultLabel1);


        formPane.setAlignment(Pos.CENTER);

        VBox formWrapper = new VBox(formPane);

        formWrapper.setAlignment(Pos.CENTER);
        formWrapper.setPrefHeight(800); // or same as root height
        VBox.setVgrow(formWrapper, javafx.scene.layout.Priority.ALWAYS); // 🔥 important

        root.setPrefSize(1540, 795); // Match your app window size//cahnge

        // root.getChildren().addAll(leftPane, formPane);//change
        root.getChildren().addAll(leftPane, formWrapper);

        VBox vbmain = new VBox(root);
        vbmain.setPrefSize(1540, 795);// change

        return vbmain;
    }

    public void initializeMultipleSelection() {

        MultipleSelection page3 = new MultipleSelection();
        page3.setMultipleStage(primaryStage);
        MultipleSelectionScene = new Scene(page3.createMultipleScene(), 1540, 795);
        page3.setMultipleScene(MultipleSelectionScene);

    }

}

