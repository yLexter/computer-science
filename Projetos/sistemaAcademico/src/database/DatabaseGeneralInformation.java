package database;

import general.GeneralInformation;
import interfaces.IModelDatabase;
import java.util.List;
import java.util.function.Predicate;

public class DatabaseGeneralInformation extends DatabaseBase<GeneralInformation> {

    public final GeneralInformation data;
    public DatabaseGeneralInformation(GeneralInformation data) {
        this.data = data;
    }

    @Override
    public GeneralInformation findById(String id) {
        return data;
    }
    

}
