package pl.psnc.scape.dicom.anonymization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.psnc.scape.dicom.data.AnonymizerData;
import pl.psnc.scape.dicom.data.FileAnonymizationResults;

@Service("FileAnonymizerService")
@Scope("prototype")
public class FileAnonymizer extends Anonymizer implements IFileAnonymizer{

	public FileAnonymizer(){}
	
    @Autowired
    public FileAnonymizer(AnonymizerData anonymizerData) {
    	super(anonymizerData);
    }
    
    public FileAnonymizationResults anonymiseFile(String dicomFilePath, String tagsFilePath) {
    	
    	if(!fileFinder.exists(dicomFilePath)){
    		logger.error("Dicom file was not found: " + dicomFilePath);
    		return null;
    	}
    	
    	if(configuration == null){
    		logger.error("Dicom config file was not found: " + dicomFilePath);
    		return null;
    	}
    	
    	return anonymiseDicom(dicomFilePath, tagsFilePath);
    }
}
