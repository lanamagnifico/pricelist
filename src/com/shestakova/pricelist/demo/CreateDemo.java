package com.shestakova.pricelist.demo;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shestakova.pricelist.entity.Item;
import com.shestakova.pricelist.entity.ItemCategory;
import com.shestakova.pricelist.entity.PriceReg;
import com.shestakova.pricelist.entity.PriceRegDetail;
import com.shestakova.pricelist.entity.ActualPrice;
import com.shestakova.pricelist.entity.PriceType;

public class CreateDemo {

	private static SessionFactory factory;
	private static Session session;

	private static Item createItem(String title) {
		Item tmpItem = new Item(title);
		session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(tmpItem);
		session.getTransaction().commit();
		return tmpItem;
	}

	private static void deleteItem(Item tmpItem) {
		session = factory.getCurrentSession();
		session.beginTransaction();
		session.delete(tmpItem);
		session.getTransaction().commit();
	}
	private static ItemCategory createCategory(String title, Item tmpItem) {
		ItemCategory tmpCategory = new ItemCategory(title);
		tmpCategory.addItem(tmpItem);
		session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(tmpCategory);
		session.getTransaction().commit();	
		return tmpCategory;
	}
	private static void deleteCategory(ItemCategory tmpCategory) {
		session = factory.getCurrentSession();
		session.beginTransaction();
		session.delete(tmpCategory);
		session.getTransaction().commit();
	}
	private static PriceType createPriceType(String title) {
		PriceType tmpPriceType = new PriceType(title);
		session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(tmpPriceType);
		session.getTransaction().commit();
		return tmpPriceType;
	}

	private static void deletePriceType(PriceType tmpPriceType) {
		session = factory.getCurrentSession();
		session.beginTransaction();
		session.delete(tmpPriceType);
		session.getTransaction().commit();
	}

	private static ActualPrice createActualPrice(int tmpItemId,int tmpPriceTypeId, int price) {
		session = factory.getCurrentSession();
		session.beginTransaction();
		Item tmpItem = session.get(Item.class, tmpItemId);
		PriceType tmpPriceType = session.get(PriceType.class, tmpPriceTypeId);
		ActualPrice tmpPrice = new ActualPrice(tmpItem,tmpPriceType,price);
		session.save(tmpPrice);
		session.getTransaction().commit();
		return tmpPrice;
	}
	private static void deleteActualPrice(ActualPrice tmpActualPrice) {
		session = factory.getCurrentSession();
		session.beginTransaction();
		session.delete(tmpActualPrice);
		session.getTransaction().commit();
	}
	private static PriceReg createPriceReg(Date tmpDate, 
			int tmpItemId, 
			int tmpPriceTypeId, 
			int newValue) {
		session = factory.getCurrentSession();
		session.beginTransaction();	
		PriceType tmpPriceType = session.get(PriceType.class, tmpPriceTypeId);

		PriceReg tmpPriceReg = new PriceReg();
		tmpPriceReg.setStartDate(tmpDate);
		tmpPriceReg.setPriceType(tmpPriceType);

		Item tmpItem = session.get(Item.class, tmpItemId);
		PriceRegDetail tmpPriceRegDetail = new PriceRegDetail(tmpItem,newValue);
		tmpPriceReg.addItemAndPrice(tmpPriceRegDetail);

		session.save(tmpPriceReg);
		session.getTransaction().commit();
		return tmpPriceReg;
	}
	private static void deletePriceReg(PriceReg tmpPriceReg) {
		session = factory.getCurrentSession();
		session.beginTransaction();
		session.delete(tmpPriceReg);
		session.getTransaction().commit();
	}
	public static void main(String[] args) {
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Item.class)
				.addAnnotatedClass(PriceType.class)
				.addAnnotatedClass(ActualPrice.class)
				.addAnnotatedClass(PriceReg.class)
				.addAnnotatedClass(PriceRegDetail.class)
				.addAnnotatedClass(ItemCategory.class)
				.buildSessionFactory();
		System.out.println("Factory created!");

		try {
			// CREATE 
			Map<String,Object> mapObjects = createObjects();	
			// DELETE 
			deleteObjects(mapObjects);
			System.out.println("Done!");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

	private static void deleteObjects(Map<String,Object> mapObjects) {
		// Price registration
		PriceReg tmpPriceReg = (PriceReg)mapObjects.get("PriceReg");
		if (tmpPriceReg != null) {
			System.out.println("\nDeleting the price registration ...");
			deletePriceReg((PriceReg)mapObjects.get("PriceReg"));
			System.out.println("Price registration was deleted succesfully");
		}
		// Actual prices
		ActualPrice tmpActualPrice = (ActualPrice)mapObjects.get("ActualPrice");
		if (tmpActualPrice != null) {
			System.out.println("\nDeleting the actual price type ...");
			deleteActualPrice(tmpActualPrice);
			System.out.println("Actual price was deleted succesfully");
		}
		// Price types
		PriceType tmpPriceType = (PriceType)mapObjects.get("PriceType");
		if (tmpPriceType != null) {
			System.out.println("\nDeleting the price type ...");
			deletePriceType(tmpPriceType);	
			System.out.println("Price type was deleted succesfully"); 
		}
		// Categories
		ItemCategory tmpCategory = (ItemCategory)mapObjects.get("Category");
		if (tmpCategory != null) {
			System.out.println("\nDeleting the category ...");
			deleteCategory(tmpCategory);
			System.out.println("Category was deleted succesfully");
		}
		// Items
		Item tmpItem = (Item)mapObjects.get("Item");
		if (tmpItem != null) {
			System.out.println("\nDeleting the item ...");
			deleteItem(tmpItem);
			System.out.println("Item was deleted succesfully");
		}
	}

	private static Map<String,Object> createObjects() throws Exception, ParseException {
		Map<String,Object> tmpMapObjects = new HashMap<>();
		// Items
		System.out.println("\nCreating item ...");
		Item tmpItem = createItem("Test item");
		int tmpItemId = tmpItem.getId();
		if (tmpItemId==0) {
			throw new Exception("Item was not cteated!");
		} else {
			tmpMapObjects.put("Item", tmpItem);
			System.out.println("Item was created succesfully: " + tmpItem);
		}	
		// Categories
		System.out.println("\nCreating category ...");
		ItemCategory tmpCategory = createCategory("Test category",tmpItem);
		int tmpCategoryId = tmpCategory.getId();
		if (tmpCategoryId==0) {
			throw new Exception("Category was not cteated!");
		} else {
			tmpMapObjects.put("Category", tmpCategory);
			System.out.println("Category was created succesfully: " + tmpCategory);
		}	
		// Price types
		System.out.println("\nCreating price type ...");
		PriceType tmpPriceType = createPriceType("Test price type");
		int tmpPriceTypeId = tmpPriceType.getId();
		if (tmpPriceTypeId==0) {
			throw new Exception("Price type was not cteated!");
		} else {
			tmpMapObjects.put("PriceType", tmpPriceType);
			System.out.println("Price type was created succesfully: " + tmpPriceType);
		}
		// Actual prices
		System.out.println("\nCreating actual price  ...");
		ActualPrice tmpActualPrice = createActualPrice(tmpItemId,tmpPriceTypeId,75);
		if (tmpActualPrice.getId()==0) {
			throw new Exception("Price was not cteated!");
		} else {
			tmpMapObjects.put("ActualPrice", tmpActualPrice);
			System.out.println("Price was created succesfully: " + tmpActualPrice);
		}
		// Price registration 
		System.out.println("\nCreating price registration  ...");
		String theDate = "01/08/2019";
		Date tmpDate = DateUtils.parseDate(theDate);
		PriceReg tmpPriceReg = createPriceReg(tmpDate, 
				tmpItemId, 
				tmpPriceTypeId,
				50);
		if (tmpPriceReg.getId()==0) {
			throw new Exception("Price registration was not cteated!");
		} else {
			tmpMapObjects.put("PriceReg", tmpPriceReg);
			System.out.println("Price registration was created succesfully: " + tmpPriceReg);
		}
		return tmpMapObjects;
	}

	
}
