package mediimento;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DBConnector {
    public static String getDatosServer(String data, String tipoConexion) throws IOException {
        URL url;
        if(data.equals("")){
            url = new URL("http://ec2-35-173-204-107.compute-1.amazonaws.com/" + tipoConexion + ".php");
        }else{
            url = new URL("http://ec2-35-173-204-107.compute-1.amazonaws.com/" + tipoConexion + ".php" + "?" + data );
        }

        HttpURLConnection sever = (HttpURLConnection) url.openConnection();
        sever.setRequestMethod("POST");
        BufferedReader reader = new BufferedReader(new InputStreamReader(sever.getInputStream()));
        return reader.readLine();
    }

}
