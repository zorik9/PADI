package pl.psnc.scape.dicom.io.writer;

import org.dcm4che2.data.DicomObject;

public interface IDicomWriter {
	public boolean write(DicomObject dicomObject, String filePath);
}
