/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.net.URL;
import java.sql.*;
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

/**
 *
 * @author WINDOWS 10
 */
public class EditAppointmentFormController implements Initializable{

    @FXML
    private TextField editApp_appointmentID;

    @FXML
    private TextField editApp_fullName;

    @FXML
    private ComboBox<String> editApp_gender;

    @FXML
    private TextField editApp_mobileNumber;

    @FXML
    private TextArea editApp_address;

    @FXML
    private Button editApp_updateBtn;

    @FXML
    private Button editApp_cancelBtn;

    @FXML
    private TextArea editApp_description;

    @FXML
    private TextField editApp_diagnosis;

    @FXML
    private TextField editApp_treatment;

    @FXML
    private ComboBox<String> editApp_doctor;

    @FXML
    private ComboBox<String> editApp_specialized;

    @FXML
    private ComboBox<String> editApp_status;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    
    private AlertMessage alert = new AlertMessage();
    
    public void displayFields(){
        editApp_appointmentID.setText(Data.temp_appID);
        editApp_fullName.setText(Data.temp_appName);
        editApp_gender.getSelectionModel().select(Data.temp_appGender);
        editApp_mobileNumber.setText(Data.temp_appMobileNumber);
        editApp_address.setText(Data.temp_appAddress);
        editApp_description.setText(Data.temp_appDescription);
        editApp_diagnosis.setText(Data.temp_appDiagnosis);
        editApp_treatment.setText(Data.temp_appTreatment);
        editApp_doctor.getSelectionModel().select(Data.temp_appDoctor);
        editApp_specialized.getSelectionModel().select(Data.temp_appSpecialized);
        editApp_status.getSelectionModel().select(Data.temp_appStatus);
    }
    
    public void doctorList(){
        String sql = "SELECT * FROM doctor WHERE delete_date IS NULL";
        
        connect = Database.connectDB();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            while(result.next()){
                listData.add(result.getString("doctor_id"));
            }
            
            editApp_doctor.setItems(listData);
            specializedList();
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void specializedList(){
        String sql = "SELECT * FROM doctor WHERE delete_date IS NULL AND doctor_id = '"
                + editApp_doctor.getSelectionModel().getSelectedItem() + "'";
        
        connect = Database.connectDB();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            if(result.next()){
                listData.add(result.getString("specialized"));
            }
            editApp_specialized.setItems(listData);
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void genderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : Data.gender) {
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        editApp_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.status) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        editApp_status.setItems(listData);
    }

    @FXML
    public void UpdateAction() throws SQLException {
        connect=Database.connectDB();
        if (editApp_appointmentID.getText().isEmpty() ||
                editApp_address.getText().isEmpty() ||
                editApp_description.getText().isEmpty()
                || editApp_diagnosis.getText().isEmpty()
                || editApp_doctor.getSelectionModel().getSelectedItem() == null
                || editApp_fullName.getText().isEmpty()
                || editApp_mobileNumber.getText().isEmpty()
                || editApp_gender.getSelectionModel().getSelectedItem() == null
                || editApp_specialized.getSelectionModel().getSelectedItem() == null
                || editApp_treatment.getText().isEmpty()
                || editApp_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("remplissez toutes les informations");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            String updateData = "UPDATE appointment SET name = '"
                    + editApp_fullName.getText() + "', diagnosis = '"
                    + editApp_diagnosis.getText() + "', treatment = '"
                    + editApp_treatment.getText() + "', specialized = '"
                    + editApp_specialized.getSelectionModel().getSelectedItem() + "', gender = '"
                    + editApp_gender.getSelectionModel().getSelectedItem() + "', mobile_number = '"
                    + editApp_mobileNumber.getText() + "', address = '"
                    + editApp_address.getText() + "', status = '"
                    + editApp_status.getSelectionModel().getSelectedItem() + "', date_modify = '"
                    + String.valueOf(sqlDate) + "' "
                    + "WHERE appointment_id = '" +editApp_appointmentID.getText() + "'";
            try {
                if (alert.confirmationMessage("Etes vous sûr de vouloir modifier ID Rendez-vous: " + editApp_appointmentID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();
                    alert.successMessage("Modification effectuée");
                } else {
                    alert.errorMessage("Cancelled.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        connect.close();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doctorList();
        genderList();
        statusList();
        
        displayFields();
    }
    
}
