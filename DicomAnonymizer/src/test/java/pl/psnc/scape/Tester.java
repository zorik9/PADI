package pl.psnc.scape;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.psnc.scape.dicom.anonymization.config.Configuration;


public class Tester {
    private static String configFilePath = "config.properties";
    
	@Test
	public void configurationFileTest() {
		Configuration conf = new Configuration();
        if(conf.configFileExist(configFilePath)){
	        conf.load(configFilePath);
	        conf.getConfigurationElementList().get(0);
	        assertEquals(1577008, conf.getConfigurationElementList().get(0).getDicomTag()); // ProtocolName(0018,1030)
	        assertEquals(false, conf.getConfigurationElementList().get(0).isFlag()); // ProtocolName(0018,1030)
	        assertEquals(524312, conf.getConfigurationElementList().get(1).getDicomTag()); // SOPInstanceUID(0008,0018)
	        assertEquals(1048592, conf.getConfigurationElementList().get(3).getDicomTag()); // PatientsName(0010,0010)
    		System.out.println();
        }
        else {
            System.out.println("No config file: " + configFilePath);
    		System.out.println();
        }
	}

}
