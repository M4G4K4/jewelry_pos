/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "LINHA_ARTIGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinhaArtigo.findAll", query = "SELECT l FROM LinhaArtigo l")
    , @NamedQuery(name = "LinhaArtigo.findByIdartigo", query = "SELECT l FROM LinhaArtigo l WHERE l.linhaArtigoPK.idartigo = :idartigo")
    , @NamedQuery(name = "LinhaArtigo.findByIdvenda", query = "SELECT l FROM LinhaArtigo l WHERE l.linhaArtigoPK.idvenda = :idvenda")
    , @NamedQuery(name = "LinhaArtigo.findByQtd", query = "SELECT l FROM LinhaArtigo l WHERE l.qtd = :qtd")
    , @NamedQuery(name = "LinhaArtigo.findByValor", query = "SELECT l FROM LinhaArtigo l WHERE l.valor = :valor")})
public class LinhaArtigo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LinhaArtigoPK linhaArtigoPK;
    @Basic(optional = false)
    @Column(name = "QTD")
    private short qtd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private BigDecimal valor;
    @JoinColumn(name = "IDARTIGO", referencedColumnName = "IDARTIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artigo artigo;
    @JoinColumn(name = "IDVENDA", referencedColumnName = "IDVENDA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venda venda;

    public LinhaArtigo() {
    }

    public LinhaArtigo(LinhaArtigoPK linhaArtigoPK) {
        this.linhaArtigoPK = linhaArtigoPK;
    }

    public LinhaArtigo(LinhaArtigoPK linhaArtigoPK, short qtd) {
        this.linhaArtigoPK = linhaArtigoPK;
        this.qtd = qtd;
    }

    public LinhaArtigo(int idartigo, int idvenda) {
        this.linhaArtigoPK = new LinhaArtigoPK(idartigo, idvenda);
    }

    public LinhaArtigoPK getLinhaArtigoPK() {
        return linhaArtigoPK;
    }

    public void setLinhaArtigoPK(LinhaArtigoPK linhaArtigoPK) {
        this.linhaArtigoPK = linhaArtigoPK;
    }

    public short getQtd() {
        return qtd;
    }

    public void setQtd(short qtd) {
        this.qtd = qtd;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (linhaArtigoPK != null ? linhaArtigoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LinhaArtigo)) {
            return false;
        }
        LinhaArtigo other = (LinhaArtigo) object;
        if ((this.linhaArtigoPK == null && other.linhaArtigoPK != null) || (this.linhaArtigoPK != null && !this.linhaArtigoPK.equals(other.linhaArtigoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.LinhaArtigo[ linhaArtigoPK=" + linhaArtigoPK + " ]";
    }
    
}
