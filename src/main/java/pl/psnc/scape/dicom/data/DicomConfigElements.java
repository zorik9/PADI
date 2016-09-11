package pl.psnc.scape.dicom.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository
public class DicomConfigElements {
    private List<DicomConfigElement> configurationElementList;

    public DicomConfigElement getConfigurationElement(String tag) {
    	for(DicomConfigElement ce : getConfigurationElementList()){
    		if(ce.getConfigTag().compareTo(tag) == 0){
    			return ce;
    		}
		}
    	return null;
    }

    public List<Integer> getTagsToAnonymise() {
        List<Integer> listOfTags = new ArrayList<Integer>();

        for (DicomConfigElement ce : getConfigurationElementList()) {
            if (ce.isFlag()) {
                listOfTags.add(ce.getDicomTag());
            }
        }

        return listOfTags;
    }
}
