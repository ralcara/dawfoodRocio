/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rosío
 */
@Entity
@Table(name = "tickets")
@NamedQueries({
    @NamedQuery(name = "Tickets.findAll", query = "SELECT t FROM Tickets t"),
    @NamedQuery(name = "Tickets.findByFechaHoraGeneracion", query = "SELECT t FROM Tickets t WHERE t.fechaHoraGeneracion = :fechaHoraGeneracion"),
    @NamedQuery(name = "Tickets.findByTpvId", query = "SELECT t FROM Tickets t WHERE t.tpvId = :tpvId"),
    @NamedQuery(name = "Tickets.findByCodigoTransaccion", query = "SELECT t FROM Tickets t WHERE t.codigoTransaccion = :codigoTransaccion"),
    @NamedQuery(name = "Tickets.findByTicketId", query = "SELECT t FROM Tickets t WHERE t.ticketId = :ticketId")})
public class Tickets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "fecha_hora_generacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraGeneracion;
    @Column(name = "tpv_id")
    private Integer tpvId;
    @Column(name = "codigo_transaccion")
    private String codigoTransaccion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ticket_id")
    private Integer ticketId;
    @OneToMany(mappedBy = "ticketId")
    private Collection<Detalle_Ticket> detalleTicketCollection;

    public Tickets() {
    }

    public Tickets(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Date getFechaHoraGeneracion() {
        return fechaHoraGeneracion;
    }

    public void setFechaHoraGeneracion(Date fechaHoraGeneracion) {
        this.fechaHoraGeneracion = fechaHoraGeneracion;
    }

    public Integer getTpvId() {
        return tpvId;
    }

    public void setTpvId(Integer tpvId) {
        this.tpvId = tpvId;
    }

    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Collection<Detalle_Ticket> getDetalleTicketCollection() {
        return detalleTicketCollection;
    }

    public void setDetalleTicketCollection(Collection<Detalle_Ticket> detalleTicketCollection) {
        this.detalleTicketCollection = detalleTicketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketId != null ? ticketId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tickets)) {
            return false;
        }
        Tickets other = (Tickets) object;
        if ((this.ticketId == null && other.ticketId != null) || (this.ticketId != null && !this.ticketId.equals(other.ticketId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Tickets[ ticketId=" + ticketId + " ]";
    }
    
}
