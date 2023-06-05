package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class GioiThieu extends JDialog{
	public GioiThieu(String title)
	{
		this.setTitle(title);
		addControls();
		addEvent();
	}

	private void addEvent() {
		// TODO Auto-generated method stub
		
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
		
		pnCenter.setLayout(new BorderLayout());
		JPanel pnImage = new JPanel();
		JLabel lblIcon = new JLabel(new ImageIcon("hinhanh/pss.gif"));
		pnImage.add(lblIcon);
		pnCenter.add(pnImage,BorderLayout.WEST);
		
		JLabel lblTitle =new JLabel("Câu lạc bộ bóng đá Paris Saint-Germain");
		lblTitle.setFont(new Font("tahoma",Font.BOLD,15));
		lblTitle.setForeground(Color.RED);
		pnNorth.add(lblTitle);	
		
		JPanel pnTrangchu=new JPanel();
		pnTrangchu.setLayout(new BoxLayout(pnTrangchu, BoxLayout.Y_AXIS));
		pnCenter.add(pnTrangchu,BorderLayout.CENTER);
		JPanel pnTenCLB= new JPanel();
		pnTenCLB.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabel1=new JLabel("Câu lạc bộ bóng đá Paris Saint-Germain là một câu ");
		JLabel jLabel2= new JLabel("lạc bộ bóng đá chuyên nghiệp có trụ sở tại Paris,");
		JLabel jLabel3 = new JLabel("Pháp. Tiền thân là câu lạc bộ đa thể thao Stade Saint-");
		JLabel jLabel4 = new JLabel("Germain, Paris Saint-Germain chính thức được thành");
		JLabel jLabel5 = new JLabel("lập năm 1970 và hiện đang thi đấu tại Ligue 1.");
		JLabel lblsan= new JLabel("Sân/sân vận động:  Sân vận động Công viên các Hoàng tử");
		JLabel lblvitri=new JLabel("Vị trí Của CLB: Thành phố Paris, Pháp");
		JLabel lblhlv= new JLabel("Người quản lý: Chủ tịch Nasser Al-Khelaifi (thg 11 2011–)");	
		JLabel lblchusohuu= new JLabel("Chủ sở hữu: Qatar Sports Investments Group ");
		JLabel lblgiaidau=new JLabel("Giải đấu: Giải Bóng đá Vô địch Quốc gia Pháp");
		pnTenCLB.add(jLabel1);
		pnTenCLB.add(jLabel2);
		pnTenCLB.add(jLabel3);
		pnTenCLB.add(jLabel4);
		pnTenCLB.add(jLabel5);
		pnTenCLB.add(lblsan);
		pnTenCLB.add(lblvitri);
		pnTenCLB.add(lblhlv);
		pnTenCLB.add(lblgiaidau);
		pnTenCLB.add(lblchusohuu);
		pnTrangchu.add(pnTenCLB);
		
	}
	public void showWindow()
	{
		this.setSize(1100,800);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}
}
