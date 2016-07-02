package com.example.weatherapp.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.weatherapp.R;
import com.example.weatherapp.db.DatabaseManager;
import com.example.weatherapp.db.DatabaseOperator;
import com.example.weatherapp.model.City;
import com.example.weatherapp.model.Province;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseCityActivity extends Activity implements OnItemClickListener{
	public static final int LEVEL_PROVINCE=0; 
	public static final int LEVEL_CITY=1;
	
	private ProgressDialog progressDialog;
	private TextView text_title;
	private ListView view_list;
	private ArrayAdapter<String> adapter;
	private DatabaseOperator databaseOperator;
	private DatabaseManager  databaseManager;
	private List<String> list_data =new ArrayList<String>();
	
	private List<Province> list_province;
	private List<City> list_city;
	private Province province_selected;
	private City city_selected;
	private int  level_current;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_city);
		view_list=(ListView) findViewById(R.id.list_choose_city);
		text_title=(TextView) findViewById(R.id.text_title);
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_data);
		view_list.setAdapter(adapter);
		databaseOperator=new DatabaseOperator();
		databaseManager=new DatabaseManager(ChooseCityActivity.this);
		databaseManager.openDatabase();
		view_list.setOnItemClickListener(this);
		query_province();
	}
	
	@Override
	public void onBackPressed(){
		if(level_current==LEVEL_CITY){
			query_province();
		}else{
			finish();
		}
	}
	private void query_province(){
		text_title.setText("China");
		list_province=databaseOperator.loadProvince(databaseManager.getDatabase());
		if(list_province.size()>0){
			list_data.clear();
			for(Province province:list_province){
				list_data.add(province.getProvinceName());
			}
			adapter.notifyDataSetChanged();
			Log.d("province", "1");
			view_list.setSelection(0);
			level_current=LEVEL_PROVINCE;
		}
	}
	
	private void query_city(){	
		list_city=databaseOperator.loadCity(databaseManager.getDatabase(), province_selected.getProvinceID());
		if(list_city.size()>0){
			list_data.clear();
			for(City city:list_city){
				list_data.add(city.getCityName());
			}
			adapter.notifyDataSetChanged();
			view_list.setSelection(0);
			text_title.setText(province_selected.getProvinceName());
			level_current=LEVEL_CITY;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
		// TODO Auto-generated method stub
		if(level_current==LEVEL_PROVINCE){
			province_selected=list_province.get(index);
			query_city();
		}else if(level_current==LEVEL_CITY){
			city_selected=list_city.get(index);
			Intent intent=new Intent(ChooseCityActivity.this,WeatherActivity.class);
			intent.putExtra("city_name", city_selected.getCityName());
			startActivity(intent);
		}
	}

}
