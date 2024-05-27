/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tipos_productos")
@NamedQueries({
    @NamedQuery(name = "TiposProductos.findAll", query = "SELECT t FROM TiposProductos t"),
    @NamedQuery(name = "TiposProductos.findByNombre", query = "SELECT t FROM TiposProductos t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TiposProductos.findByTipoId", query = "SELECT t FROM TiposProductos t WHERE t.tipoId = :tipoId")})
public class TiposProductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "nombre")
    private String nombre;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_id")
    private Integer tipoId;
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
    @ManyToOne
    private Categorias categoriaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipo")
    private Collection<Productos> productosCollection;

    public TiposProductos() {
    }

    public TiposProductos(Integer tipoId) {
        this.tipoId = tipoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTipoId() {
        return tipoId;
    }

    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

    public Categorias getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categorias categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoId != null ? tipoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposProductos)) {
            return false;
        }
        TiposProductos other = (TiposProductos) object;
        if ((this.tipoId == null && other.tipoId != null) || (this.tipoId != null && !this.tipoId.equals(other.tipoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.TiposProductos[ tipoId=" + tipoId + " ]";
    }
    
}
