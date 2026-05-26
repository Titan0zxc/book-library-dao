module org.example.library {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.library to javafx.fxml;
    opens org.example.library.model to javafx.base;

    exports org.example.library;
}
