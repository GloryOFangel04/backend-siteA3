📚 README - Projeto Final A3 - Livraria Online

📝 Sobre o Projeto
Olá! Eu sou Glória Andrade, estudante de Ciência da Computação na Anhembi Morumbi Paulista 1. Este projeto é meu trabalho final da avaliação A3 do semestre.


O projeto é uma simulação de uma livraria online, onde o usuário pode:

🆕 Criar um cadastro e gerenciar seu perfil.

🔑 Realizar login e logout na plataforma.

🛒 Navegar pelos produtos disponíveis.

💳 Adicionar itens ao carrinho e efetuar pagamentos.

O sistema foi desenvolvido utilizando Spring Boot para a criação da API, com armazenamento de dados em um banco de dados MySQL (MySQL Workbench).

💡 Apesar de ser um projeto em grupo com Gabriel Welzel, ele não contribuiu de forma significativa para o desenvolvimento.

⚙️ Funcionalidades
O sistema possui as seguintes páginas e funcionalidades:

Página de Login: Permite ao usuário se autenticar na plataforma.
Página de Cadastro: O usuário pode se cadastrar para criar uma conta.
Página Principal do Site: Exibe os produtos disponíveis na livraria.
Carrinho de Compras: Permite ao usuário adicionar itens ao carrinho.
Página de Pagamento: Realiza o processo de pagamento.
Logout: Possibilidade de o usuário sair da conta com segurança.
🛠 Tecnologias Utilizadas
As tecnologias e dependências utilizadas no projeto incluem:

Spring Boot: Framework para desenvolvimento da API.
JPA (Java Persistence API): Para a interação com o banco de dados.
Thymeleaf: Motor de templates para renderizar as páginas HTML.
DevTools: Para aceleração do desenvolvimento, com reinício automático.
Lombok: Redução do código boilerplate (getters, setters, construtores).
MS SQL Server Driver: Configuração da conexão com o banco de dados.
🏗 Arquitetura
O projeto segue a arquitetura MVC (Model-View-Controller) com os seguintes componentes:

Controllers: 4 controllers responsáveis por gerenciar as requisições e respostas.
Repositories: 2 repositórios para a comunicação com o banco de dados.
Models: 2 classes de modelo que representam as entidades principais.
🗄 Banco de Dados
O banco de dados utilizado é o MySQL (MySQL Workbench). Ele possui duas tabelas principais:

Tabela usuario
sql
Copiar código
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    celular VARCHAR(20) NOT NULL
);
Campos armazenados:
ID único
Nome e Sobrenome
Email (único para cada usuário)
Senha e Celular
Tabela compra
sql
Copiar código
CREATE TABLE compra (
    id_compra INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    email VARCHAR(300),
    endereco VARCHAR(300),
    cidade VARCHAR(300),
    estado VARCHAR(300),
    data_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    quantidade INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
);
Campos armazenados:
ID da compra
ID do usuário (referenciado na tabela usuario)
Dados de entrega (endereço, cidade, estado)
Quantidade de itens comprados e o valor total.

é isso bjs
