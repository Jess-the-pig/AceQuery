package henrotaym.env.exceptions;

public class PlantIsDeadException extends RuntimeException {

    public PlantIsDeadException() {
        super("Cette plante est morte et ne peut pas être modifiée.");
    }

    public PlantIsDeadException(String message) {
        super(message);
    }
}
