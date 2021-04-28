package com.example.mvvmretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmretrofit.R;
import com.example.mvvmretrofit.model.Result;
import com.example.mvvmretrofit.view.MovieDetailsActivity;

import java.util.ArrayList;


public class ResultAdapter
        extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private Context context;
    private ArrayList<Result> results;

    public ResultAdapter(Context context, ArrayList<Result> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_list_item, parent, false);

        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        holder.titleTextView.setText(results.get(position).getOriginalTitle());
        holder.popularityTextView.setText(Double.toString(results.get(position)
                .getPopularity()));
        String imagePath = "https://image.tmdb.org/t/p/w500/" +
                results.get(position).getPosterPath();
//        Glide.with(context)
//                .load(imagePath)
//                .placeholder(R.drawable.progress_circle)
//                .into(holder.movieImageView);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView popularityTextView;
        public ImageView movieImageView;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            popularityTextView = itemView.findViewById(R.id.popularityTextView);
            movieImageView = itemView.findViewById(R.id.movieImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int postion = getAdapterPosition();

                    if (postion != RecyclerView.NO_POSITION) {

                        Result result = results.get(postion);
                        Intent intent = new Intent(context,
                                MovieDetailsActivity.class);
                        intent.putExtra("movieData", result);
                        context.startActivity(intent);


                    }

                }
            });
        }
    }
}
