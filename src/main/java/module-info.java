module com.assignment.tictactoe.service.resturentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires jdk.security.jgss;


    opens com.assignment.resturentmanagementsystem.Controller to javafx.fxml;
    opens com.assignment.resturentmanagementsystem.Dto.TM to javafx.base;
    exports com.assignment.resturentmanagementsystem;
}