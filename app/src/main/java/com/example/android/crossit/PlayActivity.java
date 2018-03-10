package com.example.android.crossit;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class PlayActivity extends Activity {

    private GridLayout board;
    private int[] bigOIds = {R.id.topLeft,R.id.topMid,R.id.topRight,
                    R.id.midLeft,R.id.midMid,R.id.midRight,
                    R.id.bottomLeft,R.id.bottomMid,R.id.bottomRight};
    private LinearLayout piecesAvailableTray;
    private Drawable sizePicked;
    private int cellClickedId;
    private int[] player1Color = {255,255,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        board = findViewById(R.id.board);

        //determine gridSize
        int playAreaHeight = Resources.getSystem().getDisplayMetrics().widthPixels / 3;
        int playAreaWidth = Resources.getSystem().getDisplayMetrics().heightPixels / 4;
        int gridSize = Math.min(playAreaHeight, playAreaWidth);

        //construct 3x3 grid
        addBoardSector(gridSize);

        piecesAvailableTray = findViewById(R.id.pieceTray);
        piecesAvailableTray.getLayoutParams().height = gridSize;
        piecesAvailableTray.setOnClickListener(boardCellClicked);
        piecesAvailableTray.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                }
                else{

                }
            }
        });

    }

    private void addBoardSector(int gridSize){
        for(int i = 0; i < 9; i++){
        View boardSector = getLayoutInflater().inflate(R.layout.sample_cell_view,null);
        board.addView(boardSector);
        boardSector.setId(bigOIds[i]);
        boardSector.getLayoutParams().height = gridSize;
        boardSector.getLayoutParams().width = gridSize;
        boardSector.setOnClickListener(boardCellClicked);
        }


    }

    View.OnClickListener boardCellClicked = new View.OnClickListener(){
        public void onClick(View view){
            view.requestFocus();
            piecesAvailableTray.removeAllViews();
            cellClickedId = view.getId();
            View piecePicker = getLayoutInflater().inflate(R.layout.piece_picker, piecesAvailableTray);
//            piecePicker.setOnClickListener(whatSize);

        }
    };

    View.OnClickListener whatSize = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
//            ImageView clicked = view.findViewById();
            CellView cellClicked = view.findViewById(cellClickedId);



        }
    };

//    private void pickPieceForCell(Drawable sizeSelected){
//        RelativeLayout cell = findViewById(cellClickedId);
//        ImageView cellImg = cell.findViewById(R.id.boardSectorImg);
//        cellImg.setImageDrawable(sizeSelected);
//
//    }

    public void whichSize(View view) {
        Log.v("which","size");
        int idClicked = view.getId();
        int sizeClicked;
        if (idClicked == R.id.big){
            sizeClicked = CellView.LARGE;
        }
        else if(idClicked == R.id.med){
            sizeClicked = CellView.MEDIUM;
        }
        else {
            sizeClicked = CellView.SMALL;
        }
        RelativeLayout cellPieceAddedTo = findViewById(cellClickedId);
        ((CellView)cellPieceAddedTo.findViewById(R.id.theCell)).addPiece(sizeClicked, player1Color);
//
    }

//    View.OnClickListener testOverlay = new View.OnClickListener(){
//        public void onClick(View view){
//
//            ViewGroup clicked = (ViewGroup) view;
//            ImageView test = new ImageView(PlayActivity.this);
//            test.setImageResource(R.drawable.med_blue);
//            ((ViewGroup) view).addView(test);
//        }
//    };
}

