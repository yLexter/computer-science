# Análise de Árvores de Busca

Neste projeto, realizamos uma análise comparativa das implementações de árvores de busca, incluindo a Árvore de Busca Binária, Árvore AVL, Árvore Preto-Vermelho e Árvore Splay. O objetivo é estudar o desempenho dessas árvores sob várias condições.

## Requisitos

1. **Obtenção de Implementações**: Inicialmente, buscamos as implementações dessas árvores de busca em algum repositório. Preferencialmente, obtivemos as implementações do mesmo repositório para garantir consistência.

2. **Contagem de Rotações**: Nos códigos-fonte de cada implementação, modificamos-os para registrar a quantidade de rotações executadas por cada algoritmo durante as operações de manutenção do balanceamento das árvores. Contamos cada rotação isoladamente, especialmente quando várias rotações são necessárias para corrigir o balanceamento.

3. **Testes Diversificados**: Executamos os algoritmos com várias massas de testes, incluindo valores desordenados, valores ordenados em ordem crescente, valores ordenados em ordem decrescente, e valores parcialmente ordenados crescentemente (90%) com dados desordenados no final da amostra. Realizamos esses testes com 1.000.000, 2.000.000 e 3.000.000 de números não repetidos.

4. **Altura das Árvores**: Após a inserção de todos os valores, informamos a altura de cada árvore e a quantidade de rotações necessárias para construir as árvores binárias, quando aplicável.

5. **Testes com Números Repetidos**: Preparamos duas massas de teste, uma com números aleatórios não repetidos e outra com números repetidos. Para a segunda massa de testes, geramos números aleatórios no intervalo de 0 a 300.000 para garantir repetições.

6. **Operações de Busca**: Executamos operações de busca em cada algoritmo e em suas árvores resultantes usando as duas massas de teste mencionadas acima. Computamos o tempo total necessário para buscar todos os valores pesquisados.

7. **Relatório de Análise**: Finalmente, apresentamos um relatório analisando as alturas das árvores obtidas, as quantidades de rotações necessárias e o tempo necessário para a busca em cada árvore gerada. Isso é feito com base nas respectivas massas de teste do item 3, quando pesquisados os valores das massas de teste do item 5.

## Conclusão

Esta análise oferece insights valiosos sobre o desempenho e a eficiência das árvores de busca em diferentes cenários e pode ser uma referência útil para tomada de decisões em relação à escolha de algoritmos de busca em projetos futuros.
