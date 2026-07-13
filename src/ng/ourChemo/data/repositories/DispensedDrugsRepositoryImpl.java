package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrugs;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugsRepositoryImpl implements DispensedDrugsRepository {

    private int count;
    private final List<DispensedDrugs> dispensedDrugs = new ArrayList<>();

    @Override
    public long count() {
        return dispensedDrugs.size();
    }

    @Override
    public DispensedDrugs save(DispensedDrugs dispensedDrug) {
        if (isNew(dispensedDrug)) saveNewDispensedDrugs(dispensedDrug); else updateDispensedDrugs(dispensedDrug);
        return dispensedDrug;
    }

    private void updateDispensedDrugs(DispensedDrugs dispensedDrug) {
        deleteById(dispensedDrug.getId());
        dispensedDrugs.add(dispensedDrug);
    }

    private void saveNewDispensedDrugs(DispensedDrugs dispensedDrug) {
        dispensedDrug.setId(++count);
        dispensedDrugs.add(dispensedDrug);
    }

    private boolean isNew(DispensedDrugs dispensedDrug) {
        return dispensedDrug.getId() == 0;
    }

    @Override
    public DispensedDrugs findById(int id) {
        for (DispensedDrugs dispensedDrug:  dispensedDrugs)
            if (dispensedDrug.getId() == id)
                return dispensedDrug;

        return null;
    }

    @Override
    public void deleteById(int id) {
        DispensedDrugs dispensedDrug = findById(id);
        delete(dispensedDrug);
    }

    @Override
    public void delete(DispensedDrugs dispensedDrug) {
        dispensedDrugs.remove(dispensedDrug);
    }

    @Override
    public void deleteAll() {
        dispensedDrugs.clear();
    }

    @Override
    public boolean existsById(int id) {
        DispensedDrugs dispensedDrug = findById(id);
        return dispensedDrug != null;
    }
}
