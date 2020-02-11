package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "COMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c")
    , @NamedQuery(name = "Compra.findByIdcompra", query = "SELECT c FROM Compra c WHERE c.idcompra = :idcompra")
    , @NamedQuery(name = "Compra.findByData", query = "SELECT c FROM Compra c WHERE c.data = :data")
    , @NamedQuery(name = "Compra.findBybetweenData", query = "SELECT c FROM Compra c WHERE c.data BETWEEN ':datastart' AND ':dataend' ")
    , @NamedQuery(name = "Compra.findByIdFornecedor", query = "SELECT c FROM Compra c WHERE c.idfornecedor = :idfornecedor")
    , @NamedQuery(name = "Compra.findByIdFuncionario", query = "SELECT c FROM Compra c WHERE c.idfuncionario = :idfuncionario")
    , @NamedQuery(name = "Compra.findByValortotal", query = "SELECT c FROM Compra c WHERE c.valortotal = :valortotal")
    , @NamedQuery(name = "Compra.findByAllDESC", query = "SELECT c FROM Compra c ORDER BY c.idcompra DESC")   
    , @NamedQuery(name = "Compra.findByNfatura", query = "SELECT c FROM Compra c WHERE c.nfatura = :nfatura")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDCOMPRA")
    private Integer idcompra;
    @Basic(optional = false)
    @Column(name = "DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "VALORTOTAL")
    private BigDecimal valortotal;
    @Basic(optional = false)
    @Column(name = "NFATURA")
    private int nfatura;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcompra")
    private Collection<Artigo> artigoCollection;
    @JoinColumn(name = "IDFORNECEDOR", referencedColumnName = "IDFORNECEDOR")
    @ManyToOne(optional = false)
    private Fornecedor idfornecedor;
    @JoinColumn(name = "IDFUNCIONARIO", referencedColumnName = "IDUTILIZADOR")
    @ManyToOne(optional = false)
    private Utilizador idfuncionario;

    public Compra() {
    }

    public Compra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    public Compra(Integer idcompra, Date data, BigDecimal valortotal, int nfatura) {
        this.idcompra = idcompra;
        this.data = data;
        this.valortotal = valortotal;
        this.nfatura = nfatura;
    }

    public Integer getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValortotal() {
        return valortotal;
    }

    public void setValortotal(BigDecimal valortotal) {
        this.valortotal = valortotal;
    }

    public int getNfatura() {
        return nfatura;
    }

    public void setNfatura(int nfatura) {
        this.nfatura = nfatura;
    }

    @XmlTransient
    public Collection<Artigo> getArtigoCollection() {
        return artigoCollection;
    }

    public void setArtigoCollection(Collection<Artigo> artigoCollection) {
        this.artigoCollection = artigoCollection;
    }

    public Fornecedor getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(Fornecedor idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public Utilizador getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Utilizador idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompra != null ? idcompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.idcompra == null && other.idcompra != null) || (this.idcompra != null && !this.idcompra.equals(other.idcompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + getIdcompra() + " - " + getNfatura() + " - " + getIdfornecedor();
    }
    
}
