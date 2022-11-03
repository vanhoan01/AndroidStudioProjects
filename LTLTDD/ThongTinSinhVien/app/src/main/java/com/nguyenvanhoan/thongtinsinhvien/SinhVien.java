package com.nguyenvanhoan.thongtinsinhvien;

public class SinhVien {
    private String ten;
    private String msv;
    private String lop;
    private int hinh;

    public SinhVien(String ten, String msv, String lop, int hinh) {
        this.ten = ten;
        this.msv = msv;
        this.lop = lop;
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
