package com.example.cookmaster.ui.Login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookmaster.R;
import com.example.cookmaster.ui.Login.LoginRepository;
import com.example.cookmaster.ui.Login.Result;
import com.example.cookmaster.ui.Login.LoggedInUserView;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<com.example.cookmaster.ui.Login.LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<com.example.cookmaster.ui.Login.LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<com.example.cookmaster.ui.Login.LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<com.example.cookmaster.ui.Login.LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new com.example.cookmaster.ui.Login.LoginResult(new com.example.cookmaster.ui.Login.LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new com.example.cookmaster.ui.Login.LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new com.example.cookmaster.ui.Login.LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new com.example.cookmaster.ui.Login.LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new com.example.cookmaster.ui.Login.LoginFormState(true));
        }
    }

    // A placeholder username validation check
    public boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    public boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    public boolean isNewPasswordValid(String password, String password2) {
        return password.equals(password2);
    }
}