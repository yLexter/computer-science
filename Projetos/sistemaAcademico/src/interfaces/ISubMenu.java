package interfaces;
import java.util.*;

public interface ISubMenu extends Runnable {
    Map<Integer, ISubMenuOption> getOptions();
}

