package Gerenciamento_de_Colaboradores;

public interface Auditavel {
    void registrarAtividade(String atividade);
    void auditar();
}