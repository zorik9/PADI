package pl.psnc.scape.dicom.io.reader;

import org.dcm4che2.data.DicomObject;

public interface IDicomReader {
	public DicomObject read(String filePath);
}
