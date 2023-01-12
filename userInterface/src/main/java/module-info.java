module com.winterproject.userinterface {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.winterproject.userinterface to javafx.fxml;
    exports com.winterproject.userinterface;
}