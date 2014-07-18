package pl.psnc.scape.dicom.file;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.io.DicomInputStream;


public abstract class DicomReader {
    static Logger logger = Logger.getLogger(DicomReader.class);
    public static DicomObject read(String filePath) {
        DicomObject dcmObj = null;
        DicomInputStream din = null;

        try {
            din = new DicomInputStream(new File(filePath));
            dcmObj = din.readDicomObject();
        } catch (IOException e) {
            logger.error(e.getMessage() + ": " + filePath);
        } finally {
            try {
            	if(din != null){
            		din.close();
            	}
            } catch (IOException ignore) {
            }
        }

        return dcmObj;
    }
}
