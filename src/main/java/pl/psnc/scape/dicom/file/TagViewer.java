package pl.psnc.scape.dicom.file;

import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;



public abstract class TagViewer {
    
    public static String getTag(String filePath, int tagNumber){
    	DicomObject dicomObject = DicomReader.read(filePath);
        if(dicomObject != null){
	        DicomElement dicomElement = dicomObject.get(tagNumber);
	        if((dicomElement != null) && (dicomElement.length() >= 0)){
	        	String tagValue = dicomObject.getString(tagNumber);
	        	return tagValue;
	        }
        }
        return null;
    }
}
