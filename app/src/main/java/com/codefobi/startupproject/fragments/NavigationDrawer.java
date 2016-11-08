package com.codefobi.startupproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codefobi.startupproject.R;

/**
 * Created by tosantechnolocal on 11/3/2016.
 */
public class NavigationDrawer extends Fragment {

    ImageView navBackground;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        navBackground = (ImageView) view.findViewById(R.id.nav_imageView);

        Glide.with(this).load(R.drawable.nav_tempo_bg).dontAnimate().into(navBackground);

        return view;
    }
}
