package com.eventhub.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PaymentPage {

    VBox dynamicForm = new VBox(10); // Common form container
    VBox paymentMethods = new VBox(10);

    // --- Ticket data (Make this dynamic later) ---
    // int ticketQuantity = 2;
    // double ticketPrice = 90.0;
    // double totalAmountValue = ticketQuantity * ticketPrice;
     
    private int ticketQuantity;
    private double ticketPrice;
    private double totalAmountValue;

    public void showPaymentPage(double ticketPrice) {
        Stage stage = new Stage();
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #fdf6f0;");

        this.ticketPrice = ticketPrice;
        this.ticketQuantity = 1;
        this.totalAmountValue = this.ticketQuantity * this.ticketPrice;

        Label title = new Label("Payment Page");
        title.setFont(new Font(40));
        title.setStyle("-fx-text-fill:#333333;");
        title.setAlignment(Pos.CENTER);

        VBox orderBox = new VBox(8);

        // Header
        Label summaryHeader = new Label("Ticket Summary");
        summaryHeader.setFont(Font.font("Poppins", FontWeight.BOLD, 16));

        // Ticket Info
        Label quantityLabel = new Label("✔ Quantity: " + ticketQuantity + " tickets");
        Label priceLabel = new Label("✔ Price per ticket: ₹" + String.format("%.2f", ticketPrice));

        // Total
        HBox totalRow = new HBox(10);
        Label totalLabel = new Label("Total:");
        totalLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        Label totalAmountLabel = new Label("₹" + String.format("%.2f", totalAmountValue));
        totalAmountLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        totalRow.getChildren().addAll(totalLabel, spacer2, totalAmountLabel);

        orderBox.getChildren().addAll(summaryHeader, quantityLabel, priceLabel, totalRow);
        orderBox.setPadding(new Insets(10));
        orderBox.setStyle("-fx-background-color: #fffaf0; -fx-border-color: #ddd; -fx-border-radius: 8;");

        // Payment Method Section
        Label paymentMethodLabel = new Label("Select Payment Method");
        paymentMethodLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 14));

        paymentMethods.setPadding(new Insets(10));
        paymentMethods.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5;");

        addPaymentMethod("UPI");
        addPaymentMethod("Cards");
        addPaymentMethod("Wallets");
        addPaymentMethod("Net Banking");

        // Cancel Button (No action yet)
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        cancelBtn.setMaxWidth(150);

        cancelBtn.setOnAction(event -> stage.close()); // 👈 Close the payment window
        cancelBtn.setCursor(Cursor.HAND);
        HBox cancelRow = new HBox(cancelBtn);
        cancelRow.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().addAll(title, orderBox, paymentMethodLabel, paymentMethods, dynamicForm, cancelRow);

        Scene scene = new Scene(root, 1540, 795);
        stage.setTitle("ServiceConnect - Payment");
        stage.setScene(scene);
        stage.show();
    }

    private void addPaymentMethod(String method) {
        VBox rowWrapper = new VBox();
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(10));
        row.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #ddd; -fx-background-radius: 8;");

        Label methodLabel = new Label(method);
        methodLabel.setFont(Font.font("Poppins", 14));
        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        Label priceLabel = new Label("₹" + String.format("%.2f", totalAmountValue) + "\n(includes all charges)");
        priceLabel.setFont(Font.font("Poppins", 12));

        row.getChildren().addAll(methodLabel, space, priceLabel);
        rowWrapper.getChildren().add(row);

        row.setOnMouseClicked(e -> {
            dynamicForm.getChildren().clear();
            switch (method) {
                case "Cards" -> showCardForm();
                case "UPI" -> showUpiForm();
                case "Wallets" -> showWalletForm();
                case "Net Banking" -> showBankForm();
            }
        });

        paymentMethods.getChildren().add(rowWrapper);
    }

    private void showCardForm() {
        dynamicForm.setPadding(new Insets(10));
        dynamicForm.setStyle("-fx-background-color: #fffaf0; -fx-border-color: #f4c95d; -fx-border-radius: 8;");

        Label title = new Label("Enter Card Details");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        TextField cardNumber = new TextField("5123 4567 8901 2345");
        cardNumber.setPromptText("Card Number");

        TextField name = new TextField();
        name.setPromptText("Name on Card");

        HBox row = new HBox(10);
        TextField expiry = new TextField("09/27");
        expiry.setPromptText("MM/YY");
        TextField cvv = new TextField("123");
        cvv.setPromptText("CVV");
        row.getChildren().addAll(expiry, cvv);

        Button payBtn = getPayButton();

        dynamicForm.getChildren().addAll(title, cardNumber, name, row, payBtn);
    }

    private void showUpiForm() {
        dynamicForm.setPadding(new Insets(10));
        dynamicForm.setStyle("-fx-background-color: #fffaf0; -fx-border-color: #a3c9a8; -fx-border-radius: 8;");

        Label title = new Label("Enter UPI ID");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        TextField upi = new TextField("abcd@okaxis");
        upi.setPromptText("e.g. username@bank");

        Button payBtn = getPayButton();

        dynamicForm.getChildren().addAll(title, upi, payBtn);
    }

    private void showWalletForm() {
        dynamicForm.setPadding(new Insets(10));
        dynamicForm.setStyle("-fx-background-color: #fffaf0; -fx-border-color: #f26b6b; -fx-border-radius: 8;");

        Label title = new Label("Select Wallet");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 14));

        ComboBox<String> wallets = new ComboBox<>();
        wallets.getItems().addAll("PhonePe", "Paytm", "Amazon Pay");
        wallets.setValue("PhonePe");

        Button payBtn = getPayButton();

        dynamicForm.getChildren().addAll(title, wallets, payBtn);
    }

    private void showBankForm() {
        dynamicForm.setPadding(new Insets(10));
        dynamicForm.setStyle("-fx-background-color: #fffaf0; -fx-border-color: #b2dafa; -fx-border-radius: 8;");

        Label title = new Label("Select Bank");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 14));

        ComboBox<String> banks = new ComboBox<>();
        banks.getItems().addAll("SBI", "HDFC", "ICICI", "Axis Bank");
        banks.setValue("HDFC");

        Button payBtn = getPayButton();

        dynamicForm.getChildren().addAll(title, banks, payBtn);
    }

    private Button getPayButton() {
    Button btn = new Button("Pay ₹" + String.format("%.2f", totalAmountValue));
    btn.setStyle("-fx-background-color: #f26b6b; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 8 16;");
    
    btn.setOnAction(e -> {
        // Create popup stage
        Stage popup = new Stage();
        popup.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        popup.initStyle(javafx.stage.StageStyle.UNDECORATED);

        // Create popup content
        VBox popupContent = new VBox(15);
        popupContent.setPadding(new Insets(20));
        popupContent.setAlignment(Pos.CENTER);
        popupContent.setStyle("-fx-background-color: #d4edda; -fx-border-color: #28a745; -fx-border-width: 2; -fx-background-radius: 10;");

        Label tick = new Label("✅");
        tick.setFont(Font.font("Arial", 40));

        Label success = new Label("Payment completed successfully!");
        success.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        success.setStyle("-fx-text-fill: #155724;");

        Button okBtn = new Button("OK");
            okBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 6 20;");
            okBtn.setOnAction(ev -> {
                popup.close(); // Close popup
                ((Stage) btn.getScene().getWindow()).close(); // Close payment page
                FeedbackPage feedbackPage = new FeedbackPage();
                feedbackPage.showFeedbackPage(); // Navigate to feedback page
             });
        popupContent.getChildren().addAll(tick, success, okBtn);

        Scene popupScene = new Scene(popupContent, 350, 200);
        popup.setScene(popupScene);
        popup.showAndWait();
    });

    return btn;
}


}

 
