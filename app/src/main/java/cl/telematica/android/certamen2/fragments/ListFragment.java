package cl.telematica.android.certamen2.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.ArrayList;
import java.util.List;

import cl.telematica.android.certamen2.HttpServerConnection;
import cl.telematica.android.certamen2.Libro;
import cl.telematica.android.certamen2.R;
import cl.telematica.android.certamen2.UIAdapter;

public class ListFragment extends Fragment {
    private String[] myStringArray = new String[]{"HOLA","MUNDO","SOY", "UNA", "LISTA"};

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     * New instance of ListFragment
     *
     * @return new instance of ListFragment
     */
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mainView = inflater.inflate(R.layout.fragment_list, null);

        /*
         * Aquí es donde deben hacer el link a los elementos del layout fragment_list,
         * y donde prácticamente se debe hacer el desarrollo
        */
        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);





        new JSONTask().execute();
        // specify an adapter (see also next example)


        return mainView;
    }
    // se habia colocado primer parametro como URL
    public class JSONTask extends AsyncTask<String,String, String> {




        @Override
        protected String doInBackground(String... params) {
            String resultado = new HttpServerConnection().connectToServer("http://api.icndb.com/jokes/random/20",1500);
            return resultado;
        }

        protected void onPostExecute(String result){
            if(result != null){
                System.out.println(result);
                mAdapter = new UIAdapter(getLista(result));
                mRecyclerView.setAdapter(mAdapter);
            }
        }


    }

    private List<Libro> getLista(String result){
        List<Libro> listaLibros = new ArrayList<>();

        try {
            //JSONArray lista = new JSONArray(result);
            //int size = lista.length();

            JSONObject parentObject = new JSONObject(result);
            JSONArray parentArray = parentObject.getJSONArray("value");

            for( int i = 0; i<parentArray.length(); i++){
                Libro libro = new Libro();
                JSONObject objeto = parentArray.getJSONObject(i);

                ;
                libro.setID("ID: "+objeto.getString("id"));
                libro.setJokes(objeto.getString("joke"));

                listaLibros.add(libro);
            }
            return listaLibros;

        } catch (JSONException e) {
            e.printStackTrace();
            return listaLibros;
        }
    }

}
