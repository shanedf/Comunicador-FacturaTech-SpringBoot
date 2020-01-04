package com.featherlopez.emitirfactura.controllers;

import com.featherlopez.emitirfactura.services.UploadInvoice;
import defaultPackage.Response_ws;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@CrossOrigin
@Controller
//@RequestMapping("/api/v1/factura")
public class Factura {

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(/*@RequestParam("ftUsername") String ftUser, @RequestParam("ftPassword") String ftPass, */@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        System.out.println("Controller hit...");
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            UploadInvoice uploadInvoice = new UploadInvoice();
            Optional<Response_ws> response = uploadInvoice.uploadInvoiceFile("Ftorotestca057", "57A75350766AF1ACF8C48518E6E97FC07D3A5D7FE578582A738CC617F1EEADEC", bytes);

            redirectAttributes.addFlashAttribute("message",
                    response.isEmpty() ? "error" : response.get().getSuccess() + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}
