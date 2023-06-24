package database.DatabaseMemory;

import general.GeneralInformation;
import interfaces.database.IDatabaseGeneralInformation;

public class DatabaseGeneralInformation extends DatabaseBase<GeneralInformation> implements IDatabaseGeneralInformation {

    public final GeneralInformation data;
    public DatabaseGeneralInformation(GeneralInformation data) {
        this.data = data;
    }

    @Override
    public GeneralInformation findById(String id) {
        return null;
    }

    @Override
    public GeneralInformation getData() {
        return data;
    }


}
