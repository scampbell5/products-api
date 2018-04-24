package com.sean.productsapi.loader;

import com.sean.productsapi.jpa.model.Department;
import com.sean.productsapi.jpa.model.Product;
import com.sean.productsapi.jpa.repository.DepartmentRepository;
import com.sean.productsapi.jpa.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    Service that will read data from a given CSV file and persist it to the database.
 */
@Slf4j
@Service
public class CSVProductLoader {

    private final ProductRepository productRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public CSVProductLoader(ProductRepository productRepository, DepartmentRepository departmentRepository) {
        this.productRepository = productRepository;
        this.departmentRepository = departmentRepository;
    }

    public void load(String filePath) {
        try {
            File csvData = new ClassPathResource(filePath).getFile();
            load(csvData);
        } catch (IOException e) {
            log.error("IOException occurred when reading file: " + filePath);
        }
    }

    public void load(File csvData) {
        try {
            CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(),CSVFormat.RFC4180.withFirstRecordAsHeader());

            List<Product> products = new ArrayList<>();

            for (CSVRecord csvRecord : parser) {
                products.add(getProductFromCsvRecord(csvRecord));
            }

            productRepository.saveAll(products);
        } catch (IOException e) {
            log.error("IOException was thrown: " + e.getMessage());
        }
    }

    private Product getProductFromCsvRecord(CSVRecord csvRecord) {
        Product product = new Product();
        product.setExternalId(csvRecord.get(0));
        product.setDescription(csvRecord.get(1));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm/DD/yyyy");

        try {
            product.setLastSoldDate(simpleDateFormat.parse(csvRecord.get(2)));
        } catch (ParseException e) {
            log.error("Unabe to parse date for " + product.getExternalId());
        }

        product.setShelfLife(csvRecord.get(3));

        Department department = getOrCreateDepartment(csvRecord.get(4));
        product.setDepartment(department);

        product.setPrice(csvRecord.get(5));
        product.setUnit(csvRecord.get(6));
        product.setXFor(csvRecord.get(7));
        product.setCost(csvRecord.get(8));

        return product;
    }

    private Department getOrCreateDepartment(String departmentName) {
        Optional<Department> optionalDepartment = departmentRepository.findByName(departmentName);

        return optionalDepartment.orElseGet(() -> departmentRepository.save(new Department(departmentName)));
    }
}
