module com.orbiktech.codespacescompanion {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.orbiktech.codespacescompanion to javafx.fxml;
    exports com.orbiktech.codespacescompanion;
}