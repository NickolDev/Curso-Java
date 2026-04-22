# StringBuilder

Este projeto mostra como usar `StringBuilder` em Java para montar textos maiores de forma organizada e eficiente.

Neste modulo, o `StringBuilder` e usado para montar a representacao textual de um post com comentarios.

## O que e `StringBuilder`

`StringBuilder` e uma classe usada para construir strings aos poucos.

Em vez de juntar varios textos com `+` repetidamente, voce pode ir adicionando partes com:

- `append()`

Isso e util quando:

- o texto final tem varias linhas
- o conteudo vem de diferentes atributos
- existe repeticao dentro de um laco
- voce quer deixar a montagem da saida mais clara

## Estrutura do projeto

```text
StringBuilder/
|- application/
|  `- Program.java
`- entities/
   |- Comment.java
   `- Post.java
```

## Papel de cada classe

### `Program`

Arquivo: `application/Program.java`

Responsavel por:

- criar comentarios
- criar posts
- associar comentarios aos posts
- imprimir os posts no console

### `Comment`

Arquivo: `entities/Comment.java`

Representa um comentario simples.

Atributo:

- `text`

### `Post`

Arquivo: `entities/Post.java`

Representa uma postagem.

Atributos:

- `moment`
- `title`
- `content`
- `likes`
- `comments`

Metodos importantes:

- `addComment(Comment comment)`
- `removeComment(Comment comment)`
- `toString()`

O metodo mais importante para este modulo e o `toString()`, porque e nele que o `StringBuilder` aparece.

## Onde o `StringBuilder` e usado

No `Post`, o metodo `toString()` foi escrito assim:

```java
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(title + "\n");
    sb.append(likes);
    sb.append(" Likes - ");
    sb.append(sdf.format(moment) + "\n");
    sb.append(content + "\n");
    sb.append("Comments:\n");
    for (Comment c : comments) {
        sb.append(c.getText() + "\n");
    }
    return sb.toString();
}
```

O que esse metodo faz:

1. cria um `StringBuilder`
2. adiciona titulo, likes, data e conteudo
3. adiciona o texto fixo `"Comments:"`
4. percorre a lista de comentarios
5. adiciona o texto de cada comentario
6. converte tudo para `String` no final com `toString()`

## Por que usar `StringBuilder` aqui

Sem `StringBuilder`, a montagem desse texto poderia ficar mais confusa e menos eficiente, principalmente porque:

- o texto final tem varias partes
- existem quebras de linha
- os comentarios sao adicionados dentro de um `for`

Com `StringBuilder`, o codigo fica mais natural para montar a saida passo a passo.

## Relacao entre as classes

Este modulo tambem mostra composicao entre objetos:

- um `Post` tem varios `Comment`

Isso aparece neste atributo:

```java
private List<Comment> comments = new ArrayList<>();
```

Ou seja, alem de estudar `StringBuilder`, o codigo tambem mostra como um objeto pode agrupar outros objetos.

## Fluxo do programa

O `Program` segue esta ordem:

1. cria um formatador de data com `SimpleDateFormat`
2. cria comentarios
3. cria o primeiro post
4. adiciona comentarios ao primeiro post
5. cria novos comentarios
6. cria o segundo post
7. adiciona comentarios ao segundo post
8. imprime os dois posts com `System.out.println`

Quando `System.out.println(p1)` e `System.out.println(p2)` sao executados, o Java chama automaticamente o `toString()` de `Post`.

## Exemplo pratico

Trecho principal:

```java
Comment c1 = new Comment("Have a nice trip!");
Comment c2 = new Comment("Wow that's awesome!");

Post p1 = new Post(
        sdf.parse("21/06/2018 13:05:44"),
        "Traveling to New Zealand",
        "I'm going to visit this wonderful country!",
        12);

p1.addComment(c1);
p1.addComment(c2);

System.out.println(p1);
```

Saida esperada:

```text
Traveling to New Zealand
12 Likes - 21/06/2018 13:05:44
I'm going to visit this wonderful country!
Comments:
Have a nice trip!
Wow that's awesome!
```

## O que foi praticado neste exercicio

Este modulo pratica:

- uso de `StringBuilder`
- sobrescrita de `toString()`
- uso de listas com `ArrayList`
- relacionamento entre `Post` e `Comment`
- manipulacao basica de datas com `Date`
- formatacao de data com `SimpleDateFormat`

## Quando lembrar de `StringBuilder`

Pense em usar `StringBuilder` quando:

- for montar texto em varias etapas
- existir um laco adicionando partes da string
- a saida final tiver varias linhas
- voce quiser centralizar a montagem do texto em um unico metodo

## Como executar

A partir da raiz do repositorio:

```bash
javac -d out $(find revisoes/revisoes/StringBuilder -name "*.java")
java -cp out StringBuilder.application.Program
```

## Resumo final

Se voce quiser resumir a ideia deste projeto em uma frase:

> O `StringBuilder` permite montar uma string final de forma incremental, clara e apropriada para cenarios em que o texto e composto por varias partes, como um post com comentarios.
