package com.a.view.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.a.service.HallService;
import com.a.service.MovieService;
import com.a.service.SessionService;
import com.a.service.TicketService;
import com.a.service.Impl.HallServiceImpl;
import com.a.service.Impl.MovieServiceImpl;
import com.a.service.Impl.SessionServiceImpl;
import com.a.service.Impl.TicketServiceImpl;

public class DeleteHallView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Integer cinemaId;
	
	public void initTable() {
		//��table��ʾ����
		
		//����������vector��
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("���");
		columnNames.add("����");
		columnNames.add("����");
		
		//�����ݷ�װ����һ��vector��
		HallService hs = new HallServiceImpl();
		Vector<Vector<String>> data = hs.findAllHallByCinemaId(cinemaId);
		
		//���к����ݷŵ�model��
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		
		//��model��ӵ�table��
		table.setModel(dm);
	}
	
	/*public int compareTime(String time) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println(sdf.format(date));
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Date bt;
		try {
			bt = sdf1.parse(sdf.format(date));
			Date et=sdf1.parse(time); 
			if (bt.before(et)){ 
			   
				System.out.println("����ɾ��Ӱ����");
				return 1;
			}else{ 
				System.out.println("����ɾ��Ӱ��");
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		} 
	}*/
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteHallView frame = new DeleteHallView(1);
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
	public DeleteHallView(Integer cinemaId) {
		setTitle("\u5220\u9664\u5F71\u5385");
		//�رմ��ڣ���Ӱ��������
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 31, 398, 188);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("\u5220\u9664");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ɾ��Ӱ����ť�¼�
				
				//1.�õ�Ҫɾ��Ӱ��ID
				int rowIndex = table.getSelectedRow();
				if (rowIndex > -1) {
					Integer hallId = new Integer(table.getValueAt(rowIndex, 0).toString());
					
					//2.�жϸ�Ӱ���Ƿ����Ѿ��źõ�Ƭ��û��ӳ
					SessionService ss = new SessionServiceImpl();
					Map<String, Object> data = ss.findLatestMovieSessionTime(hallId, cinemaId);
					
					//2.1����У��򵯳�����˵������ɾ��Ӱ�������Ѿ��ź�Ƭ�ĵ�Ӱ
					if (data != null && !data.isEmpty()) {
						//2.1�ȶ�ӰƬ��ӳ��������ʱ�䣬
						//2.1.1�õ����һ����Ӱ��ӳʱ��
						String lastTime = data.get("SESSIONTIME").toString();
						System.out.println("��ӳʱ�䣺"+lastTime);
						//2.1.2��ӰID---> ʱ��
						Integer movieid = new Integer(data.get("MOVIEID").toString());
						System.out.println("movieId:"+movieid);
						
						MovieService ms = new MovieServiceImpl();
						String duration = ms.findMovieDurationByMovieId(movieid);
						System.out.println("duration:"+duration);
						//ȡ������ʱ�䣺
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						String nativeTime = sdf.format(date);
						
						System.out.println(nativeTime);
						
						//i=0 ����lastTime+duration > nativeTime, ������δ��ӳ�ĵ�Ӱ
						int i = PlatoonView.comparaTime(lastTime, nativeTime, duration, 0);
						
						System.out.println(i);
						
						if (i == 1) {
							//2.1.1����Ѿ���ӳ��ɾ��Ӱ��
							HallService hs = new HallServiceImpl();
							int t = hs.deleteHallById(hallId);
							String message = t>0?"�ɹ�ɾ��Ӱ����":"ɾ��ʧ������ϵϵͳ����Ա��";
							JOptionPane.showMessageDialog(null, message);
						
						} else {
							//2.1.2����ɾ����ȡ������
							
							//���Ƿ����˶�Ʊ sid -> tickets
							TicketService ts = new TicketServiceImpl();
							Integer sessionid = new Integer(data.get("SESSIONSID").toString());
							
							List<Map<String, Object>> ticketsList = ts.findTicketsBySessionid(sessionid);
							
							if (ticketsList != null && !ticketsList.isEmpty()) {
								JOptionPane.showMessageDialog(null, "��Ӱ���»���δ��ӳ���ӰƬ��\r\n�������˹�Ʊ�ˣ�����ɾ��");
								
							} else {
								int j = JOptionPane.showConfirmDialog(null, "��Ӱ���»���δ��ӳ���ӰƬ��\r\n  ����û�˹�Ʊ��\r\n  �Ƿ����ɾ����");
								if (j == 0) {
									HallService hs = new HallServiceImpl();
									int t = hs.deleteHallById(hallId);
									String message = t>0?"�ɹ�ɾ��Ӱ����":"ɾ��ʧ������ϵϵͳ����Ա��";
									JOptionPane.showMessageDialog(null, message);
								}
							}
						}
					} else {
						//2.2���û�У���ֱ��ִ��ɾ��Ӱ����������״̬��Ϊ0
						HallService hs = new HallServiceImpl();
						int t = hs.deleteHallById(hallId);
						String message = t>0?"�ɹ�ɾ��Ӱ����":"ɾ��ʧ������ϵϵͳ����Ա��";
						JOptionPane.showMessageDialog(null, message);
					}
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ��Ӱ�������У�");
				}
			}
		});
		button.setBounds(146, 279, 123, 29);
		contentPane.add(button);
		
		this.cinemaId = cinemaId;
		initTable();
	}

}
