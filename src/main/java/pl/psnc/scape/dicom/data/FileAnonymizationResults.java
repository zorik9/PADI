package pl.psnc.scape.dicom.data;

import java.util.Map;

import org.dcm4che2.data.DicomObject;
import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository
public class FileAnonymizationResults {
	private DicomObject dicomObject;
	private Map<String, String> removedTags;
	
	public FileAnonymizationResults() {}
	
	public FileAnonymizationResults(DicomObject dicomObject, Map<String, String> removedTags) {
		this.dicomObject = dicomObject;
		this.removedTags = removedTags;
	}
}
