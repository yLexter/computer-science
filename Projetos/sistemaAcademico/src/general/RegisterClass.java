package general;

import java.time.LocalDate;
import java.util.List;

public class RegisterClass {

    public record StudentCallLog (String id, boolean isMissed) {
        @Override
        public String id() {
            return id;
        }

        @Override
        public boolean isMissed() {
            return isMissed;
        }
    }

    private String description;

    private LocalDate date;

    private List<StudentCallLog> listCall;

    public RegisterClass(String description, LocalDate date, List<StudentCallLog> listCall) {
        this.description = description;
        this.date = date;
        this.listCall = listCall;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<StudentCallLog> getListCall() {
        return listCall;
    }

    public void setListCall(List<StudentCallLog> listCall) {
        this.listCall = listCall;
    }
}
