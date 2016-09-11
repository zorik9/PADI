package pl.psnc.scape.dicom.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import pl.psnc.scape.dicom.data.DicomConfigElements;
import pl.psnc.scape.dicom.tag.ITagFetcher;

@Service("BaseDicomLoaderService")
@Data
public abstract class BaseDicomLoader implements IDicomLoader {
	@Autowired
	protected DicomConfigElements dicomConfigElements;
	
	@Autowired
	protected ITagFetcher tagFetcher;
}
