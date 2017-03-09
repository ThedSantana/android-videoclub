package com.afpa.lduboeuf.videoclub.ws;

import android.os.AsyncTask;
import android.util.Log;

import com.afpa.lduboeuf.videoclub.AppProperties;
import com.afpa.lduboeuf.videoclub.Utils;
import com.afpa.lduboeuf.videoclub.model.TypeFilm;


import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ldf on 14/10/2015.
 */
public class TypeFilmProvider extends AsyncTask<String, Void, ArrayList<TypeFilm>> {


    @Override
    protected  ArrayList<TypeFilm> doInBackground(String... params) {
        ArrayList<TypeFilm> tFilms = null;



        try {
            URL url = new URL(AppProperties.URL + "/typefilm/");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            String response = Utils.convertStreamToString(urlConnection.getInputStream());
            Log.i("json response", response);

            urlConnection.disconnect();

            JSONArray new_array = new JSONArray(response);
            tFilms = new ArrayList<>(new_array.length());

            for (int i = 0, count = new_array.length(); i < count; i++) {

                JSONObject jsonObject = new_array.getJSONObject(i);
                String code = jsonObject.getString("CODE_TYPE_FILM");
                String libelle = jsonObject.getString("LIB_TYPE_FILM");
                tFilms.add(new TypeFilm(code, libelle));



            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return tFilms;
    }
}
