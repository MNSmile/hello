package com.a.view.regist;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.a.pojo.CinemaUsers;
import com.a.service.CinemaUsersService;
import com.a.service.Impl.CinemaUsersServiceImpl;
import com.a.util.RegexUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class RegistView extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtAccount;
	private JTextField txtPwd;
	private JTextField txtPwd2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistView frame = new RegistView();
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
	public RegistView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(30, 37, 81, 21);
		contentPane.add(label);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(108, 34, 138, 27);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7\u540D\uFF1A");
		label_1.setBounds(30, 91, 81, 21);
		contentPane.add(label_1);
		
		txtAccount = new JTextField();
		txtAccount.setBounds(108, 88, 138, 27);
		contentPane.add(txtAccount);
		txtAccount.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setBounds(30, 152, 81, 21);
		contentPane.add(label_2);
		
		txtPwd = new JTextField();
		txtPwd.setBounds(108, 149, 138, 27);
		contentPane.add(txtPwd);
		txtPwd.setColumns(10);
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_3.setBounds(30, 217, 109, 21);
		contentPane.add(label_3);
		
		txtPwd2 = new JTextField();
		txtPwd2.setBounds(108, 214, 138, 27);
		contentPane.add(txtPwd2);
		txtPwd2.setColumns(10);
		
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ע�ᰴť�¼�
				
				//1.�õ���Ϣ
				String userName = txtUserName.getText();
				String account = txtAccount.getText();
				String passwd = txtPwd.getText();
				String passwd2 = txtPwd2.getText();
				
				if (!"".equals(userName) && !"".equals(account) && !"".equals(passwd)) {
					if (RegexUtil.accountRegex(account) == 1) { //У���˺�
						if (RegexUtil.pwdRegex(passwd) == 1) {
							//1.1����Ƿ��������� -> account�ظ�
							CinemaUsersService cus = new CinemaUsersServiceImpl();
							List<Map<String,Object>> findUserByAccount = cus.findUserByAccount(account);
							if (!findUserByAccount.isEmpty() && findUserByAccount != null) {
								JOptionPane.showMessageDialog(null, "�˺�������");
							} else {
								//1.2������������Ƿ���ͬ
								if (!txtPwd2.getText().equals(txtPwd.getText())) {
									JOptionPane.showMessageDialog(null, "�������벻ͬ��");
								} else {
									//2.����Ϣ��װ��JavaBean
									CinemaUsers cinemausers = new CinemaUsers();
									cinemausers.setName(userName);
									cinemausers.setPasswd(passwd);
									cinemausers.setBalance(new Double(0)); //Ϊʲô��ֱ��ǿת������balanceΪnull
									cinemausers.setUaccount(account);
									cinemausers.setLevels("1");
									cinemausers.setStates("0");
									cinemausers.setStatus("1");
									
									//3.ת��service��
									CinemaUsersService cs = new CinemaUsersServiceImpl();
									int pk = cs.registUser(cinemausers);
									
									//4.����service���ؽ�������û���ʾ
									String message = pk>0?"����ɹ���":"����ʧ������ϵϵͳ����Ա��";
									JOptionPane.showMessageDialog(null, message);
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "�����������ĸ��ͷ��ֻ����Ӣ����ĸ�����ֻ������ַ���\r\n  ���ܰ����ո�\r\n  �ұ������3���ַ����ȣ�");
						}
					} else {
						JOptionPane.showMessageDialog(null, "�˺�ֻ����Ӣ����ĸ�����ֻ������ַ���\r\n  �ұ���Ϊ3-8���ַ����ȣ�\r\n  �����ǿո�Ҳ���ܰ����ո�");
					}
				} else {
					JOptionPane.showMessageDialog(null, "�û���Ϣ��������");
				}
			}
		});
		button.setBounds(87, 286, 123, 29);
		contentPane.add(button);
	}
}
