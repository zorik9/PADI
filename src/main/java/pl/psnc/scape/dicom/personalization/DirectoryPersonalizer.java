package pl.psnc.scape.dicom.personalization;

import java.io.File;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("DirectoryPersonalizerService")
@Scope("prototype")
public class DirectoryPersonalizer extends FilePersonalizer implements IDirectoryPersonalizer{
    public void personalizeDir(String directoryPath) {
        File directory = new File(directoryPath);
        File[] fList = directory.listFiles();
		for (File file : fList) {
            if (file.isFile()) {
            	personalizeFile(file.getAbsolutePath(), file.getAbsolutePath() + ".txt");
            }
            else if (file.isDirectory()) {
            	personalizeDir(file.getAbsolutePath());
            }
        }
    }
}
