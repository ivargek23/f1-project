package hr.algebra.backendapi.utils;

import hr.algebra.backendapi.model.ErrorResponse;

import java.time.LocalDateTime;

public class MessageUtils {
    private MessageUtils() {}

    public static ErrorResponse createErrorResponse(int status, String message) {
        return new ErrorResponse(status, message, LocalDateTime.now());
    }
}
