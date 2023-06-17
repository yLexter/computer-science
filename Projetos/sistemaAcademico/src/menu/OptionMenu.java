package menu;
import interfaces.ISubMenuOption;

public record OptionMenu(String label, Runnable function) implements ISubMenuOption {

    @Override
    public void run() {
        function.run();
    }

}
