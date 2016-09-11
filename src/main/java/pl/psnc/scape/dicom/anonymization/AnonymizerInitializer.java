package pl.psnc.scape.dicom.anonymization;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.psnc.scape.dicom.data.DicomConfigElements;
import pl.psnc.scape.dicom.loader.IDicomLoader;
import pl.psnc.scape.dicom.utils.fileUtils.IFileFinder;

@Service("AnonymizerInitializerService")
@Scope("prototype")
public class AnonymizerInitializer implements IAnonymizerInitializer{
	static Logger logger = Logger.getLogger(AnonymizerInitializer.class);
	
	public AnonymizerInitializer(){}
	
    @Autowired
    private IDicomLoader dicomLoader;
    
    @Autowired
    private IFileFinder fileFinder;
    
	public DicomConfigElements initAnonymizer(File configFile){
		
        if(fileFinder.exists(configFile)){
        	dicomLoader.load(configFile);
	        return dicomLoader.getDicomConfigElements();
        }
        else {
        	if(configFile == null){
        		logger.error("Config file is empty.");
        	}
        	else{
        		logger.error("No config file: " + configFile.getAbsolutePath());
        	}
        	
        	return null;
        }
    }
    
    public DicomConfigElements initAnonymizer(String configFilePath){
        if(fileFinder.exists(configFilePath)){
        	logger.info("Loading Dicom elements configuration.");
        	dicomLoader.load(configFilePath);
	        return dicomLoader.getDicomConfigElements();
        }
        else {
        	return findAndLoadDicomConfigElements(configFilePath, dicomLoader.getDicomConfigElements());
        }
    }

	private DicomConfigElements findAndLoadDicomConfigElements(String configFilePath, DicomConfigElements conf) {
		logger.info("The given config file could not be found.\nAttempting to locate the config file from project root.");
		
		File fetchedConfigFilePath = fileFinder.findFile("config", "properties");
		
		if(fetchedConfigFilePath == null){
			logger.error("Could not find the config file: " + configFilePath);
			return null;
		}
		else{
			logger.info("Config file has been found at: " + fetchedConfigFilePath.getAbsolutePath() + "\nLoading Dicom elements configuration.");
			dicomLoader.load(fetchedConfigFilePath);
		    return dicomLoader.getDicomConfigElements();
		}
	}
}
