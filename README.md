# Auto Center Silva - Sistema de Gestão de Produtos e Serviços

> Sistema de gerenciamento via terminal (CLI) desenvolvido em Java puro para a Auto Center Silva. Permite autenticação de usuários e o controle completo de produtos e serviços da oficina, com operações de CRUD, filtros de busca, relatórios estatísticos e validações de entrada.

---

## Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Arquitetura e Design Orientado a Objetos](#-arquitetura-e-design-orientado-a-objetos)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Pré-requisitos](#-pré-requisitos)
- [Como Executar](#-como-executar)
- [Fluxo de Navegação](#-fluxo-de-navegação)
- [Credenciais Padrão](#-credenciais-padrão)
- [Equipe](#-equipe)

---

## Sobre o Projeto

O **Auto Center Silva** é um sistema de gestão desenvolvido inteiramente em **Java**, executado pelo terminal, para controle interno de uma oficina mecânica. O projeto aplica na prática os principais conceitos de **Programação Orientada a Objetos** — herança, abstração, encapsulamento e polimorfismo — organizados em camadas de modelo (`model`) e serviço (`service`), em uma arquitetura limpa e extensível.

O sistema exige autenticação prévia, possui menus interativos com feedback colorido via **ANSI Escape Codes** e realiza validações robustas em todas as entradas do usuário.

---

## Funcionalidades

### Autenticação
- Login com **e-mail e senha**, com limite de **3 tentativas** antes de retornar ao menu inicial
- Cadastro de novos usuários com verificação de e-mail duplicado
- Boas-vindas personalizadas exibindo o nome e perfil do usuário logado
- Despedida personalizada ao encerrar o sistema

### Gerenciamento de Produtos
| Operação | Descrição |
|---|---|
| **Cadastrar** | Registra produto com nome, preço, estoque e categoria (menu hierárquico) |
| **Listar** | Exibe todos os produtos cadastrados com seus detalhes completos |
| **Atualizar** | Edita os dados de um produto existente pelo ID, com confirmação para estoque zerado |
| **Remover** | Remove um produto pelo ID, listando os itens antes para facilitar a escolha |
| **Buscar por Nome** | Filtra produtos por correspondência parcial no nome (case-insensitive) |
| **Buscar por Categoria** | Filtra produtos pela categoria (case-insensitive) |
| **Relatório** | Exibe total de tipos, unidades em estoque, valor total do estoque, preço médio ponderado, produto mais caro e mais barato |

**Categorias disponíveis (menu hierárquico):**
- Pneus → Pneus de Carro / Pneus de Caminhonete / Pneus de Caminhão
- Válvulas e Bicos
- Câmaras de Ar
- Produtos Químicos

### Gerenciamento de Serviços
| Operação | Descrição |
|---|---|
| **Cadastrar** | Registra serviço com nome, preço, duração (em minutos) e tipo |
| **Listar** | Exibe todos os serviços com seus detalhes completos |
| **Atualizar** | Edita os dados de um serviço existente pelo ID |
| **Remover** | Remove um serviço pelo ID |
| **Buscar por Nome** | Filtra serviços por correspondência parcial no nome (case-insensitive) |
| **Buscar por Tipo** | Filtra serviços pelo tipo (ex: Troca, Revisão, Alinhamento) |
| **Relatório** | Exibe total de serviços, preço médio, duração média, serviço mais caro e mais rápido |

### Validações de Entrada
- Campos de texto não podem ser vazios
- Preço não pode ser zero ou negativo (loop até valor válido)
- Estoque zerado solicita confirmação explícita (`S/N`) antes de salvar
- Entradas numéricas inválidas (letras no lugar de números) são tratadas com `try/catch` sem quebrar o sistema
- Decimais aceitam tanto ponto (`.`) quanto vírgula (`,`) como separador

---

## Arquitetura e Design Orientado a Objetos

O projeto é estruturado em duas camadas bem definidas e aplica quatro pilares da POO:

### Diagrama de Classes

```
                    ┌─────────────┐
                    │  «abstract» │
                    │    Item     │
                    │─────────────│
                    │ - id: int   │
                    │ - nome: str │
                    │ - preco: dbl│
                    │─────────────│
                    │ + getId()   │
                    │ + getNome() │
                    │ + getPreco()│
                    │ + exibirDetalhes()* │
                    └──────┬──────┘
                           │  herança
               ┌───────────┴───────────┐
               ▼                       ▼
        ┌─────────────┐         ┌─────────────┐
        │   Produto   │         │   Servico   │
        │─────────────│         │─────────────│
        │ - estoque   │         │ - duracao   │
        │ - categoria │         │ - tipo      │
        └─────────────┘         └─────────────┘

                    ┌─────────────┐
                    │  «abstract» │
                    │   Usuario   │
                    │─────────────│
                    │ - nome      │
                    │ - email     │
                    │ - senha     │
                    │─────────────│
                    │ + exibirPerfil()* │
                    └──────┬──────┘
                           │  herança
                           ▼
                    ┌─────────────┐
                    │    Admin    │
                    │─────────────│
                    │ exibirPerfil() → "[ ADMIN ] nome | email" │
                    └─────────────┘
```

### Pilares da POO Aplicados

| Pilar | Aplicação no Projeto |
|---|---|
| **Herança** | `Produto` e `Servico` herdam de `Item`; `Admin` herda de `Usuario` |
| **Abstração** | `Item` e `Usuario` são classes abstratas que definem contrato via método `abstract` |
| **Encapsulamento** | Todos os atributos são `private`, acessados apenas por getters/setters públicos |
| **Polimorfismo** | `exibirDetalhes()` e `exibirPerfil()` têm comportamento distinto em cada subclasse; `toString()` em `Item` delega para `exibirDetalhes()` automaticamente |

### Camadas da Aplicação

| Camada | Pacote | Responsabilidade |
|---|---|---|
| **Model** | `model/` | Representação das entidades do domínio (dados + comportamento) |
| **Service** | `service/` | Lógica de negócio, CRUD em memória e relatórios |
| **Main** | `Main.java` | Interface com o usuário via terminal, menus e validações de entrada |

---

## Estrutura do Projeto

```
projetomensal-backend-01/
│
├── pom.xml                             # Configuração Maven (Java 17, UTF-8)
│
└── src/
    └── main/
        └── java/
            │
            ├── Main.java               # Ponto de entrada — menus, fluxos e leitura de inputs
            │
            ├── model/
            │   ├── Item.java           # Classe abstrata base para Produto e Servico
            │   ├── Produto.java        # Entidade produto (herda Item) — estoque + categoria
            │   ├── Servico.java        # Entidade serviço (herda Item) — duração + tipo
            │   ├── Usuario.java        # Classe abstrata base para usuários do sistema
            │   └── Admin.java          # Tipo de usuário administrador (herda Usuario)
            │
            └── service/
                ├── AuthService.java    # Autenticação: login, cadastro e busca por e-mail
                ├── ProdutoService.java # CRUD de produtos, filtros e relatório
                └── ServicoService.java # CRUD de serviços, filtros e relatório
```

---

## Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|---|---|---|
| **Java** | 17 | Linguagem principal do projeto |
| **Maven** | — | Gerenciamento de build e dependências |
| **ANSI Escape Codes** | — | Cores no terminal (ciano, azul, roxo, verde, vermelho, amarelo) |

O projeto não possui dependências externas além do JDK. Toda a lógica é implementada com a biblioteca padrão do Java.

---

## Pré-requisitos

- **Java JDK 17** ou superior instalado
- **Maven** instalado (ou use a IDE com suporte nativo ao Maven)

Verifique as instalações:
```bash
java -version
mvn -version
```

---

## Como Executar

### Opção 1 — Via Maven (terminal)

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/projetomensal-backend-01.git
cd projetomensal-backend-01

# Compile o projeto
mvn compile

# Execute o sistema
mvn exec:java -Dexec.mainClass="Main"
```

### Opção 2 — Via IntelliJ IDEA (recomendado)

1. Abra o IntelliJ IDEA
2. Selecione **File → Open** e escolha a pasta do projeto
3. Aguarde o Maven carregar o `pom.xml`
4. Abra o arquivo `Main.java`
5. Clique no botão ▶️ ao lado do método `main` para executar

### Opção 3 — Compilação manual (javac)

```bash
# A partir da raiz do projeto
javac -d out src/main/java/model/*.java src/main/java/service/*.java src/main/java/Main.java

# Execute
java -cp out Main
```

> **Atenção:** Execute em um terminal que suporte **ANSI Escape Codes** (Linux, macOS, Windows Terminal ou IntelliJ) para visualizar as cores corretamente.

---

## Fluxo de Navegação

```
Início
  └── Tela de Login
        ├── 1. Entrar
        │     └── Informe e-mail e senha (3 tentativas)
        │           └── Login bem-sucedido → Menu Principal
        │                 ├── 1. Gerenciar Produtos
        │                 │     ├── 1. Cadastrar Produto
        │                 │     ├── 2. Listar Produtos
        │                 │     ├── 3. Atualizar Produto
        │                 │     ├── 4. Remover Produto
        │                 │     ├── 5. Buscar por Nome
        │                 │     ├── 6. Buscar por Categoria
        │                 │     ├── 7. Relatório de Produtos
        │                 │     └── 0. Voltar
        │                 ├── 2. Gerenciar Serviços
        │                 │     ├── 1. Cadastrar Serviço
        │                 │     ├── 2. Listar Serviços
        │                 │     ├── 3. Atualizar Serviço
        │                 │     ├── 4. Remover Serviço
        │                 │     ├── 5. Buscar por Nome
        │                 │     ├── 6. Buscar por Tipo
        │                 │     ├── 7. Relatório de Serviços
        │                 │     └── 0. Voltar
        │                 └── 0. Sair
        ├── 2. Cadastrar novo usuário
        └── 0. Sair
```

---

## Credenciais Padrão

O sistema já possui um usuário administrador pré-cadastrado para acesso imediato:

| Campo | Valor |
|---|---|
| **E-mail** | `admin@oficina.com` |
| **Senha** | `admin123` |

> Novos usuários podem ser cadastrados diretamente na tela de login. Todos os usuários criados recebem o perfil `Admin`.

---

## Equipe

Projeto desenvolvido por:

| Membro |
|---|
| Eduardo Florenciano dos Santos |
| Lucas Eduardo Zarza de Barros |
| Vittor Eduardo Flores Moreno |
