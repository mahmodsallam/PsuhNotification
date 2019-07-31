package com.mahmoud.mostafa.psuhnotification;

public class MainPresenter implements MainMvpPresenter {

    MainMvpView view;

    public MainPresenter(MainMvpView view) {
        this.view = view;
    }

    @Override
    public void openNextScreen(String key) {
        if (key != null) {

            if (key.equals("A"))
                view.openA();
            else
                view.openB();

        }

    }
}
