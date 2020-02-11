/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Pedro
 */
@Embeddable
public class LinhaArtigoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IDARTIGO")
    private int idartigo;
    @Basic(optional = false)
    @Column(name = "IDVENDA")
    private int idvenda;

    public LinhaArtigoPK() {
    }

    public LinhaArtigoPK(int idartigo, int idvenda) {
        this.idartigo = idartigo;
        this.idvenda = idvenda;
    }

    public int getIdartigo() {
        return idartigo;
    }

    public void setIdartigo(int idartigo) {
        this.idartigo = idartigo;
    }

    public int getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(int idvenda) {
        this.idvenda = idvenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idartigo;
        hash += (int) idvenda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LinhaArtigoPK)) {
            return false;
        }
        LinhaArtigoPK other = (LinhaArtigoPK) object;
        if (this.idartigo != other.idartigo) {
            return false;
        }
        if (this.idvenda != other.idvenda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.LinhaArtigoPK[ idartigo=" + idartigo + ", idvenda=" + idvenda + " ]";
    }
    
}
