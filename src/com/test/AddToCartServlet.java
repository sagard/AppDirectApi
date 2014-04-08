package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Item;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 List<Item> cart = new ArrayList<Item>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		int qty = Integer.parseInt(request.getParameter("qty"));
		System.out.print(qty);
		String desc = request.getParameter("desc");
		int price = Integer.parseInt(request.getParameter("price"));
	
		HttpSession session = request.getSession();
		ArrayList<Item> cart1 = (ArrayList) session.getAttribute("cart");
		// if no items just add the new items
        if(cart1 == null) 
		{
        	Item i = new Item(name,desc,price,qty);
			cart.add(i);
		}
        // if item with same name is present update the qty else add the item
        else{
        	boolean found = false;
        	for(Item k : cart)
        		if(k.getName().equals(name)){
        			found = true;
        			k.setQty(qty + k.getQty());
        		}
             if(found == false){
            	 Item i = new Item(name,desc,price,qty);
     			cart.add(i);
             }
        }
        Integer finalPrice = (Integer) session.getAttribute("total");
        if(finalPrice == null){
        	finalPrice = price * qty;
         }
        else{
        	finalPrice = finalPrice + price * qty;
        }
        session.setAttribute("total",finalPrice);         
        
        session.setAttribute("cart",cart);
        response.sendRedirect("viewcart.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
