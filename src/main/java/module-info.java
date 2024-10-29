module com.example.periodicsystemrecognition {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.periodicsystemrecognition to javafx.fxml;
    exports com.example.periodicsystemrecognition;
}