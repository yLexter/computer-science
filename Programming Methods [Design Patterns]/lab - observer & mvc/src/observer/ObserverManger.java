package observer;

import entities.AcentoOnibus;
import entities.Onibus;
import interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;

public class ObserverManger {

    private List<IObserver> observers;

    public ObserverManger() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers(Onibus onibus) {
        for (IObserver observer : this.observers) {
            observer.update(onibus);
        }
    }

  

}