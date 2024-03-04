
package hospitalmanagementsystem;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;





/**
 *
 * @author WINDOWS 10
 */
public class DoctorMainFormController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private Button logout_btn;

    @FXML
    private Label date_time;

    @FXML
    private Label current_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button patients_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_IP;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_tA;
    @FXML
    private ImageView patient_tof;
    @FXML
    private Button patientof_btn;

    @FXML
    private  Button consultation_btn;
    @FXML
    private AreaChart<?, ?> dashboad_chart_PD;

    @FXML
    private BarChart<?, ?> dashboad_chart_DD;

    @FXML
    private TableView<AppointmentData> dashboad_tableView;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_name;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_description;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_appointmentDate;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_status;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private TextField patients_patientID;

    @FXML
    private TextField patients_patientName;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextField patients_password;

    @FXML
    private TextArea patients_address;

    @FXML
    private Button patients_confirmBtn;

    @FXML
    private Label patients_PA_patientID;

    @FXML
    private Label patients_PA_password;

    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Label patients_PI_gender;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Label patients_PI_address;

    @FXML
    private Button patients_PI_addBtn;

    @FXML
    private Button patients_PI_recordBtn;

    @FXML
    private AnchorPane appointments_form;
    @FXML
    private AnchorPane consultation_form;

    @FXML
    private TableView<AppointmentData> appointments_tableView;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_name;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_gender;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_contactNumber;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_description;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_date;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateModify;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateDelete;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_status;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_action;

    @FXML
    private TextField appointment_appointmentID;

    @FXML
    private TextField appointment_name;

    @FXML
    private ComboBox<String> appointment_gender;

    @FXML
    private TextField appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private TextField appointment_treatment;

    @FXML
    private TextField appointment_mobileNumber;

    @FXML
    private TextArea appointment_address;

    @FXML
    private ComboBox<String> appointment_status;

    @FXML
    private DatePicker appointment_schedule;

    @FXML
    private Button appointment_insertBtn;

    @FXML
    private Button appointment_updateBtn;

    @FXML
    private Button appointment_clearBtn;

    @FXML
    private Button appointment_deleteBtn;

    @FXML
    private ComboBox<String> patients_gender;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circleImage;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_doctorID;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_name;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private TextArea profile_address;

    @FXML
    private ComboBox<String> profile_specialized;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;
    @FXML
    private Button captureButton;

//    DATABASE TOOLSs
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    private final AlertMessage alert = new AlertMessage();

    public void dashbboardDisplayIP() throws SQLException {
        String sql = "SELECT COUNT(id) FROM patient WHERE doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getIP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getIP = result.getInt("COUNT(id)");
            }
            dashboard_IP.setText(String.valueOf(getIP));
        } catch (Exception e) {
            e.printStackTrace();
        }
        connect.close();//fermer la connexion
    }

    public void dashbboardDisplayTP() throws SQLException {
        String sql = "SELECT COUNT(id) FROM patient WHERE doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getTP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(getTP));
        } catch (Exception e) {
            e.printStackTrace();
        }
        connect.close();
    }

    public void dashbboardDisplayAP() throws SQLException {
        String sql = "SELECT COUNT(id) FROM patient WHERE status = 'Active' AND doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getAP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getAP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(getAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
        connect.close();//fermer la connexion

    }

    public void dashbboardDisplayTA() throws SQLException {
        String sql = "SELECT COUNT(id) FROM appointment WHERE  doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getTA = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTA = result.getInt("COUNT(id)");
            }
            dashboard_tA.setText(String.valueOf(getTA));
        } catch (Exception e) {
            e.printStackTrace();
        }
        connect.close();//fermer la connexion

    }

    public ObservableList<AppointmentData> dashboardAppointmentTableView() throws SQLException {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE doctor = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData aData;
            while (result.next()) {
                aData = new AppointmentData(result.getInt("appointment_id"),
                        result.getString("name"), result.getString("description"),
                      java.sql.Date.valueOf(  result.getString("date")), result.getString("status"));

                listData.add(aData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        connect.close();//fermer la connexion

        return listData;
    }

    private ObservableList<AppointmentData> dashboardGetData;

    public void dashboardDisplayData() throws SQLException {
        dashboardGetData = dashboardAppointmentTableView();

        dashboad_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dashboad_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboad_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dashboad_col_appointmentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dashboad_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboad_tableView.setItems(dashboardGetData);
    }

    public void dashboardNOP() throws SQLException {
        dashboad_chart_PD.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM patient WHERE doctor = ? GROUP BY date";
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, Data.doctor_id); // Utiliser un paramètre pour éviter les problèmes de sécurité et de syntaxe
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_PD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connect != null) {
                connect.close(); // Fermer la connexion dans le bloc finally pour garantir la fermeture même en cas d'exception.
            }
        }
    }


    public void dashboardNOA() throws SQLException {
        dashboad_chart_DD.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM appointment WHERE doctor = ? GROUP BY date";
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, Data.doctor_id);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_DD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null) {
                    connect.close(); // Fermer la connexion dans le bloc finally pour garantir la fermeture même en cas d'exception.
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void patientConfirmBtn() {

        // CHECK IF SOME OR ALL FIELDS ARE EMPTY
        if (patients_patientID.getText().isEmpty()
                || patients_patientName.getText().isEmpty()
                || patients_gender.getSelectionModel().getSelectedItem() == null
                || patients_mobileNumber.getText().isEmpty()
                || patients_password.getText().isEmpty()
                || patients_address.getText().isEmpty()) {
            alert.errorMessage("Remplissez bien les informations");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            // TO DISPLAY THE DATA FROM PERSONAL ACCOUNT 
            patients_PA_patientID.setText(patients_patientID.getText());
            patients_PA_password.setText(patients_password.getText());
            patients_PA_dateCreated.setText(String.valueOf(sqlDate));

            // TO DISPLAY THE DATA FROM PERSONAL INFORMATION 
            patients_PI_patientName.setText(patients_patientName.getText());
            patients_PI_gender.setText(patients_gender.getSelectionModel().getSelectedItem());
            patients_PI_mobileNumber.setText(patients_mobileNumber.getText());
            patients_PI_address.setText(patients_address.getText());
        }

    }

    public void patientAddBtn() throws SQLException, IOException {

        if (patients_PA_patientID.getText().isEmpty()
                || patients_PA_password.getText().isEmpty()
                || patients_PA_dateCreated.getText().isEmpty()
                || patients_PI_patientName.getText().isEmpty()
                || patients_PI_gender.getText().isEmpty()
                || patients_PI_mobileNumber.getText().isEmpty()
                || patients_PI_address.getText().isEmpty()) {
            alert.errorMessage("Erreur ");
        } else {

          connect=  Database.connectDB();
            if (patient_tof.getImage()==null) {
                try {
                    String doctorName = "";
                    String doctorSpecialized = "";

                    String getDoctor = "SELECT * FROM doctor WHERE doctor_id = '"
                            + nav_adminID.getText() + "'";

                    statement = connect.createStatement();
                    result = statement.executeQuery(getDoctor);

                    if (result.next()) {
                        doctorName = result.getString("full_name");
                        doctorSpecialized = result.getString("specialized");
                    }
                    // CHECK IF THE PATIENT ID THAT THE DOCTORS WANT TO INSERT/ADD IS EXISTING ALREADY
                    String checkPatientID = "SELECT * FROM patient WHERE patient_id = '"
                            + patients_PA_patientID.getText() + "'";
                    statement = connect.createStatement();
                    result = statement.executeQuery(checkPatientID);
                    if (result.next()) {
                        alert.errorMessage(patients_PA_patientID.getText() + " existe deja");
                    } else {
                        String insertData = "INSERT INTO patient (patient_id, password, full_name, mobile_number, "
                                + "address, doctor, specialized, date,gender, "
                                + "status) "
                                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        prepare = connect.prepareStatement(insertData);
                        prepare.setString(1, patients_PA_patientID.getText());
                        prepare.setString(2, patients_PA_password.getText());
                        prepare.setString(3, patients_PI_patientName.getText());
                        prepare.setString(4, patients_PI_mobileNumber.getText());
                        prepare.setString(5, patients_PI_address.getText());
                        prepare.setString(6, nav_adminID.getText());
                        prepare.setString(7, doctorSpecialized);
                        prepare.setString(8, "" + sqlDate);
                        prepare.setString(9, patients_gender.getValue());
                        prepare.setString(10, "Confirm");

                        prepare.executeUpdate();

                        alert.successMessage("Ajout terminé avec succes !");
                        // TO CLEAR ALL FIELDS AND SOME LABELS
                        patientClearFields();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else{
                String path = Data.path;
                path = path.replace("\\", "\\\\");
                Path transfer = Paths.get(path);
                Path copy = Paths.get("images\\"
                        + patients_PI_patientName.getText() + ".jpg");
                Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                String insertImage = copy.toString();
                insertImage = insertImage.replace("\\", "\\\\");

                try {
                    String doctorName = "";
                    String doctorSpecialized = "";

                    String getDoctor = "SELECT * FROM doctor WHERE doctor_id = '"
                            + nav_adminID.getText() + "'";

                    statement = connect.createStatement();
                    result = statement.executeQuery(getDoctor);

                    if (result.next()) {
                        doctorName = result.getString("full_name");
                        doctorSpecialized = result.getString("specialized");
                    }
                    // CHECK IF THE PATIENT ID THAT THE DOCTORS WANT TO INSERT/ADD IS EXISTING ALREADY
                    String checkPatientID = "SELECT * FROM patient WHERE patient_id = '"
                            + patients_PA_patientID.getText() + "'";
                    statement = connect.createStatement();
                    result = statement.executeQuery(checkPatientID);
                    if (result.next()) {
                        alert.errorMessage(patients_PA_patientID.getText() + " existe deja");
                    } else {
                        String insertData = "INSERT INTO patient (image,patient_id, password, full_name, mobile_number, "
                                + "address, doctor, specialized, date,gender, "
                                + "status) "
                                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        prepare = connect.prepareStatement(insertData);
                        prepare.setString(1, insertImage);
                        prepare.setString(2, patients_PA_patientID.getText());
                        prepare.setString(3, patients_PA_password.getText());
                        prepare.setString(4, patients_PI_patientName.getText());
                        prepare.setString(5, patients_PI_mobileNumber.getText());
                        prepare.setString(6, patients_PI_address.getText());
                        prepare.setString(7, nav_adminID.getText());
                        prepare.setString(8, doctorSpecialized);
                        prepare.setString(9, "" + sqlDate);
                        prepare.setString(10, patients_gender.getValue());
                        prepare.setString(11, "Confirm");

                        prepare.executeUpdate();

                        alert.successMessage("Ajout terminé avec succes !");
                        // TO CLEAR ALL FIELDS AND SOME LABELS
                        patientClearFields();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            connect.close();
// NOW, LETS TRY
        }
    }

    public void patientRecordBtn() {
        try {
            // LINK THE NAME OF YOUR FXML FOR RECORD PAGE
            Parent root = FXMLLoader.load(getClass().getResource("RecordPageForm.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Gestion de cabinet medical");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void patientClearFields() {
        patients_patientID.clear();
        patients_patientName.clear();
        patients_gender.getSelectionModel().clearSelection();
        patients_mobileNumber.clear();
        patients_password.clear();
        patients_address.clear();

        patients_PA_patientID.setText("");
        patients_PA_password.setText("");
        patients_PA_dateCreated.setText("");

        patients_PI_patientName.setText("");
        patients_PI_gender.setText("");
        patients_PI_mobileNumber.setText("");
        patients_PI_address.setText("");
        patient_tof.setImage(null);
    }

    private void patientGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);

        patients_gender.setItems(listData);

    }

    public void appointmentInsertBtn() throws SQLException {

//        CHECK IF THE FIELD(S) ARE EMPTY
        if (appointment_appointmentID.getText().isEmpty()
                || appointment_name.getText().isEmpty()
                || appointment_gender.getSelectionModel().getSelectedItem() == null
                || appointment_mobileNumber.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null) {
            alert.errorMessage("Remplissez bien toutes les informations ");
        } else {
            String checkAppointmentID = "SELECT * FROM appointment WHERE appointment_id = "
                    + appointment_appointmentID.getText();
            connect = Database.connectDB();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkAppointmentID);

                if (result.next()) {
                    alert.errorMessage(appointment_appointmentID.getText() + " a été déja réservé");
                } else {
                    String getSpecialized = "";
                    String getDoctorData = "SELECT * FROM doctor WHERE doctor_id = '"
                            + Data.doctor_id + "'";

                    statement = connect.createStatement();
                    result = statement.executeQuery(getDoctorData);

                    if (result.next()) {
                        getSpecialized = result.getString("specialized");
                    }

                    String insertData = "INSERT INTO appointment (appointment_id, name, gender"
                            + ", description, diagnosis, treatment, mobile_number"
                            + ", address, date, status, doctor, specialized, schedule) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1, appointment_appointmentID.getText());
                    prepare.setString(2, appointment_name.getText());
                    prepare.setString(3, (String) appointment_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, appointment_description.getText());
                    prepare.setString(5, appointment_diagnosis.getText());
                    prepare.setString(6, appointment_treatment.getText());
                    prepare.setString(7, appointment_mobileNumber.getText());
                    prepare.setString(8, appointment_address.getText());

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    prepare.setString(9, "" + sqlDate);
                    prepare.setString(10, (String) appointment_status.getSelectionModel().getSelectedItem());
                    prepare.setString(11, Data.doctor_id);
                    prepare.setString(12, getSpecialized);
                    prepare.setString(13, "" + appointment_schedule.getValue());

                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Ajout terminé!");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            connect.close();
        }

    }

    public void appointmentUpdateBtn() throws SQLException {

        if (appointment_appointmentID.getText().isEmpty()
                || appointment_name.getText().isEmpty()
                || appointment_gender.getSelectionModel().getSelectedItem() == null
                || appointment_mobileNumber.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null) {
            alert.errorMessage("Remplissez bien toutes les informations");
        } else {
            // TO GET THE DATE TODAY
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

            String updateData = "UPDATE appointment SET name = '"
                    + appointment_name.getText() + "', gender = '"
                    + appointment_gender.getSelectionModel().getSelectedItem() + "', mobile_number = '"
                    + appointment_mobileNumber.getText() + "', description = '"
                    + appointment_description.getText() + "', address = '"
                    + appointment_address.getText() + "', status = '"
                    + appointment_status.getSelectionModel().getSelectedItem() + "', schedule = '"
                    + appointment_schedule.getValue() + "', date_modify = '"
                    + sqlDate + "' WHERE appointment_id = '"
                    + appointment_appointmentID.getText() + "'";

            connect = Database.connectDB();

            try {
                if (alert.confirmationMessage("Etes vous sur de modifier  Appointment ID: "
                        + appointment_appointmentID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Modifiaction éfféctuée!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            connect.close();//fermer la connexion

        }
    }

    public void appointmentDeleteBtn() throws SQLException {

        if (appointment_appointmentID.getText().isEmpty()) {
            alert.errorMessage("Selectionner d'abord");
        } else {

            String updateData = "DELETE FROM  appointment  WHERE appointment_id = '"
                    + appointment_appointmentID.getText() + "'";

            connect = Database.connectDB();

            try {
                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                if (alert.confirmationMessage("Etes vous sur de supprimer Appointment ID: "
                        + appointment_appointmentID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);

                   // prepare.setString(1, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();

                    alert.successMessage("Suppression effectuée!");
                } else {
                    alert.errorMessage("Cancelled.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            connect.close();//fermer la connexion


        }

    }

    // TO CLEAR ALL FIELDS
    public void appointmentClearBtn() {
        appointment_appointmentID.clear();
        appointment_name.clear();
        appointment_gender.getSelectionModel().clearSelection();
        appointment_mobileNumber.clear();
        appointment_description.clear();
        appointment_treatment.clear();
        appointment_diagnosis.clear();
        appointment_address.clear();
        appointment_status.getSelectionModel().clearSelection();
        appointment_schedule.setValue(null);
    }

    private Integer appointmentID;

    public void appointmentGetAppointmentID() throws SQLException {
        String sql = "SELECT MAX(appointment_id) FROM appointment";
        connect = Database.connectDB();

        int tempAppID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempAppID = result.getInt("MAX(appointment_id)");
            }
            if (tempAppID == 0) {
                tempAppID += 1;
            } else {
                tempAppID += 1;
            }
            appointmentID = tempAppID;
        } catch (Exception e) {
            e.printStackTrace();
        }
        connect.close();//fermer la connexion

    }

    public void appointmentAppointmentID() throws SQLException {
        appointmentGetAppointmentID();

        appointment_appointmentID.setText("" + appointmentID);


    }

    public void appointmentGenderList() {
        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        appointment_gender.setItems(listData);

    }

    public void appointmentStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        appointment_status.setItems(listData);

    }

    public ObservableList<AppointmentData> appointmentGetData() throws SQLException {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE date_delete IS NULL and doctor = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData appData;
            java .sql.Date date1;
            java.sql.Date date2;
            java.sql.Date date3;


            if (result.getString("date_modify") == null) {
                date1 = null;  // ou une autre valeur par défaut si nécessaire
            } else {
                date1 =  new java.sql. Date(java.sql.Date.valueOf(result.getString("date_modify")).getTime());
            }

            if (result.getString("date_delete") == null) {
                date2 = null;  // ou une autre valeur par défaut si nécessaire
            } else {
                date2 = new java.sql.Date(java.sql.Date.valueOf(result.getString("date_delete")).getTime());
            }

            if (result.getString("schedule") == null) {
                date3 = null;  // ou une autre valeur par défaut si nécessaire
            } else {
                date3 = new java.sql.Date(java.sql.Date.valueOf(result.getString("schedule")).getTime());
            }


            while (result.next()) {
//            Integer appointmentID, String name, String gender,
//            Long mobileNumber, String description, String diagnosis, String treatment, String address,
//            Date date, Date dateModify, Date dateDelete, String status, Date schedule

                appData = new AppointmentData(result.getInt("appointment_id"),
                        result.getString("name"), result.getString("gender"),
                        result.getLong("mobile_number"), result.getString("description"),
                        result.getString("diagnosis"), result.getString("treatment"),
                        result.getString("address"),java.sql.Date.valueOf( result.getString("date")),
                        date1, date2,
                        result.getString("status"), date3);
                // STORE ALL DATA
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        connect.close();//fermer la connexion

        return listData;
    }

    public ObservableList<AppointmentData> appoinmentListData;

    public void appointmentShowData() throws SQLException {
        appoinmentListData = appointmentGetData();

        appointments_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointments_tableView.setItems(appoinmentListData);
    }
// TO SELECT THE DATA PER ROW IN THE TABLE

    public void appointmentSelect() {

        AppointmentData appData = appointments_tableView.getSelectionModel().getSelectedItem();
        int num = appointments_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        appointment_appointmentID.setText("" + appData.getAppointmentID());
        appointment_name.setText(appData.getName());
        appointment_gender.getSelectionModel().select(appData.getGender());
        appointment_mobileNumber.setText("" + appData.getMobileNumber());
        appointment_description.setText(appData.getDescription());
        appointment_diagnosis.setText(appData.getDiagnosis());
        appointment_treatment.setText(appData.getTreatment());
        appointment_address.setText(appData.getAddress());
        appointment_status.getSelectionModel().select(appData.getStatus());

    }

    public void profileUpdateBtn() throws SQLException {

        connect = Database.connectDB();

        if (profile_doctorID.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_gender.getSelectionModel().getSelectedItem() ==  null
                || profile_mobileNumber.getText().isEmpty()
                || profile_address.getText().isEmpty()
                || profile_specialized.getSelectionModel().getSelectedItem() == null
                || profile_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Remplissez tous les champs");
        } else {
            // CHECK IF THE PATH IS NULL 
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE doctor SET full_name = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobileNumber.getText());
                    prepare.setString(5, profile_address.getText());
                    prepare.setString(6, profile_specialized.getSelectionModel().getSelectedItem());
                    prepare.setString(7, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Mise à jour réussie");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String updateData = "UPDATE doctor SET full_name = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, image = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobileNumber.getText());
                    prepare.setString(5, profile_address.getText());
                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    // LINK YOUR DIRECTORY FOLDER
                    Path copy = Paths.get("images\\"
                            + Data.doctor_id + ".jpg");
                    System.out.println(copy);

                    try {
                        // TO PUT THE IMAGE FILE TO YOUR DIRECTORY FOLDER
                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    prepare.setString(6, copy.toAbsolutePath().toString());
                    prepare.setString(7, profile_specialized.getSelectionModel().getSelectedItem());
                    prepare.setString(8, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(9, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Modification effectuée avec succes!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        connect.close();//fermer la connexion

    }

    public void profileChangeProfile() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*png", "*jpg", "*jpeg"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 128, 103, false, true);
            profile_circleImage.setFill(new ImagePattern(image));
        }

    }

    public void profileLabels() throws SQLException {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_label_doctorID.setText(result.getString("doctor_id"));
                profile_label_name.setText(result.getString("full_name"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        connect.close();//fermer la connexion

    }

    public void profileFields() throws SQLException {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_doctorID.setText(result.getString("doctor_id"));
                profile_name.setText(result.getString("full_name"));
                profile_email.setText(result.getString("email"));
                profile_gender.getSelectionModel().select(result.getString("gender"));
                profile_mobileNumber.setText(result.getString("mobile_number"));
                profile_address.setText(result.getString("address"));
                profile_specialized.getSelectionModel().select(result.getString("specialized"));
                profile_status.getSelectionModel().select(result.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connect.close();//fermer la connexion


    }

    public void profileDisplayImages() throws SQLException {

        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        String temp_path1 = "";
        String temp_path2 = "";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_path1 = "File:" + result.getString("image");
                temp_path2 = "File:" + result.getString("image");

                if (result.getString("image") != null) {
                    image = new Image(temp_path1, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(temp_path2, 128, 103, false, true);
                    profile_circleImage.setFill(new ImagePattern(image));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        connect.close();//fermer la connexion


    }

    public void profileGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        profile_gender.setItems(listData);

    }

    private String[] specialization = {"Allergist", "Dermatologist", "Ophthalmologist", "Gynecologist", "Cardiologist"};

    public void profileSpecializedList() {

        List<String> listS = new ArrayList<>();

        for (String data : specialization) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_specialized.setItems(listData);
    }

    public void profileStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }

    public void displayAdminIDNumberName() {

        String name = Data.doctor_name;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        nav_username.setText(name);
        nav_adminID.setText(Data.doctor_id);
        top_username.setText(name);

    }




    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == patients_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == appointments_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);
        } else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
        } else if (event.getSource()==consultation_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            consultation_form.setVisible(true);
            
        }
    }

    public void logoutBtn() {

        try {
            if (alert.confirmationMessage("Etes vous sur de vouloir vous déconnecter?")) {
                Data.doctor_id = "";
                Data.doctor_name = "";
                Parent root = FXMLLoader.load(getClass().getResource("DoctorPage.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                // TO HIDE YOUR MAIN FORM
                logout_btn.getScene().getWindow().hide();

                Data.doctor_id = "";
                Data.doctor_name = "";
                Data.temp_PatientID = 0;
                Data.temp_name = "";
                Data.temp_gender = "";
                Data.temp_number = Long.parseLong("0");
                Data.temp_address = "";
                Data.temp_status = "";
                Data.temp_date = "";
                Data.temp_path = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void patientofAction(ActionEvent event){

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image", "*jpg", "*png", "*jpeg"));

        File file = open.showOpenDialog(patientof_btn.getScene().getWindow());

        if (file != null) {

            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 112, 121, false, true);
            patient_tof.setImage(image);

        }
    }

    public void runTime() {
        new Thread() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayAdminIDNumberName();
        runTime();

        try {
            dashbboardDisplayIP();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            dashbboardDisplayTP();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            dashbboardDisplayAP();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            dashbboardDisplayTA();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            dashboardDisplayData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            dashboardNOP();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            dashboardNOA();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // TO SHOW THE DATA IMMEDIATELY ONCE YOU LOGGED IN YOUR ACCOUNT
        try {
            appointmentShowData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        appointmentGenderList();
        appointmentStatusList();
        try {
            appointmentAppointmentID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // TO SHOW THE DATA IMMEDIATELY THE PATIENT'S GENDER COMBOXBOX
        patientGenderList();

//        PROFILE
        try {
            profileLabels();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            profileFields(); // TO DISPLAY ALL DETAILS TO THE FIELDS
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        profileGenderList();
        profileSpecializedList();
        profileStatusList();
        try {
            profileDisplayImages(); // TO DISPLAY THE PROFILE PICTURE OF THE DOCTOR
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Limiter le nombre de caractères à 10
        int maxLength = 10;
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (change.isContentChange()) {
                String newText = change.getControlNewText();
                if (newText.length() > maxLength) {
                    return null; // Annuler le changement s'il dépasse la longueur maximale
                }
            }
            return change;
        });

        // Appliquer le TextFormatter au TextField
        patients_mobileNumber.setTextFormatter(textFormatter);

    }

}
