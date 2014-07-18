package pl.psnc.scape.dicom.personalization;

import java.io.File;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.VR;
import org.dcm4che2.data.VRMap;

import pl.psnc.scape.dicom.anonymization.Anonymizer;
import pl.psnc.scape.dicom.file.DicomReader;
import pl.psnc.scape.dicom.file.DicomWriter;
import pl.psnc.scape.dicom.file.TagsArchivizer;
import pl.psnc.scape.dicom.file.Utils;


public class Personalizer {
    static Logger logger = Logger.getLogger(Personalizer.class);
    public static void personalize(String dicomFilePath, String tagsFilePath) {
    	if(Utils.exist(dicomFilePath)){
    		if(Utils.exist(tagsFilePath)){
    			personalizeDicom(dicomFilePath, tagsFilePath);
    		}
        	else {
                logger.error("File not found: " + tagsFilePath);
        	}
    	}
    	else {
            logger.error("File not found: " + dicomFilePath);
    	}
    }

    public static void personalizeDir(String directoryPath) {
        File directory = new File(directoryPath);
        File[] fList = directory.listFiles();
		for (File file : fList) {
            if (file.isFile()) {
            	personalize(file.getAbsolutePath(), file.getAbsolutePath() + ".txt");
            }
            else if (file.isDirectory()) {
            	personalizeDir(file.getAbsolutePath());
            }
        }
    }
    
    private static void personalizeDicom(String dicomFilePath, String tagsFilePath){
    	DicomObject dicomObject = DicomReader.read(dicomFilePath);
   		HashMap<Integer, String> tags = TagsArchivizer.load(tagsFilePath);
        if(dicomObject != null){
        	System.out.println(dicomFilePath);
	        for (int tag : tags.keySet()) {
	            if (dicomObject.contains(tag)) {
	            	dicomObject.remove(tag);
	            }
            	VR vr = VRMap.getVRMap().vrOf(tag);
            	dicomObject.putString(tag, vr, tags.get(tag));
            	
	        }
        }
        DicomWriter.write(dicomObject, dicomFilePath);
    }
}
