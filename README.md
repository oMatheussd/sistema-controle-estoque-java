# Sistema de Controle de Estoque em Java

Projeto desenvolvido como parte do **Projeto Integrador – Laboratório de Programação**, do curso de Análise e Desenvolvimento de Sistemas da Universidade Santo Amaro (UNISA).

A aplicação tem como objetivo auxiliar pequenas empresas no controle de produtos e movimentações de estoque, oferecendo operações de cadastro, consulta, registro de entradas e saídas, acompanhamento de estoque baixo e geração de relatórios.

O sistema funciona por meio de um menu interativo no terminal e utiliza arquivos CSV para armazenar os dados.

## 1. Objetivo do projeto

O projeto foi desenvolvido considerando um cenário de pequena empresa que realiza o controle de produtos por meio de planilhas eletrônicas.

O uso de registros manuais pode ocasionar divergências entre as quantidades registradas e o estoque físico, além de dificultar o acompanhamento das movimentações.

A proposta foi desenvolver uma aplicação simples, utilizando Java, que permita organizar essas informações e aplicar regras básicas de controle de estoque.

## 2. Funcionalidades

O sistema disponibiliza as seguintes funcionalidades:

- **Cadastro de produtos:** permite informar código, nome, categoria, preço, quantidade inicial e estoque mínimo.
- **Listagem de produtos:** apresenta os produtos cadastrados em ordem crescente de código.
- **Busca por código:** localiza um produto por meio de seu código identificador.
- **Registro de entradas:** adiciona unidades ao saldo de um produto e registra a movimentação.
- **Registro de saídas:** subtrai unidades do estoque, desde que exista quantidade disponível.
- **Consulta de estoque baixo:** identifica produtos cuja quantidade seja menor ou igual ao estoque mínimo cadastrado.
- **Relatório de estoque:** apresenta quantidades, preços unitários, valores individuais e valor total do estoque.
- **Histórico de movimentações:** apresenta os registros de entradas e saídas, incluindo produto, tipo, quantidade, data e hora.
- **Persistência de dados:** salva e recupera produtos e movimentações por meio de arquivos CSV.

## 3. Tecnologias e conceitos utilizados

- **Java:** linguagem de programação utilizada no desenvolvimento.
- **JDK 25.0.2:** versão informada no ambiente de desenvolvimento do projeto.
- **Eclipse IDE:** ambiente utilizado para implementação e execução.
- **Git e GitHub:** controle de versão e hospedagem do código-fonte.
- **Programação orientada a objetos:** organização das funcionalidades em classes e pacotes.
- **ArrayList:** estrutura utilizada para armazenar produtos e movimentações durante a execução.
- **Arquivos CSV:** armazenamento persistente das informações.
- **LocalDateTime:** registro de data e hora das movimentações.

### Algoritmos utilizados

**Ordenação por seleção (Selection Sort)**

Utilizada para organizar os produtos em ordem crescente de código. O algoritmo foi implementado manualmente, sem utilizar métodos prontos de ordenação da linguagem.

Sua complexidade de tempo é O(n²), sendo adequada ao pequeno volume de dados considerado no projeto.

**Pesquisa sequencial (Linear Search)**

Utilizada para localizar produtos por código. O método percorre a lista até encontrar o produto desejado ou chegar ao final da coleção.

Sua complexidade no pior caso é O(n).

## 4. Organização do código-fonte

O projeto está dividido em três pacotes principais:

| Pacote | Classe | Responsabilidade |
|---|---|---|
| `aplicacao` | `Programa` | Inicialização do sistema, carregamento dos dados e apresentação do menu. |
| `entidades` | `Produto` | Representação dos produtos e seus atributos. |
| `entidades` | `Movimentacao` | Representação das entradas e saídas de estoque. |
| `entidades` | `TipoMovimentacao` | Definição dos tipos de movimentação: `ENTRADA` e `SAIDA`. |
| `servicos` | `EstoqueService` | Regras de negócio, cadastro, busca, ordenação, movimentações e relatórios. |
| `servicos` | `ArquivoService` | Leitura e gravação dos arquivos CSV. |

Essa divisão busca separar a interação com o usuário, as entidades do sistema, as regras de negócio e a persistência dos dados.

## 5. Regras de negócio e validações

O sistema implementa algumas regras para manter a consistência das operações:

- Não permite cadastrar dois produtos com o mesmo código.
- Impede a criação de um produto com quantidade inicial negativa.
- Não permite registrar entradas ou saídas com quantidade menor ou igual a zero.
- Impede saídas superiores à quantidade disponível no estoque.
- Exige que o produto informado nas movimentações esteja cadastrado.
- Registra no histórico as movimentações realizadas com sucesso.
- Mantém o saldo do produto atualizado após cada movimentação válida.

As validações são concentradas principalmente na classe `EstoqueService` e na entidade `Produto`.

## 6. Armazenamento e persistência dos dados

Os dados são armazenados em arquivos de texto no formato CSV, dentro da pasta `dados`, criada automaticamente durante a primeira gravação.

São utilizados dois arquivos:

**`produtos.csv`**

Armazena os seguintes campos:

`codigo, nome, categoria, preco, quantidade, estoqueMinimo`

Exemplo:

```csv
101,Teclado,Perifericos,120.0,10,3
102,Mouse,Perifericos,55.9,15,5
```

**`movimentacoes.csv`**

Armazena:

`codigoProduto, tipo, quantidade, dataHora`

Exemplo:

```csv
101,ENTRADA,5,2026-10-22T14:30:15
```

Ao iniciar o programa, os arquivos são lidos e os dados são carregados para as listas em memória. As movimentações são associadas aos respectivos produtos por meio do código cadastrado.

Quando uma operação altera os dados, os arquivos correspondentes são atualizados, permitindo recuperar as informações em execuções futuras.

**Observação:** a implementação utiliza separação simples por vírgulas. Portanto, os campos de texto não devem conter vírgulas.

## 7. Como executar o projeto

### Requisitos

- Java Development Kit (JDK) instalado.
- IDE compatível com Java, como o Eclipse.

### Passos para execução

1. Clone ou baixe este repositório.
2. Abra o Eclipse.
3. Importe ou abra o projeto na IDE.
4. Localize a classe `Programa.java`, dentro do pacote `aplicacao`.
5. Execute a classe como uma aplicação Java.
6. Utilize as opções apresentadas no terminal para acessar as funcionalidades.

Na primeira execução, caso os arquivos de dados ainda não existam, o sistema inicia com listas vazias. Os arquivos serão criados quando ocorrer a primeira gravação de dados.

## 8. Testes realizados

Durante o desenvolvimento, foram realizados testes manuais com dados fictícios para verificar o comportamento das principais funcionalidades.

Entre os cenários avaliados estão:

- Cadastro de produtos com dados válidos.
- Tentativa de cadastro com código já existente.
- Tentativa de cadastro com quantidade inicial negativa.
- Busca de produtos cadastrados e não cadastrados.
- Registro de entradas e saídas.
- Tentativa de movimentação com quantidade inválida.
- Tentativa de saída superior ao saldo disponível.
- Consulta de produtos com estoque baixo.
- Geração de relatório de quantidades e valores.
- Recuperação dos dados após encerrar e executar novamente o programa.

Os testes e exemplos de execução também estão documentados no artigo do Projeto Integrador.

## 9. Limitações e melhorias futuras

O sistema atende ao escopo acadêmico proposto, mas possui limitações que podem ser trabalhadas em versões futuras.

Entre as possibilidades de evolução estão:

- Implementação de pesquisa binária, com organização prévia da lista.
- Avaliação de desempenho com volumes maiores de produtos.
- Substituição dos arquivos CSV por um banco de dados.
- Ampliação das validações dos dados informados pelo usuário.
- Tratamento de entradas não numéricas no menu.
- Aprimoramento da leitura e gravação de arquivos CSV.
- Desenvolvimento de uma interface gráfica.
- Inclusão de novos filtros e relatórios gerenciais.

## 10. Contexto acadêmico

**Instituição:** Universidade Santo Amaro – UNISA  
**Curso:** Análise e Desenvolvimento de Sistemas  
**Disciplina:** Projeto Integrador – Laboratório de Programação  
**Aluno:** Matheus Silva Duarte  
**RA:** 5938571  
**Professor orientador:** Patrick Anderson Matias de Araujo

---

**Repositório:** [Sistema de Controle de Estoque em Java](https://github.com/oMatheussd/sistema-controle-estoque-java)

Projeto desenvolvido para fins acadêmicos, com o propósito de aplicar conceitos de programação orientada a objetos, algoritmos, estruturas de dados e persistência de informações na construção de uma solução de controle de estoque.