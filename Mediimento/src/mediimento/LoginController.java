package mediimento;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class LoginController implements Initializable {

    @FXML
    private TextField cedula;
    @FXML
    private PasswordField pass;
    @FXML
    private void handleClose(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleIn(ActionEvent actionEvent) throws IOException{
        String cedula = this.cedula.getText();
        String pass = this.pass.getText();
        String query= "user_cedula="+cedula+"&user_contraseña="+pass;
        String connect = DBConnector.getDatosServer(query,"iniciarSesion");
        if(!connect.equals("null")){
            Object obj = JSONValue.parse(connect);
            JSONObject object = (JSONObject)obj;
            Main.cambiarVista("Home.fxml", actionEvent);
        }else{
            System.out.println("Error en las credenciales");
        }
    }

    @FXML
    private void handleUp(ActionEvent actionEvent) throws IOException {
        Main.cambiarVista("Registro.fxml", actionEvent);
    }
}
