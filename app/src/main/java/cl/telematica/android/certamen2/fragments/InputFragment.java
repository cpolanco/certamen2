package cl.telematica.android.certamen2.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cl.telematica.android.certamen2.HttpServerConnection;
import cl.telematica.android.certamen2.R;
import cl.telematica.android.certamen2.User;

public class InputFragment extends Fragment implements  View.OnClickListener{

    EditText etNombre,etApellido;
    Button bParseado;
    TextView textView;
    RequestQueue requestQueue;
    /**
     * New instance of InputFragment
     *
     * @return new instance of InputFragment
     */
    public static InputFragment newInstance() {
        InputFragment fragment = new InputFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mainView = inflater.inflate(R.layout.fragment_input, null);

        /*
         * Aquí es donde deben hacer el link a los elementos del layout fragment_input,
         * y donde prácticamente se debe hacer el desarrollo
        */


        return mainView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etNombre = (EditText) view.findViewById(R.id.etNombre);
        etApellido = (EditText) view.findViewById(R.id.etApellido);
        textView = (TextView) view.findViewById(R.id.textView);
        //requestQueue = Volley.newRequestQueue(InputFragment.class);
        bParseado= (Button)  view.findViewById(R.id.bParseado);
        bParseado.setOnClickListener(this);
    }


    // se habia colocado primer parametro como URL
    public class JSONTask extends AsyncTask<String,String, String> {


        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection=null;
            BufferedReader reader = null;
            try {

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine())!= null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONObject parentArray = parentObject.getJSONObject("value");
                StringBuffer finalBufferedData = new StringBuffer();

                    String joke = parentArray.getString("joke");

                    finalBufferedData.append(joke);



                return finalBufferedData.toString();





            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally{
                connection.disconnect();
                try {
                    if(reader !=null)
                    {
                        reader.close();}

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //tvData.setText(result);
            textView.setText(result);
        }
    }


    @Override
    public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.bParseado:
                        String nombre = etNombre.getText().toString();
                        String apellido = etApellido.getText().toString();
                        User user = new User(nombre, apellido);

                        new JSONTask().execute("http://api.icndb.com/jokes/random?firstName="+nombre+"&lastName="+apellido);
                        break;


                }
            }

    }

