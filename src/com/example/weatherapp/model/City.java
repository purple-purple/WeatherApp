package com.example.weatherapp.model;

public class City {
	public int id;//主键
	public String Name_city;
	public String Code_city;//城市代号
	public int ID_City;
	public int ID_province;
	
	public int getId(){
		return id;
	}
	public String getCityName(){
		return Name_city;
	}
	public String getCityCode(){
		return Code_city;
	}
	public int getProvinceId(){
		return ID_province;
	}
	public void setId(int id){
		this.id=id;
	}
	public void setCityName(String cityname){
		this.Name_city=cityname;
	}
	public void setCityCode(String citycode){
		this.Code_city=citycode;
	}
	public void setProvinceId(int provinceId){
		this.ID_province=provinceId;
	}
	
}
