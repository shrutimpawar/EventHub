package com.eventhub.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EventDetailsPage {

    static final String PROJECT_ID = "event-hub-7033e";
    static final String API_KEY = "AIzaSyBRUq__-ukWR0ZgoZBfombRkHpd9G_MMWA";

    public Scene getScene(Stage stage) {
        Label lb = new Label("Organizer");
        lb.setFont(new Font(30));
        lb.setStyle("-fx-text-fill: #1c1c1dff;-fx-font-weight: bold");

        Label lb1 = new Label("Add Event Details");
        lb1.setStyle("-fx-text-fill: #4f4f50ff;-fx-font-weight: bold");
        lb1.setFont(new Font(40));

        // Button btn = new Button("Save");
        // btn.setFont(new Font(20));
        // btn.setPrefSize(150, 40);
        // btn.setTextFill(Color.WHITE);
        // //btn.setStyle("-fx-background-color: #c866e17b");
        // btn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
        //         + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
        //         "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");

        // btn.setOnAction(e -> {
        //    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //    alert.setTitle("Event Saved");
        //    alert.setHeaderText(null);
        //    alert.setContentText("Your event details have been saved successfully.");
        //    alert.showAndWait();
        // });

        Button btn1 = new Button("Cancel");
        btn1.setFont(new Font(20));
        btn1.setPrefSize(200, 40);
        btn1.setTextFill(Color.WHITE);
        btn1.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        btn1.setOnAction(e -> {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Action Cancelled");
           alert.setHeaderText(null);
           alert.setContentText("Event creation has been cancelled.");
           alert.showAndWait();
        });


        VBox vb = new VBox(10, lb, lb1);
        vb.setAlignment(Pos.TOP_LEFT);
        vb.setPadding(new Insets(10));

        // HBox hb = new HBox(15, btn, btn1);
        // hb.setAlignment(Pos.TOP_RIGHT);
        // hb.setPadding(new Insets(10));

        // Form Fields
        TextField eventtitle = new TextField();
        eventtitle.setPromptText("Enter Event Name");

        TextField desc = new TextField();
        desc.setPromptText("Enter Description");

        TextField eventdate = new TextField();
        eventdate.setPromptText("Enter Date");

        TextField startTime = new TextField();
        startTime.setPromptText("Start Time");

        TextField endTime = new TextField();
        endTime.setPromptText("End Time");

        HBox dateTimeBox = new HBox(10, eventdate, startTime, endTime);

        TextField venue = new TextField();
        venue.setPromptText("Venue");

        // TextField addressField = new TextField();
        // addressField.setPromptText("Address");

        HBox locationBox = new HBox(10, venue);

        TextField eventTicket = new TextField();
        eventTicket.setPromptText("Ticket Type");

        TextField eventprice = new TextField();
        eventprice.setPromptText("Price");

        HBox ticketingBox = new HBox(10, eventTicket, eventprice);

        TextField orgEmail = new TextField();
        orgEmail.setPromptText("Email Address");

        TextField orgContact = new TextField();
        orgContact.setPromptText("Phone Number");

        HBox contactBox = new HBox(10, orgEmail, orgContact);

        Button uploadBtn = new Button("Choose Photo");
        ImageView photoPreview = new ImageView();
        photoPreview.setFitHeight(100);
        photoPreview.setFitWidth(150);
        photoPreview.setPreserveRatio(true);

        final String[] selectedImageUrl = {""};


       uploadBtn.setOnAction(e -> {
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(stage);
    if (file != null) {
        Image image = new Image(file.toURI().toString());
        photoPreview.setImage(image);
        selectedImageUrl[0] = file.toURI().toString(); // store image URL
    }
});


        Button btn = new Button("Save");
        btn.setFont(new Font(20));
        btn.setPrefSize(150, 40);
        btn.setTextFill(Color.WHITE);
        //btn.setStyle("-fx-background-color: #c866e17b");
        btn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");

        btn.setOnAction(e -> {
            String eventtitle_post=eventtitle.getText();
            String desc_post=desc.getText();
            String eventdate_post=eventdate.getText();
            String startTime_post=startTime.getText();
            String endTime_post=endTime.getText();
            String eventTicket_post=eventTicket.getText();
            String orgEmail_post=orgEmail.getText();
            String orgContact_post=orgContact.getText();
            String venue_post=venue.getText();
            String eventprice_post=eventprice.getText();
          //  String imageurl_post = selectedImageUrl[0];

             String result=addEventToFirestore(eventtitle_post,desc_post,eventdate_post,startTime_post,endTime_post,eventTicket_post,orgEmail_post,orgContact_post,venue_post,eventprice_post,selectedImageUrl[0]);




           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Event Saved");
           alert.setHeaderText(null);
           alert.setContentText("Your event details have been saved successfully.");
           alert.showAndWait();
        });

        HBox hb = new HBox(15, btn, btn1);
        hb.setAlignment(Pos.TOP_RIGHT);
        hb.setPadding(new Insets(10));

        VBox photoBox = new VBox(10, uploadBtn, photoPreview);

        Label l1 = new Label("Event Name");
        l1.setStyle("-fx-text-fill: #662987ff;-fx-font-weight: bold");
        l1.setFont(new Font(20));

        Label l2 = new Label("Description");
        l2.setStyle("-fx-text-fill: #662987ff;-fx-font-weight: bold");
        l2.setFont(new Font(20));

        Label l3 = new Label("Date & Time");
        l3.setStyle("-fx-text-fill: #662987ff;-fx-font-weight: bold");
        l3.setFont(new Font(20));

        Label l4 = new Label("Location");
        l4.setStyle("-fx-text-fill: #662987ff;-fx-font-weight: bold");
        l4.setFont(new Font(20));

        Label l5 = new Label("Ticketing");
        l5.setStyle("-fx-text-fill: #662987ff;-fx-font-weight: bold");
        l5.setFont(new Font(20));

        Label l6 = new Label("Contacts");
        l6.setStyle("-fx-text-fill: #662987ff;-fx-font-weight: bold");
        l6.setFont(new Font(20));

        Label l7 = new Label("Upload Event Photos");
        l7.setStyle("-fx-text-fill: #662987ff;-fx-font-weight: bold");
        l7.setFont(new Font(20));

        Button BackBtn = new Button("Back");
        BackBtn.setPrefWidth(100);
        BackBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        BackBtn.setFont(Font.font("Arial", 20));
        BackBtn.setOnAction(e -> {
            OrganizerDetailsPage orPage = new OrganizerDetailsPage();
            Scene previousScene = orPage.getScene(stage);
            stage.setScene(previousScene);
        });

        VBox vbFormcard = new VBox(5,
            l1, eventtitle,
            l2, desc,
            l3, dateTimeBox,
            l4, locationBox,
            l5, ticketingBox,
            l6, contactBox,
            l7, photoBox,
            BackBtn
        );

        vbFormcard.setPadding(new Insets(30));
        vbFormcard.setStyle("-fx-background-color: white; -fx-background-radius: 30;");

        ScrollPane scrollPane = new ScrollPane(vbFormcard);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        StackPane stackPane = new StackPane(scrollPane);
        stackPane.setPadding(new Insets(30));
        stackPane.setAlignment(Pos.TOP_CENTER);

        VBox vbf = new VBox(20, vb, hb, stackPane);
        vbf.setStyle("-fx-background-color: #f7e8e8ff;");

       return new Scene(vbf, 1540, 795);
}
   private static String addEventToFirestore(String eventtitle_post, String desc_post, String eventdate_post, String startTime_post,
                    String endTime_post,String eventTicket_post,String orgEmail_post,String orgContact_post,String venue_post,
                    String eventprice_post,String imageUrl ) {
                if (eventtitle_post.isEmpty() || desc_post.isEmpty() || eventdate_post.isEmpty() || startTime_post.isEmpty()
                        || endTime_post.isEmpty() || eventTicket_post.isEmpty()|| orgEmail_post.isEmpty()|| orgContact_post.isEmpty()||venue_post.isEmpty()||eventprice_post.isEmpty()) {
                    return "Please enter all details!!!";
                }
                String endpoint = String.format(
                        "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/events?key=%s",
                        PROJECT_ID, API_KEY);
                String payload = String.format("{\"fields\": {"
                                                                + "\"eventtitle\": {\"stringValue\": \"%s\"},"
                                                                + "\"desc\": {\"stringValue\": \"%s\"},"
                                                                + "\"eventdate\": {\"stringValue\": \"%s\"},"
                                                                + "\"startTime\": {\"stringValue\": \"%s\"},"
                                                                + "\"endTime\": {\"stringValue\": \"%s\"}," 
                                                                +"\"eventTicket\": {\"stringValue\": \"%s\"},"
                                                                +"\"orgEmail\": {\"stringValue\": \"%s\"},"
                                                                +"\"orgContact\": {\"stringValue\": \"%s\"},"
                                                                +"\"venue\": {\"stringValue\": \"%s\"},"
                                                                + "\"eventprice\": {\"stringValue\": \"%s\"},"
                                                              + "\"imageUrl\": {\"stringValue\": \"%s\"}"
                                                             +  "}}", eventtitle_post,desc_post, eventdate_post, startTime_post, endTime_post,eventTicket_post,orgEmail_post,orgContact_post,venue_post,eventprice_post,imageUrl);

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
