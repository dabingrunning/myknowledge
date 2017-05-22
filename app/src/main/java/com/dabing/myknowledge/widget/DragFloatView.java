package com.dabing.myknowledge.widget;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.dabing.myknowledge.utils.DensityUtil;

/**
 * Created by MyKnowledge on 2017/5/22.
 */

public class DragFloatView extends AppCompatImageView {

    private Rect rect;

    public DragFloatView(Context context) {
        super(context);
        init(context);
    }

    public DragFloatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragFloatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private int screenWidth;
    private int screenHeight;
    private int screenWidthHalf;
    private int statusHeight;

    private void init(Context context){
        screenWidth= DensityUtil.getScreenHeightAndWidth(getContext()).x;
        screenWidthHalf=screenWidth/2;
        screenHeight=DensityUtil.getScreenHeightAndWidth(getContext()).y;
        statusHeight=DensityUtil.getStatusHeight(/*(Activity)*/ context);
//        ((ViewGroup)parent).getLocalVisibleRect(rect);

    }

    private int lastX;
    private int lastY;

    private boolean isDrag;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ViewParent parent = getParent();//在拖拽的时候获取父控件。在构造函数中获取时为null
        rect = new Rect();
        ((ViewGroup)parent).getLocalVisibleRect(rect);
        int top = ((ViewGroup) parent).getTop();
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        Point screenHeightAndWidth = DensityUtil.getScreenHeightAndWidth(getContext());

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                isDrag=false;
                getParent().requestDisallowInterceptTouchEvent(true);
                lastX=rawX;
                lastY=rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("父控件",parent+"可拖拽范围,宽-->"+rect.width()+"高——>"+rect.height()+"屏幕宽高"+screenHeightAndWidth.x+":"+screenHeightAndWidth.y+"状态栏高度"+statusHeight+"父控件的坐标"+top);
                isDrag=true;
                //计算手指移动了多少
                int dx=rawX-lastX;
                int dy=rawY-lastY;
                //这里修复一些华为手机无法触发点击事件的问题
                int distance= (int) Math.sqrt(dx*dx+dy*dy);
                if(distance==0){
                    isDrag=false;
                    break;
                }
                float x=getX()+dx;
                float y=getY()+dy;
                //检测是否到达边缘 左上右下
                x=x<0?0:x>screenWidth-getWidth()?screenWidth-getWidth():x;
                y=y<top?top:y+getHeight()>rect.height()?rect.height()-getHeight():y;
                if(y<top){
                    y = top;
                    return true;
                }else {
                    if(y+getHeight() > rect.height()){
                        y =  rect.height()-getHeight();
                    }
                }
                setX(x);
                setY(y);
                lastX=rawX;
                lastY=rawY;
                break;
            case MotionEvent.ACTION_UP:
                if(isDrag){
                    //恢复按压效果
                    setPressed(false);
                    if(rawX>=screenWidthHalf){
                        animate().setInterpolator(new DecelerateInterpolator())
                                .setDuration(500)
                                .xBy(screenWidth-getWidth()-getX())
                                .start();
                    }else {
                        ObjectAnimator oa= ObjectAnimator.ofFloat(this,"x",getX(),0);
                        oa.setInterpolator(new DecelerateInterpolator());
                        oa.setDuration(500);
                        oa.start();
                    }
                }
                break;
        }
        //如果是拖拽则消耗事件，否则正常传递即可。
        return isDrag || super.onTouchEvent(event);
    }
}
