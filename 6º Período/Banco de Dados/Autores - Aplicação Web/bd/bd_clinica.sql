DROP DATABASE IF EXISTS Autores;
CREATE DATABASE Autores;
USE Autores;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
--
-- Banco de dados: clinicaA
--
-- --------------------------------------------------------
--
-- Estrutura da tabela especialidade
--
CREATE TABLE nacionalidade (
  ID_Nacionalidade INT NOT NULL,
  Nacionalidade VARCHAR(100) NOT NULL
  ) ;
--
-- Inserindo dados da tabela especialidade
--
INSERT INTO nacionalidade (ID_Nacionalidade, Nacionalidade) VALUES
(1, 'Francesa'),
(2, 'Britânica'),
(3, 'Americana'),
(4, 'Italiana'),
(5, 'Alemã'),
(6, 'Espanhola'),
(7, 'Brasileira'),
(8, 'Japonesa'),
(9, 'Canadense'),
(10, 'Australiana');


-- --------------------------------------------------------
--
-- Estrutura da tabela medico
--
CREATE TABLE Autor (
  ID_Autor INT NOT NULL,
  Dt_Fale DATE DEFAULT NULL,
  Nome VARCHAR(150) NOT NULL,
  
  Dt_Nasc DATE DEFAULT NULL,
  Foto MEDIUMBLOB DEFAULT NULL,
  ID_Nacionalidade INT NOT NULL
) ;
--
-- Inserindo dados da tabela medico
--
INSERT INTO Autor (ID_Autor, Dt_Fale, Nome, Dt_Nasc, Foto,Id_Nacionalidade) VALUES
(1, NULL, 'J.K. Rowling', '1965-07-31',null,2),
(2, null, 'Neil Gaiman', '1960-11-10',null,2),
(3, '2015-03-15', 'Terry Pratchett', '1948-04-28', NULL,4),
(4, '1888-03-06', 'Louisa May Alcott', '1832-11-29', NULL,3),
(5, '1924-10-29', 'Frances Hodgson Burnett', '1849-11-24', NULL,5),
(6,  '2016-02-19', 'Harper Lee', '1926-04-28', NULL,3),
(7, '1817-07-18', 'Jane Austen', '1775-12-16', NULL,8),
(8, '1963-11-22', 'Aldous Huxley', '1894-07-26', NULL,7),
(9, '1940-12-21', 'F. Scott Fitzgerald', '1896-09-24', NULL,9),
(10, '1944-07-31', 'Antoine de Saint-Exupéry', '1900-06-29', NULL,1);

-- Índices para tabelas despejadas
--
--
-- Índices para tabela especialidade
--
ALTER TABLE nacionalidade
  ADD PRIMARY KEY (ID_Nacionalidade);
--
-- Índices para tabela medico
--
ALTER TABLE Autor
  ADD PRIMARY KEY (ID_Autor),
  
  ADD KEY FK_ID_Nacionalidade (ID_Nacionalidade);
-- AUTO_INCREMENT de tabelas 
--
-- AUTO_INCREMENT de tabela especialidade
--
ALTER TABLE nacionalidade
  MODIFY ID_Nacionalidade INT NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de tabela medico
--
ALTER TABLE Autor
  MODIFY ID_Autor INT NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Restrições para tabelas
--
--
-- Limitadores para a tabela medico
--
ALTER TABLE Autor
  ADD CONSTRAINT FK_ID_Nacionalidade FOREIGN KEY (ID_Nacionalidade) REFERENCES nacionalidade (ID_Nacionalidade);
  
COMMIT;