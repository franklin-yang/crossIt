package com.example.android.crossit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PiecesAvailableFragment extends Fragment {


    public PiecesAvailableFragment() {
        // Required empty public constructor
    }

    public static PiecesAvailableFragment newInstance(int position) {

        Bundle args = new Bundle();

        PiecesAvailableFragment fragment = new PiecesAvailableFragment();
        fragment.setArguments(args);
//        fragment.
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pieces_available, container, false);
    }

}
