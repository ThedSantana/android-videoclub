package com.afpa.lduboeuf.videoclub.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.afpa.lduboeuf.videoclub.R;
import com.afpa.lduboeuf.videoclub.model.Film;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;


/**
 * Created by ldf on 13/10/2015.
 */
public class FilmAdapter extends ArrayAdapter<Film> {


    public FilmAdapter(Context context, ArrayList<Film> films) {
        super(context, 0, films);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        Film film = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_film, parent, false);

        }

        // Lookup view for data population

        TextView titreFilm = (TextView) convertView.findViewById(R.id.titreFilm);
        final ImageView image = (ImageView) convertView.findViewById(R.id.filmImage);




        //load remote image
        if (film.getImageURL()!=null && !film.getImageURL().isEmpty()){
            Log.i("image url:", film.getImageURL());
            Picasso.with(this.getContext()).load(film.getImageURL()).placeholder(R.mipmap.placeholder_movie).into(image);

        }

        titreFilm.setText(film.getTitreFilm());

        // Return the completed view to render on screen

        return convertView;

    }
}
