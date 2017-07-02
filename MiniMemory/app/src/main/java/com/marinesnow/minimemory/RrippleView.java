package com.marinesnow.minimemory;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by mei on 2017/4/8.
 */

public class RrippleView extends Button{
    private float touchx = 0,touchy = 0;
    private ValueAnimator valueanimator,dismissa;
    private float radius;
    private float cr = 0;
    private int calpha = 255;
    private int width,height;
    private int rippleColor,backgroungColor,selectedColor;
    private Resources res;
    private Paint ripplePaint;
    private RectF rect;

    public RrippleView(Context context) {
        this(context,null);
    }

    public RrippleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RrippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){

        setPadding(10,10,10,10);
        res = getContext().getResources();
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.RrippleView);
        backgroungColor = typedArray.getColor(R.styleable.RrippleView_backgroung_color,res.getColor(R.color.holo));
        selectedColor = typedArray.getColor(R.styleable.RrippleView_selected_color,res.getColor(R.color.colorAccent));
        rippleColor = typedArray.getColor(R.styleable.RrippleView_ripple_color,res.getColor(R.color.colorAccent));
        ripplePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ripplePaint.setColor(rippleColor);

        dismissa = new ValueAnimator().ofInt(255,0);
        dismissa.setDuration(300);
        dismissa.addUpdateListener(new dismissUpdator());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ripplePaint.setAlpha(calpha);
        rect = new RectF(0,0,width,height);
        Path path = new Path();
        path.addRoundRect(rect,30f,30f, Path.Direction.CW);

        canvas.clipPath(path);
        canvas.drawColor(backgroungColor);
        canvas.drawCircle(touchx,touchy,cr,ripplePaint);
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        width = getWidth();
        height = getHeight();
        super.onLayout(b,i,i1,i2,i3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchx = event.getX();
                touchy = event.getY();
                prepareA();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void prepareA(){
        if(touchx>width/2){
            if(touchy > height/2)
                radius = (float) Math.sqrt(Math.pow(touchx,2) + Math.pow(touchy,2));
            else
                radius = (float)Math.sqrt(Math.pow(touchx,2) + Math.pow(height-touchy,2));
        }else{
            if(touchy > height/2)
                radius = (float)Math.sqrt(Math.pow(width-touchx,2) + Math.pow(touchy,2));
            else
                radius = (float)Math.sqrt(Math.pow(width-touchx,2) + Math.pow(height-touchy,2));
        }
        valueanimator = new ValueAnimator().ofFloat(0,radius);
        valueanimator.setDuration(500);
        valueanimator.addUpdateListener(new rippleUpdater());
        valueanimator.start();
    }

    public void performA(float rawX,float rawY){
        touchx = rawX - getX();
        touchy = rawY - getY();
        prepareA();
        EditText editText;
    }

    class rippleUpdater implements ValueAnimator.AnimatorUpdateListener
    {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            cr = ((float) valueAnimator.getAnimatedValue());
            RrippleView.this.invalidate();
            if(cr == radius){
                dismissa.start();
            }
        }

    }

    class dismissUpdator implements ValueAnimator.AnimatorUpdateListener{
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            calpha = (int) valueAnimator.getAnimatedValue();
            RrippleView.this.invalidate();
            if(calpha == 0){
                cr = 0;
                calpha = 255;
            }
        }
    }
}
