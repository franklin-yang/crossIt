package com.example.android.crossit;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class PlayActivity extends Activity {

    private GridLayout board;
    private int[] bigOIds = {R.id.topLeft,R.id.topMid,R.id.topRight,
                    R.id.midLeft,R.id.midMid,R.id.midRight,
                    R.id.bottomLeft,R.id.bottomMid,R.id.bottomRight};
    private LinearLayout bottomPieceTray;
    private int cellClickedId;
    private int[] player1Color = {255,255,0,0};
    View piecePicker;
    View traditionalTray;


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

        bottomPieceTray = findViewById(R.id.bottomPieceTray);
        bottomPieceTray.getLayoutParams().height = gridSize;

//        bottomPieceTray.setOnClickListener(boardCellClicked);
        traditionalTray = getLayoutInflater().inflate(R.layout.pieces_available_tray,null);

        bottomPieceTray.addView(traditionalTray);
        piecePicker = getLayoutInflater().inflate(R.layout.piece_picker, null);

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
            bottomPieceTray.removeAllViews();
            cellClickedId = view.getId();
            bottomPieceTray.addView(piecePicker);

        }
    };

    public void whichSize(View view) {
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
    }
}

