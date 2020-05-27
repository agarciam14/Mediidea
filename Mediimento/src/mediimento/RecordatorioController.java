package mediimento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecordatorioController implements Initializable {

    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn< ModelTable, String> col_nombre;
    @FXML
    private TableColumn< ModelTable, String> col_fecha_ini;
    @FXML
    private TableColumn< ModelTable, String> col_fecha_fin;
    @FXML
    private TableColumn< ModelTable, String> col_hora;
    @FXML
    private TableColumn< ModelTable, String> col_lunes;
    @FXML
    private TableColumn< ModelTable, String> col_martes;
    @FXML
    private TableColumn< ModelTable, String> col_miercoles;
    @FXML
    private TableColumn< ModelTable, String> col_jueves;
    @FXML
    private TableColumn< ModelTable, String> col_viernes;
    @FXML
    private TableColumn< ModelTable, String> col_sabado;
    @FXML
    private TableColumn< ModelTable, String> col_domingo;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        //String que se obtiene del servidor
        String json = null;
        String cedula = Main.getUser();
        String contrasena = Main.getContrasena();
        String query = "user_cedula=" + cedula + "&user_contrasena="+contrasena;
        try {
            json = DBConnector.getDatosServer(query,"consultarRecordatorio");
            //Hacemos parse
            Object obj = JSONValue.parse(json);
            int indexJsonSize = ((JSONArray) obj).size();
            for (int contador = 0; contador < indexJsonSize ; contador++) {
                JSONObject jsonObjectInIndex = (JSONObject) ((JSONArray) obj).get(contador);
                //Sacar parametros del objeto
                oblist.add(new ModelTable(determinarNombre(jsonObjectInIndex.get("codigo_m").toString()),jsonObjectInIndex.get("Fecha_inicial").toString(),
                        jsonObjectInIndex.get("Fecha_final").toString(),jsonObjectInIndex.get("Hora").toString(),jsonObjectInIndex.get("Repite_lunes").toString(),
                        jsonObjectInIndex.get("Repite_martes").toString(),jsonObjectInIndex.get("Repite_miercoles").toString(),
                        jsonObjectInIndex.get("Repite_jueves").toString(),jsonObjectInIndex.get("Repite_viernes").toString(),
                        jsonObjectInIndex.get("Repite_sabado").toString(),jsonObjectInIndex.get("Repite_domingo").toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_fecha_ini.setCellValueFactory(new PropertyValueFactory<>("fecha_ini"));
        col_fecha_fin.setCellValueFactory(new PropertyValueFactory<>("fecha_fin"));
        col_hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        col_lunes.setCellValueFactory(new PropertyValueFactory<>("lunes"));
        col_martes.setCellValueFactory(new PropertyValueFactory<>("martes"));
        col_miercoles.setCellValueFactory(new PropertyValueFactory<>("miercoles"));
        col_jueves.setCellValueFactory(new PropertyValueFactory<>("jueves"));
        col_viernes.setCellValueFactory(new PropertyValueFactory<>("viernes"));
        col_sabado.setCellValueFactory(new PropertyValueFactory<>("sabado"));
        col_domingo.setCellValueFactory(new PropertyValueFactory<>("domingo"));

        table.setItems(oblist);
    }

    private String determinarNombre(String id){
        String json = null;
        try {
            json = DBConnector.getDatosServer("id_medicamento="+id,"consultarMedicamentoID");
            //Hacemos parse
            JSONParser parser = new JSONParser();
            JSONObject jsonob = (JSONObject) parser.parse(json);
            return jsonob.get("Nombre").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return "Desconocido";
        }
    }

    public void Volver(ActionEvent actionEvent) throws IOException {
        Main.cambiarVista("Home.fxml",actionEvent);
    }

    public void Crear(ActionEvent actionEvent) throws IOException {
        Main.cambiarVista("CrearRecordatorio.fxml",actionEvent);
    }
}
