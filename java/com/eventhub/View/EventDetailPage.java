package com.eventhub.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EventDetailPage {

    Stage EventDetailPageStage, primaryStage, HomepageStage;
    Scene EventDetailPageScene;

    public void setEventDetailPageStage(Stage eventDetailPageStage) {
        this.EventDetailPageStage = eventDetailPageStage;
    }

    public void setEventDetailPageScene(Scene eventDetailPageScene) {
        this.EventDetailPageScene = eventDetailPageScene;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    private final List<VBox> bookmarkedCards = new ArrayList<>();

    public VBox createEventDetailPageScene() {
        BorderPane border = new BorderPane();
        border.setStyle("-fx-background-color: #fffaf0;");

        Label title = new Label("Explore Events");
        title.setFont(new Font(30));
        title.setStyle("-fx-font-weight:bold;-fx-text-fill: #f4f4f4;");

        // VBox vbl = new VBox(20, title);
        // vbl.setAlignment(Pos.TOP_CENTER);
        // vbl.setPadding(new Insets(10));
        // vbl.setStyle("-fx-background-color: #271539;;-fx-background-radius: 20;");

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
        
        HBox vbl = new HBox(550,backButton,title);
       // vbl.setAlignment(Pos.TOP_CENTER);
        vbl.setPadding(new Insets(10));
        vbl.setStyle("-fx-background-color: #271539;;-fx-background-radius: 20;");

        TextField searchField = new TextField();
        searchField.setPromptText("Search...");
        searchField.setPrefWidth(750);
        searchField.setPrefHeight(30);
        searchField.setFocusTraversable(false);

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);"
                + "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        searchButton.setFont(new Font(12));

        Button bookmarked = new Button("BookMarked");
        bookmarked.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);"
                + "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        bookmarked.setFont(new Font(12));
        bookmarked.setOnAction(e -> BookMarked.showBookMarkedEvents(bookmarkedCards));

        HBox searchHBox = new HBox(10,searchField, searchButton, bookmarked);
        searchHBox.setPadding(new Insets(30, 10, 30, 80));
        searchHBox.setAlignment(Pos.TOP_LEFT);

        VBox topContainer = new VBox(vbl, searchHBox);

        TilePane tilePane = new TilePane();
        tilePane.setHgap(25);
        tilePane.setVgap(18);
        tilePane.setPadding(new Insets(40, 80, 50, 180));
        tilePane.setPrefColumns(3);
        tilePane.setStyle("-fx-background-color: #f7e8e8ff");

        ScrollPane scrollPane = new ScrollPane(tilePane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background-color:transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // ---- FETCH EVENTS FROM FIRESTORE ----
        fetchEventsFromFirestore(tilePane);

        // ---- SEARCH FUNCTIONALITY (Filter existing cards) ----
        searchButton.setOnAction(e -> {
            String query = searchField.getText().trim().toLowerCase();
            for (javafx.scene.Node node : tilePane.getChildren()) {
                if (node instanceof VBox) {
                    VBox card = (VBox) node;
                    // 2nd child is event title (first is image)
                    Text titleText = (Text) card.getChildren().get(1);
                    String eventTitle = titleText.getText().toLowerCase();
                    boolean matches = eventTitle.contains(query);
                    card.setVisible(matches);
                    card.setManaged(matches);
                }
            }
        });

        VBox vbmain = new VBox();
        border.setTop(topContainer);
        border.setCenter(scrollPane);
        vbmain.getChildren().add(border);
        vbmain.setStyle("-fx-background-color: #f7e8e8ff;");

        return vbmain;
    }

    private void fetchEventsFromFirestore(TilePane tilePane) {
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
            for (int i = 1; i < events.length; i++) {
                String block = events[i];

                String title = extractValue(block, "eventtitle");
                String desc = extractValue(block, "desc");
                String eventDate = extractValue(block, "eventdate");
                String start = extractValue(block, "startTime");
                String end = extractValue(block, "endTime");
                String ticket = extractValue(block, "eventTicket");
                String email = extractValue(block, "orgEmail");
                String contact = extractValue(block, "orgContact");
                String venue = extractValue(block, "venue");
                String price = extractValue(block, "eventprice");

                String imageUrl = extractValue(block, "imageUrl");
                if (imageUrl == null || imageUrl.isEmpty()) {
                    imageUrl = "https://images.unsplash.com/photo-1531058020387-3be344556be6?auto=format&fit=crop&w=400&h=240";
                }

                VBox card = createEventCard(title, eventDate, venue, price,
                        start, end, ticket, email, contact, imageUrl,desc);
                tilePane.getChildren().add(card);
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

    private VBox createEventCard(String title, String date, String location, String price,
                                 String start, String end, String ticket, String email, String contact, String imageUrl,String desc) {

        VBox card = new VBox(8);
        card.setPadding(new Insets(10));
        card.setPrefSize(260, 320);
        card.setStyle("-fx-background-color: white;-fx-background-radius : 10;");

        ImageView img = new ImageView(new Image(imageUrl, 200, 150, false, false));

        Text eventTitle = new Text(title);
        eventTitle.setStyle("-fx-font-weight:bold");

        Text eventDate = new Text("Date: " + date);
        Text loc = new Text("Venue: " + location);
        Text eventPrice = new Text("Price: " + price);
        Text eventTime = new Text("Time: " + start + " - " + end);
        Text ticketType = new Text("Ticket: " + ticket);
        Text orgEmail = new Text("Email: " + email);
        Text orgContact = new Text("Contact: " + contact);

        Button viewDetailbtn = new Button("View Details");
        viewDetailbtn.setStyle("-fx-background-color: #501f5a;-fx-text-fill: white;");
        viewDetailbtn.setCursor(Cursor.HAND);
        viewDetailbtn.setFont(new Font(12));
        viewDetailbtn.setOnAction(e -> {
            DetailPage.showDetailPage(title, date, location, price, start, end, ticket, email, contact, imageUrl,desc);
        });

        Button bookmarkBtn = new Button("🔖 Bookmark");
        bookmarkBtn.setStyle("-fx-background-color: #501f5a;-fx-text-fill: white;");
        bookmarkBtn.setFont(new Font(12));
        bookmarkBtn.setCursor(Cursor.HAND);
        bookmarkBtn.setOnAction(e -> {
            if (!bookmarkedCards.contains(card)) {
                bookmarkedCards.add(card);
               // BookMarked.showBookMarkedEvents(bookmarkedCards);
            }
        });

        card.getChildren().addAll(img, eventTitle, eventDate, loc, eventPrice, eventTime, ticketType, orgEmail, orgContact, viewDetailbtn, bookmarkBtn);
        return card;
    }

    public void showEventDetailPage(Stage stage) {
        VBox root = createEventDetailPageScene();
        Scene scene = new Scene(root, 1540, 795);
        stage.setScene(scene);
        stage.show();
    }
}
