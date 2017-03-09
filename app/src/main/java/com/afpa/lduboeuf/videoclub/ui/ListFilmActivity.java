package com.afpa.lduboeuf.videoclub.ui;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afpa.lduboeuf.videoclub.AppProperties;
import com.afpa.lduboeuf.videoclub.R;
import com.afpa.lduboeuf.videoclub.Utils;
import com.afpa.lduboeuf.videoclub.adapters.FilmAdapter;
import com.afpa.lduboeuf.videoclub.model.Film;


import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListFilmActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_film);

        //get the selected type film
        Bundle extras = getIntent().getExtras();
        String type_film = extras.getString("type_film");

        try {

            FetchFilmTask fft = new FetchFilmTask();
            fft.execute(type_film);


        } catch (Exception e) {
            Log.e("APP", "exception", e);

        }


    }

    private class FetchFilmTask extends AsyncTask<String, Integer, ArrayList<Film>> {


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ListFilmActivity.this,"Recherche des films","Loading...");
        }

        @Override
        protected  ArrayList<Film> doInBackground(String... params) {
            ArrayList<Film> films = null;



            try {
                URL url = new URL(AppProperties.URL + "/films/?type_film=" + params[0]);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                String response = Utils.convertStreamToString(urlConnection.getInputStream());
                Log.i("json response", response);

                urlConnection.disconnect();

                JSONArray new_array = new JSONArray(response);
                films = new ArrayList<>(new_array.length());

                for (int i = 0, count = new_array.length(); i < count; i++) {

                    JSONObject jsonObject = new_array.getJSONObject(i);
                    int idFilm = jsonObject.getInt("ID_FILM");
                    String titreFilm = jsonObject.getString("TITRE_FILM");
                    String imageURL = jsonObject.getString("REF_IMAGE");

                    Film f = new Film(idFilm, titreFilm);
                    f.setImageURL(imageURL);
                    films.add(f);



                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            return films;
        }

        @Override
        protected void onPostExecute(ArrayList<Film> films) {
            ListView listView = (ListView) findViewById(R.id.listViewFilms);
            final FilmAdapter adapter = new FilmAdapter(ListFilmActivity.this, films);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Film film = adapter.getItem(position);
                    Log.i("selected id film:", String.valueOf(film.getIdFilm()));

                }
            });

            if (films.size()==0){
                Toast.makeText(getApplicationContext(),"Aucun film de trouvé",Toast.LENGTH_LONG).show();
            }

            progressDialog.dismiss();


        }


    }
}
