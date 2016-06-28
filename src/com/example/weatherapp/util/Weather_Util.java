package com.example.weatherapp.util;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class Weather_Util {
	public static String data_today;
	public static String tmp_max_today;
	public static String tmp_min_today;
	public static String txt_d;
	public static String txt_n;
	
	public static void handle_Weather_Response(String response){
		try{
			Log.d("my_response", "handle begin");
			JSONObject jsonObject=new JSONObject(response);
			JSONArray all_information_arry=jsonObject.getJSONArray("HeWeather data service 3.0");
			//String all_information_str=all_information_arry.toString();
			//Log.d("my_response",all_information_str);
			//JSONArray  jsonArray=new JSONArray(all_information_str);
			JSONObject weather_inf=all_information_arry.getJSONObject(0);			
			//Log.d("my_response",weather_inf.toString());
			//Log.d("my_response","1");
			JSONObject daily_forecast=weather_inf.getJSONArray("daily_forecast").getJSONObject(0);
			data_today=daily_forecast.getString("date");
			//Log.d("my_response", data_today);
			tmp_max_today=daily_forecast.getJSONObject("tmp").getString("max");
			tmp_min_today=daily_forecast.getJSONObject("tmp").getString("min");
			//Log.d("my_response", tmp_max_today);
			txt_d=daily_forecast.getJSONObject("cond").getString("txt_d");
			txt_n=daily_forecast.getJSONObject("cond").getString("txt_n");
			//Log.d("my_response", txt_d);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
