package Assigment2;

import java.util.Date;

class Electronics extends Good {

    protected ElectronicsCategory category = ElectronicsCategory.None;

    public Electronics(String name, Producer producer, Date productionDate, float price, long warrantyTerm) {
        super(name, producer, productionDate, price, warrantyTerm);
    }

    public Electronics(String name, Producer producer, Date productionDate, float price, long warrantyTerm, ElectronicsCategory category) {
        this(name, producer, productionDate, price, warrantyTerm);
        this.category = category;
    }

    public Electronics() {
        super();
    }


    @Override
    public String toString() {
        return "Electronics{" +
                "name='" + name + '\'' +
                ", producer=" + producer +
                ", productionDate=" + productionDate +
                ", price=" + price +
                ", warrantyTerm='" + expireDays + '\'' +
                ", category=" + category +
                '}';
    }
}
