package com.eventhub.View;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ExploreEventsUI {

    public void show(Stage primaryStage) {
        // Page title
        // Label title = new Label("Explore Page");
        // title.setFont(new Font("Arial", 36));
        // title.setTextFill(Color.BLACK);

        Label subtitle = new Label("Organizer's Events");
        subtitle.setFont(new Font("Arial", 30));
        subtitle.setTextFill(Color.BLACK);

        Button btn1 = new Button("Back");
        btn1.setFont(new Font(20));
        btn1.setMaxWidth(200);
        btn1.setPrefSize(200, 40);
        btn1.setTextFill(Color.WHITE);
        btn1.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        btn1.setFont(Font.font("Arial", 20));
        //tech.setPrefWidth(200);

        btn1.setOnAction(e -> {
            OrganizerDashboard orPage = new OrganizerDashboard();
            orPage.showOrganizerDashboard(primaryStage);
        });

    

        VBox titleBox = new VBox(15, subtitle);
        titleBox.setPadding(new Insets(20, 40, 10, 40));
        titleBox.setAlignment(Pos.CENTER);

        // HBox hb = new HBox(15, btn1);
        // hb.setAlignment(Pos.TOP_RIGHT);
        // hb.setPadding(new Insets(10, 10, 10, 10));

        // GridPane layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 300, 20, 300));
        grid.setHgap(30);
        grid.setVgap(30);

        String[] titles = {"KhelKabaddi", "Tree plantation" , "Yoga Retreat", "Startup Pitch", "NextAi", "Coding Bootcamp"};
        String[] locations = {" hadapsar", "swami narayan mountain", "Nature Park", "City Square", "zeal college", "Lab 101"};
        String[] status = {"Published", "Published", "Draft", "Draft", "Published", "Draft"};
        String[] imageUrls = {
            "Assets/Images/kabaddi.jpg",
            "Assets/Images/treeplantaion.jpg",
            "https://images.unsplash.com/photo-1599058917212-d750089bc07e?auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1552664730-d307ca884978?auto=format&fit=crop&w=500&q=60",
            "Assets/Images/hackethon.jpg",
            "https://images.unsplash.com/photo-1517433456452-f9633a875f6f?auto=format&fit=crop&w=500&q=60"
        };

        for (int i = 0; i < 6; i++) {
            VBox card = createEventCard(imageUrls[i], titles[i], locations[i], status[i]);
            grid.add(card, i % 3, i / 3);
        }

        VBox mainLayout = new VBox(titleBox,grid,btn1);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle("-fx-background-color: #f7e8e8ff");
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout, 1540, 795);
        primaryStage.setTitle("Explore Page ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createEventCard(String imageUrl, String title, String location, String status) {
        VBox card = new VBox(10);
        card.setPrefSize(250, 280);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 15;");

        ImageView imageView = new ImageView(new Image(imageUrl, true));
        imageView.setFitHeight(120);
        imageView.setFitWidth(220);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        Label titleLabel = new Label(title);
        titleLabel.setFont(new Font(18));
        titleLabel.setTextFill(Color.web("#0f172a"));
        titleLabel.setStyle("-fx-font-weight: bold;");

        Label locationLabel = new Label("Location: " + location);
        locationLabel.setTextFill(Color.web("#404969"));

        Label statusLabel = new Label(status);
        statusLabel.setFont(new Font(15));
        statusLabel.setStyle("-fx-background-color: " +
                (status.equals("Published") ? "#651085;" : "#facc15;") +
                " -fx-text-fill: #faf7f7ff; -fx-padding: 2 8 2 8; -fx-background-radius: 10;");

       // Button manageButton = new Button("Manage");
       // manageButton.setStyle("-fx-background-color: #ff7f50; -fx-text-fill: white;");
       // manageButton.setPrefWidth(100);

        card.getChildren().addAll(imageView, titleLabel, locationLabel, statusLabel);
        card.setAlignment(Pos.TOP_CENTER);
        card.setPadding(new Insets(10, 10, 10, 10));
        return card;
    }
}



