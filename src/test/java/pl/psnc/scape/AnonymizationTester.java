package pl.psnc.scape;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pl.psnc.scape.dicom.anonymization.Anonymizer;
import pl.psnc.scape.dicom.file.TagViewer;


public class AnonymizationTester {
    private static String dicomFilePath = "src/test/resources/sample.dcm";
    private static String outputFilePath = "src/test/resources/sample.txt";
    private static String configFilePath = "src/main/resources/config.properties";
    
	@Test
	public void anonymizationTest() {
		String patientsNameTag = TagViewer.getTag(dicomFilePath, 1048592);
		assertEquals("VOLUMEMERGE", patientsNameTag.trim());
		Anonymizer anonymizer = new Anonymizer(configFilePath);
		anonymizer.anonymise(dicomFilePath, outputFilePath);
		patientsNameTag = TagViewer.getTag(dicomFilePath, 1048592);
		assertEquals(null, patientsNameTag);
	}

}
