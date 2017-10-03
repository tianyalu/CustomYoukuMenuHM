package com.sty.youku.menu.utils;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Tianyalu on 2017/9/29/0029.
 */

public class AnimationUtils {

    public static int runningAnimationCount = 0;
    /**
     * 旋转出去的动画（逆时针）
     */
    public static void rotateOutAnim(RelativeLayout layout, long delay){
        int childCount = layout.getChildCount();
        for(int i = 0; i < childCount; i++){ //如果隐藏，则找到所有的子view,禁用
            layout.getChildAt(i).setEnabled(false);
        }

        RotateAnimation ra = new RotateAnimation(
                0f, -180f,  //开始角度，结束角度(减小表示逆时针)
                Animation.RELATIVE_TO_SELF, 0.5f, //相对x坐标点(指定旋转中心x值)
                Animation.RELATIVE_TO_SELF, 1.0f); //相对y坐标点(指定旋转中心y值)
        ra.setDuration(500); //持续时间
        ra.setFillAfter(true); //停留在动画结束位置
        ra.setStartOffset(delay); //设置动画执行延时时间
        ra.setAnimationListener(new MyAnimationListener()); //添加监听

        layout.startAnimation(ra);
    }

    /**
     * 旋转回来的动画（顺时针）
     */
    public static void rotateInAnim(RelativeLayout layout, long delay){
        int childCount = layout.getChildCount();
        for(int i = 0; i < childCount; i++){ //如果显示，则找到所有的子view,启用
            layout.getChildAt(i).setEnabled(true);
        }

        RotateAnimation ra = new RotateAnimation(
                -180f, 0f,  //开始角度，结束角度(增大表示顺时针)
                Animation.RELATIVE_TO_SELF, 0.5f, //相对x坐标点(指定旋转中心x值)
                Animation.RELATIVE_TO_SELF, 1.0f); //相对y坐标点(指定旋转中心y值)
        ra.setDuration(500); //持续时间
        ra.setFillAfter(true); //停留在动画结束位置
        ra.setStartOffset(delay); //设置动画执行延时时间
        ra.setAnimationListener(new MyAnimationListener()); //添加监听

        layout.startAnimation(ra);
    }

    static class MyAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            runningAnimationCount ++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            runningAnimationCount --;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
