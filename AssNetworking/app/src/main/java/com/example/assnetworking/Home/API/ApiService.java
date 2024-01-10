package com.example.assnetworking.Home.API;

import com.example.assnetworking.Comment.Comments;
import com.example.assnetworking.Comment.PostComments;
import com.example.assnetworking.DetailBook.Model.Comment;
import com.example.assnetworking.Home.Model.Book;
import com.example.assnetworking.User.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setLenient().setDateFormat("dd-MM-yyyy").create();
    ApiService apiService = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(ApiService.class);

    @GET("users/login/{username}/{passwd}")
    Call<User> getUserLogin(@Path("username") String username, @Path("passwd") String passwd);

    @POST("users/register")
    Call<User> registerUser(@Body User user);

    @GET("books")
    Call<List<Book>> getListBooks();

    @GET("books/filter")
    Call<List<Book>> getListBooksFilter(@Query("bookname") String bookname);

    @GET("books")
    Call<Book> getDetailBook(@Query("_id") String _id);

    @GET("books")
    Call<Book> getListImage(@Query("_id") String _id, @Query("read") String read);

    @GET("comments")
    Call<List<Comments>> getListComments(@Query("_id") String _id);

    @POST("comments/cmt")
    Call<PostComments> sendComment(@Body PostComments postComments);
}
