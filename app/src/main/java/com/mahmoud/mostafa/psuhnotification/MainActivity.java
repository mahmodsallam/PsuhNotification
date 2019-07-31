package com.mahmoud.mostafa.psuhnotification;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.mahmoud.mostafa.fragmentA.FrgamentA;
import com.mahmoud.mostafa.fragmentB.FrgamentB;

public class MainActivity extends AppCompatActivity implements MainMvpView {

    MainPresenter mainPresenter;
    String key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openA();
        getNotification();
        mainPresenter = new MainPresenter(this);
        mainPresenter.openNextScreen(getIntent().getStringExtra("chooser"));
    }


    public void getNotification() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        String TAG = "firebase";
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();
                        Log.d("token", token);
                    }
                });
    }

    @Override
    public void openA() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                FrgamentA.newInstance()).commit();
    }

    @Override
    public void openB() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                FrgamentB.newInstance()).commit();

    }
}
