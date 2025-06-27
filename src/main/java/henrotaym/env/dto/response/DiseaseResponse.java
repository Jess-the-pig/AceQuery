package henrotaym.env.dto.response;

import henrotaym.env.enums.DiseaseName;

public class DiseaseResponse {

    private String name;

    private DiseaseName type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiseaseName getType() {
        return type;
    }

    public void setType(DiseaseName type) {
        this.type = type;
    }
}
