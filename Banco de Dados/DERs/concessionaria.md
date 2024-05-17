# Concessionária de Automóveis

- Crie um Diagrama de Entidade-Relacionamento (DER) para uma concessionária de automóveis. A concessionária vende tanto carros novos quanto usados, e opera uma oficina de serviços. Baseie seu design nas seguintes regras de negócio:

1. Um vendedor pode vender muitos carros, mas cada carro é vendido por apenas um vendedor.
2. Um cliente pode comprar muitos carros, mas cada carro é comprado por apenas um cliente.
3. Um vendedor escreve uma única fatura para cada carro que ele ou ela vende.
4. Um cliente recebe uma fatura para cada carro que ele ou ela compra.
5. Um cliente pode ir apenas para fazer o serviço de seu carro; ou seja, um cliente não precisa comprar um carro para ser classificado como cliente.
7. Quando um cliente leva um ou mais carros para reparo ou serviço, um ticket de serviço é escrito para cada carro.
8. A concessionária mantém um histórico de serviço para cada um dos carros atendidos. Os registros de serviço são referenciados pelo número de série do carro.
9. Um carro trazido para serviço pode ser trabalhado por muitos mecânicos, e cada mecânico pode trabalhar em muitos carros.
10. Um carro que é atendido pode ou não precisar de peças (por exemplo, ajustar um carburador ou limpar um bico injetor de combustível não requer a disponibilização de novas peças).

---

![Projeto Concessionária - DER](https://i.postimg.cc/x8HYQqtw/Diagrama-Carros.png)
