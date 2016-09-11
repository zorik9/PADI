package pl.psnc.scape.dicom.anonymization;

import pl.psnc.scape.dicom.data.DirAnonymizationResults;

public interface IDirectoryAnonymizer {
	public DirAnonymizationResults anonimizeDir(String directoryPath);
}
