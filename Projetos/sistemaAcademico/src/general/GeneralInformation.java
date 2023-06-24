package general;

public class GeneralInformation {

    private String course;
    private int totalAbsemcesPerClass;
    private boolean isRegitrationPeriod;
    private String version;
    private int hourPerClass;

    public GeneralInformation(String course, int totalAbsemcesPerClass, boolean isRegitrationPeriod, String version, int hourPerClass) {
        this.course = course;
        this.totalAbsemcesPerClass = totalAbsemcesPerClass;
        this.isRegitrationPeriod = isRegitrationPeriod;
        this.version = version;
        this.hourPerClass = hourPerClass;
    }

    public GeneralInformation(GeneralInformation origin) {
        this.course = origin.getCourse();
        this.totalAbsemcesPerClass = origin.getTotalAbsemcesPerClass();
        this.isRegitrationPeriod = origin.isRegitrationPeriod();
        this.version = origin.getVersion();
        this.hourPerClass = origin.hourPerClass;
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
