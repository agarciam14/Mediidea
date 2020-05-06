/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediimento;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML TableController class
 *
 * @author Santiago
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void recordatorio(ActionEvent event) {
    }

    @FXML
    private void medicamentos(ActionEvent actionEvent) throws IOException {
        Main.cambiarVista("Table.fxml",actionEvent);
    }

    @FXML
    private void editar(ActionEvent actionEvent) throws IOException {
        Main.cambiarVista("Editar.fxml",actionEvent);
    }

    @FXML
    private void salir(ActionEvent actionEvent) throws IOException {
        Main.cambiarVista("Login.fxml",actionEvent);
    }
    private void parametros(LoginController stage1, String name, String pass){
    
    }
    
}
