package com.Fricipe_2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class InformationFragment extends Fragment {


    public InformationFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Generiert das Layout für das Fragment
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

}
