package pl.psnc.scape.dicom.main_args;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import pl.psnc.scape.dicom.anonymization.DirectoryAnonymizer;
import pl.psnc.scape.dicom.anonymization.FileAnonymizer;
import pl.psnc.scape.dicom.config.AppConfig;
import pl.psnc.scape.dicom.personalization.IDirectoryPersonalizer;
import pl.psnc.scape.dicom.personalization.IFilePersonalizer;

public class MainArgumentsManager {
	
    @Autowired
    private IFilePersonalizer filePersonalizer;
    
    @Autowired
    private IDirectoryPersonalizer directoryPersonalizer;
    
    @Autowired
    IMenuDisplayer menuDisplayer;
    
	private String configFilePath;
	
	public MainArgumentsManager(String configFilePath){
		this.configFilePath = configFilePath;
	}
	
	public void handleMainArgs(String[] args){
    	@SuppressWarnings("resource")
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	System.out.println();
    	if((args.length == 3) && (args[0].compareTo("-anonymize") == 0)){
    		FileAnonymizer fileAnonymizer = (FileAnonymizer) context.getBean("FileAnonymizerService", configFilePath);
    		fileAnonymizer.anonymiseFile(args[1], args[2]);
    	} 
    	else if((args.length == 2) && (args[0].compareTo("-anonymize_dir") == 0)){
    		DirectoryAnonymizer directoryAnonymizer = (DirectoryAnonymizer) context.getBean("DirectoryAnonymizerService", configFilePath);
    		directoryAnonymizer.anonimizeDir(args[1]);
    	}
    	else if((args.length == 3) && (args[0].compareTo("-personalize") == 0)){
    		filePersonalizer.personalizeFile(args[1], args[2]);
    	}
    	else if((args.length == 2) && (args[0].compareTo("-personalize_dir") == 0)){
    		directoryPersonalizer.personalizeDir(args[1]);
    	}
    	else if((args.length == 1) && (args[0].compareTo("-help") == 0)){
    		menuDisplayer.printHelp();
    	}
    	else {
    		menuDisplayer.printShortHelp();
    	}
    }
}
