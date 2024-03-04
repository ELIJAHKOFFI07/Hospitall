module hospital.hospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires java.desktop;



    exports hospitalmanagementsystem to  javafx.graphics;
    opens hospitalmanagementsystem to  javafx.fxml,javafx.base;
}