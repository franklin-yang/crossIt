package com.example.android.crossit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.constraint.solver.widgets.Rectangle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;


/**
 * TODO: document your custom view class.
 */
public class CellView extends View {

    public static int SMALL = 0;
    public static int MEDIUM = 1;
    public static int LARGE = 2;

    private boolean[] sizes = {false,false,false};
    private int[][] colors = new int[3][4];


    public CellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean redrawNeeded = true;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.v("called","i");
        float minDim = Math.min(canvas.getHeight(),canvas.getWidth());
        Paint test = new Paint();
        for(int i = 0; i < sizes.length; i++){
            if(sizes[i]){
                float radius = 0;
                Paint correspondingColor = new Paint();
                correspondingColor.setStyle(Paint.Style.STROKE);
                switch (i) {
                    case 0: radius = 0.15f*minDim;
                                    correspondingColor.setStyle(Paint.Style.FILL);
                                    break;
                    case 1: radius = 0.3f*minDim;
                                    correspondingColor.setStrokeWidth(minDim/30);
                                    break;
                    case 2: radius = 0.4f*minDim;
                                    correspondingColor.setStrokeWidth(minDim/100);
                                    break;
                }
                Log.v("color", Arrays.toString(colors[i]));
                correspondingColor.setARGB(colors[i][0], colors[i][1], colors[i][2], colors[i][3]);
                canvas.drawCircle(minDim/2,minDim/2, radius, correspondingColor);

            }
        }
//
//        test.setARGB(255,255,0,0);
//        test.setStrokeWidth(5);
//        test.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(minDim/2,minDim/2, minDim/2, test);


    }

    public void addPiece(int sizeClicked, int[] playerColor) {
        if ((sizeClicked == SMALL && !sizes[0]) || (sizeClicked == MEDIUM && !sizes[1]) || (sizeClicked == LARGE && !sizes[2])){
            sizes[sizeClicked] = true;
            colors[sizeClicked] = playerColor;
            invalidate();
        }
    }
}
