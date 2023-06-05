package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.BanHuanLuyen;
import model.CauThu;

public class BanHuanLuyenSV extends Dulieu {
	public int XoaBanHuanLuyen(BanHuanLuyen bhl)
	{
		try
		{
			String sql="delete from banhuanluyen where MaBanHuanLuyen=?";
			PreparedStatement preStatement =conn.prepareStatement(sql);
			preStatement.setString(1, bhl.getMaBanHuanLuyen());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
		
	}
	public ArrayList<BanHuanLuyen>DanhSachBanHuanLuyen()
	{
		ArrayList<BanHuanLuyen>ds= new ArrayList<BanHuanLuyen>();
		try
		{
			String sql="select*from banhuanluyen";
			PreparedStatement preparedStatement=conn.prepareStatement(sql);
			ResultSet result=preparedStatement.executeQuery();
			while(result.next())
			{
				BanHuanLuyen bhl= new BanHuanLuyen();
				bhl.setMaBanHuanLuyen(result.getString(1));
				bhl.setTenBanHuanLuyen(result.getString(2));
				bhl.setTuoi(result.getString(3));
				bhl.setQuocTich(result.getString(4));
				bhl.setChucVu(result.getString(5));
				bhl.setLuong(result.getString(6));
				bhl.setNgayBatDau(result.getString(7));
				bhl.setNgayKetThuc(result.getString(8));
				ds.add(bhl);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return ds;
	}
	public boolean insert(BanHuanLuyen bhl) throws Exception
	{
		String sql="insert into banhuanluyen (MaBanHuanLuyen,TenBHL,Tuoi,QuocTich,ChucVu,Luong,NgayBatDau,NgayKetThuc)"+
		"values(?,?,?,?,?,?,?,?)";
		try
		(
			PreparedStatement pre =conn.prepareStatement(sql);)
			{
			pre.setString(1,bhl.getMaBanHuanLuyen());
			pre.setString(2,bhl.getTenBanHuanLuyen());
			pre.setString(3, bhl.getTuoi());
			pre.setString(4,bhl.getQuocTich());
			pre.setString(5, bhl.getChucVu());
			pre.setString(6, bhl.getLuong());
			pre.setString(7,  bhl.getNgayBatDau());
			pre.setString(8,  bhl.getNgayKetThuc());
			
				return pre.executeUpdate()>0;
			}
		catch(SQLException e)
		{
			JOptionPane.showConfirmDialog(null, "lỗi!");
		}
		return false;
		
	}
	public boolean update(BanHuanLuyen bhl) throws Exception
	{
		String sql="update banhuanluyen set TenBHL = ?,Tuoi = ?,QuocTich = ?,ChucVu = ?,Luong = ?,NgayBatDau = ?,NgayKetThuc = ?"+
		"where MaBanHuanLuyen = ?";
		try
		(
			PreparedStatement pre =conn.prepareStatement(sql);)
			{
			pre.setString(8,bhl.getMaBanHuanLuyen());
			pre.setString(1,bhl.getTenBanHuanLuyen());
			pre.setString(2, bhl.getTuoi());
			pre.setString(3, bhl.getQuocTich());
			pre.setString(4, bhl.getChucVu());
			pre.setString(5, bhl.getLuong());
			pre.setString(6, bhl.getNgayBatDau());
			pre.setString(7, bhl.getNgayKetThuc());
		
				return pre.executeUpdate()>0;
			}
		catch(SQLException e)
		{
			JOptionPane.showConfirmDialog(null, "lỗi!");
		}
		return false;
		
	}

}

