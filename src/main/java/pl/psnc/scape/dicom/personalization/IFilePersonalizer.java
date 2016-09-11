package pl.psnc.scape.dicom.personalization;

public interface IFilePersonalizer {
	public void personalizeFile(String dicomFilePath, String tagsFilePath);
}
