package pl.psnc.scape.dicom.utils.fileUtils;

import java.io.File;

public interface IFileFinder {
	public boolean exists(String filePath);
	
	public boolean exists(File file);
	
	public File findFile(String fileName);
	
	public File findFile(String name, String extension);
	
	public File findFile(String name, String[] extensions);
}
