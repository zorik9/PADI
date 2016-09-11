package pl.psnc.scape.dicom.anonymization;

import java.io.File;

import pl.psnc.scape.dicom.data.DicomConfigElements;

public interface IAnonymizerInitializer {
	public DicomConfigElements initAnonymizer(File configFile);
	
	public DicomConfigElements initAnonymizer(String configFilePath);
}
