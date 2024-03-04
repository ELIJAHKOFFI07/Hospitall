
package hospitalmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static hospitalmanagementsystem.EmailSender.PreparationEmail;

/**
 *
 * @author WINDOWS 10
 */
public class FXMLDocumentController implements Initializable {

    // LETS NAME ALL COMPONENTS WE HAVE ON ADMIN PAGE 
    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_username;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private Button login_loginBtn;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_email;

    @FXML
    private TextField register_username;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showPassword;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private Button register_signupBtn;

    @FXML
    private Hyperlink register_loginHere;

    @FXML
    private Hyperlink admin_forgot;

    @FXML
    private AnchorPane recup_admin_form;

    @FXML
    private Button adminrecup_btn;
    @FXML
    private TextField admin_email;
    @FXML
    private Hyperlink previous;



//    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();


    public void loginAccount() throws SQLException {

        if (login_username.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Username/Password");
        } else {

            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

            connect = Database.connectDB();

            try {

                if (!login_showPassword.isVisible()) {

                    System.out.println(login_password.getText());
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_showPassword.setText(login_password.getText());
                    }
                } else {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_password.setText(login_showPassword.getText());
                    }
                }

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    String psd=result.getString("admin_id");
                    System.out.println(result.getString("admin_id"));
                    // TO GET THE USERNAME
                    Data.admin_username = login_username.getText();
                    Data.admin_id = Integer.parseInt(result.getString("admin_id"));

                    // IF CORRECT USERNAME AND PASSWORD

                    alert.successMessage("Connexion reussie " +
                            "");

                    // LINK MAIN FORM FOR ADMIN
                    Parent root = FXMLLoader.load(getClass().getResource("AdminMainForm.fxml"));
                    Stage stage = new Stage();

                    stage.setTitle("Gestion de cabinet medical | Admin Portal");
                    stage.setOnCloseRequest(e -> {
                        System.exit(0);
                        // Mettez ici le code que vous souhaitez exécuter lors de la fermeture de la fenêtre
                    });

                    stage.setScene(new Scene(root));
                    stage.show();

                    // TO HIDE YOUR ADMIN PAGE (LOGIN FORM)
                    login_loginBtn.getScene().getWindow().hide();
                } else {
                    System.out.println(result.getString("admin_id"));
                    // IF WRONG USERNAME OR PASSWORD
                    alert.errorMessage("Incorrect Username/Password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            connect.close();// fermer la connexion


        }

    }
    @FXML
    private void restore_admin() throws SQLException {
        if (admin_email.getText().isEmpty()){
            alert.errorMessage("Veuillez entrer votre adresseEmail");

        }
        else{
            PreparationEmail("admin",admin_email.getText());

        }
    }




    public void loginShowPassword() {

        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }

    }

    public void registerAccount() throws SQLException {

        if (register_email.getText().isEmpty()
                || register_username.getText().isEmpty()
                || register_password.getText().isEmpty()) {
            // LETS CREATE OUR ALERT MESSAGE
            alert.errorMessage("Remplissez correctement les champs");
        } else {

            // WE WILL CHECK IF THE USERNAME THAT USER ENTERED IS ALREADY EXIST TO OUR DATABASE 
            String checkUsername = "SELECT * FROM admin WHERE username = '"
                    + register_username.getText() + "'";

            connect = Database.connectDB();

            try {

                if (!register_showPassword.isVisible()) {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_showPassword.setText(register_password.getText());
                    }
                } else {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_password.setText(register_showPassword.getText());
                    }
                }

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(register_username.getText() + " is already exist!");
                } else if (register_password.getText().length() < 5) { // CHECK IF THE CHARACTERS OF THE PASSWORD IS LESS THAN TO 5
                    alert.errorMessage("Mot de passe incorrect minimum : 5characters ");
                } else {
                    // TO INSERT THE DATA TO OUR DATABASE
                    String insertData = "INSERT INTO admin (email, username, password, date) VALUES(?,?,?,?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, register_email.getText());
                    prepare.setString(2, register_username.getText());
                    prepare.setString(3, register_password.getText());
                    prepare.setString(4, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Inscription reussie !");
                    registerClear();

                    // TO SWITCH THE FORM INTO LOGIN FORM
                    login_form.setVisible(true);
                    register_form.setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            connect.close();// fermer la connexion


        }

    }

    public void registerClear() {
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_showPassword.clear();
    }

    public void registerShowPassword() {

        if (register_checkBox.isSelected()) {
            register_showPassword.setText(register_password.getText());
            register_showPassword.setVisible(true);
            register_password.setVisible(false);
        } else {
            register_password.setText(register_showPassword.getText());
            register_showPassword.setVisible(false);
            register_password.setVisible(true);
        }

    }

    public void userList() {

        List<String> listU = new ArrayList<>();

        for (String data : Users.user) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    public void switchPage() {

        if (login_user.getSelectionModel().getSelectedItem() == "Admin") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Gestion de cabinet medical");

                stage.setMinWidth(340);
                stage.setMinHeight(580);
                stage.setResizable(false);

                stage.setScene(new Scene(root));
                stage.setOnCloseRequest(e -> {
                    System.exit(0);
                    // Mettez ici le code que vous souhaitez exécuter lors de la fermeture de la fenêtre
                });



                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Doctor") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("DoctorPage.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Gestion de cabinet medical");

                stage.setMinWidth(340);
                stage.setMinHeight(580);
                stage.setResizable(false);

                stage.setScene(new Scene(root));
                stage.setOnCloseRequest(e -> {
                    System.exit(0);
                    // Mettez ici le code que vous souhaitez exécuter lors de la fermeture de la fenêtre
                });

                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        login_user.getScene().getWindow().hide();

    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == login_registerHere) {
            // REGISTRATION FORM WILL SHOW
            login_form.setVisible(false);

            register_form.setVisible(true);
            recup_admin_form.setVisible(false);
        } else if (event.getSource() == register_loginHere) {
            // LOGIN FORM WILL SHOW
            login_form.setVisible(true);
            register_form.setVisible(false);

            recup_admin_form.setVisible(false);

        } else if (event.getSource()==admin_forgot) {

            login_form.setVisible(false);
            register_form.setVisible(false);
            recup_admin_form.setVisible(true);

        }
        if(event.getSource()==previous){
            login_form.setVisible(true);
            register_form.setVisible(false);

            recup_admin_form.setVisible(false);

        }

    }
    @FXML


    // WE'VE GOT AN ERROR BECAUSE WE DIDN'T IMPORT THE FONTAWESOME ICON JAR TO OUR LIBRARIES
    // NOW, LETS CREATE OUR DATABASE FOR OUR USERS
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userList();
    }

}
