package com.a.view.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.a.pojo.CinemaUsers;
import com.a.service.CinemaUsersService;
import com.a.service.SessionService;
import com.a.service.TicketService;
import com.a.service.Impl.CinemaUsersServiceImpl;
import com.a.service.Impl.SessionServiceImpl;
import com.a.service.Impl.TicketServiceImpl;
import com.a.util.IdSave;

public class ShowTicketView extends JDialog {
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	
	
	public void initTable() {
		//��table��ʾ����
		
		//1.����������vector��
		Vector<String> columnNames = new Vector<String>();
		//2.��Ӱ����ӰԺ��Ӱ������ӳʱ�䣬��λ�ţ�ʱ�����۸�
		columnNames.add("���");
		columnNames.add("��Ӱ");
		columnNames.add("ӰԺ");
		columnNames.add("Ӱ��");
		columnNames.add("��λ��");
		columnNames.add("ʱ��");
		columnNames.add("ʱ��");
		columnNames.add("�۸�");
		
		//3.�����ݷ�����һ��vector��
		TicketService ts = new TicketServiceImpl();
		List<Map<String,Object>> findSessionidByUserId = ts.findSessionidByUserId("1", IdSave.userId);
		//System.out.println(IdSave.userId);
		//System.out.println(findSessionidByUserId);
		Set<String> sidSet = new HashSet<String>();
		for (Map<String, Object> map : findSessionidByUserId) {
			sidSet.add(map.get("SID").toString());
		}
		SessionService ss = new SessionServiceImpl();
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		for (String sessionid : sidSet) {
			Vector<Vector<String>> data1 = ss.findMovieRelatedInformationBySessionid(sessionid, "1",IdSave.userId);
			for (Vector<String> vector : data1) {
				data.add(vector);
			}
		}
		
		//4.���к����ݷŵ�model��
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		//5.��model��ӵ�table��
		table.setModel(dm);
		//���ر��
		hideTableColumn(table,0);
	}
	
	
	
	public void initTable_1() {
		//��table��ʾ����
		
		//����������vector��
		Vector<String> columnNames = new Vector<String>();
		//��Ӱ����ӰԺ��Ӱ������ӳʱ�䣬��λ�ţ�ʱ�����۸�
		columnNames.add("���");
		columnNames.add("��Ӱ");
		columnNames.add("ӰԺ");
		columnNames.add("Ӱ��");
		columnNames.add("��λ��");
		columnNames.add("ʱ��");
		columnNames.add("ʱ��");
		columnNames.add("�۸�");
		
		//�����ݷ�����һ��vector��
		TicketService ts = new TicketServiceImpl();
		List<Map<String,Object>> findSessionidByUserId = ts.findSessionidByUserId("0", IdSave.userId);
		//System.out.println(findSessionidByUserId);
		Set<String> sidSet = new HashSet<String>();
		for (Map<String, Object> map : findSessionidByUserId) {
			sidSet.add(map.get("SID").toString());
		}
		SessionService ss = new SessionServiceImpl();
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		for (String sessionid : sidSet) {
			Vector<Vector<String>> data1 = ss.findMovieRelatedInformationBySessionid(sessionid, "0",IdSave.userId);
			for (Vector<String> vector : data1) {
				data.add(vector);
//				System.out.println(vector);
			}
		}
		
		//���к����ݷŵ�model��
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		//��model��ӵ�table��
		table_1.setModel(dm);
		//���ر��
		hideTableColumn(table_1,0);
	}
	/**
	 * ������
	 * @param table
	 * @param column
	 */
	private void hideTableColumn(JTable table,int column) {
		//�����п���ȡֵ
		TableColumn tc = table.getTableHeader().getColumnModel().getColumn(column);
		tc.setMaxWidth(0);
		tc.setPreferredWidth(0);
		tc.setWidth(0);
		tc.setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(column).setMinWidth(0);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShowTicketView dialog = new ShowTicketView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShowTicketView() {
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(29, 92, 720, 115);
			getContentPane().add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(29, 310, 720, 141);
			getContentPane().add(scrollPane);
			{
				table_1 = new JTable();
				scrollPane.setViewportView(table_1);
			}
		}
		{
			JButton button = new JButton("\u9000\u7968");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//��Ʊ��ť�¼�
					
					//1.�õ�Ʊ��ID
					int rowIndex = table.getSelectedRow();
					if (rowIndex > -1) {
						String ticketId = (String) table.getValueAt(rowIndex, 0);
						//1.1����������ticketId -> sessionId -> ����������һ
						TicketService ts = new TicketServiceImpl();
						String sessionid = ts.findSessionidByTicketid(ticketId);
						SessionService ss = new SessionServiceImpl();
						int k = ss.updateSeatRemainBySessionId(sessionid, -1); //-1����������һ
						//1.3.c�õ���Ӱ����
						Map<String, Object> sessionInformation = ss.findSessionInformationBysessionId(sessionid);
						Integer price = Integer.parseInt(sessionInformation.get("PRICE").toString());
						System.out.println("price="+price);
						//1.2������λδ��ѡ�� -> ����ticket���statusΪ0���鿴��λʱ��������status=1
						int i = ts.setTicketStatus0(ticketId);
						
						//1.3 �����û����
						//a.�û�ID
						Integer userId = ts.findUserIdByTicketId(ticketId);
						System.out.println(userId);
						CinemaUsersService cs = new CinemaUsersServiceImpl();
						//b.�û����
						Double balance = new Double(cs.findUserById(userId).get(0).get("BALANCE").toString());
						
						CinemaUsers cu = new CinemaUsers();
						cu.setUserid(userId); //��λ�����˺�����
						balance += price; 
						System.out.println("������"+balance);
						cu.setBalance(balance); //�������
						//d.ִ���û������²���
						cs.updateUserInfoById(cu);
						
						//�ɹ�
						String message = i>0?"�ɹ���Ʊ��":"��Ʊʧ������ϵϵͳ����Ա��";
						JOptionPane.showMessageDialog(null, message);
						initTable();
						initTable_1();
					} else {
						JOptionPane.showMessageDialog(null, "��ѡ��δ����Ʊ�����У�");
					}
				}
			});
			button.setBounds(583, 237, 123, 29);
			getContentPane().add(button);
		}
		{
			textField = new JTextField();
			textField.setText("\u672A\u51FA\u884C\u8BA2\u5355\uFF1A");
			textField.setBounds(29, 50, 123, 27);
			getContentPane().add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setText("\u5386\u53F2\u8BA2\u5355\uFF1A");
			textField_1.setBounds(29, 268, 96, 27);
			getContentPane().add(textField_1);
			textField_1.setColumns(10);
		}
		
		initTable();
		initTable_1();
	}

}
