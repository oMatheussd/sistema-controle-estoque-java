# Sistema de Controle de Estoque

## Sobre o projeto

Este projeto foi desenvolvido em Java como parte do Projeto Integrador da disciplina Laboratório de Programação da UNISA.

O sistema tem como objetivo auxiliar no controle de estoque, permitindo cadastrar produtos, acompanhar suas quantidades e registrar entradas e saídas de mercadorias.

## Funcionalidades

O programa oferece as seguintes funcionalidades:

- Cadastro de produtos com código, nome, categoria, preço, quantidade e estoque mínimo.
- Listagem de produtos em ordem crescente de código.
- Busca de produtos pelo código.
- Registro de entradas e saídas de produtos.
- Consulta de produtos com estoque baixo.
- Geração de relatório com quantidades e valores do estoque.
- Consulta do histórico de movimentações, incluindo tipo, quantidade, data e hora.
- Salvamento e carregamento dos dados por meio de arquivos CSV.

## Tecnologias utilizadas

- **Java:** linguagem utilizada no desenvolvimento.
- **Programação orientada a objetos:** organização do sistema em classes.
- **Arquivos CSV:** armazenamento dos dados dos produtos e das movimentações.

## Como executar

1. Tenha o Java Development Kit (JDK) instalado.
2. Baixe ou clone o repositório do projeto.
3. Abra o projeto em uma IDE compatível com Java, como o Eclipse.
4. Localize a classe `Programa.java`, no pacote `aplicacao`.
5. Execute a classe como uma aplicação Java.
6. Utilize o menu exibido no terminal para acessar as funcionalidades do sistema.

## Armazenamento dos dados

Os dados são armazenados na pasta `dados`, nos arquivos `produtos.csv` e `movimentacoes.csv`. O sistema utiliza esses arquivos para recuperar as informações salvas quando é executado novamente.

## Organização do projeto

O código-fonte está dividido nos seguintes pacotes:

- **`aplicacao`:** contém a classe principal responsável pela execução do programa e pela apresentação do menu.
- **`entidades`:** contém as classes que representam os produtos e as movimentações, além dos tipos de movimentação.
- **`servicos`:** contém as operações de controle de estoque e os métodos responsáveis pela leitura e gravação dos arquivos.

## Projeto acadêmico

Projeto desenvolvido para fins acadêmicos no âmbito do Projeto Integrador da UNISA — Laboratório de Programação.
