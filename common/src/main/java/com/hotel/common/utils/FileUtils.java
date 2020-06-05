package com.hotel.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * file相关工具类
 *
 * @author roderick
 */
public class FileUtils {
	// 多个文件名的分隔符
	public static final String FILE_NAME_SEPARATE = ",";
	
	/**
	 * 创建临时文件
	 * 
	 * @param name 文件名
	 * @param ext 扩展名
	 * @return
	 * @throws IOException
	 */
	public static File createTmpFile(InputStream inputStream, String name, String ext) throws IOException {
		return createTmpFile(inputStream, name, ext, null);
	}
	
	/**
	 * @param tmpDirFile 临时文件目录
	 */
	public static File createTmpFile(InputStream inputStream, String name, String ext, File tmpDirFile) throws IOException {
		File tempFile;
		if (tmpDirFile == null) {
			tempFile = File.createTempFile(name, "." + ext);
		} else {
			tempFile = File.createTempFile(name, "." + ext, tmpDirFile);
		}
		
		tempFile.deleteOnExit();
		
		try(FileOutputStream fos = new FileOutputStream(tempFile)) {
			int read = 0;
			byte[] bytes = new byte[1024 * 100];
			while ((read = inputStream.read(bytes)) != -1) {
				fos.write(bytes, 0, read);
			}
			
			fos.flush();
			return tempFile;
		}
	}
	
	/**
	 * @param tmpDirFile 临时文件目录
	 */
	public static File createTmpFile(byte[] bytes, String name, String ext) throws IOException {
		File tempFile = File.createTempFile(name, "." + ext);
		
		tempFile.deleteOnExit();
		
		try(FileOutputStream fos = new FileOutputStream(tempFile)) {
			fos.write(bytes);
			fos.flush();
			return tempFile;
		}
	}
	/**
	 * 输入流写入现存文件
	 * @author ZERO
	 * @param is 输入流
	 * @param file
	 */
	public static void writeFile(InputStream is,File file){
	    FileOutputStream fos = null;  
	    try {  
	        fos = new FileOutputStream(file.getName());  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	    int ch = 0;  
	    try {  
	        while((ch=is.read()) != -1){  
	            fos.write(ch);  
	        }  
	        fos.close();  
	        is.close();  
	    } catch (IOException e1) {  
	        e1.printStackTrace();  
	    } 
	}

	public static void saveFile(MultipartFile mFile, File file) {
		try {
//			  String fileName = mFile.getOriginalFilename();
			
              byte[] bytes = mFile.getBytes();
              BufferedOutputStream buffStream = 
                      new BufferedOutputStream(new FileOutputStream(file));
              buffStream.write(bytes);
              buffStream.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
