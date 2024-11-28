package com.itachialy.moji_store.controller;

<<<<<<< HEAD
import com.itachialy.moji_store.model.Product;
=======
import com.itachialy.moji_store.dto.ProductDTO;
import com.itachialy.moji_store.model.Account;
import com.itachialy.moji_store.model.Product;
import com.itachialy.moji_store.service.IAccountManageService;
>>>>>>> 56bc2d601bfd90cbdaa0c42a3b3bdfe48f02f270
import com.itachialy.moji_store.service.ICategoryService;
import com.itachialy.moji_store.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;

import java.util.Map;
=======
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
>>>>>>> 56bc2d601bfd90cbdaa0c42a3b3bdfe48f02f270

@Controller
@RequestMapping("/admin")
public class AdminControlller {
    private final IProductService iProductService;
    private final ICategoryService iCategoryService;
<<<<<<< HEAD
    @Autowired
    public AdminControlller(IProductService iProductService, ICategoryService iCategoryService) {
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
=======
    private final IAccountManageService iAccountManageService;
    @Autowired
    public AdminControlller(IProductService iProductService, ICategoryService iCategoryService, IAccountManageService iAccountManageService) {
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
        this.iAccountManageService = iAccountManageService;
>>>>>>> 56bc2d601bfd90cbdaa0c42a3b3bdfe48f02f270
    }



    @GetMapping("")
<<<<<<< HEAD
    public String showControlPanel(Model model){
        model.addAttribute("list", iProductService.findAll());
=======
    public String showControlPanel(Model model) {
        List<Product> productList = iProductService.findAll();
        List<Account> accountList = iAccountManageService.findAll();
        model.addAttribute("list", productList);
        model.addAttribute("productCount", productList.size());
        model.addAttribute("accountCount", accountList.size());
>>>>>>> 56bc2d601bfd90cbdaa0c42a3b3bdfe48f02f270
        return "/admin/home_admin";
    }

    @GetMapping("/product-list")
    public String listProducts(@RequestParam(defaultValue = "1") int page, Model model) {
        Page<Product> productPage = iProductService.findAll(PageRequest.of(page - 1, 5));
<<<<<<< HEAD
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("page", Map.of(
                "currentPage", productPage.getNumber() + 1,
                "totalPages", productPage.getTotalPages()));
=======
        int totalPages = productPage.getTotalPages();
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", productPage.getNumber() + 1);
        model.addAttribute("totalPages", totalPages > 0 ? totalPages : 1); // đảm bảo có ít nhất 1 trang
>>>>>>> 56bc2d601bfd90cbdaa0c42a3b3bdfe48f02f270
        return "admin/list_product";
    }

    @GetMapping("/show-create")
    public String showCreate(Model model) {
<<<<<<< HEAD
        model.addAttribute("products", new Product());
        model.addAttribute("listC", iCategoryService.findAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("products") Product product, Model model) {

        return "redirect:/";
    }
=======
        model.addAttribute("products", new ProductDTO());
        model.addAttribute("categories", iCategoryService.findAll());
        return "product/create";
    }
>>>>>>> 56bc2d601bfd90cbdaa0c42a3b3bdfe48f02f270

@PostMapping("/create")
public String createProduct(@Valid @ModelAttribute("products") ProductDTO productDTO,
                            BindingResult result,
                            @RequestParam("ImageProducts") MultipartFile image) {
    if (result.hasErrors()) {
        return "product/create";
    }

    if (!image.isEmpty()) {
            try {
                String imageName = saveImageStatic(image);
                productDTO.setImageProduct("/images/" + imageName);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    Product product = new Product();
    BeanUtils.copyProperties(productDTO, product);
    iProductService.save(product);

    return "redirect:/admin/product-list";
}


    private String saveImageStatic(MultipartFile image) throws IOException {

        Path dirImages = Paths.get("D:/Project_M4/Project_M4/Moji_Store/images/");
        if (!Files.exists(dirImages)) {
            Files.createDirectories(dirImages);
        }

        String newFileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(image.getOriginalFilename());

        Path pathFileUpload = dirImages.resolve(newFileName);
        Files.copy(image.getInputStream(), pathFileUpload,
                StandardCopyOption.REPLACE_EXISTING);
        return newFileName;
    }

    @GetMapping("/show-update/{id}")
    public String showUpdate(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = iProductService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("products", product.get());
        } else {
            model.addAttribute("error", "Không tìm thấy sản phẩm.");
        }
        model.addAttribute("categories", iCategoryService.findAll());
        return "product/update";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @Valid @ModelAttribute("products") Product product,
                                BindingResult result,
                                @RequestParam("image") MultipartFile image,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "product/update";
        }

        if (!image.isEmpty()) {
            try {
                String imageName = saveImageStatic(image);
                product.setImageProduct("/images/" + imageName);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        product.setIdProduct(id);
        iProductService.save(product);
        redirectAttributes.addFlashAttribute("edit", "Cập nhật sản phẩm thành công.");

        return "redirect:/admin/product-list";
    }

    @GetMapping("delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Product> existProduct = iProductService.findById(id);
        if (existProduct.isPresent()) {
            iProductService.deleteById(id);
            redirectAttributes.addFlashAttribute("del", "Xóa sản phẩm thành công.");
        }
        return "redirect:/admin/product-list";
    }
}


