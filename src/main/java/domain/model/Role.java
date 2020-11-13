package domain.model;

public enum Role {
    ADMINISTRATOR("administrator"),
    USER("user");

    private String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
