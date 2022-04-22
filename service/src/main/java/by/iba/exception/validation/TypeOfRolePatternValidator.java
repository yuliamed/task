package by.iba.exception.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Stream.of;

public class TypeOfRolePatternValidator implements ConstraintValidator<TypeOfRolePattern, String> {
    private List<String> acceptedValues;

    @Override
    public void initialize(TypeOfRolePattern pattern) {
        acceptedValues = of(pattern.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value);
    }

}
