package com.a.view.users;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.a.pojo.Cinema;
import com.a.service.SessionService;
import com.a.service.Impl.SessionServiceImpl;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VideoNewsView extends JInternalFrame {
	private JTable table;
	private JTextField txtMovieName;
	private JTextField txtCinemaName;
	
	public void initTable(String movieName, String cinemaName) {
		//��table��ʾ����
		
		//����������vector��
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("���");
		columnNames.add("ӰԺ");
		columnNames.add("Ӱ��");
		columnNames.add("��Ӱ");
		columnNames.add("ʱ��");
		columnNames.add("ʱ��");
		columnNames.add("Ʊ��");
		columnNames.add("����");
		
		//�����ݷ�װ����һ��vector��
		SessionService ss = new SessionServiceImpl();
		Vector<Vector<String>> data = ss.findAnyMovieInfo(movieName, cinemaName);
		
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
					VideoNewsView frame = new VideoNewsView();
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
	public VideoNewsView() {
		setTitle("\u5F71\u8BAF");
		setBounds(80, 20, 636, 588);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 144, 554, 231);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("\u8D2D\u7968");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//��Ʊ��ť�¼�
				int rowIndex = table.getSelectedRow();
				if (rowIndex > -1) {
					String sessionId = (String) table.getValueAt(rowIndex, 0);
				
					ShoppingTicketView stv = new ShoppingTicketView(sessionId);
					stv.setModal(true); //ģ̬������
					stv.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ���Ӱ��Ϣ�����У�");
				}
			}
		});
		button.setBounds(420, 81, 123, 29);
		getContentPane().add(button);
		
		JLabel label = new JLabel("\u7535\u5F71\uFF1A");
		label.setBounds(15, 15, 81, 21);
		getContentPane().add(label);
		
		txtMovieName = new JTextField();
		txtMovieName.setBounds(66, 12, 96, 27);
		getContentPane().add(txtMovieName);
		txtMovieName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5F71\u9662:");
		label_1.setBounds(182, 15, 81, 21);
		getContentPane().add(label_1);
		
		txtCinemaName = new JTextField();
		txtCinemaName.setBounds(246, 12, 96, 27);
		getContentPane().add(txtCinemaName);
		txtCinemaName.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ӰѶ��ѯ��ť�¼�
				
				//�õ���ѯ����
				String movieName = txtMovieName.getText();
				String cinemaName = txtCinemaName.getText();
				
				//����initTable����
				initTable(movieName, cinemaName);
			}
		});
		btnNewButton.setBounds(420, 11, 123, 29);
		getContentPane().add(btnNewButton);
		
		initTable(null,null);
	}
}
