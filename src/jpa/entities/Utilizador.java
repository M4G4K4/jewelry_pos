package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "UTILIZADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilizador.findAll", query = "SELECT u FROM Utilizador u")
    , @NamedQuery(name = "Utilizador.findByIdutilizador", query = "SELECT u FROM Utilizador u WHERE u.idutilizador = :idutilizador")
    , @NamedQuery(name = "Utilizador.findByLogin", query = "SELECT u FROM Utilizador u WHERE u.login = :login")
    , @NamedQuery(name = "Utilizador.findByPass", query = "SELECT u FROM Utilizador u WHERE u.pass = :pass")
    , @NamedQuery(name = "Utilizador.findByNotAdmin", query = "SELECT u FROM Utilizador u where u.idutilizador !=3")
    , @NamedQuery(name = "Utilizador.findByNivelpermissao", query = "SELECT u FROM Utilizador u WHERE u.nivelpermissao = :nivelpermissao")})
public class Utilizador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDUTILIZADOR")
    private Short idutilizador;
    @Basic(optional = false)
    @Column(name = "LOGIN")
    private String login;
    @Basic(optional = false)
    @Column(name = "PASS")
    private String pass;
    @Basic(optional = false)
    @Column(name = "NIVELPERMISSAO")
    private short nivelpermissao;
    @OneToMany(mappedBy = "idfuncionario")
    private Collection<Venda> vendaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfuncionario")
    private Collection<Compra> compraCollection;

    public Utilizador() {
    }

    public Utilizador(Short idutilizador) {
        this.idutilizador = idutilizador;
    }

    public Utilizador(Short idutilizador, String login, String pass, short nivelpermissao) {
        this.idutilizador = idutilizador;
        this.login = login;
        this.pass = pass;
        this.nivelpermissao = nivelpermissao;
    }

    public Short getIdutilizador() {
        return idutilizador;
    }

    public void setIdutilizador(Short idutilizador) {
        this.idutilizador = idutilizador;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public short getNivelpermissao() {
        return nivelpermissao;
    }

    public void setNivelpermissao(short nivelpermissao) {
        this.nivelpermissao = nivelpermissao;
    }

    @XmlTransient
    public Collection<Venda> getVendaCollection() {
        return vendaCollection;
    }

    public void setVendaCollection(Collection<Venda> vendaCollection) {
        this.vendaCollection = vendaCollection;
    }

    @XmlTransient
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idutilizador != null ? idutilizador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilizador)) {
            return false;
        }
        Utilizador other = (Utilizador) object;
        if ((this.idutilizador == null && other.idutilizador != null) || (this.idutilizador != null && !this.idutilizador.equals(other.idutilizador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + getLogin() ;
    }
    
}
