/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author WINDOWS 10
 */
public class EditPatientFormController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField edit_patientID;

    @FXML
    private TextField edit_name;

    @FXML
    private ComboBox<String> edit_gender;

    @FXML
    private TextField edit_contactNumber;

    @FXML
    private TextArea edit_address;
    @FXML
    private ImageView editPatient_imageView;
    @FXML
    private Button editPatient_importBtn;

    @FXML
    private ComboBox<String> edit_status;

    @FXML
    private Button edit_updateBtn;
    private Image image;

    private AlertMessage alert = new AlertMessage();

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void updateBtn() throws SQLException {
        connect = Database.connectDB();

        if (edit_patientID.getText().isEmpty() || edit_name.getText().isEmpty()
                || edit_gender.getSelectionModel().getSelectedItem() == null
                || edit_contactNumber.getText().isEmpty()
                || edit_address.getText().isEmpty()
                || edit_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Remplissez tous les champs");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE patient SET full_name = ?, gender = ?"
                        + ", mobile_number = ?, address = ?, status = ?, date_modify = ? "
                        + "WHERE patient_id = '"
                        + edit_patientID.getText() + "'";

                try {
                    if (alert.confirmationMessage("êtes vous sûr de vouloir modifier  Patient ID: " + edit_patientID.getText()
                            + "?")) {
                        prepare = connect.prepareStatement(updateData);

                        prepare.setString(1, edit_name.getText());
                        prepare.setString(2, edit_gender.getSelectionModel().getSelectedItem());
                        prepare.setString(3, edit_contactNumber.getText());
                        prepare.setString(4, edit_address.getText());
                        prepare.setString(5, edit_status.getSelectionModel().getSelectedItem());
                        prepare.setString(6, String.valueOf(sqlDate));
                        prepare.executeUpdate();
                        alert.successMessage("Modification réussie!");
                    } else {
                        alert.errorMessage("Cancelled.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    if (alert.confirmationMessage("êtes vous sûr de vouloir modifier  Patient ID: " + edit_patientID.getText()
                            + "?")) {

                        String path = Data.path;
                        path = path.replace("\\", "\\\\");
                        Path transfer = Paths.get(path);

                        Path copy = Paths.get("images\\"
                                + edit_patientID.getText() + ".jpg");

                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                        String insertImage = copy.toString();
                        insertImage = insertImage.replace("\\", "\\\\");

                        String updateData = "UPDATE patient SET image= ?, full_name = ?, gender = ?"
                                + ", mobile_number = ?, address = ?, status = ?, date_modify = ? "
                                + "WHERE patient_id = '"
                                + edit_patientID.getText() + "'";
                        prepare = connect.prepareStatement(updateData);

                        prepare.setString(1, insertImage);
                        prepare.setString(2, edit_name.getText());
                        prepare.setString(3, edit_gender.getSelectionModel().getSelectedItem());
                        prepare.setString(4, edit_contactNumber.getText());
                        prepare.setString(5, edit_address.getText());
                        prepare.setString(6, edit_status.getSelectionModel().getSelectedItem());
                        prepare.setString(7, String.valueOf(sqlDate));
                        prepare.executeUpdate();
                        alert.successMessage("Modification réussie!");


                    } else {
                        alert.errorMessage("Cancelled.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }
        connect.close();

    }

    // CLOSE THE EDITPATIENTFORM FXML FILE AND OPEN IT AGAIN
    public void setField() {
        edit_patientID.setText(String.valueOf(Data.temp_PatientID));
        edit_name.setText(Data.temp_name);
        edit_gender.getSelectionModel().select(Data.temp_gender);
        edit_contactNumber.setText(String.valueOf(Data.temp_number));
        edit_address.setText(Data.temp_address);
        edit_status.getSelectionModel().select(Data.temp_status);
        image = new Image("File:" + Data.temp_path, 173, 153, false, true);
        editPatient_imageView.setImage(image);
    }

    public void genderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : Data.gender) {
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        edit_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.status) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        edit_status.setItems(listData);
    }
    @FXML
    public void importbtn() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image", "*jpg", "*png", "*jpeg"));

        File file = open.showOpenDialog(editPatient_importBtn.getScene().getWindow());

        if (file != null) {

            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 112, 121, false, true);
            editPatient_imageView.setImage(image);

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setField();
        genderList();
        statusList();
    }

}
