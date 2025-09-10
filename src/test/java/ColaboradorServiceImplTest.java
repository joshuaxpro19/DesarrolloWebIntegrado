import com.example.GestorColaboradores.model.Colaborador;
import com.example.GestorColaboradores.repository.ColaboradorRepository;
import com.example.GestorColaboradores.service.impl.ColaboradorServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ColaboradorServiceImplTest {

    @Mock
    private ColaboradorRepository repo;

    @InjectMocks
    private ColaboradorServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Retorna lista de colaboradores correctamente")
    void ListaDeColaboradores() {
        // Arrange: Preparar 
        Colaborador c1 = new Colaborador();
        Colaborador c2 = new Colaborador();
        when(repo.findAll()).thenReturn(Arrays.asList(c1, c2));

        // Act: Actuar
        List<Colaborador> result = service.listarTodos();

        // Assert: Verificar
        assertEquals(2, result.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    @DisplayName("Guarda colaborador correctamente") 
    void GuardarColaborador() {
        // Arrange: Preparar
        Colaborador c = new Colaborador();
        c.setEmail("test@example.com");

        when(repo.existsByEmail(c.getEmail())).thenReturn(false);
        when(repo.save(c)).thenReturn(c);

        // Act: Actuar
        Colaborador result = service.crear(c);

        // Assert: Verificar
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(repo, times(1)).existsByEmail("test@example.com");
        verify(repo, times(1)).save(c);
    }

    @Test
    @DisplayName("Lanza excepción cuando el email ya existe")
    void ExcepcionCuandoEmailYaExiste() {
        // Arrange: Preparar
        Colaborador c = new Colaborador();
        c.setEmail("test@example.com");

        when(repo.existsByEmail(c.getEmail())).thenReturn(true);

        // Act & Assert: Verificar
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.crear(c)
        );
        
        assertEquals("Email ya registrado", exception.getMessage());
        verify(repo, times(1)).existsByEmail("test@example.com");
        verify(repo, never()).save(any());
    }
}
