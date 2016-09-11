# PADI

Personalization and Anonymisation for DIcom

### What does PADI do?

This tool removes DICOM tags (specified in the configuration file: config.properties) or adds DICOM tags (specified in a text file) from/to the given DICOM file(s).

## Features and roadmap

### Version 1.0.0

* anonymization of a single DICOM file
* anonymization of multiple DICOM files provided in a given directory
* presonalization of a single DICOM file
* presonalization of multiple DICOM files provided in a given directory

## How to use

### Use

* anonymize DICOM file and save anonymized tag values in a text file.

```bash
anonymize.bat <dicom_file> <anonymized_tag_file>
```

* anonymize all DICOM files in specified directory (and its subdirectories) and save anonymized tag values in multiple text files (one text file per each DICOM file).

```bash
anonymize_dir.bat <directory>
```

* presonalize DICOM file.
 
```bash
personalize.bat <dicom_file> <anonymized_tag_file>
```

* presonalize all DICOM files in specified directory and subdirectories
 
```bash
personalize_dir.bat <directory>
```

## More information

### Licence

PADI is released under [Apache version 2.0 license](LICENSE.txt).

### Support

This tool is supported by the [Poznan Supercomputing and Networking Center](http://psnc.pl). 

