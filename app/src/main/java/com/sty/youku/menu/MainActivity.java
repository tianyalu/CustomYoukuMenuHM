package com.sty.youku.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.sty.youku.menu.utils.AnimationUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton ibHome;
    private ImageButton ibMenu;
    private ImageButton ibTest;

    private RelativeLayout rlLevel1;
    private RelativeLayout rlLevel2;
    private RelativeLayout rlLevel3;
    boolean isLevel1Display = true;
    boolean isLevel2Display = true;
    boolean isLevel3Display = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
    }

    private void initViews(){
        ibHome = (ImageButton) findViewById(R.id.ib_home);
        ibMenu = (ImageButton) findViewById(R.id.ib_menu);
        rlLevel1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rlLevel2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rlLevel3 = (RelativeLayout) findViewById(R.id.rl_level3);
    }

    private void setListeners(){
        ibHome.setOnClickListener(this);
        ibMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(AnimationUtils.runningAnimationCount > 0){
            //当前有动画正在执行，取消当前事件
            return;
        }
        switch (view.getId()){
            case R.id.ib_home:
                if(isLevel2Display){ //如果当前二级菜单已经显示，转出去
                    long delay = 0;
                    if(isLevel3Display){ //当三级菜单已经显示时，先转出去
                        AnimationUtils.rotateOutAnim(rlLevel3, 0);
                        isLevel3Display = false;
                        delay += 200;
                    }
                    AnimationUtils.rotateOutAnim(rlLevel2, delay);
                }else{ //如果当前二级菜单没有显示，转回来
                    AnimationUtils.rotateInAnim(rlLevel2, 0);
                }
                isLevel2Display = !isLevel2Display; //置反
                break;
            case R.id.ib_menu:
                if(isLevel3Display){ //如果当前三级菜单已经显示，转出去
                    AnimationUtils.rotateOutAnim(rlLevel3, 0);
                }else{ //如果当前三级菜单没有显示，转回来
                    AnimationUtils.rotateInAnim(rlLevel3, 0);
                }
                isLevel3Display = !isLevel3Display;
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //KeyCode事件码
        Log.i("Tag", "onKey down:-->" + keyCode);
        if(keyCode == KeyEvent.KEYCODE_MENU){ //如果按下的是菜单按钮
            if(AnimationUtils.runningAnimationCount > 0){
                //当前有动画正在执行，取消当前事件
                return true;
            }

            if(isLevel1Display){
                long delay = 0;
                if(isLevel3Display){ //隐藏三级菜单
                    AnimationUtils.rotateOutAnim(rlLevel3, 0);
                    isLevel3Display = false;
                }

                if(isLevel2Display){ //隐藏二级菜单
                    AnimationUtils.rotateOutAnim(rlLevel2, delay);
                    isLevel2Display = false;
                    delay += 200;
                }

                AnimationUtils.rotateOutAnim(rlLevel1, delay); //隐藏二级菜单
            }else{
                AnimationUtils.rotateInAnim(rlLevel1, 0);
                AnimationUtils.rotateInAnim(rlLevel2, 200);
                AnimationUtils.rotateInAnim(rlLevel3, 400);
            }
        }
        return true; //表示消费了当前事件
    }
}
