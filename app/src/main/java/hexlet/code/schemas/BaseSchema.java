package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    public static final String REQUIRED = "required";
    private final Map<String, Predicate> validations = new HashMap<>();
    private boolean isRequired = false;


    public final void addValidation(String name, Predicate validation) {
        validations.put(name, validation);
    }

    public BaseSchema required() {
        isRequired = true;
        return this;
    }

    public final boolean isValid(Object data) {
        if (!isRequired) {
            Predicate validation = validations.get(REQUIRED);
            if (!validation.test(data)) {
                return true;
            }
        }
        return validations.values().stream().allMatch(value -> value.test(data));
    }
}
