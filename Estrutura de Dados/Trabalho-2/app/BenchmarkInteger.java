package app;

import interfaces.IBenchmark;
import useCases.BenchmarkTree;
import useCases.Vector;

import java.util.*;
import java.util.function.Function;

public class BenchmarkInteger implements IBenchmark<Integer> {
    private record TypeVector (String name, Function<Integer, List<Integer>> function) {}
    private static final int[] SIZE_VECTORS = {
            100_000,
            500_000,
            1_500_000,
            2_000_000,
            3_000_000
    };

    private static TypeVector[] TYPES_VECTORS = {
            new TypeVector("Vetor Ordem Crescente", Vector::generateAscendingVector),
            new TypeVector("Vetor Ordem Descrecente", Vector::generateDescendingVector),
            new TypeVector("Vetor Ordem Aleatória", Vector::generateRandomOrderVector),
            new TypeVector("Vetor Ordem Aleatória (Com Repetições)", Vector::generateRandomOrderVectorWithRepetiton),
            new TypeVector("Vetor com 10% Desordenado no Final", Vector::generateAscendingVectorWithRandomEnd),
            new TypeVector("Vetor com 10% Desordenado no Começo", Vector::generateAscendingVectorWithRandomStart)
    };

    public Map<String, List<List<Integer>>> getData() {

        HashMap<String, List<List<Integer>>> mapData = new LinkedHashMap<>();

        for (TypeVector typeVector : TYPES_VECTORS) {

            for (int size : SIZE_VECTORS) {

                if (mapData.get(typeVector.name) == null) {
                    mapData.put(typeVector.name, new ArrayList<>());
                }

                mapData.get(typeVector.name).add(typeVector.function.apply(size));
            }
            
        }

        return mapData;
    }


}
