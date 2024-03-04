package hospitalmanagementsystem;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Tools {
    public static void main(String[]args){

    }
    public static void animateLabel(Label label, String text, int duration) {
        int delay = duration / text.length();

        Timeline timeline = new Timeline();
        for (int i = 0; i < text.length(); i++) {
            int currentIndex = i;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(i * delay), event -> {
                label.setText(text.substring(0, currentIndex + 1));
            });
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.play();
    }


    public static void addSlideAnimation(Node node, int temps) {
        TranslateTransition slideTransition = new TranslateTransition(Duration.millis(temps), node);
        slideTransition.setFromX(node.getBoundsInParent().getWidth());
        slideTransition.setToX(0);
        slideTransition.play();
    }

    public static void addFadeAnimation(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2500), node);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

}
