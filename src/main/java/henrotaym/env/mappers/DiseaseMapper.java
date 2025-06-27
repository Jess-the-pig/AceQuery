package henrotaym.env.mappers;

import henrotaym.env.dto.request.DiseaseRequest;
import henrotaym.env.dto.response.DiseaseResponse;
import henrotaym.env.entities.Disease;

import org.springframework.stereotype.Component;

@Component
public class DiseaseMapper {

    public Disease toEntity(DiseaseRequest diseaseRequest) {
        Disease disease = new Disease();
        disease.setName(diseaseRequest.getName());
        disease.setType(diseaseRequest.getType());

        return disease;
    }

    public DiseaseResponse fromEntity(Disease disease) {
        DiseaseResponse diseaseResponse = new DiseaseResponse();
        diseaseResponse.setName(disease.getName());
        diseaseResponse.setType(disease.getType());
        return diseaseResponse;
    }
}
