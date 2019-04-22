package com.example.vande.moviezup.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vande.moviezup.model.MovieData;

import java.util.ArrayList;
import java.util.List;


public class BD {
    private SQLiteDatabase bd;

    public BD(Context context){
        SQLiteHelper auxBd = new SQLiteHelper(context);
        bd = auxBd.getWritableDatabase();
    }

    public void inserirFilme(MovieData movie){
        ContentValues valores = new ContentValues();
        valores.put("imdbID", movie.getImdbID());
        valores.put("title", movie.getTitle());
        valores.put("year", movie.getYear());
        valores.put("type", movie.getType());
        valores.put("poster", movie.getPoster());

        bd.insert("filmes", null, valores);
    }

    public List<MovieData> listarFilmes(){
        List<MovieData> list = new ArrayList<MovieData>();
        String[] colunas = new String[]{"imdbID", "title", "year", "type", "poster"};

        Cursor cursor = bd.query("filmes", colunas, null, null, null, null, "title ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                MovieData movie = new MovieData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                list.add(movie);
            }while(cursor.moveToNext());
        }
        return(list);
    }
}
