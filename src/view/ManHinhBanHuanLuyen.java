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

import model.BanHuanLuyen;
import service.BanHuanLuyenSV;


public class ManHinhBanHuanLuyen extends JFrame {
	JTextField txtMa,txtTenBHL,txtTuoi,txtQuocTich,txtChucVu,txtLuong,txtNgayBatDau,txtNgayKetThuc,txtTimKiem;
	JButton btnThem,btnLuu,btnSua,btnXoa,btnTimKiem,btnXuatFile,btnBack;
	DefaultTableModel dtmBanHuanLuyen;
	JTable tblBanHuanLuyen;
	ArrayList<BanHuanLuyen>dsbhl;
	BanHuanLuyen bhl;
	BanHuanLuyenSV bhlsv;
	public ManHinhBanHuanLuyen(String title)
	{
		super(title);
		addControls();
		addEvent();
		HienThiBHL();
	}
	private void HienThiBHL() {
		BanHuanLuyenSV ctsv= new BanHuanLuyenSV();
		dsbhl=ctsv.DanhSachBanHuanLuyen();
		dtmBanHuanLuyen.setRowCount(0);
		for(BanHuanLuyen bhl:dsbhl)
		{
			Vector<Object> vec = new Vector<Object>();
			vec.add(bhl.getMaBanHuanLuyen());
			vec.add(bhl.getTenBanHuanLuyen());
			vec.add(bhl.getTuoi());
			vec.add(bhl.getQuocTich());
			vec.add(bhl.getLuong());
			vec.add(bhl.getChucVu());
			vec.add(bhl.getNgayBatDau());
			vec.add(bhl.getNgayKetThuc());
			dtmBanHuanLuyen.addRow(vec);
		}
	}
	
	private void addEvent() {
		tblBanHuanLuyen.addMouseListener(new MouseListener() {
			
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
				int row=tblBanHuanLuyen.getSelectedRow();
				if(row==-1)return;
				bhl=dsbhl.get(row);
				txtMa.setText(bhl.getMaBanHuanLuyen()+"");
				txtTenBHL.setText(bhl.getTenBanHuanLuyen());
				txtTuoi.setText(bhl.getTuoi()+"");
				txtQuocTich.setText(bhl.getQuocTich());
				txtChucVu.setText(bhl.getChucVu());
				txtLuong.setText(bhl.getLuong()+"");
				txtNgayBatDau.setText(bhl.getNgayBatDau()+"");
				txtNgayKetThuc.setText(bhl.getNgayKetThuc()+"");
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dtmBanHuanLuyen.fireTableDataChanged();
				TableRowSorter<DefaultTableModel> sorter =new TableRowSorter<DefaultTableModel>(dtmBanHuanLuyen);
				tblBanHuanLuyen.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));			
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Xoa();
				
			}
		});
		btnThem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            themSinhVien();              
	        }

			private void themSinhVien() {
				txtMa.setText("");
				txtTenBHL.setText("");
				txtTuoi.setText("");
				txtQuocTich.setText("");
				txtChucVu.setText("");
				txtLuong.setText("");
				txtNgayBatDau.setText("");
				txtNgayKetThuc.setText("");
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
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LuuCauThu();
				
			}

			private void LuuCauThu() {
				
				try {
					BanHuanLuyen bhl = new BanHuanLuyen();
					bhl.setMaBanHuanLuyen(txtMa.getText());
					bhl.setTenBanHuanLuyen(txtTenBHL.getText());
					bhl.setTuoi(txtTuoi.getText());
					bhl.setQuocTich(txtQuocTich.getText());
					bhl.setChucVu(txtChucVu.getText());
					bhl.setLuong(txtLuong.getText());
					bhl.setNgayBatDau(txtNgayBatDau.getText());
					bhl.setNgayKetThuc(txtNgayKetThuc.getText());
					BanHuanLuyenSV sv= new BanHuanLuyenSV();
					if(sv.insert(bhl))
					{
						HienThiBHL();
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
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Sua();
				
			}

			private void Sua() {
				try {
					BanHuanLuyen bhl = new BanHuanLuyen();
					bhl.setMaBanHuanLuyen(txtMa.getText());
					bhl.setTenBanHuanLuyen(txtTenBHL.getText());
					bhl.setTuoi(txtTuoi.getText());
					bhl.setQuocTich(txtQuocTich.getText());
					bhl.setChucVu(txtChucVu.getText());
					bhl.setLuong(txtLuong.getText());
					bhl.setNgayBatDau(txtNgayBatDau.getText());
					bhl.setNgayKetThuc(txtNgayKetThuc.getText());
					BanHuanLuyenSV sv= new BanHuanLuyenSV();
					if(sv.update(bhl))
					{
						HienThiBHL();
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
					cell.setCellValue("Mã Ban Huấn Luyện");
					
					cell=row.createCell(2,CellType.STRING);
					cell.setCellValue("Tên Ban Huấn Luyện");
					
					cell=row.createCell(3,CellType.STRING);
					cell.setCellValue("Tuổi");
					
					cell=row.createCell(4,CellType.STRING);
					cell.setCellValue("Quốc Tịch");
					
					cell=row.createCell(5,CellType.STRING);
					cell.setCellValue("Chức Vụ");
					
					cell=row.createCell(6,CellType.STRING);
					cell.setCellValue("Lương");
					
					cell=row.createCell(7,CellType.STRING);
					cell.setCellValue("Ngày Bắt Đầu");
					
					cell=row.createCell(8,CellType.STRING);
					cell.setCellValue("Ngày Kết Thúc");
					
					for(int i=0;i<dsbhl.size();i++)
					{
						row=sh.createRow(4+i);
						
						cell=row.createCell(0,CellType.NUMERIC);
						cell.setCellValue(i+1);
						
						cell=row.createCell(1,CellType.STRING);
						cell.setCellValue(dsbhl.get(i).getMaBanHuanLuyen());
						
						cell=row.createCell(2,CellType.STRING);
						cell.setCellValue(dsbhl.get(i).getTenBanHuanLuyen());
						
						cell=row.createCell(3,CellType.STRING);
						cell.setCellValue(dsbhl.get(i).getTuoi());
						
						cell=row.createCell(4,CellType.STRING);
						cell.setCellValue(dsbhl.get(i).getQuocTich());
						
						cell=row.createCell(5,CellType.STRING);
						cell.setCellValue(dsbhl.get(i).getChucVu());
						
						cell=row.createCell(6,CellType.STRING);
						cell.setCellValue(dsbhl.get(i).getLuong());
						
						cell=row.createCell(7,CellType.STRING);
						cell.setCellValue(dsbhl.get(i).getNgayBatDau());
						
						cell=row.createCell(8,CellType.STRING);
						cell.setCellValue(dsbhl.get(i).getNgayKetThuc());						
					}
					File f = new File("E:/danhsachBHL.xlsx");
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

		
	protected void Xoa() {
		if(bhl==null)return;
		int ret=JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa không?","DELETE",JOptionPane.YES_NO_OPTION);
		if(ret==JOptionPane.YES_OPTION)
		{
			if(bhlsv==null)
				bhlsv=new BanHuanLuyenSV()	;
			if(bhlsv.XoaBanHuanLuyen(bhl)>0)
			{
				HienThiBHL();
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
		JLabel lblCauThu = new JLabel("Thông Tin Ban Huấn Luyện");
		lblCauThu.setForeground(Color.blue);
		Font ft= new Font("arial",Font.BOLD,20);
		lblCauThu.setFont(ft);
		pnCauThu.add(lblCauThu);
		pnGiua.add(pnCauThu);
		
		JPanel pnMaBHL=new JPanel();
		JLabel lblMa= new JLabel("Mã Ban Huấn Luyện");
		txtMa= new JTextField(20);
		pnMaBHL.add(lblMa);
		pnMaBHL.add(txtMa);
		pnGiua.add(pnMaBHL);

		
		JPanel pnTenBanHuanLuyen=new JPanel();
		JLabel lblTenbanHuanLuyen= new JLabel("Họ Tên");
		txtTenBHL= new JTextField(20);
		pnTenBanHuanLuyen.add(lblTenbanHuanLuyen);
		pnTenBanHuanLuyen.add(txtTenBHL);
		pnGiua.add(pnTenBanHuanLuyen);
		
		JPanel pnTuoi= new JPanel();
		JLabel lblTuoi= new JLabel("Tuổi");
		txtTuoi= new JTextField(20);
		pnTuoi.add(lblTuoi);
		pnTuoi.add(txtTuoi);
		pnGiua.add(pnTuoi);
		
		JPanel pnQueQuan = new JPanel();
		JLabel lblQueQuan = new JLabel("Quốc Tịch");
		txtQuocTich= new JTextField(20);
		pnQueQuan.add(lblQueQuan);
		pnQueQuan.add(txtQuocTich);
		pnGiua.add(pnQueQuan);
		
		JPanel pnLuong= new JPanel();
		JLabel lblLuong = new JLabel("Lương");
		txtLuong= new JTextField(20);
		pnLuong.add(lblLuong);
		pnLuong.add(txtLuong);
		pnGiua.add(pnLuong);
		
		JPanel pnChucVu = new JPanel();
		JLabel lblChucVu = new JLabel("Chức Vụ ");
		txtChucVu = new JTextField(20);
		pnChucVu.add(lblChucVu);
		pnChucVu.add(txtChucVu);
		pnGiua.add(pnChucVu);
		
		JPanel pnNgayBatDau= new JPanel();
		JLabel lblNgayBatDau= new JLabel("Ngày Bắt Đầu");
		txtNgayBatDau = new JTextField(20);
		pnNgayBatDau.add(lblNgayBatDau);
		pnNgayBatDau.add(txtNgayBatDau);
		pnGiua.add(pnNgayBatDau);
		
		JPanel pnNgayKetThuc = new JPanel();
		JLabel lblNgayKetThuc= new JLabel("Ngày Kết Thúc");
		txtNgayKetThuc= new JTextField(20);
		pnNgayKetThuc.add(lblNgayKetThuc);
		pnNgayKetThuc.add(txtNgayKetThuc);
		pnGiua.add(pnNgayKetThuc);
		
		
		pnTrai.setLayout(new BoxLayout(pnTrai,BoxLayout.Y_AXIS));
		JPanel pnanh= new JPanel();
		JPanel pnImage = new JPanel();
		JLabel lblIcon = new JLabel(new ImageIcon("hinhanh/hlv.png"));
		pnImage.add(lblIcon);
		pnanh.add(pnImage);	
		pnTrai.add(pnanh); 
		
		pnPhai.setLayout(new BoxLayout(pnPhai,BoxLayout.Y_AXIS));
		JPanel pnanh1= new JPanel();
		JPanel pnImage1 = new JPanel();
		JLabel lblIcon1 = new JLabel(new ImageIcon("hinhanh/ps.png"));
		pnImage1.add(lblIcon1);
		pnanh1.add(pnImage1);	
		pnPhai.add(pnanh1); 

		JPanel pnButtonSouth= new JPanel();
		btnTimKiem= new JButton("Tìm Kiếm");
		pnButtonSouth.add(btnTimKiem);
		pnSouth.add(pnButtonSouth);
		
		JPanel pnTimKiem = new JPanel();
		JLabel lblTimKiem = new JLabel("Nhập Mã Ban Huần Luyện ");
		txtTimKiem= new JTextField(30);
		pnTimKiem.add(lblTimKiem);
		pnTimKiem.add(txtTimKiem);
		pnSouth.add(pnTimKiem);
		
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
		
		JPanel pnTl=new JPanel();
		btnBack= new JButton("Trở lại");
		pnTl.add(btnBack);
		pnThucHien.add(pnTl);
		
		pnCenter.setLayout(new BorderLayout());
		dtmBanHuanLuyen= new DefaultTableModel();
		dtmBanHuanLuyen.addColumn("Mã Ban Huấn Luyện");
		dtmBanHuanLuyen.addColumn("Họ Tên ");
		dtmBanHuanLuyen.addColumn("Tuổi");
		dtmBanHuanLuyen.addColumn("Quốc Tịch");
		dtmBanHuanLuyen.addColumn("Chức Vụ");
		dtmBanHuanLuyen.addColumn("Lương");
		dtmBanHuanLuyen.addColumn("NgàyBắtĐầu");
		dtmBanHuanLuyen.addColumn("NgàyKếtThúc");		
		tblBanHuanLuyen= new JTable(dtmBanHuanLuyen);
		JScrollPane scTable = new JScrollPane(tblBanHuanLuyen,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scTable,BorderLayout.CENTER);
		
		TitledBorder borderThongTinChiTiet= new TitledBorder(BorderFactory.createLineBorder(Color.RED),"Thông Tin Chi Tiết");
		pnChiTiet.setBorder(borderThongTinChiTiet);
		
		TitledBorder borderThucHien= new TitledBorder(BorderFactory.createLineBorder(Color.CYAN),"Chức Năng");
		pnThucHien.setBorder(borderThucHien);
		
		TitledBorder borderDanhSach= new TitledBorder(BorderFactory.createLineBorder(Color.GREEN),"Danh Sách");
		pnCenter.setBorder(borderDanhSach);
		
		lblNgayBatDau.setPreferredSize(lblMa.getPreferredSize());
		lblNgayKetThuc.setPreferredSize(lblMa.getPreferredSize());
		lblLuong.setPreferredSize(lblMa.getPreferredSize());
		lblTuoi.setPreferredSize(lblMa.getPreferredSize());
		lblQueQuan.setPreferredSize(lblMa.getPreferredSize());
		lblChucVu.setPreferredSize(lblMa.getPreferredSize());
		lblTenbanHuanLuyen.setPreferredSize(lblMa.getPreferredSize());
		
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



