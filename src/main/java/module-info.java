module com.orbiktech.codespacescompanion {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires nanohttpd;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires java.prefs;

    opens com.orbiktech.codespacescompanion to javafx.fxml;
    exports com.orbiktech.codespacescompanion;
}