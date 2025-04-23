package com.example.ParcialSC.Service;

import com.example.ParcialSC.Model.Equipo;
import com.example.ParcialSC.Model.Jugador;
import com.example.ParcialSC.Repository.EquipoRepository;
import com.example.ParcialSC.Repository.JugadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    // CRUD Básico
    @Transactional
    public List<Jugador> getAllJugadores() {
        return jugadorRepository.findAll();
    }

    @Transactional
    public Jugador getJugadorById(Integer id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con ID: " + id));
    }

    @Transactional
    public Jugador createJugador(Jugador jugador) {
        // Validar que el equipo exista
        Equipo equipo = equipoRepository.findById(jugador.getEquipo().getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + jugador.getEquipo().getIdEquipo()));

        jugador.setEquipo(equipo);
        return jugadorRepository.save(jugador);
    }

    @Transactional
    public Jugador updateJugador(Integer id, Jugador jugadorDetails) {
        Jugador jugador = getJugadorById(id);

        // Actualizar campos
        jugador.setNombre(jugadorDetails.getNombre());
        jugador.setPosicion(jugadorDetails.getPosicion());
        jugador.setDorsal(jugadorDetails.getDorsal());
        jugador.setFechaNac(jugadorDetails.getFechaNac());
        jugador.setNacionalidad(jugadorDetails.getNacionalidad());

        // Validar equipo si es diferente
        if (!jugador.getEquipo().getIdEquipo().equals(jugadorDetails.getEquipo().getIdEquipo())) {
            Equipo nuevoEquipo = equipoRepository.findById(jugadorDetails.getEquipo().getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Nuevo equipo no encontrado"));
            jugador.setEquipo(nuevoEquipo);
        }

        return jugadorRepository.save(jugador);
    }

    @Transactional
    public void deleteJugador(Integer id) {
        Jugador jugador = getJugadorById(id);
        jugadorRepository.delete(jugador);
    }

    // Consultas Nativas del Parcial
    @Transactional
    public List<Jugador> getJugadoresByEquipo(Integer idEquipo) {
        return jugadorRepository.findJugadoresByEquipo(idEquipo);
    }

    @Transactional
    public List<Jugador> getJugadoresConMasGolesQue(Integer minGoles) {
        return jugadorRepository.findJugadoresConMasGolesQue(minGoles);
    }
}