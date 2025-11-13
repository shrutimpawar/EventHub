
// package com.eventhub.View;

// import javafx.animation.KeyFrame;
// import javafx.animation.Timeline;
// import javafx.application.Platform;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.control.*;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.stage.Modality;
// import javafx.stage.Stage;
// import javafx.stage.StageStyle;
// import javafx.util.Duration;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;
// import java.time.LocalTime;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.List;

// public class ChatbotUI {

//     private List<String> eventTitles = new ArrayList<>();
//     private List<String> eventDetails = new ArrayList<>();
//     private static final String GEMINI_API_KEY = "AIzaSyBRUq__-ukWR0ZgoZBfombRkHpd9G_MMWA";
//     private static final String GEMINI_ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-preview-05-20:generateContent?key=" + GEMINI_API_KEY;

//     public void showChatbot(Stage ownerStage) {
//         // Fetch events on init for context (Firestore-specific)
//         fetchEventsFromFirestore();

//         // Use ListView for messages for better performance and scrolling
//         ListView<HBox> chatList = new ListView<>();
//         chatList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

//         ScrollPane scroll = new ScrollPane(chatList);
//         scroll.setFitToWidth(true);
//         scroll.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

//         TextField input = new TextField();
//         input.setPromptText("Ask me anything—events, general knowledge, or more! 🎉");
//         input.setPrefHeight(40);
//         input.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 10; -fx-font-size: 14;");

//         Button send = new Button("Send");
//         send.setStyle("-fx-background-radius: 20; -fx-padding: 10 20; -fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

//         send.setOnAction(e -> {
//             String msg = input.getText().trim();
//             if (!msg.isEmpty()) {
//                 addMessage(chatList, msg, true); // User message
//                 input.clear();
//                 // Show thinking indicator first
//                 Label statusLabel = new Label("EventBot is thinking...");
//                 statusLabel.setStyle("-fx-text-fill: #651085ff; -fx-font-style: italic; -fx-font-size: 12;");
//                 HBox statusBox = new HBox(statusLabel);
//                 statusBox.setAlignment(Pos.CENTER_LEFT);
//                 statusBox.setPadding(new Insets(5));
//                 chatList.getItems().add(statusBox);
//                 scrollToBottom(chatList);

//                 // Animate dots for thinking/typing
//                 Timeline statusAnimation = new Timeline(new KeyFrame(Duration.millis(500), ev -> {
//                     String text = statusLabel.getText();
//                     if (text.endsWith("...")) {
//                         statusLabel.setText(text.substring(0, text.indexOf(" is") + 4)); // Reset to base
//                     } else {
//                         statusLabel.setText(text + ".");
//                     }
//                 }));
//                 statusAnimation.setCycleCount(Timeline.INDEFINITE);
//                 statusAnimation.play();

//                 // Simulate thinking delay (1-2 seconds) before switching to typing and API call
//                 new Thread(() -> {
//                     try {
//                         Thread.sleep(1500); // Simulated thinking time
//                     } catch (InterruptedException ie) {
//                         ie.printStackTrace();
//                     }
//                     Platform.runLater(() -> statusLabel.setText("EventBot is typing..."));

//                     String response = getGeminiResponse(msg);
//                     Platform.runLater(() -> {
//                         statusAnimation.stop();
//                         chatList.getItems().remove(statusBox);
//                         addMessage(chatList, response, false); // Bot response
//                         scrollToBottom(chatList);
//                     });
//                 }).start();
//             }
//         });

//         input.setOnAction(e -> send.fire()); // Enter key sends message

//         HBox bottom = new HBox(10, input, send);
//         bottom.setPadding(new Insets(10));
//         HBox.setHgrow(input, Priority.ALWAYS);

//         BorderPane root = new BorderPane();
//         root.setCenter(scroll);
//         root.setBottom(bottom);
//         root.setStyle("-fx-background-color: #f7e8e8ff; -fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");

//         // Initial welcome message with enhanced training prompt context
//         addMessage(chatList, "Hi! I'm EventBot, powered by Gemini AI. I can answer questions about Event Hub events from Firestore, but also general queries like weather, news, math, or anything else using my broad knowledge. Ask away! 🎉", false);

//         // Embed as a modal dialog for integrated feel
//         Dialog<Void> dialog = new Dialog<>();
//         dialog.initOwner(ownerStage);
//         dialog.initModality(Modality.APPLICATION_MODAL);
//         dialog.initStyle(StageStyle.UTILITY);
//         dialog.getDialogPane().setContent(root);
//         dialog.getDialogPane().setPrefSize(500, 700);
//         dialog.getDialogPane().setStyle("-fx-background-color: #f7e8e8ff; -fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10;");
//         dialog.setTitle("EventBot - AI Chat");
//         dialog.show();
//     }

//     private void addMessage(ListView<HBox> chatList, String msg, boolean isUser) {
//         Label label = new Label(msg);
//         label.setWrapText(true);
//         label.setPadding(new Insets(12));
//         label.setFont(Font.font("Arial", 14));
//         label.setMaxWidth(350);
//         label.setEffect(new DropShadow(5, Color.LIGHTGRAY));

//         Label timestamp = new Label(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
//         timestamp.setFont(Font.font("Arial", 10));
//         timestamp.setTextFill(Color.GRAY);
//         timestamp.setPadding(new Insets(0, 0, 0, 5));

//         VBox messageContent = new VBox(label, timestamp);
//         messageContent.setSpacing(2);

//         if (isUser) {
//             messageContent.setAlignment(Pos.BOTTOM_RIGHT);
//             label.setStyle("-fx-background-color: #651085ff; -fx-text-fill: white; -fx-background-radius: 15 15 0 15;");
//             timestamp.setAlignment(Pos.BOTTOM_RIGHT);
//         } else {
//             messageContent.setAlignment(Pos.BOTTOM_LEFT);
//             label.setStyle("-fx-background-color: white; -fx-text-fill: #2c2c2c; -fx-background-radius: 15 15 15 0; -fx-border-color: #ddd; -fx-border-radius: 15 15 15 0;");
//             timestamp.setAlignment(Pos.BOTTOM_LEFT);
//         }

//         HBox wrapper = new HBox(messageContent);
//         wrapper.setPadding(new Insets(5));
//         wrapper.setAlignment(isUser ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);

//         chatList.getItems().add(wrapper);
//     }

//     private void scrollToBottom(ListView<HBox> chatList) {
//         if (!chatList.getItems().isEmpty()) {
//             chatList.scrollTo(chatList.getItems().size() - 1);
//         }
//     }

//     private String getGeminiResponse(String userMessage) {
//         try {
//             // Enhanced prompt for broader "training" – handles all questions, beyond Firestore
//             StringBuilder context = new StringBuilder("You are EventBot, an advanced AI assistant for Event Hub. ");
//             context.append("You have comprehensive knowledge on all topics: events, general queries, science, history, news, advice, creative ideas, and more. ");
//             context.append("Always be accurate, engaging, witty when appropriate, and helpful. Use emojis to make responses fun. ");
//             context.append("For Event Hub-specific questions, use the provided Firestore data. For everything else, draw from your general knowledge. ");
//             context.append("If unsure, say so and suggest alternatives. Ask follow-up questions if needed for clarity. Keep responses concise but informative.");
//             if (!eventTitles.isEmpty()) {
//                 context.append("\nEvent Hub data from Firestore:\n");
//                 for (int i = 0; i < eventTitles.size(); i++) {
//                     context.append("- ").append(eventTitles.get(i)).append(": ").append(eventDetails.get(i)).append("\n");
//                 }
//                 context.append("\nIntegrate this seamlessly if relevant; otherwise, handle as a general AI.");
//             }

//             String prompt = context.toString() + "\n\nUser: " + userMessage + "\nEventBot:";

//             // Prepare JSON payload
//             String payload = String.format(
//                     "{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}",
//                     prompt.replace("\"", "\\\"")
//             );

//             URL url = new URL(GEMINI_ENDPOINT);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("POST");
//             conn.setRequestProperty("Content-Type", "application/json");
//             conn.setDoOutput(true);

//             try (OutputStream os = conn.getOutputStream()) {
//                 byte[] input = payload.getBytes(StandardCharsets.UTF_8);
//                 os.write(input, 0, input.length);
//             }

//             int responseCode = conn.getResponseCode();
//             if (responseCode == HttpURLConnection.HTTP_OK) {
//                 BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
//                 StringBuilder response = new StringBuilder();
//                 String line;
//                 while ((line = reader.readLine()) != null) {
//                     response.append(line);
//                 }
//                 reader.close();

//                 // Improved parsing to extract only the text content
//                 String jsonResponse = response.toString();
//                 int textStart = jsonResponse.indexOf("\"text\": \"") + 9;
//                 if (textStart > 8) {
//                     int textEnd = jsonResponse.indexOf("\"", textStart); // Take only up to the next quote
//                     String rawResponse = jsonResponse.substring(textStart, textEnd).replace("\\n", "\n").replace("\\\"", "\"").trim();
//                     // Remove any trailing JSON artifacts or metadata
//                     return rawResponse.replaceAll("[{}\\s]*$", "").trim();
//                 }
//                 return "Thanks for your query! (Unexpected response format.)";
//             } else {
//                 BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
//                 StringBuilder error = new StringBuilder();
//                 String errorLine;
//                 while ((errorLine = errorReader.readLine()) != null) {
//                     error.append(errorLine);
//                 }
//                 errorReader.close();
//                 return "Oops! API Error " + responseCode + ". Try again or check the connection.";
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             return "Sorry, I encountered an error. Please try again.";
//         }
//     }

//     private void fetchEventsFromFirestore() {
//         // Existing fetch code remains the same
//         try {
//             String endpoint = String.format(
//                     "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/events?key=%s",
//                     "event-hub-7033e", "AIzaSyBRUq__-ukWR0ZgoZBfombRkHpd9G_MMWA");

//             URL url = new URL(endpoint);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("GET");
//             conn.setRequestProperty("Content-Type", "application/json");

//             BufferedReader reader = new BufferedReader(
//                     new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
//             StringBuilder response = new StringBuilder();
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 response.append(line);
//             }
//             reader.close();
//             conn.disconnect();

//             String json = response.toString();

//             String[] events = json.split("\"fields\":");
//             eventTitles.clear();
//             eventDetails.clear();
//             for (int i = 1; i < events.length; i++) {
//                 String block = events[i];

//                 String title = extractValue(block, "eventtitle");
//                 String date = extractValue(block, "eventdate");
//                 String venue = extractValue(block, "venue");
//                 String price = extractValue(block, "eventprice");
//                 String desc = extractValue(block, "desc");

//                 if (!title.isEmpty()) {
//                     eventTitles.add(title);
//                     eventDetails.add("Date: " + date + "\nVenue: " + venue + "\nPrice: " + price + "\nDescription: " + desc);
//                 }
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     private String extractValue(String json, String field) {
//         String key = "\"" + field + "\":";
//         int startIndex = json.indexOf(key);
//         if (startIndex == -1) return "";
//         String sub = json.substring(startIndex);
//         int valueStart = sub.indexOf("\"stringValue\":");
//         if (valueStart == -1) return "";
//         int firstQuote = sub.indexOf("\"", valueStart + 14);
//         int secondQuote = sub.indexOf("\"", firstQuote + 1);
//         return sub.substring(firstQuote + 1, secondQuote);
//     }
// }

package com.eventhub.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChatbotUI {

    private List<String> eventTitles = new ArrayList<>();
    private List<String> eventDetails = new ArrayList<>();
    private static final String GEMINI_API_KEY = "AIzaSyBRUq__-ukWR0ZgoZBfombRkHpd9G_MMWA";
    private static final String GEMINI_ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-preview-05-20:generateContent?key=" + GEMINI_API_KEY;

    public void showChatbot(Stage ownerStage) {
        // Fetch events on init for context (Firestore-specific)
        fetchEventsFromFirestore();

        // Use ListView for messages for better performance and scrolling
        ListView<HBox> chatList = new ListView<>();
        chatList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        ScrollPane scroll = new ScrollPane(chatList);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        TextField input = new TextField();
        input.setPromptText("Ask me anything...");
        input.setPrefHeight(40);
        input.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 10; -fx-font-size: 14;");

        Button send = new Button("Send");
        send.setStyle("-fx-background-radius: 20; -fx-padding: 10 20; -fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        send.setOnAction(e -> {
            String msg = input.getText().trim();
            if (!msg.isEmpty()) {
                addMessage(chatList, msg, true); // User message
                input.clear();
                // Show thinking indicator first
                Label statusLabel = new Label("EventBot is thinking...");
                statusLabel.setStyle("-fx-text-fill: #651085ff; -fx-font-style: italic; -fx-font-size: 12;");
                HBox statusBox = new HBox(statusLabel);
                statusBox.setAlignment(Pos.CENTER_LEFT);
                statusBox.setPadding(new Insets(5));
                chatList.getItems().add(statusBox);
                scrollToBottom(chatList);

                // Animate dots for thinking/typing
                Timeline statusAnimation = new Timeline(new KeyFrame(Duration.millis(500), ev -> {
                    String text = statusLabel.getText();
                    if (text.endsWith("...")) {
                        statusLabel.setText(text.substring(0, text.indexOf(" is") + 4)); // Reset to base
                    } else {
                        statusLabel.setText(text + ".");
                    }
                }));
                statusAnimation.setCycleCount(Timeline.INDEFINITE);
                statusAnimation.play();

                // Simulate thinking delay (1-2 seconds) before switching to typing and API call
                new Thread(() -> {
                    try {
                        Thread.sleep(1500); // Simulated thinking time
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    Platform.runLater(() -> statusLabel.setText("EventBot is typing..."));

                    String response = getGeminiResponse(msg);
                    Platform.runLater(() -> {
                        statusAnimation.stop();
                        chatList.getItems().remove(statusBox);
                        addMessage(chatList, response, false); // Bot response
                        scrollToBottom(chatList);
                    });
                }).start();
            }
        });

        input.setOnAction(e -> send.fire()); // Enter key sends message

        HBox bottom = new HBox(10, input, send);
        bottom.setPadding(new Insets(10));
        HBox.setHgrow(input, Priority.ALWAYS);

        BorderPane root = new BorderPane();
        root.setCenter(scroll);
        root.setBottom(bottom);
        root.setStyle("-fx-background-color: #f7e8e8ff; -fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");

        // Initial welcome message with enhanced training prompt context
        addMessage(chatList, "Hi! I'm EventBot", false);

        // Use Stage instead of Dialog for better control over close button
        Stage chatStage = new Stage();
        chatStage.initOwner(ownerStage);
        chatStage.initModality(Modality.APPLICATION_MODAL);
        chatStage.initStyle(StageStyle.UTILITY);
        chatStage.setScene(new Scene(root, 500, 700));
        chatStage.setTitle("EventBot - AI Chat");

        // Add close handler to return to home page
        chatStage.setOnCloseRequest(event -> {
            chatStage.close();
            Homepage homePage = new Homepage();
            homePage.showHomePage(ownerStage); // Return to home page
        });

        chatStage.show();
    }

    private void addMessage(ListView<HBox> chatList, String msg, boolean isUser) {
        Label label = new Label(msg);
        label.setWrapText(true);
        label.setPadding(new Insets(12));
        label.setFont(Font.font("Arial", 14));
        label.setMaxWidth(350);
        label.setEffect(new DropShadow(5, Color.LIGHTGRAY));

        Label timestamp = new Label(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        timestamp.setFont(Font.font("Arial", 10));
        timestamp.setTextFill(Color.GRAY);
        timestamp.setPadding(new Insets(0, 0, 0, 5));

        VBox messageContent = new VBox(label, timestamp);
        messageContent.setSpacing(2);

        if (isUser) {
            messageContent.setAlignment(Pos.BOTTOM_RIGHT);
            label.setStyle("-fx-background-color: #651085ff; -fx-text-fill: white; -fx-background-radius: 15 15 0 15;");
            timestamp.setAlignment(Pos.BOTTOM_RIGHT);
        } else {
            messageContent.setAlignment(Pos.BOTTOM_LEFT);
            label.setStyle("-fx-background-color: white; -fx-text-fill: #2c2c2c; -fx-background-radius: 15 15 15 0; -fx-border-color: #ddd; -fx-border-radius: 15 15 15 0;");
            timestamp.setAlignment(Pos.BOTTOM_LEFT);
        }

        HBox wrapper = new HBox(messageContent);
        wrapper.setPadding(new Insets(5));
        wrapper.setAlignment(isUser ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);

        chatList.getItems().add(wrapper);
    }

    private void scrollToBottom(ListView<HBox> chatList) {
        if (!chatList.getItems().isEmpty()) {
            chatList.scrollTo(chatList.getItems().size() - 1);
        }
    }

    private String getGeminiResponse(String userMessage) {
        try {
            // Enhanced prompt for broader "training" – handles all questions, beyond Firestore
            StringBuilder context = new StringBuilder("You are EventBot, an advanced AI assistant for Event Hub. ");
            context.append("You have comprehensive knowledge on all topics: events, general queries, science, history, news, advice, creative ideas, and more. ");
            context.append("Always be accurate, engaging, witty when appropriate, and helpful. Use emojis to make responses fun. ");
            context.append("For Event Hub-specific questions, use the provided Firestore data. For everything else, draw from your general knowledge. ");
            context.append("If unsure, say so and suggest alternatives. Ask follow-up questions if needed for clarity. Keep responses concise but informative.");
            if (!eventTitles.isEmpty()) {
                context.append("\nEvent Hub data from Firestore:\n");
                for (int i = 0; i < eventTitles.size(); i++) {
                    context.append("- ").append(eventTitles.get(i)).append(": ").append(eventDetails.get(i)).append("\n");
                }
                context.append("\nIntegrate this seamlessly if relevant; otherwise, handle as a general AI.");
            }

            String prompt = context.toString() + "\n\nUser: " + userMessage + "\nEventBot:";

            // Prepare JSON payload
            String payload = String.format(
                    "{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}",
                    prompt.replace("\"", "\\\"")
            );

            URL url = new URL(GEMINI_ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Improved parsing to extract only the text content
                String jsonResponse = response.toString();
                int textStart = jsonResponse.indexOf("\"text\": \"") + 9;
                if (textStart > 8) {
                    int textEnd = jsonResponse.indexOf("\"", textStart); // Take only up to the next quote
                    String rawResponse = jsonResponse.substring(textStart, textEnd).replace("\\n", "\n").replace("\\\"", "\"").trim();
                    // Remove any trailing JSON artifacts or metadata
                    return rawResponse.replaceAll("[{}\\s]*$", "").trim();
                }
                return "Thanks for your query! (Unexpected response format.)";
            } else {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
                StringBuilder error = new StringBuilder();
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    error.append(errorLine);
                }
                errorReader.close();
                return "Oops! API Error " + responseCode + ". Try again or check the connection.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, I encountered an error. Please try again.";
        }
    }

    private void fetchEventsFromFirestore() {
        // Existing fetch code remains the same
        try {
            String endpoint = String.format(
                    "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/events?key=%s",
                    "event-hub-7033e", "AIzaSyBRUq__-ukWR0ZgoZBfombRkHpd9G_MMWA");

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

            String json = response.toString();

            String[] events = json.split("\"fields\":");
            eventTitles.clear();
            eventDetails.clear();
            for (int i = 1; i < events.length; i++) {
                String block = events[i];

                String title = extractValue(block, "eventtitle");
                String date = extractValue(block, "eventdate");
                String venue = extractValue(block, "venue");
                String price = extractValue(block, "eventprice");
                String desc = extractValue(block, "desc");

                if (!title.isEmpty()) {
                    eventTitles.add(title);
                    eventDetails.add("Date: " + date + "\nVenue: " + venue + "\nPrice: " + price + "\nDescription: " + desc);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractValue(String json, String field) {
        String key = "\"" + field + "\":";
        int startIndex = json.indexOf(key);
        if (startIndex == -1) return "";
        String sub = json.substring(startIndex);
        int valueStart = sub.indexOf("\"stringValue\":");
        if (valueStart == -1) return "";
        int firstQuote = sub.indexOf("\"", valueStart + 14);
        int secondQuote = sub.indexOf("\"", firstQuote + 1);
        return sub.substring(firstQuote + 1, secondQuote);
    }
}