package com.product.microservices.ecommerce.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.product.microservices.ecommerce.model.Cart;
import com.product.microservices.ecommerce.model.JwtToken;
import com.product.microservices.ecommerce.model.Product;
import com.product.microservices.ecommerce.model.UserLoginCredential;
import com.product.microservices.ecommerce.model.Wishlist;
import com.product.microservices.ecommerce.proxy.AuthorizationProxy;
import com.product.microservices.ecommerce.proxy.ProceedToBuyProxy;
import com.product.microservices.ecommerce.proxy.ProductProxy;
import com.product.microservices.ecommerce.util.DateUtil;

import feign.FeignException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ECommerceController implements ErrorController {

	@Autowired
	HttpSession httpSession;
	
	@Autowired
	private AuthorizationProxy authorizationProxy;
	
	@Autowired
	private ProductProxy productProxy;
	
	@Autowired
	private ProceedToBuyProxy proceedToBuyProxy;
	
	private static final String PATH = "/error";
	
	@GetMapping("/")
	public ModelAndView login(@ModelAttribute("credentials") UserLoginCredential credentials) {
		return new ModelAndView("login", "invalid", false);
	}

	@Retry(name = "default", fallbackMethod = "ServiceNotAvailable")
	@PostMapping("/login")
	public ModelAndView loginSubmit(@ModelAttribute("credentials") UserLoginCredential credentials) {
	
		log.debug("Validating user details and generating token");
		
		JwtToken jwttoken = null;
		
		if(credentials.getUname() != null && credentials.getUpassword() != null && !credentials.getUname().isEmpty() && !credentials.getUpassword().isEmpty())
		{
			jwttoken = authorizationProxy.generateToken(credentials.getUname(), credentials.getUpassword());	
		}
		else
		{
			log.debug("Invalid user details");
			return new ModelAndView("login", "invalid", true);
		}
		
		if((jwttoken.getToken()).equals("default"))
		{
			log.debug("Invalid user details");
			return new ModelAndView("login", "invalid", true);
		}
		
		httpSession.setAttribute("token", jwttoken.getToken());
		httpSession.setAttribute("username", credentials.getUname());
		log.debug("Valid user details for user : "+credentials.getUname());
		return new ModelAndView("home", "notFound", false);
	}
	
	@Retry(name = "default", fallbackMethod = "ServiceNotAvailable")
	@PostMapping("/searchById.html")
	public ModelAndView searchById(@RequestParam("id") String id) {
		log.debug("Searching product by id");
		
		String token = (String)httpSession.getAttribute("token");
		
		if(id == "")
		{
			log.debug("Product not found");
			return new ModelAndView("home", "notFound", true);
		}
		
		Long productId = Long.valueOf(id);
						
		Product product = productProxy.searchProductById(token, productId);
	
		if(product.getProductId() == null)
		{
			log.debug("Product not found");
			return new ModelAndView("home", "notFound", true);
		}

		ModelAndView mv = new ModelAndView("product");
		mv.addObject("product", product);
		mv.addObject("noVendor", false);
		
		log.debug("Product found");
		
		return mv;
	}
	
	@Retry(name = "default", fallbackMethod = "ServiceNotAvailable")
	@PostMapping("/searchByName.html")
	public ModelAndView searchByName(@RequestParam("name") String name) {
		log.debug("Searching product by name");
		
		String token = (String)httpSession.getAttribute("token");
		
		if(name == "")
		{
			log.debug("Product not found");
			return new ModelAndView("home", "notFound", true);
		}
						
		Product product = productProxy.searchProductByName(token, name);

		if(product.getProductId() == null)
		{
			log.debug("Product not found");
			return new ModelAndView("home", "notFound", true);
		}
		ModelAndView mv = new ModelAndView("product");
		mv.addObject("product", product);
		mv.addObject("noVendor", false);
		
		log.debug("Product found");
		
		return mv;
	}
	
	@Retry(name = "default", fallbackMethod = "ServiceNotAvailable")
	@PostMapping("/addRating.html")
	public ModelAndView addRating(@RequestParam("rating") String rating, @RequestParam("productId") String productId)
	{
		log.debug("Updating product rating");
		
		String token = (String)httpSession.getAttribute("token");
		boolean productAdded = productProxy.addProductRating(token, Long.valueOf(productId), Float.valueOf(rating));
	
		if(productAdded)
		{
			log.debug("Rating updated");
		}
		else
		{
			log.debug("Rating not updated");
		}
		
		Product product = productProxy.searchProductById(token, Long.valueOf(productId));
		ModelAndView mv = new ModelAndView("product");
		mv.addObject("product", product);
		mv.addObject("noVendor", false);
		
		return mv;
	}
	
	@Retry(name = "default", fallbackMethod = "ServiceNotAvailable")
	@PostMapping("/checkout.html")
	public ModelAndView addToCart(@RequestParam("quantity") String quantity, @RequestParam("zipcode") String zipcode, @RequestParam("deliverydate") String deliverydate, @RequestParam("productId") String productId)
	{
		log.debug("Adding product to cart");
		
		String token = (String)httpSession.getAttribute("token");
		Cart cart = new Cart();
		cart.setDeliveryDate(DateUtil.convertToDate(deliverydate));
		cart.setProductId(Long.valueOf(productId));
		cart.setZipcode(Integer.parseInt(zipcode));
		
		Cart resultCart = proceedToBuyProxy.addProductToCart(token, cart, Long.valueOf(quantity));
		
		Product product = productProxy.searchProductById(token, Long.valueOf(productId));
		
		if(resultCart.getCartId() == null)
		{
			log.debug("Vendor not found");
			ModelAndView mv = new ModelAndView("product");
			mv.addObject("noVendor", true);
			mv.addObject("product", product);
			
			return mv;
		}
		
		if(resultCart.getVendor().getVendorId() == 0)
		{
			log.debug("Vendor not found");
			ModelAndView mv = new ModelAndView("product");
			mv.addObject("noVendor", true);
			mv.addObject("product", product);
			
			return mv;
		}
		
		log.debug("Vendor found. Product added to cart");
			
		ModelAndView mv = new ModelAndView("checkout");
		mv.addObject("quantity", Long.valueOf(quantity));
		mv.addObject("product", product);
		mv.addObject("cart", resultCart);
		
		return mv;
	}
	
	@Retry(name = "default", fallbackMethod = "ServiceNotAvailable")
	@GetMapping("/addToWishlist.html")
	public ModelAndView addToWishlist(@RequestParam("productId") String productId)
	{
		log.debug("Adding product to Wishlist");
		
		String token = (String)httpSession.getAttribute("token");
		String username = (String)httpSession.getAttribute("username");
		
		Product product = productProxy.searchProductById(token, Long.valueOf(productId));
		Wishlist wishlist = new Wishlist();
		wishlist.setAddedToWishlist(new Date());
		wishlist.setProduct(product);
		wishlist.setUsername(username);
	
		boolean status = proceedToBuyProxy.addProductToWishlist(token, wishlist);
		
		if(status) {
			log.debug("Product added to wishlist for user : " + username);
		}
		
		List<Wishlist> list = proceedToBuyProxy.getAllProducts(token, username);
		
		ModelAndView mv = new ModelAndView("wishlist");
		mv.addObject("list", list);
		
		return mv;
	}

	@GetMapping("/wishlist.html")
	public ModelAndView searchWishlist()
	{
		log.debug("Fetching wishlist details");
		
		String token = (String)httpSession.getAttribute("token");
		String username = (String)httpSession.getAttribute("username");
		List<Wishlist> list = proceedToBuyProxy.getAllProducts(token, username);
		
		if(list.isEmpty())
		{
			return new ModelAndView("empty-wishlist");
		}
		
		ModelAndView mv = new ModelAndView("wishlist");
		mv.addObject("list", list);
		
		return mv;
	}
	
	@GetMapping("/logout.html")
	public ModelAndView logout()
	{
		log.debug("Logging out");
		
		httpSession.removeAttribute("token");
		httpSession.removeAttribute("username");
		httpSession.invalidate();
		ModelAndView mv = new ModelAndView("redirect:/");
		
		return mv;
	}
	
	
	/*
	 * Method to check unauthorized access to microservice through authentication gateway.
	 * Token is not passed in header.
	*/
	@Retry(name = "default", fallbackMethod = "ServiceNotAvailable")
	@GetMapping("/checkHeader")
	public ModelAndView check() {
		log.debug("Checking Unauthorized access");
		productProxy.checkHeader();
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = PATH)
    public void error() throws Exception {
		throw new Exception();
    }

	/*
	 * @Override public String getErrorPath() { return PATH; }
	 */
	
	public ModelAndView ServiceNotAvailable(FeignException.Unauthorized ex)
	{
		log.debug("Exception handling : Unauthorized exception caught");
		return new ModelAndView("unauthorized");
	}
	
	public ModelAndView ServiceNotAvailable(Exception ex)
	{
		log.debug("Exception handling : Service not available exception caught");
		ex.printStackTrace();
		return new ModelAndView("PageNotFound");
	}
}
