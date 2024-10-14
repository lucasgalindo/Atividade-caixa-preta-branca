package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    @Test
    void criarLivroComDadosValidos() {
        Livro livro = new Livro("Entendendo Algoritmos", "Aditya Y. Bhargava");
        assertEquals("Entendendo Algoritmos", livro.getTitulo(), "Título incorreto.");
        assertEquals("Aditya Y. Bhargava", livro.getAutor(), "Autor incorreto.");
    }

    @Test
    void emprestarLivroDisponivel() {
        Livro livro = new Livro("Entendendo Algoritmos", "Aditya Y. Bhargava");
        livro.emprestar();
        assertTrue(livro.isEmprestado(), "Livro deveria estar emprestado.");
    }

    @Test
    void naoEmprestarLivroJaEmprestado() {
        Livro livro = new Livro("Entendendo Algoritmos", "Aditya Y. Bhargava");
        livro.emprestar();
        
        Exception exception = assertThrows(IllegalStateException.class, livro::emprestar);
        assertEquals("Livro já está emprestado", exception.getMessage());
    }

    @Test
    void devolverLivroEmprestado() {
        Livro livro = new Livro("Entendendo Algoritmos", "Aditya Y. Bhargava");
        livro.emprestar();
        livro.devolver();
        assertFalse(livro.isEmprestado(), "Livro deveria estar disponível.");
    }

    @Test
    void naoDevolverLivroNaoEmprestado() {
        Livro livro = new Livro("Entendendo Algoritmos", "Aditya Y. Bhargava");

        Exception exception = assertThrows(IllegalStateException.class, livro::devolver);
        assertEquals("Livro não está emprestado", exception.getMessage());
    }
}
