README - Projeto Final A3 - Livraria Online
Sobre o Projeto
Olá! Eu sou a Glória S. B. Andrade, estudante de Ciência da Computação na Anhembi Morumbi Paulista 1. Este projeto é o meu trabalho final da avaliação A3 do semestre.

O projeto é uma simulação de uma livraria online, onde o usuário pode:

Criar um cadastro e gerenciar seu perfil.
Realizar login e logout na plataforma.
Navegar pelos produtos disponíveis.
Adicionar itens ao carrinho e efetuar pagamentos.
O sistema foi desenvolvido utilizando Spring Boot para a criação da API, e todos os dados são armazenados em um banco de dados MySQL (MySQL Workbench).

Apesar de ter feito o projeto em parceria com Gabriel Welzel, ele não contribuiu de forma significativa para o desenvolvimento.

Funcionalidades
O sistema possui as seguintes páginas e funcionalidades:

Página de Login: Permite ao usuário se autenticar na plataforma.
Página de Cadastro: O usuário pode se cadastrar para criar uma conta.
Página Principal do Site: Exibe os produtos disponíveis na livraria.
Carrinho de Compras: Permite ao usuário adicionar itens ao carrinho.
Página de Pagamento: Realiza o processo de pagamento.
Logout: Possibilidade de o usuário sair da conta com segurança.
Tecnologias Utilizadas
Spring Boot: Framework utilizado para desenvolver a API.
JPA (Java Persistence API): Para a interação com o banco de dados.
Thymeleaf: Motor de templates para renderizar as páginas HTML.
DevTools: Para acelerar o desenvolvimento, com reinício automático.
Lombok: Para reduzir o código boilerplate (getters, setters, construtores).
MS SQL Server Driver: Para configurar a conexão com o banco de dados.
Arquitetura
O projeto segue a arquitetura MVC (Model-View-Controller), com os seguintes componentes principais:

Controllers: 4 controllers responsáveis por gerenciar as requisições e as respostas para as páginas.
Repositories: 2 repositórios que fazem a comunicação com o banco de dados.
Models: 2 classes de modelo que representam as entidades principais do sistema.
Banco de Dados
O banco de dados utilizado é o MySQL (MySQL Workbench). Ele contém duas tabelas principais:

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
Armazena as informações dos usuários cadastrados no sistema, incluindo:

ID único, nome, email (único), e senha.
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
Registra as compras realizadas pelos usuários, incluindo:

Informações de entrega, quantidade de itens, e o total da compra.
