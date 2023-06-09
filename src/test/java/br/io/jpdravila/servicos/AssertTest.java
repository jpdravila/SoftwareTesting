package br.io.jpdravila.servicos;

import br.io.jpdravila.entidades.Usuario;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertTest {

    @Test
    public void test(){
        Assert.assertTrue(true);
        Assert.assertFalse(false);

        Assert.assertEquals("Erro de comparação",1, 1);
        Assert.assertEquals(0.51, 0.51, 0.01);
        Assert.assertEquals(Math.PI, 3.14, 0.14);

        int i = 5;
        Integer i2 = 5;

        Assert.assertEquals(Integer.valueOf(i), i2);
        Assert.assertEquals(i, i2.intValue());

        Assert.assertEquals("Erro de comparação","bola", "bola");
        Assert.assertNotEquals("bola", "casa");
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("bola".startsWith("bo"));

        Usuario u1 = new Usuario("Usuario 1");
        Usuario u2 = new Usuario("Usuario 1");
        Usuario u3 = null;

        Assert.assertEquals(u1, u2);
        Assert.assertSame(u2, u2);
        Assert.assertNotSame(u1, u2);
        Assert.assertNull(u3);
        Assert.assertNotNull(u1);
    }

}
