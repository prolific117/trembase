/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity;

import com.proxy.leanstack.commons.repository.AbstractRepositoryModel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

/**
 *
 * @author olatunji.oduro
 */
@Entity
@Table(name = "invoice")
@NamedQueries({
  }     
)
public class Invoice extends AbstractRepositoryModel implements Serializable{
    
}
