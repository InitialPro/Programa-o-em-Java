Aplique os conceitos de persistência com JPA e o padrão DAO para gerenciar uma base de dados (alunos). Crie uma aplicação simples que insira, liste e remova alunos do banco usando DAO e os conceitos aprendidos, sem interface gráfica.

 

1. Criar a classe Aluno, anotada com @Entity, mapeando os campos:


matricula (String) – chave primária
 

nome (String)
 

ano (int)

2. Criar o arquivo persistence.xml para conexão do banco Apache Derby local.

 

3. Criar a classe AlunoDAO com os métodos:


void inserir(Aluno aluno)
 

List<Aluno> listarTodos()
 

void remover(String matricula)

4. Criar uma classe Main que:


Insira ao menos 3 alunos.
 

Liste os alunos no console com System.out.println.
 

Remova um aluno pela matrícula.
 

Liste novamente, verificando se a exclusão foi bem-sucedida.