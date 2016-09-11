package pl.psnc.scape.dicom.loader;

import java.io.File;

import pl.psnc.scape.dicom.data.DicomConfigElements;
import pl.psnc.scape.dicom.tag.ITagFetcher;

public interface IDicomLoader {
	
	public void load(File file);
	
	public void load(String filePath);
	
	public ITagFetcher getTagFetcher();
	
	public DicomConfigElements getDicomConfigElements();
}
