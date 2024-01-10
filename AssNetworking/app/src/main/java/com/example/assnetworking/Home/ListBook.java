package com.example.assnetworking.Home;

import android.app.Notification;
import android.app.SearchManager;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assnetworking.Home.API.ApiService;
import com.example.assnetworking.Home.Adapter.BookAdapter;
import com.example.assnetworking.Home.Model.Book;
import com.example.assnetworking.MainActivity;
import com.example.assnetworking.NotifyConfig;
import com.example.assnetworking.R;
import com.example.assnetworking.TransitionAnimation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListBook extends Fragment {
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private List<Book> bookList = new ArrayList<>();
    private BookAdapter bookAdapter;
    private MainActivity mainActivity;
    private Toolbar toolbar;
    private TextView tv_search_toolbar;
    private SearchView searchViewCustom;

    public ListBook() {
    }

    public static ListBook newInstance() {
        ListBook fragment = new ListBook();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_listbook);
        toolbar = view.findViewById(R.id.toolbar);
//        searchView = view.findViewById(R.id.search_view);


        layoutManager = new GridLayoutManager(getContext(), 2);

        bookAdapter = new BookAdapter(getActivity());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookAdapter);

        callApiGetDataBook();
        mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            BottomNavigationView bottomNavigationView = mainActivity.getBottomNavigationView();
            recyclerView.setOnTouchListener(new TransitionAnimation(getActivity(), bottomNavigationView));
            mainActivity.setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_toolbar, menu);
        MenuItem searchItem = menu.findItem(R.id.search_toolbar);
        View searchView = LayoutInflater.from(getContext()).inflate(R.layout.custom_searchview, null);
        searchItem.setActionView(searchView);
        tv_search_toolbar = searchView.findViewById(R.id.tv_search_toolbar);
        searchViewCustom = searchView.findViewById(R.id.searchViewCustom);
        tv_search_toolbar.setOnClickListener(view -> {
            ApiService.apiService.getListBooksFilter(searchViewCustom.getQuery().toString()).enqueue(new Callback<List<Book>>() {
                @Override
                public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                    if (response.isSuccessful()) {
                        bookList = response.body();
                        bookAdapter.setDataBook(bookList);
                    } else {
                        Toast.makeText(getActivity(), "Lỗi không lấy được danh sách truyện", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<List<Book>> call, Throwable t) {
                    Toast.makeText(getActivity(), "Lỗi không lấy được danh sách truyện", Toast.LENGTH_SHORT).show();
                }
            });
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void callApiGetDataBook() {
        ApiService.apiService.getListBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    mainActivity.setLoading(false);
                    bookList = response.body();
                    bookAdapter.setDataBook(bookList);
                } else {
                    Toast.makeText(getActivity(), "Lỗi không lấy được danh sách truyện", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                mainActivity.setLoading(true);
                Toast.makeText(getActivity(), "Lỗi không lấy được danh sách truyện", Toast.LENGTH_SHORT).show();
            }
        });
    }

}