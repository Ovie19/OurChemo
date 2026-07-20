package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugRepositoryImpl implements DrugRepository {

    private int count;
    private static final List<Drug> drugs = new ArrayList<>();

    @Override
    public long count() {
        return drugs.size();
    }

    @Override
    public Drug save(Drug drug) {
        if (isNew(drug)) saveNewDrug(drug); else updateDrug(drug);
        return drug;
    }

    private void updateDrug(Drug drug) {
        deleteById(drug.getId());
        drugs.add(drug);
    }

    private void saveNewDrug(Drug drug) {
        drug.setId(++count);
        drugs.add(drug);
    }

    private boolean isNew(Drug drug) {
        return drug.getId() == 0;
    }

    @Override
    public Drug findById(int id) {
        for (Drug drug : drugs)
            if (drug.getId() == id)
                return drug;

        return null;
    }

    @Override
    public void deleteById(int id) {
        Drug drug = findById(id);
        delete(drug);
    }

    @Override
    public void delete(Drug drug) {
        drugs.remove(drug);
    }

    @Override
    public void deleteAll() {
        drugs.clear();
    }

    @Override
    public boolean existsById(int id) {
        Drug drug = findById(id);
        return drug != null;
    }

    @Override
    public Drug findByDrugNameAndBrand(String name, String brand) {
        for (Drug drug : drugs) {
            boolean isDrugFound = drug.getName().equals(name) && drug.getBrand().equals(brand);
            if (isDrugFound) return drug;
        }
        return null;
    }
}
