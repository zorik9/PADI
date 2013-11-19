package pl.psnc.scape.dicom.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;


public abstract class TagsArchivizer {
    public static void save(HashMap<String, String> properties, String dicomFilePath, String tagsFilePath) {
        Properties prop = new Properties();

        if (properties.values().size() > 0) {
            try {
                for (String key : properties.keySet()) {
                	if(properties.get(key) != null){
                    	prop.setProperty(key, properties.get(key));
                	}
                }

                prop.store(new FileOutputStream(tagsFilePath), null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } 
    }
    
    public static HashMap<Integer, String> load(String filePath) {
        Properties prop = new Properties();
        HashMap<Integer, String> tags = new HashMap<Integer, String>();
        
        try {
            prop.load(new FileInputStream(filePath));

            Set<Object> listOfKeys = prop.keySet();

            for (Object key : listOfKeys) {
            	tags.put(Integer.parseInt((String) key), prop.getProperty((String) key));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return tags;
    }
}
