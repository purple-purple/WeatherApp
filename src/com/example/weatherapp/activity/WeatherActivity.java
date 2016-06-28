package com.example.weatherapp.activity;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.weatherapp.R;
import com.example.weatherapp.util.HttpUtil;
import com.example.weatherapp.util.Weather_Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
	
	public static final int SHOW_RESPONSE=0;
	private String address;
	private Handler handler=new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case SHOW_RESPONSE:
				weather_response=(String) msg.obj;
				//Log.d("my_response", weather_response);
				Weather_Util.handle_Weather_Response(weather_response);
				text_tem_min.setText(weather_util.tmp_min_today+"℃");
				text_tem_max.setText(weather_util.tmp_max_today+"℃");
				if(weather_util.txt_d.equals(weather_util.txt_n)){
					text_weather_message.setText(weather_util.txt_d);
				}
				else{
					text_weather_message.setText(weather_util.txt_d+" 转 "+weather_util.txt_n);
				}
			}
		}
	};
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
		
		//http_util=new HttpUtil();
		address="https://api.heweather.com/x3/weather?city="
				+
				bundle.getString("city_name").substring(0, bundle.getString("city_name").length()-1)
				+
				"&key=d3fd71a8023142f39f2a03b61e0d3df2";
		/*HttpUtil.sendHttpRequest(address, new HttpCallbackListener(){
			@Override
			public void onFinish(String response){
				weather_response=response.toString();
				Log.d("my_response", weather_response);
			}
			@Override 
			public void onError(Exception e){
				e.printStackTrace();
			}
		});*/
		sendRequest_Http_URLConnection();
		//Log.d("my_response", weather_util.tmp_max_today);
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	private void sendRequest_Http_URLConnection(){
		new Thread(new Runnable(){//开启子线程处理HTTP请求内容
			@Override
			public void run(){
				HttpURLConnection connection=null;
				try{
					URL url=new URL(address);
					//URL url=new URL("http://www.baidu.com");
					connection=(HttpURLConnection) url.openConnection();//获得连接实例
					connection.setRequestMethod("GET");//设置请求方法 GET/POST
					connection.setConnectTimeout(80000);//连接超时
					connection.setReadTimeout(80000);//读取超时
					InputStream in=connection.getInputStream();//获取服务器返回的输入流
					BufferedReader reader=new BufferedReader(new InputStreamReader(in));//输入流文本容器
					StringBuilder response=new StringBuilder();//字符串容器
					String line;
					while((line=reader.readLine())!=null){
						response.append(line);//添加内容	
						Log.d("my_response", "success");
					}
					Message message=new Message();
					message.what=SHOW_RESPONSE;
					message.obj=response.toString();
					handler.sendMessage(message);//发送信息
						
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(connection!=null){
						connection.disconnect();//关闭HTTP连接
					}
				}
			}
		}).start();
	}
	
}
