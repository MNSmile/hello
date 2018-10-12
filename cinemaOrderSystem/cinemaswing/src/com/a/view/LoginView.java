package com.a.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.a.service.CinemaUsersService;
import com.a.service.Impl.CinemaUsersServiceImpl;
import com.a.util.IdSave;
import com.a.util.RegexUtil;
import com.a.view.admin.AdminManagerView;
import com.a.view.regist.RegistView;
import com.a.view.users.UserAppView;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txtAccount;
	private JPasswordField txtPwd;
	private JRadioButton radio1 ;
	private JRadioButton radio2 ;
	//��ť��
	private ButtonGroup bg = new ButtonGroup();
	
	public void closeSelf() {
		this.setVisible(false); //�ô��ڲ���ʾ
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 250, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8D26\u53F7\uFF1A");
		label.setBounds(61, 43, 81, 21);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(61, 82, 81, 21);
		contentPane.add(label_1);
		
		txtAccount = new JTextField();
		txtAccount.setBounds(125, 40, 199, 27);
		contentPane.add(txtAccount);
		txtAccount.setColumns(10);
		
		txtPwd = new JPasswordField();
		txtPwd.setBounds(125, 79, 199, 27);
		contentPane.add(txtPwd);
		
		JButton btnLogin = new JButton("\u767B\u5F55");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//��¼��ť�ĵ���¼�
				
				//�����¼ҵ��
				//1.��ȡ��¼����Ϣ
				String uaccount = txtAccount.getText();
				String pwd = txtPwd.getText();
				String states = radio1.isSelected()?"0":"1";
				//2.����Ϣ���ݸ�service
				CinemaUsersService cs = new CinemaUsersServiceImpl();
				Map<String, Object> userInfoMap = cs.Login(uaccount, pwd, states);
				//3.��ȡservice���ؽ�����ж��Ƿ��¼�ɹ�
				if (userInfoMap == null) {
					JOptionPane.showMessageDialog(null, "�˺��������");
					//ע����Ϣ�Ժ�д
				} else {
					if ("1".equals(states)) { //����Ա
						//�򿪹���Ա����
						AdminManagerView window = new AdminManagerView();
						//��window��Ĵ�����ʾ
						window.frame.setVisible(true);
						//�رյ�¼����
						closeSelf();
						
					} else { //��ͨ�û�
						UserAppView window = new UserAppView();
						window.frame.setVisible(true);
						
						IdSave.userId = new Integer(userInfoMap.get("USERID").toString());
						closeSelf();
					}
				}
			}
		});
		btnLogin.setBounds(270, 200, 123, 29);
		contentPane.add(btnLogin);
		
		radio1 = new JRadioButton("\u7528\u6237");
		radio1.setBounds(125, 145, 74, 29);
		contentPane.add(radio1);
		
		radio2 = new JRadioButton("\u7BA1\u7406\u5458");
		radio2.setSelected(true);
		radio2.setBounds(240, 145, 177, 29);
		contentPane.add(radio2);
		
		//���û��͹���Ա����һ�飬ʹ�õ�¼������ֻ��ѡ���û��������Ա
		bg.add(radio1);
		bg.add(radio2);
		
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ע�ᰴť�¼�
				
				RegistView rv = new RegistView();
				rv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				rv.setVisible(true);
			}
		});
		button.setBounds(76, 200, 123, 29);
		contentPane.add(button);
	}
}












