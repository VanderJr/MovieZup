package com.example.vande.moviezup;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.vande.moviezup.databinding.ActivityMoviesOfflineBinding;
import com.example.vande.moviezup.databinding.ListItemMovieBinding;
import com.example.vande.moviezup.model.MovieData;
import com.example.vande.moviezup.sqlite.BD;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class MoviesOfflineActivity extends AppCompatActivity {
    private ActivityMoviesOfflineBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies_offline);

        BD bd = new BD(this);

        List<MovieData> list = bd.listarFilmes();
        List localArrayList = new ArrayList();

        for (MovieData md : list) {
            localArrayList.add(new MovieData(md.getImdbID(), md.getTitle(), md.getYear(), md.getType(), md.getPoster()));
        }

        MovieAdapter adapter = new MovieAdapter();
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.update(localArrayList);
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

    class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
        private List<MovieData> movies;

        public MovieAdapter() {
            this.movies = new ArrayList<>();
        }

        public void update(List<MovieData> data) {
            this.movies = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ListItemMovieBinding binding = ListItemMovieBinding.inflate(LayoutInflater.from(getApplicationContext()));
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.bindTo(movies.get(i));
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private ListItemMovieBinding binding;

            public ViewHolder(ListItemMovieBinding item) {
                super(item.getRoot());
                binding = item;
            }

            public void bindTo(final MovieData data) {
                binding.TVnome.setText(data.getTitle());
                binding.TVano.setText(data.getYear());
                binding.TVtipo.setText(data.getType());

                Picasso.with(getApplicationContext())
                        .load(data.getPoster())
                        .fit()
                        .into(binding.IVposter);
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MovieDetailActivity.class);
                        intent.putExtra("imdbID", data.getImdbID());
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
