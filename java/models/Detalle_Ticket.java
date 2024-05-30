/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rosío
 */
@Entity
@Table(name = "detalle_ticket")
@NamedQueries({
    @NamedQuery(name = "DetalleTicket.findAll", query = "SELECT d FROM DetalleTicket d"),
    @NamedQuery(name = "DetalleTicket.findByCantidad", query = "SELECT d FROM DetalleTicket d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleTicket.findByFechaHora", query = "SELECT d FROM DetalleTicket d WHERE d.fechaHora = :fechaHora"),
    @NamedQuery(name = "DetalleTicket.findByDetalleId", query = "SELECT d FROM DetalleTicket d WHERE d.detalleId = :detalleId")})
public class Detalle_Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_id")
    private Integer detalleId;
    @JoinColumn(name = "producto_id", referencedColumnName = "id_producto")
    @ManyToOne
    private Productos productoId;
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
    @ManyToOne
    private Tickets ticketId;

    public Detalle_Ticket() {
    }

    public Detalle_Ticket(Integer detalleId) {
        this.detalleId = detalleId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(Integer detalleId) {
        this.detalleId = detalleId;
    }

    public Productos getProductoId() {
        return productoId;
    }

    public void setProductoId(Productos productoId) {
        this.productoId = productoId;
    }

    public Tickets getTicketId() {
        return ticketId;
    }

    public void setTicketId(Tickets ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleId != null ? detalleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalle_Ticket)) {
            return false;
        }
        Detalle_Ticket other = (Detalle_Ticket) object;
        if ((this.detalleId == null && other.detalleId != null) || (this.detalleId != null && !this.detalleId.equals(other.detalleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.DetalleTicket[ detalleId=" + detalleId + " ]";
    }
    
}
