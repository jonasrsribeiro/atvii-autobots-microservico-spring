# AutoBots - Sistema para gestão de lojas especializadas em manutenção veicular e vendas de autopeças. 

Desenvolvimento de um CRUD do cadastro das informações do cliente.
Nesta primeira etapa do projeto, o desenvolvimento segue os principais padrões de projeto e conceitos SOLID.

## :gear: Padrões de Projeto Aplicados

| Padrão de Projeto                   | Localização               | Descrição                                     |
| ----------------------------------- | ------------------------- | --------------------------------------------- |
| :hammer: Builder |   Post de Clientes                        | Os Clientes são construídos por várias entidades: Cliente, Documentos, Endereço e Telefones. Seria necessário implementar um construtor para cada rota post de Cliente.  |
| :dart: Strategy |   Todos os CRUDS                       |  Facilitar a flexibilidade, leitura e manutenção do código. |
| :page_facing_up: Template Method |    Seleção de Entidade por Id                       |  O código se repetia muitas vezes e para cada nova entidade de surgir e precise ser implementada, permitindo flexibilidade. |


## :railway_track: Rotas disponíveis

### Controle de Clientes

| Método | Rota                   | Descrição                           |
| ------ | ---------------------- | ----------------------------------- |
| [![](https://img.shields.io/badge/PUT-9370DB?style=for-the-badge)]() | `/cliente/atualizar`   | Atualiza um cliente existente       |
| [![](https://img.shields.io/badge/POST-4682B4?style=for-the-badge)]()   | `/cliente/cadastro`    | Cadastra um novo cliente            |
| [![](https://img.shields.io/badge/GET-2E8B57?style=for-the-badge)]() | `/cliente/cliente/{id}`| Obtém um cliente pelo ID             |
| [![](https://img.shields.io/badge/GET-2E8B57?style=for-the-badge)]() | `/cliente/clientes`    | Obtém todos os clientes              |
| [![](https://img.shields.io/badge/DELETE-CD853F?style=for-the-badge)]() | `/cliente/excluir`     | Exclui um cliente                    |

### Controle de Endereços

| Tipo                                                                 | Rota                  | Ação                              |
| -------------------------------------------------------------------- | --------------------- | ---------------------------------- |
| [![](https://img.shields.io/badge/GET-2E8B57?style=for-the-badge)]() | `/endereco/enderecos`             | Listagem de endereços              |
| [![](https://img.shields.io/badge/GET-2E8B57?style=for-the-badge)]() | `/endereco/endereco/{id}`     | Dados de um endereço específico    |
| [![](https://img.shields.io/badge/POST-4682B4?style=for-the-badge)]() | `/endereco/cadastro`             | Cadastro de endereços              |
| [![](https://img.shields.io/badge/PUT-9370DB?style=for-the-badge)]() | `/endereco/atualizar` | Alteração do endereço    |
| [![](https://img.shields.io/badge/DELETE-CD853F?style=for-the-badge)]()| `/endereco/excluir`    | Exclusão de um endereço específico |

### Controle de Telefones

| Tipo                                                                 | Rota                  | Ação                              |
| -------------------------------------------------------------------- | --------------------- | ---------------------------------- |
| [![](https://img.shields.io/badge/GET-2E8B57?style=for-the-badge)]() | `/telefone/telefones`             | Listagem de telefones              |
| [![](https://img.shields.io/badge/GET-2E8B57?style=for-the-badge)]() | `/telefone/telefone/{id}`     | Dados de um telefone específico    |
| [![](https://img.shields.io/badge/POST-4682B4?style=for-the-badge)]() | `/telefone/cadastro`             | Cadastro de telefones              |
| [![](https://img.shields.io/badge/PUT-9370DB?style=for-the-badge)]() | `/telefone/atualizar` | Alteração do perfil do telefone    |
| [![](https://img.shields.io/badge/DELETE-CD853F?style=for-the-badge)]()| `/telefone/excluir`    | Exclusão de um telefone específico |

### Controle de Documentos

| Tipo                                                                 | Rota                  | Ação                              |
| -------------------------------------------------------------------- | --------------------- | ---------------------------------- |
| [![](https://img.shields.io/badge/GET-2E8B57?style=for-the-badge)]() | `/documento/documentos`             | Listagem de documentos              |
| [![](https://img.shields.io/badge/GET-2E8B57?style=for-the-badge)]() | `/documento/documento/{id}`     | Dados de um documento específico    |
| [![](https://img.shields.io/badge/POST-4682B4?style=for-the-badge)]() | `/documento/cadastro`             | Cadastro de documentos              |
| [![](https://img.shields.io/badge/PUT-9370DB?style=for-the-badge)]() | `/documento/atualizar` | Alteração do perfil do documento    |
| [![](https://img.shields.io/badge/DELETE-CD853F?style=for-the-badge)]()| `/documento/excluir`    | Exclusão de um documento específico |
