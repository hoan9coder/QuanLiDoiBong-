package model;

import java.util.Date;

public class BanHuanLuyen {
	private String maBanHuanLuyen;
	private String tenBanHuanLuyen;
	private String tuoi;
	private String QuocTich;
	private String ChucVu;
	private String Luong;
	private String NgayBatDau;
	private String NgayKetThuc;
	private String Menu;
	public String getMenu() {
		return Menu;
	}
	public void setMenu(String menu) {
		Menu = menu;
	}
	public String getMaBanHuanLuyen() {
		return maBanHuanLuyen;
	}
	public void setMaBanHuanLuyen(String maBanHuanLuyen) {
		this.maBanHuanLuyen = maBanHuanLuyen;
	}
	public String getTenBanHuanLuyen() {
		return tenBanHuanLuyen;
	}
	public void setTenBanHuanLuyen(String tenBanHuanLuyen) {
		this.tenBanHuanLuyen = tenBanHuanLuyen;
	}
	public String getTuoi() {
		return tuoi;
	}
	public void setTuoi(String tuoi) {
		this.tuoi = tuoi;
	}
	public String getQuocTich() {
		return QuocTich;
	}
	public void setQuocTich(String quocTich) {
		QuocTich = quocTich;
	}
	public String getChucVu() {
		return ChucVu;
	}
	public void setChucVu(String chucVu) {
		ChucVu = chucVu;
	}
	public String getLuong() {
		return Luong;
	}
	public void setLuong(String luong) {
		Luong = luong;
	}
	public String getNgayBatDau() {
		return NgayBatDau;
	}
	public void setNgayBatDau(String ngayBatDau) {
		NgayBatDau = ngayBatDau;
	}
	public String getNgayKetThuc() {
		return NgayKetThuc;
	}
	public void setNgayKetThuc(String ngayKetThuc) {
		NgayKetThuc = ngayKetThuc;
	}
}