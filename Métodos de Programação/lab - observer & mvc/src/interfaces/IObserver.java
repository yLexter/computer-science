package interfaces;

import entities.AcentoOnibus;
import entities.Onibus;

import java.util.List;

public interface IObserver {
    void update(Onibus onibus);
}