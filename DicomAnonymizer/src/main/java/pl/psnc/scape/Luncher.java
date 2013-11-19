package pl.psnc.scape;

import java.io.IOException;

import pl.psnc.scape.dicom.anonymization.Anonymizer;
import pl.psnc.scape.dicom.personalization.Personalizer;


public class Luncher {
    private static String configFilePath = "config.properties";
    
    public static void main(String[] args) throws IOException {
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
    }
}
