package entities;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        inserirFuncionarios(funcionarios);

        // 3.2 - Remover o funcion�rio �Jo�o� da lista.
        funcionarios.removeIf(funcionario -> "Jo�o".equals(funcionario.getNome()));

        // 3.3 - Imprimir todos os funcion�rios com todas suas informa��es.
        imprimirFuncionarios(funcionarios);

        // 3.4 - Os funcion�rios receberam 10% de aumento de sal�rio, atualizar a lista de funcion�rios com novo valor.
        aumentarSalarios(funcionarios, BigDecimal.valueOf(0.10));

        // 3.5 - Agrupar os funcion�rios por fun��o em um MAP.
        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparPorFuncao(funcionarios);

        // 3.6 - Imprimir os funcion�rios, agrupados por fun��o.
        imprimirFuncionariosAgrupados(funcionariosPorFuncao);

        // 3.8 - Imprimir os funcion�rios que fazem anivers�rio no m�s 10 e 12.
        imprimirAniversariantes(funcionarios, Month.OCTOBER, Month.DECEMBER);

        // 3.9 - Imprimir o funcion�rio com a maior idade.
        imprimirFuncionarioMaiorIdade(funcionarios);

        // 3.10 - Imprimir a lista de funcion�rios por ordem alfab�tica.
        ordenarFuncionariosPorNome(funcionarios);
        imprimirFuncionarios(funcionarios);

        // 3.11 - Imprimir o total dos sal�rios dos funcion�rios.
        imprimirTotalSalarios(funcionarios);

        // 3.12 - Imprimir quantos sal�rios m�nimos ganha cada funcion�rio.
        imprimirSalariosMinimos(funcionarios);
    }

    private static void inserirFuncionarios(List<Funcionario> funcionarios) {
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("Jo�o", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Direto"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("407.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Helo�sa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        System.out.println("Lista de Funcion�rios:");
        for (Funcionario funcionario : funcionarios) {
            System.out.printf("Nome: %s, Data de Nascimento: %s, Sal�rio: %.2f, Fun��o: %s%n",
                    funcionario.getNome(),
                    funcionario.getDataNascimento(),
                    funcionario.getSalario(),
                    funcionario.getFuncao());
        }
        System.out.println();
    }

    private static void aumentarSalarios(List<Funcionario> funcionarios, BigDecimal percentualAumento) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(BigDecimal.ONE.add(percentualAumento));
            funcionario.salario = novoSalario;
        }
    }

    private static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    private static void imprimirFuncionariosAgrupados(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        System.out.println("Funcion�rios Agrupados por Fun��o:");
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.printf("Fun��o: %s%n", funcao);
            for (Funcionario funcionario : listaFuncionarios) {
                System.out.printf("  Nome: %s, Data de Nascimento: %s, Sal�rio: %.2f%n",
                        funcionario.getNome(),
                        funcionario.getDataNascimento(),
                        funcionario.getSalario());
            }
        });
        System.out.println();
    }

    private static void imprimirAniversariantes(List<Funcionario> funcionarios, Month... meses) {
        System.out.println("Aniversariantes nos Meses 10 e 12:");
        for (Funcionario funcionario : funcionarios) {
            if (Arrays.asList(meses).contains(funcionario.getDataNascimento().getMonth())) {
                System.out.printf("Nome: %s, Data de Nascimento: %s%n",
                        funcionario.getNome(),
                        funcionario.getDataNascimento());
            }
        }
        System.out.println();
    }

    private static void imprimirFuncionarioMaiorIdade(List<Funcionario> funcionarios) {
        System.out.println("Funcion�rio com Maior Idade:");
        Funcionario funcionarioMaiorIdade = Collections.max(funcionarios,
                Comparator.comparing(func -> func.getDataNascimento()));
        int idade = LocalDate.now().getYear() - funcionarioMaiorIdade.getDataNascimento().getYear();
        System.out.printf("Nome: %s, Idade: %d anos%n",
                funcionarioMaiorIdade.getNome(),
                idade);
        System.out.println();
    }

    private static void ordenarFuncionariosPorNome(List<Funcionario> funcionarios) {
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
    }

    private static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        System.out.println("Total dos Sal�rios dos Funcion�rios:");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Total: %.2f%n", totalSalarios);
        System.out.println();
    }

    private static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        System.out.println("Sal�rios em Sal�rios M�nimos:");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioEmMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.printf("Nome: %s, Sal�rio em Sal�rios M�nimos: %.2f%n",
                    funcionario.getNome(),
                    salarioEmMinimos);
        }
    }
}