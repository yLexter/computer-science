package interfaces;
import java.util.*;

public interface ISubMenu extends Runnable {
    List<ISubMenuOption> getOptions();
}

