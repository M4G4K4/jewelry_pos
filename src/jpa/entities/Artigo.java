package jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ARTIGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artigo.findAll", query = "SELECT a FROM Artigo a")
    , @NamedQuery(name = "Artigo.findByIdartigo", query = "SELECT a FROM Artigo a WHERE a.idartigo = :idartigo")
    , @NamedQuery(name = "Artigo.findByIniciais", query = "SELECT a FROM Artigo a WHERE a.iniciais = :iniciais")
    , @NamedQuery(name = "Artigo.findByQtd", query = "SELECT a FROM Artigo a WHERE a.qtd = :qtd")
    , @NamedQuery(name = "Artigo.findByPeso", query = "SELECT a FROM Artigo a WHERE a.peso = :peso")
    , @NamedQuery(name = "Artigo.findByDescricao", query = "SELECT a FROM Artigo a WHERE a.descricao = :descricao")
    , @NamedQuery(name = "Artigo.findByToque", query = "SELECT a FROM Artigo a WHERE a.toque = :toque")
    , @NamedQuery(name = "Artigo.findByMetal", query = "SELECT a FROM Artigo a WHERE a.metal = :metal")
    , @NamedQuery(name = "Artigo.findByPrecoCompra", query = "SELECT a FROM Artigo a WHERE a.precoCompra = :precoCompra")
    , @NamedQuery(name = "Artigo.findByMargem", query = "SELECT a FROM Artigo a WHERE a.margem = :margem")
    , @NamedQuery(name = "Artigo.findByIva", query = "SELECT a FROM Artigo a WHERE a.iva = :iva")
    , @NamedQuery(name = "Artigo.findByDesconto", query = "SELECT a FROM Artigo a WHERE a.desconto = :desconto")
    , @NamedQuery(name = "Artigo.findByIdartigofornecedor", query = "SELECT a FROM Artigo a WHERE a.idartigofornecedor = :idartigofornecedor")
    , @NamedQuery(name = "Artigo.findByDescricaoLike", query = "SELECT a FROM Artigo a WHERE a.descricao LIKE CONCAT('%',:descricao,'%')")
    , @NamedQuery(name = "Artigo.findByPrecofinal", query = "SELECT a FROM Artigo a WHERE a.precofinal = :precofinal")
    , @NamedQuery(name = "Artigo.findByCod", query = "SELECT a FROM Artigo a WHERE a.cod = :cod")})
public class Artigo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDARTIGO")
    private Integer idartigo;
    @Basic(optional = false)
    @Column(name = "INICIAIS")
    private String iniciais;
    @Basic(optional = false)
    @Column(name = "QTD")
    private short qtd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PESO")
    private BigDecimal peso;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "TOQUE")
    private short toque;
    @Basic(optional = false)
    @Column(name = "METAL")
    private String metal;
    @Basic(optional = false)
    @Column(name = "PRECO_COMPRA")
    private BigDecimal precoCompra;
    @Basic(optional = false)
    @Column(name = "MARGEM")
    private short margem;
    @Basic(optional = false)
    @Column(name = "IVA")
    private short iva;
    @Column(name = "DESCONTO")
    private Short desconto;
    @Basic(optional = false)
    @Column(name = "IDARTIGOFORNECEDOR")
    private int idartigofornecedor;
    @Column(name = "PRECOFINAL")
    private BigDecimal precofinal;
    @Column(name = "COD")
    private String cod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artigo")
    private Collection<LinhaArtigo> linhaArtigoCollection;
    @JoinColumn(name = "IDCOMPRA", referencedColumnName = "IDCOMPRA")
    @ManyToOne(optional = false)
    private Compra idcompra;

    public Artigo() {
    }

    public Artigo(Integer idartigo) {
        this.idartigo = idartigo;
    }

    public Artigo(Integer idartigo, String iniciais, short qtd, BigDecimal peso, short toque, String metal, BigDecimal precoCompra, short margem, short iva, int idartigofornecedor) {
        this.idartigo = idartigo;
        this.iniciais = iniciais;
        this.qtd = qtd;
        this.peso = peso;
        this.toque = toque;
        this.metal = metal;
        this.precoCompra = precoCompra;
        this.margem = margem;
        this.iva = iva;
        this.idartigofornecedor = idartigofornecedor;
    }

    public Integer getIdartigo() {
        return idartigo;
    }

    public void setIdartigo(Integer idartigo) {
        this.idartigo = idartigo;
    }

    public String getIniciais() {
        return iniciais;
    }

    public void setIniciais(String iniciais) {
        this.iniciais = iniciais;
    }

    public short getQtd() {
        return qtd;
    }

    public void setQtd(short qtd) {
        this.qtd = qtd;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public short getToque() {
        return toque;
    }

    public void setToque(short toque) {
        this.toque = toque;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }

    public short getMargem() {
        return margem;
    }

    public void setMargem(short margem) {
        this.margem = margem;
    }

    public short getIva() {
        return iva;
    }

    public void setIva(short iva) {
        this.iva = iva;
    }

    public Short getDesconto() {
        return desconto;
    }

    public void setDesconto(Short desconto) {
        this.desconto = desconto;
    }

    public int getIdartigofornecedor() {
        return idartigofornecedor;
    }

    public void setIdartigofornecedor(int idartigofornecedor) {
        this.idartigofornecedor = idartigofornecedor;
    }

    public BigDecimal getPrecofinal() {
        return precofinal;
    }

    public void setPrecofinal(BigDecimal precofinal) {
        this.precofinal = precofinal;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    @XmlTransient
    public Collection<LinhaArtigo> getLinhaArtigoCollection() {
        return linhaArtigoCollection;
    }

    public void setLinhaArtigoCollection(Collection<LinhaArtigo> linhaArtigoCollection) {
        this.linhaArtigoCollection = linhaArtigoCollection;
    }

    public Compra getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Compra idcompra) {
        this.idcompra = idcompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idartigo != null ? idartigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artigo)) {
            return false;
        }
        Artigo other = (Artigo) object;
        if ((this.idartigo == null && other.idartigo != null) || (this.idartigo != null && !this.idartigo.equals(other.idartigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + getIdartigo() + " - " + getDescricao();
    }
    
}
