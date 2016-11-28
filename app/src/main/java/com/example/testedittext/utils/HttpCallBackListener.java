package com.example.testedittext.utils;

public interface HttpCallBackListener {

    public void onFinish(String response);

    public void onError(Exception e);
}
