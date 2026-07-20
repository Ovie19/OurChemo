package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.Drug;

public interface DrugRepository {

    long count();

    Drug save(Drug drug);

    Drug findById(int id);

    void deleteById(int id);

    void delete(Drug drug);

    void deleteAll();

    boolean existsById(int id);

    Drug findByDrugName(String name);
}
