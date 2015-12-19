package cl.telematica.android.certamen2;

import java.util.List;

/**
 * Created by carlos on 11-12-2015.
 */
public class Libro {
    private String ID;
    private  String Jokes;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getJokes() {
        return Jokes;
    }

    public void setJokes(String jokes) {
        Jokes = jokes;
    }



    private List<Cast> castList;





    //se crea una clase interna para el cast array
    public static class Cast{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}