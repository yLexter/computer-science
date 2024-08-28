package infrastructure.integerData;

import interfaces.IDataProvider;

import java.util.*;
import java.util.function.Function;


public class IntegerData implements IDataProvider<Integer> {
    private record TypeVector (String name, Function<Integer, List<Integer>> function) {}

    //ToDo Mudar depois
    private static final int[] SIZE_VECTORS_FIRST_MASS_TEST = {
            1_000_000,
            2_000_000,
            3_000_000
    };

    private static final int[] SIZE_VECTORS_SECOND_MASS_TEST = {
           1_000_000
    };
    private static final TypeVector[] TYPES_VECTORS_FIRST_MASS_TEST = {
            new TypeVector("Vetor Ordem Crescente", Vector::generateAscendingVector),
            new TypeVector("Vetor Ordem Descrecente", Vector::generateDescendingVector),
            new TypeVector("Vetor Ordem Aleatória", Vector::generateRandomOrderVector),
            new TypeVector("Vetor Ordenado com 10% Desordenado no Final", Vector::generateAscendingVectorWithRandomEnd),
            new TypeVector("Vetor Ordenado com 10% Desordenado no Começo", Vector::generateAscendingVectorWithRandomStart)
    };

    private static final TypeVector[] TYPES_VECTORS_SECOND_MASS_TEST = {
            new TypeVector("Vetor Ordem Aleatória (Sem Repetições)", Vector::generateRandomOrderVectorWithoutRepetition),
            new TypeVector("Vetor Ordem Aleatória (Com Repetições)", Vector::generateRandomOrderVectorWithRepetiton),
    };

    private Map<String, List<List<Integer>>> FIRST_MASS_TEST;
    private Map<String, List<List<Integer>>> SECOND_MASS_TEST;

    @Override
    public Map<String, List<List<Integer>>> getMassTestForInsert() {

        if (FIRST_MASS_TEST == null) {
            FIRST_MASS_TEST =  generateVectorTypeMap(TYPES_VECTORS_FIRST_MASS_TEST, SIZE_VECTORS_FIRST_MASS_TEST, "Inserção");;
        }

        return FIRST_MASS_TEST;
    }

    @Override
    public Map<String, List<List<Integer>>> getMassTestForSearch() {

        if (SECOND_MASS_TEST == null) {
            SECOND_MASS_TEST =  generateVectorTypeMap(TYPES_VECTORS_SECOND_MASS_TEST, SIZE_VECTORS_SECOND_MASS_TEST, "Busca");;
        }

        return SECOND_MASS_TEST;
    }

    private Map<String, List<List<Integer>>> generateVectorTypeMap(TypeVector[] typeVectors, int[] sizes, String operation) {

        LinkedHashMap<String, List<List<Integer>>> mapData = new LinkedHashMap<>();

        System.out.println("-- Gerado Massa de Teste para: " + operation);

        for (TypeVector typeVector : typeVectors) {

            for (int size : sizes) {

                if (!mapData.containsKey(typeVector.name)) {
                    mapData.put(typeVector.name, new ArrayList<>());
                }

                mapData.get(typeVector.name).add(typeVector.function.apply(size));
                System.out.printf("%s de tamanho %d gerado com sucesso\n", typeVector.name, size);
            }
        }

        System.out.println("\n");

        return mapData;
    }
}
