package henrotaym.env.configurations;

import java.util.Optional;

public class PlayerFilter {
    private Optional<String> name;
    private Optional<String> nationality;
    private Optional<String> sortBy;
    private Optional<String> sortOrder;

    public PlayerFilter(String name, String nationality, String sortBy, String sortOrder) {
        this.name = Optional.ofNullable(name);
        this.nationality = Optional.ofNullable(nationality);
        this.sortBy = Optional.ofNullable(sortBy);
        this.sortOrder = Optional.ofNullable(sortOrder);
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<String> getNationality() {
        return nationality;
    }

    public Optional<String> getSortBy() {
        return sortBy;
    }

    public Optional<String> getSortOrder() {
        return sortOrder;
    }
}