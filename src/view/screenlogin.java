package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.Login;
import service.DangNhapSV;

public class screenlogin extends JFrame {
	JTextField txtUserName;
	JCheckBox chkSave;
	JPasswordField txtPassword;
	JButton btnLogin,btnExit,btnDki,btnQuenMK;
	
	public screenlogin(String title)
	{
		super(title);
		addControl();
		addEvent();
	}
	private void addEvent()
	{
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChucNangDangNhap();
				
			}
		});
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		btnDki.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				DangKi dki = new DangKi("Đăng Kí");
				dki.showWindow();
				
			}
		});
		btnQuenMK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				QuenMatKhau qmk = new QuenMatKhau("Đăng Kí");
				qmk.showWindow();
				
			}
		});
		}
	
	protected void ChucNangDangNhap() {
		DangNhapSV dnsv= new DangNhapSV();
		Login l =dnsv.login(txtUserName.getText(), txtPassword.getText());
		if(l!=null)
		{
			dispose();
			ChonMenu menu= new ChonMenu("Chọn Menu");
			menu.showWindow();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Đăng Nhập Không Thành Công!!");
		}
	}
		
	private void addControl()
	{
		Container conn = getContentPane();
		conn.setLayout(new BorderLayout());
		JPanel pnNorth = new JPanel();
		conn.add(pnNorth,BorderLayout.NORTH);
		JPanel pnCenter= new JPanel();
		conn.add(pnCenter,BorderLayout.CENTER);
		JPanel pnSouth = new JPanel();
		conn.add(pnSouth,BorderLayout.SOUTH);
			
		ImageIcon imageIcon = new ImageIcon("ps.png");
        JFrame jFrame = new JFrame();
		jFrame.setIconImage(imageIcon.getImage());
		//Thêm Chữ
		JLabel lblTitle =new JLabel("Đăng Nhập");
		lblTitle.setFont(new Font("tahoma",Font.BOLD,15));
		lblTitle.setForeground(Color.BLACK);
		pnNorth.add(lblTitle);		
		//thêm ảnh
		pnCenter.setLayout(new BorderLayout());
		JPanel pnImage = new JPanel();
		JLabel lblIcon = new JLabel(new ImageIcon("hinhanh/gif.gif"));
		
		pnImage.add(lblIcon);
		pnCenter.add(pnImage,BorderLayout.WEST);	
		 pnSouth.setBackground(Color.RED);
		 pnNorth.setBackground(Color.CYAN);	
		
		
		// Tạo thuộc tính nhập tên dăngd nhập và pass
		//tên dăng nhập
		JPanel pnUser=new JPanel();
		pnUser.setBackground(Color.GREEN);
		pnUser.setLayout(new BoxLayout(pnUser, BoxLayout.Y_AXIS));
		pnCenter.add(pnUser,BorderLayout.CENTER);
		
		pnUser.setLayout(new BorderLayout());
		JPanel pnduoi = new JPanel();
		pnduoi.setBackground(Color.GREEN);
		pnUser.add(pnduoi,BorderLayout.SOUTH);
		JPanel pngiua = new JPanel();
		pngiua.setBackground(Color.GREEN);
		pnUser.add(pngiua,BorderLayout.CENTER);
		
		JPanel pnUserName= new JPanel();
		pnUserName.setBackground(Color.GREEN);
		pnUserName.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblUserName=new JLabel("Tài Khoản");
		txtUserName= new JTextField(20);
		pnUserName.add(lblUserName);
		pnUserName.add(txtUserName);
		pngiua.add(pnUserName);

		//mật khẩu
		JPanel pnPassword= new JPanel();
		pnPassword.setBackground(Color.GREEN);
		pnPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblPassword=new JLabel("Mật Khẩu");
		txtPassword= new JPasswordField(20);
		pnPassword.add(lblPassword);
		pnPassword.add(txtPassword);
		pngiua.add(pnPassword);
		//tạo checkbox lưu
//		JPanel pnSave= new JPanel();
//		 chkSave=new JCheckBox("Lưu Thông Tin Đăng Nhập");
//		 pnSave.add(chkSave);
//		 pnUser.add(pnSave );
		btnDki= new JButton("Tôi Chưa Có Tài Khoản?");
		btnDki.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnDki.setBackground(Color.GREEN);
		pnduoi.add(btnDki);
		
		btnQuenMK= new JButton("Quên Mật Khẩu");
		btnQuenMK.setBackground(Color.GREEN);
		pnduoi.add(btnQuenMK);
		//tạo Nút Đăng Nhập Thoát
		btnLogin= new JButton("Đăng Nhập");
		btnExit= new JButton("Thoát");
		pnSouth.add(btnLogin);
		pnSouth.add(btnExit);
		// gán ảnh cho Login và Exit
		btnLogin.setIcon(new ImageIcon("hinhanh/permission.png"));
		btnExit.setIcon(new ImageIcon("hinhanh/login-.png"));
		btnDki.setIcon(new ImageIcon("hinhanh/register.png"));
		btnQuenMK.setIcon(new ImageIcon("hinhanh/forgot-password.png"));
		
		// chỉnh cho đẹp 
		Border borderDangNhap= BorderFactory.createLineBorder(Color.RED);
		TitledBorder titleBorderDangNhap = new TitledBorder(borderDangNhap,"Thông tin đăng nhập ");
		titleBorderDangNhap.setTitleJustification(TitledBorder.CENTER);
		titleBorderDangNhap.setTitleColor(Color.black);
		pnUser.setBorder(titleBorderDangNhap);
		lblPassword.setPreferredSize(lblUserName.getPreferredSize());	
		
	}

	public void showWindow()
	{
		setSize(600,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

}
