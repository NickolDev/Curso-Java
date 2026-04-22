# Composicao

Este projeto mostra o conceito de composicao em orientacao a objetos usando Java.

## O que e composicao

Composicao acontece quando um objeto "tem" outros objetos como parte dele.

Neste exercicio:

- um `Worker` tem um `Department`
- um `Worker` tem varios `HourContract`

Ou seja, o trabalhador nao funciona sozinho no dominio do exercicio. Ele esta ligado a um departamento e pode acumular contratos por hora.

## Estrutura do projeto

```text
Composicao/
|- application/
|  `- Program.java
`- entities/
   |- Department.java
   |- HourContract.java
   |- Worker.java
   `- enums/
      `- WorkerLevel.java
```

## Papel de cada classe

### `Program`

Arquivo: `application/Program.java`

Responsavel por:

- ler os dados no console
- criar os objetos
- adicionar contratos ao trabalhador
- calcular o ganho do trabalhador em um mes especifico
- exibir o resultado final

### `Department`

Arquivo: `entities/Department.java`

Representa o departamento do trabalhador.

Atributo principal:

- `name`

Exemplo:

- TI
- Vendas
- RH

### `WorkerLevel`

Arquivo: `entities/enums/WorkerLevel.java`

Enum usado para representar o nivel do trabalhador.

No fluxo principal do programa, a entrada esperada e:

- `JUNIOR`
- `MID_LEVEL`
- `SENIOR`

### `HourContract`

Arquivo: `entities/HourContract.java`

Representa um contrato por hora.

Atributos:

- `date`
- `valuePerHour`
- `hours`

Metodo principal:

- `totalValue()`: calcula o valor total do contrato multiplicando `valuePerHour * hours`

### `Worker`

Arquivo: `entities/Worker.java`

E a classe central do exercicio.

Atributos principais:

- `name`
- `level`
- `baseSalary`
- `department`
- `contracts`

Metodos importantes:

- `addContract(HourContract contract)`: adiciona um contrato na lista
- `removeContract(HourContract contract)`: remove um contrato
- `income(int year, int month)`: soma o salario base com os contratos daquele mes

## Como a composicao aparece no codigo

No `Worker`, a composicao aparece nestes atributos:

```java
private Department department;
private List<HourContract> contracts = new ArrayList<>();
```

Isso significa que o trabalhador guarda referencias para outros objetos do dominio.

## Fluxo de execucao

O programa segue esta ordem:

1. le o nome do departamento
2. cria um `Department`
3. le os dados do trabalhador
4. cria um `Worker`
5. pergunta quantos contratos o trabalhador possui
6. cria cada `HourContract` e adiciona ao `Worker`
7. pede um mes/ano
8. calcula o ganho do trabalhador naquele periodo
9. imprime o resultado

## Exemplo pratico

Entrada:

```text
Entre o nome do departamento: TI
Entre os dados do trabalhador:
Nome: Maria
Nivel: JUNIOR, MID_LEVEL ou SENIOR: JUNIOR
Salario base: 1200.00
Quantos contratos esse trabalhador tem? 2
Entre com os dados do contrato #1:
Data (DD/MM/YYYY): 20/04/2026
Valor por hora: 50.00
Duracao (horas): 20
Entre com os dados do contrato #2:
Data (DD/MM/YYYY): 13/04/2026
Valor por hora: 30.00
Duracao (horas): 10

Entre o ano e mes para calcular o ganho (MM/YYYY): 04/2026
```

Calculo:

- salario base = `1200.00`
- contrato 1 = `50 * 20 = 1000.00`
- contrato 2 = `30 * 10 = 300.00`
- total no mes = `2500.00`

Saida esperada:

```text
Nome: Maria
Departamento: TI
Ganho para 04/2026: 2500.00
```

## O que foi feito neste exercicio

Este modulo pratica:

- criacao de classes e objetos
- relacionamento entre objetos
- uso de enum
- listas com `ArrayList`
- leitura de dados com `Scanner`
- datas com `LocalDate`
- formatacao com `DateTimeFormatter`
- calculo de regra de negocio dentro da entidade

## Problema que apareceu durante o desenvolvimento

Havia um erro de execucao com `NullPointerException` ao acessar:

```java
worker.getDepartment().getName()
```

Isso acontecia porque o `Worker` estava sendo criado por um construtor que nao atribuia os valores recebidos aos atributos da classe. Como resultado, `department` ficava `null`.

A solucao correta foi ajustar o construtor usado pelo programa para inicializar:

- `name`
- `level`
- `baseSalary`
- `department`

Assim, o objeto `Worker` passa a nascer em um estado consistente dentro do fluxo principal.

## Como executar

A partir da raiz do repositorio:

```bash
javac -d out $(find revisoes/revisoes/Composicao -name "*.java")
java -cp out Composicao.application.Program
```

## Resumo final

Se voce quiser resumir a ideia deste projeto em uma frase:

> Um trabalhador pertence a um departamento e possui varios contratos; o ganho mensal dele e o salario base somado aos contratos do mes informado.
