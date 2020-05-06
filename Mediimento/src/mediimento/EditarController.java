package mediimento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class EditarController implements Initializable {

    @FXML
    public TextField name;
    @FXML
    public TextField apellido;
    @FXML
    public TextField cedula;
    @FXML
    public DatePicker fecha_nacimiento;
    @FXML
    public TextField genero;
    @FXML
    public TextField peso;
    @FXML
    public TextField password;
    @FXML
    public TextField passwordn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void registrar(ActionEvent actionEvent) {
        String name = this.name.getText();
        String apellido = this.name.getText();
        String cedula = this.cedula.getText();
        Date fecha_nacimiento = Date.valueOf(this.fecha_nacimiento.getValue());
        String genero = this.genero.getText();
        String peso = this.genero.getText();
        String password = this.password.getText();
        String passwordn = this.passwordn.getText();
        String query = "user_cedula="+cedula+"&user_fecha="+fecha_nacimiento+"&user_nombre="+name+"&user_apellido="+apellido+
                "&user_genero="+genero+"&user_peso="+peso+"&user_contraseña_new="+passwordn+"&user_contraseña="+password;
        try {
            DBConnector.getDatosServer(query,"editarUsuario");
        } catch (IOException e) {
            System.out.println("Error al conectar con la base de datos");
        }
    }

    public void volver(ActionEvent actionEvent) throws IOException {
        Main.cambiarVista("Home.fxml",actionEvent);
    }
}
