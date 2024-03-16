package labs;

import interfaces.menu.IResponseLab;

import java.util.List;

public abstract class BaseLab {

    public record ResponseLab(String label, Runnable function) implements IResponseLab {
        @Override
        public void run() {
            function.run();
        }
    }

    public abstract List<IResponseLab> getOptions();

    public void run() {
        var options = getOptions();

        for (IResponseLab option : options) {
            System.out.println("-".repeat(50));
            System.out.printf("Pergunta: %s\n\n", option.label());
            option.run();
            System.out.println();
        }

        System.out.println("-".repeat(50));
        System.out.println();
        System.out.println();
    }
}
