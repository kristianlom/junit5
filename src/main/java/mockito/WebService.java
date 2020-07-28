package mockito;

public class WebService {

    public void login(String user, String password, Callback callback) {
        if (checkLogin(user, password)) {
            callback.onSuccess("Correct user");
        } else {
            callback.onFail("Error");
        }
    }

    public boolean checkLogin(String user, String password) {

        return user.equals("kristian") && password.equals("lopez");
    }

}
