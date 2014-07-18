# PADI

Personalization and Anonymisation for DIcom

### What does PADI do?

This tool removes DICOM tags (specified in the configuration file: config.properties) and add DICOM tags (specified in text file) from/to the given DICOM file(s).

## Features and roadmap

### Version 1.0.0

* anonymization of single DICOM file
* anonymization of many DICOM files in given directory
* presonalization of single DICOM file
* presonalization of many DICOM files in given directory

## How to use

### Use

* anonymize.bat %dicom_file %anonymized_tag_file
anonymizes DICOM file and saves anonymized tag values in text file.

* anonymize_dir.bat %directory
anonymizes all DICOM files in specified directory and subdirectories and saves
anonymized tag values in text file for each DICOM file.

* personalize.bat %dicom_file %anonymized_tag_file
presonalizes DICOM file.

* personalize_dir.bat %directory
presonalizes all DICOM files in specified directory and subdirectories

## More information

### Licence

PADU is released under [Apache version 2.0 license](LICENSE.txt).

### Support

This tool is supported by the [Poznan Supercomputing and Networking Center](http://psnc.pl). 

