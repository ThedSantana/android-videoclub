package com.afpa.lduboeuf.videoclub;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.afpa.lduboeuf.videoclub.adapters.TypeFilmAdapter;
import com.afpa.lduboeuf.videoclub.model.TypeFilm;
import com.afpa.lduboeuf.videoclub.ui.ListFilmActivity;
import com.afpa.lduboeuf.videoclub.ws.TypeFilmProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check if you are connected or not
        if (isConnected()) {

            ArrayList<TypeFilm> tFilms = null;
            try {
                TypeFilmProvider fp = new TypeFilmProvider();
                fp.execute();
                //TODO not a good practice
                tFilms = fp.get();
                ListView listView = (ListView) findViewById(R.id.listViewTypeFilm);
                final TypeFilmAdapter adapter = new TypeFilmAdapter(this, tFilms);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TypeFilm tfilm = adapter.getItem(position);

                        Log.i("selected id film:", tfilm.getCode());
                        Intent i = new Intent(getApplicationContext(), ListFilmActivity.class);
                        i.putExtra("type_film", tfilm.getCode());
                        //i.putExtra("data","kikou me voilà");
                        startActivity(i);

                    }
                });
            } catch (Exception e) {
                Log.e("APP", "exception", e);
                displayAlert("Unable to fetch data from server, please check service is running");
            }

        } else {
            displayAlert("Not connected");

        }

    }




    private void displayAlert(String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            showAbout();
        }

        return super.onOptionsItemSelected(item);
    }


    // check network connection
    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


    private void showAbout(){

        AlertDialog.Builder aboutDialogBuilder = new AlertDialog.Builder(this);
        aboutDialogBuilder.setTitle("Videoclub");
        aboutDialogBuilder.setMessage("Bienvenue sur cette mini-app de collection de film, blablabla..., thumbnails from http://www.themoviedb.org/");
        aboutDialogBuilder.setCancelable(false);
        aboutDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog aboutDialog = aboutDialogBuilder.create();
        aboutDialog.show();


    }

}
