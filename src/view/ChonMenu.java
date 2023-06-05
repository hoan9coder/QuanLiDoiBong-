package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChonMenu extends JFrame implements ActionListener{
	JButton btnCauThu,btnBanHuanLuyen,btnDanhHieu,btnThoat,btnquaylai;
	public ChonMenu(String title)
	{
		this.setTitle(title);
		addControls();
		addEvent();
	}

	private void addEvent() {
		btnCauThu.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    	dispose();
		        ManHinhCauThu mhc= new ManHinhCauThu("Quản Lí Đội Bóng");
		        mhc.showWindow();
		        GioiThieu gt = new GioiThieu("Giới Thiệu");
		        gt.showWindow();
		    }
		});
		
		btnBanHuanLuyen.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    	dispose();
		        ManHinhBanHuanLuyen bhl=new ManHinhBanHuanLuyen("Quản Lí Đội Bóng");
		        bhl.showWindow();
		        GioiThieu gt = new GioiThieu("Giới Thiệu");
		        gt.showWindow();
		    }
		});
		btnDanhHieu.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    	dispose();
		        ManHinhDanhHieu mhdh= new ManHinhDanhHieu("Danh Hiệu");
		        mhdh.showWindow();
		        GioiThieu gt = new GioiThieu("Giới Thiệu");
		        gt.showWindow();
		    }
		});
//		btnThoat.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//				
//			}
//		});
		btnquaylai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
	}

	private void addControls() {
		Container conn = getContentPane();
		conn.setLayout(new BorderLayout());
		JPanel pnNorth = new JPanel();
		conn.add(pnNorth,BorderLayout.NORTH);
		JPanel pnCenter= new JPanel();
		conn.add(pnCenter,BorderLayout.CENTER);
		JPanel pnSouth = new JPanel();
		conn.add(pnSouth,BorderLayout.SOUTH);
		
		JPanel pnTrangchu=new JPanel();
		pnTrangchu.setLayout(new BoxLayout(pnTrangchu, BoxLayout.Y_AXIS));
		pnCenter.add(pnTrangchu,BorderLayout.CENTER);
		
		
		JPanel pnTrangchu1=new JPanel();
		pnTrangchu1.setLayout(new BoxLayout(pnTrangchu1, BoxLayout.Y_AXIS));
		pnSouth.add(pnTrangchu1,BorderLayout.SOUTH);
		
		pnNorth.setBackground(Color.LIGHT_GRAY);
		pnSouth.setBackground(Color.YELLOW);
		pnCenter.setBackground(Color.LIGHT_GRAY);
		
		JPanel pnButton = new JPanel();
		btnCauThu= new JButton("Cầu Thủ");
		btnBanHuanLuyen= new JButton("Ban Huấn Luyện");
		btnDanhHieu=new JButton("Danh Hiệu");
		btnquaylai= new JButton("Quay Lại");
		pnButton.add(btnquaylai);
		pnButton.add(btnCauThu);
		btnCauThu.setBackground(Color.green);
		pnButton.add(btnBanHuanLuyen);
		btnBanHuanLuyen.setBackground(Color.CYAN);
		pnButton.add(btnDanhHieu);
		btnDanhHieu.setBackground(Color.GRAY);
		pnButton.add(btnCauThu);
		btnCauThu.setBackground(Color.green);
		pnTrangchu.add(pnButton);
		
		JPanel pnButton1= new JPanel();
		btnThoat= new JButton("Thoát");
		pnButton1.add(btnThoat);
		btnThoat.setBackground(Color.LIGHT_GRAY);
		pnTrangchu1.add(btnThoat);

		btnBanHuanLuyen.setIcon(new ImageIcon("hinhanh/referee.png"));
		btnDanhHieu.setIcon(new ImageIcon("hinhanh/title.png"));
		btnCauThu.setIcon(new ImageIcon("hinhanh/cauthu.png"));
		btnThoat.setIcon(new ImageIcon("hinhanh/exit.png"));
		
		JLabel themanh = new JLabel();
		themanh.setIcon(new ImageIcon("hinhanh/haha.jpeg"));
		pnCenter.add(themanh);
	}
	public void showWindow()
	{
		this.setSize(750,750);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
