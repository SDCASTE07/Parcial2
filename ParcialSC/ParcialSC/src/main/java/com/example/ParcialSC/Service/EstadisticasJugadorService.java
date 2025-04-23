package com.example.ParcialSC.Service;

import com.example.ParcialSC.Model.EstadisticasJugador;
import com.example.ParcialSC.Model.Jugador;
import com.example.ParcialSC.Model.Partido;
import com.example.ParcialSC.Repository.EstadisticasJugadorRepository;
import com.example.ParcialSC.Repository.JugadorRepository;
import com.example.ParcialSC.Repository.PartidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstadisticasJugadorService {

    @Autowired
    private EstadisticasJugadorRepository estadisticasRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    // CRUD Básico
    @Transactional
    public EstadisticasJugador createEstadistica(EstadisticasJugador estadistica) {
        // Validar existencia de jugador y partido
        Jugador jugador = jugadorRepository.findById(estadistica.getJugador().getIdJugador())
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        Partido partido = partidoRepository.findById(estadistica.getPartido().getIdPartido())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        estadistica.setJugador(jugador);
        estadistica.setPartido(partido);
        return estadisticasRepository.save(estadistica);
    }

    @Transactional
    public List<EstadisticasJugador> getAllEstadisticas() {
        return estadisticasRepository.findAll();
    }

    @Transactional
    public EstadisticasJugador getEstadisticaById(Integer id) {
        return estadisticasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estadística no encontrada"));
    }

    @Transactional
    public EstadisticasJugador updateEstadistica(Integer id, EstadisticasJugador estadisticaDetails) {
        EstadisticasJugador estadistica = getEstadisticaById(id);

        // Actualizar campos
        estadistica.setMinutosJugados(estadisticaDetails.getMinutosJugados());
        estadistica.setGoles(estadisticaDetails.getGoles());
        estadistica.setAsistencias(estadisticaDetails.getAsistencias());
        estadistica.setTarjetasAmarillas(estadisticaDetails.getTarjetasAmarillas());
        estadistica.setTarjetasRojas(estadisticaDetails.getTarjetasRojas());

        return estadisticasRepository.save(estadistica);
    }

    @Transactional
    public void deleteEstadistica(Integer id) {
        EstadisticasJugador estadistica = getEstadisticaById(id);
        estadisticasRepository.delete(estadistica);
    }

    // Consultas Específicas
    @Transactional
    public List<EstadisticasJugador> getEstadisticasByJugador(Integer idJugador) {
        return estadisticasRepository.findByJugadorId(idJugador);
    }

    @Transactional
    public List<EstadisticasJugador> getEstadisticasByPartido(Integer idPartido) {
        return estadisticasRepository.findByPartidoId(idPartido);
    }

    @Transactional
    public Integer getTotalGolesJugador(Integer idJugador) {
        return estadisticasRepository.sumGolesByJugador(idJugador);
    }
}
