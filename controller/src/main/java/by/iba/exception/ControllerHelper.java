package by.iba.exception;

import org.springframework.validation.BindingResult;

public final class ControllerHelper {

    public static void checkBindingResultAndThrowExceptionIfInvalid(BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result.getFieldErrors());
        }
    }

    private ControllerHelper() {
    }
}
