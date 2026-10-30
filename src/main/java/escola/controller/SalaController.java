package escola.controller;

import escola.model.Sala;
import escola.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping
    public ResponseEntity<Sala> cadastrar(@Valid @RequestBody Sala sala) {
        Sala salaCadastrada = salaService.cadastrar(sala);
        return new ResponseEntity<>(salaCadastrada, HttpStatus.CREATED); // 201 Created
    }

    @GetMapping
    public ResponseEntity<List<Sala>> listarTodas() {
        return ResponseEntity.ok(salaService.listarTodas()); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.buscarPorId(id)); // 200 OK (o Service lança 404 se não achar)[cite: 1]
    }

    @PostMapping("/{id}/reservar")
    public ResponseEntity<Sala> reservar(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.reservar(id)); // 200 OK (o Service lança os conflitos)[cite: 1]
    }

    @PostMapping("/{id}/liberar")
    public ResponseEntity<Sala> liberar(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.liberar(id)); // 200 OK (o Service lança os conflitos)[cite: 1]
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        salaService.excluir(id);
        return ResponseEntity.noContent().build(); // 204 No Content[cite: 1]
    }
}