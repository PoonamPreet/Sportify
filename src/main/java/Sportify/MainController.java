package Sportify;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import Sportify.dao.MainDao;
import Sportify.model.Sportify;

@Controller
public class MainController {
	
	@Autowired
	private MainDao mainDao;

	@RequestMapping("/home")
	public String home(Model m)
	
	{
		List<Sportify>products=mainDao.getProducts();
		
		m.addAttribute("products",products);
		
		return "home";
	}
	
	@RequestMapping("/customer")
	public String Customer(Model m)
	
	{
		List<Sportify>products=mainDao.getProducts();
		
		m.addAttribute("products",products);
		
		return "customer";
	}
	
	
	//show add product form
	@RequestMapping("/add-product")
	public String addProduct(Model m)
	{
		m.addAttribute("title","Add Product");
		return "add_product";
	}
	
	//handle add product form
	@RequestMapping(value="/handle-product" ,method=RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Sportify product,HttpServletRequest request)
	{
		System.out.println(product);
		mainDao.createProduct(product);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	
	@RequestMapping(value="/cart-product" ,method=RequestMethod.POST)
	public RedirectView cartProduct(@ModelAttribute Sportify product,HttpServletRequest request)
	{
		System.out.println(product);
		mainDao.cartProduct(0, null, 0);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	
	//delete handler 
	@RequestMapping("/delete-product/{pid}")
	public RedirectView deleteProduct(@PathVariable("pid")  int pid,HttpServletRequest request)
	{
		
		this.mainDao.deleteProduct(pid);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	
	//update handler 
		@RequestMapping("/update-product/{pid}")
		public String updateProduct(@PathVariable("pid")  int pid,Model m)
		{
			Sportify product=this.mainDao.getProduct(pid);
			m.addAttribute("product",product);
			return "update_form";
		}
		
		
}
