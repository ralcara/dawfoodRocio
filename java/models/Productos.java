/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Rosío
 */
@Entity
@Table(name = "productos")
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByNombreProducto", query = "SELECT p FROM Productos p WHERE p.nombreProducto = :nombreProducto"),
    @NamedQuery(name = "Productos.findByPrecioProducto", query = "SELECT p FROM Productos p WHERE p.precioProducto = :precioProducto"),
    @NamedQuery(name = "Productos.findByIvaProducto", query = "SELECT p FROM Productos p WHERE p.ivaProducto = :ivaProducto"),
    @NamedQuery(name = "Productos.findByStockProducto", query = "SELECT p FROM Productos p WHERE p.stockProducto = :stockProducto"),
    @NamedQuery(name = "Productos.findByIdProducto", query = "SELECT p FROM Productos p WHERE p.idProducto = :idProducto")})
public class Productos implements Serializable {
     private EntityManager em;
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nombre_producto")
    private String nombreProducto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precio_producto")
    private BigDecimal precioProducto;
    @Basic(optional = false)
    @Column(name = "iva_producto")
    private BigDecimal ivaProducto;
    @Basic(optional = false)
    @Column(name = "stock_producto")
    private int stockProducto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto")
    private Integer idProducto;
    @OneToMany(mappedBy = "productoId")
    private Collection<Detalle_Ticket> detalleTicketCollection;
    @JoinColumn(name = "id_tipo", referencedColumnName = "tipo_id")
    @ManyToOne(optional = false)
    private TiposProductos idTipo;

    public Productos() {
    }

    public Productos(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Productos(Integer idProducto, String nombreProducto, BigDecimal precioProducto, BigDecimal ivaProducto, int stockProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.ivaProducto = ivaProducto;
        this.stockProducto = stockProducto;
    }
   public ArrayList<Productos> getBebidas() {
        ArrayList<Productos> bebidas = new ArrayList<>();
        try {
            em.getTransaction().begin();
            bebidas = (ArrayList<Productos>) em.createQuery("SELECT * FROM Producto WHERE tipoId = 2", Productos.class)
                        .getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return bebidas;
    }

     public ArrayList<Productos> getComida() {
        ArrayList<Productos> comidas = new ArrayList<>();
        try {
            em.getTransaction().begin();
            comidas = (ArrayList<Productos>) em.createQuery("SELECT * FROM Producto WHERE tipoId = 1", Productos.class)
                        .getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return comidas;
    }
      public ArrayList<Productos> getPostres() {
        ArrayList<Productos> postres = new ArrayList<>();
        try {
            em.getTransaction().begin();
            postres = (ArrayList<Productos>) em.createQuery("SELECT * FROM Producto WHERE tipoId = 3", Productos.class)
                        .getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return postres;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }

    public BigDecimal getIvaProducto() {
        return ivaProducto;
    }

    public void setIvaProducto(BigDecimal ivaProducto) {
        this.ivaProducto = ivaProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Collection<Detalle_Ticket> getDetalleTicketCollection() {
        return detalleTicketCollection;
    }

    public void setDetalleTicketCollection(Collection<Detalle_Ticket> detalleTicketCollection) {
        this.detalleTicketCollection = detalleTicketCollection;
    }

    public TiposProductos getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TiposProductos idTipo) {
        this.idTipo = idTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Productos[ idProducto=" + idProducto + " ]";
    }
    
}
