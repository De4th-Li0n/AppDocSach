package com.example.appdocsach.Model;

public class TaiKhoan {
    //Các biến
    private int nId;
    private String nTenTaiKhoan;
    private String nMatKhau;
    private String nEmail;
    private int nPhanQuyen;

    //Hàm khởi tạo
    public TaiKhoan(String nTenTaiKhoan, String nMatKhau, String nEmail, int nPhanQuyen) {
        this.nTenTaiKhoan = nTenTaiKhoan;
        this.nMatKhau = nMatKhau;
        this.nEmail = nEmail;
        this.nPhanQuyen = nPhanQuyen;
    }

    public TaiKhoan(String nTenTaiKhoan, String nEmail) {
        this.nTenTaiKhoan = nTenTaiKhoan;
        this.nEmail = nEmail;
    }

    //Các Setter getter
    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    public String getnTenTaiKhoan() {
        return nTenTaiKhoan;
    }

    public void setnTenTaiKhoan(String nTenTaiKhoan) {
        this.nTenTaiKhoan = nTenTaiKhoan;
    }

    public String getnMatKhau() {
        return nMatKhau;
    }

    public void setnMatKhau(String nMatKhau) {
        this.nMatKhau = nMatKhau;
    }

    public String getnEmail() {
        return nEmail;
    }

    public void setnEmail(String nEmail) {
        this.nEmail = nEmail;
    }

    public int getnPhanQuyen() {
        return nPhanQuyen;
    }

    public void setnPhanQuyen(int nPhanQuyen) {
        this.nPhanQuyen = nPhanQuyen;
    }
}
