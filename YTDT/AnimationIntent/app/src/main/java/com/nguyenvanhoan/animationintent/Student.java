package com.nguyenvanhoan.animationintent;

public class Student {
    private String hoVaTen;
    private String lop;
    private String ngaySinh;

    public Student(String hoVaTen, String lop, String ngaySinh) {
        this.hoVaTen = hoVaTen;
        this.lop = lop;
        this.ngaySinh = ngaySinh;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
}
