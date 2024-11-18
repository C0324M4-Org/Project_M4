package com.example.moji_store.service.impl;

import com.example.moji_store.dto.ProductDTO;
import com.example.moji_store.model.Product;
import com.example.moji_store.repository.IProductRepository;
import com.example.moji_store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository iProductRepository;

    @Autowired
    public ProductServiceImpl(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    // Inject giá trị từ file cấu hình
    @Value("${file.upload.dir}")
    private String uploadDir;
    @Value("${file.upload.max-size}")
    private long maxSize;
    @Value("${file.upload.allowed-types}")
    private String allowedFileTypes;

    @Override
    public List<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public void save(Product products) {
        iProductRepository.save(products);
    }

    @Override
    public void deleteById(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public Product convertToProduct(ProductDTO productDTO) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());

        // Lưu ảnh và lấy đường dẫn
        String imagePath = saveImage(productDTO.getImage());
        if (imagePath != null) {
            product.setImage(imagePath);
        }
        return product;
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            if (!isValidImageType(file)) {
                throw new IOException("Định dạng file không hợp lệ. Chỉ chấp nhận file ảnh.");
            }

            if (file.getSize() > maxSize) {
                throw new IOException("Kích thước file quá lớn. Tối đa: " + maxSize + " bytes");
            }

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath); // Tạo thư mục nếu chưa tồn tại
            }

            String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            Files.write(filePath, file.getBytes());

            return "/img/" + fileName;
        }
        return null;
    }

    private boolean isValidImageType(MultipartFile file) {
        String[] allowedTypes = allowedFileTypes.split(",");
        for (String type : allowedTypes) {
            if (Objects.requireNonNull(file.getContentType()).equalsIgnoreCase(type.trim())) {
                return true;
            }
        }
        return false;
    }
}
