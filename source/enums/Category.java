package source.enums;


public enum Category {
    
    EDUCATION("Education"),
    MEDICAL("Medical"),
    DISASTER("Disaster"),
    HOUSEHOLD("Household"),
    OTHER("Other");


    // Fields
    public String name;

    // Ctor
    private Category(String name) {
        this.name = name;
    }

    // Methods
    @Override 
    public String toString() {
        return this.name;
    }
}