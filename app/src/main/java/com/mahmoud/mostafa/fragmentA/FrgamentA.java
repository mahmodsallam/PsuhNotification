package com.mahmoud.mostafa.fragmentA;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mahmoud.mostafa.psuhnotification.R;

public class FrgamentA extends Fragment {

    public static FrgamentA newInstance() {
        FrgamentA fragment = new FrgamentA();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a , container , false) ;
        return  view ;
    }

}
