package com.example.lyaho340hw1;

interface onGetDataListener<T> {
    // for callbacks
    void onSuccess(T dataResponse);
    void onFailure();
}
