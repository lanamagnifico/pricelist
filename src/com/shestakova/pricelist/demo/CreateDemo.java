package com.shestakova.pricelist.demo;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shestakova.pricelist.entity.Item;
import com.shestakova.pricelist.entity.PriceReg;
import com.shestakova.pricelist.entity.PriceRegDetail;
import com.shestakova.pricelist.entity.ActualPrice;
import com.shestakova.pricelist.entity.PriceType;

public class CreateDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Item.class)
				.addAnnotatedClass(PriceType.class)
				.addAnnotatedClass(ActualPrice.class)
				.addAnnotatedClass(PriceReg.class)
				.addAnnotatedClass(PriceRegDetail.class)
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

			//			System.out.println("Creating price  ...");
			//			int tmpId = 1;
			//			Item tmpItem = session.get(Item.class, tmpId);
			////			PriceType tmpPriceType = session.get(PriceType.class, tmpId);
			//			PriceType tmpPriceType = new PriceType("trade");
			//			
			//			Price price = new Price(tmpItem,tmpPriceType,80);
			//			session.save(price);

			//			System.out.println("Getting price list  ...");
			//			int tmpId = 1;
			//			Item tmpItem = session.get(Item.class, tmpId);
			//			List<Price> tmpPriceList = tmpItem.getPricelist();
			//			System.out.println("Price for item =" + tmpPriceList);
			//			session.getTransaction().commit();
			
			//			System.out.println("Deleting price with id=2  ...");
			//			int tmpId = 2;
			//			ActualPrice tmpPrice = session.get(ActualPrice.class, tmpId);
			//			System.out.println("Price with id=2" + tmpPrice);
			//			if (tmpPrice !=null) {
			//				session.delete(tmpPrice);
			//				session.getTransaction().commit();
			//			}
			System.out.println("Creating price registration  ...");
			String theDate = "01/08/2019";
			Date tmpDate = DateUtils.parseDate(theDate);
			
			Item tmpItem = session.get(Item.class,2);
			PriceType tmpPriceType = session.get(PriceType.class, 2);
//			session.save(tmpItem);
//			session.save(tmpPriceType);
			
			PriceReg tmpPriceReg = session.get(PriceReg.class, 1);
			System.out.println(tmpPriceReg);	
//			tmpPriceReg.setStartDate(tmpDate);
//			tmpPriceReg.setPriceType(tmpPriceType);
			PriceRegDetail tmpPriceRegDetail = new PriceRegDetail(tmpItem,50);
			tmpPriceReg.addItemAndPrice(tmpPriceRegDetail);
			System.out.println(tmpPriceRegDetail);	
			
			session.save(tmpPriceRegDetail);
			
			PriceReg recordPriceReg =session.get(PriceReg.class, tmpPriceReg.getId()); 
			System.out.println(recordPriceReg);		
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
