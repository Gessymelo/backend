package br.com.diegopatricio.servicex.categoria.resources;

import br.com.diegopatricio.servicex.categoria.domain.Categoria;
import br.com.diegopatricio.servicex.categoria.repositories.CategoriaRepository;
import br.com.diegopatricio.servicex.categoria.services.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaResourceTest {
     @InjectMocks
     private CategoriaService categoriaService;

     @Mock
     private CategoriaRepository categoriaRepository;

     //criando teste via metodo

    @Test
     public void testCriarCategoria_Success(){

        //criando uma instancia
         Categoria categoria = new Categoria();
         categoria.setNomeCategoria("New Category");

         //Montando o Cenario e criando comportamento
         when (categoriaRepository.existsByNomeCategoria(categoria.getNomeCategoria())).thenReturn(false);
         when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

         //Executando Logica do negocio
         Categoria result = categoriaService.criarCategoria(categoria);

         //Validando

        //Validando Resultados
         assertNotNull(result);
         assertEquals("New Category", result.getNomeCategoria());
         //verificando intenraçoes com o repositorio
         verify(categoriaRepository).existsByNomeCategoria(categoria.getNomeCategoria());
         verify(categoriaRepository).save(any(Categoria.class));
     }

     @Test
    public void testDeletarServicos_Success(){
         Integer id = 3;
         // Simular comportamento do método buscarCategoriaPorId no categoriaService
         doNothing().when(categoriaRepository).deleteById(id);
         // Simular comportamento do método deleteById no categoriaRepository
         doNothing().when(categoriaService).buscarCategoriaPorId(id);

         // Executar o método a ser testado
         categoriaService.deletarCategoria(id);

         // Verificar se os métodos foram chamados corretamente
         verify(categoriaRepository).deleteById(id);
         verify(categoriaService).buscarCategoriaPorId(id);
     }




}