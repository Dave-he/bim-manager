package com.mjwise.demo.system.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;

/**
 * Created by liyubo on 2017/5/15.
 */
public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 清空文件夹
	 * @param folderPath
	 */
	public static void delFolder(String folderPath) throws Exception{

		delAllFile(folderPath); // 删除完里面所有内容

		String filePath = folderPath;

		filePath = filePath.toString();

		File myFilePath = new File(filePath);

		myFilePath.delete(); // 删除空文件夹

	}


	/**
	 * 删除路径及文件夹
	 * @param path
	 * @return
	 */
	public static boolean delAllFile(String path) throws Exception{
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}


	/**
	 * * * 复制单个文件
	* @param oldPath String 原文件路径 如：c:/fqf.txt
	* @param newPath String 复制后路径 如：f:/fqf.txt
	*/
	public static void copyFile(String oldPath, String newPath) throws Exception{
		int bytesum = 0;
		int byteread = 0;
		File oldfile = new File(oldPath);
		if (oldfile.exists()) { //文件存在时
			InputStream inStream = new FileInputStream(oldPath); //读入原文件
			FileOutputStream fs = new FileOutputStream(newPath);
			byte[] buffer = new byte[1444];
			int length;
			while ( (byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread; //字节数 文件大小
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
		}
	}

	/**
	 * 复制整个文件夹内容
	 * @param oldPath String 原文件路径 如：c:/fqf
	 * @param newPath String 复制后路径 如：f:/fqf/ff
	 */
	public static void copyFolder(String oldPath, String newPath) throws Exception{


		(new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
		File a=new File(oldPath);
		String[] file=a.list();
		File temp=null;
		if(file!=null){
			for (int i = 0; i < file.length; i++) {
				if(oldPath.endsWith(File.separator)){
					temp=new File(oldPath+file[i]);
				}
				else{
					temp=new File(oldPath+File.separator+file[i]);
				}

				if(temp.isFile()){
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" +
							(temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ( (len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if(temp.isDirectory()){//如果是子文件夹
					copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]);
				}
			}
		}
	}


	/**
	 * 根据路径创建文件目录
	 * @param destDirName
	 * @return
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			logger.error("创建目录" + destDirName + "失败，目标目录已经存在");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		//创建目录
		if (dir.mkdirs()) {
			logger.info("创建目录" + destDirName + "成功！");

			return true;
		} else {
			logger.error("创建目录" + destDirName + "失败！");

			return false;
		}
	}

	/**
	 * 根据文件字节大小，获取文件大小
	 * @param size
	 * @return
	 */
	public static String getFileSize(long size){
		String result = "";
		DecimalFormat df = new DecimalFormat("#.00");
		if(size<1024*1024){
			result = df.format((double) size/1024)+ "KB";
		}else if (size<1024*1024*1024){
			result = df.format((double) size/1024/1024)+ "MB";
		}else if(size<1024*1024*1024*1024){
			result = df.format((double) size/1024/1024/1024)+ "GB";
		}else{
			result = df.format((double) size/1024/1024/1024/1024)+ "TB";
		}
		return result;
	}


}
