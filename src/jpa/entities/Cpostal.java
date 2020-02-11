/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
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
@Table(name = "CPOSTAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cpostal.findAll", query = "SELECT c FROM Cpostal c")
    , @NamedQuery(name = "Cpostal.findByCpostal", query = "SELECT c FROM Cpostal c WHERE c.cpostal = :cpostal")
    , @NamedQuery(name = "Cpostal.findByLocalidade", query = "SELECT c FROM Cpostal c WHERE c.localidade = :localidade")})
public class Cpostal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CPOSTAL")
    private String cpostal;
    @Basic(optional = false)
    @Column(name = "LOCALIDADE")
    private String localidade;
    @OneToMany(mappedBy = "cpostal")
    private Collection<Cliente> clienteCollection;
    @OneToMany(mappedBy = "cpostal")
    private Collection<Fornecedor> fornecedorCollection;

    public Cpostal() {
    }

    public Cpostal(String cpostal) {
        this.cpostal = cpostal;
    }

    public Cpostal(String cpostal, String localidade) {
        this.cpostal = cpostal;
        this.localidade = localidade;
    }

    public String getCpostal() {
        return cpostal;
    }

    public void setCpostal(String cpostal) {
        this.cpostal = cpostal;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @XmlTransient
    public Collection<Fornecedor> getFornecedorCollection() {
        return fornecedorCollection;
    }

    public void setFornecedorCollection(Collection<Fornecedor> fornecedorCollection) {
        this.fornecedorCollection = fornecedorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpostal != null ? cpostal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cpostal)) {
            return false;
        }
        Cpostal other = (Cpostal) object;
        if ((this.cpostal == null && other.cpostal != null) || (this.cpostal != null && !this.cpostal.equals(other.cpostal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + getCpostal();
    }
    
}
