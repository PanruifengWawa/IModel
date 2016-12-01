package com.imodel.utils;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;


public class FileUtils {
	public static boolean saveFile(String filePath,String newFileName,MultipartFile file) {
		File fileDir = new File(filePath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		try {
			FileOutputStream out = new FileOutputStream(filePath + newFileName);
		                // 写入文件
			out.write(file.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		    return false;
		}

		return true;
	}
}
