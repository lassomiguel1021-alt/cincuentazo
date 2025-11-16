module com.univalle.cincuentazo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.univalle.cincuentazo to javafx.fxml;
    opens com.univalle.cincuentazo.models to javafx.fxml;
    exports com.univalle.cincuentazo;
    exports com.univalle.cincuentazo.models;
}
