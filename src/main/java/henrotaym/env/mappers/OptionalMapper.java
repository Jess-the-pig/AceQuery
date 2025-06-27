package henrotaym.env.mappers;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class OptionalMapper {

    public static <T, R> Optional<R> mapOptional(Optional<T> optional, Function<T, R> mapper) {
        return optional.map(mapper);
    }
}
