package pl.psnc.scape.dicom.main_args;

import org.springframework.stereotype.Service;

@Service("MenuDisplayerService")
public class MenuDisplayer {
	public void printShortHelp() {
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
	
	public void printHelp() {
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
