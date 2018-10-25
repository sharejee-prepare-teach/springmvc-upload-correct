package com.basicservlet.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rith on 6/22/2018.
 */
@Controller
public class FileUploadController {
    @Autowired
    ServletContext context;

    @RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
    public ModelAndView fileUploadPage() {
        FileModel file = new FileModel();
        ModelAndView modelAndView = new ModelAndView("fileUpload", "command", file);
        return modelAndView;
    }

    @RequestMapping(value="/fileUploadPage", method = RequestMethod.POST)
    public String fileUpload(@Validated FileModel file, BindingResult result, ModelMap model) throws IOException {
        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "fileUploadPage";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = file.getFile();
            //String uploadPath = context.getRealPath("") + File.separator + "temp" + File.separator;
            String uploadPath = "D:\\home\\jee\\prepare-sharing\\springmvc-upload-correct\\src\\main\\webapp\\WEB-INF\\images\\";
            //Now do something with file...
            String fileName = multipartFile.getOriginalFilename();
            File fileRead = new File("D:\\home\\jee\\prepare-sharing\\springmvc-upload-correct\\src\\main\\webapp\\WEB-INF\\images\\"+fileName);
            if (fileRead.exists() == true) {
                System.out.println("Equal!");
            }
            FileCopyUtils.copy(file.getFile().getBytes(), new File(uploadPath+file.getFile().getOriginalFilename()));

            model.addAttribute("fileName", fileName);


            return "success";
        }
    }
}
