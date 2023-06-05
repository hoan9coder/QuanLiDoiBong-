package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.BanHuanLuyen;
import model.Login;
import service.BanHuanLuyenSV;
import service.DangNhapSV;

public class DangKi extends JFrame{
	JButton btnDangKi,btnDangNhap;
	JTextField txtTenTaiKhoan;
	JPasswordField tptMatKhau,tptNhapLai;
	public DangKi(String title)
	{
		this.setTitle(title);
		addControls();
		addEvent();
	}

	private void addEvent() {
		btnDangKi.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				DKI();	
			}
			private void DKI() {
				try {
					StringBuilder sb = new StringBuilder();
					if(txtTenTaiKhoan.getText().equals("")) {
						sb.append("Tên Đăng Nhập Rỗng!!!\n");
						txtTenTaiKhoan.setBackground(Color.red);
						txtTenTaiKhoan.requestFocus();
					} else {
						txtTenTaiKhoan.setBackground(Color.white);
					}
					String passWord = new String(tptMatKhau.getPassword());
					
					if(passWord.equals("")) {
						sb.append("Mật Khẩu Rỗng!!!\n");
						tptMatKhau.setBackground(Color.red);
						tptMatKhau.requestFocus();
					} else {
						tptMatKhau.setBackground(Color.white);
					}
					String ConfirmPassword = new String(tptNhapLai.getPassword());
					if(!passWord.equals("") && !passWord.equals(ConfirmPassword)) {
						sb.append("Mật Khẩu Xác Nhận Và Mật Khẩu Phải Giống nhau!!!");
						tptNhapLai.setBackground(Color.red);
						tptMatKhau.setBackground(Color.red);
					} else {
						tptNhapLai.setBackground(Color.white);
						tptNhapLai.setBackground(Color.white);
					}
					if(sb.length()>0) {
						JOptionPane.showMessageDialog(null, sb.toString());
						return;
					}
					Login dn = new Login();					
					dn.setTenDangNhap(txtTenTaiKhoan.getText());
					dn.setMatKhau(tptMatKhau.getText());
					dn.setNhapLaiMatKhau(tptNhapLai.getText());
					DangNhapSV sv = new DangNhapSV();
					if (sv.insert(dn)) {					
						JOptionPane.showMessageDialog(null, "Đăng Ký thành công.");					
					} else {
						JOptionPane.showMessageDialog(null, "Đăng Ký không thành công.");
					}				
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Lỗi!!!");
				}
			}			
		});
		btnDangNhap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				screenlogin lgc= new screenlogin("Đăng Nhập");
				lgc.showWindow();
				
			}
		});
		
	}

	private void addControls() {
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnNorth = new JPanel();
		JPanel pnCenter= new JPanel();
		JPanel pnSouth = new JPanel();
		con.add(pnNorth,BorderLayout.NORTH);
		con.add(pnCenter,BorderLayout.CENTER);
		con.add(pnSouth,BorderLayout.SOUTH);
		pnCenter.setLayout(new BoxLayout(pnCenter,BoxLayout.Y_AXIS));
		pnCenter.setBackground(Color.blue);
		pnNorth.setBackground(Color.MAGENTA);
		pnSouth.setBackground(Color.BLACK);
		
		JLabel lblTitle =new JLabel("Đăng Kí");
		lblTitle.setFont(new Font("tahoma",Font.BOLD,20));
		lblTitle.setForeground(Color.WHITE);
		pnNorth.add(lblTitle);
		
		JPanel pnTenDangNhap = new JPanel();
		pnTenDangNhap.setBackground(Color.CYAN);	
		JLabel lblTenDangNhap = new JLabel("Tên Đăng Nhập");	
		txtTenTaiKhoan= new JTextField(20);
		pnTenDangNhap.add(lblTenDangNhap);
		pnTenDangNhap.add(txtTenTaiKhoan);
		pnCenter.add(pnTenDangNhap);
		
		JPanel pnMK = new JPanel();
		pnMK.setBackground(Color.CYAN);	
		JLabel lblMK = new JLabel("Mật Khẩu");	
		tptMatKhau= new JPasswordField(20);
		pnMK.add(lblMK);
		pnMK.add(tptMatKhau);
		pnCenter.add(pnMK);
		
		JPanel pnNL = new JPanel();
		pnNL.setBackground(Color.CYAN);	
		JLabel lblNL = new JLabel("Nhập Lại Mật Khẩu");	
		tptNhapLai= new JPasswordField(20);
		pnNL.add(lblNL);
		pnNL.add(tptNhapLai);
		pnCenter.add(pnNL);
		
		JPanel pnDKI=new JPanel();
		pnDKI.setBackground(Color.RED);
		btnDangKi= new JButton("Đăng Kí");
		btnDangKi.setBackground(Color.WHITE);
		pnDKI.add(btnDangKi);
		pnSouth.add(pnDKI);
		
		JPanel pnDN=new JPanel();
		pnDN.setBackground(Color.RED);
		btnDangNhap= new JButton("Đăng Nhập");
		btnDangNhap.setBackground(Color.WHITE);
		pnDN.add(btnDangNhap);
		pnSouth.add(pnDN);
		
		btnDangNhap.setIcon(new ImageIcon("hinhanh/login-.png"));
		btnDangKi.setIcon(new ImageIcon("hinhanh/register.png"));
		
		lblTenDangNhap.setPreferredSize(lblNL.getPreferredSize());
		lblMK.setPreferredSize(lblNL.getPreferredSize());
	}
	public void showWindow()
	{
		setSize(400,250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
