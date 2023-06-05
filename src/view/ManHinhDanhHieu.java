package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
import model.DanhHieu;
import service.CauThuSV;
import service.DanhHieuSV;

public class ManHinhDanhHieu extends JFrame{
	JTextField txtMaDanhHieu,txtTenDanhHieu,txtNamNhan,txtmaSoAo,txtMaBHl,txtTimKiem;
	DefaultTableModel dtmDanhHieu;
	JButton btnThem,btnLuu,btnSua,btnXoa,btnTimKiem,btnXuatFile,btnTroLai;
	JTable tblDanhHieu;
	ArrayList<DanhHieu>dsdh;
	DanhHieu dh;
	DanhHieuSV dhsv;
	public ManHinhDanhHieu(String title)
	{
		super(title);
		addControls();
		addEvent();
		HienThiDanhHieu();
	}
	
	
	
	
	private void HienThiDanhHieu() {
		DanhHieuSV dhsv= new DanhHieuSV();
		dsdh=dhsv.DanhSachDanhHieu();
		dtmDanhHieu.setRowCount(0);
		for(DanhHieu dh:dsdh)
		{
			Vector<Object>vec= new Vector<Object>();
			vec.add(dh.getMaDanhHieu()); 
			vec.add(dh.getTenDanhHieu());
			vec.add(dh.getNamNhan());
			vec.add(dh.getMaSoAo());
			vec.add(dh.getMaBHL());
			dtmDanhHieu.addRow(vec);
		}
	}
	
	private void addEvent() {
		tblDanhHieu.addMouseListener(new MouseListener() {
			
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
				int row=tblDanhHieu.getSelectedRow();
				if(row==-1)return;
				dh=dsdh.get(row);
				txtMaDanhHieu.setText(dh.getMaDanhHieu());
				txtTenDanhHieu.setText(dh.getTenDanhHieu());
				txtNamNhan.setText(dh.getNamNhan());
				txtmaSoAo.setText(dh.getMaSoAo());
				txtMaBHl.setText(dh.getMaBHL());
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dtmDanhHieu.fireTableDataChanged();
				TableRowSorter<DefaultTableModel> sorter =new TableRowSorter<DefaultTableModel>(dtmDanhHieu);
				tblDanhHieu.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
				
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				XoaDanhHieu();
				
			}
		});
		btnThem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            them();              
	        }

			private void them() {
				txtMaDanhHieu.setText("");
				txtTenDanhHieu.setText("");
				txtNamNhan.setText("");
				txtmaSoAo.setText("");
				txtMaBHl.setText("");
			}

			});
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LuuDanhHieu();
				
			}

			private void LuuDanhHieu() {
				
				try {
					DanhHieu dh = new DanhHieu();
					dh.setMaBHL(txtMaDanhHieu.getText());
					dh.setTenDanhHieu(txtTenDanhHieu.getText());
					dh.setNamNhan(txtNamNhan.getText());
					dh.setMaSoAo(txtmaSoAo.getText());
					dh.setMaBHL(txtMaBHl.getText());
					DanhHieuSV dhsv= new DanhHieuSV();
					if(dhsv.insert(dh))
					{
						HienThiDanhHieu();
						JOptionPane.showMessageDialog(null,"Lưu Thành Công");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Lưu Không thành Công");
					}					
				}
				catch(Exception e)
				{
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
					DanhHieu dh = new DanhHieu();
					dh.setMaDanhHieu(txtMaDanhHieu.getText());
					dh.setTenDanhHieu(txtTenDanhHieu.getText());
					dh.setNamNhan(txtNamNhan.getText());
					dh.setMaSoAo(txtmaSoAo.getText());
					dh.setMaBHL(txtMaBHl.getText());
					DanhHieuSV dhsv= new DanhHieuSV();
					if(dhsv.update(dh))
					{
						HienThiDanhHieu();
						JOptionPane.showMessageDialog(null,"Cập Nhật Thành Công");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Cập Nhật Không thành Công");
					}
								
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Lỗi");
				}
				
			}
		});
		btnTroLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChonMenu menu = new ChonMenu("Chọn Menu");
				menu.showWindow();
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
					cell.setCellValue("Mã Danh Hiệu");
					
					cell=row.createCell(2,CellType.STRING);
					cell.setCellValue("Tên Danh Hiệu");
					
					cell=row.createCell(3,CellType.STRING);
					cell.setCellValue("Năm Nhận");
					
					cell=row.createCell(4,CellType.STRING);
					cell.setCellValue("Mã Cầu Thủ");
					
					cell=row.createCell(5,CellType.STRING);
					cell.setCellValue("Mã Danh Hiệu");
					
					
					for(int i=0;i<dsdh.size();i++)
					{
						row=sh.createRow(4+i);
						
						cell=row.createCell(0,CellType.NUMERIC);
						cell.setCellValue(i+1);
						
						cell=row.createCell(1,CellType.STRING);
						cell.setCellValue(dsdh.get(i).getMaDanhHieu());
						
						cell=row.createCell(2,CellType.STRING);
						cell.setCellValue(dsdh.get(i).getTenDanhHieu());
						
						cell=row.createCell(3,CellType.STRING);
						cell.setCellValue(dsdh.get(i).getNamNhan());
						
						cell=row.createCell(4,CellType.STRING);
						cell.setCellValue(dsdh.get(i).getMaSoAo());
						
						cell=row.createCell(5,CellType.STRING);
						cell.setCellValue(dsdh.get(i).getMaBHL());
					}
					File f = new File("E:/danhsachDanhHieu.xlsx");
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
					JOptionPane.showMessageDialog(null, "In Lỗi");
				}
					}
			
		});
	}

	protected void XoaDanhHieu() {
		if(dh==null)return;
		int ret=JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa không?","DELETE",JOptionPane.YES_NO_OPTION);
		if(ret==JOptionPane.YES_OPTION)
		{
			if(dhsv==null)
				dhsv=new DanhHieuSV()	;
			if(dhsv.XoaDanhHieu(dh)>0)
			{
				HienThiDanhHieu();
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
		JLabel lblCauThu = new JLabel("Thông Tin Danh Hiệu");
		lblCauThu.setForeground(Color.blue);
		Font ft= new Font("arial",Font.BOLD,20);
		lblCauThu.setFont(ft);
		pnCauThu.add(lblCauThu);
		pnGiua.add(pnCauThu);
		
			
		JPanel pnMaDanhHieu = new JPanel();
		JLabel lblMaDanhHieu = new JLabel("Mã Danh Hiệu");
		txtMaDanhHieu= new JTextField(20);
		pnMaDanhHieu.add(lblMaDanhHieu);
		pnMaDanhHieu.add(txtMaDanhHieu);
		pnGiua.add(pnMaDanhHieu);
		
		JPanel pnTen = new JPanel();
		JLabel lblTen = new JLabel("Tên Danh Hiệu");
		txtTenDanhHieu= new JTextField(20);
		pnTen.add(lblTen);
		pnTen.add(txtTenDanhHieu);
		pnGiua.add(pnTen);
		
		JPanel pnNamNhan = new JPanel();
		JLabel lblNamNhan = new JLabel("Năm Nhận");
		txtNamNhan= new JTextField(20);
		pnNamNhan.add(lblNamNhan);
		pnNamNhan.add(txtNamNhan);
		pnGiua.add(pnNamNhan);
		
		JPanel pnMact = new JPanel();
		JLabel lblMact = new JLabel("Mã Cầu Thủ");
		txtmaSoAo= new JTextField(20);
		pnMact.add(lblMact);
		pnMact.add(txtmaSoAo);
		pnGiua.add(pnMact);
		
		JPanel pnma = new JPanel();
		JLabel lblma = new JLabel("Mã BHL");
		txtMaBHl= new JTextField(20);
		pnma.add(lblma);
		pnma.add(txtMaBHl);
		pnGiua.add(pnma);
		
		pnTrai.setLayout(new BoxLayout(pnTrai,BoxLayout.Y_AXIS));
		JPanel pnanh= new JPanel();
		JPanel pnImage = new JPanel();
		JLabel lblIcon = new JLabel(new ImageIcon("hinhanh/cup1.jpg"));
		pnImage.add(lblIcon);
		pnanh.add(pnImage);	
		pnTrai.add(pnanh); 
		
		pnPhai.setLayout(new BoxLayout(pnPhai,BoxLayout.Y_AXIS));
		JPanel pnanh1= new JPanel();
		JPanel pnImage1 = new JPanel();
		JLabel lblIcon1 = new JLabel(new ImageIcon("hinhanh/cham1.jpg"));
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
		btnXuatFile= new JButton("In File");
		pnIn.add(btnXuatFile);
		pnThucHien.add(pnIn);
		
		JPanel pnl=new JPanel();
		btnTroLai= new JButton("Back");
		pnl.add(btnTroLai);
		pnThucHien.add(pnl);
		
		pnCenter.setLayout(new BorderLayout());
		dtmDanhHieu= new DefaultTableModel();
		dtmDanhHieu.addColumn("Mã Danh Hiệu");
		dtmDanhHieu.addColumn("Tên Danh Hiệu");
		dtmDanhHieu.addColumn("Năm Nhận");
		dtmDanhHieu.addColumn("Mã Cầu Thủ");
		dtmDanhHieu.addColumn("Mã Ban Huấn luyện");
		tblDanhHieu= new JTable(dtmDanhHieu);
		JScrollPane scTable = new JScrollPane(tblDanhHieu,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scTable,BorderLayout.CENTER);
		
		JPanel pnButtonSouth= new JPanel();
		btnTimKiem= new JButton("Tìm Kiếm");
		pnButtonSouth.add(btnTimKiem);
		pnSouth.add(pnButtonSouth);
		
		JPanel pnTimKiem = new JPanel();
		JLabel lblTimKiem = new JLabel("Nhập Tên");
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
		
		lblNamNhan.setPreferredSize(lblMaDanhHieu.getPreferredSize());
		lblTen.setPreferredSize(lblMaDanhHieu.getPreferredSize());
		lblMact.setPreferredSize(lblMaDanhHieu.getPreferredSize());
		lblma.setPreferredSize(lblMaDanhHieu.getPreferredSize());
		
		btnThem.setIcon(new ImageIcon("hinhanh/addd.png"));
		btnLuu.setIcon(new ImageIcon("hinhanh/diskette.png"));
		btnSua.setIcon(new ImageIcon("hinhanh/tools.png"));
		btnXoa.setIcon(new ImageIcon("hinhanh/delete.png"));
		btnTimKiem.setIcon(new ImageIcon("hinhanh/search.png"));
		btnXuatFile.setIcon(new ImageIcon("hinhanh/printer.png"));
		btnTroLai.setIcon(new ImageIcon("hinhanh/turn-back.png"));
	}
	public void showWindow()
	{
		this.setSize(1200,1200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

}


