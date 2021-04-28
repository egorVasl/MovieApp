package com.example.mvvmretrofit.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmretrofit.R;
import com.example.mvvmretrofit.service.MovieApiService;
import com.example.mvvmretrofit.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Result> results = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData =
            new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Result>> getMutableLiveData() {

        MovieApiService movieApiService = RetrofitInstance.getService();

        Call<MovieApiResponse> call = movieApiService
                .getPopularMovies(application.getApplicationContext()
                        .getString(R.string.api_key));

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<com.example.mvvmretrofit.model.MovieApiResponse> call,
                                   Response<MovieApiResponse> response) {

                com.example.mvvmretrofit.model.MovieApiResponse movieApiResponse =
                        response.body();

                if (movieApiResponse != null &&
                        movieApiResponse.getResults() != null) {

                    results = (ArrayList<Result>) movieApiResponse
                            .getResults();
                    mutableLiveData.setValue(results);

                }

            }

            @Override
            public void onFailure(Call<com.example.mvvmretrofit.model.MovieApiResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
