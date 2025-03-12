package personsal.common.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class checklist extends JFrame implements ActionListener
{
	private JButton   openButton;

	private JTextArea textArea;
	public static void main(String[] args)
	{
		new checklist();
	}

	public checklist()
	{
		super("部署清单文件校验");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		textArea = new JTextArea(10, 30);
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
		openButton = new JButton("Start DeploymentList Check");
		openButton.setBounds(20,20,100,50);
		openButton.addActionListener(this);
		add(openButton, BorderLayout.SOUTH);
		setLocation(400, 250);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == openButton)
		{
			// 获取当前日期
			Date currentDate = new Date();

			// 定义日期格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			// 格式化日期
			String formattedDate = sdf.format(currentDate);

			textArea.setText(""); // 清空文本区
			String path = "D:\\Users\\xywyunl\\Downloads\\"+formattedDate+".txt";
			File file = new File(path);
			try
			{
				textArea.append("=========start check the deployment list========="+"\n");
				checkDeployementList(file);
			} catch (IOException ex)
			{
				throw new RuntimeException(ex);
			}
		}
	}

	private void checkDeployementList(File file) throws IOException
	{
		int rows = 0;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		Integer lineNum = 0;
		while((line = reader.readLine()) != null){
			lineNum++;
			int start = line.indexOf("http://");
			int end = line.indexOf("bug#");
			if(line.contains("http://10.76.16.6/svn/schain")){
				rows++;
				String url = line.substring(start, end);
				String desc = line.substring(end);
				int pointindex = url.lastIndexOf(".");
				url = url.substring(0,pointindex+4);
				if(url.contains(" ")){
					textArea.append("line["+lineNum + "]:" + "url has space"+"\n");
				}
				if(url.substring(pointindex+1).equals("fmb") && (!desc.contains("appl#") || !desc.contains("lang#")) ){
					textArea.append("line["+lineNum + "]:" + "the form file has no appl# or lang# desc!" +"\n");
				}
			}

		}
		textArea.append("there is total " + rows + "rows record."+"\n");
		textArea.append("=====================End=====================" + "\n");
	}

}
