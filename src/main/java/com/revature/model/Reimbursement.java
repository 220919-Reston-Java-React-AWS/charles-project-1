
package com.revature.model;

        import java.util.Objects;

public class Reimbursement {

    private int id;
    private String amount;
    private String last_name; // 0 default
    private String description;
    private String status; // 0 default

    public Reimbursement() {
    }

    public Reimbursement(int id, String amount, String last_name, String description, String status) {
        this.id = id;
        this.amount = amount;
        this.last_name = last_name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(int graderId) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && last_name == that.last_name && description == that.description && status == that.status && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, last_name, description, status);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", last_name=" + last_name +
                ", description=" + description +
                ", status=" + status +
                '}';
    }
}

