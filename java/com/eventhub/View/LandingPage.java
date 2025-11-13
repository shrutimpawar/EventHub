package com.eventhub.View;

import javafx.util.Duration;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class LandingPage {

    public Scene createLandingScene() {
        Label title1 = new Label("🎉 EventHub 🎉");
        title1.setFont(Font.font("Arial", 36));
        title1.setTextFill(Color.web("#ffffff"));

        Image img = new Image("Assets\\Images\\Applogo.jpg");
        ImageView imView = new ImageView(img);
        imView.setFitWidth(200);
        imView.setPreserveRatio(true);
        Circle clip = new Circle(99, 99, 99);
        imView.setClip(clip);

        HBox header = new HBox(title1);
        header.setStyle("-fx-background-color: #2e074eff;");
        header.setPadding(new Insets(20));
        header.setAlignment(Pos.CENTER);

        Image bgImage = new Image("Assets\\Images\\bg.jpg");
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(1540);
        bgImageView.setFitHeight(795);
        bgImageView.setPreserveRatio(false);
        bgImageView.setOpacity(0.3);

        Label title = new Label("Discover Events Near You");
        title.setFont(Font.font("Arial", 50));
        title.setTextFill(Color.web("#222222"));

        Label subtitle = new Label("Book your tickets today.");
        subtitle.setFont(Font.font("Arial", 24));
        subtitle.setTextFill(Color.web("#333333"));

        Label intro = new Label(
            "EventHub connects you with exciting local events — from concerts and festivals to workshops and cultural shows.\n" +
            "Stay informed and never miss out on the experiences happening around you!"
        );
        intro.setFont(Font.font("Arial", 18));
        intro.setTextFill(Color.web("#444444"));
        intro.setWrapText(true);
        intro.setTextAlignment(TextAlignment.CENTER);
        intro.setMaxWidth(800);

        VBox mid = new VBox(imView);
        mid.setAlignment(Pos.TOP_CENTER);
        mid.setPadding(new Insets(75,10,10,10));

        VBox centerTextBox = new VBox(20, title, subtitle, intro);
        centerTextBox.setAlignment(Pos.CENTER);

        StackPane contentWithBg = new StackPane(bgImageView, mid,centerTextBox);
        contentWithBg.setPrefSize(1540, 755);

        VBox root = new VBox(header,contentWithBg);
        VBox.setVgrow(contentWithBg, Priority.ALWAYS);
        root.setPrefSize(1540, 795);

        // ------------------ Animations ------------------

        // Logo bounce-in
        imView.setOpacity(0);
        TranslateTransition bounce = new TranslateTransition(Duration.seconds(1.5), imView);
        bounce.setFromY(-200);
        bounce.setToY(0);
        bounce.setInterpolator(Interpolator.EASE_OUT);

        FadeTransition fadeLogo = new FadeTransition(Duration.seconds(1.5), imView);
        fadeLogo.setFromValue(0);
        fadeLogo.setToValue(1);

        ParallelTransition logoAnimation = new ParallelTransition(bounce, fadeLogo);
        logoAnimation.setDelay(Duration.seconds(0.2));
        logoAnimation.play();

        // Slide and fade each text label separately for staggering effect
        Duration baseDelay = Duration.seconds(1.0);

        applySlideFade(title, baseDelay.add(Duration.seconds(0)));
        applySlideFade(subtitle, baseDelay.add(Duration.seconds(0.3)));
        applySlideFade(intro, baseDelay.add(Duration.seconds(0.6)));

        // Pulse animation for the EventHub header
        ScaleTransition pulse = new ScaleTransition(Duration.seconds(2), title1);
        pulse.setFromX(1.0);
        pulse.setFromY(1.0);
        pulse.setToX(1.05);
        pulse.setToY(1.05);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setAutoReverse(true);
        pulse.play();

        // ------------------------------------------------

        return new Scene(root, 1540, 795);
    }

    private void applySlideFade(Label label, Duration delay) {
        label.setOpacity(0);
        TranslateTransition slide = new TranslateTransition(Duration.seconds(1), label);
        slide.setFromY(40);
        slide.setToY(0);
        slide.setInterpolator(Interpolator.EASE_OUT);

        FadeTransition fade = new FadeTransition(Duration.seconds(1), label);
        fade.setFromValue(0);
        fade.setToValue(1);

        ParallelTransition combo = new ParallelTransition(slide, fade);
        combo.setDelay(delay);
        combo.play();
 }
}

       