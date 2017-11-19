## ����������IO ##

> �ô�����Ͳ���������ַ���ʵ���ļ����ƣ����ȽϺ�ʱ�����

 - Դ����

```
public static void IOchar() throws IOException{
		File inFile = new File("D:\\Workspaces\\Java\\bin\\com\\fjnu\\JavaSubject04\\src");
		File outFile = new File("D:\\Workspaces\\Java\\bin\\com\\fjnu\\JavaSubject04\\dest");
		Reader finS = new FileReader(inFile);
		Writer foutS = new FileWriter(outFile);
		int ch;
		while((ch = finS.read())!=-1) {
			foutS.write(ch);
		}
		foutS.close();
		finS.close();
	}
	
	public static void IOBufferedChar() throws IOException{
		File inFile = new File("D:\\Workspaces\\Java\\bin\\com\\fjnu\\JavaSubject04\\src");
		File outFile = new File("D:\\Workspaces\\Java\\bin\\com\\fjnu\\JavaSubject04\\dest");
		Reader finS = new FileReader(inFile);
		Writer foutS = new FileWriter(outFile);
		BufferedReader bfinS = new BufferedReader(finS);
		BufferedWriter bfoutS = new BufferedWriter(foutS);
		int ch;
		while((ch = bfinS.read())!=-1) {
			bfoutS.write(ch);
		}
		bfoutS.close();
		bfinS.close();
		foutS.close();
		finS.close();
	} 
```

 

 - �����ͼ


![](https://github.com/948243694/JavaLearn/blob/master/src/com/fjnu/JavaSubject04/BufferTest.png)


----------


## �������л� ##

> ��Student��ʾѧ�������԰���{studentID, name, sex} (ѧ�ţ��������Ա�)��ʹ�����л���������Student��ѧ����Ϣ���ļ�list.txt���룬������ѧ���������С�ע�⣬�����������Ա������֯��String���ͣ���ѧ�ſ�����StringҲ������Long��
ʹ��ObjectOutputStream���Ѿ������ѧ����Ϣд�����ļ���student.bin���С�
ʹ��ObjectInputStream����student.bin���еĶ�����Ϣ���������ʾ�ڿ���̨�С�


 - Դ����

```
private static void inSerializableStudent(){
		// TODO Auto-generated method stub
		File inFile = new File("Student.obj");
		FileInputStream finS = null;
		ObjectInputStream oin = null;

			try {
				finS = new FileInputStream(inFile);
				oin = new ObjectInputStream(finS);
				int cout = 0;
				while(true) {
					try {
						Student get = (Student)oin.readObject();
						System.out.print(++cout+":  ");
						System.out.println(get);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						break;
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}




	private static void readStudent() {
		// TODO Auto-generated method stub
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader("D:\\Workspaces\\Java\\bin\\com\\fjnu\\JavaSubject04\\list");
				br = new BufferedReader(fr);
				String line = "";
				String[] infos = null;
				while ((line = br.readLine()) != null) {
					Student temp = new Student();
					infos = line.split(" ");
					temp.setStudentID(infos[0]);
					temp.setName(infos[1]);
					temp.setSex(infos[2]);
					studentQueue.add(temp);		
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					br.close();
					fr.close();
					Collections.sort(studentQueue, new StudentComparator());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

	}




	private static void SerializableStudent() {
		// TODO Auto-generated method stub
		File outFile = new File("Student.obj");
		FileOutputStream foutS = null;
		ObjectOutputStream oout = null;
		try {
			foutS = new FileOutputStream(outFile);
			oout = new ObjectOutputStream(foutS);
			for(Student temp : studentQueue) {
				oout.writeObject(temp);	
				oout.flush();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
```

- �����ͼ


![](https://github.com/948243694/JavaLearn/blob/master/src/com/fjnu/JavaSubject04/Serializable.png)

