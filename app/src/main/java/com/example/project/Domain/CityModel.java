package com.example.project.Domain;

public class CityModel {
    String cityId, provinceId, province, city, postCode;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public CityModel(String cityId, String provinceId, String province, String city, String postCode) {
        this.cityId = cityId;
        this.provinceId = provinceId;
        this.province = province;
        this.city = city;
        this.postCode = postCode;
    }

    public CityModel(){

    }
}
