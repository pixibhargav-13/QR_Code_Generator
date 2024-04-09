package com.bhargav.qr_code_generator.controller;

import jakarta.servlet.http.HttpServletResponse;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
public class QRCodeController {

    @GetMapping("/generateQRCode")
    public void generateQRCode(
            @RequestParam String url,
            @RequestParam(defaultValue = "png") String imageType,
            @RequestParam(defaultValue = "QR_Code") String fileName,
            HttpServletResponse response) throws IOException {

        // Generate QR code based on URL parameter
        QRCode qrCode = QRCode.from(url);

        // Set image type (default is PNG)
        if ("jpg".equalsIgnoreCase(imageType)) {
            qrCode.to(ImageType.JPG);
        } else if ("gif".equalsIgnoreCase(imageType)) {
            qrCode.to(ImageType.GIF);
        } else {
            qrCode.to(ImageType.PNG);
        }

        // Write QR code to response
        response.setContentType("image/" + imageType);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "." + imageType + "\"");
        qrCode.writeTo(response.getOutputStream());
    }
}
