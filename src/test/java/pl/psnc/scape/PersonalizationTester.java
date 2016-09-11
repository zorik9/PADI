package pl.psnc.scape;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import pl.psnc.scape.dicom.personalization.IFilePersonalizer;
import pl.psnc.scape.dicom.tag.ITagFetcher;


public class PersonalizationTester {
    private static String dicomFilePath = "src/test/resources/sample.dcm";
    private static String outputFilePath = "src/test/resources/sample.txt";
    
    @Autowired
    private ITagFetcher tagViewer;
    
    @Autowired
    private IFilePersonalizer personalizer;
    
	@Test
	public void personalizationTest() {
		personalizer.personalizeFile(dicomFilePath, outputFilePath);
		String patientsNameTag = tagViewer.getTagValue(dicomFilePath, 1048592);
		Assert.assertEquals("VOLUMEMERGE", patientsNameTag.trim());
	}
}
