package com.example.ParcialSC.Controller;

import com.example.ParcialSC.Model.Jugador;
import com.example.ParcialSC.Service.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@Tag(name = "Gestión de Jugadores", description = "Operaciones CRUD y consultas específicas de jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    // CRUD Básico
    @GetMapping
    @Operation(summary = "Obtener todos los jugadores")
    public ResponseEntity<List<Jugador>> getAllJugadores() {
        return ResponseEntity.ok(jugadorService.getAllJugadores());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener jugador por ID")
    public ResponseEntity<Jugador> getJugadorById(@PathVariable Integer id) {
        return ResponseEntity.ok(jugadorService.getJugadorById(id));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo jugador")
    public ResponseEntity<Jugador> createJugador(@RequestBody Jugador jugador) {
        return new ResponseEntity<>(jugadorService.createJugador(jugador), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar jugador existente")
    public ResponseEntity<Jugador> updateJugador(
            @PathVariable Integer id,
            @RequestBody Jugador jugadorDetails) {
        return ResponseEntity.ok(jugadorService.updateJugador(id, jugadorDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar jugador por ID")
    public ResponseEntity<Void> deleteJugador(@PathVariable Integer id) {
        jugadorService.deleteJugador(id);
        return ResponseEntity.noContent().build();
    }

    // Consultas Nativas del Parcial
    @GetMapping("/equipo/{idEquipo}")
    @Operation(summary = "Obtener jugadores por equipo")
    public ResponseEntity<List<Jugador>> getJugadoresByEquipo(
            @PathVariable("idEquipo") Integer idEquipo) {
        return ResponseEntity.ok(jugadorService.getJugadoresByEquipo(idEquipo));
    }

    @GetMapping("/goles")
    @Operation(summary = "Jugadores con más de X goles")
    public ResponseEntity<List<Jugador>> getJugadoresConMasGolesQue(
            @RequestParam("minGoles") Integer minGoles) {
        return ResponseEntity.ok(jugadorService.getJugadoresConMasGolesQue(minGoles));
    }
}