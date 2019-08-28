package com.shestakova.pricelist.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shestakova.pricelist.entity.Item;
import com.shestakova.pricelist.entity.Price;
import com.shestakova.pricelist.entity.PriceType;

public class CreateDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Item.class)
				.addAnnotatedClass(PriceType.class)
				.addAnnotatedClass(Price.class)
				.buildSessionFactory();
		System.out.println("Factory created");
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
//			System.out.println("Creating item ...");
//			Item tmpItem = new Item("Black Box");				
//			session.save(tmpItem);
			
//			System.out.println("Creating price type ...");
//			PriceType tmpPriceType = new PriceType("retail");				
//			session.save(tmpPriceType);
			
			System.out.println("Creating price  ...");
			int tmpId = 1;
			Item tmpItem = session.get(Item.class, tmpId);
//			PriceType tmpPriceType = session.get(PriceType.class, tmpId);
			PriceType tmpPriceType = new PriceType("trade");
			
			Price price = new Price(tmpItem,tmpPriceType,80);
			session.save(price);
			
			session.getTransaction().commit();
			System.out.println("Done!!!");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factory.close();
		}
	}

}
