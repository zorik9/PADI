package pl.psnc.scape;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import pl.psnc.scape.dicom.config.AppConfig;
import pl.psnc.scape.dicom.loader.IDicomLoader;
import pl.psnc.scape.dicom.tag.ITagFetcher;
import pl.psnc.scape.dicom.utils.fileUtils.IFileFinder;

@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class TagViewerTester extends AbstractTestNGSpringContextTests{
    static Logger logger = Logger.getLogger(TagViewerTester.class);
    private static String configFilePath = "src/main/resources/tags.properties";
    private static String filePath = "src/test/resources/sample.dcm";
    
    @Autowired
    private IDicomLoader deicomLoader;
    
    @Autowired
    private ITagFetcher tagFetcher;
    
    @Autowired
    private IFileFinder fileFinder;
    
	@Test
	public void getTagTest() {
        if(fileFinder.exists((configFilePath))){
        	deicomLoader.load(configFilePath);
	        String tagValue = tagFetcher.getTagValue(filePath, tagFetcher.getTag("PatientsName(0010,0010)"));
	        Assert.assertEquals("VOLUMEMERGE", tagValue.trim());
		}
	    else {
	    	logger.error("No config file: " + configFilePath);
	    }
	}
}
