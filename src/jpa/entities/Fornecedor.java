package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "FORNECEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f")
    , @NamedQuery(name = "Fornecedor.findByIdfornecedor", query = "SELECT f FROM Fornecedor f WHERE f.idfornecedor = :idfornecedor")
    , @NamedQuery(name = "Fornecedor.findByNome", query = "SELECT f FROM Fornecedor f WHERE f.nome = :nome")
    , @NamedQuery(name = "Fornecedor.findByTelemovel", query = "SELECT f FROM Fornecedor f WHERE f.telemovel = :telemovel")
    , @NamedQuery(name = "Fornecedor.findByEmail", query = "SELECT f FROM Fornecedor f WHERE f.email = :email")
    , @NamedQuery(name = "Fornecedor.findByMorada", query = "SELECT f FROM Fornecedor f WHERE f.morada = :morada")
    , @NamedQuery(name = "Fornecedor.findByNomeLike", query = "SELECT c FROM Fornecedor c WHERE c.nome LIKE CONCAT('%',:nome,'%')")
    , @NamedQuery(name = "Fornecedor.findByMoradaLike", query = "SELECT c FROM Fornecedor c WHERE c.morada LIKE CONCAT('%',:morada,'%')")
    , @NamedQuery(name = "Fornecedor.findByObs", query = "SELECT f FROM Fornecedor f WHERE f.obs = :obs")})
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDFORNECEDOR")
    private Integer idfornecedor;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "TELEMOVEL")
    private String telemovel;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "MORADA")
    private String morada;
    @Column(name = "OBS")
    private String obs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfornecedor")
    private Collection<Compra> compraCollection;
    @JoinColumn(name = "CPOSTAL", referencedColumnName = "CPOSTAL")
    @ManyToOne
    private Cpostal cpostal;

    public Fornecedor() {
    }

    public Fornecedor(Integer idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public Fornecedor(Integer idfornecedor, String nome, String telemovel) {
        this.idfornecedor = idfornecedor;
        this.nome = nome;
        this.telemovel = telemovel;
    }

    public Integer getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(Integer idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @XmlTransient
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    public Cpostal getCpostal() {
        return cpostal;
    }

    public void setCpostal(Cpostal cpostal) {
        this.cpostal = cpostal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfornecedor != null ? idfornecedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.idfornecedor == null && other.idfornecedor != null) || (this.idfornecedor != null && !this.idfornecedor.equals(other.idfornecedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + getNome() ;
    }
    
}
