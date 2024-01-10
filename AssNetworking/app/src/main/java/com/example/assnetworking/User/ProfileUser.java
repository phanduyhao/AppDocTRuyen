package com.example.assnetworking.User;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.assnetworking.LoginActivity;
import com.example.assnetworking.MainActivity;
import com.example.assnetworking.R;
import com.example.assnetworking.ReadBook.ReadBook;
import com.example.assnetworking.SharedPreferences.MySharedPreferences;
import com.example.assnetworking.User.FragmentProfile.ProfileUserDetail;
import com.example.assnetworking.User.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileUser extends Fragment {
    private TextView tv_name_profile;
    private LinearLayout tv_profile, tv_logout;
    private User myUser;

    public ProfileUser() {
        // Required empty public constructor
    }

    public static ProfileUser newInstance() {
        ProfileUser fragment = new ProfileUser();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_name_profile = view.findViewById(R.id.tv_name_profile);
        tv_profile = view.findViewById(R.id.tv_profile);
        tv_logout = view.findViewById(R.id.tv_logout);
        myUser = MySharedPreferences.getAccount(getActivity());
        tv_name_profile.setText(myUser.getFullname());
        tv_profile.setOnClickListener(view1 -> {
            FragmentManager fragmentManager = ((AppCompatActivity) view.getContext()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.layout_content, ProfileUserDetail.newInstance()).addToBackStack(null).commit();
        });
        tv_logout.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Đăng xuất");
            builder.setMessage("Bạn có chắc muốn đăng xuất không?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }
            });
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
        ShowBottomNavigation();
    }

    private void ShowBottomNavigation() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            BottomNavigationView bottomNavigationView = mainActivity.getBottomNavigationView();
            if (bottomNavigationView.getVisibility() == View.GONE){
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        }
    }
}