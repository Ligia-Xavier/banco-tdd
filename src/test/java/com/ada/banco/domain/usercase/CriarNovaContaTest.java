package com.ada.banco.domain.usercase;

import com.ada.banco.domain.gateway.ContaGateway;
import com.ada.banco.domain.model.Conta;
import com.ada.banco.dummy.ContaGatewayDummyImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CriarNovaContaTest {

    @Test
    public void DeveLancarExceptionCasoAContaJaExista(){
        //Given
        ContaGateway contaGateway = new ContaGatewayDummyImpl();
        CriarNovaConta criarNovaConta = new CriarNovaConta(contaGateway);
        Conta conta = new Conta(1L, 2L, 3L, BigDecimal.ZERO, "Pedro", "123456789");
        //When Then
        Throwable throwable = Assertions.assertThrows(
                Exception.class,
                () -> criarNovaConta.execute(conta)
        );
        Assertions.assertEquals("Usuario ja possui uma conta", throwable.getMessage());
    }
    @Test
    public void DeveCriarNovaConta() throws Exception {
        //Given
        ContaGateway contaGateway = new ContaGatewayDummyImpl();
        CriarNovaConta criarNovaConta = new CriarNovaConta(contaGateway);
        Conta conta = new Conta(1L, 2L, 3L, BigDecimal.ZERO, "Pedro", "987654321");
        //When
        Conta novaConta = criarNovaConta.execute(conta);
        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(1L, novaConta.getId()),
                () -> Assertions.assertEquals("Pedro",novaConta.getTitular())
        );
    }
}
