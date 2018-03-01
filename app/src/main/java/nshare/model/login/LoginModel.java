package nshare.model.login;

/**
 * Created by Administrator on 2018/1/18.
 */

public interface LoginModel {
    void getPass(String username, String password, LoginCall loginCall);
}
