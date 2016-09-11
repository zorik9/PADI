package pl.psnc.scape.dicom.tag;

import org.dcm4che2.data.DicomObject;

public interface ITagFetcher {
	public String getTagValue(String filePath, int tagNumber);
	
	public String getTagValue(DicomObject dicomObject, int tagNumber);
	
	public int getTag(String propertiesTag);
}
