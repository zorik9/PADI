package pl.psnc.scape.dicom.tag;

import java.util.Iterator;

import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.ElementDictionary;

import pl.psnc.scape.dicom.io.reader.DicomReader;

public class TagDisplayer {
    public void showTag(String dicomFilePath, int tag) {
    	DicomObject dicomObject = new DicomReader().read(dicomFilePath);
        if(dicomObject != null){
	        DicomElement dicomElement = dicomObject.get(tag);
	        if(dicomElement.length() >= 0){
	        	String tagValue = dicomObject.getString(tag);
		    	String tagName = ElementDictionary.getDictionary().nameOf(tag).replace(" ", "");
		    	System.out.println(tagName +  "=" + tagValue);
	        }
        }
    }

    public void printAllTags(String dicomFilePath) {
    	DicomObject dicomObject = new DicomReader().read(dicomFilePath);
        if(dicomObject != null){
        	Iterator<DicomElement> i = dicomObject.iterator();
        	DicomElement de; 
			while((de = i.next()) != null){
		    	System.out.println(de);
			}
        }
    }
    
    public String getAccessionNumberTag(String dicomFilePath) {
    	DicomObject dicomObject = new DicomReader().read(dicomFilePath);
        if(dicomObject != null){
        	Iterator<DicomElement> i = dicomObject.iterator();
        	DicomElement de; 
			while((de = i.next()) != null){
				if(de.toString().contains("0008,0050")){
		        	System.out.println(de.toString());
					return de.toString();
				}
			}
        }
		return null;
    }
}
