package pl.psnc.scape;

import java.io.IOException;

import pl.psnc.scape.dicom.anonymization.Anonymizer;
import pl.psnc.scape.dicom.personalization.Personalizer;


public class Luncher {
    private static String configFilePath = "config.properties";

    public static void main(String[] args) throws IOException {	
    	System.out.println();
    	if((args.length == 3) && (args[0].compareTo("-anonymize") == 0)){
    		Anonymizer anonymizer = new Anonymizer(configFilePath);
    		anonymizer.anonymise(args[1], args[2]);
    	} 
    	else if((args.length == 2) && (args[0].compareTo("-anonymize_dir") == 0)){
    		Anonymizer anonymizer = new Anonymizer(configFilePath);
    		anonymizer.anonimizeDir(args[1]);
    	}
    	else if((args.length == 3) && (args[0].compareTo("-personalize") == 0)){
            Personalizer.personalize(args[1], args[2]);
    	}
    	else if((args.length == 2) && (args[0].compareTo("-personalize_dir") == 0)){
            Personalizer.personalizeDir(args[1]);
    	}
    	else if((args.length == 1) && (args[0].compareTo("-help") == 0)){
    		printHelp();
    	}
    	else {
    		printShortHelp();
    	}
    }

	private static void printShortHelp() {
		System.out.println("This tool removes DICOM tags (specified in the configuration file: config.properties) and add DICOM tags (specified in text file) to from/to the given DICOM file(s).");
		System.out.println();
		System.out.println("Parameters:");
		System.out.println("-anonymize <dicom_file> <anonymized_tag_file>");
		System.out.println("-anonymize_dir <directory> ");
		System.out.println("-personalize <dicom_file> <anonymized_tag_file>");
		System.out.println("-personalize_dir <directory>");
		System.out.println("-help");
		System.out.println(); 
	}
	
	private static void printHelp() {
		System.out.println("This tool removes DICOM tags (specified in the configuration file: config.properties) and add DICOM tags (specified in text file) to from/to the given DICOM file(s).");
		System.out.println();
		System.out.println("Parameters:");
		
		System.out.println("-anonymize <dicom_file> <anonymized_tag_file>");
		System.out.println(" anonymizes DICOM file and saves anonymized tag values in text file.");
		System.out.println();
		System.out.println("-anonymize_dir <directory> ");
		System.out.println(" anonymizes all DICOM files in specified directory and subdirectories and saves anonymized tag values in text file for each DICOM file.");
		System.out.println();
		System.out.println("-personalize <dicom_file> <anonymized_tag_file>");
		System.out.println(" presonalizes DICOM file.");
		System.out.println();
		System.out.println("-personalize_dir <directory>");
		System.out.println(" presonalizes all DICOM files in specified directory and subdirectories");
		System.out.println(); 
	}
}
