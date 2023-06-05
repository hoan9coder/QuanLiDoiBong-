package model;

import java.io.Serializable;

public class Login implements Serializable {
	private String TenDangNhap;
	private String MatKhau;
	private String NhapLaiMatKhau;
	public String getTenDangNhap() {
		return TenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		TenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return MatKhau;
	}
	public String getNhapLaiMatKhau() {
		return NhapLaiMatKhau;
	}
	public void setNhapLaiMatKhau(String nhapLaiMatKhau) {
		NhapLaiMatKhau = nhapLaiMatKhau;
	}
	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}
	
}