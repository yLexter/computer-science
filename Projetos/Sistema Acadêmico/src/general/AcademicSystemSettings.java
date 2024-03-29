package general;

public class AcademicSystemSettings {

    private String course;
    private int totalAbsemcesPerClass;
    private boolean isRegitrationPeriod;
    private String version;
    private int hourPerClass;
    private int minimumAverage;
    private int maximumLessonsPerSchedule;

    public AcademicSystemSettings(String course, int totalAbsemcesPerClass, boolean isRegitrationPeriod, String version, int hourPerClass, int minimumAverage, int maximumLessonsPerSchedule) {
        this.course = course;
        this.totalAbsemcesPerClass = totalAbsemcesPerClass;
        this.isRegitrationPeriod = isRegitrationPeriod;
        this.version = version;
        this.hourPerClass = hourPerClass;
        this.minimumAverage = minimumAverage;
        this.maximumLessonsPerSchedule = maximumLessonsPerSchedule;
    }

    public int getMinimumAverage() {
        return minimumAverage;
    }

    public void setMinimumAverage(int minimumAverage) {
        this.minimumAverage = minimumAverage;
    }

    public int getMaximumLessonsPerSchedule() {
        return maximumLessonsPerSchedule;
    }

    public void setMaximumLessonsPerSchedule(int maximumLessonsPerSchedule) {
        this.maximumLessonsPerSchedule = maximumLessonsPerSchedule;
    }

    public int getHourPerClass() {
        return hourPerClass;
    }

    public void setHourPerClass(int hourPerClass) {
        this.hourPerClass = hourPerClass;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getTotalAbsemcesPerClass() {
        return totalAbsemcesPerClass;
    }

    public void setTotalAbsemcesPerClass(int totalAbsemcesPerClass) {
        this.totalAbsemcesPerClass = totalAbsemcesPerClass;
    }

    public boolean isRegitrationPeriod() {
        return isRegitrationPeriod;
    }

    public void setRegitrationPeriod(boolean regitrationPeriod) {
        isRegitrationPeriod = regitrationPeriod;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}
