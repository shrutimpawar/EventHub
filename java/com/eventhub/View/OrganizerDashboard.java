package com.eventhub.View;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OrganizerDashboard{
     private String organizerDocId;  // <-- new field

    public void setOrganizerDocId(String docId) {
        this.organizerDocId = docId;
        System.out.println("[DEBUG] OrganizerDashboard docId set: " + docId);
    }

    public void showOrganizerDashboard(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPrefSize(1540, 795);

        // Sidebar
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #eec8c8ff;-fx-background-radius: 10; -fx-border-color: #ddd; -fx-border-radius: 10;");
        sidebar.setPrefWidth(240);

        Label logo = new Label("Dashboard");
        logo.setFont(Font.font("Arial", 30));

        Button btn0 = createSidebarButton("👤 Edit Profile");
        //Button btn1 = createSidebarButton("🏠 Home Page");
        Button btn2 = createSidebarButton("📝 Create new Events");
        //Button btn3 = createSidebarButton("📁 Manage");
        Button btn4 = createSidebarButton("📅 View Booking");
        Button btn5 = createSidebarButton("🌐 Explore Event");
        Button btn6 = createSidebarButton("➡ Log Out");

        sidebar.getChildren().addAll( logo, btn0,  btn2,  btn4, btn5,btn6 );

        // Load background image
        Image backgroundImage = new Image("Assets\\Images\\bg.jpg");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(1300);
        backgroundView.setFitHeight(800);
        backgroundView.setPreserveRatio(false);
        backgroundView.setOpacity(0.6);
        

        // Top Banner
        VBox banner = new VBox(10);
        banner.setPadding(new Insets(10,10,10,10));
        banner.setPrefHeight(100);
        banner.setAlignment(Pos.CENTER);
        banner.setStyle("-fx-background-color: #271539; -fx-background-radius: 10;");
        banner.setOpacity(0.8);
        Label bannerText = new Label("Start organizing. The crowd is waiting!");
        //bannerText.setAlignment(Pos.CENTER);
        bannerText.setFont(Font.font("Arial", 30));
        bannerText.setTextFill(Color.WHITE);
        banner.getChildren().add(bannerText);

        // Search Bar
        //HBox searchBox = new HBox(10);
        //TextField searchField = new TextField();
        //searchField.setPromptText("Search by Keyword, Category etc.");
        //searchField.setPrefWidth(800);
        //Button searchBtn = new Button("Search View");
        //earchBtn.setStyle("-fx-background-color: #6366F1; -fx-text-fill: white;");

        //searchBox.getChildren().addAll(searchField, searchBtn);
        //searchBox.setAlignment(Pos.CENTER_LEFT);
        //searchBox.setPadding(new Insets(20, 0, 20, 0));
        //searchBox.setMaxWidth(900);

        // Foreground layout over background
        VBox contentBox = new VBox(20, banner);
        contentBox.setPadding(new Insets(0,5,10,5));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundView, contentBox);
        StackPane.setAlignment(contentBox, Pos.TOP_CENTER);
        StackPane.setAlignment(backgroundView, Pos.CENTER);
        stackPane.setPadding(new Insets(0,0,0,0));


        // ✅ Event Handlers
    btn0.setOnAction(e -> {
        // You can create a similar EditProfilePage class
        //VBox editProfilePage = new VBox(new Label("Edit Profile Page Coming Soon!"));
        //editProfilePage.setAlignment(Pos.CENTER);
        //root.setCenter(editProfilePage);
        ProfilePage profilePage = new ProfilePage();
       profilePage.setOrganizerDocId(LoggedInHelper.getUserDocId());  // Pass docId
       System.out.println("[DEBUG] Organizer docId -> " + LoggedInHelper.getUserDocId());

       profilePage.show(primaryStage);

    });

    btn2.setOnAction(e -> {
    OrganizerDetailsPage detailsPage = new OrganizerDetailsPage();
    Scene newScene = detailsPage.getScene(primaryStage);
    primaryStage.setScene(newScene);
});

     btn4.setOnAction(e -> {
    ViewBooking bookPage = new ViewBooking();
    bookPage.show(primaryStage);
    });

        
        //
        //ViewBooking viewp = new ViewBooking();
        //root.setCenter(eventPage.getScene(pStage).getRoot());
        //viewp.start(primaryStage);
        

    btn5.setOnAction(e -> {
        ExploreEventsUI explorePage = new ExploreEventsUI();
        explorePage.show(primaryStage); // replaces entire scene
    });

    btn6.setOnAction(e -> {
    SignUpLogin signupLoginPage = new SignUpLogin();
    Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
    try {
        signupLoginPage.start(currentStage); // Start the SignUpLogin page
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});
        // Add to root layout
        root.setLeft(sidebar);
        root.setCenter(stackPane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Event Dashboard");
        primaryStage.show();
    }

    private Button createSidebarButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #15171aff; -fx-font-size: 14;");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        return btn;
    }


}


