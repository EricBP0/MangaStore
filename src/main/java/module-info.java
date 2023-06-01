module com.example.mangastoreservidor {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jasypt;
    requires java.desktop;

    opens com.example.mangastoreservidor to javafx.fxml;
    exports com.example.mangastoreservidor;
}