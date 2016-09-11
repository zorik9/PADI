package pl.psnc.scape.dicom.data;

import java.util.Map;

import lombok.Data;
import pl.psnc.scape.dicom.anonymization.IAnonymizerInitializer;

@Data
public class AnonymizerData {
	private String configFilePath;
	private boolean archiveTags;
	private Map<Integer, String> tagsReplacement;
	private IAnonymizerInitializer anonymizerInitializer;
}
