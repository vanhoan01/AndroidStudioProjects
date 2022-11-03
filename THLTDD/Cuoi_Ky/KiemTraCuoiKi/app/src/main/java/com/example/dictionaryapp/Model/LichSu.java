package com.example.dictionaryapp.Model;

public class LichSu {
    private String nhap;
    private String xuat;

    public LichSu(String nhap, String xuat) {
        this.nhap = nhap;
        this.xuat = xuat;
    }

    public String getNhap() {
        return nhap;
    }

    public void setNhap(String nhap) {
        this.nhap = nhap;
    }

    public String getXuat() {
        return xuat;
    }

    public void setXuat(String xuat) {
        this.xuat = xuat;
    }
}
