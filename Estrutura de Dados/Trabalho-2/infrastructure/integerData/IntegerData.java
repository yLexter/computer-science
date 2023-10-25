package infrastructure.integerData;

import interfaces.IDataProvider;

import java.util.*;
import java.util.function.Function;


public class IntegerData implements IDataProvider<Integer> {
    private record TypeVector (String name, Function<Integer, List<Integer>> function) {}

    //ToDo Mudar depois
    private static final int[] SIZE_VECTORS_FIRST_MASS_TEST = {
            100,
            500,
            1_500,
            2_000,
            3_000
    };

    private static final int[] SIZE_VECTORS_SECOND_MASS_TEST = {
           100,
           200
    };
    private static final TypeVector[] TYPES_VECTORS_FIRST_MASS_TEST = {
            new TypeVector("Vetor Ordem Crescente", Vector::generateAscendingVector),
            new TypeVector("Vetor Ordem Descrecente", Vector::generateDescendingVector),
            new TypeVector("Vetor com 10% Desordenado no Final", Vector::generateAscendingVectorWithRandomEnd),
            new TypeVector("Vetor com 10% Desordenado no Começo", Vector::generateAscendingVectorWithRandomStart)
    };

    private static final TypeVector[] TYPES_VECTORS_SECOND_MASS_TEST = {
            new TypeVector("Vetor Ordem Aleatória", Vector::generateRandomOrderVector),
            new TypeVector("Vetor Ordem Aleatória (Com Repetições)", Vector::generateRandomOrderVectorWithRepetiton),
    };
    private Map<String, List<List<Integer>>> SECOND_MASS_TEST;

    @Override
    public Map<String, List<List<Integer>>> getMassTestForInsert() {

        HashMap<String, List<List<Integer>>> mapData = new LinkedHashMap<>();

        for (TypeVector typeVector : TYPES_VECTORS_FIRST_MASS_TEST) {

            for (int size : SIZE_VECTORS_FIRST_MASS_TEST) {

                if (!mapData.containsKey(typeVector.name)) {
                    mapData.put(typeVector.name, new ArrayList<>());
                }

                mapData.get(typeVector.name).add(typeVector.function.apply(size));
            }

        }

        return mapData;
    }

    @Override
    public Map<String, List<List<Integer>>> getMassTestForSearch() {

        if (SECOND_MASS_TEST != null) {
            return SECOND_MASS_TEST;
        }

        LinkedHashMap<String, List<List<Integer>>> mapData = new LinkedHashMap<>();

        for (TypeVector typeVector : TYPES_VECTORS_SECOND_MASS_TEST) {

            for (int size : SIZE_VECTORS_SECOND_MASS_TEST) {

                if (!mapData.containsKey(typeVector.name)) {
                    mapData.put(typeVector.name, new ArrayList<>());
                }

                mapData.get(typeVector.name).add(typeVector.function.apply(size));
            }
        }

        this.SECOND_MASS_TEST = mapData;

        return mapData;
    }

}
