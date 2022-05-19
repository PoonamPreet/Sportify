package Sportify.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import Sportify.model.Sportify;


@Component
public class MainDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public void createProduct(Sportify sportify)
	
	{
		
		this.hibernateTemplate.saveOrUpdate(sportify);
		
	}
	
	
	public List<Sportify> getProducts()
	{
		List<Sportify> products=this.hibernateTemplate.loadAll(Sportify.class);
		return products;
	}
	
	@Transactional
	public void deleteProduct(int pid)
	{
		
		Sportify product=this.hibernateTemplate.load(Sportify.class,pid);
		this.hibernateTemplate.delete(product);
	}
	
	public Sportify getProduct(int pid)
	{
		return this.hibernateTemplate.get(Sportify.class,pid);
	}
	public Sportify cartProduct(int pid,String name,int price)
	{
		return this.hibernateTemplate.get(Sportify.class,pid);
		
	}
	
}

