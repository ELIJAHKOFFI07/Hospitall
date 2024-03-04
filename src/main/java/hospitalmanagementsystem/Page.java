package hospitalmanagementsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


import static hospitalmanagementsystem.Animate.addFadeAnimation;

public class Page {
    String url; Node fenetre;Boolean previous_hide;String new_window_name;


    public Page(String url,Node fenetre,String new_window_name,Boolean previous_hide){
        this.url=url;
        this.fenetre=fenetre;
        this.previous_hide=previous_hide;
        this.new_window_name=new_window_name;


    }
    public Page(String url,Node fenetre,String new_window_name){
        this.url=url;
        this.fenetre=fenetre;
        this.previous_hide=false;
        this.new_window_name=new_window_name;

    }



     void  load(){
        if (previous_hide){
         this.fenetre.getScene().getWindow().hide();}//pour supprimer l'ancien fenetre
         Stage home= new Stage();
         home.setMinWidth(340);
         home.setMinHeight(580);

         try {
             Parent fxml = FXMLLoader.load(getClass().getResource(url));
             AnchorPane container = new AnchorPane(fxml);
             addFadeAnimation(container,1100); // Ajoute l'animation de glissement
             Scene scene = new Scene(container);

             scene.setFill(Color.TRANSPARENT); // Rend le fond de la scène transparent (si vous utilisez StageStyle.TRANSPARENT)
             home.setScene(scene);
             if (new_window_name!=null){
                 home.setTitle(new_window_name);
             }
             home.show();



         } catch (IOException e) {

             e.printStackTrace();}


    }



    }






