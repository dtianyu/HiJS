/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.rs;

import com.jinshanlife.comm.Lib;
import com.jinshanlife.ejb.StoreBean;
import com.jinshanlife.ejb.SystemUserBean;
import com.jinshanlife.entity.Cart;
import com.jinshanlife.entity.Store;
import com.jinshanlife.entity.SystemUser;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author kevindong
 */
@Stateless
@Path("cart")
public class CartFacadeREST extends AbstractFacade<Cart> {

    @EJB
    private SystemUserBean systemUserBean;
    
    @EJB
    private StoreBean storeBean;
    
    @PersistenceContext(unitName = "HiJS-cartPU")
    private EntityManager em;
    
    public CartFacadeREST() {
        super(Cart.class);
    }
    
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Cart entity) {
        SystemUser user ;
        user = systemUserBean.findByUserId(entity.getPhone());        
        if (user==null){
            Integer pwd = (int) (Math.random() * 10000);
            try {
                user = new SystemUser(entity.getPhone(),entity.getContacter(),Lib.securityMD5(pwd.toString()));
                user.setSuperuser(Boolean.FALSE);
                user.setOwnstore(Boolean.FALSE);
                user.setLocked(Boolean.FALSE);
                user.setStatusToNew();
                user.setCreatorToSystem();
                user.setCredateToNow();
                systemUserBean.persist(user);
                user = systemUserBean.findByUserId(entity.getPhone());
                Lib.sendShortMessagePassword(entity.getPhone(), pwd.toString());
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CartFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                      
        Store store ;
        store = storeBean.findById(entity.getStoreid());
        if(store ==null){
            return ;
        }
        
        entity.setUserid(user.getId());
        entity.setStore(store.getName());
        entity.setStatus("N");
        entity.setCreator("system");
        entity.setCredate(Lib.getDate());
        super.create(entity);
        
        Lib.sendShortMessageForCustomer(entity.getPhone(), store.getName(), entity.getCartid(),entity.getAmts().add(entity.getFreight()).toString());
        
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
    
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Cart> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Cart> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
