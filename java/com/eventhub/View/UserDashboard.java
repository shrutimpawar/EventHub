package com.eventhub.View;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.*;

import java.io.File;

public class UserDashboard  {
    private ImageView profileImageView;

    Stage primaryStage;
    public void setProfileImageView(ImageView profileImageView) {
        this.profileImageView = profileImageView;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setUserProfilePageScene(Scene userProfilePageScene) {
        UserProfilePageScene = userProfilePageScene;
    }

    public void setEventDetailPageScene(Scene eventDetailPageScene) {
        EventDetailPageScene = eventDetailPageScene;
    }

    public void setSignuploginscene(Scene signuploginscene) {
        this.signuploginscene = signuploginscene;
    }

    Scene UserProfilePageScene, EventDetailPageScene,signuploginscene;


    public void showUserDashboardPage() {
        this.primaryStage = primaryStage;

        StackPane root = new StackPane();
        root.setPrefSize(1000, 600);
        // BACK BUTTON TO HOMEPAGE
        
Button backButton = new Button("<<");
backButton.setFont(Font.font("Arial", 20));
 backButton.setStyle("-fx-background-color: white;-fx-background-radius: 160; -fx-text-fill: #9f52a9ff;");
backButton.setOnAction(e -> {
    Homepage homepage = new Homepage();
    homepage.setPrimaryStage(primaryStage);
    homepage.showHomePage(primaryStage);
});

HBox topBar = new HBox(backButton);
topBar.setPadding(new Insets(20, 0, 0, 20));
topBar.setAlignment(Pos.TOP_LEFT);


        ImageView backgroundImage = new ImageView(
                new Image("https://images.unsplash.com/photo-1503264116251-35a269479413?auto=format&fit=crop&w=1600&q=80")
        );
        backgroundImage.setFitWidth(1540);
        backgroundImage.setFitHeight(795);
        backgroundImage.setPreserveRatio(false);
        backgroundImage.setSmooth(true);

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(30));
        centerBox.setMaxWidth(300);
        centerBox.setMaxHeight(400);
        centerBox.setStyle("-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 12;");

        profileImageView = new ImageView(new Image("https://img.icons8.com/ios-filled/50/user-male-circle.png"));
        profileImageView.setFitHeight(100);
        profileImageView.setFitWidth(100);
        Circle clip = new Circle(50, 50, 50);
        profileImageView.setClip(clip);
        //profileImageView.setOnMouseClicked(e -> showProfilePopup(primaryStage));

        Label nameLabel = new Label("Welcome");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nameLabel.setTextFill(Color.DARKMAGENTA);

        Button uploadButton = new Button("Upload Image");
        uploadButton.setOnAction(e -> chooseImage(primaryStage));

        Button profileButton = new Button("Your Profile");
        
        profileButton.setOnAction(e -> {
            initializeUserProfilePage();
            primaryStage.setScene(UserProfilePageScene);
        });

        Button exploreBtn = new Button("🔍 Explore");
       
        exploreBtn.setOnAction(e -> {
            try {
                initializeEventDetailPage();
                primaryStage.setScene(EventDetailPageScene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        

        for (Button btn : new Button[]{uploadButton, profileButton, exploreBtn}) {
             btn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        btn.setFont(Font.font("Arial",20));
            btn.setPrefWidth(200);
            //btn.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 6;");
        }

        centerBox.getChildren().addAll(profileImageView, nameLabel, uploadButton, profileButton, exploreBtn);

        root.getChildren().addAll(backgroundImage,topBar,  centerBox);
        StackPane.setAlignment(centerBox, Pos.CENTER);

        Scene scene = new Scene(root);
        primaryStage.setTitle("User Dashboard - EventHub");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void chooseImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Image");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            profileImageView.setImage(image);
        }
    }

    private void showProfilePopup(Stage owner) {
        Stage profileStage = new Stage();
        profileStage.initOwner(owner);
        profileStage.initModality(Modality.WINDOW_MODAL);
        profileStage.setTitle("Your Profile");

        VBox profileBox = new VBox(15);
        profileBox.setPadding(new Insets(20));
        profileBox.setAlignment(Pos.TOP_CENTER);
        profileBox.setStyle("-fx-background-color: white;");

        ImageView largeProfile = new ImageView(profileImageView.getImage());
        largeProfile.setFitHeight(100);
        largeProfile.setFitWidth(100);
        Circle clip = new Circle(50, 50, 50);
        largeProfile.setClip(clip);

        Label name = new Label("Shruti");
        name.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label email = new Label("shruti@example.com");
        Label location = new Label("Pune, India");

        Button editBtn = new Button("✏️ Edit Profile");
        editBtn.setStyle("-fx-background-color: #d1b3ff; -fx-text-fill: white;");

        profileBox.getChildren().addAll(largeProfile, name, email, location, editBtn);

        Scene scene = new Scene(profileBox, 300, 300);
        profileStage.setScene(scene);
        profileStage.show();
    }
  


    public void initializeUserProfilePage() {
        UserProfilePage userProfpage = new UserProfilePage();
        userProfpage.setUserProfilePageStage(primaryStage);
        UserProfilePageScene = new Scene(userProfpage.createUserProfileScene(), 1540, 795);
        userProfpage.setUserProfilePageScene(UserProfilePageScene);
    }

    public void initializeEventDetailPage() {
        EventDetailPage eventDetailPage = new EventDetailPage();
        VBox eventDetailRoot = eventDetailPage.createEventDetailPageScene();
        EventDetailPageScene = new Scene(eventDetailRoot, 1540, 795);
        eventDetailPage.setEventDetailPageStage(primaryStage);
        eventDetailPage.setEventDetailPageScene(EventDetailPageScene);
    }

   
}
