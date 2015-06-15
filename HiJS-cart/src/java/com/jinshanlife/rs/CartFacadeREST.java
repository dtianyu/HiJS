/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.rs;

import com.jinshanlife.ejb.CartBean;
import com.jinshanlife.entity.Cart;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author C0160
 */
@Stateless
@Path("cart")
public class CartFacadeREST extends AbstractFacade<Cart> {

    @EJB
    private CartBean cartBean;
    
    public CartFacadeREST() {
        super(Cart.class);
    }
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(List<Cart> entityList) {
        setSuperEJB(cartBean);
        for (Cart entity : entityList) {
            super.create(entity);
        }      
    }
    
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Cart entity) {
        super.edit(entity);
    }
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Cart find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
}
