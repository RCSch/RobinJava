package com.example.robinjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PersonService
{
        @GET("Person/")
        Call<List<Person>> getAllPersoner();

        @GET("Person/{id}")
        Call<Person> getPersonById(@Path("id") int id);

        @POST("Person/")
        Call<Void> addElev(@Body Person e);
}
