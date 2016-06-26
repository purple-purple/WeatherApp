package com.example.weatherapp.activity;


import com.example.weatherapp.R;
import com.example.weatherapp.util.HttpCallbackListener;
import com.example.weatherapp.util.HttpUtil;
import com.example.weatherapp.util.Weather_Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherActivity extends Activity implements OnClickListener {

	private LinearLayout layout_weather;
	private TextView text_city_name;
	private TextView text_tem_max;
	private TextView text_tem_min;
	private TextView text_weather_message;
	private Weather_Util weather_util;
	private HttpUtil http_util;
	private String weather_response;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_weather_inf);
		layout_weather=(LinearLayout) findViewById(R.id.layout_weather_inf);
		text_city_name=(TextView) findViewById(R.id.name_city);
		text_tem_max=(TextView) findViewById(R.id.tem_max);
		text_tem_min=(TextView) findViewById(R.id.tem_min);
		text_weather_message=(TextView) findViewById(R.id.weather_txt);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		text_city_name.setText(bundle.getString("city_name"));
		
		http_util=new HttpUtil();
		String address="https://api.heweather.com/x3/weather?city=œ√√≈&key=d3fd71a8023142f39f2a03b61e0d3df2";
		Log.d("my_response", "1");
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener(){
			@Override
			public void onFinish(String response){
				weather_response=response.toString();
				Log.d("my_response", weather_response);
			}
			@Override 
			public void onError(Exception e){
				e.printStackTrace();
			}
		});
		weather_util.handle_Weather_Response(this, weather_response);
		//Log.d("my_response", weather_util.tmp_max_today);
		text_tem_max.setText(weather_util.tmp_max_today);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
