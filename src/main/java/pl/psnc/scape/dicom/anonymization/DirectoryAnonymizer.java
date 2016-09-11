package pl.psnc.scape.dicom.anonymization;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.psnc.scape.dicom.data.AnonymizerData;
import pl.psnc.scape.dicom.data.DirAnonymizationResults;
import pl.psnc.scape.dicom.data.FileAnonymizationResults;

@Service("DirectoryAnonymizerService")
@Scope("prototype")
public class DirectoryAnonymizer extends FileAnonymizer implements IDirectoryAnonymizer{
	
	public DirectoryAnonymizer(){}
	
    @Autowired
    public DirectoryAnonymizer(AnonymizerData anonymizerData) {
    	super(anonymizerData);
    }

    public DirAnonymizationResults anonimizeDir(String directoryPath) {
    	DirAnonymizationResults dirAnonymizationResults = new DirAnonymizationResults();
    	List<File> skippedFiles = new ArrayList<>();
    	Map<String, FileAnonymizationResults> fileAnonymizationResultsMap = new HashMap<>();
    	FileAnonymizationResults fileAnonymizationResults;
    	File directory = new File(directoryPath);
        
        File[] fList = directory.listFiles();
		for (File file : fList) {
            if (file.isFile()) {
            	fileAnonymizationResults = anonymiseFile(file.getAbsolutePath(), file.getAbsolutePath() + ".txt");
            	
            	if(fileAnonymizationResults == null || fileAnonymizationResults.getDicomObject() == null){
            		skippedFiles.add(file);
            	}
            	else{
            		fileAnonymizationResultsMap.put(file.getAbsolutePath(), fileAnonymizationResults);
            	}
            } else if (file.isDirectory()) {
            	anonimizeDir(file.getAbsolutePath());
            }
        }
		
		dirAnonymizationResults.setSkippedFiles(skippedFiles);
		dirAnonymizationResults.setFileAnonymizationResultsMap(fileAnonymizationResultsMap);
		return dirAnonymizationResults;
    }
}
