package pl.psnc.scape.dicom.tag;

import java.util.HashMap;
import java.util.Map;

public interface ITagArchivizer {
	public void save(Map<String, String> properties, String dicomFilePath, String tagsFilePath);
	
	public HashMap<Integer, String> load(String filePath);
}
