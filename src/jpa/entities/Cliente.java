/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente")
    , @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome")
    , @NamedQuery(name = "Cliente.findByTelemovel", query = "SELECT c FROM Cliente c WHERE c.telemovel = :telemovel")
    , @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email")
    , @NamedQuery(name = "Cliente.findByNif", query = "SELECT c FROM Cliente c WHERE c.nif = :nif")
    , @NamedQuery(name = "Cliente.findByCc", query = "SELECT c FROM Cliente c WHERE c.cc = :cc")
    , @NamedQuery(name = "Cliente.findByNcontribuinte", query = "SELECT c FROM Cliente c WHERE c.ncontribuinte = :ncontribuinte")
    , @NamedQuery(name = "Cliente.findByDatanascimento", query = "SELECT c FROM Cliente c WHERE c.datanascimento = :datanascimento")
    , @NamedQuery(name = "Cliente.findByObs", query = "SELECT c FROM Cliente c WHERE c.obs = :obs")
    , @NamedQuery(name = "Cliente.findByNomeLike", query = "SELECT c FROM Cliente c WHERE c.nome LIKE CONCAT('%',:nome,'%')")
    , @NamedQuery(name = "Cliente.findByMoradaLike", query = "SELECT c FROM Cliente c WHERE c.morada LIKE CONCAT('%',:morada,'%')")
    , @NamedQuery(name = "Cliente.findByMorada", query = "SELECT c FROM Cliente c WHERE c.morada = :morada")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDCLIENTE")
    private Integer idcliente;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Column(name = "TELEMOVEL")
    private String telemovel;
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "NIF")
    private long nif;
    @Basic(optional = false)
    @Column(name = "CC")
    private long cc;
    @Basic(optional = false)
    @Column(name = "NCONTRIBUINTE")
    private long ncontribuinte;
    @Column(name = "DATANASCIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datanascimento;
    @Column(name = "OBS")
    private String obs;
    @Column(name = "MORADA")
    private String morada;
    @OneToMany(mappedBy = "idcliente")
    private Collection<Venda> vendaCollection;
    @JoinColumn(name = "CPOSTAL", referencedColumnName = "CPOSTAL")
    @ManyToOne
    private Cpostal cpostal;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Cliente(Integer idcliente, String nome, long nif, long cc, long ncontribuinte) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.nif = nif;
        this.cc = cc;
        this.ncontribuinte = ncontribuinte;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
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

    public long getNif() {
        return nif;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public long getCc() {
        return cc;
    }

    public void setCc(long cc) {
        this.cc = cc;
    }

    public long getNcontribuinte() {
        return ncontribuinte;
    }

    public void setNcontribuinte(long ncontribuinte) {
        this.ncontribuinte = ncontribuinte;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    @XmlTransient
    public Collection<Venda> getVendaCollection() {
        return vendaCollection;
    }

    public void setVendaCollection(Collection<Venda> vendaCollection) {
        this.vendaCollection = vendaCollection;
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
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + getNome();
    }
    
}
