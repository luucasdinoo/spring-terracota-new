package com.terracota.customer;

import com.terracota.validation.Error;
import com.terracota.validation.ValidationHandler;
import com.terracota.validation.Validator;

public class CustomerValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;
    private final Customer customer;

    protected CustomerValidator(final Customer aCustomer, final ValidationHandler handler) {
        super(handler);
        this.customer = aCustomer;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkEmailConstraints();
    }

    private void checkNameConstraints() {
        final String name = this.customer.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'Name' should not be null"));
            return;
        }
        if (name.isBlank()){
            this.validationHandler().append(new Error("'Name' should not be empty"));
            return;
        }
        if (name.trim().length() > NAME_MAX_LENGTH || name.trim().length() < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'Name' must be between 3 and 255 characters"));
        }
    }

    private void checkEmailConstraints() {
        final String email = this.customer.getEmail();
        if (email == null) {
            this.validationHandler().append(new Error("'Email' should not be null"));
            return;
        }
        if (email.isBlank()) {
            this.validationHandler().append(new Error("'Email' should not be empty"));
            return;
        }
        if (!isValidEmail(email)) {
            this.validationHandler().append(new Error("'Email' has an invalid format"));
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
