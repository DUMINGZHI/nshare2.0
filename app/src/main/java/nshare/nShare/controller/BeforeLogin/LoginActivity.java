package nshare.nShare.controller.BeforeLogin;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.payge.swipecardview.R;
import nshare.model.login.LoginCall;
import nshare.model.login.LoginImp;
import nshare.model.login.LoginModel;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    private LoginModel loginModel;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //绑定ButterKnife注入框架
        ButterKnife.bind(this);
        isNetworkAvalible(this);

        loginModel = new LoginImp();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what ==0)
                {
                    Toast.makeText(LoginActivity.this,"erroe",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    btnLogin.setEnabled(true);
                }else if(msg.what ==1)
                {
                    Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    btnLogin.setEnabled(true);
                    startActivity(new Intent(LoginActivity.this,IndexActivity.class));
                    finish();

                }
            }
        };


    }


    @OnClick(R.id.btnLogin)
    public void btnLogin(){
       if(username.getText().toString().length()!=0&&password.getText().toString().length()!=0){
            btnLogin.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            loginModel.getPass(username.getText().toString().toLowerCase(), password.getText().toString().toLowerCase(), new LoginCall() {
                @Override
                public void onError(String msg) {
                    handler.sendEmptyMessage(0);
                }

                @Override
                public void onSuccess(String msg) {
                    handler.sendEmptyMessage(1);
                }
            });
       }
       else{
           Toast.makeText(this,"请填写有效账号密码信息后登录",Toast.LENGTH_SHORT).show();
       }

   }

   //网络是否连接
    public void isNetworkAvalible(Context context) {
        int flag = 0;
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            Toast.makeText(LoginActivity.this,"网络发生错误",Toast.LENGTH_SHORT).show();
        } else {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();

            if (net_info != null) {
                for (int i = 0; i < net_info.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
                        btnLogin.setEnabled(true);
                        flag = 1;
                    }
                }
                if(flag == 0){
                    Toast.makeText(LoginActivity.this,"当前没有网络连接 请连接网络后重试",Toast.LENGTH_SHORT).show();
                    btnLogin.setEnabled(false);
                }

            }
        }

    }

}
