package pl.psnc.scape;

import java.io.IOException;

import pl.psnc.scape.dicom.main_args.MainArgumentsManager;

public class Luncher {
    private static String configFilePath = "tags.properties";

    public static void main(String[] args) throws IOException {
    	new MainArgumentsManager(configFilePath).handleMainArgs(args);
    }
}
