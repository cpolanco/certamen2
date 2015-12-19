package cl.telematica.android.certamen2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.String;import java.lang.StringBuilder;import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ricardo on 04-12-2015.
 */
public class HttpServerConnection {

    public String connectToServer(String myUrl, int timeOut){
            try {
                URL url = new URL(myUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(timeOut);
                conn.setRequestMethod("GET");
                //retener informacion
                conn.setDoInput(true);

                conn.connect();

                InputStream is = conn.getInputStream();
                return readIt(is);

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "URL MAL FORMADA";
            } catch (IOException e) {
                e.printStackTrace();
                return "HOLA SOY UN ERROR";
            }

    }

    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {

        Reader reader = null;
        StringBuilder inputStringBuilder = new StringBuilder();
        reader = new InputStreamReader(stream, "UTF-8");
        BufferedReader bufferedReader = new 			 	BufferedReader(reader);
        String line = bufferedReader.readLine();
        while(line != null){
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        return inputStringBuilder.toString();
    }

}
