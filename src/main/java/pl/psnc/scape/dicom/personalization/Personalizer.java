package pl.psnc.scape.dicom.personalization;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.VR;
import org.dcm4che2.data.VRMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.psnc.scape.dicom.io.reader.DicomReader;
import pl.psnc.scape.dicom.io.writer.DicomWriter;
import pl.psnc.scape.dicom.tag.ITagArchivizer;
import pl.psnc.scape.dicom.utils.fileUtils.IFileFinder;

@Service("PersonalizerService")
public class Personalizer {
    static Logger logger = Logger.getLogger(Personalizer.class);
    
    @Autowired
    private ITagArchivizer tagArchivizer;
    
    @Autowired
	protected IFileFinder fileFinder;
    
    protected void personalizeDicom(String dicomFilePath, String tagsFilePath){
    	DicomObject dicomObject = new DicomReader().read(dicomFilePath);
   		HashMap<Integer, String> tags = tagArchivizer.load(tagsFilePath);
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
        new DicomWriter().write(dicomObject, dicomFilePath);
    }
}
