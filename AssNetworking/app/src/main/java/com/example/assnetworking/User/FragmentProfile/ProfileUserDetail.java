package com.example.assnetworking.User.FragmentProfile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assnetworking.MainActivity;
import com.example.assnetworking.R;
import com.example.assnetworking.SharedPreferences.MySharedPreferences;
import com.example.assnetworking.User.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileUserDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileUserDetail extends Fragment {
    private Toolbar toolbar_profile;
    private TextView tv_fullname_profile, tv_email_profile;
    private User myUser;

    public ProfileUserDetail() {
        // Required empty public constructor
    }
    public static ProfileUserDetail newInstance() {
        ProfileUserDetail fragment = new ProfileUserDetail();
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
        return inflater.inflate(R.layout.fragment_profile_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar_profile = view.findViewById(R.id.toolbar_profile);
        tv_fullname_profile = view.findViewById(R.id.tv_fullname_profile);
        tv_email_profile = view.findViewById(R.id.tv_email_profile);

        myUser = MySharedPreferences.getAccount(getActivity());

        directToolBar();
        tv_fullname_profile.setText(myUser.getFullname());
        tv_email_profile.setText(myUser.getEmail());

    }
    private void directToolBar() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            BottomNavigationView bottomNavigationView = mainActivity.getBottomNavigationView();
            bottomNavigationView.setVisibility(View.GONE);
            mainActivity.setSupportActionBar(toolbar_profile);
            toolbar_profile.setNavigationOnClickListener(view1 -> {
                getActivity().onBackPressed();
            });
        }
    }
}