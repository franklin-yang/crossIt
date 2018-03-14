package com.example.android.crossit;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class PlayActivity extends FragmentActivity {

    private GridLayout board;
    private int[] bigOIds = {R.id.topLeft,R.id.topMid,R.id.topRight,
            R.id.midLeft,R.id.midMid,R.id.midRight,
            R.id.bottomLeft,R.id.bottomMid,R.id.bottomRight};
    private LinearLayout bottomPieceTray;
    public static int cellClickedId;
    public static int[] player1Color = {255,255,0,0};
    private View piecePicker;
    private View traditionalTray;
    private View rootView;
    private int numPlayers;
    private int[][] piecesLeft;
    private View mPickSizeFragment;
    private View mPiecesAvailableFragment;
    private ViewPager bottomTray;
    private PagerAdapter pieceTrayAdapter;

    private static final int NUM_PAGES = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        board = findViewById(R.id.board);
        rootView = findViewById(R.id.playArea);
        rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        rootView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                Log.v("fd","Fj");
//                if(b){
//                    bottomPieceTray.removeAllViews();
//                }
//            }
//        });
//        rootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.v("clic","df");
//                view.requestFocus();
//            }
//        });
//
        //determine gridSize
        int playAreaHeight = Resources.getSystem().getDisplayMetrics().widthPixels / 3;
        int playAreaWidth = Resources.getSystem().getDisplayMetrics().heightPixels / 4;
        int gridSize = Math.min(playAreaHeight, playAreaWidth);

        class TrayAdapter extends FragmentPagerAdapter{

            TrayAdapter(FragmentManager fm){
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                Log.d(position+"","d");
                switch (position) {
                    case 0:
                        return PiecesAvailableFragment.newInstance(position);
                    case 1:
                        return PickSizeFragment.newInstance(position);
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        }



        bottomTray = findViewById(R.id.pager);
        bottomTray.getLayoutParams().height = gridSize;
        pieceTrayAdapter = new TrayAdapter(getSupportFragmentManager());
        bottomTray.setAdapter(pieceTrayAdapter);

//        //construct 3x3 grid
        addBoardSector(gridSize);

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
            bottomTray.setCurrentItem(1);
            cellClickedId = view.getId();
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


