/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "PAGAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p")
    , @NamedQuery(name = "Pagamento.findByIdpagamento", query = "SELECT p FROM Pagamento p WHERE p.idpagamento = :idpagamento")
    , @NamedQuery(name = "Pagamento.findByMontante", query = "SELECT p FROM Pagamento p WHERE p.montante = :montante")
    , @NamedQuery(name = "Pagamento.findByFormapagamento", query = "SELECT p FROM Pagamento p WHERE p.formapagamento = :formapagamento")
    , @NamedQuery(name = "Pagamento.findByEfetuado", query = "SELECT p FROM Pagamento p WHERE p.efetuado = :efetuado")})
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPAGAMENTO")
    private Integer idpagamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTANTE")
    private BigDecimal montante;
    @Basic(optional = false)
    @Column(name = "FORMAPAGAMENTO")
    private short formapagamento;
    @Column(name = "EFETUADO")
    private Short efetuado;
    @OneToMany(mappedBy = "idpagamento")
    private Collection<Venda> vendaCollection;

    public Pagamento() {
    }

    public Pagamento(Integer idpagamento) {
        this.idpagamento = idpagamento;
    }

    public Pagamento(Integer idpagamento, short formapagamento) {
        this.idpagamento = idpagamento;
        this.formapagamento = formapagamento;
    }

    public Integer getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(Integer idpagamento) {
        this.idpagamento = idpagamento;
    }

    public BigDecimal getMontante() {
        return montante;
    }

    public void setMontante(BigDecimal montante) {
        this.montante = montante;
    }

    public short getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(short formapagamento) {
        this.formapagamento = formapagamento;
    }

    public Short getEfetuado() {
        return efetuado;
    }

    public void setEfetuado(Short efetuado) {
        this.efetuado = efetuado;
    }

    @XmlTransient
    public Collection<Venda> getVendaCollection() {
        return vendaCollection;
    }

    public void setVendaCollection(Collection<Venda> vendaCollection) {
        this.vendaCollection = vendaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpagamento != null ? idpagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.idpagamento == null && other.idpagamento != null) || (this.idpagamento != null && !this.idpagamento.equals(other.idpagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Pagamento[ idpagamento=" + idpagamento + " ]";
    }
    
}
