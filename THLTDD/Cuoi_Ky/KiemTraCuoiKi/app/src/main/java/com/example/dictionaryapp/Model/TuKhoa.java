package com.example.dictionaryapp.Model;

public class TuKhoa {
    private int stt;
    private String nhap;
    private String xuat;

    public TuKhoa(int stt, String nhap, String xuat) {
        this.stt = stt;
        this.nhap = nhap;
        this.xuat = xuat;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
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
