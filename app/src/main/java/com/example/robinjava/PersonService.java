package com.example.robinjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PersonService
{
        //Create
        @POST("Person/")
        Call<Void> addElev(@Body Person e);

        //Read
        @GET("Person/")
        Call<List<Person>> getAllPersoner();

        @GET("Person/{id}")
        Call<Person> getPersonById(@Path("id") int id);

        //Update
        @PUT("Person")
        Call<Void> updatePerson(@Body Person p);

        //Delete
        @DELETE("Person/{id}")
        Call<Void> deletePersonById(@Path("id") int id);
}
