package hospitalmanagementsystem;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animate {

    public static void addSlideAnimation(Node node, int temps) {
        TranslateTransition slideTransition = new TranslateTransition(Duration.millis(temps), node);
        slideTransition.setFromX(node.getBoundsInParent().getWidth());
        slideTransition.setToX(0);
        slideTransition.play();
    }

    public static void addFadeAnimation(Node node,int temps) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(temps), node);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
}
