package pl.psnc.scape.dicom.anonymization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.ElementDictionary;
import org.dcm4che2.data.VR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import pl.psnc.scape.dicom.data.AnonymizerData;
import pl.psnc.scape.dicom.data.DicomConfigElements;
import pl.psnc.scape.dicom.data.FileAnonymizationResults;
import pl.psnc.scape.dicom.io.reader.IDicomReader;
import pl.psnc.scape.dicom.io.writer.DicomWriter;
import pl.psnc.scape.dicom.tag.ITagArchivizer;
import pl.psnc.scape.dicom.tag.ITagFetcher;
import pl.psnc.scape.dicom.utils.fileUtils.IFileFinder;

@Service("AnonymizerService")
@Scope("prototype")
public class Anonymizer {
    static Logger logger = Logger.getLogger(Anonymizer.class);
    
    protected DicomConfigElements configuration;
    
    protected AnonymizerData anonymizerData;
    
    protected Map<String, FileAnonymizationResults> anonymizationDirResults = new TreeMap<String, FileAnonymizationResults>();
    
    @Autowired
    protected IDicomReader dicomReader;
    
    @Autowired
    protected ITagArchivizer tagArchivizer;
    
    @Autowired
	protected IFileFinder fileFinder;
    
    @Autowired
    protected ITagFetcher tagFetcher;
    
    public Anonymizer(){}
    
    @Autowired
    public Anonymizer(AnonymizerData anonymizerData) {
    	this.anonymizerData = anonymizerData;
    	configuration = anonymizerData.getAnonymizerInitializer().initAnonymizer(anonymizerData.getConfigFilePath());
    }
    
    protected FileAnonymizationResults anonymiseDicom(String dicomFilePath, String tagsFilePath) {
    	DicomObject dicomObject = dicomReader.read(dicomFilePath);
    	
        if(dicomObject == null){
        	logger.error("Failed to read dicom object from the file: " + dicomFilePath);
        	return null;
        }
        
        List<Integer> tagsToAnonymise = configuration.getTagsToAnonymise();
        Map<String, String> removedTags = new HashMap<String, String>();

        System.out.println(dicomFilePath);
        
        anonymizeDicomElements(dicomObject, tagsToAnonymise, removedTags);

        new DicomWriter().write(dicomObject, dicomFilePath);
        
        if(anonymizerData.isArchiveTags()){
        	tagArchivizer.save(removedTags, dicomFilePath, tagsFilePath);
        }
        
        return new FileAnonymizationResults(dicomObject, removedTags);
    }

	private void anonymizeDicomElements(DicomObject dicomObject, List<Integer> tagsToAnonymise,
			Map<String, String> removedTags) {
		
		String valueofReplaceTag;
		Map<Integer, String> tagsReplacement = anonymizerData.getTagsReplacement();
		
        for (int tag : tagsToAnonymise) {
        	valueofReplaceTag = tagsReplacement != null && tagsReplacement.containsKey(tag) ?
					 tagsReplacement.get(tag) : "";
					 
            if (dicomObject.contains(tag)) {
            	DicomElement dicomElement = dicomObject.get(tag);
            	if(dicomElement.length() >= 0){
	            	anonymizeDicomElement(dicomObject, removedTags, valueofReplaceTag, tag);
            	}
            }
        }
	}

	private void anonymizeDicomElement(DicomObject dicomObject, Map<String, String> removedTags,
			String valueofReplaceTag, int tag) {
		
		String tagValue = dicomObject.getString(tag);
		if(StringUtils.isEmpty(valueofReplaceTag)){
			dicomObject.remove(tag);
		}
		else{
			VR replaceTagVR = dicomObject.vrOf(tag);
			dicomObject.putString(tag, replaceTagVR, valueofReplaceTag);
			tagValue = valueofReplaceTag;
		}
		removedTags.put(String.valueOf(tag), tagValue);
		String tagName = ElementDictionary.getDictionary().nameOf(tag).replace(" ", "");
		System.out.println(tagName +  "=" + tagValue);
	}
}
