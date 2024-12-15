public class Main {
    public static void main(String[] args) {


        NumeroRacional q1 = new NumeroRacional(6, 2);
        NumeroRacional q2 = new NumeroRacional(2, 1);

        NumeroRacional soma = q1.soma(q2);
        NumeroRacional subtracao = q1.subtrai(q2);
        NumeroRacional mult = q1.multiplica(q2);
        NumeroRacional divisao = q1.divide(q2);

        System.out.println("\n----- Operações não simplificadas -----\n");
        System.out.println("Q1 = " + q1 + ";\nQ2 = " + q2 + ";\n");
        System.out.println(q1 + " + " + q2 + " = " + soma);
        System.out.println(q1 + " - " + q2 + " = " + subtracao);
        System.out.println(q1 + " * " + q2 + " = " + mult);
        System.out.println(q1 + " / " + q2 + " = " + divisao);

        q1.simplifica();
        q2.simplifica();
        soma.simplifica();
        subtracao.simplifica();
        mult.simplifica();
        divisao.simplifica();

        System.out.println("\n----- Operações simplificadas -----\n");
        System.out.println("Q1 = " + q1 + " (simplificada);\nQ2 = " + q2 + " (simplificada);\n");
        System.out.println(q1 + " + " + q2 + " = " + soma + " (simplificada)");
        System.out.println(q1 + " - " + q2 + " = " + subtracao + " (simplificada)");
        System.out.println(q1 + " * " + q2 + " = " + mult + " (simplificada)");
        System.out.println(q1 + " / " + q2 + " = " + divisao + " (simplificada)");
    }
}