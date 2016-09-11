package pl.psnc.scape.dicom.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Service;

import pl.psnc.scape.dicom.data.DicomConfigElement;

@Service("DicomLoaderService")
public class DicomLoader extends BaseDicomLoader{
	
	public void load(File file){
        Properties prop = new Properties();
        dicomConfigElements.setConfigurationElementList(new ArrayList<DicomConfigElement>());

        try {
            prop.load(new FileInputStream(file));

            Set<Object> listOfDicomTags = prop.keySet();

            for (Object object : listOfDicomTags) {
                int tag = tagFetcher.getTag((String) object);
                boolean flag = getFlag((String) prop.get(object));

                DicomConfigElement ce = new DicomConfigElement();
                ce.setDicomTag(tag);
                ce.setConfigTag((String) object);
                ce.setFlag(flag);

                dicomConfigElements.getConfigurationElementList().add(ce);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void load(String filePath) {
    	load(new File(filePath));
    }
    
    private boolean getFlag(String propertiesValue) {
        return propertiesValue.endsWith("1");
    }
}
