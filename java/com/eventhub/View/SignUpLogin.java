package com.eventhub.View;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUpLogin extends Application {

    public void setUserSignUpScene(Scene userSignUpScene) {
        this.userSignUpScene = userSignUpScene;
    }

    public void setSignuploginscene(Scene signuploginscene) {
        this.signuploginscene = signuploginscene;
    }

    public void setOrganizerSignUpScene(Scene organizerSignUpScene) {
        this.organizerSignUpScene = organizerSignUpScene;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    Scene userSignUpScene, signuploginscene, organizerSignUpScene, loginScene;
    Stage primaryStage;

    @Override
    public void start(Stage myStage)  {

        LandingPage landingPage = new LandingPage();
        Scene landingScene = landingPage.createLandingScene();

        myStage.setScene(landingScene);
        myStage.setTitle("EventHub - Landing Page");
        myStage.setMaximized(true);
        myStage.show();

        // Auto-redirect after 3 seconds to SignUp/Login scene
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> showSignUpLoginScene(myStage));
     delay.play();
    }

     public void showSignUpLoginScene(Stage myStage){

        Image backimage = new Image("Assets/Images/bg.jpg");
        ImageView backView = new ImageView(backimage);
        backView.setOpacity(0.5);

        backView.setFitWidth(1530);
        backView.setFitHeight(800);
        backView.setPreserveRatio(false);

        Image img = new Image("Assets/Images/logo.jpg");
        ImageView imView = new ImageView(img);
        imView.setFitWidth(200);
        imView.setPreserveRatio(true);

        Circle clip = new Circle(99, 99, 99); // centerX, centerY, radius
        imView.setClip(clip);

        Text welcometx = new Text("Welcome to EventHub");
        welcometx.setFont(new Font("Arial", 35));
        welcometx.setStyle("-fx-fill:white;");

        Button userBtn = new Button("SignUp as User");
        userBtn.setMaxWidth(200);
        userBtn.setMaxHeight(80);
        userBtn.setFont(new Font(16));
        // userBtn.setStyle("-fx-fill:white;-fx-background-color : #ff7f50");

        userBtn.setStyle(
                "-fx-background-radius: 30;" + // Rounded edges
                        "-fx-padding: 8 20;" + // Top-bottom and left-right padding
                        "-fx-background-color: linear-gradient(to right, #87f5f5, #f042ff);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" + "-fx-font-weight: bold;");

        userBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeUsersignUpPage();
                primaryStage.setScene(userSignUpScene);
            }

        });

        Button organizerBtn = new Button("SignUp as Organizer");
        organizerBtn.setMaxWidth(200);
        organizerBtn.setMaxHeight(150);
        organizerBtn.setFont(new Font(16));

        organizerBtn.setStyle("-fx-fill:white;-fx-background-color : #bde4f4");
        organizerBtn.setStyle(
                "-fx-background-radius: 30;" + // Rounded edges
                        "-fx-padding: 8 20;" + // Top-bottom and left-right padding
                        "-fx-background-color: linear-gradient(to right, #87f5f5, #f042ff);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" + "-fx-font-weight: bold;");

        organizerBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeOrganizersignUpPage();
                primaryStage.setScene(organizerSignUpScene);
            }

        });

        Region underline = new Region();
        underline.setPrefHeight(2);
        underline.setPrefWidth(60);
        underline.setBackground(
                new Background(new BackgroundFill(Color.web("#ff7fdf"), CornerRadii.EMPTY, Insets.EMPTY)));

        Hyperlink goToLogin = new Hyperlink("Already have an account? Login");
        goToLogin.setStyle("-fx-text-fill: #fafaf4ff; -fx-font-size: 14px;");

        goToLogin.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                initializeLoginPage();
                primaryStage.setScene(loginScene);

            }

        });

        VBox vb = new VBox(25, imView, welcometx, userBtn, organizerBtn, underline, goToLogin);
        vb.setMaxHeight(80);
        vb.setMaxWidth(200);
        vb.setAlignment(Pos.TOP_CENTER);
        vb.setStyle("-fx-background-color: #420d4b; -fx-padding: 30px; -fx-background-radius: 15;");

        StackPane stpane = new StackPane();
        stpane.getChildren().addAll(backView, vb);
        stpane.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(stpane,1540, 795);
        myStage.setScene(myScene);
       // myStage.setMaximized(true);
        signuploginscene = myScene;
        primaryStage = myStage;
        primaryStage.setMaximized(true);
        myStage.show();

    }


    private void styleButton(Button organizerBtn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'styleButton'");
    }

    public void initializeUsersignUpPage() {

        UserSignUpPage page2 = new UserSignUpPage();
        page2.setStage(primaryStage);
        page2.setPrimaryStage(primaryStage); 
        userSignUpScene = new Scene(page2.createSignUpScene(this::handleBackButton), 1540, 795);
        page2.setScene(userSignUpScene);

    }

    private void handleBackButton() {
        primaryStage.setScene(signuploginscene);
    } 

    public void initializeOrganizersignUpPage() {

        OrganizerSignUp page2 = new OrganizerSignUp();
        page2.setOganizerSignUpStage(primaryStage);
        organizerSignUpScene = new Scene(page2.createorganizerSignUpScene(this::organizerhandleBackButton), 1540, 795);
        page2.setOrganizerSignUpScene(organizerSignUpScene);

    }

    private void organizerhandleBackButton() {
        primaryStage.setScene(signuploginscene);
    }

    public void initializeLoginPage() {

        Login page = new Login();
        page.setLoginStage(primaryStage);
        loginScene = new Scene(page.createLogin(this::loginhandleBackButton), 1540, 795);
        page.setLoginScene(loginScene);

    }

    private void loginhandleBackButton() {
        primaryStage.setScene(signuploginscene);
    }

}
