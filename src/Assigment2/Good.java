package Assigment2;

import java.util.Date;

abstract class Good {
    protected String name;
    protected Producer producer;
    protected Date productionDate;
    protected float price;
    protected long expireDays;

    Good(String name, Producer producer, Date productionDate, float price, long expireDays) {
        this.name = name;
        this.producer = producer;
        this.productionDate = productionDate;
        this.price = price;
        this.expireDays = expireDays;
    }

    protected Good() {

    }

    public Date getExpireDate() {
        return new Date(productionDate.getTime() + expireDays * 86400 * 1000);
    }

    public boolean checkIfExpired() {
        return getExpireDate().getTime() - new Date().getTime() < 0;
    }
}
