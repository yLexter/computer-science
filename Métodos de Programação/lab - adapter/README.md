#Lab-8: Adapter

## Description

This project demonstrates the implementation of the Adapter Design Pattern to allow a client class to manipulate objects from the `Map` and `List` interfaces through the interface established by `List` and `Map`, respectively. This adaptation goes both ways, as illustrated in the class slides.

### Methods to Consider

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

## Implementation

### List to Map Adapter

To adapt a `List` to the `Map` interface, we create a `ListToMapAdapter` class that implements the necessary methods of the `Map` interface.

### Map to List Adapter

To adapt a `Map` to the `List` interface, we create a `MapToListAdapter` class that implements the necessary methods of the `List` interface.