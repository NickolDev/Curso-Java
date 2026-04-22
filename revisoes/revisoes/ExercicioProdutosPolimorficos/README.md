# ExercicioProdutosPolimorficos

Este projeto mostra um sistema simples de produtos em Java usando heranca e polimorfismo.

Neste exercicio aparecem tres ideias principais:

- uma classe base para produto comum
- subclasses para produto usado e produto importado
- sobrescrita de `priceTag()` para mudar a saida conforme o tipo real do objeto

## O que o projeto representa

O programa simula o cadastro de varios produtos e imprime a etiqueta de preco de cada um.

Cada produto pode ser:

- comum
- usado
- importado

Todos sao armazenados na mesma lista, mas cada tipo monta sua etiqueta de forma diferente.

## Estrutura do projeto

```text
revisoes/revisoes/ExercicioProdutosPolimorficos/
|- application/
|  `- Program.java
|- entities/
|  |- Product.java
|  |- ImportedProduct.java
|  `- UsedProduct.java
`- README.md
```

## Papel de cada classe

### `Program`

Arquivo: `revisoes/revisoes/ExercicioProdutosPolimorficos/application/Program.java`

Responsavel por:

- ler os dados no console
- decidir qual tipo de produto sera criado
- instanciar `Product`, `UsedProduct` ou `ImportedProduct`
- armazenar todos em uma lista do tipo `List<Product>`
- imprimir as etiquetas finais

### `Product`

Arquivo: `revisoes/revisoes/ExercicioProdutosPolimorficos/entities/Product.java`

Representa o produto comum.

Atributos:

- `name`
- `price`

Metodo principal:

- `priceTag()`: devolve a etiqueta basica no formato `nome + preco`

### `ImportedProduct`

Arquivo: `revisoes/revisoes/ExercicioProdutosPolimorficos/entities/ImportedProduct.java`

Representa um produto importado.

Ela herda os dados de `Product` e adiciona:

- `customsFee`

Metodos importantes:

- `totalPrice()`: soma `price + customsFee`
- `priceTag()`: sobrescreve a etiqueta para mostrar o preco total e a taxa de importacao

### `UsedProduct`

Arquivo: `revisoes/revisoes/ExercicioProdutosPolimorficos/entities/UsedProduct.java`

Representa um produto usado.

Ela herda os dados de `Product` e adiciona:

- `manufactureDate`

Metodo sobrescrito:

- `priceTag()`: mostra o nome, o preco e a data de fabricacao

## Como o polimorfismo aparece no codigo

No `Program`, a lista principal e declarada assim:

```java
List<Product> list = new ArrayList<>();
```

Mas nela sao armazenados objetos de tipos diferentes:

```java
list.add(new Product(name, price));
list.add(new UsedProduct(name, price, date));
list.add(new ImportedProduct(name, price, customsFee));
```

Na hora de imprimir:

```java
for (Product prod : list) {
    System.out.println(prod.priceTag());
}
```

Mesmo usando a referencia do tipo `Product`, o Java executa a versao correta de `priceTag()` de acordo com o tipo real de cada objeto.

## O que e sobrescrita neste exemplo

As subclasses mudam o comportamento herdado da classe base:

Em `ImportedProduct`:

```java
@Override
public String priceTag() {
    return getName()
            + " $ "
            + String.format("%.2f", totalPrice())
            + " (Customs fee: $ "
            + String.format("%.2f", customsFee)
            + ")";
}
```

Em `UsedProduct`:

```java
@Override
public String priceTag() {
    return getName()
            + " (used) $ "
            + String.format("%.2f", getPrice())
            + " (Manufacture date: "
            + manufactureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            + ")";
}
```

## Fluxo do programa

O programa segue esta ordem:

1. le a quantidade de produtos
2. para cada produto, pergunta se ele e comum, usado ou importado
3. le nome e preco
4. se for usado, le a data de fabricacao
5. se for importado, le a taxa de importacao
6. cria o objeto correto e adiciona na lista
7. percorre a lista e imprime as etiquetas

## Exemplo pratico

Entrada:

```text
Entre com o numero de produtos: 3
Datos do produto #1:
Comum, usado, importado (c/u/i)? c
Nome: Computer
Preco: 1100.00
Datos do produto #2:
Comum, usado, importado (c/u/i)? u
Nome: iPhone 10
Preco: 400.00
Data de fabricacao (DD/MM/YYYY): 15/03/2017
Datos do produto #3:
Comum, usado, importado (c/u/i)? i
Nome: Tablet
Preco: 260.00
Taxa de importacao: 20.00
```

Saida esperada:

```text
ETIQUETAS DE PRECO:
Computer $ 1100.00
iPhone 10 (used) $ 400.00 (Manufacture date: 15/03/2017)
Tablet $ 280.00 (Customs fee: $ 20.00)
```

## O que foi praticado neste exercicio

Este modulo pratica:

- heranca
- polimorfismo
- sobrescrita de metodo com `@Override`
- uso de `super`
- listas com `ArrayList`
- leitura com `Scanner`
- uso de `LocalDate`
- formatacao de data com `DateTimeFormatter`
- formatacao numerica com `String.format()`

## Pacotes do projeto

As classes deste modulo usam pacotes que comecam com o prefixo:

```text
ExercicioProdutosPolimorficos
```

Na pratica, os arquivos estao organizados em pacotes como:

- `ExercicioProdutosPolimorficos.application`
- `ExercicioProdutosPolimorficos.entities`

Por isso, o comando de execucao precisa chamar:

```text
ExercicioProdutosPolimorficos.application.Program
```

## Como executar

A partir da raiz do repositorio:

```bash
javac -d out $(find revisoes/revisoes/ExercicioProdutosPolimorficos -name "*.java")
java -cp out ExercicioProdutosPolimorficos.application.Program
```

## Resumo final

> O programa armazena produtos diferentes na mesma lista e imprime etiquetas personalizadas para cada tipo, mostrando na pratica heranca, sobrescrita e polimorfismo.
