package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.CauThu;
import model.Login;

public class CauThuSV extends Dulieu {
	public int XoaCauThu(CauThu ct)
	{
		try
		{
			String sql="delete from cauthu where SoAo=?";
			PreparedStatement preStatement =conn.prepareStatement(sql);
			preStatement.setString(1, ct.getSoAo());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
		
	}
	public ArrayList<CauThu>DanhSachCauThu()
	{
		ArrayList<CauThu>ds= new ArrayList<CauThu>();
		try
		{
			String sql="select*from cauthu";
			PreparedStatement preparedStatement=conn.prepareStatement(sql);
			ResultSet result=preparedStatement.executeQuery();
			while(result.next())
			{
				CauThu ct= new CauThu();
				ct.setSoAo(result.getString(1));
				ct.setTenCauThu(result.getString(2));
				ct.setTuoi(result.getString(3));
				ct.setVitri(result.getString(5));
				ct.setQuequan(result.getString(4));
				ct.setMucLuong(result.getString(6));
				ct.setNgayBatDau(result.getString(7));
				ct.setNgayKetThuc(result.getString(8));
				
				ds.add(ct);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return ds;
	}
	public boolean insert(CauThu ct) throws Exception
	{
		String sql="insert into cauthu (SoAo,TenCauThu,Tuoi,QueQuan,ViTri,Luong,NgayBatDau,NgayKetThuc)"+
		"values(?,?,?,?,?,?,?,?)";
		try
		(
			PreparedStatement pre =conn.prepareStatement(sql);)
			{
			pre.setString(1,ct.getSoAo());
			pre.setString(2,ct.getTenCauThu());
			pre.setString(3, ct.getTuoi());
			pre.setString(4,ct.getQuequan());
			pre.setString(5, ct.getVitri());
			pre.setString(6, ct.getMucLuong());
			pre.setString(7,  ct.getNgayBatDau());
			pre.setString(8,  ct.getNgayKetThuc());
		
				return pre.executeUpdate()>0;
			}
		catch(SQLException e)
		{
			JOptionPane.showConfirmDialog(null, "lỗi!");
		}
		return false;
		
	}
	public boolean update(CauThu ct) throws Exception
	{
		String sql="update cauthu set TenCauThu = ?,Tuoi = ?,QueQuan = ?,ViTri = ?,Luong = ?,NgayBatDau = ?,NgayKetThuc = ?"+
				"where SoAo = ?";
				try
				(
					PreparedStatement pre =conn.prepareStatement(sql);)
					{
					pre.setString(8,ct.getSoAo());
					pre.setString(1,ct.getTenCauThu());
					pre.setString(2, ct.getTuoi());
					pre.setString(3,ct.getQuequan());
					pre.setString(4, ct.getVitri());
					pre.setString(5, ct.getMucLuong());
					pre.setString(6,  ct.getNgayBatDau());
					pre.setString(7,  ct.getNgayKetThuc());
					
						return pre.executeUpdate()>0;
					}
				catch(SQLException e)
				{
					JOptionPane.showConfirmDialog(null, "lỗi!");
				}
				return false;
				
	}
}
	

