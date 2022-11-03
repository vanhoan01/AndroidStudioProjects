package com.example.dictionaryapp.Model;

import java.io.Serializable;

public class TaiKhoan implements Serializable {
    private int ma;
    private String taiKhoan;
    private String matKhau;
    private int phanQuyen;

    public TaiKhoan(int ma, String taiKhoan, String matKhau, int phanQuyen) {
        this.ma = ma;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.phanQuyen = phanQuyen;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(int phanQuyen) {
        this.phanQuyen = phanQuyen;
    }
}
