package com.example.ParcialSC.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "estadisticas_jugador")
public class EstadisticasJugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estadistica")
    private Integer idEstadistica;

    // Relación N:1 con Jugador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador", nullable = false)
    private Jugador jugador;

    // Relación N:1 con Partido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partido", nullable = false)
    private Partido partido;

    @Column(name = "minutos_jugados")
    private Integer minutosJugados;

    @Column(name = "goles")
    private Integer goles;

    @Column(name = "asistencias")
    private Integer asistencias;

    @Column(name = "tarjetas_amarillas")
    private Integer tarjetasAmarillas;

    @Column(name = "tarjetas_rojas")
    private Integer tarjetasRojas;

    // Getters y Setters
    public Integer getIdEstadistica() { return idEstadistica; }
    public void setIdEstadistica(Integer idEstadistica) { this.idEstadistica = idEstadistica; }
    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
    public Partido getPartido() { return partido; }
    public void setPartido(Partido partido) { this.partido = partido; }
    public Integer getMinutosJugados() { return minutosJugados; }
    public void setMinutosJugados(Integer minutosJugados) { this.minutosJugados = minutosJugados; }
    public Integer getGoles() { return goles; }
    public void setGoles(Integer goles) { this.goles = goles; }
    public Integer getAsistencias() { return asistencias; }
    public void setAsistencias(Integer asistencias) { this.asistencias = asistencias; }
    public Integer getTarjetasAmarillas() { return tarjetasAmarillas; }
    public void setTarjetasAmarillas(Integer tarjetasAmarillas) { this.tarjetasAmarillas = tarjetasAmarillas; }
    public Integer getTarjetasRojas() { return tarjetasRojas; }
    public void setTarjetasRojas(Integer tarjetasRojas) { this.tarjetasRojas = tarjetasRojas; }
}
