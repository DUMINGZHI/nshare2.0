package nshare.model.login;

/**
 * Created by Administrator on 2018/1/20.
 */

public interface LoginCall {
    void onError(String msg);
     void onSuccess(String msg);
}
