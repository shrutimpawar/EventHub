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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrganizerSignUp {

    static final String PROJECT_ID = "event-hub-7033e";
    static final String API_KEY = "AIzaSyBRUq__-ukWR0ZgoZBfombRkHpd9G_MMWA";

    FireBaseAuthController controllerobj = new FireBaseAuthController();

    Scene organizerSignUpScene;
    Stage oganizerSignUpStage, primaryStage;

    public void setOrganizerSignUpScene(Scene organizerSignUpScene) {
        this.organizerSignUpScene = organizerSignUpScene;
    }

    public void setOganizerSignUpStage(Stage oganizerSignUpStage) {
        this.oganizerSignUpStage = oganizerSignUpStage;
    }

    public VBox createorganizerSignUpScene(Runnable back) {

        HBox root = new HBox();
        root.setPrefSize(1000, 800);

        VBox leftPane = new VBox();
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPrefWidth(600);
        leftPane.setSpacing(20);
        leftPane.setPadding(new Insets(20));
        leftPane.setStyle("-fx-background-color: linear-gradient(to bottom left, #e57a44, #251351);");

        Label logo = new Label("● Event Hub ●");
       // logo.setAlignment(Pos.TOP_LEFT);
        logo.setFont(Font.font("Arial", 24));
        logo.setTextFill(Color.WHITE);

        
        Label desc = new Label(
                "We bring local events to your fingertips, helping you stay connected with everything happening around you. Whether it's concerts, exhibitions, meetups, or college fests you'll find it all here.\n"
                        + "\n" + "✨ Sign up now and never miss out on what’s happening nearby!");
        desc.setWrapText(true);
        desc.setTextFill(Color.LIGHTGRAY);
        desc.setFont(Font.font("Arial", 18));

        leftPane.getChildren().addAll(logo, desc);

        Label signUpLabel = new Label("Sign up as Organizer");
        signUpLabel.setFont(Font.font("Arial", 25));
        signUpLabel.setTextFill(Color.web("#010101ff"));
        signUpLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");

        Region underline = new Region();
        underline.setPrefHeight(2);
        underline.setMaxWidth(540);
        underline.setBackground(new Background(new BackgroundFill(Color.web("#ff7fdf"), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox formPane = new VBox(15);
     //   formPane.setPadding(new Insets(70));
        formPane.setSpacing(25);
        formPane.setAlignment(Pos.CENTER);
        formPane.setStyle("-fx-background-color: #6e3482;");
        formPane.setPrefWidth(950);
        formPane.setPadding(new Insets(0, 0, 0, 200));
        formPane.setPrefHeight(Region.USE_COMPUTED_SIZE);// change
        VBox.setVgrow(formPane, javafx.scene.layout.Priority.ALWAYS);// cahnge

        Text fullname = new Text("Full Name");
        fullname.setStyle("-fx-text-fill: white;");
        TextField organizer = new TextField();
        organizer.setPromptText("full name");
        organizer.setMaxWidth(550);
        organizer.setFocusTraversable(false);
        
        organizer.setStyle(
                "-fx-background-color: #f0f0f0;" + // Light gray background
                        "-fx-border-color: #863889;" + // Purple border
                        "-fx-border-radius: 8;" + // Rounded corners
                        "-fx-background-radius: 8;" + // Rounded background
                        "-fx-padding: 8;" + // Inner spacing
                        "-fx-font-size: 16px;" // Text size
                        
        );

        VBox organizerBox = new VBox(10, fullname, organizer);

        Text emailtx = new Text("Email");
        TextField email = new TextField();
        email.setMaxWidth(550);
        email.setPromptText("email address");
        email.setStyle(
                "-fx-background-color: #f0f0f0;" + // Light gray background
                        "-fx-border-color: #863889;" + // Purple border
                        "-fx-border-radius: 8;" + // Rounded corners
                        "-fx-background-radius: 8;" + // Rounded background
                        "-fx-padding: 8;" + // Inner spacing
                        "-fx-font-size: 14px;" // Text size
        );

        VBox emailBox = new VBox(15, emailtx, email);

        Text organizernametx = new Text("Username");
        TextField organizername = new TextField();
        organizername.setMaxWidth(550);
        organizername.setPromptText("User name");
        organizername.setStyle(
                "-fx-background-color: #f0f0f0;" + // Light gray background
                        "-fx-border-color: #863889;" + // Purple border
                        "-fx-border-radius: 8;" + // Rounded corners
                        "-fx-background-radius: 8;" + // Rounded background
                        "-fx-padding: 8;" + // Inner spacing
                        "-fx-font-size: 14px;" // Text size
        );

        VBox organizerNameBox = new VBox(15, organizernametx, organizername);

        Text passwordtx = new Text("Password");
        PasswordField organizerpassword = new PasswordField();
        organizerpassword.setMaxWidth(550);
        organizerpassword.setPromptText("password");
        organizerpassword.setStyle(
                "-fx-background-color: #f0f0f0;" + // Light gray background
                        "-fx-border-color: #863889;" + // Purple border
                        "-fx-border-radius: 8;" + // Rounded corners
                        "-fx-background-radius: 8;" + // Rounded background
                        "-fx-padding: 8;" + // Inner spacing
                        "-fx-font-size: 14px;" // Text size
        );

        VBox passwordBox = new VBox(15, passwordtx, organizerpassword);

        Text roletx = new Text("Role(organizer/User)");
        TextField role = new TextField();
        role.setMaxWidth(550);
        role.setPromptText("user/organizer");
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

        Label resultLabel = new Label();

        Button SignUpBtn = new Button("Sign Up");
        SignUpBtn.setStyle(
                "-fx-background-radius: 30;" + // Rounded edges
                        "-fx-padding: 8 20;" + // Top-bottom and left-right padding
                        "-fx-background-color: linear-gradient(to right, #87f5f5, #f042ff);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
         SignUpBtn.setMaxWidth(100);

        SignUpBtn.setOnAction(e -> {
            System.out.println("SignUp button clicked!");

            String fullName = organizer.getText();
            String Email = email.getText();
            String userName = organizername.getText();
            String Role = role.getText();
            String emailvar = email.getText().trim();
            String password = organizerpassword.getText().trim();

            if (emailvar.isEmpty() || password.isEmpty()) {
                resultLabel.setText("Email and Password must not be empty.");
                return;
            }

            boolean success = controllerobj.signUpwithEmailandPassword(emailvar, password);
            String result = addOrganizerToFirestore(fullName, Email, userName, password, Role);

            if (success) {
                resultLabel.setText("SignUp Successful");
                OrganizerDashboard organizerDashboardobj = new OrganizerDashboard();
                Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
                organizerDashboardobj.showOrganizerDashboard(currentStage);
            } else {
                resultLabel.setText("SignUp Failed: Email already exists or is invalid.");
            }
        });

        VBox vbLabel=new VBox(15,signUpLabel);
        vbLabel.setPadding(new Insets(0,0,0,200));

        VBox vbunderline = new VBox(15, underline);
        // vbunderline.setAlignment(Pos.CENTER);
        // vbLabel.setPadding(new Insets(0,0,0,200));

        VBox vbButton = new VBox(15, SignUpBtn, BackBtn);
        vbButton.setPadding(new Insets(0, 0, 0, 200));
        // vbButton.setAlignment(Pos.CENTER);

        formPane.getChildren().addAll(vbLabel, vbunderline, organizerBox, emailBox, organizerNameBox,passwordBox, roleBox, vbButton,resultLabel);
        formPane.setAlignment(Pos.CENTER);

        VBox formWrapper=new VBox(formPane);
        formWrapper.setPrefHeight(800); // or same as root height
        VBox.setVgrow(formWrapper, javafx.scene.layout.Priority.ALWAYS); // 🔥 important
        root.setPrefSize(1540, 795); // Match your app window size//cahnge

        //formPane.setMaxSize(1540, 795);
      //  formPane.setMaxHeight(800);
        root.getChildren().addAll(leftPane,formWrapper);

        VBox vbmain = new VBox(root);
        vbmain.setMaxSize(1540, 795);
        return vbmain;
    }

    // --------- FIXED METHOD MOVED OUTSIDE -----------
    private String addOrganizerToFirestore(String fullName, String Email, String userName, String password, String Role) {
        if (fullName.isEmpty() || Email.isEmpty() || userName.isEmpty() || password.isEmpty() || Role.isEmpty()) {
            return "Please enter all details!!!";
        }
        String endpoint = String.format(
                "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/organizer?key=%s",
                PROJECT_ID, API_KEY);
        String payload = String.format("{\"fields\": {"
                + "\"name\": {\"stringValue\": \"%s\"},"
                + "\"email\": {\"stringValue\": \"%s\"},"
                + "\"username\": {\"stringValue\": \"%s\"},"
                + "\"password\": {\"stringValue\": \"%s\"},"
                + "\"role\": {\"stringValue\": \"%s\"}"
                + "}}", fullName, Email, userName, password, Role);

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
            return "Error: " + e.getMessage();
        }
    }
}
