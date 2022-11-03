package org.springframework.samples.petclinic.product;

//package org.springframework.samples.petclinic.product;
//
//import java.util.Map;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/product")
//public class ProductController {
//	
//	private static final String VIEWS_PRODUCT_CREATE_OR_UPDATE_FORM = "products/createOrUpdateProductForm";
//	
//	private ProductService productserv;
//	
//	@Autowired
//	public ProductController(ProductService serv) {
//		this.productserv = serv;
//	}
//	
//	@GetMapping(value = "/create")
//	public String initCreationForm(Map<String, Object> model) {
//		Product product = new Product();
//		model.put("product", product);
//		return VIEWS_PRODUCT_CREATE_OR_UPDATE_FORM;
//	}
//    
//	@PostMapping(value = "/create")
//	public String processCreationForm(@Valid Product product, BindingResult result) {
//		if (result.hasErrors()) {
//			return VIEWS_PRODUCT_CREATE_OR_UPDATE_FORM;
//		}
//		else {
//			//creating owner, user and authorities
//			this.productserv.save(product);
//			
//			return "redirect:/welcome/";
//		}
//	}
//}
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    ProductService productService;

    @ModelAttribute("productType")
    public List<ProductType> populateProductTypes() {
        return this.productService.findAllProductTypes();
    }

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/create")
    public ModelAndView createProduct() {
        ModelAndView mav = new ModelAndView("products/createOrUpdateProductForm");
        mav.addObject("product", new Product());
        return mav;
    }

    @PostMapping(value = "/product/create")
    public ModelAndView processCreationForm(@Valid Product product, BindingResult result) {
        ModelAndView mav;
        if (result.hasErrors()) {
            mav=new ModelAndView("products/createOrUpdateProductForm");
            mav.addObject("product", product);
            mav.addObject("types", productService.findAllProductTypes());
        }
        else {
            this.productService.save(product);
            mav = new ModelAndView("welcome");
        }
        return mav;
    }

}