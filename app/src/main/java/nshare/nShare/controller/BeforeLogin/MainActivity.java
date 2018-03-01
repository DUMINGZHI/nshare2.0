package nshare.nShare.controller.BeforeLogin;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.payge.swipecardview.R;


public class MainActivity extends AppCompatActivity {

    //依靠ButterKnife的控件初始化
    @BindView(R.id.countDown)
    Button btnCountDown;
    @BindView(R.id.applogo)
    ImageView applogo;
    private Handler handlerCountDown;
    private int countDownTime = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yao);
        //绑定ButterKnife注入框架
        ButterKnife.bind(this);

        ObjectAnimator animator = ObjectAnimator.ofFloat(applogo, "alpha",0f,1f);
        animator.setDuration(2000);
        animator.start();

        //handler模拟3秒倒计时功能
        handlerCountDown = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0) {
                    //如果3秒倒计时还没走完
                    if(countDownTime>0) {
                        btnCountDown.setText("[" + countDownTime + "s]跳过");
                        countDownTime -= 1;
                        handlerCountDown.sendEmptyMessageDelayed(0, 1000);
                    } //如果3秒倒计时正好走完
                    else if(countDownTime == 0){
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        finish();
                    }
                }
            }
        };
        //app初始化完成即开启倒计时
        handlerCountDown.sendEmptyMessage(0);

    }

    //countDown按钮事件
    @OnClick(R.id.countDown)
    public void countDownClick(){
        //如果倒计时还没走完则取消倒计时handlerCountDown请求
        if(countDownTime>0) {
            handlerCountDown.removeMessages(0);
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }
}
