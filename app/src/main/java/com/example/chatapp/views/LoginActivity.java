package com.example.chatapp.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.chatapp.R;
import com.example.chatapp.databinding.ActivityLoginBinding;
import com.example.chatapp.viewmodel.MyViewModel;

public class LoginActivity extends AppCompatActivity {
    MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        ActivityLoginBinding activityLoginBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setVModel(viewModel);
    }
}