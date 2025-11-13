// package com.eventhub.View;

// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Label;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.stage.Stage;

// public class AdminDashboard extends Application {

//     @Override
//     public void start(Stage primaryStage) {
//         // Root container
//         VBox root = new VBox(30);
//         root.setPadding(new Insets(30));
//         root.setAlignment(Pos.TOP_CENTER);
//         root.setStyle("-fx-background-color: #f3e8ff;"); // Light purple

//         // Header
//         Label title = new Label("Admin Dashboard");
//         title.setFont(Font.font("Arial", 28));
//         title.setTextFill(Color.web("#6a0dad")); // Deep purple

//         // Dashboard cards
//         HBox statsBox = new HBox(30);
//         statsBox.setAlignment(Pos.CENTER);

//         // Total Users Card
//         VBox userCard = createStatCard("Total Users", "350");

//         // Total Organizers Card
//         VBox organizerCard = createStatCard("Total Organizers", "45");

//         // Average Feedback Rating Card
//         VBox ratingCard = createStatCard("Avg. Feedback Rating", "4.6 ★");

//         statsBox.getChildren().addAll(userCard, organizerCard, ratingCard);

//         root.getChildren().addAll(title, statsBox);

//         Scene scene = new Scene(root, 700, 400);
//         primaryStage.setTitle("Admin Dashboard");
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }

//     // Helper to create dashboard stat cards
//     private VBox createStatCard(String labelText, String valueText) {
//         VBox card = new VBox(10);
//         card.setPadding(new Insets(20));
//         card.setAlignment(Pos.CENTER);
//         card.setPrefSize(180, 120);
//         card.setStyle("-fx-background-color: #d8b4fe; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 5, 5);");

//         Label label = new Label(labelText);
//         label.setFont(Font.font("Arial", 16));
//         label.setTextFill(Color.web("#4b0082")); // Indigo

//         Label value = new Label(valueText);
//         value.setFont(Font.font("Arial", 24));
//         value.setTextFill(Color.web("#6a0dad")); // Deep purple

//         card.getChildren().addAll(label, value);
//         return card;
//     }

  
// }
package com.eventhub.View;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserManagementPageAPI extends Application {

    private final String PROJECT_ID = "event-hub-7033e";
    private final String API_KEY = "AIzaSyBRUq__-ukWR0ZgoZBfombRkHpd9G_MMWA";
    private TableView<User> tableView;
    private ObservableList<User> userList;

    @Override
    public void start(Stage primaryStage) {
        tableView = new TableView<>();
        userList = FXCollections.observableArrayList();

        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<User, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");

            {
                editBtn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    System.out.println("Edit user: " + user.getFullName());
                    // Open popup for edit if needed
                });

                deleteBtn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    deleteUserFromFirestore(user.getDocId());
                });

                HBox hBox = new HBox(5, editBtn, deleteBtn);
                hBox.setAlignment(Pos.CENTER);
                setGraphic(hBox);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : getGraphic());
            }
        });

        tableView.getColumns().addAll(nameCol, emailCol, roleCol, actionCol);

        VBox layout = new VBox(10, tableView);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 700, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("User Management (API)");
        primaryStage.show();

        fetchUsersFromFirestore();
    }

    private void fetchUsersFromFirestore() {
        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID +
                    "/databases/(default)/documents/users?key=" + API_KEY;
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) response.append(line);
            br.close();

            JSONObject json = new JSONObject(response.toString());
            JSONArray documents = json.getJSONArray("documents");

            userList.clear();
            for (int i = 0; i < documents.length(); i++) {
                JSONObject doc = documents.getJSONObject(i);
                String name = doc.getJSONObject("fields").getJSONObject("fullname").getString("stringValue");
                String email = doc.getJSONObject("fields").getJSONObject("email").getString("stringValue");
                String role = doc.getJSONObject("fields").getJSONObject("role").getString("stringValue");

                // Extract document ID from name (doc path)
                String docName = doc.getString("name");
                Pattern pattern = Pattern.compile(".*/(.*)$");
                Matcher matcher = pattern.matcher(docName);
                String docId = matcher.find() ? matcher.group(1) : "";

                userList.add(new User(name, email, role, docId));
            }
            tableView.setItems(userList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteUserFromFirestore(String docId) {
        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID +
                    "/databases/(default)/documents/users/" + docId + "?key=" + API_KEY;
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("DELETE");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Deleted user: " + docId);
                fetchUsersFromFirestore();
            } else {
                System.out.println("Failed to delete user. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class User {
        private String fullName;
        private String email;
        private String role;
        private String docId;

        public User(String fullName, String email, String role, String docId) {
            this.fullName = fullName;
            this.email = email;
            this.role = role;
            this.docId = docId;
        }

        public String getFullName() { return fullName; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public String getDocId() { return docId; }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
