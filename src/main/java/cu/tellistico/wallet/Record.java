package cu.tellistico.wallet;

/**
 * Created by roberto on 18/06/2017.
 */
public class Record {

    double amount;
    String category;
    String subcategory;
    String account;
    String date;
    int hours;
    int minutes;
    String paymentType;
    int type;
    String notes;

    public Record(double amount, String category, String subcategory, String account, String date, int hours, int minutes, String paymentType, int type, String notes) {
        this.amount = amount;
        this.category = category;
        this.subcategory = subcategory;
        this.account = account;
        this.date = date;
        this.hours = hours;
        this.minutes = minutes;
        this.paymentType = paymentType;
        this.type = type;
        this.notes = notes;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getAccount() {
        return account;
    }

    public String getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public int getType() {
        return type;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Record{" +
                "amount=" + amount +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", account='" + account + '\'' +
                ", date='" + date + '\'' +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", paymentType='" + paymentType + '\'' +
                ", type=" + type +
                ", notes='" + notes + '\'' +
                '}';
    }
}
