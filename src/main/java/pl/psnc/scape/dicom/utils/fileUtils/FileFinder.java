package pl.psnc.scape.dicom.utils.fileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.io.Files;

@Service("FileFinderService")
public class FileFinder implements IFileFinder{
	
	@Autowired
	private IProjectRootFetcher projectRootFetcher;
	
	public boolean exists(String filePath){
		if(filePath == null){
			return false;
		}
		return exists(new File(filePath));
	}
	
	public boolean exists(File file){
		return file != null && file.exists();
	}
	
	public File findFile(String fileName){
		String extension = Files.getFileExtension(fileName);
		String nameWithoutExtension = Files.getNameWithoutExtension(fileName);
		return findFile(nameWithoutExtension, extension);
	}
	
	public File findFile(String name, String extension){
		return findFile(name, new String[]{extension});
	}
	
	public File findFile(String name, String[] extensions){
	    File root = projectRootFetcher.getProjectRoot();
	    boolean recursive = true;

	    Collection<?> files = FileUtils.listFiles(root, extensions, recursive);

	    for (Iterator<?> iterator = files.iterator(); iterator.hasNext();) {
	    	File file = (File) iterator.next();
	    	if(file.getName().toLowerCase().contains(name)){
	    		return file;
	    	}
	    }
	    return null;
    }
}
