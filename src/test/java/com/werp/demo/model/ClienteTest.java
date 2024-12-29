package com.werp.demo.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ClienteTest {
    @Test
    void validarCreacionCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Jose Lema");
        cliente.setIdentificacion("1234567890");
        cliente.setDireccion("Otavalo sn y principal");
        cliente.setTelefono("098254785");
        cliente.setPassword("1234");
        cliente.setEstado(true);

        assertNotNull(cliente);
        assertEquals("Jose Lema", cliente.getNombre());
        assertEquals("1234567890", cliente.getIdentificacion());
    }
}