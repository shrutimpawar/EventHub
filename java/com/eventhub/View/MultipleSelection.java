
package com.eventhub.View;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MultipleSelection {

    Scene MultipleSelectionScene;
    public void setMultipleScene(Scene multipleSelectionScene) {
        MultipleSelectionScene = multipleSelectionScene;
    }

    public void setMultipleStage(Stage multipleSelectionStage) {
        MultipleSelectionStage = multipleSelectionStage;
    }

    Stage MultipleSelectionStage;

    // Keep references to all toggle tiles
    private final ObservableList<ToggleButton> tiles = FXCollections.observableArrayList();

    
    public VBox createMultipleScene() {

        // Title
        Text title = new Text("Welcome! What are you interested in?");
        title.setFont(Font.font("Arial", 30));
        title.setStyle("-fx-font-weight: bold");

        Text subTitle = new Text("Select at least three interests to personalize your event recommendations.");
        subTitle.setFont(Font.font("Arial", 18));

        // Grid for tiles
        GridPane grid = new GridPane();
        grid.setHgap(22);
        grid.setVgap(22);
        grid.setAlignment(Pos.CENTER);

        // Add tiles (row, col)
        addTile(grid, 0, 0, createCategoryTile("Music", "#9b59b6"));
        addTile(grid, 0, 1, createCategoryTile("Dance", "#d2b4de"));
        addTile(grid, 0, 2, createCategoryTile("Social Awareness", "#9b59b6"));

        addTile(grid, 1, 0, createCategoryTile("College Festival", "#d2b4de"));
        addTile(grid, 1, 1, createCategoryTile("Sports", "#9b59b6"));
        addTile(grid, 1, 2, createCategoryTile("Fare", "#d2b4de"));

        addTile(grid, 2, 0, createCategoryTile("Festive Gathering", "#9b59b6"));
        addTile(grid, 2, 1, createCategoryTile("Exhibitions", "#d2b4de"));
        addTile(grid, 2, 2, createCategoryTile("Tech Conference", "#9b59b6"));

        // Buttons
        Button saveBtn = new Button("Save Preferences");
        saveBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");

        saveBtn.setDisable(true); 
        //saveBtn.setMaxWidth(100);
        saveBtn.setMaxHeight(100);// start disabled
        saveBtn.setOnAction(e -> {
    System.out.println("Selected events:");
    tiles.stream()
            .filter(ToggleButton::isSelected)
            .map(ToggleButton::getText)
            .forEach(System.out::println);

    // Create popup stage
    Stage popupStage = new Stage();
    popupStage.initOwner(MultipleSelectionStage);
    popupStage.setTitle("Preferences Saved");
    

    VBox popupContent = new VBox(15);
    popupContent.setPadding(new Insets(20));
    popupContent.setAlignment(Pos.CENTER);
    popupContent.setStyle("-fx-background-color: #dabea6ff");

    Text confirmationText = new Text("Your preferences have been saved!");
    confirmationText.setFont(Font.font("Arial", 18));
    Button okButton = new Button("OK");
    okButton.setStyle("-fx-background-color: #58235dff; -fx-text-fill: white; -fx-padding: 6 20;");
    
    okButton.setOnAction(event -> {
        popupStage.close();

        // After closing popup, navigate to HomePage or next scene if needed
        Homepage homeobj = new Homepage();
        homeobj.showHomePage(MultipleSelectionStage);
    });

    popupContent.getChildren().addAll(confirmationText, okButton);

    Scene popupScene = new Scene(popupContent, 350, 150);
    popupStage.setScene(popupScene);
    popupStage.show();
});

        

        Button skipBtn = new Button("Skip");
        skipBtn.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        skipBtn.setMaxWidth(100);
        skipBtn.setPrefWidth(150);

        //skipBtn.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10 24;");

        // Enable Save when at least 3 tiles selected
        saveBtn.disableProperty().bind(
                Bindings.createBooleanBinding(
                        () -> tiles.stream().filter(ToggleButton::isSelected).count() < 3,
                        tiles.stream().map(ToggleButton::selectedProperty)
                                .toArray(observable -> new javafx.beans.Observable[observable])));


        skipBtn.setOnAction(e -> {
            System.out.println("User skipped selection.");
             
            Homepage homeobj=new Homepage();
            homeobj.showHomePage(MultipleSelectionStage);
        });

            // TODO: navigate to another scene
        

        HBox buttonBox = new HBox(30, saveBtn, skipBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox mainVBox = new VBox(30, title, subTitle, grid, buttonBox);
        mainVBox.setPadding(new Insets(35));
        mainVBox.setAlignment(Pos.TOP_CENTER);
        mainVBox.setStyle("-fx-background-color: #f7e8e8ff;");

        Scene scene = new Scene(mainVBox, 1540, 795);
        MultipleSelectionStage.setScene(scene);
        MultipleSelectionStage.setTitle("Choice Selection Page");
        MultipleSelectionStage.show();

        VBox vb_main=new VBox(mainVBox);

        return vb_main;
    }

    /** Helper to add a tile to grid and to tiles list */
    private void addTile(GridPane grid, int row, int col, ToggleButton tile) {
        grid.add(tile, col, row);
        tiles.add(tile);
    }

    /**
     * Creates a ToggleButton styled as a rectangular rounded tile.
     * When selected it changes background + border.
     */
    private ToggleButton createCategoryTile(String label, String colorHex) {
        ToggleButton tile = new ToggleButton(label);
        tile.setPrefSize(200, 150);
        tile.setWrapText(true); // allow multi-line (e.g., Social Awareness)
        tile.setAlignment(Pos.CENTER);
        tile.setFont(Font.font("Arial", 16));
        tile.setTextFill(Color.WHITE);
        tile.setFocusTraversable(false); // remove focus ring unless desired

        // Base (unselected) style
        String baseStyle = """
                -fx-background-color: %s;
                -fx-background-radius: 18;
                -fx-border-radius: 18;
                -fx-border-color: transparent;
                -fx-font-weight: normal;
                -fx-cursor: hand;
                """.formatted(colorHex);

        // Selected style (brighter + border)
        String selectedStyle = """
                -fx-background-color: derive(%s, 20%%);
                -fx-background-radius: 18;
                -fx-border-radius: 18;
                -fx-border-color: black;
                -fx-border-width: 2;
                -fx-font-weight: bold;
                -fx-cursor: hand;
                """.formatted(colorHex);

        tile.setStyle(baseStyle);

        // Toggle styling
        tile.selectedProperty().addListener((obs, was, is) -> {
            if (is)
                tile.setStyle(selectedStyle);
            else
                tile.setStyle(baseStyle);
        });

        // Optional hover effect (only when not selected)
        tile.hoverProperty().addListener((obs, oldH, hovering) -> {
            if (!tile.isSelected()) {
                if (hovering)
                    tile.setStyle(baseStyle + "-fx-opacity: 0.9;");
                else
                    tile.setStyle(baseStyle);
            }
        });

        return tile;
       
    }
     

   

}
