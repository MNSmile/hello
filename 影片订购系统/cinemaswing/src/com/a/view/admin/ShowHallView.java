package com.a.view.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.a.service.HallService;
import com.a.service.Impl.HallServiceImpl;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowHallView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Integer cinemaId;
	
	public void initTable() {
		//��table��ʾ����
		
		//����������vector��
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("���");
		columnNames.add("Ӱ������");
		columnNames.add("����");
		
		//�����ݷ�װ����һ��vector��
		HallService hs = new HallServiceImpl();
		Vector<Vector<String>> data = hs.findAllHallByCinemaId(cinemaId);
		
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
					ShowHallView frame = new ShowHallView(null);
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
	public ShowHallView(Integer cinemaId) {
		//�رմ˴��ڣ���Ӱ��������
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 500, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 15, 398, 214);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		this.cinemaId = cinemaId;
		initTable();
	}
}
