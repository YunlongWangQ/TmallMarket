package personsal.common.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeploymentCheck extends JFrame implements ActionListener
{
	private JButton   openButton;
	private JButton   exportButton;
	private JTextArea textArea;

	public static void main(String[] args)
	{
		new DeploymentCheck();
	}
	public DeploymentCheck()
	{
		super("待部署文件校验");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		textArea = new JTextArea(10, 30);
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
		openButton = new JButton("Start Deployment Check");
		openButton.setBounds(20, 20, 100, 50);
		openButton.addActionListener(this);
		// exportButton = new JButton("Export Error Message");
		// exportButton.setBounds(40,40,100,50);
		// exportButton.addActionListener(this);
		// add(exportButton, BorderLayout.SOUTH);
		add(openButton, BorderLayout.SOUTH);
		setLocation(400, 250);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == openButton)
		{
			textArea.setText(""); // 清空文本区
			File rootDir = new File("D:\\Users\\xywyunl\\Desktop\\DeploymentCheckSrcFolder");
			java.util.List<String> allFiles = listFiles(rootDir);
			for (String filePath : allFiles)
			{
				File file = new File(filePath);
				String mode = getFileSuffix(file);
				textArea.append("=====================" + file.getName() + "=====================" + "\n");
				// 文件名空格校验
				if (file.getName().contains(" "))
				{
					textArea.append(filePath + ":" + file.getName() + "file name is contais space" + "\n");
				}
				checkDeploymentFile(mode, file);
			}
			textArea.append("====the num of files is " + allFiles.size() + "====" + "\n");
			textArea.append("=====================End=====================" + "\n");
		}
		if(e.getSource() == exportButton){
			String content = textArea.getText();

			if (content == null || content.trim().isEmpty()) {
				textArea.append("There is no error message in this check and the program will terminate after 5 seconds.");
				exportButton.setEnabled(false);
				openButton.setEnabled(false);

				Timer timer = new Timer(5000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// 关闭窗口
						dispose();
					}
				});
				timer.start();

				return;
			}
			// 指定输出文件的路径
			String outputPath = "D:\\Users\\xywyunl\\Desktop\\DeployementErrorMessageLog.txt";

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
				// 写入文本内容
				writer.write(content);
			} catch (IOException ex)
			{
				throw new RuntimeException(ex);
			}
		}
	}

	private void checkDeploymentFile(String mode, File file)
	{
		Integer rownum = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			String line;
			if (mode.equals("pck"))
			{
				checkPckHeader(file);
				while ((line = reader.readLine()) != null)
				{
					rownum += 1;
					if (line.length() > 0 && checkAmpersands(line))
					{
						textArea.append(file.getName() + ":" + "[" + rownum + "]:" + line + "\n");
					}
				}
			} else if (mode.equals("sql"))
			{
				CheckSqlHeader(file);
			}
		} catch (IOException ex)
		{
			JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 功能：判断sql脚本的头是否
	 *
	 * @param file
	 * @author 王云龙
	 * @date 2024-08-07 17:17
	 **/
	private void CheckSqlHeader(File file) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String filename = file.getName();
		Integer flag = 0;
		String line;
		Integer rownum = 0;
		String sqltype = "";
		while ((line = reader.readLine()) != null)
		{
			rownum += 1;
			line = line.replaceAll("\\s+", "").toUpperCase();
			if (flag == 1)
			{
				if (!line.contains("*/"))
				{
					continue;
				} else
				{
					if (line.indexOf("*/") == line.length() - 2)
					{
						continue;
					}
					flag = 0;
				}
			}
			if (line.contains("CREATEORREPLACEVIEW"))
			{
				sqltype = "view";
			}
			// 视图空行检测
			if (sqltype == "view" && line.length() == 0)
			{
				textArea.append(file.getName() + " is a view and there is a empty line at line" + "[" + rownum + "]" + "\n");
			}
			// 判断当前行是否有多行注释开始的标志
			if (line.contains("/*"))
			{
				// 改变校验标记的值
				flag = 1;
				// 若当前行是以多行注释开始的标志为起始，则跳过本行的校验
				if (line.indexOf("/*") != 0)
				{
					continue;
				}
			}
			// 注释行不能有换行校验
			if (line.indexOf("COMMENTONCOLUMN") == 0 && !line.contains(";"))
			{
				textArea.append(file.getName() + "contains comment line which is not a single line:" + "[" + rownum + "]:" + line + "\n");
			}
			// Create语句必须有属主， 用【.】来作为判断依据
			if (line.indexOf("--") == -1 && line.contains("CREATE") && !line.contains(".") && !filename.substring(0,5).equals("BPEL_"))
			{
				int i = line.indexOf("CREATE");
				if (Character.isWhitespace(line.charAt(i + 7)))
				{
					textArea.append(file.getName() + " may has no owner:" + "[" + rownum + "]:" + line + "\n");
				}
			}
		}
	}

	/**
	 * 获取文件的后缀名。
	 *
	 * @param file 文件对象
	 * @return 文件的后缀名，如果没有后缀则返回空字符串
	 */
	private static String getFileSuffix(File file)
	{
		if (file == null)
		{
			return "";
		}
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex > 0 && dotIndex < fileName.length() - 1)
		{
			return fileName.substring(dotIndex + 1);
		}
		return "";
	}

	/**
	 * 功能：检查pck文件头是否含有apps
	 *
	 * @param file
	 * @author 王云龙
	 * @date 2024-08-07 16:22
	 **/
	public void checkPckHeader(File file) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		String errmessage = "headbodynoapps!";
		while ((line = reader.readLine()) != null)
		{
			line = line.replaceAll("\\s+", "");
			line = line.toUpperCase();
			if (line.contains("CREATEORREPLACEPACKAGEAPPS."))
			{
				errmessage = errmessage.replace("head", "");
			}
			if (line.contains("CREATEORREPLACEPACKAGEBODYAPPS."))
			{
				errmessage = errmessage.replace("body", "");
			}
		}
		if (errmessage.length() > 7)
		{
			textArea.append(file.getName() + "程序包不符合规范:" + errmessage + "\n");
		}
	}



	/**
	 * 功能：
	 * 1、判断字符串中是否含有&字符，且&字符后直接跟别的字符
	 * 2、判断字符串是否含有*@，且*@后直接跟字符
	 *
	 * @param str 待检测字符串
	 * @author 王云龙
	 * @date 2024-08-05 18:02
	 **/
	private static boolean checkAmpersands(String str)
	{
		if (str == null || str.isEmpty())
		{
			return false;
		}
		// 构建正则表达式
		String regexAnd = "&[a-zA-Z]";
		String regexAt = "&[a-zA-Z]";
		// 创建Pattern对象
		Pattern patternAnd = Pattern.compile(regexAnd);
		Pattern patternAt = Pattern.compile(regexAt);
		// 创建Matcher对象
		Matcher matcherAnd = patternAnd.matcher(str);
		Matcher matcherAt = patternAnd.matcher(str);
		// 判断是否匹配
		return (matcherAnd.find() || matcherAt.find());
	}

	/**
	 * 递归遍历文件夹及其子文件夹，收集所有文件的路径。
	 *
	 * @param directory 文件夹路径
	 * @return 所有文件的路径列表
	 */
	public static java.util.List<String> listFiles(File directory)
	{
		java.util.List<String> files = new ArrayList<>();
		File[] entries = directory.listFiles();
		if (entries != null)
		{
			for (File entry : entries)
			{
				if (entry.isDirectory())
				{
					files.addAll(listFiles(entry)); // 递归调用
				} else
				{
					files.add(entry.getAbsolutePath()); // 添加文件的绝对路径
				}
			}
		}
		return files;
	}
}
