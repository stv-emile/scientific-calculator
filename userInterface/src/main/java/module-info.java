module com.winter.userinterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.winter.engine;
    uses com.winter.userinterface.MainWindowApplication;

    opens com.winter.userinterface to javafx.fxml;
    exports com.winter.userinterface;
}