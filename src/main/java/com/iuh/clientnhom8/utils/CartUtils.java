package com.iuh.clientnhom8.utils;

import com.iuh.clientnhom8.entity.Cart;

import javax.servlet.http.HttpServletRequest;

public class CartUtils {
	 
	   // Products in the cart, stored in Session.
	   public static Cart getCartInSession(HttpServletRequest request) {

		   Cart cartInfo = (Cart) request.getSession().getAttribute("myCart");
	 
	    
	      if (cartInfo == null) {
	         cartInfo = new Cart();
	          
	         request.getSession().setAttribute("myCart", cartInfo);
	      }
	 
	      return cartInfo;
	   }
	 
	   public static void removeCartInSession(HttpServletRequest request) {
	      request.getSession().removeAttribute("myCart");
	   }
	 
	   public static void storeLastOrderedCartInSession(HttpServletRequest request, Cart cartInfo) {
	      request.getSession().setAttribute("lastOrderedCart", cartInfo);
	   }
	 
	   public static Cart getLastOrderedCartInSession(HttpServletRequest request) {
	      return (Cart) request.getSession().getAttribute("lastOrderedCart");
	   }
	    
	}
