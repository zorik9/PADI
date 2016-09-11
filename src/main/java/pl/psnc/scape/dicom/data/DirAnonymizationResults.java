package pl.psnc.scape.dicom.data;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.dcm4che2.data.DicomObject;

import lombok.Data;

@Data
public class DirAnonymizationResults {
	private Map<String, DicomObject> filesBeforeAnonymization;
	private Map<String, FileAnonymizationResults> fileAnonymizationResultsMap;
	private List<File> skippedFiles;
}