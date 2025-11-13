package com.eventhub.View;



import java.net.URI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.awt.Desktop;

public class DetailPage {

   public static void showDetailPage(String title, String date, String location, String price, String start,String end, String ticket, String email, String contact, String images, String descText)
 { 
        Stage detailStage = new Stage();

        // Back Button
        Button backButton = new Button("<<");
        backButton.setFont(Font.font("Arial", 20));
        backButton.setStyle("-fx-background-color: white;-fx-background-radius: 160; -fx-text-fill: #9f52a9ff;");

        backButton.setOnAction(e -> {
            detailStage.close();

        });

        Label title1 = new Label("Events Detail");
        title1.setFont(new Font(30));
        title1.setStyle("-fx-font-weight:bold;-fx-text-fill: #f4f4f4;");

        HBox backContainer = new HBox(550,backButton,title1);
        backContainer.setAlignment(Pos.CENTER_LEFT);
        backContainer.setPadding(new Insets(20));
        backContainer.setStyle("-fx-background-color: #271539;;-fx-background-radius: 20;");

        // Image
        Image img = new Image(images);
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, #aaa, 8,0.2,0,4);");

        VBox leftBox = new VBox(imageView);
        leftBox.setPadding(new Insets(20));
        leftBox.setAlignment(Pos.TOP_CENTER);
        leftBox.setStyle("-fx-background-color: #ffffff; -fx-background-radius:12;");

        // Right-side event details
        Label eventtitle = new Label(title);   
        eventtitle.setFont(Font.font("Arial", 22));
        eventtitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c2c2c;");

        Label eventdate = new Label("📅 " +date);    //"📅 27 Jul - 31 Aug, 2025 | 🕖 7:00 PM"
        Label startTime = new Label("🕖 Start Time : "+start);
        Label endTime = new Label("🕖 End Time : "+end);
        Label eventTicket = new Label("🎟 Ticket : "+ticket);
        Label orgEmail = new Label("✉ Email : "+email);
        Label orgContact = new Label("📞 Contact : "+contact);
        Label venue = new Label("📍Venue: "+location ); //Venue: Buntara Bhavan, Pune"
        Label eventprice = new Label("💸 Price: "+price); // ₹599

        VBox infoBox = new VBox(8, eventtitle, eventdate, startTime, endTime, eventTicket, orgEmail, orgContact,venue, eventprice);
        infoBox.setStyle("-fx-font-size: 14px; -fx-text-fill:#444;");

        Button bookNow = new Button("Book Now");
        bookNow.setStyle("-fx-background-color: #651085ff; -fx-text-fill: white; -fx-font-size: 16px;-fx-padding: 10px 20px; -fx-background-radius: 8px;");
        // bookNow.setOnAction(e -> {

        //       PaymentPage paymentPage = new PaymentPage();
        //       paymentPage.showPaymentPage();
        //       detailStage.close(); 

        // });

        bookNow.setOnAction(e -> {
            // Extract only the numeric part from price string (e.g., "₹599" or "₹0" or
            // "Free")
            String priceText = price.replaceAll("[^\\d.]", ""); // removes ₹ and other non-numeric characters

            double priceValue = 0;
            try {
                priceValue = priceText.isEmpty() ? 0 : Double.parseDouble(priceText);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            if (priceValue == 0) {
                // Free event: go directly to feedback page
                FeedbackPage feedbackPage = new FeedbackPage();
                feedbackPage.showFeedbackPage();
                detailStage.close();
            } else {
                // Paid event: redirect to payment
                PaymentPage paymentPage = new PaymentPage();
                paymentPage.showPaymentPage(priceValue); // pass the double value
                detailStage.close();
        }
});

        

        Image mapIcon = new Image("Assets\\Images\\map.jpg"); // your icon path
        ImageView iconView = new ImageView(mapIcon);
        iconView.setFitWidth(60); // adjust size
        iconView.setFitHeight(35);


        Button viewMap = new Button(" ", iconView);
        viewMap.setStyle("""
        -fx-background-color: transparent;
        -fx-cursor: hand;
        """);

        viewMap.setTooltip(new Tooltip("View Location on Google Maps"));

        viewMap.setOnAction(event -> {
            try {
                String query = location.replace(" ", "+");
                URI uri = new URI("https://www.google.com/maps/search/?api=1&query=" + query);
                Desktop.getDesktop().browse(uri);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // SHARE BUTTON WITH POPUP MENU
        Image shareIcon = new Image("Assets\\Images\\share.jpg"); // Ensure this icon exists
        ImageView shareView = new ImageView(shareIcon);
        shareView.setFitWidth(30);
        shareView.setFitHeight(30);

        Button shareButton = new Button("", shareView);
        shareButton.setTooltip(new Tooltip("Share this event"));
        shareButton.setStyle("""
        -fx-background-color: transparent;
        -fx-cursor: hand;
        """);
     
        VBox rightBox = new VBox(15, infoBox, bookNow,viewMap,shareButton);
        rightBox.setPadding(new Insets(20));
        rightBox.setAlignment(Pos.TOP_LEFT);
        rightBox.setStyle(
                "-fx-background-color: #ffffff; -fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10;");
        rightBox.setPrefWidth(460);


        // Create share message
        String message = "Check out this event: " + title + "\n📅 " + date +
                "\n📍Location: " + location + "\n🎟 Ticket: " + ticket + "\n💸 Price: " + price;

        String encodedMessage;
        String encodedSubject;
        try {
            encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
            encodedSubject = java.net.URLEncoder.encode("Event: " + title, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            encodedMessage = message; // fallback to unencoded if error
            encodedSubject = "Event: " + title;
        }
        final String finalEncodedMessage = encodedMessage;
        final String finalEncodedSubject = encodedSubject;

       
        MenuItem whatsapp = new MenuItem("Share via WhatsApp");
        whatsapp.setOnAction(event -> {
            openInBrowser("https://wa.me/?text=" + finalEncodedMessage);
        });

        MenuItem facebook = new MenuItem("Share on Facebook");
        facebook.setOnAction(event -> {
            String url = "https://youreventhub.com";
            openInBrowser("https://www.facebook.com/sharer/sharer.php?u=" + url + "&quote=" + finalEncodedMessage);
        });

        MenuItem twitter = new MenuItem("Share on Twitter");
        twitter.setOnAction(event -> {
            openInBrowser("https://twitter.com/intent/tweet?text=" + finalEncodedMessage);
        });

        MenuItem email1 = new MenuItem("Share via Email");
        email1.setOnAction(event -> {
            openInBrowser("mailto:?subject=" + finalEncodedSubject + "&body=" + finalEncodedMessage);
        });

      // Create the context menu
        ContextMenu shareMenu = new ContextMenu(whatsapp, facebook, twitter, email1);

        shareButton.setOnAction(e -> {
        shareMenu.show(shareButton, javafx.geometry.Side.BOTTOM, 0, 0);
        });

        HBox buttonRow = new HBox(10, bookNow, viewMap,shareButton);
        rightBox = new VBox(15, infoBox, buttonRow);
        buttonRow.setAlignment(Pos.CENTER_LEFT);


        HBox topRow = new HBox(100, leftBox, rightBox);
        topRow.setPadding(new Insets(80));
        topRow.setStyle("-fx-background-color: #f7e8e8ff;");
        topRow.setAlignment(Pos.TOP_CENTER);

        // Description
       Label descHeading = new Label("About Event");

        descHeading.setFont(Font.font("Arial", 20));
        descHeading.setStyle("-fx-font-weight: bold;");

        Label desc = new Label(descText);
        // Label desc = new Label("SwaraMalhar is an exclusive festival of the rare Malhaar Ragas, which are rendered only during the rains. "
        //   + "This event is a Musical prayer to God for Rain. All the Audience will have the privilege to listen to the Malhaar Ragas "
        //   + "such as Miyan Malhaar, Gaud Malhaar, Megh, Megh Malhaar, Sur Malhaar and many more.");
        desc.setWrapText(true);
        desc.setFont(Font.font("Arial", 14));

        // Label session1 = new Label("Session-1: Ojas Adhiya – (Tabla)");
        // Label session2 = new Label("Session-2: Sniti Mishra – (Vocal)");
        // Label session3 = new Label("Session-3: Rahul Deshpande – (Vocal)");

        VBox descBox = new VBox(10, descHeading, desc);
        descBox.setPadding(new Insets(20));
        descBox.setPrefHeight(100);
        descBox.setStyle("-fx-background-color: #f7e8e8ff; -fx-background-radius: 10; -fx-border-color: #ddd; -fx-border-radius: 10;");

        VBox main = new VBox(20,backContainer, topRow, descBox);
        main.setStyle("-fx-background-color: #f7e8e8ff;");

        // Scene scene = new Scene(main, 1540, 795); // Use 3-argument constructor
        // main.setStyle("-fx-background-color: white;"); // Set white background using CSS
        // detailStage.setTitle("Event Details - SwaraMalhar");
        // detailStage.setScene(scene);
        // detailStage.show();
        ScrollPane scrollPane = new ScrollPane(main);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: white;");

        Scene scene = new Scene(scrollPane, 1540, 795);
        detailStage.setTitle("Event Details");
        detailStage.setScene(scene);
        detailStage.show();
    }
    private static void openInBrowser(String url) {
    try {
        Desktop.getDesktop().browse(new URI(url));
    } catch (Exception e) {
        e.printStackTrace();
  }
}
}
