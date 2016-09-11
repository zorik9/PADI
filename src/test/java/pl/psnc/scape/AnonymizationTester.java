package pl.psnc.scape;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import pl.psnc.scape.dicom.anonymization.AnonymizerInitializer;
import pl.psnc.scape.dicom.anonymization.FileAnonymizer;
import pl.psnc.scape.dicom.anonymization.IAnonymizerInitializer;
import pl.psnc.scape.dicom.anonymization.IFileAnonymizer;
import pl.psnc.scape.dicom.config.AppConfig;
import pl.psnc.scape.dicom.data.FileAnonymizationResults;
import pl.psnc.scape.dicom.tag.ITagFetcher;

@Service("AnonymizationTesterService")
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class AnonymizationTester extends AbstractTestNGSpringContextTests {
    private static String dicomFilePath = "src/test/resources/sample.dcm";
    private static String outputFilePath = "src/test/resources/sample.txt";
    private static String configFilePath = "src/main/resources/tags.properties";
    
    @Autowired
    private ITagFetcher tagFetcher;
    
    private IFileAnonymizer fileAnonymizer;
    
	@Test
	public void anonymizationTest() {
		
		setFileAnonymizer();
		int tagNumber = 1048592;
		String patientsNameTag = tagFetcher.getTagValue(dicomFilePath, tagNumber);
		
		File originalDicomFile = new File(dicomFilePath);
		File parentFile = originalDicomFile.getParentFile();
		String origFileName = Files.getNameWithoutExtension(dicomFilePath);
		String newFileName = origFileName + "_copy";
		File newDicomFile = new File(parentFile, newFileName);
		try {
			Files.copy(originalDicomFile, newDicomFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileAnonymizationResults fileAnonymizationResults = fileAnonymizer.anonymiseFile(newDicomFile.getAbsolutePath(), configFilePath);
		DicomObject dicomObject = fileAnonymizationResults.getDicomObject();
		
        DicomElement dicomElement = dicomObject.get(tagNumber);
        
        Assert.assertNotNull(dicomElement);
        
        Assert.assertTrue(dicomElement.length() > 0);
        
        String tagValue = dicomObject.getString(tagNumber);
        
        Assert.assertNull(tagValue);
        
        patientsNameTag = tagFetcher.getTagValue(dicomFilePath, tagNumber);
        
        Assert.assertNull(patientsNameTag);
	}
	
	private void setFileAnonymizer(){
		AbstractApplicationContext padiMasterContext = new AnnotationConfigApplicationContext(AppConfig.class);
		IAnonymizerInitializer anonymizerInitializer = (AnonymizerInitializer)padiMasterContext.getBean("AnonymizerInitializerService");
		Map<Integer, String> tagsReplacement = null;
 		fileAnonymizer = (FileAnonymizer) padiMasterContext.getBean("FileAnonymizerService", configFilePath, false, tagsReplacement, anonymizerInitializer);
		padiMasterContext.close();	
	}
}
