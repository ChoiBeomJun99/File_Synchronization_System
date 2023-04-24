package synchronizationOfFile.synchronizationOfFile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import synchronizationOfFile.synchronizationOfFile.domain.FileInfo;
import synchronizationOfFile.synchronizationOfFile.domain.FileTransferObject;
import synchronizationOfFile.synchronizationOfFile.repository.FileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    FileRepository fileRepository;

    @PostMapping("/upload")
    public String upload(@RequestParam("uploadfile") MultipartFile[] uploadfile, Model model) {
        List<FileTransferObject> list = new ArrayList<>();

        for (MultipartFile file : uploadfile) {
            FileTransferObject dto = new FileTransferObject(file.getOriginalFilename(), file.getContentType());
            list.add(dto);


            FileInfo fileName = new FileInfo();
            fileName.setName(file.getOriginalFilename());
            fileName.setType(file.getContentType());

            fileRepository.save(fileName);

            File newFileName = new File(dto.getFileName());

            try {
                file.transferTo(newFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("files", fileRepository.findAll());
        return "main";
    }

    @Value("${spring.servlet.multipart.location}")
    String filePath;

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@ModelAttribute FileTransferObject dto) throws IOException {
        System.out.println("filePath : " + filePath);
        Path path = Paths.get(filePath + "/" + dto.getFileName());

        String contentType = Files.probeContentType(path);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentDisposition(
                        ContentDisposition.builder("attachment")
                        .filename(dto.getFileName(), StandardCharsets.UTF_8)
                        .build());

        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
