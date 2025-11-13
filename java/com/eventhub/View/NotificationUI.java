// package com.eventhub.View;

// import javafx.application.Application;
// import javafx.geometry.*;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.text.*;
// import javafx.stage.Stage;

// public class NotificationUI{

// Stage primaryStage,notificationUIStage;

//     public void setPrimaryStage(Stage primaryStage) {
//     this.primaryStage = primaryStage;
// }

// public void setNotificationUIStage(Stage notificationUIStage) {
//     this.notificationUIStage = notificationUIStage;
// }

//     public void showNotificationPage(Stage primaryStage) {
//         VBox root = new VBox(20);
//         root.setPadding(new Insets(20));
//         root.setStyle("-fx-background-color: #f7e8e8ff;"); // light pink background

//          Button backButton = new Button("<<");
//       backButton.setFont(Font.font("Arial", 20));
//       backButton.setStyle("-fx-background-color: white;-fx-background-radius: 160; -fx-text-fill: #9f52a9ff;");
//         backButton.setOnAction(e -> {
//             Homepage homeobj = new Homepage();
//            Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
//     try {
//         homeobj.showHomePage(currentStage);

//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });
//         Label title = new Label("🔔 Notifications");
//         title.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
//         title.setTextFill(Color.web("#5e0b8f")); // deep purple

//         VBox notificationList = new VBox(15);
//         notificationList.setPadding(new Insets(10));

//         // Sample Notifications
//         notificationList.getChildren().addAll(
//             createNotification("📅 Upcoming Event", "Tech Conference 2025 - Aug 10, 10:00 AM", false),
//             createNotification("📅 Upcoming Event", "Startup Pitch Day - July 22, 3:00 PM", false),
//             createNotification("⏰ Reminder", "Networking session today at 6:00 PM", true),
//             createNotification("⏰ Reminder", "Event registration closes tomorrow!", true)
//         );

//         ScrollPane scrollPane = new ScrollPane(notificationList);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setStyle("-fx-background-color: transparent;");

//         root.getChildren().addAll(backButton,title, scrollPane);

//         Scene scene = new Scene(root, 1540, 795);
//         //notificationUIstage.setTitle("EventHub - Notifications");
//         primaryStage.setScene(scene);
//        primaryStage.show();
//     }

//     private HBox createNotification(String heading, String content, boolean isUrgent) {
//         VBox messageBox = new VBox(5);
//         messageBox.setPadding(new Insets(10));
//         messageBox.setStyle(
//             "-fx-background-color: #ffffff; " + // white background
//             "-fx-border-color: #b266ff; " +     // purple border
//             "-fx-border-radius: 10; " +
//             "-fx-background-radius: 10;"
//         );

//         Label title = new Label(heading);
//         title.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
//         title.setTextFill(isUrgent ? Color.web("#cc0066") : Color.web("#800080")); // pink vs purple

//         Label msg = new Label(content);
//         msg.setFont(Font.font("Poppins", 13));
//         msg.setWrapText(true);

//         Button clear = new Button("Mark as Read");
//         clear.setStyle(
//             "-fx-background-color: #ffccf2; " +     // light pink button
//             "-fx-font-size: 12; " +
//             "-fx-padding: 4 10;" +
//             "-fx-text-fill: #5e0b8f;"
//         );
//         clear.setOnAction(e -> {
//             title.setTextFill(Color.GRAY);
//             msg.setTextFill(Color.GRAY);
//             messageBox.setStyle(
//                 "-fx-background-color: #f7f7f7; " +
//                 "-fx-border-color: #cccccc; " +
//                 "-fx-border-radius: 10; " +
//                 "-fx-background-radius: 10;"
//             );
//             clear.setDisable(true);
//             clear.setText("✓ Read");
//         });

//         HBox container = new HBox(10, messageBox, clear);
//         container.setAlignment(Pos.CENTER_LEFT);
//         HBox.setHgrow(messageBox, Priority.ALWAYS);
//         messageBox.getChildren().addAll(title, msg);
//         return container;
//     }

  
// }
package com.eventhub.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

public class NotificationUI {

    Stage primaryStage, notificationUIStage;
    VBox notificationList = new VBox(15);
    int lastEventCount = 0;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setNotificationUIStage(Stage notificationUIStage) {
        this.notificationUIStage = notificationUIStage;
    }

    public void showNotificationPage(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f7e8e8ff;");

        Button backButton = new Button("<<");
        backButton.setFont(Font.font("Arial", 20));
        backButton.setStyle("-fx-background-color: white;-fx-background-radius: 160; -fx-text-fill: #9f52a9ff;");
        backButton.setOnAction(e -> {
            Homepage homeobj = new Homepage();
            Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
            try {
                homeobj.showHomePage(currentStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Label title = new Label("🔔 Notifications");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
        title.setTextFill(Color.web("#5e0b8f"));

        notificationList.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(notificationList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        root.getChildren().addAll(backButton, title, scrollPane);

        Scene scene = new Scene(root, 1540, 795);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start polling Firestore every 30 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30), e -> checkForNewEvents()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Initial fetch
        checkForNewEvents();
    }

    private HBox createNotification(String heading, String content, boolean isUrgent) {
        VBox messageBox = new VBox(5);
        messageBox.setPadding(new Insets(10));
        messageBox.setStyle(
                "-fx-background-color: #ffffff; " +
                        "-fx-border-color: #b266ff; " +
                        "-fx-border-radius: 10; " +
                        "-fx-background-radius: 10;"
        );

        Label title = new Label(heading);
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        title.setTextFill(isUrgent ? Color.web("#cc0066") : Color.web("#800080"));

        Label msg = new Label(content);
        msg.setFont(Font.font("Poppins", 13));
        msg.setWrapText(true);

        Button clear = new Button("Mark as Read");
        clear.setStyle(
                "-fx-background-color: #ffccf2; " +
                        "-fx-font-size: 12; " +
                        "-fx-padding: 4 10;" +
                        "-fx-text-fill: #5e0b8f;"
        );
        clear.setOnAction(e -> {
            title.setTextFill(Color.GRAY);
            msg.setTextFill(Color.GRAY);
            messageBox.setStyle(
                    "-fx-background-color: #f7f7f7; " +
                            "-fx-border-color: #cccccc; " +
                            "-fx-border-radius: 10; " +
                            "-fx-background-radius: 10;"
            );
            clear.setDisable(true);
            clear.setText("✓ Read");
        });

        HBox container = new HBox(10, messageBox, clear);
        container.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(messageBox, Priority.ALWAYS);
        messageBox.getChildren().addAll(title, msg);
        return container;
    }

    private void checkForNewEvents() {
        try {
            String endpoint = "https://firestore.googleapis.com/v1/projects/event-hub-7033e" + //
                                "/databases/(default)/documents/events";
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            conn.disconnect();

            JSONObject json = new JSONObject(response.toString());
            JSONArray documents = json.getJSONArray("documents");
            int currentEventCount = documents.length();

            if (currentEventCount > lastEventCount) {
                for (int i = lastEventCount; i < currentEventCount; i++) {
                    JSONObject event = documents.getJSONObject(i);
                    JSONObject fields = event.getJSONObject("fields");

                    String eventTitle = fields.getJSONObject("eventtitle").getString("stringValue");
                    String eventDate = fields.getJSONObject("eventdate").getString("stringValue");
                    String startTime = fields.getJSONObject("startTime").getString("stringValue");

                    String content = eventTitle + " - " + eventDate + ", " + startTime;

                    // Add to top
                    notificationList.getChildren().add(0, createNotification("🆕 New Event Added", content, false));
                }
            }

            lastEventCount = currentEventCount;

        } catch (Exception e) {
            e.printStackTrace();
 }
}
}
