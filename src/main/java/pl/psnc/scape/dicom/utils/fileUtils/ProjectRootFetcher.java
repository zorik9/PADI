package pl.psnc.scape.dicom.utils.fileUtils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service("ProjectRootFetcherService")
public class ProjectRootFetcher implements IProjectRootFetcher{
	public File getProjectRoot(){
		return new File(getProjectRootPath().toUri());
	}
	
	public Path getProjectRootPath(){
        String className = getClass().getName();
        System.out.println("Class name: " + className);
        String classfileName = "/" + className.replace('.', '/') + ".class";
        URL classfileResource = getClass().getResource(classfileName);
        
        if (classfileResource != null) {
            Path absolutePackagePath = null;
			try {
				String resourcePath = classfileResource.toURI().toString();
				System.out.println("current class file resource: " + resourcePath);
				if(resourcePath.contains("jar:")){
					resourcePath = resourcePath.replace("jar:", "").
								   replaceFirst("tarball-copier-.*.jar!", "classes");
					
					System.out.println("Updated resource path: " + resourcePath);
					
				}
				URI resourceUri = new URI(resourcePath);
				System.out.println("current class file resource uri path: " + Paths.get(resourceUri));
				absolutePackagePath = Paths.get(resourceUri).getParent();
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return null;
			}
            int packagePathSegments = className.length()
                    - className.replace(".", "").length();
            // Remove package segments from path, plus two more levels
            // for "target/classes", which is the standard location for
            // classes in Eclipse.
            Path path = absolutePackagePath;
            for (int i = 0, segmentsToRemove = packagePathSegments + 2;
                    i < segmentsToRemove; i++) {
                path = path.getParent();
            }
            
            return path;
        }
        
        return null;
	}
}
