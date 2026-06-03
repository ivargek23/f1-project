package hr.algebra.backendapi.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String field, Object value) {
      super(field + " " + value + " already exists");
    }
}
