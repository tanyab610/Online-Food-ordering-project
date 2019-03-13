package com.ofo.servlets.pojos;

import java.util.ArrayList;

public class OrderDetailsList {

	private ArrayList<OrderDetails> orderDetailsList;

	public OrderDetailsList() {
		orderDetailsList = new ArrayList<OrderDetails>();
	}

	public synchronized void create(int productId, int quantity, float price) {
		boolean found = false;
		for (OrderDetails od : orderDetailsList) {
			int pid = od.getProductId();
			if (pid == productId) {
				od.setQuantity(od.getQuantity() + 1);
				found = true;
				break;
			}
		}
		if (!found) {
			OrderDetails od = new OrderDetails(0, productId, quantity, price);
			orderDetailsList.add(od);
		}
	}

	public synchronized void remove(int productId) {
		for (int i = 0; i < orderDetailsList.size(); i++) {
			int pid = orderDetailsList.get(i).getProductId();
			if (pid == productId) {
				orderDetailsList.remove(i);
				break;
			}
		}
	}

	public synchronized ArrayList<OrderDetails> get() {
		return orderDetailsList;
	}
}