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

public class MyClien extends JFrame { // 创建类继承JFrame类
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedReader reader; // 创建BufferedReader对象
	private PrintWriter writer; // 声明PrintWriter类对象
	Socket socket; // 声明Socket对象
	private JTextPane ta = new JTextPane(); // 创建JtextArea对象
	private JTextField tf = new JTextField(); // 创建JtextField对象
	Container cc; // 声明Container对象

	public MyClien(String title) { // 构造方法
		super(title); // 调用父类的构造方法
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cc = this.getContentPane(); // 实例化对象

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(ta);
		cc.add(tf, "South"); // 将文本框放在窗体的下部

		Style def = ta.getStyledDocument().addStyle(null, null);
		StyleConstants.setFontFamily(def, "verdana");
		StyleConstants.setFontSize(def, 12);
		Style normal = ta.addStyle("normal", def);
		Style s = ta.addStyle("red", normal);
		StyleConstants.setForeground(s, Color.RED);
		ta.setParagraphAttributes(normal, true);

		tf.addActionListener(new ActionListener() {
			// 绑定事件
			public void actionPerformed(ActionEvent e) {
				// 将文本框中信息写入流
				writer.println(tf.getText());
				// 将文本框中信息显示在文本域中
				// ta.setForeground(Color.red);
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				try {
					ta.getDocument().insertString(ta.getDocument().getLength(),
							dateFormat.format(now) + "\n客户机:" + tf.getText() + '\n', ta.getStyle("normal"));
				} catch (BadLocationException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				ta.setSelectionEnd(ta.getText().length());
				tf.setText(""); // 将文本框清空
			}
		});
	}

	private void connect() throws BadLocationException { // 连接套接字方法
		// ta.append("尝试连接...\n"); // 文本域中提示信息
		ta.getDocument().insertString(ta.getDocument().getLength(), "尝试连接...\n", ta.getStyle("normal"));
		try { // 捕捉异常
			socket = new Socket("127.0.0.1", 8998); // 实例化Socket对象
			writer = new PrintWriter(socket.getOutputStream(), true);
			// ta.append("完成连接\n"); // 文本域中提示信息
			ta.getDocument().insertString(ta.getDocument().getLength(), "完成连接\n", ta.getStyle("normal"));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 实例化BufferedReader对象
			getTcpMessage(); // 调用getClientMessage()方法
		} catch (Exception e) {
			e.printStackTrace(); // 输出异常信息
		}
	}

	private void getTcpMessage() {
		// TODO 自动生成的方法存根
		try {
			while (true) { // 如果套接字是连接状态
				if (reader.ready()) {
					// 获得客户端信息
					ta.setForeground(Color.blue);
					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					ta.getDocument().insertString(ta.getDocument().getLength(),
							dateFormat.format(now) + "\n服务器:" + reader.readLine() + '\n', ta.getStyle("red"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // 输出异常信息
		}
		try {
			if (reader != null) {
				reader.close(); // 关闭流
			}
			if (socket != null) {
				socket.close(); // 关闭套接字
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) { // 主方法
		MyClien clien = new MyClien("客户端"); // 创建本例对象
		clien.setSize(400, 400); // 设置窗体大小
		clien.setVisible(true); // 将窗体显示
		try {
			clien.connect();
		} catch (BadLocationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} // 调用连接方法
	}
}
