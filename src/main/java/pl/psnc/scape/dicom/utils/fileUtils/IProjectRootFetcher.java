package pl.psnc.scape.dicom.utils.fileUtils;

import java.io.File;
import java.nio.file.Path;

public interface IProjectRootFetcher {
	public File getProjectRoot();
	
	public Path getProjectRootPath();
}