# ExercicioPedidos

Este projeto mostra um sistema simples de pedido em Java usando orientacao a objetos.

Neste exercicio aparecem tres ideias principais:

- composicao entre objetos
- uso de `enum` para status do pedido
- sobrescrita de `toString()` para montar o resumo final

## O que o projeto representa

O programa simula o cadastro de um pedido feito por um cliente.

Cada pedido possui:

- um momento de criacao
- um status
- um cliente
- uma lista de itens

Cada item do pedido possui:

- um produto
- um preco
- uma quantidade

Ao final, o sistema monta um resumo com todos os dados do pedido e o valor total.

## Estrutura do projeto

```text
revisoes/revisoes/ExercicioPedidos/
|- application/
|  `- Program.java
|- entities/
|  |- Client.java
|  |- Order.java
|  |- OrderItem.java
|  |- Product.java
|  `- enums/
|     `- OrderStatus.java
`- README.md
```

## Papel de cada classe

### `Program`

Arquivo: `revisoes/revisoes/ExercicioPedidos/application/Program.java`

Responsavel por:

- ler os dados no console
- criar o cliente
- criar o pedido
- criar os produtos e itens do pedido
- adicionar os itens ao pedido
- imprimir o resumo final

### `Client`

Arquivo: `revisoes/revisoes/ExercicioPedidos/entities/Client.java`

Representa o cliente do pedido.

Atributos:

- `name`
- `email`
- `birthDate`

O metodo `toString()` dessa classe devolve o cliente neste formato:

```text
Alex Green (15/03/1985) - alex@gmail.com
```

### `Product`

Arquivo: `revisoes/revisoes/ExercicioPedidos/entities/Product.java`

Representa o produto comprado.

Atributos:

- `name`
- `price`

### `OrderItem`

Arquivo: `revisoes/revisoes/ExercicioPedidos/entities/OrderItem.java`

Representa um item dentro do pedido.

Atributos:

- `quantity`
- `price`
- `product`

Metodo importante:

- `subTotal()`: calcula `price * quantity`

O `toString()` dessa classe monta a linha de cada item do resumo.

Exemplo:

```text
TV, $1000.00, Quantity: 1, Subtotal: $1000.00
```

### `Order`

Arquivo: `revisoes/revisoes/ExercicioPedidos/entities/Order.java`

E a classe principal do exercicio.

Atributos:

- `moment`
- `status`
- `client`
- `items`

Metodos importantes:

- `addItem(OrderItem item)`: adiciona um item ao pedido
- `removeItem(OrderItem item)`: remove um item
- `total()`: soma o subtotal de todos os itens
- `toString()`: monta o resumo completo do pedido

### `OrderStatus`

Arquivo: `revisoes/revisoes/ExercicioPedidos/entities/enums/OrderStatus.java`

Enum usado para representar os estados do pedido.

No fluxo principal do programa, a entrada esperada e:

- `PENDING_PAYMENT`
- `PROCESSING`
- `SHIPPED`
- `DELIVERED`

## Como a composicao aparece no codigo

Este projeto usa composicao em mais de um ponto.

No `Order`:

```text
private Client client;
private List<OrderItem> items = new ArrayList<>();
```

No `OrderItem`:

```text
private Product product;
```

Isso significa que:

- um pedido tem um cliente
- um pedido tem varios itens
- um item tem um produto

## Como o `toString()` e usado

O resumo final do pedido e montado no `Order`.

Quando o programa executa:

```text
System.out.println(order);
```

o Java chama automaticamente o `toString()` da classe `Order`.

Esse metodo usa `StringBuilder` para montar uma saida com varias linhas, incluindo:

- momento do pedido
- status
- dados do cliente
- lista de itens
- total final

## Pacotes do projeto

As classes deste modulo usam pacotes que comecam com o prefixo:

```text
ExercicioPedidos
```

Na pratica, os arquivos estao organizados em pacotes como:

- `ExercicioPedidos.application`
- `ExercicioPedidos.entities`
- `ExercicioPedidos.entities.enums`

Por isso, o comando de execucao precisa chamar:

```text
ExercicioPedidos.application.Program
```

## Fluxo de execucao

O programa segue esta ordem:

1. le os dados do cliente
2. cria um `Client`
3. le o status do pedido
4. cria um `Order` com a data atual
5. pergunta quantos itens existem
6. cria cada `Product`
7. cria cada `OrderItem`
8. adiciona os itens ao pedido
9. imprime o resumo final

## Exemplo pratico

Entrada:

```text
Enter client data:
Name: Alex Green
Email: alex@gmail.com
Birth date (DD/MM/YYYY): 15/03/1985
Enter order data:
Status: PROCESSING
How many items to this order? 2
Enter #1 item data:
Product name: TV
Product price: 1000.00
Quantity: 1
Enter #2 item data:
Product name: Mouse
Product price: 40.00
Quantity: 2
```

Saida esperada:

```text
ORDER SUMMARY:
Order moment: 20/04/2018 11:25:09
Order status: PROCESSING
Client: Alex Green (15/03/1985) - alex@gmail.com
Order items:
TV, $1000.00, Quantity: 1, Subtotal: $1000.00
Mouse, $40.00, Quantity: 2, Subtotal: $80.00
Total price: $1080.00
```

O valor exato de `Order moment` muda conforme a data e hora da execucao, porque o pedido e criado com `new Date()`.

## O que foi praticado neste exercicio

Este modulo pratica:

- criacao de classes e objetos
- relacionamento entre objetos
- listas com `ArrayList`
- uso de `enum`
- leitura com `Scanner`
- manipulacao de datas com `Date` e `SimpleDateFormat`
- sobrescrita de `toString()`
- montagem de texto com `StringBuilder`
- calculo de subtotal e total do pedido

## Como executar

A partir da raiz do repositorio:

```bash
javac -d out $(find revisoes/revisoes/ExercicioPedidos -name "*.java")
java -cp out ExercicioPedidos.application.Program
```

## Resumo final

Se voce quiser resumir a ideia deste projeto em uma frase:

> Um pedido possui cliente, status e varios itens, e o sistema monta um resumo final calculando o total com base nos subtotais de cada item.
