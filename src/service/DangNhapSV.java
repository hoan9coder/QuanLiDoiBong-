package service;

import model.BanHuanLuyen;
import model.CauThu;
import model.Login;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DangNhapSV extends Dulieu {
		
	public Login login(String user,String pass)
	{
		Login dn = null;
		try
		{
			String sql =" select * from dangnhap where TenDangNhap=? and MatKhau=?";
			PreparedStatement pre =conn.prepareStatement(sql);
			pre.setString(1,user);
			pre.setString(2,pass);
			ResultSet result=pre.executeQuery();
			while(result.next())
			{
				dn=new Login();
				dn.setTenDangNhap(result.getString(1));
				dn.setMatKhau(result.getString(2));
				
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dn;
	}
	public boolean insert(Login ct) throws Exception
	{
		String sql="insert into dangnhap (TenDangNhap,MatKhau,NhapLaiMK)"+
		"values(?,?,?)";
		try
		(
			PreparedStatement pre =conn.prepareStatement(sql);)
			{
			pre.setString(1,ct.getTenDangNhap());
			pre.setString(2,ct.getMatKhau());
			pre.setString(3,ct.getNhapLaiMatKhau());
		
				return pre.executeUpdate()>0;
			}
		catch(SQLException e)
		{
			JOptionPane.showConfirmDialog(null, "lỗi!");
		}
		return false;
		
	}
	public boolean update(Login ct) throws Exception
	{
		String sql="update dangnhap set MatKhau = ?,NhapLaiMK = ?"+
		"where TenDangNhap = ?";
		try
		(
			PreparedStatement pre =conn.prepareStatement(sql);)
			{
			pre.setString(3,ct.getTenDangNhap());
			pre.setString(1,ct.getMatKhau());
			pre.setString(2,ct.getNhapLaiMatKhau());
		
				return pre.executeUpdate()>0;
			}
		catch(SQLException e)
		{
			JOptionPane.showConfirmDialog(null, "lỗi!");
		}
		return false;
		
	}
	

}