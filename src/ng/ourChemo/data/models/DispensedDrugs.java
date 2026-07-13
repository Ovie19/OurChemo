package ng.ourChemo.data.models;

import java.time.LocalDateTime;
import java.util.List;

public class DispensedDrugs {
    private int id;
    private List<DispensedDrug> dispensedDrugs;
    private User dispensedBy;
    private LocalDateTime dispensedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DispensedDrug> getDispensedDrugs() {
        return dispensedDrugs;
    }

    public void setDispensedDrugs(List<DispensedDrug> dispensedDrugs) {
        this.dispensedDrugs = dispensedDrugs;
    }

    public User getDispensedBy() {
        return dispensedBy;
    }

    public void setDispensedBy(User dispensedBy) {
        this.dispensedBy = dispensedBy;
    }

    public LocalDateTime getDispensedDate() {
        return dispensedDate;
    }

    public void setDispensedDate(LocalDateTime dispensedDate) {
        this.dispensedDate = dispensedDate;
    }
}
