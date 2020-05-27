package mediimento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class CrearRecordatorioController implements Initializable {

    @FXML
    public DatePicker fecha_ini;
    @FXML
    public DatePicker fecha_fin;
    @FXML
    public TextField hora;
    @FXML
    public CheckBox lunes;
    @FXML
    public CheckBox martes;
    @FXML
    public CheckBox miercoles;
    @FXML
    public CheckBox jueves;
    @FXML
    public CheckBox viernes;
    @FXML
    public CheckBox sabado;
    @FXML
    public CheckBox domingo;
    @FXML
    private TextField name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void registrar(ActionEvent actionEvent) throws IOException, ParseException {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

        String cedula = Main.getUser();
        String name = this.name.getText();
        Date fecha_ini = Date.valueOf(this.fecha_ini.getValue());
        Date fecha_fin = Date.valueOf(this.fecha_fin.getValue());
        java.util.Date time = dateFormat.parse(this.hora.getText());
        Time hora = new java.sql.Time(time.getTime());
        String L = this.lunes.selectedProperty().getValue() ? "1" : "0";
        String M = this.martes.selectedProperty().getValue() ? "1" : "0";
        String W = this.miercoles.selectedProperty().getValue() ? "1" : "0";
        String J = this.jueves.selectedProperty().getValue() ? "1" : "0";
        String V = this.viernes.selectedProperty().getValue() ? "1" : "0";
        String S = this.sabado.selectedProperty().getValue() ? "1" : "0";
        String D = this.domingo.selectedProperty().getValue() ? "1" : "0";


        String query = "user_cedula=" + cedula + "&id_medicamento=" + determinarId(name) + "&fecha_inicial=" + fecha_ini.toString() + "&fecha_final=" +
                fecha_fin.toString() + "&hora_recordatorio=" + hora.toString() + "&boolean_lunes=" + L + "&boolean_martes=" + M + "&boolean_miercoles=" + W +
                "&boolean_jueves=" + J + "&boolean_viernes=" + V + "&boolean_sabado=" + S + "&boolean_domingo=" + D;

        System.out.println(query);
        try {
            System.out.println(DBConnector.getDatosServer(query, "crearRecordatorio"));
            Main.cambiarVista("Recordatorio.fxml", actionEvent);
        } catch (IOException e) {
            System.out.println("Error al crear usuario");
        }
    }

    private String determinarId(String nombre){
        String json = null;
        try {
            json = DBConnector.getDatosServer("nombre_medicamento="+nombre,"consultarMedicamento");
            //Hacemos parse
            JSONParser parser = new JSONParser();
            JSONObject jsonob = (JSONObject) parser.parse(json);
            return jsonob.get("Codigo").toString();
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
            return "Desconocido";
        }
    }

    public void volver(ActionEvent actionEvent) throws IOException {
        Main.cambiarVista("Recordatorio.fxml", actionEvent);
    }
}
