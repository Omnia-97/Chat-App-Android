package com.example.chatapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.chatapp.repository.Repository;

public class MyViewModel extends AndroidViewModel{
    Repository repository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }
    public void signUpAnonymousUser(){
        Context c = this.getApplication();
        repository.firebaseAnonymousAuth(c);
    }
    public String getCurrentUserId(){
        return repository.getCurrentUserId();
    }
    public void signOut(){
        repository.signOut();
    }
}
