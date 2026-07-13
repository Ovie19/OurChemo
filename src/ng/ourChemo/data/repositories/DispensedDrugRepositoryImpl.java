package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrug;
import ng.ourChemo.data.models.Drug;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugRepositoryImpl implements DispensedDrugRepository {
    private int count;
    private final List<DispensedDrug> dispensedDrugs = new ArrayList<>();

    @Override
    public long count() {
        return dispensedDrugs.size();
    }

    @Override
    public DispensedDrug save(DispensedDrug dispensedDrug) {
        if (isNew(dispensedDrug)) saveNewDispensedDrug(dispensedDrug); else updateDispensedDrug(dispensedDrug);
        return dispensedDrug;
    }

    private void updateDispensedDrug(DispensedDrug dispensedDrug) {
        deleteById(dispensedDrug.getId());
        dispensedDrugs.add(dispensedDrug);
    }

    private void saveNewDispensedDrug(DispensedDrug dispensedDrug) {
        dispensedDrug.setId(++count);
        dispensedDrugs.add(dispensedDrug);
    }

    private boolean isNew(DispensedDrug dispensedDrug) {
        return dispensedDrug.getId() == 0;
    }

    @Override
    public DispensedDrug findById(int id) {
        for (DispensedDrug dispensedDrug:  dispensedDrugs)
            if (dispensedDrug.getId() == id)
                return dispensedDrug;

        return null;
    }

    @Override
    public void deleteById(int id) {
        DispensedDrug dispensedDrug = findById(id);
        delete(dispensedDrug);
    }

    @Override
    public void delete(DispensedDrug dispensedDrug) {
        dispensedDrugs.remove(dispensedDrug);
    }

    @Override
    public void deleteAll() {
        dispensedDrugs.clear();
    }

    @Override
    public boolean existsById(int id) {
        DispensedDrug dispensedDrug = findById(id);
        return dispensedDrug != null;
    }
}
