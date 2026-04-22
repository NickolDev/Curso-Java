# DataHora

Este projeto mostra os principais conceitos de data e hora em Java usando a API `java.time`.

O objetivo aqui nao e apenas executar codigo, mas entender o papel de cada tipo e quando usar cada um.

## O que este modulo estuda

O arquivo `DataHora.java` cobre estes topicos:

- `LocalDate`
- `LocalDateTime`
- `Instant`
- `ZoneId`
- `DateTimeFormatter`
- datas e horas atuais
- leitura de datas em formato ISO
- leitura de datas em formato personalizado
- criacao manual de datas
- formatacao para exibicao
- conversao de fuso horario
- acesso a componentes da data e hora
- calculos com datas
- duracao entre momentos
- imutabilidade das classes de `java.time`

## Estrutura do projeto

```text
DataHora/
|- DataHora.java
`- README
```

## Ideia central de cada tipo

### `LocalDate`

Representa somente uma data.

Exemplo:

- ano
- mes
- dia

Nao possui hora e nao possui fuso horario.

### `LocalDateTime`

Representa data e hora.

Exemplo:

- ano
- mes
- dia
- hora
- minuto
- segundo

Tambem nao possui fuso horario.

### `Instant`

Representa um momento exato global em UTC.

Ele e ideal quando voce quer guardar um instante absoluto, independente do pais ou da regiao em que o sistema esta rodando.

### `ZoneId`

Representa um fuso horario.

Ele e usado para transformar um `Instant` em uma data e hora local.

Exemplos:

- `ZoneId.systemDefault()`
- `ZoneId.of("Europe/Lisbon")`

### `DateTimeFormatter`

Serve para:

- formatar data e hora em texto
- ler texto e transformar em data/hora

Exemplos do projeto:

- `dd/MM/yyyy`
- `dd/MM/yyyy HH:mm`

## Como o codigo foi reestruturado

O `DataHora.java` foi organizado em etapas:

1. cria os dados principais de estudo
2. cria os calculos derivados
3. cria as duracoes
4. imprime cada assunto em uma secao separada

Isso deixa o codigo mais facil de ler, revisar e usar como material de consulta.

## Topicos explicados pelo codigo

### 1. Obtendo data e hora atuais

O codigo mostra como capturar:

- a data atual com `LocalDate.now()`
- a data e hora atuais com `LocalDateTime.now()`
- o instante atual em UTC com `Instant.now()`

Exemplo:

```java
LocalDate hoje = LocalDate.now();
LocalDateTime agora = LocalDateTime.now();
Instant agoraUtc = Instant.now();
```

## 2. Convertendo textos no formato ISO

O Java entende muito bem o padrao ISO 8601.

Exemplos usados:

- `2022-07-20`
- `2022-07-20T01:30:26`
- `2022-07-20T01:30:26Z`
- `2022-07-20T01:30:26-03:00`

No codigo:

```java
LocalDate dataIso = LocalDate.parse("2022-07-20");
LocalDateTime dataHoraIso = LocalDateTime.parse("2022-07-20T01:30:26");
Instant instanteIso = Instant.parse("2022-07-20T01:30:26Z");
```

### 3. Convertendo textos com formato personalizado

Quando o texto nao esta em ISO, voce precisa fornecer um `DateTimeFormatter`.

Exemplos:

```java
DateTimeFormatter.ofPattern("dd/MM/yyyy");
DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
```

Uso no projeto:

```java
LocalDate dataCustom = LocalDate.parse("20/07/2022", DATE_FORMATTER);
LocalDateTime dataHoraCustom = LocalDateTime.parse("20/07/2022 01:30", DATE_TIME_FORMATTER);
```

### 4. Criando datas manualmente

Tambem e possivel criar datas e horarios diretamente no codigo:

```java
LocalDate.of(2022, 7, 20);
LocalDateTime.of(2022, 7, 20, 1, 30);
```

Isso e util quando a data nao vem de texto nem do relogio do sistema.

### 5. Formatando para exibicao

Depois de criar a data, voce pode exibi-la do jeito que quiser:

```java
dataIso.format(DATE_FORMATTER);
dataHoraIso.format(DATE_TIME_FORMATTER);
```

Para `Instant`, o projeto tambem mostra a diferenca entre:

- exibicao no fuso local
- exibicao ISO padrao em UTC

### 6. Convertendo `Instant` para data e hora local

Como `Instant` representa um momento global, ele precisa de um fuso para virar horario local.

Exemplo do projeto:

```java
LocalDate.ofInstant(instanteIso, SYSTEM_ZONE);
LocalDateTime.ofInstant(instanteIso, LISBON_ZONE);
```

O ponto principal aqui e:

> o mesmo `Instant` pode aparecer com horarios diferentes em fusos diferentes

### 7. Acessando partes da data e hora

O codigo mostra como extrair componentes especificos:

- dia
- mes
- ano
- hora
- minuto

Exemplos:

```java
dataIso.getDayOfMonth();
dataIso.getMonthValue();
dataIso.getYear();
dataHoraIso.getHour();
dataHoraIso.getMinute();
```

### 8. Fazendo calculos com datas e horas

O projeto usa:

- `minusDays()`
- `plusDays()`
- `minus(..., ChronoUnit.DAYS)`
- `plus(..., ChronoUnit.DAYS)`

Exemplo:

```java
LocalDate semanaPassada = dataIso.minusDays(7);
LocalDate proximaSemana = dataIso.plusDays(7);
Instant semanaPassadaUtc = instanteIso.minus(7, ChronoUnit.DAYS);
```

Esses metodos retornam novos objetos. Eles nao alteram o original.

### 9. Calculando duracao entre momentos

O projeto usa `Duration.between(...)` para medir o tempo entre dois momentos.

Exemplos:

```java
Duration.between(semanaPassada.atStartOfDay(), dataIso.atStartOfDay());
Duration.between(semanaPassadaComHora, dataHoraIso);
Duration.between(semanaPassadaUtc, instanteIso);
```

Para `LocalDate`, o codigo usa `atStartOfDay()` porque `Duration` precisa de um ponto no tempo com hora.

Tambem ha um exemplo de duracao negativa quando a ordem dos parametros e invertida.

### 10. Imutabilidade

Este e um dos conceitos mais importantes do modulo.

As classes de `java.time` usadas aqui sao imutaveis.

Isso significa que:

- `plusDays()`
- `minusDays()`
- `plus()`
- `minus()`

nao alteram o objeto original.

Elas sempre retornam um novo objeto.

## Exemplo mental rapido

Pense assim:

- `LocalDate` = calendario
- `LocalDateTime` = calendario + relogio
- `Instant` = momento exato universal
- `ZoneId` = lente local para enxergar um `Instant`
- `DateTimeFormatter` = tradutor entre objeto e texto

## Como executar

A partir da raiz do repositorio:

```bash
javac -d out revisoes/revisoes/DataHora/DataHora.java
java -cp out DataHora.DataHora
```

## Resumo final

Se voce quiser resumir a ideia do modulo em uma frase:

> O pacote `java.time` separa claramente data, data com hora, instante global, fuso horario, formatacao e calculo de tempo, o que torna o codigo mais preciso e mais facil de manter.
