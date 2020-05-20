package com.ivrotef.slashmusic.model;

import com.ivrotef.slashmusic.model.Usuario;
import com.ivrotef.slashmusic.model.PublicacionCompartidaID;

import javax.persistence.FetchType;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.EmbeddedId;


@Entity
@Table(name = "Publicacion_Compartida")
public class PublicacionCompartida {
  /* La llave compuesta del id_publicacion de la publicación y el id_compartir de la publicación compartida*/
  @EmbeddedId
  private PublicacionCompartidaID publicacionCompartidaID;

  @ManyToOne
  @JoinColumn(name = "usuario")
  private Usuario usuarioPC;

  public PublicacionCompartida(){
  }

  public PublicacionCompartida (PublicacionCompartidaID publicacionCompartidaID) {
    this.publicacionCompartidaID = publicacionCompartidaID;
  }

  public PublicacionCompartidaID getPublicacionCompartidaID () {
    return this.publicacionCompartidaID;
  }

  public void setPublicacionCompartidaID (PublicacionCompartidaID publicacionCompartidaID) {
    this.publicacionCompartidaID = publicacionCompartidaID;
  }

  public Usuario getUsuarioPC () {
    return this.usuarioPC;
  }

  public void setUsuarioPC (Usuario usuarioPC) {
    this.usuarioPC = usuarioPC;
  }
}