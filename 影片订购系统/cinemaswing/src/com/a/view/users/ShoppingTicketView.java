package com.a.view.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.a.service.CinemaUsersService;
import com.a.service.HallService;
import com.a.service.SessionService;
import com.a.service.TicketService;
import com.a.service.Impl.CinemaUsersServiceImpl;
import com.a.service.Impl.HallServiceImpl;
import com.a.service.Impl.SessionServiceImpl;
import com.a.service.Impl.TicketServiceImpl;
import com.a.util.IdSave;

public class ShoppingTicketView extends JDialog {
	private String sessionid;
	private JTextField txtCinemaName;
	private JTextField txtHallName;
	private JTextField txtMovieName;
	private JTextField txtSesstionTime;
	private String price;
	private String hallId;
	
	/**�û������Ʊ**/
	private Set ticketSet = new HashSet();
	
	private void initTxt() {
		SessionService ss = new SessionServiceImpl();
		Map<String, Object> movieMap = ss.findMovieInfoBySessionId(this.sessionid);
		
		//���ı���ֵ
		txtCinemaName.setText(movieMap.get("CINEMANAME").toString());
		txtHallName.setText(movieMap.get("HALLNAME").toString());
		txtMovieName.setText(movieMap.get("MOVIENAME").toString());
		txtSesstionTime.setText(movieMap.get("SESSIONTIME").toString());
		
		//����
		this.price = (String) movieMap.get("PRICE");
		//Ӱ��ID������ѡ��ʱ����Ӱ������
		this.hallId = (String) movieMap.get("HALLID");
	}
	
	private void initSeat() {
		//1.����ĳһӰԺĳһ��Ӱ�Ѿ���������λ����sessionID����
		SessionService ss = new SessionServiceImpl();
		Set<String> seatSet = ss.findSeatSetBySessionId(sessionid);
		int count = seatSet.size(); //��ѡ��λ����
		//System.out.println("��ѡ��λ����:"+count);
		
		//��ѯӰԺӰ������ sessionid-->hid-->capacity
		HallService hs = new HallServiceImpl();
		String Hallcapacity = hs.findHallCapacityById(hallId);
		
		int n = new Integer(Hallcapacity);
		//System.out.println("Ӱ��������"+n);
		int s = 1;
		for (int y = 0; y < n/5; y++) {
			for (int x = 0; x < 5; x++) {
				JCheckBox CheckBox = new JCheckBox(s + "");
				CheckBox.setBounds(11+x*100, 158+y*50, 45, 29);
				
				getContentPane().add(CheckBox);
				
				seatSet.add(s+"");
				if (seatSet.size() == count) {  //˵������λ�ű�ѡ��
					CheckBox.setSelected(true); //���ñ�ѡ����
					CheckBox.setEnabled(false); //���ò����ٴα�ѡ
				} else {
					seatSet.remove(s+""); //˵������λû�б�ѡ�����Ƚ����Ƴ�������Ӱ��ѡ��
				}
				
				CheckBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean flag = CheckBox.isSelected();
						if (flag) {
							ticketSet.add(CheckBox.getText());
						} else {
							ticketSet.remove(CheckBox.getText());
						}
						//System.out.println(CheckBox.isSelected()+"----"+CheckBox.getText());
						//System.out.println(ticketSet.toString());
					}
				});
				s++;
			}
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShoppingTicketView dialog = new ShoppingTicketView("1");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShoppingTicketView(String sessionid) {
		setTitle("\u9009\u5EA7");
		setBounds(100, 100, 528, 579);
		
		this.sessionid = sessionid;

		getContentPane().setLayout(null);
		
		txtCinemaName = new JTextField();
		txtCinemaName.setBounds(15, 27, 96, 27);
		getContentPane().add(txtCinemaName);
		txtCinemaName.setColumns(10);
		
		txtHallName = new JTextField();
		txtHallName.setBounds(126, 27, 130, 27);
		getContentPane().add(txtHallName);
		txtHallName.setColumns(10);
		
		txtMovieName = new JTextField();
		txtMovieName.setBounds(15, 84, 96, 27);
		getContentPane().add(txtMovieName);
		txtMovieName.setColumns(10);
		
		txtSesstionTime = new JTextField();
		txtSesstionTime.setBounds(126, 84, 130, 27);
		getContentPane().add(txtSesstionTime);
		txtSesstionTime.setColumns(10);
		
		initTxt(); //�ı���ֵ,��ʼ��
		
		JButton button = new JButton("\u8D2D\u7968");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��Ʊ��ť�¼�
				
				//1.ȡ���û�ID��sessionID����λ��
				//IdSave.userId
				//System.out.println(ticketSet.toString());
				
				TicketService ts = new TicketServiceImpl();
				//2.�ж�����Ƿ�֧������Ʊ��*Ʊ����
				//2.1ȡ�����--�õ��û�ID��IdSave.userId;
				Integer userId = IdSave.userId;
				CinemaUsersService cus = new CinemaUsersServiceImpl();
				Double balance = new Double(cus.findUserById(userId).get(0).get("BALANCE").toString());
				//Double balance = cus.findBalanceById(userId);
				
				//2.2ȡ��Ʊ�ۺ���Ʊ����
				Double moviePrice = new Double(price);
				int count = ticketSet.size();
				//3.�ȶԼ�Ǯ�����
				//3.1������
				if (balance >= moviePrice*count) { 
					//3.2��Ʊ��������
					int i = ts.shoppingTicket(IdSave.userId, sessionid, ticketSet,new Double(price));
					
					String message = i>0?"��Ʊ�ɹ���":"��Ʊʧ������ϵϵͳ����Ա��";
					JOptionPane.showMessageDialog(null, message);
					
					//3.3��Ʊ�ɹ�����������  sessionid --> remain, ѡ����λ�� ticketSet.size();
					SessionService ss = new SessionServiceImpl();
					int seatUpdate = ss.updateSeatRemainBySessionId(sessionid, ticketSet.size());
					String message1 = i>0?"���������³ɹ���":"����������ʧ��!����ϵϵͳ����Ա!";
					JOptionPane.showMessageDialog(null, message1);
				} else { //����
					JOptionPane.showMessageDialog(null, "������ǰ��<��������><�����˺�>��ֵ!");
					
				}
			}
		});
		button.setBounds(335, 43, 123, 29);
		getContentPane().add(button);
		
		initSeat();
	}
}
