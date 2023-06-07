package synchronizationOfFile.synchronizationOfFile.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.PropertyEditorRegistrar;
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

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.tags.ParamTag;
import synchronizationOfFile.synchronizationOfFile.domain.FileInfo;
import synchronizationOfFile.synchronizationOfFile.domain.FileTransferObject;
import synchronizationOfFile.synchronizationOfFile.repository.FileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    FileRepository fileRepository;

    @PostMapping("/upload")
    public String upload(HttpServletResponse response, @RequestParam("uploadfile") MultipartFile[] uploadfile, @RequestParam("memberId") Long memberId, Model model, RedirectAttributes re) throws Exception {
        List<FileTransferObject> list = new ArrayList<>();
        re.addAttribute("memberId", memberId);

        for (MultipartFile file : uploadfile) {

            // 업로드 할 파일이 없는 상황
            if(file.isEmpty()) {
                ScriptUtils.alertAndBackPage(response, "파일을 등록해 주세요.");
                return null;
            } else {

                // 파일 업로드 과정
                FileTransferObject dto = new FileTransferObject(file.getOriginalFilename(), file.getContentType());
                list.add(dto);

                // db에 파일 저장 정보 입력
                FileInfo fileName = new FileInfo();
                fileName.setName(file.getOriginalFilename());
                fileName.setMemberId(memberId);
                fileName.setCreatedAt(LocalDateTime.now());
                fileName.setUpdatedAt(LocalDateTime.now());
                fileName.setType(file.getContentType());

                fileRepository.save(fileName);
                File newFileName = new File(dto.getFileName());

                try {
                    file.transferTo(newFileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        model.addAttribute("files", fileRepository.findAll());
        // 업로드 성공 시 해당 페이지를 redirect 물론 본인의 memberId도 포함
        return "redirect:/main";
    }

    @Value("${spring.servlet.multipart.location}")
    String filePath;

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@ModelAttribute FileTransferObject dto, @RequestParam("memberId") Long memberId) throws IOException {
        // 각 멤버당 파일 담당 디렉토리가 있기에 멤버의 아이디를 같이 참조한다.
//        Path path = Paths.get(filePath + "/" + memberId + "/" + dto.getFileName());

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
