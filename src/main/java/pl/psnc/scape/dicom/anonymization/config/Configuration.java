package pl.psnc.scape.dicom.anonymization.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Configuration {
    private List<ConfigurationElement> configurationElementList;

    public void load(String filePath) {
        Properties prop = new Properties();
        setConfigurationElementList(new ArrayList<ConfigurationElement>());

        try {
            prop.load(new FileInputStream(filePath));

            Set<Object> listOfDicomTags = prop.keySet();

            for (Object object : listOfDicomTags) {
                int tag = getDicomTag((String) object);
                boolean flag = getFlag((String) prop.get(object));

                ConfigurationElement ce = new ConfigurationElement();
                ce.setDicomTag(tag);
                ce.setConfigTag((String) object);
                ce.setFlag(flag);

                getConfigurationElementList().add(ce);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean configFileExist(String filePath){
    	File file = new File(filePath);
    	return file.exists();
    }

    public void store(HashMap<String, String> properties, String filepath) {
        Properties prop = new Properties();

        try {
            for (String key : properties.keySet()) {
                prop.setProperty(key, properties.get(key));
            }

            prop.store(new FileOutputStream(filepath + ".result"), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getDicomTag(String propertiesTag) {
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(propertiesTag);
        matcher.find();

        String tagId = matcher.group(1);
        tagId = tagId.replaceAll(",", "");

        return Integer.parseInt(tagId, 16);
    }

    private boolean getFlag(String propertiesValue) {
        if (propertiesValue.compareTo("1") == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setConfigurationElementList(
        List<ConfigurationElement> configurationElementList) {
        this.configurationElementList = configurationElementList;
    }

    public List<ConfigurationElement> getConfigurationElementList() {
        return configurationElementList;
    }

    public ConfigurationElement getConfigurationElement(String tag) {
    	for(ConfigurationElement ce : getConfigurationElementList()){
    		if(ce.getConfigTag().compareTo(tag) == 0){
    			return ce;
    		}
		}
    	return null;
    }

    public List<Integer> getTagsToAnonymise() {
        List<Integer> listOfTags = new ArrayList<Integer>();

        for (ConfigurationElement ce : getConfigurationElementList()) {
            if (ce.isFlag()) {
                listOfTags.add(ce.getDicomTag());
            }
        }

        return listOfTags;
    }

    public boolean getFlag(int tag) {
        for (ConfigurationElement ce : getConfigurationElementList()) {
            if (ce.getDicomTag() == tag) {
                return ce.isFlag();
            }
        }

        return false;
    }

    public String getConfigTag(int tag) {
        for (ConfigurationElement ce : getConfigurationElementList()) {
            if (ce.getDicomTag() == tag) {
                return ce.getConfigTag();
            }
        }

        return null;
    }
}
