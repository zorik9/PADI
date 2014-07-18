package pl.psnc.scape.dicom.anonymization.config;

public class ConfigurationElement {
    private int dicomTag;
    private String configTag;
    private boolean flag;

    public void setDicomTag(int dicomTag) {
        this.dicomTag = dicomTag;
    }

    public int getDicomTag() {
        return dicomTag;
    }

    public void setConfigTag(String configTag) {
        this.configTag = configTag;
    }

    public String getConfigTag() {
        return configTag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }
}
