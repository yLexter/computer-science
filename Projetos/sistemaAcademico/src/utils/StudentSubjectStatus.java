package utils;

public enum StudentSubjectStatus {

    PENDING("Pendente"),
    APPROVED("Aprovado"),
    DISAPPROVED("Reprovado");

    private final String description;

    StudentSubjectStatus(String description) {
        this.description = description;
    }

    public String get() {
        return description;
    }

}

