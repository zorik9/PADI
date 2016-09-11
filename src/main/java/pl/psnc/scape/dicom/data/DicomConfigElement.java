package pl.psnc.scape.dicom.data;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository
public class DicomConfigElement {
    private int dicomTag;
    private String configTag;
    private boolean flag;
}
