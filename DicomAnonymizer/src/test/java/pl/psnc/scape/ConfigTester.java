package pl.psnc.scape;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.psnc.scape.dicom.anonymization.config.Configuration;
import pl.psnc.scape.dicom.anonymization.config.ConfigurationElement;


public class ConfigTester {
    private static String configFilePath = "config.properties";
    
	@Test
	public void configurationFileTest() {
		Configuration conf = new Configuration();
        if(conf.configFileExist(configFilePath)){
	        conf.load(configFilePath);

	        assertEquals(1577008, conf.getConfigurationElement("ProtocolName(0018,1030)").getDicomTag());
	        assertEquals(524312, conf.getConfigurationElement("SOPInstanceUID(0008,0018)").getDicomTag());
	        assertEquals(524434, conf.getConfigurationElement("ReferringPhysiciansAddress(0008,0092)").getDicomTag());
	        assertEquals(1048592, conf.getConfigurationElement("PatientsName(0010,0010)").getDicomTag());
	        assertEquals(1052688, conf.getConfigurationElement("PatientsAge(0010,1010)").getDicomTag());
	        assertEquals(1057120, conf.getConfigurationElement("EthnicGroup(0010,2160)").getDicomTag());
	        assertEquals(528456, conf.getConfigurationElement("PhysicianofRecord(0008,1048)").getDicomTag());
	        assertEquals(2097166, conf.getConfigurationElement("SeriesInstanceUID(0020,000E)").getDicomTag());
	        assertEquals(4237104, conf.getConfigurationElement("ContentSequence(0040,A730)").getDicomTag());
	        assertEquals(1057152, conf.getConfigurationElement("Occupation(0010,2180)").getDicomTag());
	        assertEquals(528446, conf.getConfigurationElement("SeriesDescription(0008,103E)").getDicomTag());
	        assertEquals(1048624, conf.getConfigurationElement("PatientsBirthDate(0010,0030)").getDicomTag());
	        assertEquals(1057200, conf.getConfigurationElement("AdditionalPatientsHistory(0010,21B0)").getDicomTag());
	        assertEquals(524308, conf.getConfigurationElement("InstanceCreatorUID(0008,0014)").getDicomTag());
	        assertEquals(1052816, conf.getConfigurationElement("MedicalRecordLocator(0010,1090)").getDicomTag());
	        assertEquals(1048626, conf.getConfigurationElement("PatientsBirthTime(0010,0032)").getDicomTag());
	        assertEquals(805699778, conf.getConfigurationElement("RelatedFrameofReferenceUID(3006,00C2)").getDicomTag());
	        assertEquals(528464, conf.getConfigurationElement("PerformingPhysiciansName(0008,1050)").getDicomTag());
	        assertEquals(528480, conf.getConfigurationElement("NameofPhysicianReadingStudy(0008,1060)").getDicomTag());
	        assertEquals(1052673, conf.getConfigurationElement("OtherPatientNames(0010,1001)").getDicomTag());
	        assertEquals(1048608, conf.getConfigurationElement("PatientID(0010,0020)").getDicomTag());
	        assertEquals(524368, conf.getConfigurationElement("AccessionNumber(0008,0050)").getDicomTag());
	        assertEquals(528432, conf.getConfigurationElement("StudyDescription(0008,1030)").getDicomTag());
	        assertEquals(528448, conf.getConfigurationElement("InstitutionalDepartmentName(0008,1040)").getDicomTag());
	        assertEquals(524416, conf.getConfigurationElement("InstitutionName(0008,0080)").getDicomTag());
	        assertEquals(524432, conf.getConfigurationElement("ReferringPhysiciansName(0008,0090)").getDicomTag());
	        assertEquals(524417, conf.getConfigurationElement("InstitutionAddress(0008,0081)").getDicomTag());
	        assertEquals(4194933, conf.getConfigurationElement("RequestAttributesSequence(0040,0275)").getDicomTag());
	        assertEquals(528725, conf.getConfigurationElement("ReferencedSOPInstanceUID(0008,1155)").getDicomTag());
	        assertEquals(4235556, conf.getConfigurationElement("UID(0040,A124)").getDicomTag());
	        assertEquals(1052704, conf.getConfigurationElement("PatientsSize(0010,1020)").getDicomTag());
	        assertEquals(2097664, conf.getConfigurationElement("SynchronizationFrameofReferenceUID(0020,0200)").getDicomTag());
	        assertEquals(524436, conf.getConfigurationElement("ReferringPhysiciansTelephoneNumbers(0008,0094)").getDicomTag());
	        assertEquals(805699620, conf.getConfigurationElement("ReferencedFrameofReferenceUID(3006,0024)").getDicomTag());
	        assertEquals(1052672, conf.getConfigurationElement("OtherPatientIds(0010,1000)").getDicomTag());
	        assertEquals(8913216, conf.getConfigurationElement("StorageMediaFile-setUID(0088,0140)").getDicomTag());
	        assertEquals(2113536, conf.getConfigurationElement("ImageComments(0020,4000)").getDicomTag());
	        assertEquals(2097234, conf.getConfigurationElement("FrameofReferenceUID(0020,0052)").getDicomTag());
	        assertEquals(528400, conf.getConfigurationElement("StationName(0008,1010)").getDicomTag());
	        assertEquals(2097168, conf.getConfigurationElement("StudyID(0020,0010)").getDicomTag());
	        assertEquals(1576960, conf.getConfigurationElement("DeviceSerialNumber(0018,1000)").getDicomTag());
	        assertEquals(1064960, conf.getConfigurationElement("PatientComments(0010,4000)").getDicomTag());
	        assertEquals(2097165, conf.getConfigurationElement("StudyInstanceUID(0020,000D)").getDicomTag());
	        assertEquals(532753, conf.getConfigurationElement("DerivationDescription(0008,2111)").getDicomTag());
	        assertEquals(528496, conf.getConfigurationElement("OperatorsName(0008,1070)").getDicomTag());
	        assertEquals(1052720, conf.getConfigurationElement("PatientsWeight(0010,1030)").getDicomTag());
	        assertEquals(1048640, conf.getConfigurationElement("PatientsSex(0010,0040)").getDicomTag());
	        assertEquals(528512, conf.getConfigurationElement("AdmittingDiagnosesDescription(0008,1080)").getDicomTag());
        }
        else {
            System.out.println("No config file: " + configFilePath);
    		System.out.println();
        }
	}

}
