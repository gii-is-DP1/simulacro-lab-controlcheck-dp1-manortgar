package org.springframework.samples.petclinic.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	private ProductRepository productRepo;
	
	@Autowired
	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public List<Product> getProductsCheaperThan(double price) {
    	List<Product> res = new ArrayList<Product>();
    	List<Product> lista = productRepo.findAll();
    	for(Product producto:lista) {
    		if(producto.price<price) {
    			res.add(producto);
    		}
    	}
    	return res;
    	
    }

    public ProductType getProductType(String typeName) {
        return productRepo.findTypeByName(typeName);
    }

    public Product save(Product p){
        return productRepo.save(p);       
    }

    public List<ProductType> findAllProductTypes(){
    	return productRepo.findAllProductTypes();
    }
}
