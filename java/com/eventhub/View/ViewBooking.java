// package com.eventhub.View;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.Stage;

// public class ViewBooking {

//     public void show(Stage stage) {

//         Label header = new Label("📋 View Bookings");
//         header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
//         header.setTextFill(Color.web("#0c0b0bff"));

//         VBox bookingList = new VBox(20);
//         bookingList.setPadding(new Insets(20));
//         bookingList.setStyle("-fx-background-color: #580d5bff; -fx-text-fill: white; -fx-font-weight: bold;");
        
//         // Dummy booking cards (can be replaced by dynamic data)
//         for (int i = 1; i <= 5; i++) {
//             VBox card = new VBox(8);
//             card.setPadding(new Insets(15));
//             card.setStyle("-fx-background-color: #f7e8e8ff; -fx-background-radius: 12; "
//                     + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 4);");

//             Label eventName = new Label("🎉 Event " + i);
//             eventName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
//             eventName.setTextFill(Color.web("#0f1010ff"));

//             Label date = new Label("📅 Date: 2025-08-0" + i);
//             Label bookedBy = new Label("👤 Booked by: User" + i);
//             Label email = new Label("📧 Email: user" + i + "@mail.com");
//             Label phone = new Label("📞 Phone: 98765" + (10000 + i));
//             Label tickets = new Label("🎟 Tickets: " + (2 + i));
//             Label total = new Label("💰 Total Amount: ₹" + (499 * (2 + i)));
//             Label bookingTime = new Label("⏰ Booking Time: 12:0" + i + " PM");
//             Label bookingId = new Label("🪪 Booking ID: BK2025" + i + "XYZ");

//             Label[] labels = {date, bookedBy, email, phone, tickets, total, bookingTime, bookingId};
//             for (Label label : labels) {
//                 label.setFont(Font.font("Segoe UI", 14));
//                 label.setTextFill(Color.web("#0d0d0dff"));
//             }

//             card.getChildren().addAll(eventName, date, bookedBy, email, phone, tickets, total, bookingTime, bookingId);
//             bookingList.getChildren().add(card);
//         }

//         Button BackBtn = new Button("Back");
//         BackBtn.setPrefWidth(100);
//        BackBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
//                 + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
//                 "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
//         BackBtn.setFont(Font.font("Arial", 20));
//         //tech.setPrefWidth(200);

//         BackBtn.setOnAction(e -> {
//             OrganizerDashboard orPage = new OrganizerDashboard();
//             orPage.showOrganizerDashboard(stage);
//         });

//         ScrollPane scrollPane = new ScrollPane(bookingList);
//         scrollPane.setFitToWidth(true);

//         VBox root = new VBox(25, header, scrollPane, BackBtn);
//         root.setPadding(new Insets(30));
//         root.setStyle("-fx-background-color: #f7e8e8ff;");
//         root.setAlignment(Pos.TOP_CENTER);

//         Scene scene = new Scene(root, 1540, 795);
//         stage.setTitle("Organizer - View Bookings");
//         stage.setScene(scene);
//         stage.show();
// }

// }

package com.eventhub.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewBooking {

    public void show(Stage stage) {

        Label header = new Label("📋 View Bookings");
        header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
        header.setTextFill(Color.web("#0c0b0bff"));

        VBox bookingList = new VBox(20);
        bookingList.setPadding(new Insets(20));
        bookingList.setStyle("-fx-background-color: #580d5bff; -fx-text-fill: white; -fx-font-weight: bold;");

        // Manually added Booking 1
        VBox card1 = new VBox(8);
        card1.setPadding(new Insets(15));
        card1.setStyle("-fx-background-color: #f7e8e8ff; -fx-background-radius: 12; "
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 4);");

        Label eventName1 = new Label("KhelKabbadi");
        eventName1.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        eventName1.setTextFill(Color.web("#0f1010ff"));

        Label date1 = new Label("📅 Date: 2025-07-05");
        Label bookedBy1 = new Label("👤 Booked by: Shreya");
        Label email1 = new Label("📧 Email: shreya12@mail.com");
       // Label phone1 = new Label("📞 Phone: 9876511111");
        Label tickets1 = new Label("🎟 Tickets: 1");
        Label total1 = new Label("💰 Total Amount: ₹250");
        //Label bookingTime1 = new Label("⏰ Booking Time: 12:02 PM");
        //Label bookingId1 = new Label("🪪 Booking ID: BK20251XYZ");

        Label[] labels1 = {date1, bookedBy1, email1, tickets1, total1};
        for (Label label : labels1) {
            label.setFont(Font.font("Segoe UI", 14));
            label.setTextFill(Color.web("#0d0d0dff"));
        }

        card1.getChildren().addAll(eventName1, date1, bookedBy1, email1, tickets1, total1);

        // Manually added Booking 2
        VBox card2 = new VBox(8);
        card2.setPadding(new Insets(15));
        card2.setStyle("-fx-background-color: #f7e8e8ff; -fx-background-radius: 12; "
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 4);");

        Label eventName2 = new Label("Tree Plantation");
        eventName2.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        eventName2.setTextFill(Color.web("#0f1010ff"));

        Label date2 = new Label("📅 Date: 2025-07-27");
        Label bookedBy2 = new Label("👤 Booked by: Sayali Pawar");
        Label email2 = new Label("📧 Email: rahul.sayali5566@mail.com");
       // Label phone2 = new Label("📞 Phone: 9876522222");
        Label tickets2 = new Label("🎟 Tickets: 1");
        Label total2 = new Label("💰 Total Amount: Free");
        //Label bookingTime2 = new Label("⏰ Booking Time: 12:03 PM");
        //Label bookingId2 = new Label("🪪 Booking ID: BK20252XYZ");

        Label[] labels2 = {date2, bookedBy2, email2, tickets2, total2,};
        for (Label label : labels2) {
            label.setFont(Font.font("Segoe UI", 14));
            label.setTextFill(Color.web("#0d0d0dff"));
        }

        card2.getChildren().addAll(eventName2, date2, bookedBy2, email2, tickets2, total2);

        // Manually added Booking 3
        VBox card3 = new VBox(8);
        card3.setPadding(new Insets(15));
        card3.setStyle("-fx-background-color: #f7e8e8ff; -fx-background-radius: 12; "
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 4);");

        Label eventName3 = new Label("NextAi");
        eventName3.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        eventName3.setTextFill(Color.web("#0f1010ff"));

        Label date3 = new Label("📅 Date: 2025-08-08");
        Label bookedBy3 = new Label("👤 Booked by: Saundarya Surana");
        Label email3 = new Label("📧 Email: sau@mail.com");
       // Label phone3 = new Label("📞 Phone: 9876533333");
        Label tickets3 = new Label("🎟 Tickets: 1");
        Label total3 = new Label("💰 Total Amount: ₹250");
        //Label bookingTime3 = new Label("⏰ Booking Time: 12:04 PM");
        //Label bookingId3 = new Label("🪪 Booking ID: BK20253XYZ");

        Label[] labels3 = {date3, bookedBy3, email3,tickets3, total3};
        for (Label label : labels3) {
            label.setFont(Font.font("Segoe UI", 14));
            label.setTextFill(Color.web("#0d0d0dff"));
        }

        card3.getChildren().addAll(eventName3, date3, bookedBy3, email3,tickets3, total3);

        // Add manually created cards to the booking list
        bookingList.getChildren().addAll(card1, card2, card3);

        Button BackBtn = new Button("Back");
        BackBtn.setPrefWidth(100);
        BackBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);"
                + "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        BackBtn.setFont(Font.font("Arial", 20));

        BackBtn.setOnAction(e -> {
            OrganizerDashboard orPage = new OrganizerDashboard();
            orPage.showOrganizerDashboard(stage);
        });

        ScrollPane scrollPane = new ScrollPane(bookingList);
        scrollPane.setFitToWidth(true);

        VBox root = new VBox(25, header, scrollPane, BackBtn);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f7e8e8ff;");
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 1540, 795);
        stage.setTitle("Organizer - View Bookings");
        stage.setScene(scene);
        stage.show();
    }
}