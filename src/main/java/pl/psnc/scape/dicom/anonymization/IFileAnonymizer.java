package pl.psnc.scape.dicom.anonymization;

import pl.psnc.scape.dicom.data.FileAnonymizationResults;

public interface IFileAnonymizer {
	public FileAnonymizationResults anonymiseFile(String dicomFilePath, String tagsFilePath);
}
