module net.normalv.golpeserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.java_websocket;
    requires com.google.gson;
    requires jdk.compiler;

    opens net.normalv.golpeserver.websocket.packets.impl to com.google.gson;
    opens net.normalv.golpeserver.websocket.packets to com.google.gson;

    opens net.normalv.golpeserver to javafx.fxml;
    exports net.normalv.golpeserver;
}