package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.CauThu;
import service.CauThuSV;


public class ManHinhCauThu extends JFrame {
	JTextField txtSoAo,txtTenCauThu,txtTuoi,txtQueQuan,txtViTri,txtLuong,txtNgayBatDau,txtNgayKetThuc,txtTimKiem;
	JButton btnThem,btnLuu,btnSua,btnXoa,btnTimKiem,btnXuatFile,btnBack;
	DefaultTableModel dtmCauThu;
	JTable tblCauThu;
	ArrayList<CauThu>dsct;
	CauThu ct;
	CauThuSV ctsv;
	public ManHinhCauThu(String title)
	{
		super(title);
		addControls();
		addEvent();
		HienThiCauThu();
	}
	private void HienThiCauThu() {
		CauThuSV ctsv= new CauThuSV();
		dsct=ctsv.DanhSachCauThu();
		dtmCauThu.setRowCount(0);
		for(CauThu ct:dsct)
		{
			Vector<Object>vec= new Vector<Object>();
			vec.add(ct.getSoAo()); 
			vec.add(ct.getTenCauThu());
			vec.add(ct.getTuoi());
			vec.add(ct.getQuequan());
			vec.add(ct.getVitri());
			vec.add(ct.getMucLuong());
			vec.add(ct.getNgayBatDau());
			vec.add(ct.getNgayKetThuc());
			dtmCauThu.addRow(vec);
		}
	}
	
	private void addEvent() {
		tblCauThu.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=tblCauThu.getSelectedRow();
				if(row==-1)return;
				ct=dsct.get(row);
				txtSoAo.setText(ct.getSoAo());
				txtTenCauThu.setText(ct.getTenCauThu());
				txtTuoi.setText(ct.getTuoi());
				txtQueQuan.setText(ct.getQuequan());
				txtViTri.setText(ct.getVitri());
				txtLuong.setText(ct.getMucLuong());
				txtNgayBatDau.setText(ct.getNgayBatDau());
				txtNgayKetThuc.setText(ct.getNgayKetThuc());
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dtmCauThu.fireTableDataChanged();
				TableRowSorter<DefaultTableModel> sorter =new TableRowSorter<DefaultTableModel>(dtmCauThu);
				tblCauThu.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
				
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				XoaCauThu();
				
			}
		});
		btnThem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            them();              
	        }

			private void them() {
				txtSoAo.setText("");
				txtTenCauThu.setText("");
				txtTuoi.setText("");
				txtQueQuan.setText("");
				txtViTri.setText("");
				txtLuong.setText("");
				txtNgayBatDau.setText("");
				txtNgayKetThuc.setText("");
			}

			});
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LuuCauThu();
				
			}

			private void LuuCauThu() {
				
				try {
					CauThu ct = new CauThu();
					ct.setSoAo(txtSoAo.getText());
					ct.setTenCauThu(txtTenCauThu.getText());
					ct.setTuoi(txtTuoi.getText());
					ct.setQuequan(txtQueQuan.getText());
					ct.setVitri(txtViTri.getText());
					ct.setMucLuong(txtLuong.getText());
					ct.setNgayBatDau(txtNgayBatDau.getText());
					ct.setNgayKetThuc(txtNgayKetThuc.getText());;
					CauThuSV ctsv= new CauThuSV();
					if(ctsv.insert(ct))
					{
						HienThiCauThu();
						JOptionPane.showMessageDialog(null,"Lưu Thành Công");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Lưu Không thành Công");
					}
				
					
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Lỗi");
				}
				
			}
		});
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChonMenu menu = new ChonMenu("Chọn Menu");
				menu.showWindow();
				
			}
		});
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Sua();
				
			}

			private void Sua() {
				try {
					CauThu ct = new CauThu();
					ct.setSoAo(txtSoAo.getText());
					ct.setTenCauThu(txtTenCauThu.getText());
					ct.setTuoi(txtTuoi.getText());
					ct.setQuequan(txtQueQuan.getText());
					ct.setVitri(txtViTri.getText());
					ct.setMucLuong(txtLuong.getText());
					ct.setNgayBatDau(txtNgayBatDau.getText());
					ct.setNgayKetThuc(txtNgayKetThuc.getText());;
					CauThuSV ctsv= new CauThuSV();
					if(ctsv.update(ct))
					{
						HienThiCauThu();
						JOptionPane.showMessageDialog(null,"Cập Nhật Thành Công");
					}
					
					else
					{
						JOptionPane.showMessageDialog(null,"Cập Nhật Không thành Công");
					}
								
				}
				catch(Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Lỗi");
				}
				
			}
		});
		btnXuatFile.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					XSSFWorkbook wb = new XSSFWorkbook();
					XSSFSheet sh =wb.createSheet("Danh Sách");
					XSSFRow row = null;
					Cell cell = null;
					row=sh.createRow(3);
					cell=row.createCell(0,CellType.STRING);
					cell.setCellValue("STT");
					
					cell=row.createCell(1,CellType.STRING);
					cell.setCellValue("Số Áo");
					
					cell=row.createCell(2,CellType.STRING);
					cell.setCellValue("Tên Cầu Thủ");
					
					cell=row.createCell(3,CellType.STRING);
					cell.setCellValue("Tuổi");
					
					cell=row.createCell(4,CellType.STRING);
					cell.setCellValue("Quê Quán");
					
					cell=row.createCell(5,CellType.STRING);
					cell.setCellValue("Vị Trí");
					
					cell=row.createCell(6,CellType.STRING);
					cell.setCellValue("Lương");
					
					cell=row.createCell(7,CellType.STRING);
					cell.setCellValue("Ngày Bắt Đầu");
					
					cell=row.createCell(8,CellType.STRING);
					cell.setCellValue("Ngày Kết Thúc");
					
					for(int i=0;i<dsct.size();i++)
					{
						row=sh.createRow(4+i);
						
						cell=row.createCell(0,CellType.NUMERIC);
						cell.setCellValue(i+1);
						
						cell=row.createCell(1,CellType.STRING);
						cell.setCellValue(dsct.get(i).getSoAo());
						
						cell=row.createCell(2,CellType.STRING);
						cell.setCellValue(dsct.get(i).getTenCauThu());
						
						cell=row.createCell(3,CellType.STRING);
						cell.setCellValue(dsct.get(i).getTuoi());
						
						cell=row.createCell(4,CellType.STRING);
						cell.setCellValue(dsct.get(i).getQuequan());
						
						cell=row.createCell(5,CellType.STRING);
						cell.setCellValue(dsct.get(i).getVitri());
						
						cell=row.createCell(6,CellType.STRING);
						cell.setCellValue(dsct.get(i).getMucLuong());
						
						cell=row.createCell(7,CellType.STRING);
						cell.setCellValue(dsct.get(i).getNgayBatDau());
						
						cell=row.createCell(8,CellType.STRING);
						cell.setCellValue(dsct.get(i).getNgayKetThuc());
						
					}
					File f = new File("E:/danhsachcauthu.xlsx");
					try
					{
						FileOutputStream fis = new FileOutputStream(f);
						wb.write(fis);
						fis.close();
					}
					catch(FileNotFoundException ex)
					{
						ex.printStackTrace();
					}
					catch(IOException ee)
					{
						ee.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "In Thành Công");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "In Lỗi");
				}
					}
			
		});
	}

	protected void XoaCauThu() {
		if(ct==null)return;
		int ret=JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa không?","DELETE",JOptionPane.YES_NO_OPTION);
		if(ret==JOptionPane.YES_OPTION)
		{
			if(ctsv==null)
				ctsv=new CauThuSV()	;
			if(ctsv.XoaCauThu(ct)>0)
			{
				HienThiCauThu();
			}
		}
		
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
		
		pnNorth.setLayout(new BorderLayout());
		JPanel pnChiTiet =new JPanel();
		pnNorth.add(pnChiTiet,BorderLayout.CENTER);
		JPanel pnThucHien= new JPanel();
		pnNorth.add(pnThucHien,BorderLayout.EAST);
		
		pnChiTiet.setLayout(new BorderLayout());
		JPanel pnTrai = new JPanel();
		pnChiTiet.add(pnTrai,BorderLayout.WEST);
		JPanel pnPhai = new JPanel();
		pnChiTiet.add(pnPhai,BorderLayout.EAST);
		JPanel pnGiua = new JPanel();
		pnChiTiet.add(pnGiua,BorderLayout.CENTER);
		
		pnGiua.setLayout(new BoxLayout(pnGiua,BoxLayout.Y_AXIS));
		JPanel pnCauThu = new JPanel();
		JLabel lblCauThu = new JLabel("Thông Tin Cầu Thủ");
		lblCauThu.setForeground(Color.blue);
		Font ft= new Font("arial",Font.BOLD,20);
		lblCauThu.setFont(ft);
		pnCauThu.add(lblCauThu);
		pnGiua.add(pnCauThu);
		
			
		JPanel pnSoAo = new JPanel();
		JLabel lblSoAo = new JLabel("Số Áo");
		txtSoAo= new JTextField(20);
		pnSoAo.add(lblSoAo);
		pnSoAo.add(txtSoAo);
		pnGiua.add(pnSoAo);
		
		JPanel pnTen = new JPanel();
		JLabel lblTen = new JLabel("Tên Cầu Thủ");
		txtTenCauThu= new JTextField(20);
		pnTen.add(lblTen);
		pnTen.add(txtTenCauThu);
		pnGiua.add(pnTen);
		
		JPanel pnTuoi = new JPanel();
		JLabel lblTuoi = new JLabel("Tuổi");
		txtTuoi= new JTextField(20);
		pnTuoi.add(lblTuoi);
		pnTuoi.add(txtTuoi);
		pnGiua.add(pnTuoi);
		
		JPanel pnQueQuan = new JPanel();
		JLabel lblQueQuan = new JLabel("Quê Quán");
		txtQueQuan= new JTextField(20);
		pnQueQuan.add(lblQueQuan);
		pnQueQuan.add(txtQueQuan);
		pnGiua.add(pnQueQuan);
		
		JPanel pnViTri = new JPanel();
		JLabel lblViTri = new JLabel("Vị Trí");
		txtViTri= new JTextField(20);
		pnViTri.add(lblViTri);
		pnViTri.add(txtViTri);
		pnGiua.add(pnViTri);
		
		JPanel pnLuong = new JPanel();
		JLabel lblLuong = new JLabel("Lương");
		txtLuong= new JTextField(20);
		pnLuong.add(lblLuong);
		pnLuong.add(txtLuong);
		pnGiua.add(pnLuong);
		
		JPanel pnNgayBatDau = new JPanel();
		JLabel lblNgayBatDau = new JLabel("Ngày Bắt Đầu");
		txtNgayBatDau= new JTextField(20);
		pnNgayBatDau.add(lblNgayBatDau);
		pnNgayBatDau.add(txtNgayBatDau);
		pnGiua.add(pnNgayBatDau);
		
		JPanel pnNgayKetThuc = new JPanel();
		JLabel lblNgayKetThuc = new JLabel("Ngày Kết Thúc");
		txtNgayKetThuc= new JTextField(20);
		pnNgayKetThuc.add(lblNgayKetThuc);
		pnNgayKetThuc.add(txtNgayKetThuc);
		pnGiua.add(pnNgayKetThuc);
		
		pnTrai.setLayout(new BoxLayout(pnTrai,BoxLayout.Y_AXIS));
		JPanel pnanh= new JPanel();
		JPanel pnImage = new JPanel();
		JLabel lblIcon = new JLabel(new ImageIcon("hinhanh/mmm.gif"));
		pnImage.add(lblIcon);
		pnanh.add(pnImage);	
		pnTrai.add(pnanh); 
		
		pnPhai.setLayout(new BoxLayout(pnPhai,BoxLayout.Y_AXIS));
		JPanel pnanh1= new JPanel();
		JPanel pnImage1 = new JPanel();
		JLabel lblIcon1 = new JLabel(new ImageIcon("hinhanh/mess.gif"));
		pnImage1.add(lblIcon1);
		pnanh1.add(pnImage1);	
		pnPhai.add(pnanh1); 
		
		
		pnThucHien.setLayout(new BoxLayout(pnThucHien, BoxLayout.Y_AXIS));
		
		JPanel pnThem=new JPanel();
		btnThem= new JButton("Thêm");
		pnThem.add(btnThem);
		pnThucHien.add(pnThem);
		
		
		JPanel pnLuu=new JPanel();
		btnLuu= new JButton("Lưu");
		pnLuu.add(btnLuu);
		pnThucHien.add(pnLuu);
		
		
		JPanel pnSua=new JPanel();
		btnSua= new JButton("Sửa");
		pnSua.add(btnSua);
		pnThucHien.add(pnSua);
			
		
		JPanel pnXoa=new JPanel();
		btnXoa= new JButton("Xóa");
		pnXoa.add(btnXoa);
		pnThucHien.add(pnXoa);
		
		JPanel pnIn=new JPanel();
		btnXuatFile= new JButton("Xuất File");
		pnIn.add(btnXuatFile);
		pnThucHien.add(pnIn);
		
		JPanel pnBack=new JPanel();
		btnBack= new JButton("Trở Lại");
		pnBack.add(btnBack);
		pnThucHien.add(pnBack);
		
		pnCenter.setLayout(new BorderLayout());
		dtmCauThu= new DefaultTableModel();
		dtmCauThu.addColumn("Số Áo");
		dtmCauThu.addColumn("Tên Cầu Thủ");
		dtmCauThu.addColumn("Tuổi");
		dtmCauThu.addColumn("Quê Quán");
		dtmCauThu.addColumn("Vị Trí");
		dtmCauThu.addColumn("Lương");
		dtmCauThu.addColumn("Ngày Bắt Đầu");
		dtmCauThu.addColumn("Ngày Kết Thúc");
		tblCauThu= new JTable(dtmCauThu);
		JScrollPane scTable = new JScrollPane(tblCauThu,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scTable,BorderLayout.CENTER);
		
		
		JPanel pnButtonSouth= new JPanel();
		btnTimKiem= new JButton("Tìm Kiếm");
		pnButtonSouth.add(btnTimKiem);
		pnSouth.add(pnButtonSouth);
		
		JPanel pnTimKiem = new JPanel();
		JLabel lblTimKiem = new JLabel("Nhập Mã Cầu ");
		txtTimKiem= new JTextField(30);
		pnTimKiem.add(lblTimKiem);
		pnTimKiem.add(txtTimKiem);
		pnSouth.add(pnTimKiem);
		
		TitledBorder borderThongTinChiTiet= new TitledBorder(BorderFactory.createLineBorder(Color.RED),"Thông Tin Chi Tiết");
		pnChiTiet.setBorder(borderThongTinChiTiet);
		
		TitledBorder borderThucHien= new TitledBorder(BorderFactory.createLineBorder(Color.CYAN),"Chức Năng");
		pnThucHien.setBorder(borderThucHien);
		
		TitledBorder borderDanhSach= new TitledBorder(BorderFactory.createLineBorder(Color.GREEN),"Danh Sách");
		pnCenter.setBorder(borderDanhSach);
		
		lblSoAo.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblTen.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblTuoi.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblQueQuan.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblLuong.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblViTri.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblNgayBatDau.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		
		btnThem.setIcon(new ImageIcon("hinhanh/addd.png"));
		btnLuu.setIcon(new ImageIcon("hinhanh/diskette.png"));
		btnSua.setIcon(new ImageIcon("hinhanh/tools.png"));
		btnXoa.setIcon(new ImageIcon("hinhanh/delete.png"));
		btnTimKiem.setIcon(new ImageIcon("hinhanh/search.png"));
		btnXuatFile.setIcon(new ImageIcon("hinhanh/printer.png"));
		btnBack.setIcon(new ImageIcon("hinhanh/turn-back.png"));
	}
	public void showWindow()
	{
		this.setSize(1200,1200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

}


