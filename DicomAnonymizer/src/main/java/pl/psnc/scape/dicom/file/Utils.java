package pl.psnc.scape.dicom.file;

import java.io.File;

public abstract class Utils {
	public static boolean exist(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			return true;
		}
		else {
			return false;
		}
	}
	
	public static String getExtension (String filePath){
		String extension = "";

		int i = filePath.lastIndexOf('.');
		if (i > 0) {
		    extension = filePath.substring(i+1);
		}
		return extension;
	}
}
