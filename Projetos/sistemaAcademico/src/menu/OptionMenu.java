package menu;
import interfaces.menu.ISubMenuOption;

public record OptionMenu(String label, Runnable function) implements ISubMenuOption {

    @Override
    public void run() {
        function.run();
    }

}
