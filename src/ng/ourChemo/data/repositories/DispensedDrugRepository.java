package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrug;

public interface DispensedDrugRepository {

    long count();

    DispensedDrug save(DispensedDrug dispensedDrug);

    DispensedDrug findById(int id);

    void deleteById(int id);

    void delete(DispensedDrug dispensedDrug);

    void deleteAll();

    boolean existsById(int id);
}
