package com.example.vande.moviezup.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
	private static final String NOME_BD = "TESTEZUP";
	private static final int VERSAO_BD = 1;

	public SQLiteHelper(Context ctx){
		super(ctx, NOME_BD, null, VERSAO_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase bd) {
		//TABELA FILMES
		bd.execSQL("CREATE TABLE filmes (imdbID TEXT PRIMARY KEY,"
									  	+ "title TEXT,"
									  	+ "year TEXT,"
									  	+ "type TEXT,"
									  	+ "poster TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
		bd.execSQL("DROP TABLE filmes;");
		onCreate(bd);
	}
}