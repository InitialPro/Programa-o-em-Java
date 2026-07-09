# Programa-o-em-Java
Vamos praticar? Para consolidar os conceitos de herança, coleções, encapsulamento e vinculação dinâmica de métodos, desenvolva um sistema simples para controle de contas bancárias em uma agência digital.

 

Crie uma classe abstrata chamada Conta com os seguintes atributos encapsulados:
 
titular (String)
 
numero (int)
 
saldo (double)
 

2. Implemente na classe Conta:

 

Um construtor que inicialize todos os atributos.
 
Um método depositar(double valor), que adicione valor ao saldo apenas se for positivo.
 
Um método abstrato sacar(double valor), que será implementado nas subclasses.
 
Um método exibirInformacoes() que imprime no console os dados da conta (titular, número, saldo).
 

3. Crie duas subclasses:
 

ContaCorrente
 
ContaPoupanca
 

4. Em cada subclasse, implemente o método sacar(double valor) com regras diferentes:
 

ContaCorrente: permite saque, desde que o valor não ultrapasse o saldo.
 
ContaPoupanca: permite saque apenas se o valor for múltiplo de 10 e não exceder o saldo.
 

5. No método main:

 

Crie uma lista (ArrayList) de contas do tipo Conta.
 
Adicione, pelo menos, duas contas de cada tipo (ContaCorrente e ContaPoupanca).
 
Para cada conta:
 
Realize um depósito de valor arbitrário.
 
Tente realizar um saque.
 
Exiba as informações da conta.
 

Dicas:

 

Use ArrayList<Conta> e aplique polimorfismo dinâmico: o método sacar() se comportará de forma diferente, dependendo do tipo da conta.
 
Os atributos devem ser private, com modificadores de acesso apropriados.
 
Valide os dados nas operações para ter integridade.
 
Use mensagens no System.out.println() para orientar o aluno sobre cada operação.
