# Lab-8: Adapter

## Descrição

Este projeto demonstra a implementação do Padrão de Projeto Adapter para permitir que uma classe cliente manipule objetos das interfaces `Map` e `List` através da interface estabelecida por `List` e `Map`, respectivamente. Esta adaptação é feita nos dois sentidos, conforme ilustrado nos slides da aula.

### Métodos a serem Considerados

#### Interface Map
- `clear`
- `containsKey`
- `containsValue`
- `equals`
- `get`
- `isEmpty`
- `put`
- `remove`
- `size`
- `values`

#### Interface List
- `add`
- `clear`
- `contains`
- `equals`
- `get`
- `isEmpty`
- `iterator`
- `remove`
- `size`
- `toArray`

## Implementação

### List para Map Adapter

Para adaptar uma `List` para a interface `Map`, criamos uma classe `ListToMapAdapter` que implementa os métodos necessários da interface `Map`.

### Map para List Adapter

Para adaptar um `Map` para a interface `List`, criamos uma classe `MapToListAdapter` que implementa os métodos necessários da interface `List`.
