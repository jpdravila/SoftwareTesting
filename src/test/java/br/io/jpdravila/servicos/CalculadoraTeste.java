package br.io.jpdravila.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

class NaoPodeDividirPorZeroException extends Exception{

}

class Calculadora{

    private Calculadora calc;

    @Before
    public void setup(){
        calc = new Calculadora();
    }

    public int somar(int a, int b){
        return a + b;
    }

    public int subtrair(int a, int b){
        return a - b;
    }

    public int divide(int a, int b) throws NaoPodeDividirPorZeroException {
        if(b == 0){
            throw new NaoPodeDividirPorZeroException();
        }
        return a / b;
    }

}

public class CalculadoraTeste {

    private Calculadora calc;

    @Before
    public void setup(){
        calc = new Calculadora();
    }

    @Test
    public void deveSomarDoisValore(){
        //Cenario
            int a = 5;
            int b = 3;
            //Calculadora calc = new Calculadora();
        //Ação
            int resultado = calc.somar(a, b);
        //Verificação
        Assert.assertEquals(8, resultado);

    }

    @Test
    public void deveSubtrairDoisValores(){
        //Cenario
            int a = 8;
            int b = 5;
        //Calculadora calc = new Calculadora();
        //Ação
            int resultado = calc.subtrair(a, b);

        //Verificação;
        Assert.assertEquals(3, resultado);
    }

    @Test
    public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
        //Cenario
        int a = 6;
        int b = 3;
        //Calculadora calc = new Calculadora();

        //Ação
        int resultado = calc.divide(a, b);

        //Verificação
        Assert.assertEquals(2, resultado);
    }

    @Test(expected = NaoPodeDividirPorZeroException.class)
    public void deveLancarExececaoAoDivivrPorZero() throws NaoPodeDividirPorZeroException {
        int a = 10;
        int b = 0;
        //Calculadora calc = new Calculadora();

        calc.divide(a, b);
    }

}
