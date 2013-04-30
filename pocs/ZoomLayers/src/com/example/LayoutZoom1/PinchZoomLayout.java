package com.example.LayoutZoom1;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class PinchZoomLayout extends Activity {
    View mainView = null;

    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();

    float oldDist = 1f;
    PointF oldDistPoint = new PointF();

    public static String TAG = "ZOOM";

    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mainView = (LinearLayout) findViewById(R.id.linearLayout);

       /* Button buttonZoomOut = (Button) findViewById(R.id.buttonZoomOut);
        Button buttonNormal = (Button) findViewById(R.id.buttonNormal);
        Button buttonZoomIn = (Button) findViewById(R.id.buttonZoomIn);

        buttonZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoom(0.4f, 0.4f, new PointF(0, 0));
            }
        });
        buttonNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoom(1f, 1f, new PointF(0, 0));
            }
        });
        buttonZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoom(2f, 2f, new PointF(0, 0));
            }
        });             */

        mainView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "mode=DRAG");
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        start.set(event.getX(), event.getY());
                        Log.d(TAG, "mode=DRAG");
                        mode = DRAG;

                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        oldDistPoint = spacingPoint(event);
                        Log.d(TAG, "oldDist=" + oldDist);
                        if (oldDist > 10f) {
                            midPoint(mid, event);
                            mode = ZOOM;
                            Log.d(TAG, "mode=ZOOM");
                        }
                        System.out.println("current time :" + System.currentTimeMillis());
                        break;// return !gestureDetector.onTouchEvent(event);
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        Log.d(TAG, "mode=NONE");
                        mode = NONE;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {

                        } else if (mode == ZOOM) {
                            PointF newDist = spacingPoint(event);
                            float newD = spacing(event);
                            Log.e(TAG, "newDist=" + newDist);
                            float[] old = new float[9];
                            float[] newm = new float[9];
                            Log.e(TAG, "x=" + old[0] + ":&:" + old[2]);
                            Log.e(TAG, "y=" + old[4] + ":&:" + old[5]);
                            float scale = newD / oldDist;
                            float scalex = newDist.x / oldDistPoint.x;
                            float scaley = newDist.y / oldDistPoint.y;
                            zoom(scale, scale, start);
                        }
                        break;
                }
                return true;
            }
        });
    }

    /**
     * zooming is done from here
     */
    public void zoom(Float scaleX, Float scaleY, PointF pivot) {
       // mainView.setPivotX(pivot.x);
      //  mainView.setPivotY(pivot.y);
        mainView.setScaleX(scaleX);
        mainView.setScaleY(scaleY);
    }

    /**
     * space between the first two fingers
     */
    private float spacing(MotionEvent event) {
        // ...
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    private PointF spacingPoint(MotionEvent event) {
        PointF f = new PointF();
        f.x = event.getX(0) - event.getX(1);
        f.y = event.getY(0) - event.getY(1);
        return f;
    }

    /**
     * the mid point of the first two fingers
     */
    private void midPoint(PointF point, MotionEvent event) {
        // ...
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
}
