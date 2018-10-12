package com.a.view.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.a.pojo.Cinema;
import com.a.service.CinemaService;
import com.a.service.HallService;
import com.a.service.Impl.CinemaServiceImpl;
import com.a.service.Impl.HallServiceImpl;

public class ShowCinemaView extends JInternalFrame {
	private JTextField txtCinemaName;
	private JTable table;
	
	
	public void initTable(Cinema c) {
		//��table��ʾ����
		
		//����������vector��
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("ӰԺ���");
		columnNames.add("ӰԺ����");
		columnNames.add("ӰԺ��ַ");
		
		//�����ݷ�װ����һ��vector��
		CinemaService cs = new CinemaServiceImpl();
		
		Vector data = cs.findCinema(c);
		
		
		//���к����ݷŵ�model��
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		
		//��model��ӵ�table��
		table.setModel(dm);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowCinemaView frame = new ShowCinemaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowCinemaView() {
		setBounds(60, 20, 589, 494);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5F71\u9662\u540D\u79F0\uFF1A");
		label.setBounds(40, 40, 101, 21);
		getContentPane().add(label);
		
		txtCinemaName = new JTextField();
		txtCinemaName.setBounds(128, 37, 96, 27);
		getContentPane().add(txtCinemaName);
		txtCinemaName.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ѯӰԺ��ť�¼�
				
				//��ѯ����
				String cinemaname = txtCinemaName.getText();
				Cinema c = new Cinema();
				c.setCinemaName(cinemaname);
				//ˢ������
				initTable(c);
			}
		});
		
		button.setBounds(239, 36, 101, 29);
		getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 100, 529, 201);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//table����¼�
				
				//��ȡ����
				int rowIndex = table.getSelectedRow();
				Integer cinemaId = new Integer(table.getValueAt(rowIndex, 0).toString());
				
			}
		});
		table.setBounds(296, 222, 1, 1);
		
		scrollPane.setViewportView(table);
		
		JButton button_2 = new JButton("\u6DFB\u52A0\u5F71\u5385");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//���Ӱ����ť�¼�
				
				//�õ�ӰԺID
				int rowIndex = table.getSelectedRow();
				if (rowIndex > -1) {
					Integer cinemaId = new Integer(table.getValueAt(rowIndex, 0).toString());
					//���Ӱ������
					AddHallView ahv = new AddHallView(cinemaId);
					
					ahv.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ��ӰԺ�����У�");
				}
			}
		});
		button_2.setBounds(366, 36, 113, 29);
		getContentPane().add(button_2);
		
		JButton button_1 = new JButton("\u5220\u9664\u5F71\u9662");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ɾ��ӰԺ��ť�¼�
				System.out.println("jfsl");
				//1.�õ�Ҫɾ��ӰԺID
				int rowIndex = table.getSelectedRow();
				if (rowIndex > -1) {

					Integer cinemaId = new Integer(table.getValueAt(rowIndex, 0).toString());
					
					
					//2.��ѯӰԺ�����Ƿ���Ӱ��
					HallService hs = new HallServiceImpl();
					Vector<Vector<String>> cinemaData = hs.findAllHallByCinemaId(cinemaId);
					
					System.out.println(cinemaData);
					if (cinemaData.size() == 0) {
						//2.1���û����ֱ��ִ��ɾ��Ӱ������
						
						CinemaService cs = new CinemaServiceImpl();
						int i = cs.deleteCinema(cinemaId);
						String message = i>0?"�ɹ�ɾ��ӰԺ��":"ɾ��ʧ������ϵϵͳ����Ա��";
						JOptionPane.showMessageDialog(null, message);
						
					} else {
						//2.2 �����Ӱ���򷵻�һ�����ڲ�˵����ɾ��Ӱ��
						System.out.println("no");
						JOptionPane.showMessageDialog(null, "����ɾ��Ӱ����");
					}
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ��ӰԺ�����У�");
				}
			}
		});
		button_1.setBounds(40, 349, 123, 29);
		getContentPane().add(button_1);
		
		JButton button_3 = new JButton("\u5220\u9664\u5F71\u5385");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ɾ��Ӱ����ť�¼�
				
				//1.�õ�Ҫɾ��ӰԺID
				int rowIndex = table.getSelectedRow();
				if (rowIndex > -1) {
					Integer cinemaId = new Integer(table.getValueAt(rowIndex, 0).toString());
					
					//2.����ɾ��Ӱ������
					DeleteHallView dhv = new DeleteHallView(cinemaId);
					
					//�رմ��ڣ���Ӱ�������ڣ��Ѿ��� DeleteHallView ���췽���������Ļ��˴�����Ҫ
					//dhv.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
					
					dhv.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ��ӰԺ�����У�");
				}
			}
		});
		button_3.setBounds(356, 349, 123, 29);
		getContentPane().add(button_3);
		
		Cinema c = new Cinema();
		initTable(c);
	}
}
