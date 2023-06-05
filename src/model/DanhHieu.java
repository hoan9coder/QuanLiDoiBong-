package model;

import java.util.Date;

public class DanhHieu {
	private String maDanhHieu;
	private String tenDanhHieu;
	private String maSoAo;
	private String NamNhan;
	private String maBHL;
	public String getMaDanhHieu() {
		return maDanhHieu;
	}
	public void setMaDanhHieu(String maDanhHieu) {
		this.maDanhHieu = maDanhHieu;
	}
	
	public String getTenDanhHieu() {
		return tenDanhHieu;
	}
	public void setTenDanhHieu(String tenDanhHieu) {
		this.tenDanhHieu = tenDanhHieu;
	}
	public String getNamNhan() {
		return NamNhan;
	}
	public void setNamNhan(String namNhan) {
		NamNhan = namNhan;
	}
	public String getMaSoAo() {
		return maSoAo;
	}
	public void setMaSoAo(String maSoAo) {
		this.maSoAo = maSoAo;
	}
	public String getMaBHL() {
		return maBHL;
	}
	public void setMaBHL(String maBHL) {
		this.maBHL = maBHL;
	}
}
