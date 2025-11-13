package com.eventhub.View;



import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookMarked {

    public static void showBookMarkedEvents(List<VBox> bookmarkedCards){

        Stage myStage = new Stage();
        myStage.setTitle("BookMarked Events");

        VBox vb = new VBox(20);
        vb.setPadding(new Insets(20));
        //vb.setStyle("-fx-background-color:grey");
        vb.setStyle("-fx-background-color: rgba(128,128,128,0.3); -fx-background-radius: 10;");
        vb.setAlignment(Pos.TOP_CENTER);

        Text heading = new Text("Bookmarked Events");
        heading.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-fill: #333;");

        HBox headingBox = new HBox(heading);
        headingBox.setAlignment(Pos.TOP_CENTER);
       
        // if (bookmarkedCards.isEmpty()) {
            
        //     Text msg = new Text("No Bookmarked Events.");
        //     msg.setStyle("-fx-font-size:18px;-fx-fill:grey;");

        //     VBox emptyBox = new VBox(msg);
        //     emptyBox.setAlignment(Pos.CENTER);
        //     emptyBox.setPadding(new Insets(100));
        //     Scene emptyscene = new Scene(emptyBox,1540,795);
        //     myStage.setScene(emptyscene);
        //     myStage.show();
        //     return;
        // }

        
        if (bookmarkedCards.isEmpty()) {
            Stage popup = new Stage();
            popup.initOwner(myStage);
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initStyle(StageStyle.UNDECORATED);


            Text msg = new Text("No bookmarked events to show.");
            msg.setStyle("-fx-font-size: 18px; -fx-fill: #333333;");

            Button ok = new Button("OK");
            ok.setStyle("-fx-background-color: #ff7f50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
            ok.setOnAction(e -> popup.close());

            HBox row = new HBox(15, msg);
            row.setAlignment(Pos.CENTER);

            VBox vbPop = new VBox(20, row, ok);
            vbPop.setAlignment(Pos.CENTER);
            vbPop.setPadding(new Insets(30));
            vbPop.setStyle("-fx-background-color: skyblue; -fx-background-radius: 15; -fx-border-color: #ccc; -fx-border-radius: 15;");

            Scene popupScene = new Scene(vbPop);
            popup.setScene(popupScene);
            popup.showAndWait();
            return;
        }

        for (VBox Ocard : bookmarkedCards) {
            
            HBox card = new HBox(20);
            card.setPadding(new Insets(15));
            card.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
            card.setAlignment(Pos.CENTER_LEFT);
            card.setPrefWidth(700);

            ImageView img = new ImageView(((ImageView) Ocard.getChildren().get(0)).getImage());
            img.setFitWidth(150);
            img.setFitHeight(100);
            img.setPreserveRatio(false);

            Text title = new Text(((Text) Ocard.getChildren().get(1)).getText());
            title.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

            Text date = new Text(((Text) Ocard.getChildren().get(2)).getText());
            Text loc = new Text(((Text) Ocard.getChildren().get(3)).getText());
            Button deleteButton = new Button("Remove");
            deleteButton.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
            deleteButton.setPrefWidth(150);

            deleteButton.setCursor(Cursor.HAND);

            deleteButton.setOnAction(e -> {

                vb.getChildren().remove(card);
                bookmarkedCards.remove(Ocard);
     });

            //Button viewDetailsButton = new Button("View Details");
            //viewDetailsButton.setStyle("-fx-background-color: #4CAF50;-fx-text-fill: white; -fx-font-weight: bold; ");
            //viewDetailsButton.setCursor(Cursor.HAND);

            // viewDetailsButton.setOnAction(e -> {

            //     System.out.println("Viewing details for: " + title.getText());
            //     DetailPage.showDetailPage();
            // });



            VBox textBox = new VBox(5, title, date, loc,deleteButton);
            card.getChildren().addAll(img, textBox);
            vb.getChildren().add(card);
        }

        ScrollPane scrollPane = new ScrollPane(vb);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: #f7e8e8ff;");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> myStage.close());
        closeButton.setStyle("-fx-background-radius: 30;" + "-fx-padding: 8 20;"
                + "-fx-background-color: linear-gradient(to right, #0f4646ff, #f042ff);" +
                "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
        closeButton.setMaxWidth(150);

        closeButton.setPadding(new Insets(10, 20, 10, 20));


        VBox vbmain = new VBox(10,headingBox,scrollPane,closeButton);
        vbmain.setStyle("#f7e8e8ff;");
        vbmain.setAlignment(Pos.TOP_CENTER);
        vbmain.setPadding(new Insets(20));

        Scene scene = new Scene(vbmain, 1540, 795);
        myStage.setScene(scene);
        myStage.show();
   }
}
