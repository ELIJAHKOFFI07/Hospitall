/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static hospitalmanagementsystem.EmailSender.PreparationEmail;

/**
 *
 * @author WINDOWS 10
 */
public class DoctorPageController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_doctorID;

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
    private TextField register_fullName;

    @FXML
    private TextField register_email;

    @FXML
    private TextField register_doctorID;

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

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();
    @FXML
    private TextField doctor_email;
    @FXML
    private Button restoredoc_info;
    @FXML
    private AnchorPane recup_doctor_form;
    @FXML
    private Hyperlink doctor_forgot;

    @FXML
    private Hyperlink previous_doctor;
    protected  static boolean verify=false;

    @FXML
    void loginAccount() throws SQLException {

        if (login_doctorID.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Doctor ID/Password");
        } else {

            String sql = "SELECT * FROM doctor WHERE doctor_id = ? AND password = ? AND delete_date IS NULL";
            connect = Database.connectDB();

            try {

                if (!login_showPassword.isVisible()) {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_showPassword.setText(login_password.getText());
                    }
                } else {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_password.setText(login_showPassword.getText());
                    }
                }

                // CHECK IF THE STATUS OF THE DOCTOR IS CONFIRM 
                String checkStatus = "SELECT status FROM doctor WHERE doctor_id = '"
                        + login_doctorID.getText() + "' AND password = '"
                        + login_password.getText() + "' AND status = 'Confirm'";

                prepare = connect.prepareStatement(checkStatus);
                result = prepare.executeQuery();

                if (!result.next()) {

                    alert.errorMessage("L'administrateur doit confirmer!");
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, login_doctorID.getText());
                    prepare.setString(2, login_password.getText());

                    result = prepare.executeQuery();

                    if (result.next()) {
                        
                        Data.doctor_id = result.getString("doctor_id");
                        Data.doctor_name = result.getString("full_name");
                        
                        alert.successMessage("Connexion reussie!");

                        // LINK YOUR DOCTOR MAIN FORM
                        Parent root = FXMLLoader.load(getClass().getResource("DoctorMainForm.fxml"));
                        Stage stage = new Stage();
                        
                        stage.setTitle("Gestion de cabinet medical");
                        stage.setOnCloseRequest(e -> {
                            System.exit(0);
                            // Mettez ici le code que vous souhaitez exécuter lors de la fermeture de la fenêtre
                        });

                        stage.setScene(new Scene(root));
                        stage.show();
                        
                        // TO HIDE YOUR DOCTOR PAGE
                        login_loginBtn.getScene().getWindow().hide();
                        
                    } else {
                        alert.errorMessage("Incorrect Doctor ID/Password");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        connect.close();// fermer la connexion


    }

    @FXML
    void loginShowPassword() {

        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_password.setVisible(false);
            login_showPassword.setVisible(true);
        } else {
            login_password.setText(login_showPassword.getText());
            login_password.setVisible(true);
            login_showPassword.setVisible(false);
        }

    }

    @FXML
    void registerAccount() throws SQLException {

        if (register_fullName.getText().isEmpty()
                || register_email.getText().isEmpty()
                || register_doctorID.getText().isEmpty()
                || register_password.getText().isEmpty()) {
            alert.errorMessage("Remplissez les informations");
        } else {

            String checkDoctorID = "SELECT * FROM doctor WHERE doctor_id = '"
                    + register_doctorID.getText() + "'"; // LETS CREATE OUR TABLE FOR DOCTOR FIRST

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

                prepare = connect.prepareStatement(checkDoctorID);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(register_doctorID.getText() + " is already taken");
                } else if (register_password.getText().length() < 5) {
                    alert.errorMessage("Mot de passe trop court.");
                } else {

                    String insertData = "INSERT INTO doctor (full_name, email, doctor_id, password, date, status) "
                            + "VALUES(?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(1, register_fullName.getText());
                    prepare.setString(2, register_email.getText());
                    prepare.setString(3, register_doctorID.getText());
                    prepare.setString(4, register_password.getText());
                    prepare.setString(5, String.valueOf(sqlDate));
                    prepare.setString(6, "Confirm");

                    prepare.executeUpdate();

                    alert.successMessage("inscription reussie");


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        connect.close();// fermer la connexion

    }

    @FXML
    void registerShowPassword() {

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

    public void registerDoctorID() throws SQLException {
        String doctorID = "DID-";
        int tempID = 0;
        String sql = "SELECT MAX(id) FROM doctor";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempID = result.getInt("MAX(id)");
            }

            if (tempID == 0) {
                tempID += 1;
                doctorID += tempID;
            } else {
                doctorID += (tempID + 1);
            }

            register_doctorID.setText(doctorID);
            register_doctorID.setDisable(true);



        } catch (Exception e) {
            e.printStackTrace();
        }

        connect.close();// fermer la connexion



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

                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        login_user.getScene().getWindow().hide();

    }

    @FXML
    void switchForm(ActionEvent event) {

        if (event.getSource() == register_loginHere) {
            login_form.setVisible(true);
            register_form.setVisible(false);
            recup_doctor_form.setVisible(false);
        } else if (event.getSource() == login_registerHere ||verify) {
            login_form.setVisible(false);
            recup_doctor_form.setVisible(false);
            register_form.setVisible(true);
        }
        else if (event.getSource()==doctor_forgot) {
            recup_doctor_form.setVisible(true);
            login_form.setVisible(false);
            register_form.setVisible(false);


        }
        else if (event.getSource()==previous_doctor){
            login_form.setVisible(true);
            register_form.setVisible(false);
            recup_doctor_form.setVisible(false);

        }

    }
    @FXML
    private void restore_doctor() throws SQLException {
        if (doctor_email.getText().isEmpty()){
            alert.errorMessage("Veuillez entrer votre adresseEmail");

        }
        else{
            PreparationEmail("doctor",doctor_email.getText());

        }



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(verify){
            login_form.setVisible(false);
            recup_doctor_form.setVisible(false);
            register_form.setVisible(true);

        }
        try {
            registerDoctorID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        userList();
        System.out.println(verify);
    }


}
