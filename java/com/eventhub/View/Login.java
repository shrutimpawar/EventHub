
package com.eventhub.View;

import com.eventhub.Controller.FireBaseAuthController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.eventhub.View.LoggedInHelper;

public class Login {

    Scene loginScene;
    Stage loginStage, primaryStage;

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    public VBox createLogin(Runnable back) {

        Image backimage = new Image("Assets/Images/loginback.jpg");
        ImageView backImageView = new ImageView(backimage);
        backImageView.setFitWidth(1540);
        backImageView.setFitHeight(795);
        backImageView.setPreserveRatio(false);
        backImageView.setOpacity(0.85);//new

        Label LoginLabel = new Label("Login");
        LoginLabel.setFont(Font.font("Arial", 25));
        LoginLabel.setTextFill(Color.web("#e6dedeff"));
        LoginLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");

        Region underline = new Region();
        underline.setPrefHeight(2);
        underline.setPrefWidth(60);
        underline.setBackground(new Background(new BackgroundFill(Color.web("#8a7e87ff"), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox formPane = new VBox(15);
        formPane.setMaxHeight(80);
        formPane.setMaxWidth(400);
        formPane.setPadding(new Insets(40));
        formPane.setSpacing(25);
        formPane.setAlignment(Pos.CENTER);
        formPane.setStyle("-fx-background-color: #bc8ac5ff;-fx-background-radius: 15;");
        formPane.setPrefWidth(300);

        Text emailtx = new Text("Email");
        emailtx.setFont(Font.font("Arial", 18));
        TextField email = new TextField();
        email.setStyle("-fx-background-color: #f0f0f0;-fx-border-color: #863889;-fx-border-radius: 8;-fx-background-radius: 8;-fx-padding: 8;-fx-font-size: 14px;");
        email.setMaxWidth(350);
        email.setPromptText("email address");
        email.setFocusTraversable(false);

        VBox emailVBox = new VBox(10, emailtx, email);

        Text passwordtx = new Text("Password");
        passwordtx.setFont(Font.font("Arial", 18));
        PasswordField password = new PasswordField();
        password.setStyle("-fx-background-color: #f0f0f0;-fx-border-color: #863889;-fx-border-radius: 8;-fx-background-radius: 8;-fx-padding: 8;-fx-font-size: 14px;");
        password.setMaxWidth(350);
        password.setPromptText("password");
        password.setFocusTraversable(false);
        VBox passwordVBox = new VBox(10, passwordtx, password);

        Text roletx = new Text("Role");
        roletx.setFont(Font.font("Arial", 18));
        ComboBox<String> role = new ComboBox<>();
        role.getItems().addAll("Organizer", "User", "Admin");
        role.setStyle("-fx-background-color: #f0f0f0;-fx-border-color: #863889;-fx-border-radius: 8;-fx-background-radius: 8;-fx-padding: 8;-fx-font-size: 14px;");
        role.setMaxWidth(350);
        role.setPromptText("Select Role");
        role.setFocusTraversable(false);

        VBox roleVBox = new VBox(10, roletx, role);

        Button LoginBtn = new Button("Login");
        LoginBtn.setMaxWidth(100);
        Label resultLabel = new Label();
        FireBaseAuthController controllerobj = new FireBaseAuthController();

        LoginBtn.setStyle("-fx-background-radius: 30;-fx-padding: 8 20;-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);-fx-text-fill: white;-fx-font-size: 15px;");

        LoginBtn.setOnAction(e -> {
            System.out.println("login button clicked!");
            String emailvar = email.getText();
            String password1 = password.getText();
            String roleId = role.getValue() == null ? "" : role.getValue().trim();
            System.out.println("[DEBUG] Entered role: " + roleId);

            if (emailvar.isEmpty() || password1.isEmpty() || roleId.isEmpty()) {
                resultLabel.setText("Email, Password, and Role must not be empty.");
                return;
            }

            // Hardcoded Admin
            if (emailvar.equalsIgnoreCase("admin@eventhub.com") && password1.equals("admin123")) {
                resultLabel.setText("Admin Login Successful");
                AdminDashboard adminDashboard = new AdminDashboard();
                Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
                try {
                    adminDashboard.start(currentStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }

            boolean success = controllerobj.signInwithEmailandPassword(emailvar, password1);

            if (success) {
                // Fetch docId based on role
                String collection = roleId.equalsIgnoreCase("Organizer") ? "organizer" : "users";
                String docId = fetchDocumentId(emailvar, collection);
                System.out.println("[DEBUG] fetchDocumentId returned: " + docId);
                LoggedInHelper.setUserDocId(docId);
                LoggedInHelper.setEmail(emailvar);

                if (roleId.equalsIgnoreCase("Organizer")) {
                    resultLabel.setText("Login Successful");
                    OrganizerDashboard organizerDashboard = new OrganizerDashboard();
                    organizerDashboard.setOrganizerDocId(docId);  // pass Firestore docId
                    Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
                    organizerDashboard.showOrganizerDashboard(currentStage);
                } else {
                    Homepage homeobj = new Homepage();
                    homeobj.showHomePage(loginStage);

                    // Prepare user profile page
                    UserProfilePage profilePage = new UserProfilePage();
                    profilePage.setUserDocId(docId);
                    profilePage.setUserProfilePageStage(loginStage);
                    Scene scene = new Scene(profilePage.createUserProfileScene(), 1540, 795);
                    profilePage.setUserProfilePageScene(scene);
                }
            } else {
                resultLabel.setText("Login Failed: Credentials invalid.");
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setMaxWidth(100);
        backBtn.setMaxHeight(80);
        backBtn.setFont(new Font(16));
        backBtn.setStyle("-fx-background-radius: 30;-fx-padding: 8 20;-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);-fx-text-fill: white;-fx-font-size: 15px;");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                back.run();
            }
        });

        formPane.getChildren().addAll(LoginLabel, underline, emailVBox, passwordVBox, roleVBox, resultLabel, LoginBtn, backBtn);

        StackPane stkpane = new StackPane();
        stkpane.getChildren().addAll(backImageView, formPane);
        stkpane.setAlignment(Pos.CENTER);

        VBox vb_main = new VBox(stkpane);
        vb_main.setMaxHeight(800);
        vb_main.setMaxWidth(1000);
        return vb_main;
    }

    private String fetchDocumentId(String email, String collection) {
        try {
            System.out.println("[DEBUG] Fetching docId for email: " + email + " in collection: " + collection);

            String query = "{ \"structuredQuery\": { \"from\": [{\"collectionId\": \"" + collection + "\"}], " +
                    "\"where\": {\"fieldFilter\": {\"field\": {\"fieldPath\": \"email\"}, \"op\": \"EQUAL\", " +
                    "\"value\": {\"stringValue\": \"" + email + "\"}}}, \"limit\": 1}}";

            URL url = new URL("https://firestore.googleapis.com/v1/projects/event-hub-7033e/databases/(default)/documents:runQuery");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.getOutputStream().write(query.getBytes(StandardCharsets.UTF_8));

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            conn.disconnect();

            String json = response.toString();
            System.out.println("[DEBUG] Firestore runQuery response: " + json);

            int nameIndex = json.indexOf("\"name\"");
            if (nameIndex == -1) {
                System.out.println("[DEBUG] Email not found in Firestore query response.");
                return "";
            }
            int startQuote = json.indexOf("\"", nameIndex + 7);
            int endQuote = json.indexOf("\"", startQuote + 1);
            String fullPath = json.substring(startQuote + 1, endQuote);
            System.out.println("[DEBUG] Full Firestore path: " + fullPath);
            return fullPath.substring(fullPath.lastIndexOf("/") + 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
