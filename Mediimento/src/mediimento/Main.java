package mediimento;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static String user;
    private static String contrasena;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Table View");
        primaryStage.setScene(new Scene(root, 764, 488));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    protected static void cambiarVista(String filename, ActionEvent actionEvent) throws IOException {
        Parent file = FXMLLoader.load(Main.class.getResource(filename));
        Scene scene = new Scene(file);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Main.user = user;
    }

    public static String getContrasena() {
        return contrasena;
    }

    public static void setContrasena(String contrasena) {
        Main.contrasena = contrasena;
    }
}
