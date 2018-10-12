package com.a.view.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.a.pojo.Session;
import com.a.service.HallService;
import com.a.service.MovieService;
import com.a.service.SessionService;
import com.a.service.Impl.HallServiceImpl;
import com.a.service.Impl.MovieServiceImpl;
import com.a.service.Impl.SessionServiceImpl;
import com.a.util.RegexUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlatoonView extends JInternalFrame {
	private JTable table;
	private JTable table_1;
	private JTextField txtSessionTime;
	private JTextField txtPrice;
	
	/**
	 *�Ƚ����ڴ�С������1��ʾbeforeTime����duration��thisTime֮ǰ��0��ʾ������ 
	 * @param beforeTime ֮ǰ��ʱ��
	 * @param thisTime 
	 * @param duration ʱ��
	 * @param cleaningTime ��ɨ����ʱ��
	 * @return
	 */
	public static int comparaTime(String beforeTime, String thisTime, String duration, int cleaningTime) {
		Calendar nowTime = Calendar.getInstance();// �õ���ǰʱ��
		String str  = beforeTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			nowTime.setTime(sdf.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		} // ���ó����ʱ��
		//����duration����
		nowTime.add(Calendar.MINUTE, cleaningTime+new Integer(duration)); 
		String b = thisTime;
		Date bt;
		try {
			bt = sdf.parse(sdf.format(nowTime.getTime()));
			Date et = sdf.parse(b);
			
			System.out.println("bt="+sdf.format(bt));
			System.out.println("et="+sdf.format(et));
			if (bt.before(et)) {
				return 1; //������Ƭ
			} else {
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void initMovieTable() {
		//��table��ʾ����
		
		//����������vector��
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("ӰƬ���");
		columnNames.add("ӰƬ����");
		columnNames.add("ӰƬʱ��");
		columnNames.add("ӰƬ����");
		//�����ݷ�װ����һ��vector��
		
		MovieService ms = new MovieServiceImpl();
		Vector<Vector<String>> data = ms.findAllMovies();
		
		//���к����ݷŵ�model��
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		
		//��model��ӵ�table��
		table.setModel(dm);
	}
	
	public void initHallTable() {
		//��table��ʾ����
		
		//����������vector��
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Ӱ�����");
		columnNames.add("Ӱ������");
		columnNames.add("Ӱ������");
		columnNames.add("ӰԺ���");
		columnNames.add("ӰԺ����");
		//�����ݷ�װ����һ��vector��
		
		HallService hs = new HallServiceImpl();
		Vector<Vector<String>> data = hs.findAllHall();
		
		//���к����ݷŵ�model��
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		
		//��model��ӵ�table��
		table_1.setModel(dm);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlatoonView frame = new PlatoonView();
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
	public PlatoonView() {
		setBounds(80, 40, 715, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 36, 315, 245);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(368, 36, 300, 245);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel label = new JLabel("\u4E0A\u6620\u65F6\u95F4:");
		label.setBounds(15, 296, 81, 21);
		getContentPane().add(label);
		
		txtSessionTime = new JTextField();
		txtSessionTime.setBounds(111, 293, 189, 27);
		getContentPane().add(txtSessionTime);
		txtSessionTime.setColumns(10);
		
		JButton button = new JButton("\u6392\u7247");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��Ƭ��ť�¼�
				
				//1.�õ���ӰID��ӰԺID��Ӱ��ID��������������
				//1.1������δѡ���У�����-1
				int rowIndex = table.getSelectedRow();
				if (rowIndex > -1) {
					Integer movieId = new Integer(table.getValueAt(rowIndex, 0).toString());
					int rowIndex1 = table_1.getSelectedRow();
					if (rowIndex1 > -1) {
						Integer hallId = new Integer(table_1.getValueAt(rowIndex1, 0).toString());
						Integer cinemaId = new Integer(table_1.getValueAt(rowIndex1, 3).toString());
						String remain = table_1.getValueAt(rowIndex1, 2).toString();
						
						//2.�õ�Ҫ��Ƭ��Ӱ��ӳʱ�䣬����
						String sessionTime = txtSessionTime.getText();
						String price = txtPrice.getText();
						
						//У��ʱ�䣬����
						if (RegexUtil.TimeRegex(sessionTime) == 1) {
							if (RegexUtil.balanceRegex(price) == 1) {
								if (!"".equals(sessionTime)) {
									if (!"".equals(price)) {
										//3.��������Ϣ��װ
										Session s = new Session();
										s.setCid(cinemaId);
										s.setHid(hallId);
										s.setMovieId(movieId);
										s.setPrice(price);
										s.setRemain(remain);
										s.setSessionTime(sessionTime);
										
										SessionService ss = new SessionServiceImpl();
										
										
										//4.�жϵ�ǰ�Ƿ������Ƭ
										//4.1�ӳ��α��в��������Ƭ��Ӱ�������һ����Ӱ��ʱ��b,����У���Ϊʱ�����ֵ��û����Ϊ0
										Map<String, Object> data = ss.findLatestMovieSessionTime(hallId, cinemaId);
										System.out.println(data);
										
										if (data!=null && !data.isEmpty() && data.get("MOVIEID") != null) {
											//4.2�õ�ӰƬʱ��
											Integer movieid = new Integer(data.get("MOVIEID").toString());
											MovieService ms = new MovieServiceImpl();
											String duration = ms.findMovieDurationByMovieId(movieid);
											//4.3���һ����Ӱ��ӳʱ��
											String movieTime = (String) data.get("SESSIONTIME");
											int t = comparaTime(movieTime, sessionTime, duration, 20);
											if (t > 0) {
												ss.addSession(s);
											}
											String s1 = "��Ƭʧ�ܣ�"+" ��һ����Ӱ��ӳʱ�䣺"+movieTime+" ʱ����"+duration+"����"+" ��ɨ����ʱ�䣺"+20+"����";
											String message = t>0?"��Ƭ�ɹ���":s1;
											JOptionPane.showMessageDialog(null, message);
										} else {
											int k = ss.addSession(s);
											String message = k>0?"��Ƭ�ɹ���":"����ʧ������ϵϵͳ����Ա��";
											JOptionPane.showMessageDialog(null, message);
										}
									} else {
										JOptionPane.showMessageDialog(null, "����д���ۣ�");
									}
								} else {
									JOptionPane.showMessageDialog(null, "����д��ӳʱ�䣡");
								}
							} else {
								JOptionPane.showMessageDialog(null, "����ֻ�������֣�С�����һλ");
							}
						} else {
							JOptionPane.showMessageDialog(null, "ʱ���ʽ 2018-09-09 10:10 \r\n ����2019-9-9 9:5");
						}
					} else {
						JOptionPane.showMessageDialog(null, "��ѡ��ӰԺ��");
					}
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ���Ӱ��");
				}
			}
		});
		button.setBounds(273, 346, 123, 29);
		getContentPane().add(button);
		
		JLabel label_1 = new JLabel("\u5355\u4EF7\uFF1A");
		label_1.setBounds(368, 296, 81, 21);
		getContentPane().add(label_1);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(434, 293, 234, 27);
		getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		initMovieTable();
		initHallTable();
	}

}
