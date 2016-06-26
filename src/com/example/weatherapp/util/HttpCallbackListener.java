package com.example.weatherapp.util;

public interface HttpCallbackListener{
	void onFinish(String response);
	void onError(Exception e);
}