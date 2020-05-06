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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable {
    @FXML
    private TableView<ModelTable> table;
    @FXML
    public TableColumn< ModelTable, String> col_presentacion;
    @FXML
    public TableColumn< ModelTable, String> col_descripcion;
    @FXML
    private TableColumn< ModelTable, String > col_nombre;
    @FXML
    private TableColumn< ModelTable, String > col_dosis;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        //String que se obtiene del servidor
        String json = null;
        try {
            json = DBConnector.getDatosServer("","medicamento");
            //Hacemos parse
            Object obj = JSONValue.parse(json);
            int indexJsonSize = ((JSONArray) obj).size();
            for (int contador = 0; contador < indexJsonSize ; contador++) {
                JSONObject jsonObjectInIndex = (JSONObject) ((JSONArray) obj).get(contador);
                //Sacar parametros del objeto
                oblist.add(new ModelTable(jsonObjectInIndex.get("Nombre").toString(),jsonObjectInIndex.get("Dosis_recomendada").toString(),
                        jsonObjectInIndex.get("Presentacion").toString(),jsonObjectInIndex.get("Descripcion").toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        col_presentacion.setCellValueFactory(new PropertyValueFactory<>("presentacion"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_dosis.setCellValueFactory(new PropertyValueFactory<>("dosis"));
        col_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        table.setItems(oblist);


    }

    public void Volver(ActionEvent actionEvent) throws IOException {
        Main.cambiarVista("Home.fxml",actionEvent);
    }
}
