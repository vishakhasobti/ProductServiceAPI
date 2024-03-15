package com.scalar.productservice.service;

import com.scalar.productservice.dto.FakeStoreProductDto;
import com.scalar.productservice.exceptions.ProductNotExistsException;
import com.scalar.productservice.model.Category;
import com.scalar.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("fakeStoreProductService")  // qualifier
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    @Autowired

    //GETTING THE RESTTEMPLATE FROM APPLICATIONCONFIG -- aUTOWIRE IT YOURSELF, I WILL NOT CREATE IT .
    //how autowire:? from the application context of the spring ..
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProduct) {
        Product product = new Product();
        product.setTitle(fakeStoreProduct.getTitle());
        product.setId(fakeStoreProduct.getId());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setImageUrl(fakeStoreProduct.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProduct.getCategory());

        return product;
    }

    @Override
    //map the response directly to a Resource DTO - getForObject
    public Product getSingleProduct(Long id) throws ProductNotExistsException {
        FakeStoreProductDto productDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
                //convert into product of FakeStoreProductDto class
        );
        if (productDto == null) {
            throw new ProductNotExistsException(
                    "Product with id: " + id + " doesn't exist."
            );
        }
        //convert the productDto dto to the product type,
        // coz service wants us to return the product type:

        return convertFakeStoreProductToProduct(productDto);
    }

    @Override
    public List<Product> getAllProducts() {
         FakeStoreProductDto[] response= restTemplate.getForObject(
              "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        List<Product> answer = new ArrayList<>();

        for(FakeStoreProductDto dto: response) {
            answer.add(convertFakeStoreProductToProduct(dto));
        }
        return answer;
    }

    @Override
    public Product addnewProduct(Product product) {

        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        //resttemplate put returns void..
        //instead of put use exchnage , high level abstraction
            RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDto(),
                    FakeStoreProductDto.class);
            HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                    new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                            restTemplate.getMessageConverters());
            FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" +id, HttpMethod.PUT, requestCallback, responseExtractor, new Object[]{});

            return convertFakeStoreProductToProduct(response);
    }
    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }

}
