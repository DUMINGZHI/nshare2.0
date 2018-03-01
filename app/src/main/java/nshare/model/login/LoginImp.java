package nshare.model.login;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import nshare.nShare.controller.BeforeLogin.LoginActivity;
import nshare.nShareEntity.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/18.
 */

public class LoginImp implements LoginModel {

    private OkHttpClient okHttpClient;
    private Request request;
    private String htmlStr,htmlBody;
    private Document document;
    private List<User> userList = new ArrayList<User>();;
    private LoginActivity loginActivity = new LoginActivity();
    private Gson gson;
    private Type type;
    @Override
    public void getPass(final String username,final String password,final LoginCall loginCall) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String url = "http://www.dumingzhi.com/getPass?username=" + username;
                okHttpClient = new OkHttpClient();
                request = new Request.Builder()
                        .url(url)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        loginCall.onError("error");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //处理接口返回的json
                        htmlStr = response.body().string();
                        document = Jsoup.parse(htmlStr);
                        htmlBody = document.body().getElementsByTag("body").text();
                        gson = new Gson();
                        type = new TypeToken<ArrayList<User>>() {
                        }.getType();
                        userList = gson.fromJson(htmlBody, type);
                        //判断用户是否存在
                        if(userList.size() == 1) {
                            //存在进行密码比对验证
                            if(userList.get(0).getPassWord().toString().equalsIgnoreCase(password.toString()))
                            {
                                Log.i("dmin",userList.get(0).getPassWord().toString() + password.toString());
                                //比对成功
                                loginCall.onSuccess("success");
                            }else {
                                //比对失败
                                Log.i("dmin",userList.get(0).getPassWord().toString() + password.toString());
                                loginCall.onError("error");
                            }
                        }else if(userList.size() <= 0){
                            //不存在返回LOGIN_FAIL
                            Log.i("dmin",userList.get(0).getPassWord().toString() + password.toString());
                            loginCall.onError("error");
                        }else {
                            //不存在返回LOGIN_FAIL
                            Log.i("dmin",userList.get(0).getPassWord().toString() + password.toString());
                            loginCall.onError("error");
                        }

                    }
                });

            }
        }).start();

    }


}
