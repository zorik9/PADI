package pl.psnc.scape.dicom.tag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.psnc.scape.dicom.io.reader.DicomReader;

@Service("TagFetcherService")
@Scope("prototype")
public class TagFetcher implements ITagFetcher{
    
    public String getTagValue(String filePath, int tagNumber){
    	DicomObject dicomObject = new DicomReader().read(filePath);
    	return getTagValue(dicomObject, tagNumber);
    }
    
    public String getTagValue(DicomObject dicomObject, int tagNumber){
    	if(dicomObject == null){
    		return null;
    	}
    	
        DicomElement dicomElement = dicomObject.get(tagNumber);
        if((dicomElement != null) && (dicomElement.length() >= 0)){
        	String tagValue = dicomObject.getString(tagNumber);
        	return tagValue;
        }
        return null;
    }
    
    public int getTag(String propertiesTag) {
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(propertiesTag);
        matcher.find();

        String tagId = matcher.group(1);
        tagId = tagId.replaceAll(",", "");

        return Integer.parseInt(tagId, 16);
    }
}
