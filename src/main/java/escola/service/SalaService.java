package escola.service;

import escola.model.Sala;
import escola.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public Sala cadastrar(Sala sala) {
        // Regra: O código é único (erro 409 Conflict)
        if (salaRepository.existsByCodigo(sala.getCodigo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma sala com este código.");
        }
        return salaRepository.save(sala);
    }

    public List<Sala> listarTodas() {
        return salaRepository.findAll();
    }

    public Sala buscarPorId(Long id) {
        // Regra: Pedir uma sala que não existe é "não encontrado" (erro 404 Not Found)[cite: 1]
        return salaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada."));
    }

    public Sala reservar(Long id) {
        Sala sala = buscarPorId(id); // Já trata o 404 se não existir[cite: 1]

        // Regra: Uma sala que já está reservada não pode ser reservada de novo (erro 409 Conflict)[cite: 1]
        if (sala.isReservada()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A sala já está reservada.");
        }

        sala.setReservada(true);
        return salaRepository.save(sala);
    }

    public Sala liberar(Long id) {
        Sala sala = buscarPorId(id);

        // Regra: Só pode ser liberada uma sala que está reservada (erro 409 Conflict)[cite: 1]
        if (!sala.isReservada()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A sala já está livre.");
        }

        sala.setReservada(false);
        return salaRepository.save(sala);
    }

    public void excluir(Long id) {
        Sala sala = buscarPorId(id);

        // Regra: Uma sala reservada não pode ser excluída (erro 409 Conflict)[cite: 1]
        if (sala.isReservada()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Uma sala reservada não pode ser excluída.");
        }

        salaRepository.delete(sala);
    }
}