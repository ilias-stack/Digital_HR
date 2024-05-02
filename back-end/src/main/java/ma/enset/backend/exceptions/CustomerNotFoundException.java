package ma.enset.backend.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String customerNotFound) {
        super(customerNotFound);
    }
}
