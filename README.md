# 📦 Sistema de Gerenciamento de Pedidos (Fila de Pedidos)

Este projeto foi desenvolvido em **Java** como parte da disciplina de **Estrutura de Dados**, com foco no uso prático da estrutura de dados **Fila (Queue)** para organizar pedidos em ordem cronológica.

## 🚀 Funcionalidades

- ✅ **Cadastro de pedidos** com validações de entrada
- 📄 **Listagem de pedidos**
- 🔍 **Consulta por ID, nome do cliente ou produto**
- ✏️ **Alteração de dados do pedido**
- ❌ **Remoção de pedidos**
- 💾 **Exportação e importação** de dados em JSON (usando a biblioteca **Gson**)
- 📝 **Relatório completo de ações** realizadas no sistema (com uso de `List` e `Map`)
- 🔐 **Validações robustas**: campos obrigatórios, tipos corretos, valores válidos, etc.

## 📚 Conceitos Aplicados

- Estrutura de dados: `Queue`, `List`, `Map`
- Manipulação de arquivos (leitura/escrita JSON)
- Validação de dados
- Controle por ID único
- Organização de código em **métodos e classes**
- Boas práticas de tratamento de exceções

## 💻 Tecnologias Utilizadas

- Java 24+
- Biblioteca [Gson](https://github.com/google/gson) para manipulação de arquivos JSON
- Execução via terminal (modo texto)

---

### 🧠 Objetivo

Demonstrar o uso prático de estruturas de dados para gerenciar um sistema simples de pedidos, simulando uma fila real de atendimento, com funcionalidades completas de CRUD e persistência de dados.

---

### 📁 Estrutura do Projeto

├── br/
│ └── com/
│ └── unijorge/
│ └── projetos/
│ └── estruturadedados/
│ └── av3/
│ ├── controller/
│ │ └── grcmPedidos.java // Lógica de gerenciamento dos pedidos
│ ├── model/
│ │ └── Pedido.java // Classe representando um pedido
│ └── view/
│ └── Principal.java // Classe principal que executa o menu do sistema
├── pedidos.json // Arquivo gerado/consumido para importação/exportação de dados
---

### 📌 Observações

- Todos os dados manipulados são mantidos em memória durante a execução e podem ser exportados/importados via arquivos JSON.
- Projeto focado em lógica e estrutura de dados — interface gráfica será desenvolvida em versões futuras (JavaFX/Swing).

---

### 👨‍💻 Autor

Desenvolvido por ANTÔNIO CARLOS, DAVID SOBRAL, MAYA MOTA, MELVIN ARAÚJO, PAULO MATEUS  — estudantes de Ciência da Computação | Unijorge  
