# Heranca

Este projeto mostra o conceito de heranca em orientacao a objetos usando Java.

## O que e heranca

Heranca acontece quando uma classe mais especifica reaproveita atributos e comportamentos de uma classe mais geral.

Neste exercicio:

- `Account` e a classe base
- `BusinessAccount` herda de `Account`
- `SavingsAccount` herda de `Account`

Isso evita repeticao de codigo e permite especializar o comportamento de cada tipo de conta.

## Estrutura do projeto

```text
Heranca/
|- application/
|  `- Program.java
`- entities/
   |- Account.java
   |- BusinessAccount.java
   `- SavingsAccount.java
```

## Papel de cada classe

### `Program`

Arquivo: `application/Program.java`

Responsavel por:

- criar objetos das classes de conta
- chamar o metodo `withdraw`
- mostrar no console como o polimorfismo funciona

### `Account`

Arquivo: `entities/Account.java`

Representa uma conta bancaria generica.

Atributos principais:

- `number`
- `holder`
- `balance`

Metodos principais:

- `deposit(double amount)`: adiciona saldo
- `withdraw(double amount)`: realiza saque com taxa padrao de `5.0`

### `BusinessAccount`

Arquivo: `entities/BusinessAccount.java`

Representa uma conta empresarial.

Ela herda todos os dados de `Account` e adiciona:

- `loanLimit`

Metodos proprios:

- `loan(double amount)`: libera emprestimo se o valor estiver dentro do limite

Comportamento sobrescrito:

- `withdraw(double amount)`: chama o saque da superclasse e desconta mais `2.0`

### `SavingsAccount`

Arquivo: `entities/SavingsAccount.java`

Representa uma conta poupanca.

Ela herda todos os dados de `Account` e adiciona:

- `interestRate`

Metodos proprios:

- `updateBalance()`: aplica juros ao saldo

Comportamento sobrescrito:

- `withdraw(double amount)`: faz o saque sem cobrar a taxa padrao da classe `Account`

## Como a heranca aparece no codigo

Nas classes filhas:

```java
public class BusinessAccount extends Account {
```

```java
public class SavingsAccount extends Account {
```

Isso significa que as duas classes reaproveitam os atributos e metodos da superclasse `Account`.

## O que e sobrescrita

Sobrescrita, ou `override`, acontece quando a subclasse redefine um metodo herdado para ter um comportamento proprio.

Exemplo em `BusinessAccount`:

```java
@Override
public void withdraw(double amount) {
    super.withdraw(amount);
    balance -= 2.0;
}
```

Aqui:

- `super.withdraw(amount)` executa a regra da classe `Account`
- depois a conta empresarial desconta uma taxa adicional

Exemplo em `SavingsAccount`:

```java
@Override
public void withdraw(double amount) {
    balance -= amount;
}
```

Aqui a conta poupanca ignora a taxa de saque da superclasse.

## O que e polimorfismo no exemplo

No `Program`, estas linhas mostram polimorfismo:

```java
Account acc2 = new SavingsAccount(1002, "Maria", 1000.0, 0.01);
Account acc3 = new BusinessAccount(1003, "Bob", 1000.0, 500.0);
```

Mesmo a variavel sendo do tipo `Account`, o objeto real pode ser de uma subclasse.

Quando o metodo `withdraw` e chamado, o Java executa a versao correta de acordo com o objeto criado.

## Fluxo do programa

O programa segue esta ordem:

1. cria uma conta comum
2. realiza um saque e imprime o saldo
3. cria uma conta poupanca usando referencia do tipo `Account`
4. realiza um saque e imprime o saldo
5. cria uma conta empresarial usando referencia do tipo `Account`
6. realiza um saque e imprime o saldo

## Resultado esperado dos saques

Considerando saldo inicial de `1000.0` e saque de `200.0`:

- `Account`: `1000 - 200 - 5 = 795.0`
- `SavingsAccount`: `1000 - 200 = 800.0`
- `BusinessAccount`: `1000 - 200 - 5 - 2 = 793.0`

Saida esperada:

```text
795.0
800.0
793.0
```

## O que foi praticado neste exercicio

Este modulo trabalha:

- heranca entre classes
- reutilizacao de codigo
- sobrescrita de metodo com `@Override`
- uso de `super`
- polimorfismo
- especializacao de comportamento em subclasses

## Como executar

A partir da raiz do repositorio:

```bash
javac -d out $(find revisoes/revisoes/Heranca -name "*.java")
java -cp out Heranca.application.Program
```

## Resumo final

> `Account` define o comportamento geral, enquanto `BusinessAccount` e `SavingsAccount` reutilizam essa base e modificam apenas as regras especificas de cada tipo de conta.
