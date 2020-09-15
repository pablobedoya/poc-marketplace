package poc.marketplace.model;

public enum ProductStatus {

    PENDING_REGISTRATION(1),
    ACTIVE(2),
    PENDING_DELETION(3),
    DELETED(4);

    private int id;

    ProductStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
