package pl.psnc.scape;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pl.psnc.scape.dicom.file.TagViewer;
import pl.psnc.scape.dicom.personalization.Personalizer;


public class PersonalizationTester {
    private static String dicomFilePath = "src/test/resources/sample.dcm";
    private static String outputFilePath = "src/test/resources/sample.txt";
    
	@Test
	public void personalizationTest() {
		Personalizer.personalize(dicomFilePath, outputFilePath);
		String patientsNameTag = TagViewer.getTag(dicomFilePath, 1048592);
		assertEquals("VOLUMEMERGE", patientsNameTag.trim());
	}
}
