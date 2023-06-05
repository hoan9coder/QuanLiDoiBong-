package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.BanHuanLuyen;
import model.CauThu;
import model.DanhHieu;

public class DanhHieuSV extends Dulieu {
	public int XoaDanhHieu(DanhHieu dh)
	{
		try
		{
			String sql="delete from danhhieu where MaDanhHieu=?";
			PreparedStatement preStatement =conn.prepareStatement(sql);
			preStatement.setString(1, dh.getMaDanhHieu());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
		
	}
	public ArrayList<DanhHieu>DanhSachDanhHieu()
	{
		ArrayList<DanhHieu>ds= new ArrayList<DanhHieu>();
		try
		{
			String sql="select*from danhhieu ";
			PreparedStatement preparedStatement=conn.prepareStatement(sql);
			
			ResultSet result=preparedStatement.executeQuery();
			while(result.next())
			{
				DanhHieu dh= new DanhHieu();
				dh.setMaDanhHieu(result.getString(1));
				dh.setTenDanhHieu(result.getString(2));
				dh.setMaSoAo(result.getString(4));
				dh.setNamNhan(result.getString(3));
				dh.setMaBHL(result.getString(5));
				ds.add(dh);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return ds;
	}
	public boolean insert(DanhHieu dh) throws Exception
	{
		String sql="insert into danhhieu (MaDanhHieu,TenDanhHieu,NamNhan,maSoAo,maBanHuanLuyen)"+
		"values(?,?,?,?,?);";
		try
		(
			PreparedStatement pre =conn.prepareStatement(sql);)
			{
			pre.setString(1,dh.getMaDanhHieu());
			pre.setString(2,dh.getTenDanhHieu());
			pre.setString(3,dh.getNamNhan());
			pre.setString(4,dh.getMaSoAo());
			pre.setString(5,dh.getMaBHL());
			
				return pre.executeUpdate()>0;
			}
		catch(SQLException e)
		{
			JOptionPane.showConfirmDialog(null, "lỗi!");
		}
		return false;
		
	}
	public boolean update(DanhHieu dh)throws Exception
	{
		String sql="update danhhieu set TenDanhHieu = ?,NamNhan = ?,maSoAo = ?,maBanHuanLuyen = ?"+
				" where MaDanhHieu = ?";
				try
				(
					PreparedStatement pre =conn.prepareStatement(sql);)
					{
					pre.setString(5,dh.getMaDanhHieu());
					pre.setString(1,dh.getTenDanhHieu());
					pre.setString(2,dh.getNamNhan());
					pre.setString(3,dh.getMaSoAo());
					pre.setString(4,dh.getMaBHL());
						return pre.executeUpdate()>0;
					}
	catch(SQLException e)
	{
		JOptionPane.showConfirmDialog(null, "lỗi!");
	}
	return false;
	
	
	}
}

