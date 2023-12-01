package entities;

import java.math.BigDecimal;
import java.time.LocalDate;

class Funcionario extends Pessoa {
    BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
}


