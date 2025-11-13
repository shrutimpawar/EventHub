// package com.eventhub.View;

// import javafx.application.Application;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.chart.PieChart;
// import javafx.scene.control.*;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.stage.Stage;

// import org.json.JSONArray;
// import org.json.JSONObject;

// import java.io.*;
// import java.net.HttpURLConnection;
// import java.net.URL;

// public class AdminDashboard{

//     private final String PROJECT_ID = "eventhub-6ff68";
//     private final String API_KEY = "AIzaSyCHO01TTU6mn5ZveXUzNE4DmHsiscoPEm4";

//     private ObservableList<User> userList = FXCollections.observableArrayList();
//     private ObservableList<Organizer> organizerList = FXCollections.observableArrayList();
//     private ObservableList<Event> eventList = FXCollections.observableArrayList();

//     private Label usersCountLabel;
//     private Label organizersCountLabel;
//     private Label eventsCountLabel;
//     private TableView<User> userTable;
//     private TableView<Organizer> organizerTable;
//     private TableView<Event> eventTable;
//     private PieChart pieChart;
//     private TabPane tabPane; // **to switch tabs on sidebar click**

    
//     public void start(Stage stage) {
//         BorderPane root = new BorderPane();

//         // Create TabPane first so we can reference it in sidebar
//         tabPane = new TabPane();
//         Tab dashboardTab = new Tab("Dashboard", createDashboardTab());
//         Tab usersTab = new Tab("Users", createUserManagementTab());
//         Tab organizersTab = new Tab("Organizers", createOrganizerTab());
//         Tab eventsTab = new Tab("Events", createEventManagementTab());
//         dashboardTab.setClosable(false);
//         usersTab.setClosable(false);
//         organizersTab.setClosable(false);
//         eventsTab.setClosable(false);
//         tabPane.getTabs().addAll(dashboardTab, usersTab, organizersTab, eventsTab);

//         // Sidebar created after tabPane initialization
//         VBox sidebar = createSidebar(usersTab, organizersTab, eventsTab);
//         root.setLeft(sidebar);
//         root.setCenter(tabPane);

//         Scene scene = new Scene(root, 1540, 795);
//         scene.getRoot().setStyle("-fx-background-color: #f7e8e8ff;");
//         stage.setScene(scene);
//         stage.setTitle("Admin Dashboard");
//         stage.show();

//         fetchDashboardCounts();
//         fetchUsersFromFirestore();
//         fetchOrganizersFromFirestore();
//         fetchEventsFromFirestore();
//     }

//     // ------------------- Sidebar --------------------
//     private VBox createSidebar(Tab usersTab, Tab organizersTab, Tab eventsTab) {
//         VBox sidebar = new VBox(20);
//         sidebar.setPadding(new Insets(30));
//         sidebar.setPrefWidth(200);
//         sidebar.setStyle("-fx-background-color: #eec8c8ff;");

//         Label title = new Label("Admin Menu");
//         title.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");

//         Region underline=new Region();
//          underline.setBackground(
//                 new Background(new BackgroundFill(Color.web("#ff7fdf"), CornerRadii.EMPTY, Insets.EMPTY)));

//         Button usersBtn = new Button("Users");
//         usersBtn.setOnAction(e -> tabPane.getSelectionModel().select(usersTab));
//         usersBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
//                 + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
//                 "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
//         usersBtn.setFont(Font.font("Arial", 20));
//         usersBtn.setPrefWidth(150);

//         Button orgBtn = new Button("Organizers");
//         orgBtn.setOnAction(e -> tabPane.getSelectionModel().select(organizersTab));
//         orgBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
//                 + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
//                 "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
//         orgBtn.setFont(Font.font("Arial", 20));
//         orgBtn.setPrefWidth(150);

//         Button eventsBtn = new Button("Events");
//         eventsBtn.setOnAction(e -> tabPane.getSelectionModel().select(eventsTab));
//         eventsBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
//                 + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
//                 "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
//         eventsBtn.setFont(Font.font("Arial", 20));
//         eventsBtn.setPrefWidth(150);

//         Button logoutBtn = new Button("Logout");
//         //eventsBtn.setOnAction();
//         logoutBtn.setOnAction(e -> {
//         SignUpLogin signupLoginPage = new SignUpLogin();
//         Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
//         try {
//             signupLoginPage.start(currentStage); // Start the SignUpLogin page
//         } catch (Exception ex) {
//         ex.printStackTrace();
//     }
// });
//         logoutBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
//                 + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
//                 "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
//        logoutBtn.setFont(Font.font("Arial", 20));
//        logoutBtn.setPrefWidth(150);

//         sidebar.getChildren().addAll(title,underline, usersBtn, orgBtn, eventsBtn,logoutBtn);
//         sidebar.setAlignment(Pos.TOP_CENTER);
//         return sidebar;
//     }

//     // ---------------------- Dashboard ----------------------
//     private VBox createDashboardTab() {
//         usersCountLabel = new Label("Users: 0");
//         organizersCountLabel = new Label("Organizers: 0");
//         eventsCountLabel = new Label("Events: 0");

//         String cardStyle = "-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 12; "
//                 + "-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.2),10,0,0,5); -fx-font-size: 18; -fx-font-weight: bold;";
//         usersCountLabel.setStyle(cardStyle);
//         organizersCountLabel.setStyle(cardStyle);
//         eventsCountLabel.setStyle(cardStyle);

//         HBox statsBox = new HBox(30, usersCountLabel, organizersCountLabel, eventsCountLabel);
//         statsBox.setAlignment(Pos.CENTER);

//         pieChart = new PieChart();
//         pieChart.setTitle("System Overview");

//         VBox dashboard = new VBox(40, statsBox, pieChart);
//         dashboard.setPadding(new Insets(30));
//         dashboard.setAlignment(Pos.TOP_CENTER);
//         return dashboard;
//     }

//     private void updateChart(int users, int organizers, int events) {
//         pieChart.setData(FXCollections.observableArrayList(
//                 new PieChart.Data("Users", users),
//                 new PieChart.Data("Organizers", organizers),
//                 new PieChart.Data("Events", events)
//         ));
//     }

//     private void fetchDashboardCounts() {
//         try {
//             int users = getCountFromCollection("users");
//             int organizers = getCountFromCollection("organizer");
//             int events = getCountFromCollection("events");

//             usersCountLabel.setText("Users: " + users);
//             organizersCountLabel.setText("Organizers: " + organizers);
//             eventsCountLabel.setText("Events: " + events);
//             updateChart(users, organizers, events);
//         } catch (Exception e) { e.printStackTrace(); }
//     }

//     private int getCountFromCollection(String collection) throws Exception {
//         String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID +
//                 "/databases/(default)/documents/" + collection + "?key=" + API_KEY;
//         HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
//         conn.setRequestMethod("GET");
//         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//         StringBuilder response = new StringBuilder();
//         String line;
//         while ((line = br.readLine()) != null) response.append(line);
//         br.close();
//         JSONObject json = new JSONObject(response.toString());
//         JSONArray docs = json.has("documents") ? json.getJSONArray("documents") : new JSONArray();
//         return docs.length();
//     }

//     // ---------------------- Users ----------------------
//     private VBox createUserManagementTab() {
//         userTable = new TableView<>();
//         TableColumn<User, String> nameCol = new TableColumn<>("Name");
//         nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
//         TableColumn<User, String> emailCol = new TableColumn<>("Email");
//         emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
//         TableColumn<User, String> roleCol = new TableColumn<>("Role");
//         roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

//         TableColumn<User, Void> actionCol = new TableColumn<>("Actions");
//         actionCol.setCellFactory(param -> new TableCell<>() {
//             private final Button deleteBtn = new Button("Delete");
//             { deleteBtn.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");
//                 deleteBtn.setOnAction(event -> {
//                     User u = getTableView().getItems().get(getIndex());
//                     deleteUserFromFirestore(u.getDocId());
//                 });
//             }
//             @Override protected void updateItem(Void item, boolean empty) {
//                 super.updateItem(item, empty);
//                 setGraphic(empty ? null : deleteBtn);
//             }
//         });
//         userTable.getColumns().addAll(nameCol, emailCol, roleCol, actionCol);
//         VBox layout = new VBox(10, new Label("Users List"), userTable);
//         layout.setPadding(new Insets(20));
//         return layout;
//     }

//     private void fetchUsersFromFirestore() {
//         try {
//             String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID +
//                     "/databases/(default)/documents/users?key=" + API_KEY;
//             HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
//             conn.setRequestMethod("GET");
//             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//             StringBuilder response = new StringBuilder();
//             String line;
//             while ((line = br.readLine()) != null) response.append(line);
//             br.close();
//             JSONObject json = new JSONObject(response.toString());
//             JSONArray documents = json.has("documents") ? json.getJSONArray("documents") : new JSONArray();
//             userList.clear();
//             for (int i = 0; i < documents.length(); i++) {
//                 JSONObject doc = documents.getJSONObject(i);
//                 JSONObject fields = doc.getJSONObject("fields");
//                 String name = fields.has("name") ? fields.getJSONObject("name").getString("stringValue") : "No Name";
//                 String email = fields.has("email") ? fields.getJSONObject("email").getString("stringValue") : "No Email";
//                 String role = fields.has("role") ? fields.getJSONObject("role").getString("stringValue") : "No Role";
//                 String docId = doc.getString("name").substring(doc.getString("name").lastIndexOf("/") + 1);
//                 userList.add(new User(name, email, role, docId));
//             }
//             userTable.setItems(userList);
//         } catch (Exception e) { e.printStackTrace(); }
//     }
//     private void deleteUserFromFirestore(String docId) {
//         try {
//             String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID +
//                     "/databases/(default)/documents/users/" + docId + "?key=" + API_KEY;
//             HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
//             conn.setRequestMethod("DELETE");
//             if (conn.getResponseCode() == 200) fetchUsersFromFirestore();
//         } catch (Exception e) { e.printStackTrace(); }
//     }

//     // ---------------------- Organizers ----------------------
//     private VBox createOrganizerTab() {
//         organizerTable = new TableView<>();
//         TableColumn<Organizer, String> nameCol = new TableColumn<>("Name");
//         nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//         TableColumn<Organizer, String> emailCol = new TableColumn<>("Email");
//         emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
//         TableColumn<Organizer, String> usernameCol = new TableColumn<>("Username");
//         usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
//         TableColumn<Organizer, String> roleCol = new TableColumn<>("Role");
//         roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

//         TableColumn<Organizer, Void> actionCol = new TableColumn<>("Actions");
//         actionCol.setCellFactory(param -> new TableCell<>() {
//             private final Button deleteBtn = new Button("Delete");
//             { deleteBtn.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");
//                 deleteBtn.setOnAction(event -> {
//                     Organizer o = getTableView().getItems().get(getIndex());
//                     deleteOrganizerFromFirestore(o.getDocId());
//                 });
//             }
//             @Override protected void updateItem(Void item, boolean empty) {
//                 super.updateItem(item, empty);
//                 setGraphic(empty ? null : deleteBtn);
//             }
//         });
//         organizerTable.getColumns().addAll(nameCol, emailCol, usernameCol, roleCol, actionCol);
//         VBox layout = new VBox(10, new Label("Organizers List"), organizerTable);
//         layout.setPadding(new Insets(20));
//         return layout;
//     }

//     private void fetchOrganizersFromFirestore() {
//         try {
//             String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID +
//                     "/databases/(default)/documents/organizer?key=" + API_KEY;
//             HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
//             conn.setRequestMethod("GET");
//             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//             StringBuilder response = new StringBuilder();
//             String line;
//             while ((line = br.readLine()) != null) response.append(line);
//             br.close();
//             JSONObject json = new JSONObject(response.toString());
//             JSONArray documents = json.has("documents") ? json.getJSONArray("documents") : new JSONArray();
//             organizerList.clear();
//             for (int i = 0; i < documents.length(); i++) {
//                 JSONObject doc = documents.getJSONObject(i);
//                 JSONObject fields = doc.getJSONObject("fields");
//                 String name = fields.has("name") ? fields.getJSONObject("name").getString("stringValue") : "No Name";
//                 String email = fields.has("email") ? fields.getJSONObject("email").getString("stringValue") : "No Email";
//                 String username = fields.has("username") ? fields.getJSONObject("username").getString("stringValue") : "No Username";
//                 String role = fields.has("role") ? fields.getJSONObject("role").getString("stringValue") : "Organizer";
//                 String docId = doc.getString("name").substring(doc.getString("name").lastIndexOf("/") + 1);
//                 organizerList.add(new Organizer(name, email, username, role, docId));
//             }
//             organizerTable.setItems(organizerList);
//         } catch (Exception e) { e.printStackTrace(); }
//     }
//     private void deleteOrganizerFromFirestore(String docId) {
//         try {
//             String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID +
//                     "/databases/(default)/documents/organizer/" + docId + "?key=" + API_KEY;
//             HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
//             conn.setRequestMethod("DELETE");
//             if (conn.getResponseCode() == 200) fetchOrganizersFromFirestore();
//         } catch (Exception e) { e.printStackTrace(); }
//     }

//     // ---------------------- Events ----------------------
//     private VBox createEventManagementTab() {
//         eventTable = new TableView<>();
//         TableColumn<Event, String> nameCol = new TableColumn<>("Event Name");
//         nameCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));
//         TableColumn<Event, String> dateCol = new TableColumn<>("Date");
//         dateCol.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
//         TableColumn<Event, String> locationCol = new TableColumn<>("Location");
//         locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

//         TableColumn<Event, Void> actionCol = new TableColumn<>("Actions");
//         actionCol.setCellFactory(param -> new TableCell<>() {
//             private final Button deleteBtn = new Button("Delete");
//             { deleteBtn.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");
//                 deleteBtn.setOnAction(event -> {
//                     Event e = getTableView().getItems().get(getIndex());
//                     deleteEventFromFirestore(e.getDocId());
//                 });
//             }
//             @Override protected void updateItem(Void item, boolean empty) {
//                 super.updateItem(item, empty);
//                 setGraphic(empty ? null : deleteBtn);
//             }
//         });
//         eventTable.getColumns().addAll(nameCol, dateCol, locationCol, actionCol);
//         VBox layout = new VBox(10, new Label("Events List"), eventTable);
//         layout.setPadding(new Insets(20));
//         return layout;
//     }

//     private void fetchEventsFromFirestore() {
//         try {
//             String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID +
//                     "/databases/(default)/documents/events?key=" + API_KEY;
//             HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
//             conn.setRequestMethod("GET");
//             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//             StringBuilder response = new StringBuilder();
//             String line;
//             while ((line = br.readLine()) != null) response.append(line);
//             br.close();
//             JSONObject json = new JSONObject(response.toString());
//             JSONArray documents = json.has("documents") ? json.getJSONArray("documents") : new JSONArray();
//             eventList.clear();
//             for (int i = 0; i < documents.length(); i++) {
//                 JSONObject doc = documents.getJSONObject(i);
//                 JSONObject fields = doc.getJSONObject("fields");
//                 String name = fields.has("eventtitle") ? fields.getJSONObject("eventName").getString("stringValue")
//                         : fields.has("eventtitle") ? fields.getJSONObject("eventtitle").getString("stringValue") : "Unnamed";
//                 String date = fields.has("eventdate") ? fields.getJSONObject("eventDate").getString("stringValue")
//                         : fields.has("eventdate") ? fields.getJSONObject("eventdate").getString("stringValue") : "No Date";
//                 String location = fields.has("venue") ? fields.getJSONObject("location").getString("stringValue")
//                         : fields.has("venue") ? fields.getJSONObject("venue").getString("stringValue") : "No Location";
//                 String docId = doc.getString("name").substring(doc.getString("name").lastIndexOf("/") + 1);
//                 eventList.add(new Event(name, date, location, docId));
//             }
//             eventTable.setItems(eventList);
//         } catch (Exception e) { e.printStackTrace(); }
//     }
//     private void deleteEventFromFirestore(String docId) {
//         try {
//             String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID +
//                     "/databases/(default)/documents/events/" + docId + "?key=" + API_KEY;
//             HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
//             conn.setRequestMethod("DELETE");
//             if (conn.getResponseCode() == 200) fetchEventsFromFirestore();
//         } catch (Exception e) { e.printStackTrace(); }
//     }

//     // ---------------------- Models ----------------------
//     public static class User {
//         private String name, email, role, docId;
//         public User(String name, String email, String role, String docId) { this.name = name; this.email = email; this.role = role; this.docId = docId; }
//         public String getFullName() { return name; }
//         public String getEmail() { return email; }
//         public String getRole() { return role; }
//         public String getDocId() { return docId; }
//     }
//     public static class Organizer {
//         private String name, email, username, role, docId;
//         public Organizer(String name, String email, String username, String role, String docId) { this.name = name; this.email = email; this.username = username; this.role = role; this.docId = docId; }
//         public String getName() { return name; }
//         public String getEmail() { return email; }
//         public String getUsername() { return username; }
//         public String getRole() { return role; }
//         public String getDocId() { return docId; }
//     }
//     public static class Event {
//         private String eventName, eventDate, location, docId;
//         public Event(String eventName, String eventDate, String location, String docId) { this.eventName = eventName; this.eventDate = eventDate; this.location = location; this.docId = docId; }
//         public String getEventName() { return eventName; }
//         public String getEventDate() { return eventDate; }
//         public String getLocation() { return location; }
//         public String getDocId() { return docId; }
//     }


// }
package com.eventhub.View;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdminDashboard {

    private final String PROJECT_ID = "event-hub-7033e";
    private final String API_KEY = "AIzaSyBRUq__-ukWR0ZgoZBfombRkHpd9G_MMWA";

    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<Organizer> organizerList = FXCollections.observableArrayList();
    private ObservableList<Event> eventList = FXCollections.observableArrayList();

    private Label usersCountLabel;
    private Label organizersCountLabel;
    private Label eventsCountLabel;
    private TableView<User> userTable;
    private TableView<Organizer> organizerTable;
    private TableView<Event> eventTable;
    private PieChart pieChart;
    private TabPane tabPane;

    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        tabPane = new TabPane();
        Tab dashboardTab = new Tab("Dashboard", createDashboardTab());
        Tab usersTab = new Tab("Users", createUserManagementTab());
        Tab organizersTab = new Tab("Organizers", createOrganizerTab());
        Tab eventsTab = new Tab("Events", createEventManagementTab());
        dashboardTab.setClosable(false);
        usersTab.setClosable(false);
        organizersTab.setClosable(false);
        eventsTab.setClosable(false);
        tabPane.getTabs().addAll(dashboardTab, usersTab, organizersTab, eventsTab);

        VBox sidebar = createSidebar(usersTab, organizersTab, eventsTab);
        root.setLeft(sidebar);
        root.setCenter(tabPane);

        Scene scene = new Scene(root, 1540, 795);
        scene.getRoot().setStyle("-fx-background-color: #f7e8e8ff;");
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard");
        stage.show();

        fetchDashboardCounts();
        fetchUsersFromFirestore();
        fetchOrganizersFromFirestore();
        fetchEventsFromFirestore();
    }

    private VBox createSidebar(Tab usersTab, Tab organizersTab, Tab eventsTab) {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(30));
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #eec8c8ff;");

        Label title = new Label("Admin Menu");
        title.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");

        Region underline = new Region();
        underline.setBackground(new Background(new BackgroundFill(Color.web("#ff7fdf"), CornerRadii.EMPTY, Insets.EMPTY)));

        Button usersBtn = new Button("Users");
        usersBtn.setOnAction(e -> tabPane.getSelectionModel().select(usersTab));
        usersBtn.setStyle("-fx-background-radius: 30; -fx-padding: 8 20; -fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff); -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold;");
        usersBtn.setFont(Font.font("Arial", 20));
        usersBtn.setPrefWidth(150);

        Button orgBtn = new Button("Organizers");
        orgBtn.setOnAction(e -> tabPane.getSelectionModel().select(organizersTab));
        orgBtn.setStyle("-fx-background-radius: 30; -fx-padding: 8 20; -fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff); -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold;");
        orgBtn.setFont(Font.font("Arial", 20));
        orgBtn.setPrefWidth(150);

        Button eventsBtn = new Button("Events");
        eventsBtn.setOnAction(e -> tabPane.getSelectionModel().select(eventsTab));
        eventsBtn.setStyle("-fx-background-radius: 30; -fx-padding: 8 20; -fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff); -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold;");
        eventsBtn.setFont(Font.font("Arial", 20));
        eventsBtn.setPrefWidth(150);

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> {
            SignUpLogin signupLoginPage = new SignUpLogin();
            Stage currentStage = (Stage) ((Button) e.getSource()).getScene().getWindow();
            try {
                signupLoginPage.start(currentStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        logoutBtn.setStyle("-fx-background-radius: 30; -fx-padding: 8 20; -fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff); -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold;");
        logoutBtn.setFont(Font.font("Arial", 20));
        logoutBtn.setPrefWidth(150);

        sidebar.getChildren().addAll(title, underline, usersBtn, orgBtn, eventsBtn, logoutBtn);
        sidebar.setAlignment(Pos.TOP_CENTER);
        return sidebar;
    }

    private VBox createDashboardTab() {
        usersCountLabel = new Label("Users: 0");
        organizersCountLabel = new Label("Organizers: 0");
        eventsCountLabel = new Label("Events: 0");

        String cardStyle = "-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian,rgba(0,0,0,0.2),10,0,0,5); -fx-font-size: 18; -fx-font-weight: bold;";
        usersCountLabel.setStyle(cardStyle);
        organizersCountLabel.setStyle(cardStyle);
        eventsCountLabel.setStyle(cardStyle);

        HBox statsBox = new HBox(30, usersCountLabel, organizersCountLabel, eventsCountLabel);
        statsBox.setAlignment(Pos.CENTER);

        pieChart = new PieChart();
        pieChart.setTitle("System Overview");

        VBox dashboard = new VBox(40, statsBox, pieChart);
        dashboard.setPadding(new Insets(30));
        dashboard.setAlignment(Pos.TOP_CENTER);
        return dashboard;
    }

    private void updateChart(int users, int organizers, int events) {
        pieChart.setData(FXCollections.observableArrayList(
                new PieChart.Data("Users", users),
                new PieChart.Data("Organizers", organizers),
                new PieChart.Data("Events", events)
        ));
    }

    private void fetchDashboardCounts() {
        try {
            int users = getCountFromCollection("users");
            int organizers = getCountFromCollection("organizer");
            int events = getCountFromCollection("events");

            usersCountLabel.setText("Users: " + users);
            organizersCountLabel.setText("Organizers: " + organizers);
            eventsCountLabel.setText("Events: " + events);
            updateChart(users, organizers, events);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private int getCountFromCollection(String collection) throws Exception {
        String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/" + collection + "?key=" + API_KEY;
        HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
        conn.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) response.append(line);
        br.close();
        JSONObject json = new JSONObject(response.toString());
        JSONArray docs = json.has("documents") ? json.getJSONArray("documents") : new JSONArray();
        return docs.length();
    }

    private VBox createUserManagementTab() {
        userTable = new TableView<>();
        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<User, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<User, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteBtn = new Button("Delete");
            { deleteBtn.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteBtn.setOnAction(event -> {
                    User u = getTableView().getItems().get(getIndex());
                    deleteUserFromFirestore(u.getDocId());
                });
            }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteBtn);
            }
        });
        userTable.getColumns().addAll(nameCol, emailCol, roleCol, actionCol);
        VBox layout = new VBox(10, new Label("Users List"), userTable);
        layout.setPadding(new Insets(20));
        return layout;
    }

    private void fetchUsersFromFirestore() {
        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/users?key=" + API_KEY;
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) response.append(line);
            br.close();
            JSONObject json = new JSONObject(response.toString());
            JSONArray documents = json.has("documents") ? json.getJSONArray("documents") : new JSONArray();
            userList.clear();
            for (int i = 0; i < documents.length(); i++) {
                JSONObject doc = documents.getJSONObject(i);
                JSONObject fields = doc.getJSONObject("fields");
                String name = fields.has("name") ? fields.getJSONObject("name").getString("stringValue") : "No Name";
                String email = fields.has("email") ? fields.getJSONObject("email").getString("stringValue") : "No Email";
                String role = fields.has("role") ? fields.getJSONObject("role").getString("stringValue") : "No Role";
                String docId = doc.getString("name").substring(doc.getString("name").lastIndexOf("/") + 1);
                userList.add(new User(name, email, role, docId));
            }
            userTable.setItems(userList);
        } catch (Exception e) { e.printStackTrace(); }
    }
    private void deleteUserFromFirestore(String docId) {
        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/users/" + docId + "?key=" + API_KEY;
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("DELETE");
            if (conn.getResponseCode() == 200) fetchUsersFromFirestore();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private VBox createOrganizerTab() {
        organizerTable = new TableView<>();
        TableColumn<Organizer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Organizer, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Organizer, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<Organizer, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<Organizer, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteBtn = new Button("Delete");
            { deleteBtn.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteBtn.setOnAction(event -> {
                    Organizer o = getTableView().getItems().get(getIndex());
                    deleteOrganizerFromFirestore(o.getDocId());
                });
            }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteBtn);
            }
        });
        organizerTable.getColumns().addAll(nameCol, emailCol, usernameCol, roleCol, actionCol);
        VBox layout = new VBox(10, new Label("Organizers List"), organizerTable);
        layout.setPadding(new Insets(20));
        return layout;
    }

    private void fetchOrganizersFromFirestore() {
        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/organizer?key=" + API_KEY;
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) response.append(line);
            br.close();
            JSONObject json = new JSONObject(response.toString());
            JSONArray documents = json.has("documents") ? json.getJSONArray("documents") : new JSONArray();
            organizerList.clear();
            for (int i = 0; i < documents.length(); i++) {
                JSONObject doc = documents.getJSONObject(i);
                JSONObject fields = doc.getJSONObject("fields");
                String name = fields.has("name") ? fields.getJSONObject("name").getString("stringValue") : "No Name";
                String email = fields.has("email") ? fields.getJSONObject("email").getString("stringValue") : "No Email";
                String username = fields.has("username") ? fields.getJSONObject("username").getString("stringValue") : "No Username";
                String role = fields.has("role") ? fields.getJSONObject("role").getString("stringValue") : "Organizer";
                String docId = doc.getString("name").substring(doc.getString("name").lastIndexOf("/") + 1);
                organizerList.add(new Organizer(name, email, username, role, docId));
            }
            organizerTable.setItems(organizerList);
        } catch (Exception e) { e.printStackTrace(); }
    }
    private void deleteOrganizerFromFirestore(String docId) {
        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/organizer/" + docId + "?key=" + API_KEY;
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("DELETE");
            if (conn.getResponseCode() == 200) fetchOrganizersFromFirestore();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private VBox createEventManagementTab() {
        eventTable = new TableView<>();
        TableColumn<Event, String> nameCol = new TableColumn<>("Event Title");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("eventTitle"));
        TableColumn<Event, String> dateCol = new TableColumn<>("Event Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        TableColumn<Event, String> venueCol = new TableColumn<>("Venue");
        venueCol.setCellValueFactory(new PropertyValueFactory<>("venue"));

        TableColumn<Event, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteBtn = new Button("Delete");
            { deleteBtn.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteBtn.setOnAction(event -> {
                    Event e = getTableView().getItems().get(getIndex());
                    deleteEventFromFirestore(e.getDocId());
                });
            }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteBtn);
            }
        });
        eventTable.getColumns().addAll(nameCol, dateCol, venueCol, actionCol);
        VBox layout = new VBox(10, new Label("Events List"), eventTable);
        layout.setPadding(new Insets(20));
        return layout;
    }

    private void fetchEventsFromFirestore() {
        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/events?key=" + API_KEY;
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            System.out.println("Events Response: " + response);

            String line;
            while ((line = br.readLine()) != null) response.append(line);
            br.close();
            JSONObject json = new JSONObject(response.toString());
            JSONArray documents = json.has("documents") ? json.getJSONArray("documents") : new JSONArray();
            eventList.clear();
            for (int i = 0; i < documents.length(); i++) {
                JSONObject doc = documents.getJSONObject(i);
                JSONObject fields = doc.getJSONObject("fields");
                String eventTitle = fields.has("eventtitle") ? fields.getJSONObject("eventtitle").getString("stringValue") : "Unnamed";
String eventDate = fields.has("eventdate") ? fields.getJSONObject("eventdate").getString("stringValue") : "No Date";
String venue = fields.has("venue") ? fields.getJSONObject("venue").getString("stringValue") : "No Venue";
String docId = doc.getString("name").substring(doc.getString("name").lastIndexOf("/") + 1);
eventList.add(new Event(eventTitle, eventDate, venue, docId));

            }
            eventTable.setItems(eventList);
        } catch (Exception e) { e.printStackTrace(); }
    }
    private void deleteEventFromFirestore(String docId) {
        try {
            String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/events/" + docId + "?key=" + API_KEY;
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("DELETE");
            if (conn.getResponseCode() == 200) fetchEventsFromFirestore();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static class User {
        private String name, email, role, docId;
        public User(String name, String email, String role, String docId) { this.name = name; this.email = email; this.role = role; this.docId = docId; }
        public String getFullName() { return name; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public String getDocId() { return docId; }
    }
    public static class Organizer {
        private String name, email, username, role, docId;
        public Organizer(String name, String email, String username, String role, String docId) { this.name = name; this.email = email; this.username = username; this.role = role; this.docId = docId; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getUsername() { return username; }
        public String getRole() { return role; }
        public String getDocId() { return docId; }
    }
    public static class Event {
        private String eventTitle, eventDate, venue, docId;
        public Event(String eventTitle, String eventDate, String venue, String docId) { this.eventTitle = eventTitle; this.eventDate = eventDate; this.venue = venue; this.docId = docId; }
        public String getEventTitle() { return eventTitle; }
        public String getEventDate() { return eventDate; }
        public String getVenue() { return venue; }
        public String getDocId() { return docId; }
    }
}
