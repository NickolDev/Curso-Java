# Enumeracoes

Este projeto mostra o conceito de enumeracoes em orientacao a objetos usando Java.

## O que e uma enumeracao

Uma enumeracao, ou `enum`, e um tipo especial usado quando um atributo so pode assumir um conjunto fixo de valores.

Isso evita usar `String` solta pelo codigo para representar estados que ja sao conhecidos e limitados.

Neste exercicio, a enumeracao usada e:

- `OrderStatus`

Ela representa os possiveis status de um pedido.

## Estrutura do projeto

```text
Enumeracoes/
|- application/
|  `- Program.java
`- entities/
   |- Order.java
   `- enums/
      `- OrderStatus.java
```

## Papel de cada classe

### `Program`

Arquivo: `application/Program.java`

Responsavel por:

- criar um pedido (`Order`)
- definir um status usando o enum `OrderStatus`
- mostrar o objeto no console
- demonstrar duas formas de trabalhar com enum

### `Order`

Arquivo: `entities/Order.java`

Representa um pedido.

Atributos:

- `id`
- `moment`
- `status`

O atributo mais importante para este exercicio e:

- `status`, que usa o tipo `OrderStatus`

Isso quer dizer que o pedido nao aceita qualquer texto como status. Ele aceita apenas os valores definidos no enum.

### `OrderStatus`

Arquivo: `entities/enums/OrderStatus.java`

Enum que representa os estados possiveis de um pedido.

Valores atuais:

- `PENDING_PAYMENT`
- `PROCESSING`
- `SHIPPED`
- `DELIVERED`

## Como a enumeracao aparece no codigo

No `Order`, a enumeracao aparece neste atributo:

```java
private OrderStatus status;
```

Isso significa que o status do pedido e controlado por um conjunto fixo de valores.

## O que o `Program` faz

O fluxo do programa e simples:

1. cria um `Order`
2. usa `new Date()` para registrar o momento atual
3. define o status inicial como `OrderStatus.PENDING_PAYMENT`
4. imprime o pedido
5. cria uma variavel enum diretamente com `OrderStatus.DELIVERED`
6. converte a `String` `"DELIVERED"` para enum com `OrderStatus.valueOf("DELIVERED")`
7. imprime os dois resultados

## Duas formas de usar enum

### 1. Atribuicao direta

```java
OrderStatus os1 = OrderStatus.DELIVERED;
```

Aqui voce usa diretamente um valor do enum.

### 2. Conversao de texto para enum

```java
OrderStatus os2 = OrderStatus.valueOf("DELIVERED");
```

Aqui o Java pega uma `String` e transforma no valor correspondente do enum.

Isso e util quando o valor vem de:

- entrada do usuario
- banco de dados
- arquivo
- API

## Exemplo pratico

Trecho principal do programa:

```java
Order order = new Order(1080, new Date(), OrderStatus.PENDING_PAYMENT);

System.out.println(order);

OrderStatus os1 = OrderStatus.DELIVERED;
OrderStatus os2 = OrderStatus.valueOf("DELIVERED");

System.out.println(os1);
System.out.println(os2);
```

Saida esperada de forma aproximada:

```text
Order [id=1080, moment=Wed Apr 22 10:30:00 BRT 2026, status=PENDING_PAYMENT]
DELIVERED
DELIVERED
```

O valor exato de `moment` muda de acordo com a data e hora da execucao.

## O que foi praticado neste exercicio

Este modulo trabalha:

- criacao de `enum`
- uso de enum como tipo de atributo
- instanciacao de objetos
- sobrescrita de `toString()`
- conversao de `String` para enum com `valueOf`
- uso de `Date` para registrar o momento do pedido

## Vantagens de usar enum

Usar enum neste caso melhora o codigo porque:

- reduz erros de digitacao em status
- deixa o dominio mais claro
- facilita validacao
- melhora a leitura do codigo
- evita usar textos soltos em varios lugares

## Ponto importante sobre `valueOf`

Este metodo:

```java
OrderStatus.valueOf("DELIVERED")
```

funciona apenas quando o texto bate exatamente com o nome do enum.

Exemplo valido:

- `DELIVERED`

Exemplos invalidos:

- `delivered`
- `Delivered`
- ` delivered `

Se o texto vier diferente, o Java lanca `IllegalArgumentException`.

## Como executar

A partir da raiz do repositorio:

```bash
javac -d out $(find revisoes/revisoes/Enumeracoes -name "*.java")
java -cp out Enumeracoes.application.Program
```

## Resumo final


> Um pedido possui um status controlado por enum, o que garante que ele use apenas valores predefinidos e consistentes.
