package com.example.android.crossit;


import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class PickSizeFragment extends Fragment {


    public PickSizeFragment() {
        // Required empty public constructor
    }

    public static PickSizeFragment newInstance(int position) {

        Bundle args = new Bundle();

        PickSizeFragment fragment = new PickSizeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pick_size, container, false);
        ViewGroup sizeTray = rootView.findViewById(R.id.pick_size_tray);
//        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAP
        for (int i = 0; i < sizeTray.getChildCount(); i++){
            if(sizeTray.getChildAt(i) instanceof ImageView){
                sizeTray.getChildAt(i).setOnClickListener(trySize);
            }

        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    View.OnClickListener trySize = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            view.requestFocus();
            int idClicked = view.getId();
            RelativeLayout cellPieceAddedTo = getActivity().findViewById(PlayActivity.cellClickedId);
            ImageView toChange = cellPieceAddedTo.findViewById(idClicked);
            toChange.setColorFilter(Color.argb(PlayActivity.player1Color[0],PlayActivity.player1Color[1],PlayActivity.player1Color[2],PlayActivity.player1Color[3]));
            toChange.setVisibility(View.VISIBLE);

//            ((CellView) cellPieceAddedTo.(CellView.addPiece(sizeClicked, PlayActivity.player1Color));
        }
    };
}
