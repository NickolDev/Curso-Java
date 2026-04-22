# Polimorfismo

Este projeto mostra o conceito de polimorfismo em orientacao a objetos usando Java.

## O que e polimorfismo

Polimorfismo acontece quando uma variavel de um tipo mais geral pode apontar para objetos de tipos mais especificos.

Neste exercicio:

- `Employee` e a classe base
- `OutsourcedEmployee` herda de `Employee`
- a lista principal usa o tipo `List<Employee>`

Isso permite tratar funcionarios comuns e terceirizados de forma uniforme, mas com comportamentos diferentes no calculo do pagamento.

## Estrutura do projeto

```text
Polimorfismo/
|- application/
|  `- Program.java
`- entities/
   |- Employee.java
   `- OutsourcedEmployee.java
```

## Papel de cada classe

### `Program`

Arquivo: `application/Program.java`

Responsavel por:

- ler os dados dos funcionarios
- decidir se o funcionario e comum ou terceirizado
- criar os objetos corretos
- armazenar todos na mesma lista
- imprimir o pagamento de cada funcionario

### `Employee`

Arquivo: `entities/Employee.java`

Representa um funcionario comum.

Atributos principais:

- `name`
- `hours`
- `valuePerHour`

Metodo principal:

- `payment()`: calcula o salario multiplicando `hours * valuePerHour`

### `OutsourcedEmployee`

Arquivo: `entities/OutsourcedEmployee.java`

Representa um funcionario terceirizado.

Ela herda os dados de `Employee` e adiciona:

- `additionalCharge`

Comportamento sobrescrito:

- `payment()`: soma o pagamento normal com `110%` do valor adicional

## Como o polimorfismo aparece no codigo

No `Program`, a lista e declarada assim:

```java
List<Employee> list = new ArrayList<>();
```

Mas nela sao armazenados objetos de tipos diferentes:

```java
list.add(new Employee(name, hours, valuePerHour));
list.add(new OutsourcedEmployee(name, hours, valuePerHour, additionalCharge));
```

Mesmo a lista sendo do tipo `Employee`, ela aceita objetos da subclasse `OutsourcedEmployee`.

## O que e sobrescrita neste exemplo

Em `OutsourcedEmployee`, o metodo `payment()` foi sobrescrito:

```java
@Override
public Double payment() {
    return super.payment() + additionalCharge * 1.1;
}
```

Aqui:

- `super.payment()` calcula o pagamento basico
- `additionalCharge * 1.1` adiciona a taxa extra de 10%

## Fluxo do programa

O programa segue esta ordem:

1. le a quantidade de funcionarios
2. para cada funcionario, pergunta se ele e terceirizado
3. le nome, horas e valor por hora
4. se for terceirizado, le tambem o valor adicional
5. cria o objeto correto e adiciona na lista
6. percorre a lista e imprime o pagamento de cada funcionario

## Exemplo pratico

Entrada:

```text
Enter the number of employees: 2
Employee #1 data:
Outsourced (y/n)? n
Name: Alex
Hours: 50
Value per hour: 20.00
Employee #2 data:
Outsourced (y/n)? y
Name: Maria
Hours: 60
Value per hour: 20.00
Additional charge: 200.00
```

Calculo:

- `Alex`: `50 * 20 = 1000.00`
- `Maria`: `60 * 20 = 1200.00`
- adicional de `Maria`: `200 * 1.1 = 220.00`
- total de `Maria`: `1420.00`

Saida esperada:

```text
PAYMENTS:
Alex - $ 1000.00
Maria - $ 1420.00
```

## O que foi praticado neste exercicio

Este modulo trabalha:

- heranca
- polimorfismo
- sobrescrita de metodo com `@Override`
- uso de `super`
- listas com `ArrayList`
- leitura de dados com `Scanner`
- decisao de instancia com base na entrada do usuario

## Como executar

A partir da raiz do repositorio:

```bash
javac -d out $(find revisoes/revisoes/Polimorfismo -name "*.java")
java -cp out Polimorfismo.application.Program
```

## Resumo final

> A lista usa o tipo `Employee`, mas o Java executa o metodo `payment()` de acordo com o tipo real de cada objeto, mostrando na pratica o conceito de polimorfismo.
