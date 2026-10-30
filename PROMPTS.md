# PROMPTS: Grupo ____

**Integrantes:**

1. Ana Clara Grigolon Dutra Rosa
2. Davi Furlan Pereira
3. Lucas Brulezzi dos Santos

**Prova:** API em MVC (Reserva de Salas)

**Usamos IA?** (X) Sim  ( ) Não

**Qual ferramenta e modelo (se usaram):** Google Gemini

## Registro, na ordem

### Prompt 1

**O prompt (cópia exata):**

> Atue como um desenvolvedor Sênior especializado em construção de APIs RESTful. Estou fazendo uma prova prática com o projeto chamado 'Escola-base' e preciso que você desenvolva o código seguindo RESTRITAMENTE as seguintes exigências do meu professor:

Arquitetura MVC: O projeto deve ser estritamente organizado nas camadas: Model, Repository, Service e Controller.
Regras de Negócio: Todas as regras de negócio e validações devem ficar EXCLUSIVAMENTE na camada Service. O Controller deve ser limpo, servindo apenas para receber a requisição, chamar a Service e devolver a resposta.
Status HTTP: A API deve responder a cada situação com o status HTTP correto (ex: 201 Created para criação com sucesso, 200 OK para buscas, 404 Not Found se o recurso não existir, 400 Bad Request para erros de validação, 204 No Content para exclusões).
O que deve ser feito na prova de hoje:

## A situação
A escola quer uma API para controlar a reserva de suas salas: laboratórios, auditório, salas de aula. Cada sala tem um código, um nome e uma capacidade, e a escola precisa saber quais estão reservadas e quais estão livres.

## O que a API precisa fazer
Cadastrar uma sala, listar todas, buscar uma pelo id e excluir.
Reservar uma sala e liberar uma sala.
Responder cada situação com o status HTTP certo: sucesso, criado, sem conteúdo, não encontrado, conflito e dados inválidos.

## O que o projeto precisa ter
O projeto **`Escola-base`**, que o professor entrega, e que vocês usam como ponto de partida. Ele já traz Spring Web, Spring Data JPA, validação e banco **H2 em memória**, e já sobe sem nenhuma classe de vocês. Não precisa criar projeto. O arquivo `README.md` dele explica como rodar.
Organização em camadas, **cada uma no seu pacote, criado por vocês** dentro de `src/main/java/escola`: `model`, `repository`, `service` e `controller`. O projeto base não traz essas pastas prontas.
As regras da escola ficam na **Service**. O **Controller** só recebe a requisição, chama a Service e devolve a resposta HTTP. O Controller não conversa direto com o Repository.
As rotas seguem o padrão REST. Vocês escolhem os nomes.

Entregáveis que preciso que você gere:

O código completo e correto para as classes correspondentes em cada camada (Model, Repository, Service e Controller).
Uma lista passo a passo descrevendo o caminho lógico de criação do código e os comandos/prompts utilizados. Preciso disso para colocar no meu arquivo PROMPTS.md, pois o "Processo" vale 40% da nota.
Um modelo base para o arquivo README.md contendo um espaço para os nomes dos integrantes do grupo e o roteiro de conferência padrão preenchido. 
**O que a IA devolveu (uma ou duas linhas):**
A IA alertou que faltavam as regras de negócio específicas (entidades, campos e regras da prova) para poder gerar a primeira camada corretamente.

**O que fizemos com a resposta:** (x) usamos como veio  ( ) alteramos  ( ) descartamos

**Por quê (o que conferimos, o que mudamos):**
Aceitamos a orientação da IA e percebemos que esquecemos de colar o roteiro da prova no prompt. Copiamos as regras e enviamos no próximo passo.

### Prompt 2

**O prompt (cópia exata):**

> # As regras da escola
Toda sala cadastrada começa livre.
O **código é único**: não pode haver duas salas com o mesmo código.
**Código e nome são obrigatórios**, e a **capacidade precisa ser maior que zero**.
Uma sala que já está reservada **não pode ser reservada de novo**.
Só pode ser liberada uma sala que **está reservada**.
Uma sala reservada **não pode ser excluída**.
Pedir, reservar, liberar ou excluir uma sala que não existe é um caso de "não encontrado".

Crie SalaRepository.java

**O que a IA devolveu (uma ou duas linhas):**
Explicou que a prova exigia 4 camadas e gerou o código da interface `SalaRepository` com o método `existsByCodigo`.

**O que fizemos com a resposta:** (x) usamos como veio  ( ) alteramos  ( ) descartamos

**Por quê (o que conferimos, o que mudamos):**
Precisávamos do repositório para conectar ao banco H2 e também do método de verificação para barrar códigos repetidos. Salvamos no pacote `repository`.

### Prompt 3

**O prompt (cópia exata):**

> Crie SalaService.java seguindo as regras de negocio.

**O que a IA devolveu (uma ou duas linhas):**
Gerou a classe `SalaService` contendo todas as regras de negócio, lançando `ResponseStatusException` (409 Conflict, 404 Not Found) nas validações de reserva e liberação.

**O que fizemos com a resposta:** (x) usamos como veio  ( ) alteramos  ( ) descartamos

**Por quê (o que conferimos, o que mudamos):**
A lógica cumpria perfeitamente a exigência do professor de isolar as regras na Service. Salvamos no pacote `service`.

### Prompt 4

**O prompt (cópia exata):**

> Gere o arquivo SalaController conforme as instruções e usando de base a camada service

**O que a IA devolveu (uma ou duas linhas):**
Criou o `SalaController` totalmente limpo, apenas repassando as requisições para a Service e definindo os retornos HTTP corretos (201, 200, 204).

**O que fizemos com a resposta:** (x) usamos como veio  ( ) alteramos  ( ) descartamos

**Por quê (o que conferimos, o que mudamos):**
O Controller fechou a arquitetura MVC sem violar a regra de negócio. Salvamos no pacote `controller` e testamos a aplicação.

### Prompt 5

**O prompt (cópia exata):**

> "Proxima etapa" / "vou mandar os 2 arquivos base pra vc conferir"

**O que a IA devolveu (uma ou duas linhas):**
Conferiu os templates de documentação oficiais exigidos pelo professor.

**O que fizemos com a resposta:** ( ) usamos como veio  (x) alteramos  ( ) descartamos

**Por quê (o que conferimos, o que mudamos):**
Revisamos o conteúdo para entregar o `PROMPTS.md` finalizado.

## Como conferimos
Gemini rodou os codigos e não identificou nenhuma inconsistencia.

## Respostas finais, sem consultar a IA

*(Lembrem-se de preencher estas 3 perguntas com as palavras de vocês nos 5 minutos finais da prova!)*

**Pergunta 1:**
Controller: Recebe a requisição HTTP.
Service: Centraliza toda a lógica.
Repository: Faz a ligação dos dados, buscando e salvando as alterações no banco.
Model: Representa a entidade e seus atributos.

**Pergunta 2:**
O Controller deve ser limpo, enquanto todas as validações e lógicas devem ficar isoladas na camada Service.

**Pergunta 3:**
Se a escola trocar o banco de dados H2, nenhuma classe do projeto precisa mudar. Apenas o arquivo pom.xml e o arquivo de configuração precisam ser atualizados.