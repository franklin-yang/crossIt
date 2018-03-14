package com.example.android.crossit;


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
            int sizeClicked;
            if (idClicked == R.id.big) {
                sizeClicked = CellView.LARGE;
            } else if (idClicked == R.id.med) {
                sizeClicked = CellView.MEDIUM;
            } else {
                sizeClicked = CellView.SMALL;
            }
            RelativeLayout cellPieceAddedTo = getActivity().findViewById(PlayActivity.cellClickedId);
            ((CellView) cellPieceAddedTo.findViewById(R.id.theCell)).addPiece(sizeClicked, PlayActivity.player1Color);
        }
    };
}
