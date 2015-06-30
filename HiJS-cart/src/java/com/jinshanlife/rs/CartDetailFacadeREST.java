/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.rs;

import com.jinshanlife.comm.Lib;
import com.jinshanlife.ejb.CartBean;
import com.jinshanlife.ejb.SystemUserBean;
import com.jinshanlife.entity.Cart;
import com.jinshanlife.entity.CartDetail;
import com.jinshanlife.entity.SystemUser;
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
 * @author kevindong
 */
@Stateless
@Path("cartdetail")
public class CartDetailFacadeREST extends AbstractFacade<CartDetail> {

    @EJB
    private SystemUserBean systemUserBean;

    @EJB
    private CartBean cartBean;

    @PersistenceContext(unitName = "HiJS-cartPU")
    private EntityManager em;

    public CartDetailFacadeREST() {
        super(CartDetail.class);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(List<CartDetail> entityList) {

        SystemUser user;
        user = systemUserBean.findById(entityList.get(0).getUserid());
        if (user == null) {
            return;
        }
        Cart cart;
        cart = cartBean.findByCartId(entityList.get(0).getCartid());
        if (cart == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();

        for (CartDetail entity : entityList) {
            entity.setStatusToNew();
            entity.setCreatorToSystem();
            entity.setCredateToNow();
            super.create(entity);
            sb.append(entity.getContent()).append(" ").append("数量").append(entity.getQty());
            if (entity.getRemark() != null && !"".equals(entity.getRemark())) {
                sb.append(entity.getRemark()).append(";");
            } else {
                sb.append(";");
            }
        }
        String str = cart.getAddress() + "预定时间" + Lib.formatDate("yy-MM-dd", cart.getRecdate()) + " " + Lib.formatDate("HH:mm", cart.getRectime());
        Lib.sendShortMessageForVendor(user.getUserid(), cart.getContacter() + cart.getPhone(), sb.toString(), cart.getAmts().add(cart.getFreight()).toString(),str);

    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, CartDetail entity) {
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
    public CartDetail find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<CartDetail> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<CartDetail> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
