package pl.psnc.scape.dicom.anonymization;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.ElementDictionary;

import pl.psnc.scape.dicom.anonymization.config.Configuration;
import pl.psnc.scape.dicom.file.DicomReader;
import pl.psnc.scape.dicom.file.DicomWriter;
import pl.psnc.scape.dicom.file.TagsArchivizer;
import pl.psnc.scape.dicom.file.Utils;


public class Anonymizer {
    private Configuration configuration;

    public Anonymizer(String configFilePath) {        
        Configuration conf = new Configuration();
        if(conf.configFileExist(configFilePath)){
	        conf.load(configFilePath);
	        this.configuration = conf;
        }
        else {
            System.out.println("No config file: " + configFilePath);
    		System.out.println();
        }
        
    }

    public void anonymise(String dicomFilePath, String tagsFilePath) {
    	if(Utils.exist(dicomFilePath)){
	    	if(configuration != null){
	    		anonymiseDicom(dicomFilePath, tagsFilePath);
	    	}
    	}
    	else {
    		System.out.println("File not found: " + dicomFilePath);
    		System.out.println();
    	}
    }

    public void anonimizeDir(String directoryPath) {
        File directory = new File(directoryPath);
        File[] fList = directory.listFiles();
		for (File file : fList) {
            if (file.isFile()) {
        		this.anonymise(file.getAbsolutePath(), file.getAbsolutePath() + ".txt");
            } else if (file.isDirectory()) {
            	anonimizeDir(file.getAbsolutePath());
            }
        }
    }
    
    private void anonymiseDicom(String dicomFilePath, String tagsFilePath) {
    	DicomObject dicomObject = DicomReader.read(dicomFilePath);
        if(dicomObject != null){
	        List<Integer> tagsToAnonymise = configuration.getTagsToAnonymise();
	        HashMap<String, String> removedTags = new HashMap<String, String>();

            System.out.println(dicomFilePath);
	        for (int tag : tagsToAnonymise) {
	            if (dicomObject.contains(tag)) {
	            	DicomElement dicomElement = dicomObject.get(tag);
	            	if(dicomElement.length() >= 0){
		            	String tagValue = dicomObject.getString(tag);
				    	dicomObject.remove(tag);
		                removedTags.put(String.valueOf(tag), tagValue);
		                String tagName = ElementDictionary.getDictionary().nameOf(tag).replace(" ", "");
		                System.out.println(tagName +  "=" + tagValue);
	            	}
	            }
	        }
            System.out.println();

	        DicomWriter.write(dicomObject, dicomFilePath);
	        TagsArchivizer.save(removedTags, dicomFilePath, tagsFilePath);
        }
    }

    public void showTag(String dicomFilePath, int tag) {
    	DicomObject dicomObject = DicomReader.read(dicomFilePath);
        if(dicomObject != null){
	        DicomElement dicomElement = dicomObject.get(tag);
	        if(dicomElement.length() >= 0){
	        	String tagValue = dicomObject.getString(tag);
		    	String tagName = ElementDictionary.getDictionary().nameOf(tag).replace(" ", "");
		    	System.out.println(tagName +  "=" + tagValue);
	        }
            System.out.println();
        }
    }


    public void printAllTags(String dicomFilePath) {
    	DicomObject dicomObject = DicomReader.read(dicomFilePath);
        if(dicomObject != null){
        	Iterator<DicomElement> i = dicomObject.iterator();
        	DicomElement de; 
			while((de = i.next()) != null){
		    	System.out.println(de);
			}
        }
    }
    
    public String getAccessionNumberTag(String dicomFilePath) {
    	DicomObject dicomObject = DicomReader.read(dicomFilePath);
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
