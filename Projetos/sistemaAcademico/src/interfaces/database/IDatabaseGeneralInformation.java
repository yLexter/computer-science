package interfaces.database;

import general.GeneralInformation;

public interface IDatabaseGeneralInformation extends IDatabaseBase<GeneralInformation> {
    GeneralInformation getData();
}
