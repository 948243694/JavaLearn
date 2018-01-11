package com.fjnu.JavaSubject06;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class MyClien extends JFrame { // ������̳�JFrame��
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedReader reader; // ����BufferedReader����
	private PrintWriter writer; // ����PrintWriter�����
	Socket socket; // ����Socket����
	private JTextPane ta = new JTextPane(); // ����JtextArea����
	private JTextField tf = new JTextField(); // ����JtextField����
	Container cc; // ����Container����

	public MyClien(String title) { // ���췽��
		super(title); // ���ø���Ĺ��췽��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cc = this.getContentPane(); // ʵ��������

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(ta);
		cc.add(tf, "South"); // ���ı�����ڴ�����²�

		Style def = ta.getStyledDocument().addStyle(null, null);
		StyleConstants.setFontFamily(def, "verdana");
		StyleConstants.setFontSize(def, 12);
		Style normal = ta.addStyle("normal", def);
		Style s = ta.addStyle("red", normal);
		StyleConstants.setForeground(s, Color.RED);
		ta.setParagraphAttributes(normal, true);

		tf.addActionListener(new ActionListener() {
			// ���¼�
			public void actionPerformed(ActionEvent e) {
				// ���ı�������Ϣд����
				writer.println(tf.getText());
				// ���ı�������Ϣ��ʾ���ı�����
				// ta.setForeground(Color.red);
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				try {
					ta.getDocument().insertString(ta.getDocument().getLength(),
							dateFormat.format(now) + "\n�ͻ���:" + tf.getText() + '\n', ta.getStyle("normal"));
				} catch (BadLocationException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				ta.setSelectionEnd(ta.getText().length());
				tf.setText(""); // ���ı������
			}
		});
	}

	private void connect() throws BadLocationException { // �����׽��ַ���
		// ta.append("��������...\n"); // �ı�������ʾ��Ϣ
		ta.getDocument().insertString(ta.getDocument().getLength(), "��������...\n", ta.getStyle("normal"));
		try { // ��׽�쳣
			socket = new Socket("127.0.0.1", 8998); // ʵ����Socket����
			writer = new PrintWriter(socket.getOutputStream(), true);
			// ta.append("�������\n"); // �ı�������ʾ��Ϣ
			ta.getDocument().insertString(ta.getDocument().getLength(), "�������\n", ta.getStyle("normal"));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // ʵ����BufferedReader����
			getTcpMessage(); // ����getClientMessage()����
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
	}

	private void getTcpMessage() {
		// TODO �Զ����ɵķ������
		try {
			while (true) { // ����׽���������״̬
				if (reader.ready()) {
					// ��ÿͻ�����Ϣ
					ta.setForeground(Color.blue);
					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					ta.getDocument().insertString(ta.getDocument().getLength(),
							dateFormat.format(now) + "\n������:" + reader.readLine() + '\n', ta.getStyle("red"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
		try {
			if (reader != null) {
				reader.close(); // �ر���
			}
			if (socket != null) {
				socket.close(); // �ر��׽���
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) { // ������
		MyClien clien = new MyClien("�ͻ���"); // ������������
		clien.setSize(400, 400); // ���ô����С
		clien.setVisible(true); // ��������ʾ
		try {
			clien.connect();
		} catch (BadLocationException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} // �������ӷ���
	}
}
