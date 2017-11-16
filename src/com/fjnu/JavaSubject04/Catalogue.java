package com.fjnu.JavaSubject04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Scanner;

public class Catalogue {
	
	public static void main(String[] args) throws IOException {
		String Path = "src\\com\\fjnu\\JavaSubject04";
		File directoryinfo = new File("src\\com\\fjnu\\JavaSubject04\\directoryinfo.txt");
		if (!directoryinfo.exists()) {
			directoryinfo.createNewFile();
		}
		Writer info = new FileWriter(directoryinfo);
		File afile = new File(Path);
		String[] list;
		list = afile.list();
		String[] temps = new String[list.length];
		int j = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i].lastIndexOf('.') == -1) {
				File file = new File(Path + "\\" + list[i]);
				Long lastModified = file.lastModified();
				Date date = new Date(lastModified);
				info.write("directoryname:" + list[i] + "  lastchangedate:" + date + "\r\n");
			} else {
				temps[j++] = list[i];
			}
		}
		for (int k = 0; k < j; k++) {
			File file = new File(Path + "\\" + temps[k]);
			Long lastModified = file.lastModified();
			Date date = new Date(lastModified);
			info.write("file:" + temps[k] + "  lastchangedate:" + date + "  filesize:" + file.length() + "\r\n");
		}
		info.close();

	}
}
