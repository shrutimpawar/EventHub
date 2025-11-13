
package com.eventhub.View;


import java.util.List;

import com.google.firestore.v1.Cursor;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Homepage {

    private HBox musicImageRow;
    private HBox danceImageRow;
    private HBox SocialImageRow;
    private HBox collegeImageRow;
    private HBox sportsImageRow;
    private HBox fareImageRow;
    private HBox gatheringImageRow;
    private HBox exhibiImageRow;
    private HBox techImageRow;
    Stage primaryStage,notificationUIStage,HomepageStage;
    Scene EventDetailPageScene,HomePageScene;
    


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setEventDetailPageScene(Scene eventDetailPageScene) {
        EventDetailPageScene = eventDetailPageScene;
    }

    private HBox createAppBar(ImageView appLogo) {

        // --- Search text field (events) ---
        TextField searchEvents = new TextField();
        searchEvents.setPromptText("Search events");
        searchEvents.setPrefWidth(700);

        Button searchButton = new Button("Search");
        searchButton.setStyle(
                "-fx-background-color: skyblue; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        searchButton.setFont(new Font(12));
        searchButton.setMaxWidth(100);
        searchButton.setMaxHeight(17);
        //searchButton.setCursor(Cursor.HAND);

        searchButton.setOnAction(e -> {
            String query = searchEvents.getText().trim().toLowerCase();

            if (query.contains("music")) {
                showImagesPopup("Music", musicImageRow);
            } else if (query.contains("dance")) {
                showImagesPopup("Dance", danceImageRow);
            } else if (query.contains("social")) {
                showImagesPopup("Social", SocialImageRow);
            } else if (query.contains("college")) {
                showImagesPopup("College Festival", collegeImageRow);
            } else if (query.contains("sports")) {
                showImagesPopup("Sports", sportsImageRow);
            } else if (query.contains("fare")) {
                showImagesPopup("Fare", fareImageRow);
            } else if (query.contains("festive") || query.contains("gathering")) {
                showImagesPopup("Festive Gathering", gatheringImageRow);
            } else if (query.contains("exhibition")) {
                showImagesPopup("Exhibitions", exhibiImageRow);
            } else if (query.contains("tech")) {
                showImagesPopup("Tech Conference", techImageRow);
            } else {
                // If no event found
                Stage popup = new Stage();
                Text msg = new Text("No such upcoming event!");
                msg.setStyle("-fx-font-size: 18;-fx-text-fill:black;-fx-font-weight:bold;");
                VBox vb = new VBox(msg);
                vb.setAlignment(Pos.CENTER);
                vb.setPadding(new Insets(20));
                popup.setScene(new Scene(vb, 400, 200));
                popup.setTitle("Event Not Found");
                popup.show();
            }
        });

        searchEvents.setOnAction(e -> searchButton.fire());


        HBox searchBar = new HBox(10, new Text("🔍"), searchEvents);
        searchBar.setAlignment(Pos.CENTER_LEFT);
        searchBar.setPadding(new Insets(6, 20, 6, 12));
        searchBar.setStyle("""
                -fx-background-color: #F9F9FB;
                -fx-border-color: #E0E0E7;
                -fx-border-radius: 999;
                -fx-background-radius: 999;
                """);

        // ---- right‑hand menu buttons (flat links) ----
        Button home = flatLinkBtn("Dashboard");
       home.setOnAction(e -> {
    UserDashboard userDashboardobj = new UserDashboard();
    userDashboardobj.setPrimaryStage((Stage) ((Button) e.getSource()).getScene().getWindow());
    userDashboardobj.showUserDashboardPage();
});

        Button explore = flatLinkBtn("Explore");
           explore.setOnAction(e -> {
    EventDetailPage eventPage = new EventDetailPage();
    Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
 
    eventPage.showEventDetailPage(currentStage);
});
        Button notification = flatLinkBtn("Notification");
        //Button notification = flatLinkBtn("Notification");
       notification.setOnAction(e -> {
       NotificationUI notiobj = new NotificationUI();
       Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
       notiobj.showNotificationPage(currentStage);  // This guarantees correct stage
});
         Button AboutUs = flatLinkBtn("About Us");
         AboutUs.setOnAction(e -> {
         About aboutpageobj = new About();
         Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
         aboutpageobj.showAboutPage(currentStage);  // This guarantees correct stage

});
           Button chatBot = flatLinkBtn("Chatbot");
            chatBot.setOnAction(e -> {
            ChatbotUI chat = new ChatbotUI();
            Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
            chat.showChatbot(currentStage); // Pass current stage as owner
});

        Button LogOut = flatLinkBtn("Logout");
        

LogOut.setOnAction(e -> {
    SignUpLogin signupLoginPage = new SignUpLogin();
    Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
    try {
        signupLoginPage.start(currentStage); // Start the SignUpLogin page
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});

        

        HBox menuBox = new HBox(18,searchButton, home, explore, notification,AboutUs,chatBot, LogOut);
        menuBox.setAlignment(Pos.CENTER_RIGHT);

        // allow menu to push to far right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // final AppBar row
        HBox appBar = new HBox(25, appLogo, searchBar, spacer, menuBox);
        appBar.setAlignment(Pos.CENTER_LEFT);
        appBar.setPadding(new Insets(8, 24, 8, 24));
        appBar.setStyle("-fx-background-color: white; -fx-border-color: #DDDDDD; -fx-border-width: 0 0 1 0;");
        return appBar;
    }

    /** Flat link‑style button used in the AppBar menu. */
    private Button flatLinkBtn(String text) {
        Button b = new Button(text);
        b.setFocusTraversable(false);
        b.setStyle("""
                -fx-background-color: transparent;
                -fx-text-fill: #2E2F41;
                -fx-padding: 4 0 4 0;
                -fx-font-size: 15px;
                """);
        b.setOnMouseEntered(ev -> b.setStyle(b.getStyle() + "-fx-underline: true;"));
        b.setOnMouseExited(ev -> b.setStyle(b.getStyle().replace("-fx-underline: true;", "")));
        return b;
    }

  
    public void showHomePage(Stage primaryStage) {

        Image logo = new Image("Assets\\Images\\Applogo.jpg");
        ImageView appLogo = new ImageView(logo);
        appLogo.setFitWidth(40);
        appLogo.setFitHeight(40);
        HBox appBar = createAppBar(appLogo);

        Button music = new Button("Music");
        music.setMaxWidth(100);
        music.setMaxHeight(10);
        music.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        music.setFont(Font.font("Arial", 20));
        //music.setPrefWidth(200);

        Button dance = new Button("Dance");
        dance.setMaxWidth(200);
        dance.setMaxHeight(10);
        dance.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        dance.setFont(Font.font("Arial", 20));
       // dance.setPrefWidth(200);
        
        Button social = new Button("Social");
        social.setMaxWidth(100);
        social.setMaxHeight(10);
        social.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        social.setFont(Font.font("Arial", 20));
       // social.setPrefWidth(200);

        Button college = new Button("College Festival");
        college.setMaxWidth(200);
        college.setMaxHeight(10);
        college.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        college.setFont(Font.font("Arial", 20));
       //college.setPrefWidth(200);

        Button sports = new Button("Sports");
        sports.setMaxWidth(100);
        sports.setMaxHeight(10);
        sports.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        sports.setFont(Font.font("Arial", 20));
       // sports.setPrefWidth(200);

        Button fare = new Button("Fare");
        fare.setMaxWidth(100);
        fare.setMaxHeight(10);
        fare.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        fare.setFont(Font.font("Arial", 20));
        //fare.setPrefWidth(200);

        Button gathering = new Button("Festive Gathering");
        gathering.setMaxWidth(200);
        gathering.setMaxHeight(10);
        gathering.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        gathering.setFont(Font.font("Arial", 20));
        //gathering.setPrefWidth(200);

        Button exhibition = new Button("Exhibitions");
        exhibition.setMaxWidth(150);
        exhibition.setMaxHeight(10);
        exhibition.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        exhibition.setFont(Font.font("Arial", 20));
        //exhibition.setPrefWidth(200);

        Button tech = new Button("Tech Conference");
        tech.setMaxWidth(200);
        tech.setMaxHeight(10);
        tech.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        tech.setFont(Font.font("Arial", 20));
        //tech.setPrefWidth(200);

        HBox all_buttons = new HBox(20, music, dance, social, college, sports, fare, gathering, exhibition, tech);

        VBox buttons = new VBox(20, all_buttons);

        Text recommended = new Text("Recommended Events..");
        recommended.setFont(Font.font("Arial", 30));
        recommended.setStyle("-fx-font-weight: bold");
        VBox recommend = new VBox(20, recommended);
        List<String> imagePaths = List.of("Assets\\Images\\music_recomm.jpg",
                "Assets\\Images\\sports.recomm.jpg",
                "Assets\\Images\\dancemissing.jpg",
                "Assets\\Images\\exhibi_recomm.jpg", "Assets\\Images\\tech_recomm.jpg",
                "Assets\\Images\\college.jpg", "Assets\\Images\\exhibi1_recomm.jpg",
                "Assets\\Images\\sports1_recomm.jpg", "Assets\\Images\\music1_recomm.jpg",
                "Assets\\Images\\tech1_recomm.jpg", "Assets\\Images\\college1_recomm.jpg",
                "Assets\\Images\\fare_recomm.jpg");
        // Add images in HBox
        HBox imageRow = new HBox(20);
        imageRow.setAlignment(Pos.CENTER_LEFT);
        VBox images = new VBox();
        for (String path : imagePaths) {
            ImageView poster = createPoster(path);
            imageRow.getChildren().add(poster);
        }
        // ScrollPane to make it scrollable horizontally
        ScrollPane scroller = new ScrollPane(imageRow);
        scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.setFitToHeight(true);
        scroller.setPannable(true);
        scroller.setStyle("-fx-background-color: transparent;");
        Timeline autoScroll = new Timeline(
                new KeyFrame(Duration.millis(100), e -> {

                    double hValue = scroller.getHvalue();

                    scroller.setHvalue(hValue + 0.002); // Adjust scroll speed here
                    if (scroller.getHvalue() >= 1.0) {
                        scroller.setHvalue(0); // Loop back
                    }
                }));
        autoScroll.setCycleCount(Animation.INDEFINITE);
        autoScroll.play();
        images.getChildren().add(scroller);
      //  images.getChildren().add(scroller);

        Text musicText = new Text("Music");
        musicText.setFont(Font.font("Arial", 30));
        musicText.setStyle("-fx-font-weight: bold");
        VBox musicVBox = new VBox(20, musicText);
        List<String> musicImagePaths = List.of("Assets\\Images\\musicpop1.jpg", "Assets\\Images\\musicpop2.jpg",
                "Assets\\Images\\musicpop3.jpg", "Assets\\Images\\musicpop4.jpg", "Assets\\Images\\musicpop5.jpg");
        // Add images in HBox
        musicImageRow = new HBox(20);
        musicImageRow.setAlignment(Pos.CENTER_LEFT);
        VBox musicImages = new VBox();
        for (String musicpath : musicImagePaths) {
            ImageView musicposter = createPosterMusic(musicpath);
            musicImageRow.getChildren().add(musicposter);
        }
        music.setOnAction(e -> showImagesPopup("Music", musicImageRow));
        // ScrollPane to make it scrollable horizontally
        ScrollPane musicscroller = new ScrollPane(musicImageRow);
        musicscroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        musicscroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        musicscroller.setFitToHeight(true);
        musicscroller.setPannable(true);
        musicscroller.setStyle("-fx-background-color: transparent;");
        musicImages.getChildren().add(musicscroller);

        Text danceText = new Text("Dance");
        danceText.setFont(Font.font("Arial", 30));
        danceText.setStyle("-fx-font-weight: bold");
        VBox danceVBox = new VBox(20, danceText);
        List<String> danceImagePaths = List.of("Assets\\Images\\dancepop1.jpg", "Assets\\Images\\dancepop2.jpg",
                "Assets\\Images\\dancepop3.jpg", "Assets\\Images\\dancepop4.jpg", "Assets\\Images\\dancepop5.jpg");
        // Add images in HBox
        danceImageRow = new HBox(20);
        danceImageRow.setAlignment(Pos.CENTER_LEFT);
        VBox danceImages = new VBox();
        for (String dancepath : danceImagePaths) {
            ImageView danceposter = createPosterDance(dancepath);
            danceImageRow.getChildren().add(danceposter);
        }
        dance.setOnAction(e -> showImagesPopup("Dance", danceImageRow));
        // ScrollPane to make it scrollable horizontally
        ScrollPane dancescroller = new ScrollPane(danceImageRow);
        dancescroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dancescroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dancescroller.setFitToHeight(true);
        dancescroller.setPannable(true);
        dancescroller.setStyle("-fx-background-color: transparent;");
        danceImages.getChildren().add(dancescroller);

        Text socialText = new Text("Social");
        socialText.setFont(Font.font("Arial", 30));
        socialText.setStyle("-fx-font-weight: bold");
        VBox socialVBox = new VBox(20, socialText);
        List<String> socialImagePaths = List.of("Assets\\Images\\socialpop1.jpg", "Assets\\Images\\socialpop2.jpg",
                "Assets\\Images\\socialpop3.jpg", "Assets\\Images\\socialpop4.jpg", "Assets\\Images\\socialpop5.jpg");
        SocialImageRow = new HBox(20);
        SocialImageRow.setAlignment(Pos.CENTER_LEFT);
        VBox socialImages = new VBox();
        for (String socialpath : socialImagePaths) {
            ImageView socialposter = createPosterSocial(socialpath);
            SocialImageRow.getChildren().add(socialposter);
        }
        social.setOnAction(e -> showImagesPopup("Social", SocialImageRow));
        // ScrollPane to make it scrollable horizontally
        ScrollPane socialscroller = new ScrollPane(SocialImageRow);
        socialscroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        socialscroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        socialscroller.setFitToHeight(true);
        socialscroller.setPannable(true);
        socialscroller.setStyle("-fx-background-color: transparent;");
        socialImages.getChildren().add(socialscroller);

        Text collegeText = new Text("College Festival");
        collegeText.setFont(Font.font("Arial", 30));
        collegeText.setStyle("-fx-font-weight: bold");
        VBox collegeVBox = new VBox(20, collegeText);
        List<String> collegeImagePaths = List.of("Assets\\Images\\collegepop1.jpg", "Assets\\Images\\collegepop2.jpg",
                "Assets\\Images\\collegepop3.jpg", "Assets\\Images\\collegepop4.jpg",
                "Assets\\Images\\collegepop5.jpg");
        collegeImageRow = new HBox(20);
        collegeImageRow.setAlignment(Pos.CENTER_LEFT);
        VBox collegeImages = new VBox();
        for (String collegepath : collegeImagePaths) {
            ImageView collegeposter = createPosterCollege(collegepath);
            collegeImageRow.getChildren().add(collegeposter);
        }
        college.setOnAction(e -> showImagesPopup("College Festival", collegeImageRow));
        // ScrollPane to make it scrollable horizontally
        ScrollPane collegescroller = new ScrollPane(collegeImageRow);
        collegescroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        collegescroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        collegescroller.setFitToHeight(true);
        collegescroller.setPannable(true);
        collegescroller.setStyle("-fx-background-color: transparent;");
        collegeImages.getChildren().add(collegescroller);

        Text sportsText = new Text("Sports");
        sportsText.setFont(Font.font("Arial", 30));
        sportsText.setStyle("-fx-font-weight: bold");
        VBox sportsVBox = new VBox(20, sportsText);
        List<String> sportsImagePaths = List.of("Assets\\Images\\sportspop1.jpg", "Assets\\Images\\sportspop2.jpg",
                "Assets\\Images\\sportspop3.jpg", "Assets\\Images\\sportspop4.jpg", "Assets\\Images\\sportspop5.jpg");
        sportsImageRow = new HBox(20);
        sportsImageRow.setAlignment(Pos.CENTER_LEFT);
        VBox sportsImages = new VBox();
        for (String sportspath : sportsImagePaths) {
            ImageView sportsposter = createPosterSports(sportspath);
            sportsImageRow.getChildren().add(sportsposter);
        }
        sports.setOnAction(e -> showImagesPopup("Sports", sportsImageRow));
        // ScrollPane to make it scrollable horizontally
        ScrollPane sportsscroller = new ScrollPane(sportsImageRow);
        sportsscroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sportsscroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sportsscroller.setFitToHeight(true);
        sportsscroller.setPannable(true);
        sportsscroller.setStyle("-fx-background-color: transparent;");
        sportsImages.getChildren().add(sportsscroller);

        Text fareText = new Text("Fare");
        fareText.setFont(Font.font("Arial", 30));
        fareText.setStyle("-fx-font-weight: bold");
        VBox fareVBox = new VBox(20, fareText);
        List<String> fareImagePaths = List.of("Assets\\Images\\farepop1.jpg", "Assets\\Images\\farepop2.jpg",
                "Assets\\Images\\farepop3.jpg", "Assets\\Images\\farepop4.jpg", "Assets\\Images\\farepop5.jpg");
        fareImageRow = new HBox(20);
        fareImageRow.setAlignment(Pos.CENTER_LEFT);
        VBox fareImages = new VBox();
        for (String farepath : fareImagePaths) {
            ImageView fareposter = createPosterFare(farepath);
            fareImageRow.getChildren().add(fareposter);
        }
        fare.setOnAction(e -> showImagesPopup("Fare", fareImageRow));
        // ScrollPane to make it scrollable horizontally
        ScrollPane farescroller = new ScrollPane(fareImageRow);
        farescroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        farescroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        farescroller.setFitToHeight(true);
        farescroller.setPannable(true);
        farescroller.setStyle("-fx-background-color: transparent;");
        fareImages.getChildren().add(farescroller);

        Text gatheringText = new Text("Festive Gathering");
        gatheringText.setFont(Font.font("Arial", 30));
        gatheringText.setStyle("-fx-font-weight: bold");
        VBox gatheringVBox = new VBox(20, gatheringText);
        List<String> gatheringImagePaths = List.of("Assets\\Images\\gathpop1.jpg", "Assets\\Images\\gathpop2.jpg",
                "Assets\\Images\\gathpop3.jpg", "Assets\\Images\\gathpop4.jpg", "Assets\\Images\\gathpop5.jpg");
        gatheringImageRow = new HBox(20);
        gatheringImageRow.setAlignment(Pos.CENTER_LEFT);
        VBox gatheringImages = new VBox();
        for (String gatheringpath : gatheringImagePaths) {
            ImageView gatheringposter = createPosterGathering(gatheringpath);
            gatheringImageRow.getChildren().add(gatheringposter);
        }
        gathering.setOnAction(e -> showImagesPopup("Festive Gathering", gatheringImageRow));
        // ScrollPane to make it scrollable horizontally
        ScrollPane gatheringcroller = new ScrollPane(gatheringImageRow);
        gatheringcroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gatheringcroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gatheringcroller.setFitToHeight(true);
        gatheringcroller.setPannable(true);
        gatheringcroller.setStyle("-fx-background-color: transparent;");
        gatheringImages.getChildren().add(gatheringcroller);

        Text exhibiText = new Text("Exhibitions");
        exhibiText.setFont(Font.font("Arial", 30));
        exhibiText.setStyle("-fx-font-weight: bold");
        VBox exhibiVBox = new VBox(20, exhibiText);
        List<String> exhibiImagePaths = List.of("Assets\\Images\\exhipop1.jpg", "Assets\\Images\\exhipop2.jpg",
                "Assets\\Images\\exhipop3.jpg", "Assets\\Images\\exhipop4.jpg", "Assets\\Images\\exhipop5.jpg");
        exhibiImageRow = new HBox(20);
        exhibiImageRow.setAlignment(Pos.CENTER_LEFT);
        VBox exhibiImages = new VBox();
        for (String exhibipath : exhibiImagePaths) {
            ImageView exhibiposter = createPosterExhibitions(exhibipath);
            exhibiImageRow.getChildren().add(exhibiposter);
        }
        exhibition.setOnAction(e -> showImagesPopup("Exhibitions", exhibiImageRow));
        // ScrollPane to make it scrollable horizontally
        ScrollPane exhibiscroller = new ScrollPane(exhibiImageRow);
        exhibiscroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        exhibiscroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        exhibiscroller.setFitToHeight(true);
        exhibiscroller.setPannable(true);
        exhibiscroller.setStyle("-fx-background-color: transparent;");
        exhibiImages.getChildren().add(exhibiscroller);

        Text techText = new Text("Tech Conference");
        techText.setFont(Font.font("Arial", 30));
        techText.setStyle("-fx-font-weight: bold");
        VBox techVBox = new VBox(20, techText);
        List<String> techImagePaths = List.of("Assets\\Images\\techpop1.jpg", "Assets\\Images\\techpop2.jpg",
                "Assets\\Images\\techpop3.jpg", "Assets\\Images\\techpop4.jpg", "Assets\\Images\\techpop5.jpg");
        techImageRow = new HBox(20);
        techImageRow.setAlignment(Pos.CENTER_LEFT);
        VBox techImages = new VBox();
        for (String techpath : techImagePaths) {
            ImageView techposter = createPosterTech(techpath);
            techImageRow.getChildren().add(techposter);
        }
        tech.setOnAction(e -> showImagesPopup("Tech Conference", techImageRow));
        // ScrollPane to make it scrollable horizontally
        ScrollPane techscroller = new ScrollPane(techImageRow);
        techscroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        techscroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        techscroller.setFitToHeight(true);
        techscroller.setPannable(true);
        techscroller.setStyle("-fx-background-color: transparent;");
        techImages.getChildren().add(techscroller);

        Font calligraphyFont = Font.loadFont(
                getClass().getResourceAsStream("/Fonts/style.ttf"), 48);

        Label descHeading = new Label("Event Hub");
        //descHeading.setFont(Font.font("Brush Script MT", FontWeight.NORMAL, 48));
        descHeading.setFont(calligraphyFont);
        descHeading.setFont(Font.font("Arial", 40));
        descHeading.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
        // HBox heading = new HBox(20, descHeading);
        descHeading.setAlignment(Pos.CENTER_LEFT);
        if (calligraphyFont != null) {
            descHeading.setFont(calligraphyFont);
        } else {
            System.out.println("⚠ Font not loaded, check file path!");
            descHeading.setFont(Font.font("Arial", 40)); // fallback font
        }

        if (calligraphyFont != null) {
            descHeading.setFont(calligraphyFont);
        } else {
            System.out.println("⚠ Font not loaded, check file path!");
            descHeading.setFont(Font.font("Arial", 40)); // fallback font
        }

        Circle circle = new Circle(120);
        circle.setFill(Color.WHITE); //
        StackPane circleHeading = new StackPane(circle, descHeading);
        circleHeading.setAlignment(Pos.CENTER);

        Label desc = new Label("🎉 Stop Scrolling, Start Doing! 🚀\n" + //
                "Welcome to EventHub – where the most exciting events 🎭🎤🎨 happening around you are just a tap away! 📲\n"
                + //
                "Whether you're in the mood for fun 😄, learning 📚, or connecting 🤝,\n" + //
                "your perfect local experience is waiting for you! 🌟✨\r\n" + //
                "");
        desc.setWrapText(true);
        desc.setFont(Font.font("Arial", 22));
        desc.setStyle("-fx-text-fill: black;");
        desc.setAlignment(Pos.CENTER_LEFT);

        HBox innerBox = new HBox(100, circleHeading, desc);
        innerBox.setAlignment(Pos.CENTER_LEFT);

        // HBox as container with gradient background
        HBox description = new HBox(60, innerBox);
        description.setAlignment(Pos.CENTER);
        description.setPadding(new Insets(20));
        description.setStyle(
                "-fx-background-color: linear-gradient(to right, #949e9eff, #ed6cf9ff);" +
                        "-fx-background-radius: 10; -fx-border-color: #ddd; -fx-border-radius: 10;");
        description.setMaxWidth(1500);
        description.setPrefHeight(300);

        // Add this to your main VBox
        VBox descBox = new VBox(60, description);
        descBox.setAlignment(Pos.CENTER);


        VBox vbmain = new VBox(20, appBar, buttons, recommend, images,descBox, musicVBox, musicImages, danceVBox, danceImages,
                socialVBox, socialImages, collegeVBox, collegeImages, sportsVBox, sportsImages, fareVBox, fareImages,
                gatheringVBox, gatheringImages, exhibiVBox, exhibiImages, techVBox, techImages);
        vbmain.setStyle("-fx-background-color: #f7e8e8ff");
        ScrollPane rootScroll = new ScrollPane(vbmain);
        rootScroll.setFitToWidth(true);
        rootScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rootScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        vbmain.setPadding(new Insets(30));
        Scene scene = new Scene(rootScroll, 1540, 795);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Home Page");
        primaryStage.show();
    }

    private ImageView createPoster(String path) {
        Image img;
        try {
            img = new Image(path, true);
        } catch (Exception e) {
            img = new Image("https://via.placeholder.com/200x300/cccccc/000000?text=No+Image");
        }
        ImageView view = new ImageView(img);
        view.setFitWidth(200);
        view.setFitHeight(300);
        view.setPreserveRatio(false);
        return view;
    }

    private ImageView createPosterMusic(String musicpath) {
        Image musicimg = new Image(musicpath, true);
        ImageView musicview = new ImageView(musicimg);
        musicview.setFitWidth(300);
        musicview.setFitHeight(300);
        musicview.setPreserveRatio(false);
        return musicview;
    }

    private ImageView createPosterDance(String dancepath) {
        Image danceimg = new Image(dancepath, true);
        ImageView danceview = new ImageView(danceimg);
        danceview.setFitWidth(300);
        danceview.setFitHeight(300);
        danceview.setPreserveRatio(false);
        return danceview;
    }

    private ImageView createPosterSocial(String socialpath) {
        Image socialimg = new Image(socialpath, true);
        ImageView socialview = new ImageView(socialimg);
        socialview.setFitWidth(300);
        socialview.setFitHeight(300);
        socialview.setPreserveRatio(false);
        return socialview;
    }

    private ImageView createPosterCollege(String collegepath) {
        Image collegeimg = new Image(collegepath, true);
        ImageView collegeview = new ImageView(collegeimg);
        collegeview.setFitWidth(300);
        collegeview.setFitHeight(300);
        collegeview.setPreserveRatio(false);
        return collegeview;
    }

    private ImageView createPosterSports(String sportspath) {
        Image sportsimg = new Image(sportspath, true);
        ImageView sportsview = new ImageView(sportsimg);
        sportsview.setFitWidth(300);
        sportsview.setFitHeight(300);
        sportsview.setPreserveRatio(false);
        return sportsview;
    }

    private ImageView createPosterFare(String farepath) {
        Image fareimg = new Image(farepath, true);
        ImageView fareview = new ImageView(fareimg);
        fareview.setFitWidth(300);
        fareview.setFitHeight(300);
        fareview.setPreserveRatio(false);
        return fareview;
    }

    private ImageView createPosterGathering(String gatheringpath) {
        Image gatheringimg = new Image(gatheringpath, true);
        ImageView gatheringview = new ImageView(gatheringimg);
        gatheringview.setFitWidth(300);
        gatheringview.setFitHeight(300);
        gatheringview.setPreserveRatio(false);
        return gatheringview;
    }

    private ImageView createPosterExhibitions(String exhibipath) {
        Image exhibiimg = new Image(exhibipath, true);
        ImageView exhibiview = new ImageView(exhibiimg);
        exhibiview.setFitWidth(300);
        exhibiview.setFitHeight(300);
        exhibiview.setPreserveRatio(false);
        return exhibiview;
    }

    private ImageView createPosterTech(String techpath) {
        Image techimg = new Image(techpath, true);
        ImageView techview = new ImageView(techimg);
        techview.setFitWidth(300);
        techview.setFitHeight(300);
        techview.setPreserveRatio(false);
        return techview;
    }

    private void showImagesPopup(String category, HBox musicImageRow) {
        // Clone the HBox content (to avoid removing from main UI)
        HBox popupRow = new HBox(20);
        popupRow.setAlignment(Pos.CENTER);
        for (javafx.scene.Node node : musicImageRow.getChildren()) {
            if (node instanceof ImageView) {
                ImageView original = (ImageView) node;
                ImageView copy = new ImageView(original.getImage());
                copy.setFitWidth(original.getFitWidth());
                copy.setFitHeight(original.getFitHeight());
                popupRow.getChildren().add(copy);
            }
        }

        ScrollPane scrollPane = new ScrollPane(popupRow);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToHeight(true);

        Text title = new Text(category + " Events");
        title.setFont(Font.font("Arial", 24));
        title.setStyle("-fx-font-weight: bold");

        VBox popupContent = new VBox(20, title, scrollPane);
        popupContent.setAlignment(Pos.CENTER);
        popupContent.setPadding(new Insets(20));

        Stage popupStage = new Stage();
        popupStage.setTitle(category + " Events");
        popupStage.setScene(new Scene(popupContent, 800, 400));
       popupStage.show();
    }
    public void initializeEventDetailPage() {
        EventDetailPage eventDetailPage = new EventDetailPage();
       // VBox eventDetailRoot = eventDetailPage.createEventDetailPageScene();
        
        eventDetailPage.setEventDetailPageStage(primaryStage);
        EventDetailPageScene = new Scene(eventDetailPage.createEventDetailPageScene(), 1540, 795);
        eventDetailPage.setEventDetailPageScene(EventDetailPageScene);
    }
   
}

