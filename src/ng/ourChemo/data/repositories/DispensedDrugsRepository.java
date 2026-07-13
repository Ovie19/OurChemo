package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrugs;

public interface DispensedDrugsRepository {

    long count();

    DispensedDrugs save(DispensedDrugs dispensedDrugs);

    DispensedDrugs findById(int id);

    void deleteById(int id);

    void delete(DispensedDrugs dispensedDrugs);

    void deleteAll();

    boolean existsById(int id);
}
