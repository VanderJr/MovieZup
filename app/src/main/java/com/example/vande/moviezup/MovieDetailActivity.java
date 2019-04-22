package com.example.vande.moviezup;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import com.example.vande.moviezup.databinding.ActivityMovieDetailBinding;
import com.example.vande.moviezup.webservices.Api;

public class MovieDetailActivity extends AppCompatActivity {
    private ActivityMovieDetailBinding mBinding;
    String imdbID;
    Button BTadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        if (getIntent().hasExtra("imdbID")) {
            imdbID = getIntent().getStringExtra("imdbID");

            String params = "&i=" + imdbID;

            Api api = new Api(this);
            api.buscaDetalhe(params);
        } else {
            finish();
        }
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        switch (paramMenuItem.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(paramMenuItem);
        }
    }
}