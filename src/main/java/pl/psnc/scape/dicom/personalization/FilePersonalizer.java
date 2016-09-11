package pl.psnc.scape.dicom.personalization;

import org.springframework.stereotype.Service;

@Service("FilePersonalizerService")
public class FilePersonalizer extends Personalizer implements IFilePersonalizer{
    public void personalizeFile(String dicomFilePath, String tagsFilePath) {
    	if(fileFinder.exists(dicomFilePath)){
    		if(fileFinder.exists(tagsFilePath)){
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
}
