package source.enums;


public enum ApplyStatus {
    
    PENDING_VERIFICATION("Pending Verification"),
    APPROVED_PENDING_TRANSACTION("Approved, Pending Transaction"),
    TRANSACTION_SUCCESS("Transaction Success"),
    REJECTED("Rejected");


    // Fields
    public String name;

    // Ctor
    private ApplyStatus(String name) {
        this.name = name;
    }

    // Methods
    @Override 
    public String toString() {
        return this.name;
    }
}