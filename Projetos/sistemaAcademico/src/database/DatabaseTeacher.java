package database;

import general.AcademicSystem;
import general.CollegeClass;
import general.RegisterClass;
import general.Teacher;
import utils.Global;

public class DatabaseTeacher extends DatabaseEmployee<Teacher> {

    public void saveCall(RegisterClass registerClass) {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        CollegeClass collegeClass = academicSystem.db.collegeClass.findById(registerClass.getClassId());

        collegeClass.getRegisterClasses().add(registerClass);

        academicSystem.db.collegeClass.update(registerClass.getClassId(), collegeClass);
    }

}
