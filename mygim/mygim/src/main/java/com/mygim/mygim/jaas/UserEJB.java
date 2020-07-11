/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.jaas;

import com.mygim.mygim.entities.Grupousuario;
import com.mygim.mygim.entities.Usuario;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author JavierSanchezGozalo
 */
@Stateless
public class UserEJB {

    @PersistenceContext
    private EntityManager em;

    public Usuario createUser(Usuario usuario) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            usuario.setPassword(AuthenticationUtils.encodeSHA256(usuario.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Grupousuario grupousuario = new Grupousuario();
        grupousuario.setEmail(usuario.getEmail());

        if (request.isUserInRole("admin")) {
            grupousuario.setNombregrupo("entrenador");
        } else {
            grupousuario.setNombregrupo("cliente");
        }

        em.persist(usuario);
        em.persist(grupousuario);
        return usuario;
    }

    public void destroyUser(Usuario usuario, Grupousuario grupo) {
        em.getTransaction().begin();
        em.remove(usuario);
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.remove(grupo);
        em.getTransaction().commit();
    }

    public Usuario findByEmail(String email) {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByEmail",
                Usuario.class);
        query.setParameter("email", email);
        Usuario user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
        }
        return user;
    }

    public Usuario getUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        return findByEmail(request.getUserPrincipal().getName());
    }

}
