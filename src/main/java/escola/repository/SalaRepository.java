package escola.repository;

import escola.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    // Método para a Service verificar se o código já existe no banco
    boolean existsByCodigo(String codigo);
}