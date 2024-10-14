package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class BibliotecaTest {

    private Biblioteca biblioteca;

    @BeforeEach
    void setUp() {
        biblioteca = new Biblioteca();
    }

    @Test
    void adicionarLivroValido() {
        Livro livro = new Livro("O Poder da Escolha Certa", "Raymond Charles Barker");
        biblioteca.adicionarLivro(livro);

        List<Livro> disponiveis = biblioteca.consultarLivrosDisponiveis();
        assertEquals(1, disponiveis.size(), "Deve haver 1 livro disponível.");
    }

    @Test
    void emprestarLivroDisponivelComTituloCorreto() {
        Livro livro = new Livro("O Poder da Escolha Certa", "Raymond Charles Barker");
        biblioteca.adicionarLivro(livro);

        Livro emprestado = biblioteca.emprestarLivro("O Poder da Escolha Certa");
        assertTrue(emprestado.isEmprestado(), "Livro deveria estar emprestado.");
    }

    @Test
    void naoEmprestarLivroInexistente() {
        Exception exception = assertThrows(IllegalStateException.class, () -> biblioteca.emprestarLivro("LivO Poder da Escolha Certa"));
        assertEquals("Livro não disponível ou já emprestado", exception.getMessage());
    }

    @Test
    void devolverLivroEmprestadoComTituloCorreto() {
        Livro livro = new Livro("O Poder da Escolha Certa", "Raymond Charles Barker");
        biblioteca.adicionarLivro(livro);
        biblioteca.emprestarLivro("O Poder da Escolha Certa");

        biblioteca.devolverLivro("O Poder da Escolha Certa");
        assertFalse(livro.isEmprestado(), "Livro deveria estar disponível.");
    }

    @Test
    void naoDevolverLivroNaoEmprestadoOuInexistente() {
        Livro livro = new Livro("O Poder da Escolha Certa", "Raymond Charles Barker");
        biblioteca.adicionarLivro(livro);

        Exception exception = assertThrows(IllegalStateException.class, () -> biblioteca.devolverLivro("O Poder da Escolha Certa"));
        assertEquals("Livro não está emprestado ou não existe", exception.getMessage());
    }
}
