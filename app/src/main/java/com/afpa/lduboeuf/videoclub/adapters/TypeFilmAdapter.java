package com.afpa.lduboeuf.videoclub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.afpa.lduboeuf.videoclub.R;
import com.afpa.lduboeuf.videoclub.model.TypeFilm;

import java.util.ArrayList;

/**
 * Created by ldf on 14/10/2015.
 */
public class TypeFilmAdapter extends ArrayAdapter<TypeFilm> {


    public TypeFilmAdapter(Context context, ArrayList<TypeFilm> typeFilms) {
        super(context, 0, typeFilms);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        TypeFilm tFilm = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_type_film, parent, false);

        }

        // Lookup view for data populatio

        TextView libelleTypeFilm = (TextView) convertView.findViewById(R.id.typeFilmLibelle);

        // Populate the data into the template view using the data object


        libelleTypeFilm.setText(tFilm.getLibelle());

        // Return the completed view to render on screen

        return convertView;

    }
}
