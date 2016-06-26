package com.example.weatherapp.model;

public class Province {
	public int id;
	public String Name_province;
	public String Code_province;
	public int ID_Province;
	
	public int getId(){
		return this.id;
	}
	public String getProvinceName(){
		return this.Name_province;
	}
	public String getProvinceCode(){
		return this.Code_province;
	}
	public int getProvinceID(){
		return this.ID_Province;
	}
	public void setId(int id){
		this.id=id;
	}
	public void setProvinceName(String ProvinceName){
		this.Name_province=ProvinceName;
	}
	public void setProvinceCode(String ProvinceCode){
		this.Code_province=ProvinceCode;
	}
	
}	
