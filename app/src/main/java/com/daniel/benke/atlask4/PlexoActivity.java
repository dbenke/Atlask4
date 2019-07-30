package com.daniel.benke.atlask4;

/**
 * Created by Andrea on 06/02/2017.
 */

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TabHost;


public class PlexoActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {
    private FloatingActionButton fabz;
    private float scale = 0.5f;
    private ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7;
    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix();
    private float cx, cy;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plexo);
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);

        iv1 = (ImageView) findViewById(R.id.itab1);
        iv2 = (ImageView) findViewById(R.id.itab2);
        iv3 = (ImageView) findViewById(R.id.itab3);
        iv4 = (ImageView) findViewById(R.id.itab4);
        iv5 = (ImageView) findViewById(R.id.itab5);


        matrix.setScale(scale, scale);
        iv1.setImageMatrix(matrix);
        iv2.setImageMatrix(matrix);
        iv3.setImageMatrix(matrix);
        iv4.setImageMatrix(matrix);
        iv5.setImageMatrix(matrix);


        iv1.setScaleType(ImageView.ScaleType.FIT_START);
        iv2.setScaleType(ImageView.ScaleType.FIT_START);
        iv3.setScaleType(ImageView.ScaleType.FIT_START);
        iv4.setScaleType(ImageView.ScaleType.FIT_START);
        iv5.setScaleType(ImageView.ScaleType.FIT_START);


        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        setTitle("Plexo coróide");
        fabz = (FloatingActionButton) this.findViewById(R.id.fabzoom);
        this.findViewById(R.id.tabClean).setVisibility(View.VISIBLE);
        final HorizontalScrollView hs = (HorizontalScrollView) findViewById(R.id.horizontal);
        TabHost host = (TabHost) findViewById(R.id.tabHost);
        String current = host.getCurrentTabTag();

        if (current == "Zoom") {
            findViewById(R.id.fabzoom).setVisibility(View.VISIBLE);
        } else findViewById(R.id.fabzoom).setVisibility(View.INVISIBLE);

        fabz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabHost host = (TabHost) findViewById(R.id.tabHost);
                String current = host.getCurrentTabTag();



                if (current=="Zoom") {

                    Intent nextScreen = new Intent(getApplicationContext(), PlexoZoomActivity.class);
                    startActivity(nextScreen);}

            }
        });

        host.setup();
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");

        //Tab 2
        spec = host.newTabSpec("plexo coróide");
        spec.setContent(R.id.tab2);
        spec.setIndicator("plexo coróide");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("epitélio");
        spec.setContent(R.id.tab3);
        spec.setIndicator("epitélio");
        host.addTab(spec);

        //Tab 4
        spec = host.newTabSpec("conjuntivo");
        spec.setContent(R.id.tab4);
        spec.setIndicator("conjuntivo");
        host.addTab(spec);

        //Tab 5
        spec = host.newTabSpec("Zoom");
        spec.setContent(R.id.tab5);
        spec.setIndicator("zoom");
        host.addTab(spec);


        ViewTreeObserver vto = hs.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                hs.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        hs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("ScrollValue", Integer.toString(hs.getScrollX()));

                if (hs.getScrollX() == 0) {
                    findViewById(R.id.previous).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.previous).setVisibility(View.VISIBLE);
                }

                if (hs.getScrollX() >= hs.getChildAt(0).getMeasuredWidth() - getWindowManager().getDefaultDisplay().getWidth()) {
                    findViewById(R.id.next).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.next).setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                findViewById(R.id.tabClean).setVisibility(View.INVISIBLE);
                TabHost host = (TabHost) findViewById(R.id.tabHost);
                String current = host.getCurrentTabTag();

                WexternalActivity log = new WexternalActivity();
                log.Write(getApplicationContext(),"plexo"+current+", ");

                if (current=="Zoom") {
                    findViewById(R.id.fabzoom).setVisibility(View.VISIBLE);
                }
                else findViewById(R.id.fabzoom).setVisibility(View.INVISIBLE);

//Wext2Activity.writelog("oioi",Wext2Activity.);




            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_laminas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.laminas_action_settings:
                // Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT).show();
                Intent nextScreen = new Intent(getApplicationContext(), PlexoTextoActivity.class);
                startActivity(nextScreen);
                break;
            default:
                break;
        }

        return true;
    }

    float prevX, prevY;


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        scaleGestureDetector.onTouchEvent(ev);

        if (this.mDetector.onTouchEvent(ev)) {
            return true;
        }


        float currX = ev.getX(), currY = ev.getY();



        switch (ev.getAction()) {

            //case MotionEvent.ACTION_DOWN:
            //  prevX = ev.getX();
             // prevY = ev.getY();
            //  Log.i("D ON TOUCH EVENT", "DOWN + X: " + preX + " Y " + preY);

            //  break;


            case MotionEvent.ACTION_MOVE:
                //    Log.i("M ON TOUCH EVENT", "DOWN + X: " + preX + " Y " + preY);
             //      currX = ev.getX();
             //      currY = ev.getY();


                if (iv3.getScrollX() + (prevX - currX) > 990){
                    iv1.scrollBy((int) (prevX - currX), 0);
                    iv2.scrollBy((int) (prevX - currX), 0);
                    iv3.scrollBy((int) (prevX - currX), 0);
                    iv4.scrollBy((int) (prevX - currX), 0);
                    iv5.scrollBy((int) (prevX - currX), 0);

                }

                if (iv3.getScrollY() + (prevY - currY) >990){
                //if (iv3.getScrollX()+(prevX - currX)>760*scale/2) currX=prevX;
                //if (iv3.getScrollX()+(prevY - currY)>760/scale) currY=prevY;
                iv1.scrollBy(0, (int) (prevY - currY));
                iv2.scrollBy(0, (int) (prevY - currY));
                iv3.scrollBy(0, (int) (prevY - currY));
                iv4.scrollBy(0, (int) (prevY - currY));
                iv5.scrollBy(0, (int) (prevY - currY));

                }


/*
//                if (iv3.getScrollX() + (prevX - currX) < 1) break; //currX = prevX;
//                if (iv3.getScrollY() + (prevY - currY) < 1) break; //currY = prevY;

                //if (iv3.getScrollX()+(prevX - currX)>760*scale/2) currX=prevX;
                //if (iv3.getScrollX()+(prevY - currY)>760/scale) currY=prevY;
                iv1.scrollBy((int) (prevX - currX), (int) (prevY - currY));
                iv2.scrollBy((int) (prevX - currX), (int) (prevY - currY));
                iv3.scrollBy((int) (prevX - currX), (int) (prevY - currY));
                iv4.scrollBy((int) (prevX - currX), (int) (prevY - currY));
                iv5.scrollBy((int) (prevX - currX), (int) (prevY - currY));
                iv6.scrollBy((int) (prevX - currX), (int) (prevY - currY));
                iv7.scrollBy((int) (prevX - currX), (int) (prevY - currY));
*/
                //  preX = currentX;
                //  preY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                //   Log.i("U ON TOUCH EVENT", "DOWN + X: " + preX + " Y " + preY);
                //   currentX = event.getX();
                //   currentY = event.getY();
                //   iv3.scrollBy((int) (preX - currentX-10), (int) (preY - currentY));
                break;
        }
        prevX = currX;
        prevY = currY;
        cx = currX;
        cy = currY;
        //iv3.invalidate();
        return true;

    }

    private class ScaleListener extends ScaleGestureDetector.
            SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            scale *= detector.getScaleFactor();
            scale = Math.max(0.45f, Math.min(scale, 3.0f));

            //matrix.setTranslate(scaleGestureDetector.getFocusX(),scaleGestureDetector.getFocusY());
            //matrix.setScale(scale, scale, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            matrix.setScale(scale, scale);
            //float scaleFactor = detector.getScaleFactor();
            //scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
            //matrix.setScale(scaleFactor, scaleFactor);

            //this.findViewById(R.id.tabClean).setImageMatrix(matrix);
        /*    iv1.setX(cx);
            iv2.setX(cx);
            iv3.setX(cx);
            iv4.setX(cx);
            iv1.setY(cy);
            iv2.setY(cy);
            iv3.setY(cy);
            iv4.setY(cy);
*/
            iv1.setImageMatrix(matrix);
            iv2.setImageMatrix(matrix);
            iv3.setImageMatrix(matrix);
            iv4.setImageMatrix(matrix);
            iv5.setImageMatrix(matrix);


/*
            iv1.setX(0);
            iv2.setX(0);
            iv3.setX(0);
            iv4.setX(0);
            iv1.setY(0);
            iv2.setY(0);
            iv3.setY(0);
            iv4.setY(0);
*/
            return true;
        }
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        scale = 0.5f;
        matrix.setScale(scale, scale);
        iv1.setImageMatrix(matrix);
        iv2.setImageMatrix(matrix);
        iv3.setImageMatrix(matrix);
        iv4.setImageMatrix(matrix);
        iv5.setImageMatrix(matrix);


        return false;
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }


}