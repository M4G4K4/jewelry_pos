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

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "VENDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v")
    , @NamedQuery(name = "Venda.findByIdvenda", query = "SELECT v FROM Venda v WHERE v.idvenda = :idvenda")
    , @NamedQuery(name = "Venda.findByData", query = "SELECT v FROM Venda v WHERE v.data = :data")
    , @NamedQuery(name = "Venda.findByTotal", query = "SELECT v FROM Venda v WHERE v.total = :total")
    , @NamedQuery(name = "Venda.findByCliente", query = "SELECT v FROM Venda v WHERE v.idcliente = :idcliente")
    , @NamedQuery(name = "Venda.findtopID", query = "SELECT v FROM Venda v ORDER BY v.idvenda DESC")
    , @NamedQuery(name = "Venda.findByFinalizada", query = "SELECT v FROM Venda v WHERE v.finalizada = :finalizada")})
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDVENDA")
    private Integer idvenda;
    
    //@Basic(optional = false)
    @Column(name = "DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Column(name = "FINALIZADA")
    private Short finalizada;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE")
    @ManyToOne
    private Cliente idcliente;
    @JoinColumn(name = "IDPAGAMENTO", referencedColumnName = "IDPAGAMENTO")
    @ManyToOne
    private Pagamento idpagamento;
    @JoinColumn(name = "IDFUNCIONARIO", referencedColumnName = "IDUTILIZADOR")
    @ManyToOne
    private Utilizador idfuncionario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    private Collection<LinhaArtigo> linhaArtigoCollection;

    public Venda() {
    }

    public Venda(Integer idvenda) {
        this.idvenda = idvenda;
    }

    public Venda(Integer idvenda, Date data) {
        this.idvenda = idvenda;
        this.data = data;
    }

    public Integer getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(Integer idvenda) {
        this.idvenda = idvenda;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Short getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Short finalizada) {
        this.finalizada = finalizada;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Pagamento getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(Pagamento idpagamento) {
        this.idpagamento = idpagamento;
    }

    public Utilizador getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Utilizador idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    @XmlTransient
    public Collection<LinhaArtigo> getLinhaArtigoCollection() {
        return linhaArtigoCollection;
    }

    public void setLinhaArtigoCollection(Collection<LinhaArtigo> linhaArtigoCollection) {
        this.linhaArtigoCollection = linhaArtigoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvenda != null ? idvenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.idvenda == null && other.idvenda != null) || (this.idvenda != null && !this.idvenda.equals(other.idvenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "jpa.entities.Venda[ idvenda=" + idvenda + " ]";
        return "" + getIdcliente();
    }
    
}
