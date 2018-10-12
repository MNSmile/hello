package com.a.view.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.a.pojo.CinemaUsers;
import com.a.service.CinemaUsersService;
import com.a.service.Impl.CinemaUsersServiceImpl;
import com.a.util.RegexUtil;

public class AddUserView extends JInternalFrame {
	private JTextField txtUserName;
	private JTextField txtAccount;
	private JTextField txtBalance;
	private JTextField txtPwd;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUserView frame = new AddUserView();
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
	public AddUserView() {
		setTitle("\u6DFB\u52A0\u7528\u6237");
		setBounds(100, 60, 305, 411);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(43, 56, 81, 21);
		getContentPane().add(label);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(109, 53, 150, 27);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setBounds(43, 117, 81, 21);
		getContentPane().add(label_1);
		
		txtAccount = new JTextField();
		txtAccount.setColumns(10);
		txtAccount.setBounds(109, 114, 150, 27);
		getContentPane().add(txtAccount);
		
		JLabel label_4 = new JLabel("\u4F59\u989D\uFF1A");
		label_4.setBounds(43, 242, 81, 21);
		getContentPane().add(label_4);
		
		txtBalance = new JTextField();
		txtBalance.setColumns(10);
		txtBalance.setBounds(109, 239, 150, 27);
		getContentPane().add(txtBalance);
		
		JLabel label_3 = new JLabel("\u5BC6\u7801\uFF1A");
		label_3.setBounds(43, 181, 81, 21);
		getContentPane().add(label_3);
		
		txtPwd = new JTextField();
		txtPwd.setColumns(10);
		txtPwd.setBounds(109, 178, 150, 27);
		getContentPane().add(txtPwd);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				//����û���ť�¼�
				
				//1.�ռ��û���ӵ�����
				String userName = txtUserName.getText();
				String account = txtAccount.getText();
				String balance = txtBalance.getText();
				String passwd = txtPwd.getText();
				if (!"".equals(userName) && !"".equals(account) &&!"".equals(balance) && !"".equals(passwd)) {
					if (RegexUtil.accountRegex(account) == 1) {
						if (RegexUtil.balanceRegex(balance) == 1) {
							if (RegexUtil.pwdRegex(passwd) == 1) {
								//2.��װ��JavaBean
								CinemaUsers cu = new CinemaUsers();
								cu.setName(userName);
								cu.setUaccount(account);
								cu.setBalance(new Double(balance));
								cu.setPasswd(passwd);
								cu.setLevels("1");
								cu.setStates("0");
								cu.setStatus("1");
								//3.ת��service��
								CinemaUsersService cus = new CinemaUsersServiceImpl();
								int i = cus.registUser(cu);
								String message = i>0?"��ӳɹ���":"���ʧ������ϵϵͳ����Ա��";
								JOptionPane.showMessageDialog(null, message);
							} else {
								JOptionPane.showMessageDialog(null, "����ֻ������ĸ���������ַ�\r\n  �ҳ��ȱ�����������ַ�");
							}
						} else {
							JOptionPane.showMessageDialog(null, "���ֻ�������֣���ֻ����һλС��");
						}
					} else {
						JOptionPane.showMessageDialog(null, "�˺�ֻ������ĸ���������ַ�\r\n�ҳ��Ƚ���3��8֮�䣡");
					}
				} else {
					JOptionPane.showMessageDialog(null, "�û���Ϣ��������");
				}
			}
		});
		button.setBounds(136, 314, 123, 29);
		getContentPane().add(button);

	}
}
