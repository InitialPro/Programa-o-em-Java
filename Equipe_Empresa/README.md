Vamos praticar? Para consolidar os conceitos de encapsulamento, sobrescrita dos métodos herdados (toString, equals e hashCode) e uso do operador instanceof em hierarquias de classes, desenvolva um sistema para controle de cadastro e comparação de funcionários de uma empresa.

 

1. Crie uma classe chamada Funcionario com os seguintes atributos encapsulados (private):
 

nome (String)
 
identificador (String)
 
salario (double)
 

2. Implemente na classe Funcionario:
 

Um construtor que inicialize todos os atributos.
 
Métodos getters e setters para todos os atributos, aplicando validações apropriadas.
 
O salário não pode ser negativo.
 

Em seguida, sobrescreva o método toString() para exibir as informações do funcionário de forma legível, incluindo nome, identificador e salário. Sobrescreva ainda os métodos equals(Object obj) e hashCode() para que dois funcionários sejam considerados iguais se tiverem o mesmo identificador.

 

Por fim, implemente um método abstrato calcularBonus(), que retorna um double e deve ser implementado nas subclasses.

 

3. Crie duas subclasses de Funcionario:
 

Gerente
 
Desenvolvedor
 

4. Em cada subclasse, implemente o método calcularBonus() com regras diferentes:
 

Gerente: bônus é 20% do salário.
 
Desenvolvedor: bônus é 10% do salário.
 

5. No método main:
 

Crie uma lista (ArrayList<Funcionario>) e adicione, pelo menos, dois Gerentes e dois Desenvolvedores.
 
Para cada funcionário:

 

Exiba as informações usando toString().
 
Calcule e mostre o bônus.
 
Compare dois funcionários utilizando equals() e imprima se são iguais ou não.
 
Verifique com instanceof o tipo real de cada funcionário e imprima uma mensagem personalizada, como:
 
"O funcionário João é um Gerente."
 
"O funcionário Maria é uma Desenvolvedora."

Preste atenção nas dicas!

 

Todos os atributos devem ser private, com getters e setters públicos.
 
Objects.hash(...)implementam hashCode() de forma consistente. Portanto, utilize-os.
 
instanceof pode ser usado para diferenciar, de maneira dinâmica, se um objeto Funcionario é Gerente ou Desenvolvedor.
 
Valide os dados nas operações para certificar a integridade dos objetos.
 
Use mensagens no System.out.println() para guiar a execução do programa.